import * as THREE from 'three';
import Delaunator from 'delaunator';

import {
    createEulerFromQuaternion,
    positionAtOffset,
    positionAtOffset111test,
    positionAtOffset222test,
    curveOffset,
    createExtrudeShape
} from '../../extras/CurveUtils';

import { AbstractWay } from './AbstractWay.js';
import { ReferenceLineObject } from './ReferenceLineObject.js'


const sprite_texture = new THREE.TextureLoader().load("textures/circle.png");
const sprite_material = new THREE.SpriteMaterial({ map: sprite_texture, depthTest: false, color: 0x0000ff });

function LaneObject(parentRoad) {

    AbstractWay.call(this);

    this.type = 'Lane';

    this.lanePosition = 'left';

    this.parentRoad = parentRoad;

    this.laneOffset = 0;

    this.curve3 = new THREE.CatmullRomCurve3();
    this.curve01 = new THREE.CatmullRomCurve3();
    this.curve02 = new THREE.CatmullRomCurve3();
    this.roadMarkLineCurve3 = new THREE.CatmullRomCurve3();

    this.edgeGeometry = new THREE.EdgesGeometry(new THREE.BufferGeometry().setAttribute('position', new THREE.BufferAttribute(new Float32Array(0 * 3), 3)));

    this.plane = new THREE.Mesh(new THREE.BufferGeometry(), new THREE.MeshPhongMaterial({ color: AbstractWay.MESH_COLOR['driving'], side: THREE.DoubleSide, wireframe: this.isWireframeVisible() }));

    this.refLine = new ReferenceLineObject(new THREE.BufferGeometry(), new THREE.LineBasicMaterial({ color: 0xff5100, opacity: 0.35 }));

    this.refLine.translateY(0.5);

    // ############################################
    this.sprite01 = new THREE.Sprite(sprite_material.clone());
    this.sprite01.type = 'ControlSprite';
    this.sprite01.name = 'sprite01';
    this.sprite01.basePoint = true;
    this.sprite01.position2 = new THREE.Vector2(0, 1);
    this.sprite01.tangent = new THREE.Vector3(1, 0, 0);
    this.sprite01.visible = this.isCtrlSpriteVisible();
    this.add(this.sprite01);

    // ####
    this.sprite02 = new THREE.Sprite(sprite_material.clone());
    this.sprite02.type = 'ControlSprite';
    this.sprite02.name = 'sprite02';
    this.sprite02.basePoint = false;
    this.sprite02.position2 = new THREE.Vector2(1, 1);
    this.sprite02.tangent = new THREE.Vector3(1, 0, 0);
    this.sprite02.visible = this.isCtrlSpriteVisible();
    this.add(this.sprite02);

    // ####
    this.spriteEdge = new THREE.Line(
        new THREE.BufferGeometry().setFromPoints([new THREE.Vector3(0, 0, 0), new THREE.Vector3(0, 0, 4)]),
        new THREE.LineBasicMaterial({
            color: 0x0000ff, side: THREE.DoubleSide, depthTest: false, transparent: true
        })
    );
    this.spriteEdge.offsetType = 'none';
    this.spriteEdge.offsetInward = false;
    this.spriteEdge.visible = this.isCtrlSpriteVisible();
    this.add(this.spriteEdge);

    this.waypointLineCurve3 = new THREE.CatmullRomCurve3();
    // ############################################

    this.extensionInfo = [];

    this.add(this.plane);
    this.add(this.refLine);
}

