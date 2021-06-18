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

var SetRoadMarkValueCommand = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetRoadMarkValueCommand';
	this.name = 'Set ' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object[attributeName] : undefined;
	this.newValue = newValue;

};

SetRoadMarkValueCommand.prototype = {

	execute: function () {
		let lineObject;
		if (this.object.type === 'RoadMark.Line') {
			lineObject = this.object;
			if (this.attributeName === 'parameters') {
				if (this.object.material['color'].getHex() !== this.newValue.color) {
					this.object.material['color'].setHex(this.newValue.color);
					this.editor.signals.materialChanged.dispatch(this.material);
				}
			}
		} else if (this.object.type === 'RoadMark.LineSegment') {
			lineObject = this.object.parent;
			if (this.attributeName === 'lane') {
				this.object['name'] = `${this.newValue[1].road}_${this.newValue[1].lane}`;
			}
		}
		this.object[this.attributeName] = this.newValue;

		this.editor.computeRoadMarkLineGeometry(lineObject);

		this.editor.signals.objectChanged.dispatch(this.object);
	},

	undo: function () {
		let lineObject;
		if (this.object.type === 'RoadMark.Line') {
			lineObject = this.object;
			if (this.attributeName === 'parameters') {
				if (this.object.material['color'].getHex() !== this.oldValue.color) {
					this.object.material['color'].setHex(this.oldValue.color);
					this.editor.signals.materialChanged.dispatch(this.material);
				}
			}
		} else if (this.object.type === 'RoadMark.LineSegment') {
			lineObject = this.object.parent;
			if (this.attributeName === 'lane') {
				this.object['name'] = `${this.oldValue[1].road}_${this.oldValue[1].lane}`;
			}
		}
		this.object[this.attributeName] = this.oldValue;

		this.editor.computeRoadMarkLineGeometry(lineObject);

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

export { SetRoadMarkValueCommand };
