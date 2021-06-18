/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIBreak, UIRow, UIColor, UISelect, UIText } from '../../../vendors/js/libs/ui.js';
import { UIOutliner } from '../../../vendors/js/libs/ui.three.js';

var SidebarRoad = function (editor) {

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

		var html = '<span class="type ' + object.type + '"></span> ' + escapeHTML(object.name);

		if (object.type === 'Lane') {

			html += escapeHTML(` (${object.laneIndex})`)

			html += ' <span class="type ' + object.metalData.position + '"></span> ';

		}

		if (object.refLine !== undefined && object.refLine.visible) {

			html += ' <span class="type refline-visible"></span> ';

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

	// direction

	function onDirectionChanged() {

		signals.scenarioDirectionChanged.dispatch(
			directionType.getValue()
		);

	}

	var directionRow = new UIRow();

	var directionType = new UISelect().setOptions({

		'left': 'Left',
		'right': 'Right'

	}).setWidth('150px');
	directionType.onChange(function () {

		onDirectionChanged();
		refreshDirectionUI();

	});
	directionType.setValue('left');

	directionRow.add(new UIText(strings.getKey('sidebar/scenario/direction')).setWidth('90px'));
	directionRow.add(directionType);

	container.add(directionRow);

	//

	function refreshDirectionUI() {

		var type = directionType.getValue();

		directionType.setDisplay(type === 'Right' ? '' : 'Left');

	}

	//

	function refreshUI() {

		var scene = editor.scene;

		var options = [];

		options.push(buildOption(scene, false));

		(function addObjects(objects, pad) {

			for (var i = 0, l = objects.length; i < l; i++) {

				var object = objects[i];

				if (object.type === 'Road' || object.type === 'Lane') {

					var option = buildOption(object, false);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);

					var leftLanes = object.children.filter(child => child.type === 'Lane' && child.lanePosition === 'left');
					for (var lane of leftLanes) {
						option = buildOption(lane, false);
						option.style.paddingLeft = ((pad + 1) * 10) + 'px';
						options.push(option);
					}

					var centerLanes = object.children.filter(child => child.type === 'Lane' && child.lanePosition === 'center');
					for (var lane of centerLanes) {
						option = buildOption(lane, false);
						option.style.paddingLeft = ((pad + 1) * 10) + 'px';
						options.push(option);
						break;
					}

					var rightLanes = object.children.filter(child => child.type === 'Lane' && child.lanePosition === 'right');
					for (var lane of rightLanes) {
						option = buildOption(lane, false);
						option.style.paddingLeft = ((pad + 1) * 10) + 'px';
						options.push(option);
					}
				} else if (object.goType === 'GORoad') {

					var option = buildOption(object, false);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);

					var Lanes = object.children.filter(child => child.goType === 'GOLane');
					for (var lane of Lanes) {
						option = buildOption(lane, false);
						option.style.paddingLeft = ((pad + 1) * 10) + 'px';
						options.push(option);
					}
				}

			}

		})(scene.children, 1);

		(function addObjects(objects, pad) {

			for (var i = 0, l = objects.length; i < l; i++) {

				var object = objects[i];

				if (object.type === 'Junction' || object.type === 'JunctionLane') {

					var option = buildOption(object, false);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);

					addObjects(object.children, pad + 1);
				}

			}

		})(scene.children, 1);

		outliner.setOptions(options);

		if (editor.selected !== null) {

			outliner.setValue(editor.selected.id);

		}


		directionType.setValue(scene.direction);

		refreshDirectionUI();

	}

	refreshUI();

	// events

	signals.editorCleared.add(refreshUI);

	signals.sceneGraphChanged.add(refreshUI);

	signals.objectChanged.add(function (object) {

		var changedObject = object.type === 'ReferenceLine' ? object.parent : object;

		var options = outliner.options;

		for (var i = 0; i < options.length; i++) {

			var option = options[i];

			if (option.value === changedObject.id) {

				option.innerHTML = buildHTML(changedObject);
				return;

			}

		}

	});

	signals.objectSelected.add(function (object) {

		if (ignoreObjectSelectedSignal === true) return;

		if (object !== null && object.type === 'ControlSprite') {
			outliner.setValue(object !== null && object.parent !== null ? object.parent.id : null);
		} else {
			outliner.setValue(object !== null ? object.id : null);
		}

	});

	return container;

};

export { SidebarRoad };
