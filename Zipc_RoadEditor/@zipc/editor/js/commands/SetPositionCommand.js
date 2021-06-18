/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object THREE.Object3D
 * @param newPosition THREE.Vector3
 * @param optionalOldPosition THREE.Vector3
 * @constructor
 */
var SetPositionCommand = function (editor, object, newPosition, optionalOldPosition) {

	Command.call(this, editor);

	this.type = 'SetPositionCommand';
	this.name = 'Set Position';
	this.updatable = true;

	this.object = object;

	if (object !== undefined && newPosition !== undefined) {

		this.oldPosition = object.position.clone();
		this.newPosition = newPosition.clone();

	}

	if (optionalOldPosition !== undefined) {

		this.oldPosition = optionalOldPosition.clone();

	}

};
SetPositionCommand.prototype = {

	execute: function () {

		this.object.position.copy(this.newPosition);
		if (this.object.type === 'Road') {
			if (this.object['metalData']['point'] === undefined)
				this.object['metalData']['point'] = { "x": 0, "y": 0, "z": 0, "roll": 0, "yaw": 0, "pitch": 0 };
			this.object['metalData']['point']['x'] = this.newPosition.x;
			this.object['metalData']['point']['y'] = this.newPosition.y;
			this.object['metalData']['point']['z'] = this.newPosition.z;
		} else if (this.object.type === 'RoadMark.Signal.TrafficLight') {
			const oldVec = this.object.parameters.positionVec;
			this.object.parameters.positionVec = {x: this.newPosition.x + oldVec.x - this.oldPosition.x, 
				y: this.newPosition.y+ oldVec.y - this.oldPosition.y, 
				z: this.newPosition.z + oldVec.z - this.oldPosition.z};
		}
		this.object.updateMatrixWorld(true);

		this.object.dispatchEvent({ type: 'updateConnection' });
		this.editor.signals.objectChanged.dispatch(this.object);

	},

	undo: function () {

		this.object.position.copy(this.oldPosition);
		if (this.object.type === 'Road') {
			if (this.object['metalData']['point'] === undefined)
				this.object['metalData']['point'] = { "x": 0, "y": 0, "z": 0, "roll": 0, "yaw": 0, "pitch": 0 };
			this.object['metalData']['point']['x'] = this.oldPosition.x;
			this.object['metalData']['point']['y'] = this.oldPosition.y;
			this.object['metalData']['point']['z'] = this.oldPosition.z;
		}
		this.object.updateMatrixWorld(true);

		this.object.dispatchEvent({ type: 'updateConnection' });
		this.editor.signals.objectChanged.dispatch(this.object);

	},

	update: function (command) {

		this.newPosition.copy(command.newPosition);

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);

		output.objectUuid = this.object.uuid;
		output.oldPosition = this.oldPosition.toArray();
		output.newPosition = this.newPosition.toArray();

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.object = this.editor.objectByUuid(json.objectUuid);
		this.oldPosition = new THREE.Vector3().fromArray(json.oldPosition);
		this.newPosition = new THREE.Vector3().fromArray(json.newPosition);

	}

};

export { SetPositionCommand };
