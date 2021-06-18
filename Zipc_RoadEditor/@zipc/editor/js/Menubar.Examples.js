/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { UIPanel, UIRow, UIHorizontalRule } from '../../../vendors/js/libs/ui.js';

var MenubarExamples = function (editor) {

	var strings = editor.strings;

	var junctionBoxHelper = new THREE.BoxHelper();
	junctionBoxHelper.material.depthTest = false;
	junctionBoxHelper.material.transparent = true;
	junctionBoxHelper.visible = false;

	var container = new UIPanel();
	container.setClass('menu');

	var title = new UIPanel();
	title.setClass('title');
	title.setTextContent(strings.getKey('menubar/examples'));
	container.add(title);

	var options = new UIPanel();
	options.setClass('options');
	container.add(options);

	var items = [
		{ title: 'menubar/examples/lane/01', file: 'lane_01.json' },
		{ title: 'menubar/examples/road/curve', file: 'road_curve.json' },
		{ title: 'menubar/examples/road/branch', file: 'road_branch.json' },
		{ title: 'menubar/examples/road/merge', file: 'road_merge.json' },
		null,
		{ title: 'menubar/examples/kouhokuinter', file: 'kouhokuinter.json' },
		{ title: 'menubar/examples/junction', file: 'junction.json' },
		// { title: 'menubar/examples/trajectory', file: 'trajectory.json' },
	];

	var loader = new THREE.FileLoader();

	for (var i = 0; i < items.length; i++) {

		(function (i) {

			var item = items[i];

			if (item === null) {
				options.add(new UIHorizontalRule());
			} else {
				var option = new UIRow();
				option.setClass('option');
				option.setTextContent(strings.getKey(item.title));
				option.onClick(function () {

					if (confirm('Any unsaved data will be lost. Are you sure?')) {

						loader.load('examples/' + item.file, function (text) {

							editor.clear();
							editor.loader.loadFromJson(JSON.parse(text));

						});
					}

					editor.select(editor.scene);
				});
				options.add(option);
			}

		})(i);

	}

	return container;

};

export { MenubarExamples };
