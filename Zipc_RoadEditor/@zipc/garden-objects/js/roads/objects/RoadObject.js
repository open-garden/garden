import * as THREE from 'three';
import Delaunator from 'delaunator';

import {
    createCurve3,
    createEulerFromQuaternion,
    positionAtOffset
} from '../../extras/CurveUtils';

import { AbstractWay } from './AbstractWay.js';
import { LaneObject } from './LaneObject.js'
import { ReferenceLineObject } from './ReferenceLineObject.js'

function RoadObject() {

    AbstractWay.call(this);

    this.addEventListener('updateConnection', this.updateConnection);


    this.type = 'Road';

    this.curve3 = new THREE.CatmullRomCurve3();

    this.negateCurve3 = new THREE.CatmullRomCurve3();

    this.leftEdgeOffset = 0;

    this.rightEdgeOffset = 0;

    this.leftEdgeCurve3 = new THREE.CatmullRomCurve3();

    this.rightEdgeCurve3 = new THREE.CatmullRomCurve3();

    this.refLine = new ReferenceLineObject(new THREE.BufferGeometry(), new THREE.LineBasicMaterial({ color: 0x000000 }));

    this.refLine.translateY(0.3);

    this.edge = new THREE.LineSegments(new THREE.BufferGeometry(), new THREE.LineBasicMaterial({ color: '#303030', linewidth: 0.01, transparent: true, opacity: 0.0 }));

    this.add(this.edge);
    this.add(this.refLine);

}

RoadObject.laneIndexFormatter = new Intl.NumberFormat('ja', {
    minimumIntegerDigits: 2,
    minimumFractionDigits: 0,
    maximumFractionDigits: 0,
    useGrouping: false
});

