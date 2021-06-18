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
var RemoveLineSegmentObjectCommand = function (editor, object) {

	Command.call(this, editor);

	this.type = 'RemoveLineSegmentObjectCommand';
	this.name = 'Remove RoadMark.LineSegment';

	this.object = object;
	if (object.parent !== undefined) {
		this.parent = this.object.parent;
		this.index = this.parent.segments.indexOf(this.object);
	}

};

RemoveLineSegmentObjectCommand.prototype = {

	execute: function () {
		if (this.parent !== undefined && this.object !== undefined) {
			if (this.index >= 0 && this.index <= this.parent.segments.length) {
				this.parent.segments.splice(this.index, 1);
			}
			this.editor.removeObject(this.object);

			this.editor.computeRoadMarkLineGeometry(this.parent);
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.parent);
		}
	},

	undo: function () {
		if (this.parent !== undefined && this.object !== undefined) {
			this.parent.segments.splice(this.index, 0, this.object);
			this.editor.addObject(this.object, this.parent);

			this.editor.computeRoadMarkLineGeometry(this.parent);
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

export { RemoveLineSegmentObjectCommand };
