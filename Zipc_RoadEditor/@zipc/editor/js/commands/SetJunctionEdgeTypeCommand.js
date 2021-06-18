/**
 * 
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object GARDEN.AbstractWay
 * @param newValue string
 * @constructor
 */
var SetJunctionEdgeTypeCommand = function (editor, object, newValue) {

	Command.call(this, editor);

	this.type = 'SetJunctionEdgeTypeCommand';
	this.name = 'Set Junction Edge-Type';
	this.updatable = true;

	this.object = object;
	this.oldValue = (object !== undefined) ? object['metalData']['edgeType'] : [];
	this.newValue = newValue;

};

SetJunctionEdgeTypeCommand.prototype = {

	execute: function () {
		this.object['metalData']['edgeType'] = this.newValue;
		this.object.computeStructure();
		this.object.dispatchEvent({ type: 'updateConnection' });
		this.object.updateMatrixWorld(true);
		this.editor.signals.gardenRoadStructureChanged.dispatch();
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.sceneGraphChanged.dispatch();
	},

	undo: function () {
		this.object['metalData']['edgeType'] = this.oldValue;
		this.object.computeStructure();
		this.object.dispatchEvent({ type: 'updateConnection' });
		this.object.updateMatrixWorld(true);
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

export { SetJunctionEdgeTypeCommand };
