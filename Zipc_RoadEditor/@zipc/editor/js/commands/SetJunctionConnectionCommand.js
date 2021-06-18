/**
 * 
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object GARDEN.AbstractWay
 * @param newValue Object
 * @constructor
 */
var SetJunctionConnectionCommand = function (editor, object, newValue) {

	Command.call(this, editor);

	this.type = 'SetJunctionConnectionCommand';
	this.name = 'Set Junction Connection';
	this.updatable = true;

	this.object = object;
	this.oldValue = (object !== undefined) ? object['metalData']['connections'] : [];
	this.newValue = newValue;

};

SetJunctionConnectionCommand.prototype = {

	execute: function () {
		let shapePoints = this.computeJunctionShapePoints(this.newValue);
		this.object.computeStructure(shapePoints);
		this.object.updateMatrixWorld(true);
		let lanes = this.computeJunctionLanes();
		this.object['metalData']['lanes'] = lanes;
		this.object.computeStructure();
		this.object.updateMatrixWorld(true);

		this.object.dispatchEvent({ type: 'updateConnection' });
		this.object.updateMatrixWorld(true);
		this.editor.signals.gardenRoadStructureChanged.dispatch();
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.sceneGraphChanged.dispatch();
	},

	undo: function () {
		let shapePoints = this.computeJunctionShapePoints(this.oldValue);
		this.object.computeStructure(shapePoints);
		this.object.updateMatrixWorld(true);
		let lanes = this.computeJunctionLanes();
		this.object['metalData']['lanes'] = lanes;
		this.object.computeStructure();
		this.object.updateMatrixWorld(true);

		this.object.dispatchEvent({ type: 'updateConnection' });
		this.object.updateMatrixWorld(true);
		this.editor.signals.gardenRoadStructureChanged.dispatch();
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.sceneGraphChanged.dispatch();
	},

	computeJunctionShapePoints: function (dataValue) {
		var child;
		for (var i = this.object.children.length - 1; i >= 0; i--) {
			child = this.object.children[i];
			if (child.type === 'JunctionLane') {
				this.editor.removeObject(child);
			}
		}

		this.object['metalData']['connections'] = dataValue;
		this.object['metalData']['junctionShapePoints'] = [];
		this.object['metalData']['junctionIntersects'] = [];

		let tempInfo = [];
		var junctionShapePoints = this.object['metalData']['junctionShapePoints'];
		for (var connection of dataValue) {
			if (connection.objectId === undefined) {
				continue;
			}

			var connectionObject = this.editor.scene.getObjectById(parseInt(connection.objectId), true);
			if (connectionObject === undefined || connectionObject.type !== 'Road') {
				continue;
			}

			var edgePosition = connectionObject.getEdgePositions(connection.type);
			if (connection.type === 'predecessor') {
				junctionShapePoints.push(edgePosition.left);
				junctionShapePoints.push(edgePosition.right);
				tempInfo.push({ name: connectionObject.name, p1: edgePosition.left, p2: edgePosition.right });
			} else { // 'successor'
				junctionShapePoints.push(edgePosition.right);
				junctionShapePoints.push(edgePosition.left);
				tempInfo.push({ name: connectionObject.name, p1: edgePosition.right, p2: edgePosition.left });
			}
		}

		if (junctionShapePoints.length > 2) {
			for (let i = 0, l = tempInfo.length; i < l; i++) {
				let info01 = tempInfo[i];
				let info02 = tempInfo[(i + 1) % l];
				let line = new THREE.Line3(info01.p1, info02.p1);
				let plane = new THREE.Plane().setFromCoplanarPoints(info01.p2, info02.p2, new THREE.Vector3(info01.p2.x, info01.p2.y + 1, info01.p2.z));
				let delta = plane.normal.angleTo(line.delta(new THREE.Vector3()).normalize()) % (Math.PI / 2);
				if (!plane.intersectsLine(line)
					&& !((plane.distanceToPoint(line.start) <= 0.01 ^ plane.distanceToPoint(line.end) < 0.01))) {
					this.object['metalData']['junctionIntersects'].push(`${info01.name} -> ${info02.name}`);
				}
			}

			junctionShapePoints.push(junctionShapePoints[0].clone());
		}

		return junctionShapePoints;
	},

	computeJunctionLanes: function () {
		var newLanes = [];
		var data = this.object.metalData;
		var laneMaps = { 'incoming': [], 'outgoing': [] };

		for (var connection of data.connections) {
			if (connection.objectId === undefined || connection.objectId === null || connection.objectId === '') {
				continue;
			}
			var connectionObject = this.editor.scene.getObjectById(parseInt(connection.objectId), true);
			if (connectionObject !== undefined && connectionObject !== null) {
				for (var child of connectionObject.children) {
					if (child.type !== 'Lane' || child.metalData.position === 'center' || !(child.metalData.type === 'driving' || child.metalData.type === 'ramp')) continue;

					var leftHand = connectionObject.getCurrentHand();
					if (connection.type === 'predecessor') {
						if (child.metalData.position === leftHand) {
							if (child.spriteEdge.offsetType === 'merge' && (child.sprite02.position2.x < 1 || child.sprite02.position2.y < 0.5)) {
								continue;
							}
						} else {
							if (child.spriteEdge.offsetType === 'branch' && (child.sprite01.position2.x > 0 || child.sprite01.position2.y < 0.5)) {
								continue;
							}
						}
						laneMaps[child.metalData.position === leftHand ? 'incoming' : 'outgoing'].push(child);
					} else {
						if (child.metalData.position === leftHand) {
							if (child.spriteEdge.offsetType === 'branch' && (child.sprite01.position2.x > 0 || child.sprite01.position2.y < 0.5)) {
								continue;
							}
						} else {
							if (child.spriteEdge.offsetType === 'merge' && (child.sprite02.position2.x < 1 || child.sprite02.position2.y < 0.5)) {
								continue;
							}
						}
						laneMaps[child.metalData.position !== leftHand ? 'incoming' : 'outgoing'].push(child);
					}
				}
			}
		}

		const intersectsInfo = this.object['metalData']['junctionIntersects'];
		var newQuaternion = new THREE.Quaternion();
		this.object.plane.geometry.computeBoundingBox(); // boundingBox
		//var planeBox = new THREE.Box3(new THREE.Vector3(0, 0.5, 0).add(this.object.box.min), new THREE.Vector3(0, 0.5, 0).add(this.object.box.max)).applyMatrix4(this.object.matrixWorld);
		var planeBox = new THREE.Box3(this.object.plane.geometry.boundingBox.min.clone(), this.object.plane.geometry.boundingBox.max.clone()).applyMatrix4(this.object.matrixWorld);
		for (var lane01 of laneMaps['incoming']) {
			for (var lane02 of laneMaps['outgoing']) {
				if (lane01 === lane02 || lane01.parent === lane02.parent) continue;

				let key01 = `${lane01.parent.name} -> ${lane02.parent.name}`;
				let key02 = `${lane02.parent.name} -> ${lane01.parent.name}`;
				if (intersectsInfo.indexOf(key01) !== -1 || intersectsInfo.indexOf(key02) !== -1) {
					continue;
				}

				var laneCurve01 = lane01.waypointLineCurve3.points.length > 0 ? lane01.waypointLineCurve3 : lane01.roadMarkLineCurve3;
				var laneCurve02 = lane02.waypointLineCurve3.points.length > 0 ? lane02.waypointLineCurve3 : lane02.roadMarkLineCurve3;
				var v0 = lane01.localToWorld(laneCurve01.getPoint(1));
				var v1 = new THREE.Vector3();
				var v2 = lane02.localToWorld(laneCurve02.getPoint(0))
				var ray = new THREE.Ray(v0, laneCurve01.getTangentAt(1).applyQuaternion(lane01.getWorldQuaternion(newQuaternion)));
				var plane = new THREE.Plane().setFromNormalAndCoplanarPoint(new THREE.Vector3(0, 1, 0).cross(laneCurve02.getTangentAt(0).applyQuaternion(lane02.getWorldQuaternion(newQuaternion))), v2);
				var hasIntersect = ray.intersectsPlane(plane);
				ray.intersectPlane(plane, v1);
				v1.y = (planeBox.min.y + planeBox.max.y) / 2;
				if (hasIntersect && planeBox.containsPoint(v1)) {
					ray.intersectPlane(plane, v1);
					var v00 = this.object.worldToLocal(v0);
					var v11 = this.object.worldToLocal(v1);
					var v22 = this.object.worldToLocal(v2);
					var newCurve3 = new THREE.QuadraticBezierCurve3(v00, v11, v22);
					newCurve3.arcLengthDivisions = Math.floor(newCurve3.getLength());
					newLanes.push({ 'id': `${lane01.parent.name}_${lane01.name}-${lane02.parent.name}_${lane02.name}`, 'curve': newCurve3, 'refLineVisible': true });
				} else {
					var centerPosition = new THREE.Vector3().addVectors(v0, v2).multiplyScalar(0.5);
					var c1 = new THREE.Vector3();
					var ray01 = new THREE.Ray(v0, laneCurve01.getTangentAt(1).applyQuaternion(lane01.getWorldQuaternion(newQuaternion)));
					var plane01 = new THREE.Plane().setFromNormalAndCoplanarPoint(laneCurve01.getTangentAt(1).applyQuaternion(lane01.getWorldQuaternion(newQuaternion)), centerPosition);
					if (ray01.intersectsPlane(plane01)) {
						ray01.intersectPlane(plane01, c1);
						if (c1.distanceTo(v0) < 5) {
							var plane01 = new THREE.Plane().setFromNormalAndCoplanarPoint(laneCurve01.getTangentAt(1).applyQuaternion(lane01.getWorldQuaternion(newQuaternion)), ray01.at(5, new THREE.Vector3()));
							//var helper = new THREE.PlaneHelper(plane01, 10, 0xffff00);
							//this.editor.scene.add(helper);
							ray01.intersectPlane(plane01, c1);
						}
					}

					var ray02 = new THREE.Ray(v2, laneCurve02.getTangentAt(0).applyQuaternion(lane02.getWorldQuaternion(newQuaternion)).multiply(new THREE.Vector3(-1, 1, -1)));
					var plane02 = new THREE.Plane().setFromNormalAndCoplanarPoint(laneCurve02.getTangentAt(0).applyQuaternion(lane02.getWorldQuaternion(newQuaternion)), centerPosition);
					var c2 = new THREE.Vector3();
					if (ray02.intersectsPlane(plane02)) {
						ray02.intersectPlane(plane02, c2);
						if (c2.distanceTo(v2) < 5) {
							plane02 = new THREE.Plane().setFromNormalAndCoplanarPoint(laneCurve02.getTangentAt(0).applyQuaternion(lane02.getWorldQuaternion(newQuaternion)), ray02.at(5, new THREE.Vector3()));
							ray02.intersectPlane(plane02, c2);
						}
					}

					var v00 = this.object.worldToLocal(v0);
					var c11 = this.object.worldToLocal(c1);
					var c22 = this.object.worldToLocal(c2);
					var v22 = this.object.worldToLocal(v2);
					var newCurve3 = new THREE.CubicBezierCurve3(v00, c11, c22, v22);
					newCurve3.arcLengthDivisions = Math.floor(newCurve3.getLength());
					newLanes.push({ 'id': `${lane01.parent.name}_${lane01.name}-${lane02.parent.name}_${lane02.name}`, 'curve': newCurve3, 'refLineVisible': true });
				}
			}
		}

		return newLanes;
	},

	update: function (cmd) {

		this.newValue = cmd.newValue;

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);

		output.objectUuid = this.object.uuid;
		output.oldValue = this.oldValue;
		output.newValue = this.newValue;

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.oldValue = json.oldValue;
		this.newValue = json.newValue;
		this.object = this.editor.objectByUuid(json.objectUuid);

	}

};

export { SetJunctionConnectionCommand };
