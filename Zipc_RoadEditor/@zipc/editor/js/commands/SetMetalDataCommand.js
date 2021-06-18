/**
 * 
 */

import { Command } from '../Command.js';

/**
 * @param editor Editor
 * @param object GARDEN.AbstractWay
 * @param attributeName string
 * @param newValue number, string, boolean or object
 * @constructor
 */
var SetMetalDataCommand = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetMetalDataCommand';
	this.name = 'Set ' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object[attributeName] : undefined;
	this.newValue = newValue;

};

SetMetalDataCommand.prototype = {

	execute: function () {

		this.object[this.attributeName] = this.newValue;
		this.object.computeStructure();
		this.object.dispatchEvent({ type: 'updateConnection' });


		this.editor.signals.gardenRoadStructureChanged.dispatch();
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.sceneGraphChanged.dispatch();

	},

	undo: function () {

		this.object[this.attributeName] = this.oldValue;
		this.object.computeStructure();
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

export { SetMetalDataCommand };
