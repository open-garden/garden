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
var SetControlSpritePosition2Command = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetControlSpritePosition2Command';
	this.name = 'Set ' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object[attributeName] : undefined;
	this.newValue = newValue;

};

SetControlSpritePosition2Command.prototype = {

	execute: function () {

		this.object[this.attributeName] = this.newValue;
		let lane =  this.object.parent;
		if (lane.parent === null) {
			this.object.parent = lane.parentRoad.children.find(child => child.name === lane.name);
			this.oldValue = this.object.parent[this.object.name][this.attributeName];
			this.object.parent[this.object.name] = this.object;
		}
		this.object.parent.update();
		this.object.parent.parent.computeStructure();
		this.editor.signals.objectChanged.dispatch(this.object.parent);

	},

	undo: function () {

		this.object[this.attributeName] = this.oldValue;
		this.object.parent.update();
		this.object.parent.parent.computeStructure();
		this.editor.signals.objectChanged.dispatch(this.object.parent);

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

export { SetControlSpritePosition2Command };
