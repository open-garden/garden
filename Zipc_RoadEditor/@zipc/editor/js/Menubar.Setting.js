/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { UIPanel } from '../../../vendors/js/libs/ui.js';
import { UIBoolean } from '../../../vendors/js/libs/ui.three.js';

var MenubarSetting = function (editor) {

	var strings = editor.strings;

	var container = new UIPanel();
	container.setClass('menu right');

	// Wire frame
	var wireframe = new UIBoolean(editor.config.getKey('wireframe'), strings.getKey('menubar/setting/wireframe'));
	wireframe.setPadding('8px');
	wireframe.text.setColor('#888');

	wireframe.onChange(function () {
		var value = this.getValue();
		editor.config.setKey('wireframe', value);
		var scene = editor.scene;

		(function addObjects(objects) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					addObjects(object.children);
				} else if (object.type === 'Lane') {
					object.plane.material.wireframe = value;
					object.plane.material.needsUpdate = true;
					addObjects(object.children);
				} else if (object.type === 'Junction') {
					object.plane.material.wireframe = value;
					object.plane.material.needsUpdate = true;
				} else if (object.type === 'RoadMark.Sign') {
					object.material.wireframe = value;
					object.material.needsUpdate = true;
				} else if (object.type === 'RoadMark.Signal.TrafficLight') {
					object.material.wireframe = value;
					object.material.needsUpdate = true;
				} else if (object.type === 'RoadMark.Crosswalk') {
					object.material.wireframe = value;
					object.material.needsUpdate = true;
				} else if (object.type === 'RoadMark.TurningMark') {
					object.material.wireframe = value;
					object.material.needsUpdate = true;
				}
			}
		})(scene.children);
		editor.signals.sceneGraphChanged.dispatch();
	});
	container.add(wireframe);

	// Reference line

	editor.config.setKey('referenceLine', false);
	var referenceLine = new UIBoolean(editor.config.getKey('referenceLine'), strings.getKey('menubar/setting/reference_line'));
	referenceLine.setPadding('8px');
	referenceLine.text.setColor('#888');

	referenceLine.onChange(function () {
		var value = this.getValue();
		editor.config.setKey('referenceLine', value);
		var scene = editor.scene;

		(function addObjects(objects) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					// object.refLine.visible = value;
					addObjects(object.children);
				} else if (object.type === 'Lane') {
					object.refLine.visible = value;
					if (object.metalData.position === 'center' || !(object.metalData.type === 'driving' || object.metalData.type === 'ramp')) {
						object.refLine.visible = false;
					}
				} else if (object.type === 'Junction') {
					addObjects(object.children);
				} else if (object.type === 'JunctionLane') {
					object.refLine.visible = value;
				}

				if (object.goType === 'GORoad') {
					// object.refLine.visible = value;
					addObjects(object.children);
				} else if (object.goType === 'GOLane') {
					object.refLine.visible = value;
				}
			}
		})(scene.children);
		editor.signals.sceneGraphChanged.dispatch();
	});
	container.add(referenceLine);

	// Control Sprite

	editor.config.setKey('ctrlSprite', false);
	var ctrlSprite = new UIBoolean(editor.config.getKey('ctrlSprite'), strings.getKey('menubar/setting/controlSprite'));
	ctrlSprite.setPadding('8px');
	ctrlSprite.text.setColor('#888');

	ctrlSprite.onChange(function () {
		var value = this.getValue();
		editor.config.setKey('ctrlSprite', value);
		var scene = editor.scene;

		(function addObjects(objects) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					// object.refLine.visible = value;
					addObjects(object.children);
				} else if (object.type === 'Lane') {
					let isVisible = value ? (object.spriteEdge.offsetType === 'none' ? false : true) : value;
					object.sprite01.visible = isVisible;
					object.sprite02.visible = isVisible;
					object.spriteEdge.visible = isVisible;
				}
			}
		})(scene.children);
		editor.signals.sceneGraphChanged.dispatch();
	});
	container.add(ctrlSprite);

	//
	editor.signals.savingStarted.add(function () {

		wireframe.text.setTextDecoration('underline');
		referenceLine.text.setTextDecoration('underline');
		ctrlSprite.text.setTextDecoration('underline');

	});

	editor.signals.savingFinished.add(function () {

		wireframe.text.setTextDecoration('none');
		referenceLine.text.setTextDecoration('none');
		ctrlSprite.text.setTextDecoration('underline');

	});

	return container;

};

export { MenubarSetting };
