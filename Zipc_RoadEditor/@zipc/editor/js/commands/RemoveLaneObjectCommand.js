/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object GARDEN.LaneObject
 * @constructor
 */
var RemoveLaneObjectCommand = function (editor, object) {

	Command.call(this, editor);

	this.type = 'RemoveLaneObjectCommand';
	this.name = 'Remove LaneObject';

	this.object = object;
	this.parent = (object !== undefined) ? object.parent : undefined;
	if (this.parent !== undefined) {

		this.index = this.parent.children.indexOf(this.object);

		this.metalData = JSON.parse(JSON.stringify(this.parent.metalData));

	} else {
		this.metalData = undefined;
	}

};

RemoveLaneObjectCommand.prototype = {

	execute: function () {

		if (this.parent !== undefined && this.metalData !== undefined) {
			var metalData = JSON.parse(JSON.stringify(this.metalData));
			metalData.lanes = metalData.lanes.filter(lane => lane.objectId !== this.object.id);
			this.parent.setFromMetalData(metalData);
		}

		this.editor.removeObject(this.object);
		this.editor.deselect();

	},

	undo: function () {

		this.editor.addObject(this.object, this.parent, this.index);
		this.editor.select(this.object);

		if (this.parent !== undefined && this.metalData !== undefined) {
			this.parent.setFromMetalData(this.metalData);

			this.editor.signals.objectRemoved.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
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

export { RemoveLaneObjectCommand };
