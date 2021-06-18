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
var RemoveLaneCommand = function (editor, object) {
	Command.call(this, editor);

	this.type = 'RemoveLaneCommand';
	this.name = 'Remove Lane';

	this.object = object;
	if (this.object !== undefined) {
		this.parent = this.object.parent;
		this.data = JSON.parse(JSON.stringify(this.parent.metalData));
	}
};

RemoveLaneCommand.prototype = {

	execute: function () {
		if (this.parent !== undefined && this.data !== undefined) {
			let newData = JSON.parse(JSON.stringify(this.data));
			const siblings = newData.lanes[this.object.metalData.position];
			if (this.object.metalData.position === 'left') {
				siblings.splice(siblings.length - this.object.metalData.laneIndex, 1);
			} else {
				siblings.splice(Math.abs(this.object.metalData.laneIndex) - 1, 1);
			}

			this.editor.removeObject(this.object);
			this.parent.setFromMetalData(newData);
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.parent);
		}
	},

	undo: function () {
		if (this.parent !== undefined && this.data !== undefined) {

			this.parent.children.splice(0, 0, this.object);
			this.object.parent = this.parent;

			this.parent.setFromMetalData(JSON.parse(JSON.stringify(this.data)));
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.objectAdded.dispatch(this.object);
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

export { RemoveLaneCommand };
