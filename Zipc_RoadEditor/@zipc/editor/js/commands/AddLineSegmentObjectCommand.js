/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

import * as THREE from 'three';

import * as RoadMark from '../../../garden-objects/js/roadmarks/RoadMarkObjects';

/**
 * @param editor Editor
 * @param object GARDEN.LaneObject
 * @constructor
 */
var AddLineSegmentObjectCommand = function (editor, parent, object) {

	Command.call(this, editor);

	this.type = 'AddLineSegmentObjectCommand';
	this.name = 'Add RoadMark.LineSegment';

	if (parent !== undefined) {
		this.parent = parent;
		this.object = object === undefined ? new RoadMark.LineSegment() : object;
	}

};

AddLineSegmentObjectCommand.prototype = {

	execute: function () {
		if (this.parent !== undefined && this.object !== undefined) {
			this.parent.segments.push(this.object);
			this.editor.addObject(this.object, this.parent, this.parent.children.length - 1);

			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.parent);
		}
	},

	undo: function () {
		if (this.parent !== undefined && this.object !== undefined) {
			let index = this.parent.segments.indexOf(this.object);
			if (index >= 0 && index <= this.parent.segments.length) {
				this.parent.segments.splice(index, 1);
			}

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

export { AddLineSegmentObjectCommand };
