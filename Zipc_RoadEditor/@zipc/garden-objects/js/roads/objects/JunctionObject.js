import * as THREE from 'three';

import { AbstractWay } from './AbstractWay.js';
import { JunctionLaneObject } from './JunctionLaneObject.js'

function JunctionObject() {

    AbstractWay.call(this);

    this.type = 'Junction';

    this.plane = new THREE.Mesh(new THREE.BufferGeometry(), new THREE.MeshPhongMaterial({ color: AbstractWay.MESH_COLOR['driving'], side: THREE.DoubleSide, wireframe: this.isWireframeVisible() }));

    this.edge = new THREE.LineSegments(new THREE.BufferGeometry(), new THREE.LineBasicMaterial({ color: '#303030', linewidth: 0.01, transparent: true, opacity: 0.0 }));

    this.add(this.plane);
    this.add(this.edge);

}

JunctionObject.prototype = Object.assign(Object.create(AbstractWay.prototype), {

    constructor: JunctionObject,

    computeStructure: function () {

        var data = this.metalData;
        var junctionShapePoints = data.junctionShapePoints || [];

        var shape = new THREE.Shape();
        if (junctionShapePoints.length > 2) {
            var edgePoints = JSON.parse(JSON.stringify(junctionShapePoints));
            var centerPoint = new THREE.Vector3();
            var junctionBox = new THREE.Box3().setFromPoints(edgePoints);
            junctionBox.getCenter(centerPoint);

            for (let ii = 0; ii < edgePoints.length; ii++) {
                // junctionShapePoints[ii].sub(centerPoint);
                edgePoints[ii].x -= centerPoint.x;
                edgePoints[ii].y -= centerPoint.y;
                edgePoints[ii].z -= centerPoint.z;
                edgePoints[ii].z *= -1;
            }

            if (data.edgeType === 'typea') {
                var min = junctionBox.min.clone().sub(centerPoint);
                var max = junctionBox.max.clone().sub(centerPoint);
                shape.moveTo(min.x, min.z);
                shape.lineTo(max.x, min.z);
                shape.lineTo(max.x, max.z);
                shape.lineTo(min.x, max.z);
                shape.lineTo(min.x, min.z);
            } else {
                shape.moveTo(edgePoints[0].x, edgePoints[0].z);
                for (let ii = 1; ii < edgePoints.length; ii++) {
                    if (ii % 2 == 1) {
                        shape.lineTo(edgePoints[ii].x, edgePoints[ii].z);
                    } else {
                        if (data.edgeType === 'typeb') {
                            shape.lineTo(edgePoints[ii].x, edgePoints[ii].z);
                        } else if (data.edgeType === 'typec') {
                            let line01 = new THREE.Line3(
                                new THREE.Vector3(edgePoints[ii - 2].x, edgePoints[ii - 2].y, edgePoints[ii - 2].z),
                                new THREE.Vector3(edgePoints[ii - 1].x, edgePoints[ii - 1].y, edgePoints[ii - 1].z));
                            let delta01 = line01.delta(new THREE.Vector3()).normalize();
                            let dir01 = new THREE.Vector3().crossVectors(new THREE.Vector3(0, 1, 0), delta01).normalize();
                            let dir11 = new THREE.Vector3().crossVectors(new THREE.Vector3(0, -1, 0), delta01).normalize();
                            let line02 = new THREE.Line3(
                                new THREE.Vector3(edgePoints[ii].x, edgePoints[ii].y, edgePoints[ii].z),
                                new THREE.Vector3(edgePoints[ii === edgePoints.length - 1 ? 1 : ii + 1].x, edgePoints[ii === edgePoints.length - 1 ? 1 : ii + 1].y, edgePoints[ii === edgePoints.length - 1 ? 1 : ii + 1].z));
                            let delta02 = line02.delta(new THREE.Vector3()).normalize();

                            let angle = delta01.angleTo(delta02);
                            if (angle <= 0.02 || Math.abs(Math.PI - angle) <= 0.02) {
                                shape.lineTo(edgePoints[ii].x, edgePoints[ii].z);
                            } else {
                                let ray01 = new THREE.Ray(line01.end.clone(), dir01);
                                let ray02 = new THREE.Ray(line01.end.clone(), dir11);
                                let plane = new THREE.Plane(delta02).translate(line02.start.clone());
                                let target = new THREE.Vector3();
                                if (Math.abs(Math.PI - delta01.angleTo(delta02)) <= 0.02) {
                                    target.addVectors(line01.end, line02.start).addScalar(0.5);
                                } else if (ray01.intersectsPlane(plane)) {
                                    ray01.intersectPlane(plane, target);
                                } else if (ray02.intersectsPlane(plane)) {
                                    ray02.intersectPlane(plane, target);
                                }
                                shape.quadraticCurveTo(
                                    target.x,
                                    target.z,
                                    edgePoints[ii].x,
                                    edgePoints[ii].z
                                );
                            }
                        } else {
                            shape.lineTo(edgePoints[ii].x, edgePoints[ii].z);
                        }
                    }
                }
            }
            this.position.copy(centerPoint);
        } else {
            shape.moveTo(0, 0);
            shape.lineTo(0, 0.01);
            shape.lineTo(0.01, 0.01);
            shape.lineTo(0.01, 0);
            shape.lineTo(0, 0);
        }

        var geom = new THREE.ShapeGeometry(shape);
        geom.rotateX(-90 * THREE.MathUtils.DEG2RAD);
        this.plane.geometry.dispose();
        this.edge.geometry.dispose();

        this.plane.geometry = geom;
        this.edge.geometry = new THREE.EdgesGeometry(geom);


        for (var i = this.children.length - 1; i >= 0; i--) {
            var child = this.children[i];
            if (child.type === 'JunctionLane') {
                this.remove(child);
            }
        }

        if (!data.hasOwnProperty('lanes')) data['lanes'] = [];
        for (var lane of data.lanes) {

            var newJunctionLane = new JunctionLaneObject(this, lane.id);

            newJunctionLane.setFromMetalData(lane);

            this.add(newJunctionLane);
        }

        this.plane.material.wireframe = this.isWireframeVisible();
        this.updateMatrixWorld(true);

    },

    setFromMetalData: function (data) {

        this.metalData = data;
        this.name = data.id;
        this.computeStructure();

        return this;

    }

});

export { JunctionObject }