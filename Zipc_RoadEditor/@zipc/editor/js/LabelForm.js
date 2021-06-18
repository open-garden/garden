/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIRow, UIText } from '../../../vendors/js/libs/ui.js';

var LabelForm = function (editor) {

	var strings = editor.strings;
	var signals = editor.signals;

	// container
	var container = new UIPanel();
	container.setId('labelForm');
	container.dom.classList.add('label');

	var innerPanel = new UIPanel();
	innerPanel.dom.classList.add('label-inner');
	container.add(innerPanel);

	// position2
	var spritePositionRow = new UIRow();
	var spritePosition = new UIText();
	spritePositionRow.add(spritePosition);
	innerPanel.add(spritePositionRow);


	// signals
	signals.objectSelected.add(function (object) {
		if (object !== undefined && object !== null && object.type === 'ControlSprite') {
			spritePosition.setValue(`${object.position2.x.toFixed(3)}, ${object.position2.y.toFixed(3)}`);
		}
	});


	signals.sceneRendered.add(function () {
		var object = editor.selected;
		if (object !== undefined && object !== null && object.type === 'ControlSprite') {
			spritePosition.setValue(`${object.position2.x.toFixed(3)}, ${object.position2.y.toFixed(3)}`);
			var rect = document.getElementById("viewport").getBoundingClientRect();
			const project = object.getWorldPosition(new THREE.Vector3()).clone().project(editor.camera);
			const sx = (rect.width / 2) * (project.x + 1.0) - rect.left;
			const sy = (rect.height / 2) * (-project.y + 1.0) + rect.top;
			container.setLeft(sx + 'px').setTop(sy + 'px');
			if (!innerPanel.dom.classList.toggle('show')) {
				innerPanel.dom.classList.toggle('show');
			}
		} else if (innerPanel.dom.classList.toggle('show')) {
			innerPanel.dom.classList.toggle('show');
		}
	});

	return container;

};

export { LabelForm };
