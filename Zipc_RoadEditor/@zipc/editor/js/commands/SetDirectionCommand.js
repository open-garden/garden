/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

/**
 * @param editor Editor
 * @param object GardenObjects.RoadStructure
 * @param value string
 * @constructor
 */
var SetDirectionCommand = function (editor, object, value) {

	Command.call(this, editor);

	this.type = 'SetDirectionCommand';
	this.name = 'Set Direction';
	this.updatable = true;

	this.object = object;

	if (object !== undefined && value !== undefined) {

		this.oldValue = object.direction;
		this.newValue = value;

	}

};
SetDirectionCommand.prototype = {

	execute: function () {
		const editor = this.editor;
		this.object.direction = this.newValue;
		(function addObjects(objects) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					for (let child of object.children) {
						if (child.type === 'Lane') {
							if (child.sprite01 !== undefined && child.sprite02 !== undefined && child.spriteEdge !== undefined) {
								let p01 = new THREE.Vector2().copy(child.sprite01.position2);
								let p02 = new THREE.Vector2().copy(child.sprite02.position2);
								child.sprite01.position2 = new THREE.Vector2(1 - p02.x, p02.y);
								child.sprite02.position2 = new THREE.Vector2(1 - p01.x, p01.y);
								child.spriteEdge.offsetType = child.spriteEdge.offsetType === 'none' ? 'none' : (child.spriteEdge.offsetType === 'branch' ? 'merge' : 'branch');
							}
						}
					}
				}
			}
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					object.computeStructure();
					editor.signals.objectChanged.dispatch(object);
				} else if (object.type === 'Junction') {
					object.metalData.lanes = [];
					object.computeStructure();
					editor.signals.objectChanged.dispatch(object);
				}
			}
		})(this.object.children);
		this.editor.signals.sceneGraphChanged.dispatch();

	},

	undo: function () {
		this.object.direction = this.oldValue;
		(function addObjects(objects) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					for (let child of object.children) {
						if (child.type === 'Lane') {
							if (child.sprite01 !== undefined && child.sprite02 !== undefined && child.spriteEdge !== undefined) {
								child.sprite01.position2 = new THREE.Vector2(1 - child.sprite01.position2.x, child.sprite01.position2.y);
								child.sprite02.position2 = new THREE.Vector2(1 - child.sprite02.position2.x, child.sprite02.position2.y);
								child.spriteEdge.offsetType = child.spriteEdge.offsetType === 'none' ? 'none' : (child.spriteEdge.offsetType === 'branch' ? 'merge' : 'branch');
							}
						}
					}
				}
			}
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					object.computeStructure();
					editor.signals.objectChanged.dispatch(object);
				} else if (object.type === 'Junction') {
					object.metalData.lanes = [];
					object.computeStructure();
					editor.signals.objectChanged.dispatch(object);
				}
			}
		})(this.object.children);
		this.editor.signals.sceneGraphChanged.dispatch();
	},

	update: function (command) {

		this.newValue = command.newValue;

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

		this.object = this.editor.objectByUuid(json.objectUuid);
		this.oldValue = json.oldValue;
		this.newValue = json.newValue;

	}

};

export { SetDirectionCommand };