LaneObject.prototype = Object.assign(Object.create(AbstractWay.prototype), {

    constructor: LaneObject,

    getPositive: function () {

        var position, rotationY, rotationZ;

        var v0 = this.curve3.getTangentAt(1).applyEuler(this.rotation);
        var v1 = this.curve3.getTangentAt(0).applyEuler(this.rotation);
        if (this.metalData.position === this.getCurrentHand()) {
            var position = this.localToWorld(this.roadMarkLineCurve3.getPoint(0));
            //rotationY = createEulerFromQuaternion(this.getWorldQuaternion(new THREE.Quaternion())).y;
            rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v0.x, 0, v0.z), new THREE.Vector3(v1.x, 0, v1.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? -1 : 1);
        } else {
            var position = this.localToWorld(this.roadMarkLineCurve3.getPoint(1));
            //rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v1.x, 0, v1.z), new THREE.Vector3(v0.x, 0, v0.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v1.x, v1.y, v1.z), new THREE.Vector3(v0.x, v0.y, v0.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? 1 : -1);
            //rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? 1 : -1);
        }


        var positive = {
            position: position,
            rotationY: rotationY,
            rotationZ: rotationZ

        };

        return positive;

    },

    getNegative: function () {

        var position, rotationY, rotationZ;

        var v0 = this.curve3.getTangentAt(1).applyEuler(this.rotation);
        var v1 = this.curve3.getTangentAt(0).applyEuler(this.rotation);

        if (this.metalData.position === this.getCurrentHand()) {
            position = this.localToWorld(this.roadMarkLineCurve3.getPoint(1));
            //rotationY = createEulerFromQuaternion(this.getWorldQuaternion(new THREE.Quaternion())).y;
            //rotationY = v0.angleTo(new THREE.Vector3(-1, 0, 0));
            if (this.parentRoad.metalData.reverse) {
                rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v1.x, 0, v1.z), new THREE.Vector3(v0.x, 0, v0.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            } else {
                rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v0.x, 0, v0.z), new THREE.Vector3(-1, 0, 0)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            }
            rotationZ = new THREE.Vector3(v1.x, 0, v1.z).angleTo(v1) * (v1.y > 0 ? 1 : -1);
        } else {
            position = this.localToWorld(this.roadMarkLineCurve3.getPoint(0));
            if (this.parentRoad.metalData.reverse) {
                rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v0.x, 0, v0.z), new THREE.Vector3(v1.x, 0, v1.z)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            } else {
                rotationY = createEulerFromQuaternion(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(v1.x, 0, v1.z), new THREE.Vector3(1, 0, 0)).multiply(this.getWorldQuaternion(new THREE.Quaternion()))).y;
            }

            rotationZ = new THREE.Vector3(v0.x, 0, v0.z).angleTo(v0) * (v0.y > 0 ? 1 : -1);
        }

        var negative = {
            position: position,
            rotationY: rotationY,
            rotationZ: rotationZ,
        };

        return negative;

    },

    getRoadMarkLineCurve3: function () {
        let curve3 = this.roadMarkLineCurve3.clone();
        if (this.laneOffset === 0) {
            // curve3.setOffset(-this.metalData.width / 2, this.getCurrentHand());
            curve3 = curveOffset(curve3, -this.metalData.width / 2, this.getCurrentHand());
        } else {
            // curve3.setOffset((this.laneOffset < 0 ? -1 : 1) * (Math.abs(this.laneOffset) - this.metalData.width / 2), this.getCurrentHand());
            // curve3.setOffset(this.laneOffset, this.getCurrentHand());
            curve3 = curveOffset(curve3, this.laneOffset, this.getCurrentHand());
        }

        const result = [];
        const points = curve3.getPoints(curve3.arcLengthDivisions);
        for (let p of points) {
            result.push(new THREE.Vector3(p.x, p.y + 0.02, p.z).applyMatrix4(this.matrixWorld));
        }
        //
        return result;
    },

    getRoadMarkLineExtrudeGeometry: function (w, o, p, l, s, e, pr) {
        const lineWidth = w === undefined ? 0.15 : w;
        const lineOffset = o === undefined ? 0.0 : o;
        const linePosition = p === undefined ? 'right' : p;
        const start = s === undefined ? 0 : s;
        const end = e === undefined ? l : e;
        const precision = pr === undefined ? 1 : pr;

        if (this.name === 'C-00') {
            //console.warn(`curve01: ${this.curve01.points[0].x}, ${this.curve01.points[0].y}, ${this.curve01.points[0].z} => ${this.curve01.points[this.curve01.arcLengthDivisions].x}, ${this.curve01.points[this.curve01.arcLengthDivisions].y}, ${this.curve01.points[this.curve01.arcLengthDivisions].z}`);
            //console.warn(`curve02: ${this.curve02.points[0].x}, ${this.curve02.points[0].y}, ${this.curve02.points[0].z} => ${this.curve02.points[this.curve02.arcLengthDivisions].x}, ${this.curve02.points[this.curve02.arcLengthDivisions].y}, ${this.curve02.points[this.curve02.arcLengthDivisions].z}`);
        }

        let curve3;
        if (linePosition === 'right') {
            curve3 = positionAtOffset(this.curve02, this.curve02.arcLengthDivisions, lineOffset);
        } else {
            curve3 = positionAtOffset(this.curve01, this.curve01.arcLengthDivisions, lineOffset);
        }


        let extrudeSettings = {
            shape: createExtrudeShape(lineWidth / 2, -lineWidth / 2, 0),
            options: { depth: 0, bevelEnabled: false, steps: 1, curveSegments: 1, extrudePath: null }
        };
        const dirValue = linePosition === 'right' ? 1 : -1;

        let newPoints = [];
        let curveSegments = Math.floor(end) - Math.ceil(start);
        const arcLengthDivisions = curve3.arcLengthDivisions / precision;
        if (start > Math.floor(start)) {
            newPoints.push(curve3.getPointAt(start / arcLengthDivisions));
            curveSegments++;
        }
        for (let i = Math.ceil(start), ii = Math.floor(end); i <= ii; i++) {
            newPoints.push(curve3.getPointAt(i / arcLengthDivisions));
        }
        if (end > Math.floor(end)) {
            newPoints.push(curve3.getPointAt(end / arcLengthDivisions));
            curveSegments++;
        }
        /*const points = curve3.getSpacedPoints(l);
        for (let i = start; i <= end; i++) {
            newPoints.push(points[i].clone());
        }*/
        let offsetCurve3 = new THREE.CatmullRomCurve3(newPoints);


        extrudeSettings.options.steps = curveSegments;
        extrudeSettings.options.curveSegments = curveSegments;
        extrudeSettings.options.extrudePath = offsetCurve3;
        let extrudeGeometry = new THREE.ExtrudeBufferGeometry(extrudeSettings.shape, extrudeSettings.options);
        extrudeGeometry.translate(0, 0.02, 0);
        extrudeGeometry.applyMatrix4(this.matrixWorld);
        //
        return extrudeGeometry;
    },

    getSilblingLaneSpriteInfo: function (silblingLaneIndex) {
        let spriteIndex = { 'lane': 'none', 'type': 'none', 'start': -1, 'end': -1 };

        const siblingLane = this.parent.children.find(child =>
            child.type === 'Lane'
            && child.laneIndex !== 0
            && child.laneIndex === silblingLaneIndex
            && child.spriteEdge.offsetType !== 'none'
            && child.metalData.type === 'driving'
        );
        if (siblingLane === undefined) {
            return spriteIndex;
        }
        if ((siblingLane.spriteEdge.offsetType === 'merge' && siblingLane.sprite02.position2.y < 0.5)
            || (siblingLane.spriteEdge.offsetType === 'branch' && siblingLane.sprite01.position2.y < 0.5)
            || (siblingLane.spriteEdge.offsetType === 'branch' && siblingLane.sprite01.position2.y < 0.5)) {
            spriteIndex.lane = siblingLane.name;
            spriteIndex.type = siblingLane.spriteEdge.offsetType;
            const x01 = Math.floor(this.waypointLineCurve3.points.length * (siblingLane.sprite01.position2.x || 0));
            const x02 = Math.floor(this.waypointLineCurve3.points.length * (siblingLane.sprite02.position2.x || 0));
            spriteIndex.start = x01;
            spriteIndex.end = x02;
        }

        return spriteIndex;
    },

    getSibling: function (laneData) {
        let bending = laneData.position === 'right' ? -1 : 1;  // reference lineの曲げる方向を -1, 1で表す
        if (this.spriteEdge.offsetInward) {
            bending *= -1;
        }
        let silblingLaneIndex = laneData.laneIndex + bending;
        const siblingLane = this.parent.children.find(child =>
            child.type === 'Lane'
            && child.laneIndex !== 0
            && child.laneIndex === silblingLaneIndex
            && child.metalData.type === 'driving'
        );
        return siblingLane;

    },

    getSiblingLane: function (laneData) {
        let siblingLane = this.getSibling(laneData);
        if (siblingLane === undefined) {
            this.spriteEdge.offsetInward = !this.spriteEdge.offsetInward;
            siblingLane = this.getSibling(laneData);
        }
        return siblingLane;
    },

    computeRefLine: function () {

        const data = this.metalData;
        const direction = this.parent.parent.direction;

        let points = [];
        if (data.position === 'center') {
            return;
        }

        const points01 = this.curve01.points;
        const points02 = this.curve02.points;

        if (this.spriteEdge.offsetType === 'merge' && this.sprite02.position2.y < 0.5) {
            let siblingLane = this.getSiblingLane(data);
            if (siblingLane !== undefined) {
                if (!this.spriteEdge.offsetInward) {
                    let tempCurve3 = positionAtOffset(this.curve01, this.curve01.arcLengthDivisions, (direction === 'right' ? 1 : -1) * data.width / 2);
                    const offsetWidth = (direction === 'right' ? -1 : 1) * (data.width + siblingLane.metalData.width) / 2;
                    this.waypointLineCurve3 = positionAtOffset222test(tempCurve3, tempCurve3.arcLengthDivisions, offsetWidth, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0, 0, this.sprite02.position2.x);
                    return;
                } else {
                    let tempCurve3 = positionAtOffset(this.curve02, this.curve02.arcLengthDivisions, (direction === 'right' ? -1 : 1) * data.width / 2);
                    const offsetWidth = (direction === 'right' ? 1 : -1) * (data.width + siblingLane.metalData.width) / 2;
                    this.waypointLineCurve3 = positionAtOffset222test(tempCurve3, tempCurve3.arcLengthDivisions, offsetWidth, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0, 0, this.sprite02.position2.x);
                    return;
                }
            }

        } else if (this.spriteEdge.offsetType === 'branch' && this.sprite01.position2.y < 0.5) {
            let siblingLane = this.getSiblingLane(data);
            if (siblingLane !== undefined) {
                if (!this.spriteEdge.offsetInward) {
                    let tempCurve3 = positionAtOffset(this.curve01, this.curve01.arcLengthDivisions, (direction === 'right' ? -1 : 1) * siblingLane.metalData.width / 2);
                    const offsetWidth = (direction === 'right' ? 1 : -1) * (data.width + siblingLane.metalData.width) / 2;
                    this.waypointLineCurve3 = positionAtOffset222test(tempCurve3, tempCurve3.arcLengthDivisions, offsetWidth, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0, this.sprite01.position2.x, 1);
                    return;
                } else {
                    let tempCurve3 = positionAtOffset(this.curve02, this.curve02.arcLengthDivisions, (direction === 'right' ? 1 : -1) * siblingLane.metalData.width / 2);
                    const offsetWidth = (direction === 'right' ? -1 : 1) * (data.width + siblingLane.metalData.width) / 2;
                    this.waypointLineCurve3 = positionAtOffset222test(tempCurve3, tempCurve3.arcLengthDivisions, offsetWidth, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0, this.sprite01.position2.x, 1);
                    return;
                }
            }

        }/*if (this.spriteEdge.offsetType === 'none')*/ else {
            for (let i = 0, l = Math.min(points01.length, points02.length); i < l; i++) {
                let lVec = new THREE.Vector3().addVectors(points01[i], points02[i]).multiplyScalar(0.5);
                points.push(lVec);
            }
            this.waypointLineCurve3 = new THREE.CatmullRomCurve3(points);
            this.waypointLineCurve3.arcLengthDivisions = points.length - 1;

            return;
        }
        this.waypointLineCurve3 = new THREE.CatmullRomCurve3([]);
    },

    computeWaypoint: function () {
        const data = this.metalData;
        const direction = this.parent.parent.direction;
        // 走行データ分析のデータ
        this.extensionInfo = [];

        if (data.position === 'center') {
            return;
        }
        const points01 = this.curve01.points;
        const points02 = this.curve02.points;
        const waypoints = this.waypointLineCurve3.points;

        if (this.spriteEdge.offsetType === 'merge' && this.sprite02.position2.y < 0.5) {
            let siblingLane = this.getSiblingLane(data);
            if (siblingLane == undefined) {
                return;
            }
            if (this.spriteEdge.offsetInward) {
                let tempCurve3 = positionAtOffset(this.curve01, this.curve01.arcLengthDivisions, (direction === 'right' ? 1 : -1) * data.width / 2);
                const offsetStart = Math.floor(tempCurve3.arcLengthDivisions * (this.sprite01.position2.x || 0));
                for (let i = 0, l = waypoints.length; i < l; i++) {
                    let vec = this.localToWorld(new THREE.Vector3(waypoints[i].x, waypoints[i].y, waypoints[i].z));
                    this.extensionInfo.push({ 'index': i, 'roadGeo': [i < offsetStart ? 'main roadway' : 'merge zone'], 'point': { 'x': vec.x, 'y': vec.y, 'z': vec.z } });
                }
                return;
            } else {
                let tempCurve3 = positionAtOffset(this.curve02, this.curve02.arcLengthDivisions, (direction === 'right' ? -1 : 1) * data.width / 2);
                const offsetStart = Math.floor(tempCurve3.arcLengthDivisions * (this.sprite01.position2.x || 0));
                for (let i = 0, l = waypoints.length; i < l; i++) {
                    let vec = this.localToWorld(new THREE.Vector3(waypoints[i].x, waypoints[i].y, waypoints[i].z));
                    this.extensionInfo.push({ 'index': i, 'roadGeo': [i < offsetStart ? 'main roadway' : 'merge zone'], 'point': { 'x': vec.x, 'y': vec.y, 'z': vec.z } });
                }
                return;
            }
        } else if (this.spriteEdge.offsetType === 'branch' && this.sprite01.position2.y < 0.5) {
            let siblingLane = this.getSiblingLane(data);
            if (siblingLane == undefined) {
                return;
            }
            if (this.spriteEdge.offsetInward) {
                let tempCurve3 = positionAtOffset(this.curve01, this.curve01.arcLengthDivisions, (direction === 'right' ? -1 : 1) * siblingLane.metalData.width / 2);
                const offsetStart = Math.floor(tempCurve3.arcLengthDivisions * ((this.sprite02.position2.x - this.sprite01.position2.x) || 0));
                for (let i = 0, l = waypoints.length; i < l; i++) {
                    let vec = this.localToWorld(new THREE.Vector3(waypoints[i].x, waypoints[i].y, waypoints[i].z));
                    this.extensionInfo.push({ 'index': i, 'roadGeo': [i > offsetStart ? 'main roadway' : 'departure zone'], 'point': { 'x': vec.x, 'y': vec.y, 'z': vec.z } });
                }
                return;
            } else {
                let tempCurve3 = positionAtOffset(this.curve02, this.curve02.arcLengthDivisions, (direction === 'right' ? 1 : -1) * siblingLane.metalData.width / 2);
                const offsetStart = Math.floor(tempCurve3.arcLengthDivisions * ((this.sprite02.position2.x - this.sprite01.position2.x) || 0));
                for (let i = 0, l = waypoints.length; i < l; i++) {
                    let vec = this.localToWorld(new THREE.Vector3(waypoints[i].x, waypoints[i].y, waypoints[i].z));
                    this.extensionInfo.push({ 'index': i, 'roadGeo': [i > offsetStart ? 'main roadway' : 'departure zone'], 'point': { 'x': vec.x, 'y': vec.y, 'z': vec.z } });
                }
                return;
            }

        } else {
            for (let i = 0, l = Math.min(points01.length, points02.length); i < l; i++) {
                let wVec = this.localToWorld(new THREE.Vector3(waypoints[i].x, waypoints[i].y, waypoints[i].z));
                this.extensionInfo.push({ 'index': i, 'roadGeo': [], 'point': { 'x': wVec.x, 'y': wVec.y, 'z': wVec.z } });
            }
            let silblingLaneIndexes01 = [this.laneIndex - 1, this.laneIndex + 1];
            for (let silblingLaneIndex of silblingLaneIndexes01) {
                let spriteInfo01 = this.getSilblingLaneSpriteInfo(silblingLaneIndex);
                if (spriteInfo01.type === 'none') {
                    continue;
                }
                for (let i = 0, l = this.extensionInfo.length; i < l; i++) {
                    let info = this.extensionInfo[i];
                    if (i >= spriteInfo01.start && i <= spriteInfo01.end) {
                        info.roadGeo.splice(0, 1, spriteInfo01.type === 'merge' ? 'merge zone' : 'departure zone');
                    }
                }
            }

            for (let i = 0, l = this.extensionInfo.length; i < l; i++) {
                let info = this.extensionInfo[i];
                if (info.roadGeo.length === 0) {
                    info.roadGeo.push('main roadway');
                }
            }

            return;
        }
    },

    update: function () {

        const data = this.metalData;
        const direction = this.parent.parent.direction;

        this.lanePosition = this.metalData.position;

        if (data.position === this.getCurrentHand()) {
            this.curve3 = this.parentRoad.negateCurve3.clone();
            this.roadMarkLineCurve3 = this.parentRoad.negateCurve3.clone();
        } else {
            this.curve3 = this.parentRoad.curve3.clone();
            this.roadMarkLineCurve3 = this.parentRoad.curve3.clone();
        }
        // this.curve3.setOffset(this.laneOffset, this.getCurrentHand());
        this.curve3 = curveOffset(this.curve3, this.laneOffset, this.getCurrentHand());
        // this.roadMarkLineCurve3.setOffset(this.laneOffset, this.getCurrentHand());
        this.roadMarkLineCurve3 = curveOffset(this.roadMarkLineCurve3, this.laneOffset, this.getCurrentHand());

        // #####################################################
        if (this.lanePosition !== 'center') {
            const siblingIndex = this.lanePosition === 'right' ? this.laneIndex + 1 : this.laneIndex - 1;
            const siblings = this.parent === null ? [] : this.parent.children.filter(c => c.type === 'Lane' && c.laneIndex !== 0);
            for (let sibling of siblings) {
                if (sibling.laneIndex === siblingIndex) {
                    const sw = sibling.metalData.width * (sibling.lanePosition === 'right' ? -1 : 1);
                    let pppp = [];

                    for (let innerPoint of sibling.curve01.points) {
                        pppp.push(new THREE.Vector3(innerPoint.x, innerPoint.y, innerPoint.z + sw));
                    }
                    this.curve3 = new THREE.CatmullRomCurve3(pppp);
                    this.curve3.arcLengthDivisions = pppp.length - 1;
                    this.roadMarkLineCurve3 = new THREE.CatmullRomCurve3(pppp);
                    this.roadMarkLineCurve3.arcLengthDivisions = pppp.length - 1;
                    break;
                }
            }
        }
        // #####################################################


        // #############################################
        var points3d = [];
        var pointIntersection = [];
        var pointsCount = this.curve3.arcLengthDivisions;
        let points01 = [], points02 = [];

        let x1 = Math.floor(pointsCount * this.sprite01.position2.x);
        let x2 = Math.floor(pointsCount * this.sprite02.position2.x);
        if (data.position === 'center') {
            let isEmpty01 = points01.length === 0;
            let isEmpty02 = points02.length === 0;
            if (isEmpty01) {
                points01 = positionAtOffset(this.curve3, pointsCount, data.width / 2).points;
            }
            this.curve01 = new THREE.CatmullRomCurve3(points01);
            this.curve01.arcLengthDivisions = points01.length - 1;
            if (isEmpty02) {
                points02 = positionAtOffset(this.curve3, pointsCount, -data.width / 2).points;
            }
            this.curve02 = new THREE.CatmullRomCurve3(points02);
            this.curve02.arcLengthDivisions = points02.length - 1;

            let points00 = [];
            for (let i = 0, l = Math.min(points01.length, points02.length); i < l; i++) {
                if (direction === 'right') {
                    points00.push(new THREE.Vector3().addVectors(points01[isEmpty01 ? i : l - i - 1], points02[i]).multiplyScalar(0.5));
                } else {
                    points00.push(new THREE.Vector3().addVectors(points01[i], points02[isEmpty02 ? i : l - i - 1]).multiplyScalar(0.5));
                }
            }
            // this.curve3 = new THREE.CatmullRomCurve3(points00);
            this.roadMarkLineCurve3 = new THREE.CatmullRomCurve3(points00);
            this.roadMarkLineCurve3.arcLengthDivisions = points00.length - 1;
        } else if (this.getCurrentDirection() === 'left') {
            /*if (this.spriteEdge.offsetInward) {
                this.curve01 = positionAtOffset111test(this.curve3, pointsCount, 0, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
                this.curve02 = positionAtOffset111test(this.curve3, pointsCount, -this.metalData.width, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
            } else {
                this.curve01 = positionAtOffset111test(this.curve3, pointsCount, this.metalData.width, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
                this.curve02 = positionAtOffset111test(this.curve3, pointsCount, 0, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
            }*/

            const isReverse = this.parent.metalData.reverse;
            this.curve01 = positionAtOffset111test(data.position === direction, isReverse, this.curve3, pointsCount, this.metalData.width, this.spriteEdge.offsetType,
                this.sprite01.position2.x, this.sprite01.position2.y,
                this.sprite02.position2.x, this.sprite02.position2.y);
            this.curve02 = positionAtOffset111test(data.position === direction, isReverse, this.curve3, pointsCount, 0, this.spriteEdge.offsetType,
                this.sprite01.position2.x, this.sprite01.position2.y,
                this.sprite02.position2.x, this.sprite02.position2.y);

            points01 = [...this.curve01.points];
            points02 = [...this.curve02.points];
            let newEdgePoints = [];
            switch (this.spriteEdge.offsetType) {
                case 'merge':
                    newEdgePoints = [...points01.slice(x1, x2 + 1).reverse(), ...points02.slice(x1, x2 + 1)];
                    break;
                case 'branch':
                    newEdgePoints = [...points01.slice(x1, x2 + 1), ...points02.slice(x1, x2 + 1).reverse()];
                    break;
                default:
                    newEdgePoints = [];
                    break;
            }

            if (newEdgePoints.length > 1 && this.sprite02.position2.y > 0) {
                newEdgePoints.push(new THREE.Vector3().copy(newEdgePoints[0]));
            }

            this.spriteEdge.geometry.dispose();
            this.spriteEdge.geometry = new THREE.BufferGeometry().setFromPoints(newEdgePoints);
            this.spriteEdge.geometry.computeBoundingSphere();
        } else {
            /*if (this.spriteEdge.offsetInward) {
                this.curve01 = positionAtOffset111test(this.curve3, pointsCount, 0, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
                this.curve02 = positionAtOffset111test(this.curve3, pointsCount, this.metalData.width, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
            } else {
                this.curve01 = positionAtOffset111test(this.curve3, pointsCount, -this.metalData.width, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
                this.curve02 = positionAtOffset111test(this.curve3, pointsCount, 0, this.spriteEdge.offsetType, this.sprite01.position2.x, 0, this.sprite02.position2.x, 0);
            }*/

            const isReverse = this.parent.metalData.reverse;
            this.curve01 = positionAtOffset111test(data.position === direction, isReverse, this.curve3, pointsCount, -this.metalData.width, this.spriteEdge.offsetType,
                this.sprite01.position2.x, this.sprite01.position2.y,
                this.sprite02.position2.x, this.sprite02.position2.y);
            this.curve02 = positionAtOffset111test(data.position === direction, isReverse, this.curve3, pointsCount, 0, this.spriteEdge.offsetType,
                this.sprite01.position2.x, this.sprite01.position2.y,
                this.sprite02.position2.x, this.sprite02.position2.y);

            points01 = [...this.curve01.points];
            points02 = [...this.curve02.points];
            let newEdgePoints = [];
            switch (this.spriteEdge.offsetType) {
                case 'merge':
                    newEdgePoints = [...points01.slice(x1, x2 + 1).reverse(), ...points02.slice(x1, x2 + 1)];
                    break;
                case 'branch':
                    newEdgePoints = [...points01.slice(x1, x2 + 1), ...points02.slice(x1, x2 + 1).reverse()];
                    break;
                default:
                    newEdgePoints = [];
                    break;
            }

            if (newEdgePoints.length > 1 && this.sprite02.position2.y > 0) {
                newEdgePoints.push(new THREE.Vector3().copy(newEdgePoints[0]));
            }

            this.spriteEdge.geometry.dispose();
            this.spriteEdge.geometry = new THREE.BufferGeometry().setFromPoints(newEdgePoints);
            this.spriteEdge.geometry.computeBoundingSphere();
        }

        // this.computeWaypoint();
        this.computeRefLine();
        // ############################################


        let startIndex = this.spriteEdge.offsetType === 'branch' ? x1 : 0;
        let endIndex = this.spriteEdge.offsetType === 'merge' ? x2 + 1 : points01.length;
        for (let i = startIndex; i < endIndex; i++) {
            if (points01[i].distanceTo(points02[i]) <= 0.01) {
                pointIntersection.push((i - startIndex) * 2);
                pointIntersection.push((i - startIndex) * 2 + 1);
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
        }

        geom.setIndex(meshIndex); // add three.js index to the existing geometry
        geom.computeVertexNormals();

        // #######################################################
        if (this.lanePosition !== 'center') {
            this.sprite01.position.copy(this.curve02.getPoint(Math.floor(pointsCount * this.sprite01.position2.x) / pointsCount));
            this.sprite01.tangent.copy(this.curve02.getTangent(Math.floor(pointsCount * this.sprite01.position2.x) / pointsCount));
            this.sprite02.position.copy(this.curve02.getPoint(Math.floor(pointsCount * this.sprite02.position2.x) / pointsCount));
            this.sprite02.tangent.copy(this.curve02.getTangent(Math.floor(pointsCount * this.sprite02.position2.x) / pointsCount));

            this.sprite01.visible = this.spriteEdge.offsetType === 'none' ? false : this.isCtrlSpriteVisible();
            this.sprite02.visible = this.spriteEdge.offsetType === 'none' ? false : this.isCtrlSpriteVisible();
            this.spriteEdge.visible = this.spriteEdge.offsetType === 'none' ? false : this.isCtrlSpriteVisible();

        } else if (this.lanePosition === 'center') {
            this.sprite01.visible = this.spriteEdge.offsetType === 'none' ? false : this.isCtrlSpriteVisible();
            this.sprite02.visible = this.spriteEdge.offsetType === 'none' ? false : this.isCtrlSpriteVisible();
            this.spriteEdge.visible = this.spriteEdge.offsetType === 'none' ? false : this.isCtrlSpriteVisible();
        }

        if (data.sprite01 !== undefined) {
            data.sprite01.basePoint = this.sprite01.basePoint;
            data.sprite01.position2.x = this.sprite01.position2.x;
            data.sprite01.position2.y = this.sprite01.position2.y;
            data.sprite01.tangent.x = this.sprite01.tangent.x;
            data.sprite01.tangent.y = this.sprite01.tangent.y;
        }
        if (data.sprite02 !== undefined) {
            data.sprite02.basePoint = this.sprite02.basePoint;
            data.sprite02.position2.x = this.sprite02.position2.x;
            data.sprite02.position2.y = this.sprite02.position2.y;
            data.sprite02.tangent.x = this.sprite02.tangent.x;
            data.sprite02.tangent.y = this.sprite02.tangent.y;
        }
        if (data.spriteEdge !== undefined) {
            data.spriteEdge.offsetType = this.spriteEdge.offsetType;
            data.spriteEdge.offsetInward = this.spriteEdge.offsetInward;
        }
        // #######################################################

        this.edgeGeometry = new THREE.EdgesGeometry(geom);
        this.plane.geometry.dispose();
        this.plane.geometry = geom;
        this.plane.material.color.set(AbstractWay.MESH_COLOR[data.type || 'driving']);
        if (this.waypointLineCurve3.points.length > 0) {
            this.refLine.setFromCurve3(this.waypointLineCurve3);
        } else {
            this.refLine.setFromCurve3(this.roadMarkLineCurve3);
        }


        this.refLine.visible = data.position !== 'center' && data.type === 'driving' ? this.isRefLineVisible() : false;
        this.position.copy(new THREE.Vector3(0, 0, this.laneOffset));

        // 路面表示を更新する。
        for (let child of this.children) {
            if (child.type === 'RoadMark.Sign') {
                child.computeGeometry();
            }
        }

        this.updateMatrixWorld(true);
    },

    setFromMetalData: function (data) {

        this.metalData = data;
        this.name = data.id;
        this.label = data.label;
        data.objectId = this.id;
        this.update();

        return this;

    },

    copy: function (source, recursive) {

        AbstractWay.prototype.copy.call(this, source, recursive);

        this.metalData = JSON.parse(JSON.stringify(source.metalData));;
        this.waypointLineCurve3 = source.waypointLineCurve3.clone();

        return this;

    },

});

export { LaneObject };