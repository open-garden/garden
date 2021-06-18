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
var AddLaneObjectCommand = function (editor, parent, object) {

	Command.call(this, editor);

	this.type = 'AddLaneObjectCommand';
	this.name = 'Add LaneObject';

	this.parent = parent;
	this.object = object;

	if (this.parent !== undefined) {

		this.metalData = JSON.parse(JSON.stringify(this.parent.metalData));
		var lanePosition = 'left';
		try {
			if (parent.parent.direction === 'right') {
				lanePosition = 'right';
			}
		} catch (e) { }

		if (this.object === undefined) {
			this.object = new GARDEN.LaneObject(parent);
			this.object.plane.material.wireframe = this.editor.config.getKey('wireframe');
			this.object.setFromMetalData({
				'id': 'Lane_new',
				'type': 'driving',
				'position': lanePosition,
				'width': 3.5,
				'refLineVisible': true
			});
		}
	}

};

AddLaneObjectCommand.prototype = {

	execute: function () {

		if (this.parent !== undefined && this.object !== undefined) {
			this.editor.addObject(this.object, this.parent, this.parent.children.length - 1);

			this.parent.metalData.lanes.push(this.object.metalData);
			this.parent.setFromMetalData(this.parent.metalData);

			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.parent);

		}

	},

	undo: function () {

		if (this.parent !== undefined && this.object !== undefined) {
			var metalData = JSON.parse(JSON.stringify(this.parent.metalData));
			metalData.lanes = metalData.lanes.filter(lane => lane.objectId !== this.object.id);
			this.parent.setFromMetalData(metalData);

			this.editor.removeObject(this.object);
			this.editor.signals.objectChanged.dispatch(this.parent);
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

export { AddLaneObjectCommand };
