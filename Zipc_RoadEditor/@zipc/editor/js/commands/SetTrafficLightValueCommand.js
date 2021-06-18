/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';
import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object THREE.Object3D
 * @param attributeName string
 * @param newValue number, string, boolean or object
 * @constructor
 */

var SetTrafficLightValueCommand = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetTrafficLightValueCommand';
	this.name = 'Set ' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object[attributeName] : undefined;
	this.newValue = newValue;

};

SetTrafficLightValueCommand.prototype = {

	execute: function () {
		if (this.object.type !== 'RoadMark.Signal.TrafficLight') {
			return;
		}

		if (this.attributeName === 'road') {
			this.editor.moveObject(this.object, this.newValue, this.object.parent);
		}
		this.object[this.attributeName] = this.newValue;
		this.object.computeGeometry();
		this.object.updateMatrixWorld();

		this.editor.signals.objectChanged.dispatch(this.object);
	},

	undo: function () {
		if (this.object.type !== 'RoadMark.Signal.TrafficLight') {
			return;
		}

		if (this.attributeName === 'road') {
			this.editor.moveObject(this.object, this.oldValue, this.object.parent);
		}
		this.object[this.attributeName] = this.oldValue;
		this.object.computeGeometry();
		this.object.updateMatrixWorld();


		this.editor.signals.objectChanged.dispatch(this.object);
	},

	update: function (cmd) {

		this.newValue = cmd.newValue;

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);

		output.objectUuid = this.object.uuid;
		output.attributeName = this.attributeName;
		output.oldValue = this.oldValue;
		output.newValue = this.newValue;

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.attributeName = json.attributeName;
		this.oldValue = json.oldValue;
		this.newValue = json.newValue;
		this.object = this.editor.objectByUuid(json.objectUuid);

	}

};

export { SetTrafficLightValueCommand as SetTrafficLightValueCommand };
