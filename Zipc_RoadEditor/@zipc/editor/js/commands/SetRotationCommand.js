/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object THREE.Object3D
 * @param newRotation THREE.Euler
 * @param optionalOldRotation THREE.Euler
 * @constructor
 */
var SetRotationCommand = function (editor, object, newRotation, optionalOldRotation) {

	Command.call(this, editor);

	this.type = 'SetRotationCommand';
	this.name = 'Set Rotation';
	this.updatable = true;

	this.object = object;

	if (object !== undefined && newRotation !== undefined) {

		this.oldRotation = object.rotation.clone();
		this.newRotation = newRotation.clone();

	}

	if (optionalOldRotation !== undefined) {

		this.oldRotation = optionalOldRotation.clone();

	}

};

SetRotationCommand.prototype = {

	execute: function () {

		this.object.rotation.copy(this.newRotation);
		if (this.object.type === 'Road') {
			if (this.object['metalData']['point'] === undefined)
				this.object['metalData']['point'] = { "x": 0, "y": 0, "z": 0, "roll": 0, "yaw": 0, "pitch": 0 };
			this.object['metalData']['point']['yaw'] = this.newRotation.y * THREE.MathUtils.RAD2DEG;
		}
		this.object.updateMatrixWorld(true);

		this.object.dispatchEvent({ type: 'updateConnection' });
		this.editor.signals.objectChanged.dispatch(this.object);

	},

	undo: function () {

		this.object.rotation.copy(this.oldRotation);
		if (this.object.type === 'Road') {
			if (this.object['metalData']['point'] === undefined)
				this.object['metalData']['point'] = { "x": 0, "y": 0, "z": 0, "roll": 0, "yaw": 0, "pitch": 0 };
			this.object['metalData']['point']['yaw'] = this.oldRotation.y * THREE.MathUtils.RAD2DEG;
		}
		this.object.updateMatrixWorld(true);

		this.object.dispatchEvent({ type: 'updateConnection' });
		this.editor.signals.objectChanged.dispatch(this.object);

	},

	update: function (command) {

		this.newRotation.copy(command.newRotation);

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);

		output.objectUuid = this.object.uuid;
		output.oldRotation = this.oldRotation.toArray();
		output.newRotation = this.newRotation.toArray();

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.object = this.editor.objectByUuid(json.objectUuid);
		this.oldRotation = new THREE.Euler().fromArray(json.oldRotation);
		this.newRotation = new THREE.Euler().fromArray(json.newRotation);

	}

};

export { SetRotationCommand };
