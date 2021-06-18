/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIBreak } from '../../../vendors/js/libs/ui.js';
import { UIOutliner, UITexture, UICubeTexture } from '../../../vendors/js/libs/ui.three.js';

var SidebarRoute = function (editor) {

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

		(function addObjects(objects, pad) {

			for (var i = 0, l = objects.length; i < l; i++) {

				var object = objects[i];

				if (object.type === 'Route') {

					var option = buildOption(object, true);
					option.style.paddingLeft = (pad * 10) + 'px';
					options.push(option);

				}

			}

		})(scene.children, 1);

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

export { SidebarRoute };
