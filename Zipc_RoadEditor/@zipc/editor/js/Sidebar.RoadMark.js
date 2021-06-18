/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIBreak } from '../../../vendors/js/libs/ui.js';
import { UIOutliner } from '../../../vendors/js/libs/ui.three.js';

var SidebarRoadMark = function (editor) {

	var signals = editor.signals;
	var strings = editor.strings;

	var container = new UIPanel();
	container.setBorderTop('0');
	container.setPaddingTop('20px');

	// outliner

	function buildOption(object, draggable) {

		var option = document.createElement('div');
		option.draggable = draggable;
		option.innerHTML = buildHTML(object);
		option.value = object.id;

		return option;

	}

	function getMaterialName(material) {

		if (Array.isArray(material)) {

			var array = [];

			for (var i = 0; i < material.length; i++) {

				array.push(material[i].name);

			}

			return array.join(',');

		}

		return material.name;

	}

	function escapeHTML(html) {

		return html
			.replace(/&/g, '&amp;')
			.replace(/"/g, '&quot;')
			.replace(/'/g, '&#39;')
			.replace(/</g, '&lt;')
			.replace(/>/g, '&gt;');

	}

	function buildHTML(object) {

		var html = '<span class="type ' + object.type + '"></span>';

		if (object.type === 'RoadMark.LineSegment') {
			try {
				html += ` ${escapeHTML(object.lane[1].road)}_${escapeHTML(object.lane[1].lane)}`;
			} catch (e) {
				// TODO
			}
		} else {
			html += ` ${escapeHTML(object.name)}`;
		}

		if (object.isMesh) {

			var geometry = object.geometry;
			var material = object.material;

			html += ' <span class="type ' + geometry.type + '"></span> ' + escapeHTML(geometry.name);
			html += ' <span class="type ' + material.type + '"></span> ' + escapeHTML(getMaterialName(material));

		}

		html += getScript(object.uuid);

		return html;

	}

	function getScript(uuid) {

		if (editor.scripts[uuid] !== undefined) {

			return ' <span class="type Script"></span>';

		}

		return '';

	}

	var ignoreObjectSelectedSignal = false;

	var outliner = new UIOutliner(editor);
	outliner.setId('outliner');
	outliner.onChange(function () {

		ignoreObjectSelectedSignal = true;

		editor.selectById(parseInt(outliner.getValue()));

		ignoreObjectSelectedSignal = false;

	});
	outliner.onDblClick(function () {

		editor.focusById(parseInt(outliner.getValue()));

	});
	container.add(outliner);
	container.add(new UIBreak());

	//

	function refreshUI() {

		var scene = editor.scene;

		var options = [];

		options.push(buildOption(scene, false));

		// 白線
		(function addObjects(objects, pad) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					addObjects(object.children, pad);
				} else if (object.type === 'Lane') {
					addObjects(object.children, pad);
				} else if (object.type === 'RoadMark.Line') {
					var option = buildOption(object, true);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);

					for (var ls of object.segments) {
						option = buildOption(ls, false);
						option.style.paddingLeft = ((pad + 1) * 10) + 'px';
						options.push(option);
					}
				}
			}

		})(scene.children, 1);

		// 路面標識
		let arrows = [];
		(function addObjects(objects, pad) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					addObjects(object.children, pad);
				} else if (object.type === 'Lane') {
					addObjects(object.children, pad);
				} else if (object.type === 'RoadMark.Sign') {
					arrows.push(object);
				}
			}
		})(scene.children, 1);
		arrows.sort(function (a, b) {
			return a.id - b.id;
		});
		for (let arrow of arrows) {
			var option = buildOption(arrow, true);
			option.style.paddingLeft = '10px';
			options.push(option);
		}

		//横断歩道
		(function addObjects(objects, pad) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					addObjects(object.children, pad);
				} else if (object.type === 'Junction') {
					addObjects(object.children, pad);
				} else if (object.type === 'RoadMark.Crosswalk') {
					var option = buildOption(object, true);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);
				}
			}
		})(scene.children, 1);

		//右左折の方法
		(function addObjects(objects, pad) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					addObjects(object.children, pad);
				} else if (object.type === 'Junction') {
					addObjects(object.children, pad);
				} else if (object.type === 'RoadMark.TurningMark') {
					var option = buildOption(object, true);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);
				}
			}
		})(scene.children, 1);

		// 信号
		let signals = [];
		(function addObjects(objects, pad) {
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (object.type === 'Road') {
					addObjects(object.children, pad);
				} else if (object.type === 'Lane') {
					addObjects(object.children, pad);
				} else if (object.type === 'RoadMark.Signal.TrafficLight') {
					signals.push(object);
				}
			}
		})(scene.children, 1);
		signals.sort(function (a, b) {
			return a.id - b.id;
		});
		for (let signal of signals) {
			var option = buildOption(signal, true);
			option.style.paddingLeft = '10px';
			options.push(option);
		}

		outliner.setOptions(options);

		if (editor.selected !== null) {

			outliner.setValue(editor.selected.id);

		}

	}

	refreshUI();

	// events

	signals.editorCleared.add(refreshUI);

	signals.sceneGraphChanged.add(refreshUI);

	signals.objectChanged.add(function (object) {

		var options = outliner.options;

		for (var i = 0; i < options.length; i++) {

			var option = options[i];

			if (option.value === object.id) {

				option.innerHTML = buildHTML(object);
				return;

			}

		}

	});

	signals.objectSelected.add(function (object) {

		if (ignoreObjectSelectedSignal === true) return;

		outliner.setValue(object !== null ? object.id : null);

	});

	return container;

};

export { SidebarRoadMark };
