/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

/**
 * @param editor Editor
 * @param object THREE.Object3D
 * @param attributeName string
 * @param newValue number, string, boolean or object
 * @constructor
 */

var SetRoadMarkSignValueCommand = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetRoadMarkSignValueCommand';
	this.name = 'Set ' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object[attributeName] : undefined;
	this.newValue = newValue;

};

SetRoadMarkSignValueCommand.prototype = {

	execute: function () {
		if (this.object.type !== 'RoadMark.Sign') {
			return;
		}

		if (this.attributeName === 'road') {
			this.editor.moveObject(this.object, this.newValue, this.object.parent);
		} else if (this.attributeName === 'lane') {
			this.editor.moveObject(this.object, this.newValue, this.object.parent);
		} else if (this.attributeName === 'parameters') {
			if (this.object.material['color'].getHex() !== this.newValue.color) {
				this.object.material['color'].setHex(this.newValue.color);
				this.editor.signals.materialChanged.dispatch(this.material);
			}
		}
		this.object[this.attributeName] = this.newValue;
		this.object.computeGeometry();
		this.object.updateMatrixWorld();

		this.editor.signals.objectChanged.dispatch(this.object);
	},

	undo: function () {
		if (this.object.type !== 'RoadMark.Sign') {
			return;
		}

		if (this.attributeName === 'road') {
			this.editor.moveObject(this.object, this.oldValue, this.object.parent);
		} else if (this.attributeName === 'lane') {
			this.editor.moveObject(this.object, this.oldValue, this.object.parent);
		} else if (this.attributeName === 'parameters') {
			if (this.object.material['color'].getHex() !== this.oldValue.color) {
				this.object.material['color'].setHex(this.oldValue.color);
				this.editor.signals.materialChanged.dispatch(this.material);
			}
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

export { SetRoadMarkSignValueCommand };
