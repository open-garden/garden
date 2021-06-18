/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { UIPanel, UIRow, UIInput, UIColor, UICheckbox, UITextArea, UIText, UIButton } from '../../../vendors/js/libs/ui.js';
import { SetValueCommand } from './commands/SetValueCommand.js';
import { SetMetalDataCommand } from './commands/SetMetalDataCommand.js';
import { SetMetalDataValueCommand } from './commands/SetMetalDataValueCommand.js';


var SidebarRouteObject = function (editor) {

	var strings = editor.strings;

	var signals = editor.signals;

	var container = new UIPanel();
	container.setBorderTop('0');
	container.setPaddingTop('20px');
	container.setDisplay('none');

	// type

	var objectTypeRow = new UIRow();
	var objectType = new UIText();

	objectTypeRow.add(new UIText(strings.getKey('sidebar/object/type')).setWidth('90px'));
	objectTypeRow.add(objectType);

	container.add(objectTypeRow);

	// id

	var objectIDRow = new UIRow();
	var objectID = new UIInput().setWidth('150px').setFontSize('12px').onChange(function () {
		const selected = editor.selected;
		if (selected.type === 'RoadStructure' || selected.type === 'Route') {
			if (objectID.isCorrectFormat()) {
				editor.execute(new SetMetalDataValueCommand(editor, selected, 'gid', objectID.getValue()));
			} else {
				alert('Inserted data for ID is invalid.\nStart with only Alphabet.\nAnd alphabet, number and \'.\' only are allowed.');
				objectID.setValue(selected.gid);
			}
		} else {
			editor.execute(new SetMetalDataValueCommand(editor, selected, 'id', objectID.getValue()));
		}
	});

	objectIDRow.add(new UIText(strings.getKey('sidebar/scenario/id')).setWidth('90px'));
	objectIDRow.add(objectID);

	container.add(objectIDRow);

	// description

	var objectDescriptionRow = new UIRow();
	var objectDescription = new UITextArea().setWidth('150px').setFontSize('12px').onChange(function () {
		editor.execute(new SetMetalDataValueCommand(editor, editor.selected, 'description', objectDescription.getValue()));

	});

	objectDescriptionRow.add(new UIText(strings.getKey('sidebar/scenario/description')).setWidth('90px'));
	objectDescriptionRow.add(objectDescription);

	container.add(objectDescriptionRow);

	// Route

	var routeRouteRow = new UIRow();
	var routeRouteEdit = new UIButton(strings.getKey('sidebar/script/edit'));
	routeRouteEdit.setMarginLeft('4px');
	routeRouteEdit.onClick(function () {
		var object = editor.selected;
		if (object !== null && object.type === 'Route') {
			signals.editScript.dispatch(object, 'routeRoute');
		}
	});

	routeRouteRow.add(new UIText(strings.getKey('sidebar/scenario/route/route')).setWidth('90px'));
	routeRouteRow.add(routeRouteEdit);

	container.add(routeRouteRow);

	// Route WayPoints

	var routeWayPointsRow = new UIRow();
	var routeWayPointsVisible = new UICheckbox(true).setPosition('absolute').setLeft('75px').onChange(update);
	var routeWayPointsColor = new UIColor().setDisabled(true);
	var routeWayPointsView = new UIButton(strings.getKey('sidebar/script/view'));
	routeWayPointsView.setMarginLeft('4px');
	routeWayPointsView.onClick(function () {
		var object = editor.selected;
		if (object !== null && object.type === 'Route') {
			signals.editScript.dispatch(object, 'routeWayPoints');
		}
	});

	routeWayPointsRow.add(new UIText(strings.getKey('sidebar/scenario/route/waypoints')).setWidth('90px'));
	routeWayPointsRow.add(routeWayPointsVisible);
	routeWayPointsRow.add(routeWayPointsColor);
	routeWayPointsRow.add(routeWayPointsView);

	container.add(routeWayPointsRow);

	// Route PointProperties

	var routePointPropsRow = new UIRow();
	var routePointPropsView = new UIButton(strings.getKey('sidebar/script/view'));
	routePointPropsView.setMarginLeft('4px');
	routePointPropsView.onClick(function () {
		var object = editor.selected;
		if (object !== null && object.type === 'Route') {
			signals.editScript.dispatch(object, 'routePointProps');
		}
	});

	routePointPropsRow.add(new UIText(strings.getKey('sidebar/scenario/route/pointprops')).setWidth('90px'));
	routePointPropsRow.add(routePointPropsView);

	container.add(routePointPropsRow);


	//

	function update() {

		var scene = editor.scene;

		var object = editor.selected;

		if (object !== null) {

			if (object.type === 'RoadStructure') {

				// TODO

			} else {

				if (object.type === 'Route') {

					var metalData = JSON.parse(JSON.stringify(object.metalData));

					if (object.visible !== routeWayPointsVisible.getValue()) {

						editor.execute(new SetValueCommand(editor, object, 'visible', routeWayPointsVisible.getValue()));

					}

					if (JSON.stringify(object.metalData) != JSON.stringify(metalData)) {

						editor.execute(new SetMetalDataCommand(editor, object, 'metalData', metalData));

					}

				}
			}
		}
	}

	//

	function updateRows(object) {

		var roadStructureProperties = {
		};

		var routeProperties = {
			'routeRoute': routeRouteRow,
			'routeWayPoints': routeWayPointsRow,
			'routePointProps': routePointPropsRow,
			'objectDescriptionRow': objectDescriptionRow
		};

		if (object.type === 'RoadStructure') {
			for (var property in routeProperties) {
				routeProperties[property].setDisplay('none');
			}
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('');
			}
		} else if (object.type === 'Route') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in routeProperties) {
				routeProperties[property].setDisplay('');
			}
		} else {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in routeProperties) {
				routeProperties[property].setDisplay('none');
			}
		}

	}

	// events

	signals.objectSelected.add(function (object) {

		if (object !== null && (object.type === 'RoadStructure' || object.type === 'Route')) {

			container.setDisplay('block');

			updateRows(object);
			updateUI(object);

		} else {

			container.setDisplay('none');

		}

	});

	signals.objectChanged.add(function (object) {

		if (object !== editor.selected) return;

		updateUI(object);

	});

	signals.refreshSidebarObject3D.add(function (object) {

		if (object !== editor.selected) return;

		updateUI(object);

	});

	function updateUI(object) {

		var scene = editor.scene;

		objectType.setValue(object.type);
		objectID.setValue(object.name);
		objectDescription.setValue(object.description);

		if (object.type === 'RoadStructure') {

			// TODO

		} else if (object.type === 'Route') {

			var currentColor = new THREE.Color(object.color);

			routeWayPointsColor.setValue('#' + currentColor.getHexString());
			routeWayPointsVisible.setValue(object.visible);

		}

	}

	return container;

};

export { SidebarRouteObject };
