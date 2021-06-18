/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

import * as GARDEN from '../../../garden-objects/js/roads/GardenObjects.js';

/**
 * @param editor Editor
 * @param object GARDEN.LaneObject
 * @constructor
 */
var AddLaneCommand = function (editor, parent, position) {

	Command.call(this, editor);

	this.type = 'AddLaneCommand';
	this.name = 'Add Lane';

	this.parent = parent;
	this.position = position || 'left';
	this.object = null;
	this.metalData = {};

	if (this.parent !== undefined) {
		this.object = new GARDEN.LaneObject(parent);
		this.data = JSON.parse(JSON.stringify(this.parent.metalData));
		this.metalData = {
			'id': `${this.position === 'left' ? 'L-' : 'R-'}${GARDEN.RoadObject.laneIndexFormatter.format(this.parent.metalData.lanes[this.position].length + 1)}`,
			'type': 'driving',
			'position': `${this.position === 'left' ? 'left' : 'right'}`,
			'width': 4,
			'refLineVisible': false,
			'objectId': this.object.id
		};
	}
};

AddLaneCommand.prototype = {

	execute: function () {
		if (this.parent !== undefined) {
			let newData = JSON.parse(JSON.stringify(this.data));
			const siblings = newData.lanes[this.position];
			if (this.position === 'left') {
				siblings.splice(0, 0, this.metalData);
			} else {
				siblings.push(this.metalData);
			}
			this.parent.children.splice(0, 0, this.object);
			this.object.parent = this.parent;

			this.parent.setFromMetalData(newData);
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.objectAdded.dispatch(this.object);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.parent);
		}
	},

	undo: function () {
		if (this.parent !== undefined && this.object !== null) {
			this.editor.removeObject(this.object);
			this.parent.setFromMetalData(JSON.parse(JSON.stringify(this.data)));
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.parent);
		}
	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);
		output.object = this.object.toJSON();
		output.index = this.index;
		output.parentUuid = this.parent.uuid;

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.parent = this.editor.objectByUuid(json.parentUuid);
		if (this.parent === undefined) {

			this.parent = this.editor.scene;

		}

		this.index = json.index;

		this.object = this.editor.objectByUuid(json.object.object.uuid);
		if (this.object === undefined) {

			var loader = new THREE.ObjectLoader();
			this.object = loader.parse(json.object);

		}

	}

};

export { AddLaneCommand };
