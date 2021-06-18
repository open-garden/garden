import * as THREE from 'three';

import { AbstractWay } from './AbstractWay.js';

const ARC_SEGMENTS = 200;

function TrajectoryObject(color) {

    AbstractWay.call(this);

    this.type = 'Trajectory';

    this.meshColor = new THREE.Color(color || 0x000000);

    this.modeLine = new THREE.Line(
        new THREE.BufferGeometry(),
        new THREE.LineBasicMaterial({
            color: '#ff0000',
            opacity: 0.35
        }));
    this.modeLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
    this.modeLine.type = 'ModeLine';
    this.modeLine.visible = true;

    this.averageLine = new THREE.Line(
        new THREE.BufferGeometry(),
        new THREE.LineBasicMaterial({
            color: '#00ff00',
            opacity: 0.35
        }));
    this.averageLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
    this.averageLine.type = 'AverageLine';
    this.averageLine.visible = true;

    this.edgeLine = new THREE.Line(
        new THREE.BufferGeometry(),
        new THREE.LineBasicMaterial({
            color: '#0000ff',
            opacity: 0.35
        }));
    this.edgeLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
    this.edgeLine.type = 'EdgeLine';
    this.edgeLine.visible = true;

    this.add(this.modeLine);
    this.add(this.averageLine);
    //this.add(this.edgeLine);

}

TrajectoryObject.prototype = Object.assign(Object.create(AbstractWay.prototype), {

    constructor: TrajectoryObject,

    computeStructure: function () {

        // this.edgeCurve = new THREE.CatmullRomCurve3();

        var data = this.metalData;

        var point = new THREE.Vector3();

        // Base Line
        var child;
        var i = this.children.length - 1;
        for (; i >= 0; i--) {
            child = this.children[i];
            if (child.type === 'BaseLine') {
                this.remove(child);
            }
        }

        // var baseColor = `#${this.meshColor.getHexString()}`;
        var baseColor = '#000000';
        for (var entity of data.routes) {
            var basePoints = entity.points || [];
            if (basePoints.length > 1) {
                var baseLine = new THREE.Line(
                    new THREE.BufferGeometry(),
                    new THREE.LineBasicMaterial({
                        color: baseColor,
                        opacity: 0.35
                    }));
                baseLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
                baseLine.type = 'BaseLine';
                baseLine.visible = true;
                this.add(baseLine);

                var positions = [];
                for (var p of basePoints) {
                    positions.push(new THREE.Vector3(p.x, p.y, p.z));
                }
                var curve = new THREE.CatmullRomCurve3(positions);
                var position = baseLine.geometry.attributes.position;
                for (var i = 0; i < ARC_SEGMENTS; i++) {
                    var t = i / (ARC_SEGMENTS - 1);
                    curve.getPoint(t, point);
                    position.setXYZ(i, point.x, point.y, point.z);
                }
                position.needsUpdate = true;
            }
        }

        // Mode Line
        var modeColor = data.mode !== null ? (data.mode.color || '#ff0000') : '#ff0000';
        var modePoints = data.mode.points || [];
        if (modePoints.length > 1) {
            var positions = [];
            for (var p of modePoints) {
                positions.push(new THREE.Vector3(p.x, p.y, p.z));
            }
            var curve = new THREE.CatmullRomCurve3(positions);
            var position = this.modeLine.geometry.attributes.position;
            for (var i = 0; i < ARC_SEGMENTS; i++) {
                var t = i / (ARC_SEGMENTS - 1);
                curve.getPoint(t, point);
                position.setXYZ(i, point.x, point.y, point.z);
            }
            position.needsUpdate = true;
            this.modeLine.material.color.set(modeColor);
        } else {
            this.modeLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
        }

        // Average Line
        var averageColor = data.average !== null ? (data.average.color || '#00ff00') : '#00ff00';
        var averagePoints = data.average.points || [];
        if (averagePoints.length > 1) {
            var positions = [];
            for (var p of averagePoints) {
                positions.push(new THREE.Vector3(p.x, p.y, p.z));
            }
            var curve = new THREE.CatmullRomCurve3(positions);
            var position = this.averageLine.geometry.attributes.position;
            for (var i = 0; i < ARC_SEGMENTS; i++) {
                var t = i / (ARC_SEGMENTS - 1);
                curve.getPoint(t, point);
                position.setXYZ(i, point.x, point.y, point.z);
            }
            position.needsUpdate = true;
            this.averageLine.material.color.set(averageColor);
        } else {
            this.averageLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
        }

        // Edge Line
        var edgeColor = data.edge !== null ? (data.edge.color || '#0000ff') : '#0000ff';
        var edgePoints = data.edge.points || [];
        if (edgePoints.length > 1) {
            var positions = [];
            for (var p of edgePoints) {
                positions.push(new THREE.Vector3(p.x, p.y, p.z));
            }
            var curve = new THREE.CatmullRomCurve3(positions);
            var position = this.edgeLine.geometry.attributes.position;
            for (var i = 0; i < ARC_SEGMENTS; i++) {
                var t = i / (ARC_SEGMENTS - 1);
                curve.getPoint(t, point);
                position.setXYZ(i, point.x, point.y, point.z);
            }
            position.needsUpdate = true;
            this.edgeLine.material.color.set(edgeColor);
        } else {
            this.edgeLine.geometry.setAttribute('position', new THREE.BufferAttribute(new Float32Array(ARC_SEGMENTS * 3), 3));
        }

        this.updateMatrixWorld(true);

    },

    setFromMetalData: function (data) {

        this.name = data.id;
        this.metalData = data;
        data.objectId = this.id;
        this.computeStructure();

        return this;

    }/*,

    copy: function (source) {

        THREE.Object3D.prototype.copy.call(this, source, false);

        this.metalData = JSON.parse(JSON.stringify(source.metalData));
        this.type = source.type;
        this.name = source.name;
        this.color = source.meshColor;

        for (var child of this.children) {
            child.remove();
        }

        this.routeWayPoints = source.routeWayPoints.clone();

        this.add(this.routeWayPoints);

        return this;

    }*/

});

export { TrajectoryObject };