
/**
 * @author dforrer / https://github.com/dforrer
 * Developed as part of a project at University of Applied Sciences and Arts Northwestern Switzerland (www.fhnw.ch)
 */

import { Command } from '../Command.js';

/**
 * @param editor Editor
 * @param object THREE.Object3D
 * @param attributeName string
 * @param newValue number, string, boolean or object
 * @constructor
 */
var SetOffsetTypeCommand = function (editor, object, attributeName, newValue) {

	Command.call(this, editor);

	this.type = 'SetOffsetTypeCommand';
	this.name = 'Set ' + attributeName;
	this.updatable = true;

	this.object = object;
	this.attributeName = attributeName;
	this.oldValue = (object !== undefined) ? object[attributeName] : undefined;
	this.newValue = newValue;

};

SetOffsetTypeCommand.prototype = {

	execute: function () {
		this.object.offsetType = this.newValue;
		let lane =  this.object.parent;
		if (lane.parent === null) {
			this.object.parent = lane.parentRoad.children.find(child => child.name === lane.name);
			this.object.parent.spriteEdge= this.object;
		}
		const ctrlSpriteObject01 = this.object.parent.sprite01;
		const ctrlSpriteObject02 = this.object.parent.sprite02;
		switch (this.newValue) {
			case 'merge':
				ctrlSpriteObject01.position2.copy(new THREE.Vector2(0, 1));
				ctrlSpriteObject02.position2.copy(new THREE.Vector2(1, 0));
				break;
			case 'branch':
				ctrlSpriteObject01.position2.copy(new THREE.Vector2(0, 0));
				ctrlSpriteObject02.position2.copy(new THREE.Vector2(1, 1));
				break;
			default:
				ctrlSpriteObject01.position2.copy(new THREE.Vector2(0, 1));
				ctrlSpriteObject02.position2.copy(new THREE.Vector2(1, 1));
		}

		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.objectChanged.dispatch(this.object.parent);
		// this.editor.signals.sceneGraphChanged.dispatch();

	},

	undo: function () {

		const lane =  this.object.parent;
		const ctrlSpriteObject01 = lane.sprite01;
		const ctrlSpriteObject02 = lane.sprite02;
		switch (this.oldValue) {
			case 'merge':
				ctrlSpriteObject01.position2.copy(new THREE.Vector2(0, 1));
				ctrlSpriteObject02.position2.copy(new THREE.Vector2(1, 0));
				break;
			case 'branch':
				ctrlSpriteObject01.position2.copy(new THREE.Vector2(0, 0));
				ctrlSpriteObject02.position2.copy(new THREE.Vector2(1, 1));
				break;
			default:
				ctrlSpriteObject01.position2.copy(new THREE.Vector2(0, 1));
				ctrlSpriteObject02.position2.copy(new THREE.Vector2(1, 1));
		}

		this.object.offsetType = this.oldValue;
		this.editor.signals.objectChanged.dispatch(this.object);
		this.editor.signals.objectChanged.dispatch(this.object.parent);
		// this.editor.signals.sceneGraphChanged.dispatch();

	},

	update: function (cmd) {

		this.newValue = cmd.newValue;

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);

		output.objectUuid = this.object.uuid;
		output.attributeName = this.attributeName;
		output.oldValue = this.oldValue;
		output.newValue = this.newValue;

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.attributeName = json.attributeName;
		this.oldValue = json.oldValue;
		this.newValue = json.newValue;
		this.object = this.editor.objectByUuid(json.objectUuid);

	}

};

export { SetOffsetTypeCommand };
