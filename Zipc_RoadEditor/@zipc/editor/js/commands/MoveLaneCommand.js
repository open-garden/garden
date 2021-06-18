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
const laneIndexFormatter = new Intl.NumberFormat('ja', {
	minimumIntegerDigits: 2,
	minimumFractionDigits: 0,
	maximumFractionDigits: 0,
	useGrouping: false
});

var MoveLaneCommand = function (editor, object, position) {
	Command.call(this, editor);

	this.type = 'MoveLaneCommand';
	this.name = 'Move Lane';

	this.object = object;
	this.oldPosition = object.lanePosition;
	this.newPosition = position;
	if (this.object !== undefined && this.oldPosition !== this.newPosition) {
		this.parent = this.object.parent;
		this.oldData = JSON.parse(JSON.stringify(this.object.metalData));
		this.newData = {
			'id': `${this.newPosition === 'left' ? 'L-' : 'R-'}${GARDEN.RoadObject.laneIndexFormatter.format(this.parent.metalData.lanes[this.newPosition].length + 1)}`,
			'objectId': this.oldData.objectId,
			'type': this.oldData.type,
			'position': `${this.newPosition === 'left' ? 'left' : 'right'}`,
			'width': this.oldData.width,
			'refLineVisible': this.oldData.refLineVisible
		};
	}
};

MoveLaneCommand.prototype = {

	execute: function () {
		if (this.parent !== undefined && this.object !== undefined) {
			const oldSiblings = this.parent.metalData.lanes[this.oldPosition];
			const newSiblings = this.parent.metalData.lanes[this.newPosition];
			if (this.oldPosition === 'left') {
				oldSiblings.splice(oldSiblings.length - this.oldData.laneIndex, 1);
				newSiblings.push(this.newData);
			} else {
				oldSiblings.splice(Math.abs(this.oldData.laneIndex) - 1, 1);
				newSiblings.splice(0, 0, this.newData);
			}

			this.parent.setFromMetalData(this.parent.metalData);
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.object);
		}
	},

	undo: function () {
		if (this.parent !== undefined && this.object !== undefined) {
			const oldSiblings = this.parent.metalData.lanes[this.oldPosition];
			const newSiblings = this.parent.metalData.lanes[this.newPosition];
			if (this.newPosition === 'left') {
				newSiblings.splice(newSiblings.length - this.newData.laneIndex, 1);
				oldSiblings.push(this.oldData);
			} else {
				newSiblings.splice(Math.abs(this.newData.laneIndex) - 1, 1);
				oldSiblings.splice(0, 0, this.oldData);
			}

			this.parent.setFromMetalData(this.parent.metalData);
			this.editor.signals.objectChanged.dispatch(this.parent);
			this.editor.signals.sceneGraphChanged.dispatch();
			this.editor.select(this.object);
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

export { MoveLaneCommand };
