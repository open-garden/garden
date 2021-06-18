/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIRow, UIInput, UIText } from '../../../vendors/js/libs/ui.js';

var PopupForm = function (editor) {

	var strings = editor.strings;
	var signals = editor.signals;

	// container
	var container = new UIPanel();
	container.setId('popupForm');
	container.dom.classList.add('popup');

	var innerPanel = new UIPanel();
	innerPanel.dom.classList.add('popup-inner');
	container.add(innerPanel);

	// WayPoint id
	var wayPointIdRow = new UIRow();
	var wayPointId = new UIText();
	wayPointIdRow.add(new UIText(strings.getKey('sidebar/object/waypoint/id')).setWidth('90px'));
	wayPointIdRow.add(wayPointId);
	innerPanel.add(wayPointIdRow);

	// WayPoint index
	var wayPointIndexRow = new UIRow();
	var wayPointIndex = new UIText();
	wayPointIndexRow.add(new UIText(strings.getKey('sidebar/object/waypoint/index')).setWidth('90px'));
	wayPointIndexRow.add(wayPointIndex);
	innerPanel.add(wayPointIndexRow);

	// id
	var objectIDRow = new UIRow();
	var objectID = new UIInput().setWidth('150px').setFontSize('12px').onChange(update);
	objectIDRow.add(new UIText(strings.getKey('sidebar/scenario/id')).setWidth('90px'));
	objectIDRow.add(objectID);
	innerPanel.add(objectIDRow);

	// name
	var objectNameRow = new UIRow();
	var objectName = new UIInput().setWidth('150px').setFontSize('12px').onChange(update);
	objectNameRow.add(new UIText(strings.getKey('sidebar/scenario/name')).setWidth('90px'));
	objectNameRow.add(objectName);
	innerPanel.add(objectNameRow);


	// signals
	signals.objectSelected.add(function (object) {
		if (object !== undefined && object !== null && object.type === 'Sprite') {
			wayPointId.setValue(object.parent.parent.name);
			wayPointIndex.setValue(object.index);

			var props = object.parent.parent.metalData.pointProperties || [];
			var filterProps = props.filter((p) => p.index === object.index);

			if (filterProps.length > 0) {
				objectID.setValue(filterProps[0].id);
				objectName.setValue(filterProps[0].name);
			} else {
				objectID.setValue('');
				objectName.setValue('');
			}
		}
	});


	signals.sceneRendered.add(function () {
		var object = editor.selected;
		if (object !== undefined && object !== null && object.type === 'Sprite') {
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

	//

	function update() {
		var object = editor.selected;
		if (object !== undefined && object !== null && object.type === 'Sprite') {
			var props = object.parent.parent.metalData.pointProperties || [];
			var propIndex = props.findIndex(p => p.index === object.index);

			var newId = objectID.getValue();
			var newName = objectName.getValue();
			var emptyValues = !newId && !newName;
			if (emptyValues) {
				if (propIndex < 0) return;
				else props.splice(propIndex, 1);
				object.material.color.set(object.parent.color);
			} else {
				if (propIndex < 0) {
					props.push({ 'id': newId, 'name': newName, 'index': object.index });
					props.sort(function (a, b) {
						return a.index - b.index;
					});
				} else {
					props[propIndex].id = newId;
					props[propIndex].name = newName;
				}
				object.material.color.setRGB(object.parent.invertColor)
			}
		}
	}


	return container;

};

export { PopupForm };