RoadObject.prototype = Object.assign(Object.create(AbstractWay.prototype), {

    constructor: RoadObject,

    makeConnection: function (object, reverse) {

        var connectionInfo = reverse ? object.getNegative() : object.getPositive();

        this.setRotationFromQuaternion(connectionInfo.rotation);

        this.position.copy(connectionInfo.position);

        this.updateMatrixWorld(true);

        return this;

    },

    getPositive: function () {

        var rotationY, rotationZ;

        var position = this.localToWorld(this.getObjectByName('C-00').roadMarkLineCurve3.getPoint(1));

        var v0 = this.curve3.getTangentAt(1)/*.applyEuler(this.rotation)*/;
        var v1 = this.curve3.getTangentAt(0)/*.applyEuler(this.rotation)*/;
        if (this.metalData.reverse) {
            rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v0.x, v0.y, v0.z), new THREE.Vector3(v1.x, v1.y, v1.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? -1 : 1);
        } else {
            const direction = this.parent.direction;
            let leftPosition = this.localToWorld(this.leftEdgeCurve3.getPoint(direction === 'left' ? 1 : 0));
            let rightPosition = this.localToWorld(this.rightEdgeCurve3.getPoint(direction === 'left' ? 0 : 1));
            let vec = new THREE.Vector3().subVectors(rightPosition, leftPosition).normalize();
            let dir = new THREE.Vector3().crossVectors(new THREE.Vector3(0, 1, 0), vec).normalize();
            rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(1, 0, 0), new THREE.Vector3(dir.x, dir.y, dir.z))).y;

            //rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v1.x, v1.y, v1.z), new THREE.Vector3(v0.x, v0.y, v0.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            //rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v1.x, v1.y, v1.z), new THREE.Vector3(v0.x, v0.y, v0.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? 1 : -1);
        }


        var positive = {
            position: position,
            rotationY: rotationY,
            rotationZ: rotationZ

        };

        return positive;

    },

    getEdgePositions: function (type) {

        if (type === 'predecessor') {
            return {
                left: this.localToWorld(this.leftEdgeCurve3.getPoint(0)),
                right: this.localToWorld(this.rightEdgeCurve3.getPoint(1))
            };
        } else {
            return {
                left: this.localToWorld(this.leftEdgeCurve3.getPoint(1)),
                right: this.localToWorld(this.rightEdgeCurve3.getPoint(0))
            };
        }

    },

    getNegative: function () {

        var rotationY, rotationZ;
        var position = this.localToWorld(this.getObjectByName('C-00').roadMarkLineCurve3.getPoint(0));

        var v0 = this.curve3.getTangentAt(1).applyEuler(this.rotation);
        var v1 = this.curve3.getTangentAt(0).applyEuler(this.rotation);


        if (this.metalData.reverse) {
            rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(-v0.x, -v0.y, -v0.z), new THREE.Vector3(-v1.x, -v1.y, -v1.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            rotationZ = new THREE.Vector3(v1.x, 0, v1.z).angleTo(v1) * (v1.y > 0 ? 1 : -1);
        } else {
            rotationY = createEulerFromQuaternion(this.getWorldQuaternion(new THREE.Quaternion())).y;
            rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? 1 : -1);
        }

        var negative = {
            position: position,
            rotationY: rotationY,
            rotationZ: rotationZ,
        };

        return negative;

    },

    updateOrCreateLane: function (laneData, offsetValue) {
        if (laneData.id === 'DefaultCenterLane') return;
        var laneObject = this.children.find(child =>
            child.type === 'Lane'
            && child.id === laneData.objectId
        );

        if (laneObject === undefined) {
            laneObject = new LaneObject(this);
            laneObject.parent = this;

            if (laneData.sprite01 !== undefined) {
                laneObject.sprite01.basePoint = laneData.sprite01.basePoint;
                laneObject.sprite01.position2.copy(new THREE.Vector2(laneData.sprite01.position2.x, laneData.sprite01.position2.y));
                laneObject.sprite01.tangent.copy(new THREE.Vector2(laneData.sprite01.tangent.x, laneData.sprite01.tangent.y));
            }
            if (laneData.sprite02 !== undefined) {
                laneObject.sprite02.basePoint = laneData.sprite02.basePoint;
                laneObject.sprite02.position2.copy(new THREE.Vector2(laneData.sprite02.position2.x, laneData.sprite02.position2.y));
                laneObject.sprite02.tangent.copy(new THREE.Vector2(laneData.sprite02.tangent.x, laneData.sprite02.tangent.y));
            }
            if (laneData.spriteEdge !== undefined) {
                laneObject.spriteEdge.offsetType = laneData.spriteEdge.offsetType;
                laneObject.spriteEdge.offsetInward = laneData.spriteEdge.offsetInward;
            }
            this.children.push(laneObject);
        }
        laneObject.childIndex = laneData.childIndex;
        laneObject.laneIndex = laneData.laneIndex;
        laneObject.laneOffset = -offsetValue;

        laneObject.setFromMetalData(laneData);
    },

    computeOffset: function () {
        var data = this.metalData;
        var centerLanes = data.lanes.center;
        var leftLanes = data.lanes.left;
        var rightLanes = data.lanes.right;
        const direction = this.parent.direction;

        const offsets = {};
        // 左車線
        let width = centerLanes[0].width;
        let offset = width / 2;
        for (let l = leftLanes.length, i = l - 1; i >= 0; i--) {
            if (l - i !== 1) {
                offset += leftLanes[i + 1].width;
            }
            offsets[`L-${RoadObject.laneIndexFormatter.format(l - i)}`] = offset;
        }
        // 右車線
        offset = -width / 2;
        for (let i = 0, l = rightLanes.length; i < l; i++) {
            if (i !== 0) {
                offset -= rightLanes[i - 1].width;
            }
            offsets[`R-${RoadObject.laneIndexFormatter.format(i + 1)}`] = offset;
        }

        return offsets;
    },

    computeStructure: function () {
        // 基準の曲線を作成する
        var data = this.metalData;
        const direction = this.parent.direction;
        this.curve3 = createCurve3(data);
        this.negateCurve3 = this.curve3.clone();
        this.negateCurve3.negate();

        // 各車線のOffset値を計算する
        var centerLanes = data.lanes.center;
        var leftLanes = data.lanes.left;
        var rightLanes = data.lanes.right;

        let centerLaneWidth = 0;
        let childIndex = 0;
        // 左車線
        for (let l = leftLanes.length, i = l - 1; i >= 0; i--) {
            leftLanes[i].id = `L-${RoadObject.laneIndexFormatter.format(l - i)}`;
            leftLanes[i].childIndex = l - ++childIndex;
            leftLanes[i].laneIndex = l - i;
        }
        // 中央車線
        for (let i = 0, l = centerLanes.length; i < l; i++) {
            centerLanes[i].id = 'C-00';
            centerLanes[i].childIndex = childIndex++;
            centerLanes[i].laneIndex = 0;
            centerLaneWidth = centerLanes[i].width;
            break;
        }
        // 右車線
        for (let i = 0, l = rightLanes.length; i < l; i++) {
            rightLanes[i].id = `R-${RoadObject.laneIndexFormatter.format(i + 1)}`;
            rightLanes[i].childIndex = childIndex++;
            rightLanes[i].laneIndex = -(i + 1);
        }

        // #############################################################
        // #############################################################


        let offsets = this.computeOffset();

        // 車線の作成
        for (let lane of centerLanes) {
            this.updateOrCreateLane(lane, offsets[lane.id])
            //delete lane['newWidthStart'];
            break;
        }
        for (let i = leftLanes.length - 1; i >= 0; i--) {
            const lane = leftLanes[i];
            if (lane.spriteEdge !== undefined
                && i - 1 >= 0) {
                this.updateOrCreateLane(leftLanes[i - 1], offsets[leftLanes[i - 1].id])
            }
            this.updateOrCreateLane(leftLanes[i], offsets[leftLanes[i].id])
        }
        for (let i = 0; i < rightLanes.length; i++) {
            const lane = rightLanes[i];
            if (lane.spriteEdge !== undefined
                && i + 1 < rightLanes.length) {
                this.updateOrCreateLane(rightLanes[i + 1], offsets[rightLanes[i + 1].id])
            }
            this.updateOrCreateLane(lane, offsets[lane.id])

        }
        this.children.sort(function (a, b) {
            if (a.type === 'Lane' && b.type === 'Lane') {
                return a.childIndex > b.childIndex ? 1 : -1;

            }
        });



        this.leftEdgeCurve3 = null;
        this.rightEdgeCurve3 = null;
        let leftEdgeOffset = 0, rightEdgeOffset = 0;
        if (leftLanes.length > 0) {
            const edgeLaneObject = this.getObjectByName(leftLanes[0].id);
            leftEdgeOffset = edgeLaneObject.laneOffset;
            const curve01Points = edgeLaneObject.curve01.points;
            let edgePoints = [];
            for (let c01p of curve01Points) {
                // edgePoints.push(edgeLaneObject.localToWorld(new THREE.Vector3().copy(c01p)));
                edgePoints.push(new THREE.Vector3(c01p.x, c01p.y, c01p.z + leftEdgeOffset));
            }
            this.leftEdgeCurve3 = new THREE.CatmullRomCurve3(edgePoints);
            this.leftEdgeCurve3.arcLengthDivisions = edgePoints.length - 1;
        }
        if (rightLanes.length > 0) {
            const edgeLaneObject = this.getObjectByName(rightLanes[rightLanes.length - 1].id);
            rightEdgeOffset = edgeLaneObject.laneOffset;
            const curve01Points = edgeLaneObject.curve01.points;
            let edgePoints = [];
            for (let c01p of curve01Points) {
                // edgePoints.push(edgeLaneObject.localToWorld(new THREE.Vector3().copy(c01p)));
                edgePoints.push(new THREE.Vector3(c01p.x, c01p.y, c01p.z + rightEdgeOffset));
            }
            this.rightEdgeCurve3 = new THREE.CatmullRomCurve3(edgePoints);
            this.rightEdgeCurve3.arcLengthDivisions = edgePoints.length - 1;
        }
        if (this.leftEdgeCurve3 === null) {
            if (direction === 'left') {
                this.leftEdgeCurve3 = positionAtOffset(this.curve3, data.length, centerLaneWidth / 2);
            } else {
                this.leftEdgeCurve3 = positionAtOffset(this.curve3, data.length, centerLaneWidth / 2, true);
            }
        }
        if (this.rightEdgeCurve3 === null) {
            if (direction === 'right') {
                this.rightEdgeCurve3 = positionAtOffset(this.curve3, data.length, -centerLaneWidth / 2);
            } else {
                this.rightEdgeCurve3 = positionAtOffset(this.curve3, data.length, -centerLaneWidth / 2, true);
            }
        }

        // #############################################
        var points3d = [];
        var pointIntersection = [];
        var pointsCount = this.curve3.arcLengthDivisions;
        const points01 = this.leftEdgeCurve3.points;
        const points02 = this.rightEdgeCurve3.points;
        for (let i = 0; i <= pointsCount; i++) {
            if (points01[i].distanceTo(points02[i]) <= 0.01) {
                pointIntersection.push(i * 2);
                pointIntersection.push(i * 2 + 1);
            }
            points3d.push(new THREE.Vector3(points01[i].x, points01[i].y, points01[i].z));
            points3d.push(new THREE.Vector3(points02[i].x, points02[i].y, points02[i].z));
        }

        var geom = new THREE.BufferGeometry().setFromPoints(points3d);

        // triangulate by [x, z]
        var indexDelaunay = Delaunator.from(
            points3d.map(v => {
                return [v.x, v.z];
            })
        );

        var meshIndex = []; // delaunay index => three.js index
        var a, b, c;
        for (let i = 0; i < indexDelaunay.triangles.length; i += 3) {
            a = indexDelaunay.triangles[i];
            b = indexDelaunay.triangles[i + 1];
            c = indexDelaunay.triangles[i + 2];
            if ((pointIntersection.includes(a) && b % 2 === c % 2)
                || (pointIntersection.includes(b) && a % 2 === c % 2)
                || (pointIntersection.includes(c) && a % 2 === b % 2)
                || (a % 2 === b % 2 && b % 2 === c % 2)
            ) {
                continue;
            }
            meshIndex.push(indexDelaunay.triangles[i]);
            meshIndex.push(indexDelaunay.triangles[i + 1]);
            meshIndex.push(indexDelaunay.triangles[i + 2]);
            // meshIndex.push(indexDelaunay.triangles[i]);
        }

        geom.setIndex(meshIndex); // add three.js index to the existing geometry
        geom.computeVertexNormals();
        // #############################################

        this.edge.geometry.dispose();
        this.edge.geometry = new THREE.EdgesGeometry(geom);

        if (data.point !== undefined) {

            var newPosition = new THREE.Vector3(data.point.x, data.point.y, data.point.z);
            this.position.copy(newPosition);

            var newRotation = new THREE.Euler(0, data.point.yaw * THREE.MathUtils.DEG2RAD, 0);
            this.rotation.copy(newRotation);
        }

        // ref線を更新する
        this.refLine.setFromCurve3(this.curve3);
        this.refLine.visible = false;

        // 路面表示を更新する。
        for (let child of this.children) {
            if (child.type === 'RoadMark.Sign') {
                child.computeGeometry();
            } else if (child.type === 'RoadMark.Crosswalk') {
                child.computeGeometry();
            }
        }

        for (let child of this.parent.children) {
            if (child.type === 'RoadMark.Line') {
                this.parent.editor.computeRoadMarkLineGeometry(child);
            }
        }

        // 自分の情報を更新する
        this.updateMatrixWorld(true);

    },

    setFromMetalData: function (data) {

        this.metalData = data;
        if (this.metalData.connection === undefined) {
            this.metalData.connection = { "road": "", "lane": "" };
        }
        this.name = data.id;
        this.computeStructure();

        return this;

    },

    updateConnection: function (event) {

        if (event.id === this.id) {
            var connectionInfo = event.connectionInfo;
            this['metalData']['rotationZ'] = connectionInfo.rotationZ;
            this['metalData']['point'] = { "x": connectionInfo.position.x, "y": connectionInfo.position.y, "z": connectionInfo.position.z, "roll": 0, "yaw": connectionInfo.rotationY * THREE.MathUtils.RAD2DEG, "pitch": 0 };
            this.computeStructure();
            this.updateMatrixWorld(true);
        }

        for (let item of this.connectedTo) {
            var connectionInfo = item['metalData']['reverse'] ? this.getNegative() : this.getPositive();
            item.dispatchEvent({
                type: 'updateConnection',
                id: item.id,
                connectionInfo: {
                    position: { x: connectionInfo.position.x, y: connectionInfo.position.y, z: connectionInfo.position.z },
                    rotationY: connectionInfo.rotationY,
                    rotationZ: connectionInfo.rotationZ
                }
            });
        }

        for (let child of this.children) {
            if (child.type !== 'Lane') continue;

            for (let item of child.connectedTo) {
                var connectionInfo = item['metalData']['reverse'] ? child.getNegative() : child.getPositive();
                item.dispatchEvent({
                    type: 'updateConnection',
                    id: item.id,
                    connectionInfo: {
                        position: { x: connectionInfo.position.x, y: connectionInfo.position.y, z: connectionInfo.position.z },
                        rotationY: connectionInfo.rotationY,
                        rotationZ: connectionInfo.rotationZ
                    }
                });
            }
        }
    }

});

export { RoadObject };