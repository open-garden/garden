/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';
import * as THREE from 'three';

import * as GARDEN from '../../../garden-objects/js/objects/GOWay';

/**
 * @param editor Editor
 * @param object GARDEN.GORoad
 * @constructor
 */
var AddGOLaneCommand = function (editor, object) {

	Command.call(this, editor);

	this.type = 'AddGOLaneCommand';
	this.index = -1;

	this.object = object;
	if (object !== undefined) {
		this.name = 'Add GOLane: ' + object.name;
		this.index = object.numLanes;
		this.newLane = new GARDEN.GOLane(`${object.numLanes}`, 'driving', object.maxSpeed, object.laneWidth);
	}

};

AddGOLaneCommand.prototype = {

	execute: function () {

		this.newLane.refLine.visible = this.editor.config.setKey('referenceLine', false);;
		this.object.addLane(this.newLane, this.index);
		this.editor.addObject(this.newLane, this.object, this.index);
		this.editor.signals.geometryChanged.dispatch(this.object);
		this.editor.select(this.object);

	},

	undo: function () {

		this.editor.removeObject(this.newLane);
		this.object.removeLane(this.newLane, this.index);
		this.editor.signals.geometryChanged.dispatch(this.object);
		this.editor.select(this.object);

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);
		output.object = this.object.toJSON();

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.object = this.editor.objectByUuid(json.object.object.uuid);

		if (this.object === undefined) {

			var loader = new THREE.ObjectLoader();
			this.object = loader.parse(json.object);

		}

	}

};

export { AddGOLaneCommand };
