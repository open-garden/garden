/**
 * 
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object GARDEN.AbstractWay
 * @param newId GARDEN.AbstractWay ID
 * @param newReverse boolean
 * @constructor
 */
var SetConnectionCommand = function (editor, object, newId, newReverse) {

	Command.call(this, editor);

	this.type = 'SetConnectionCommand';
	this.name = 'Set Connection';
	this.updatable = true;

	this.object = object;
	this.oldId = (object !== undefined) ? object['metalData']['connectionId'] : undefined;
	this.oldReverse = (object !== undefined) ? object['metalData']['reverse'] : false;
	this.newId = newId;
	this.newReverse = newReverse;

};

SetConnectionCommand.prototype = {

	execute: function () {

		var oldConnectionObject = this.oldId !== undefined ? this.editor.scene.getObjectById(parseInt(this.oldId), true) : undefined;

		if (oldConnectionObject !== undefined) {
			oldConnectionObject.connectedTo.delete(this.object);
		}

		var connectionObject = this.editor.scene.getObjectById(parseInt(this.newId), true);

		if (connectionObject !== undefined && connectionObject !== this.object) {

			connectionObject.connectedTo.add(this.object);

			var connectionInfo = this.newReverse ? connectionObject.getNegative() : connectionObject.getPositive();

			this.object['metalData']['connectionType'] = connectionObject.type === 'Lane' ? connectionObject.metalData.position : 'center';
			this.object['metalData']['connectionId'] = this.newId;
			if (connectionObject.type === 'Road') {
				this.object['metalData']['connection']['road'] = connectionObject.name;
				this.object['metalData']['connection']['lane'] = '';
			} else if (connectionObject.type === 'Lane') {
				this.object['metalData']['connection']['road'] = connectionObject.parent.name;
				this.object['metalData']['connection']['lane'] = connectionObject.name;
			}
			this.object['metalData']['reverse'] = this.newReverse;
			this.object['metalData']['rotationZ'] = connectionInfo.rotationZ;
			if (this.object['metalData']['point'] === undefined) {
				this.object['metalData']['point'] = { "x": 0, "y": 0, "z": 0, "roll": 0, "yaw": 0, "pitch": 0 };
			}
			this.object['metalData']['point']['x'] = connectionInfo.position.x;
			this.object['metalData']['point']['y'] = connectionInfo.position.y;
			this.object['metalData']['point']['z'] = connectionInfo.position.z;
			this.object['metalData']['point']['yaw'] = connectionInfo.rotationY * THREE.MathUtils.RAD2DEG;

			//this.object.position.copy(connectionInfo.position);
			//this.object.rotation.copy(new THREE.Euler(0, connectionInfo.rotationY, 0), 'XYZ');

			this.object.computeStructure();
			this.object.updateMatrixWorld(true);

		} else {

			this.object['metalData']['connectionId'] = this.newId;
			this.object['metalData']['connection'] = { "road": "", "lane": "" };
			this.object['metalData']['reverse'] = this.newReverse;
			//this.object['metalData']['rotationZ'] = 0;

			this.object.computeStructure();
			this.object.updateMatrixWorld(true);

			//this.object.position.copy(new THREE.Vector3());
			//this.object.rotation.copy(new THREE.Euler(0, 0, 0), 'XYZ');
		}




		this.object.dispatchEvent({ type: 'updateConnection' });
		this.editor.signals.gardenRoadStructureChanged.dispatch();
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.sceneGraphChanged.dispatch();

	},

	undo: function () {

		var oldConnectionObject = this.newId !== undefined ? this.editor.scene.getObjectById(parseInt(this.newId), true) : undefined;

		if (oldConnectionObject !== undefined) {
			oldConnectionObject.connectedTo.delete(this.object);
		}

		var connectionObject = this.editor.scene.getObjectById(parseInt(this.oldId), true);

		if (connectionObject !== undefined && connectionObject !== this.object) {

			connectionObject.connectedTo.add(this.object);

			var connectionInfo = this.oldReverse ? connectionObject.getNegative() : connectionObject.getPositive();

			this.object['metalData']['connectionType'] = connectionObject.type === 'Lane' ? connectionObject.metalData.position : 'center';
			this.object['metalData']['connectionId'] = this.oldId;
			if (connectionObject.type === 'Road') {
				this.object['metalData']['connection']['road'] = connectionObject.name;
				this.object['metalData']['connection']['lane'] = '';
			} else if (connectionObject.type === 'Lane') {
				this.object['metalData']['connection']['road'] = connectionObject.parent.name;
				this.object['metalData']['connection']['lane'] = connectionObject.name;
			}
			this.object['metalData']['reverse'] = this.oldReverse;
			this.object['metalData']['rotationZ'] = connectionInfo.rotationZ;
			if (this.object['metalData']['point'] === undefined) {
				this.object['metalData']['point'] = { "x": 0, "y": 0, "z": 0, "roll": 0, "yaw": 0, "pitch": 0 };
			}
			this.object['metalData']['point']['x'] = connectionInfo.position.x;
			this.object['metalData']['point']['y'] = connectionInfo.position.y;
			this.object['metalData']['point']['z'] = connectionInfo.position.z;
			this.object['metalData']['point']['yaw'] = connectionInfo.rotationY * THREE.MathUtils.RAD2DEG;

			//this.object.position.copy(connectionInfo.position);
			//this.object.rotation.copy(new THREE.Euler(0, connectionInfo.rotationY, 0), 'XYZ');

			this.object.computeStructure();
			this.object.updateMatrixWorld(true);

		} else {

			this.object['metalData']['connectionId'] = this.oldId;
			this.object['metalData']['connection'] = { "road": "", "lane": "" };
			this.object['metalData']['reverse'] = this.oldReverse;
			//this.object['metalData']['rotationZ'] = 0;

			this.object.computeStructure();
			this.object.updateMatrixWorld(true);

			//this.object.position.copy(new THREE.Vector3());
			//this.object.rotation.copy(new THREE.Euler(0, 0, 0), 'XYZ');
		}




		this.object.dispatchEvent({ type: 'updateConnection' });
		this.editor.signals.gardenRoadStructureChanged.dispatch();
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.sceneGraphChanged.dispatch();

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

export { SetConnectionCommand };
