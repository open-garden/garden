/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';
import * as THREE from 'three';

/**
 * @param editor Editor
 * @param object GARDEN.GORoad
 * @constructor
 */
var RemoveGOLaneCommand = function (editor, object) {

	Command.call(this, editor);

	this.type = 'RemoveGOLaneCommand';
	this.name = 'Remove GOLane';

	this.object = object;
	this.parent = (object !== undefined) ? object.parent : undefined;
	if (this.parent !== undefined) {

		this.index = this.parent.children.indexOf(this.object);

	}

};

RemoveGOLaneCommand.prototype = {

	execute: function () {

		this.editor.removeObject(this.object);
		this.parent.removeLane(this.object, this.index);
		this.editor.signals.geometryChanged.dispatch(this.parent);
		this.editor.select(this.parent);

	},

	undo: function () {

		this.parent.addLane(this.object, this.index);
		this.editor.addObject(this.object, this.parent, this.index);
		this.editor.signals.geometryChanged.dispatch(this.parent);
		this.editor.select(this.parent);

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

export { RemoveGOLaneCommand };
