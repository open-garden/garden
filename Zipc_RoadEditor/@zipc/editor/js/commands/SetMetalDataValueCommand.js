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
var SetMetalDataValueCommand = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetMetalDataValueCommand';
	this.name = 'Set MetalData.' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object['metalData'][attributeName] : undefined;
	this.newValue = newValue;

};

SetMetalDataValueCommand.prototype = {

	execute: function () {

		if (this.attributeName === 'id') {
			this.object.name = this.newValue;
		} else if (this.attributeName === 'gid') {
			this.object.gid = this.newValue;
			this.object.name = this.newValue;
		} else if (this.attributeName === 'description') {
			this.object.description = this.newValue;
		}
		this.object['metalData'][this.attributeName] = this.newValue;

		this.editor.signals.objectChanged.dispatch(this.object);
		if (this.object.type === 'Car' && this.attributeName === 'id') {
			this.object.actorObject.name = this.object.name + '_Mesh01';
			this.editor.signals.gardenRoadStructureChanged.dispatch();
		}
		// this.editor.signals.sceneGraphChanged.dispatch();

	},

	undo: function () {
		if (this.attributeName === 'id') {
			if (this.object.type === 'RoadStructure' || this.object.type === 'Route') {
				this.object.gid = this.oldValue;
			}
			this.object.name = this.oldValue;

		}
		if (this.attributeName === 'description') {
			this.object.description = this.oldValue;
		}

		this.object['metalData'][this.attributeName] = this.oldValue;

		this.editor.signals.objectChanged.dispatch(this.object);
		if (this.object.type === 'Car' && this.attributeName === 'id') {
			this.object.actorObject.name = this.object.name + '_Mesh01';
			this.editor.signals.gardenRoadStructureChanged.dispatch();
		}
		// this.editor.signals.sceneGraphChanged.dispatch();

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

export { SetMetalDataValueCommand };
