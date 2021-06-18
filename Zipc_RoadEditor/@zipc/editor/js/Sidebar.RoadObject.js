/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { Utils } from '../../garden-objects/js/Utils.js';

import { UIPanel, UIRow, UIInput, UISelect, UICheckbox, UIInteger, UIText, UINumber, UIButton, UITable, UIFieldset, UITextArea } from '../../../vendors/js/libs/ui.js';
import { UITexture } from '../../../vendors/js/libs/ui.three.js';

import { SetValueCommand } from './commands/SetValueCommand.js';
import { SetPositionCommand } from './commands/SetPositionCommand.js';
import { SetRotationCommand } from './commands/SetRotationCommand.js';
import { SetConnectionCommand } from './commands/SetConnectionCommand.js';
import { SetMetalDataCommand } from './commands/SetMetalDataCommand.js';
import { SetMetalDataValueCommand } from './commands/SetMetalDataValueCommand.js';
import { SetJunctionConnectionCommand } from './commands/SetJunctionConnectionCommand.js';
import { SetJunctionEdgeTypeCommand } from './commands/SetJunctionEdgeTypeCommand.js';
import { SetJunctionLanesCommand } from './commands/SetJunctionLanesCommand.js';
import { AddLaneCommand } from './commands/AddLaneCommand.js';
import { MoveLaneCommand } from './commands/MoveLaneCommand.js';
import { AddGOLaneCommand } from './commands/AddGOLaneCommand.js';
import { SetGOLanePropertyCommand } from './commands/SetGOLanePropertyCommand.js';
import { SetControlSpritePosition2Command } from './commands/SetControlSpritePosition2Command.js';
import { MultiCmdsCommand } from './commands/MultiCmdsCommand.js';
import { SetOffsetTypeCommand } from './commands/SetOffsetTypeCommand.js';





var SidebarRoadObject = function (editor) {

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

	// position

	var objectPositionRow = new UIRow();
	var objectPositionX = new UINumber().setPrecision(3).setWidth('50px').onChange(update);
	var objectPositionY = new UINumber().setPrecision(3).setWidth('50px').onChange(update);
	var objectPositionZ = new UINumber().setPrecision(3).setWidth('50px').onChange(update);

	objectPositionRow.add(new UIText(strings.getKey('sidebar/object/position')).setWidth('90px'));
	objectPositionRow.add(objectPositionX, objectPositionY, objectPositionZ);

	container.add(objectPositionRow);

	// rotation

	var objectRotationRow = new UIRow();
	var objectRotationX = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);
	var objectRotationY = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);
	var objectRotationZ = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);

	objectRotationRow.add(new UIText(strings.getKey('sidebar/object/rotation')).setWidth('90px'));
	objectRotationRow.add(objectRotationX, objectRotationY, objectRotationZ);

	container.add(objectRotationRow);

	// background00 range

	var background00Fieldset = new UIFieldset(strings.getKey('sidebar/scenario/background/00'));

	// Top Max Lat
	var background00TopRow = new UIRow();
	background00TopRow.add(new UIText(strings.getKey('sidebar/scenario/background/00/top')).setWidth('75px'));
	var background00Top = new UINumber().setPrecision(5).setStep(10).setNudge(0.1).setUnit('°').setWidth('100px').onChange(() => { });
	background00Top.setValue(0.00);
	background00TopRow.add(background00Top);
	background00Fieldset.add(background00TopRow);

	// Bottom Min Lat
	var background00BottomRow = new UIRow();
	background00BottomRow.add(new UIText(strings.getKey('sidebar/scenario/background/00/bottom')).setWidth('75px'));
	var background00Bottom = new UINumber().setPrecision(5).setStep(10).setNudge(0.1).setUnit('°').setWidth('100px').onChange(() => { });
	background00Bottom.setValue(0.00);
	background00BottomRow.add(background00Bottom);
	background00Fieldset.add(background00BottomRow);

	// Left Min Lon
	var background00LeftRow = new UIRow();
	background00LeftRow.add(new UIText(strings.getKey('sidebar/scenario/background/00/left')).setWidth('75px'));
	var background00Left = new UINumber().setPrecision(5).setStep(10).setNudge(0.1).setUnit('°').setWidth('100px').onChange(() => { });
	background00Left.setValue(0.00);
	background00LeftRow.add(background00Left);
	background00Fieldset.add(background00LeftRow);

	// Right Max Lon
	var background00RightRow = new UIRow();
	background00RightRow.add(new UIText(strings.getKey('sidebar/scenario/background/00/right')).setWidth('75px'));
	var background00Right = new UINumber().setPrecision(5).setStep(10).setNudge(0.1).setUnit('°').setWidth('100px').onChange(() => { });
	background00Right.setValue(0.00);
	background00RightRow.add(background00Right);
	background00Fieldset.add(background00RightRow);

	var background00EnabledRow = new UIRow();
	background00EnabledRow.add(new UIText(strings.getKey('sidebar/scenario/background/00/visible')).setWidth('75px'));
	var background00Enabled = new UICheckbox(false).setPosition('absolute').setLeft('75px').onChange(onBackground00Changed);
	background00EnabledRow.add(background00Enabled);

	var background00UpdateButton = new UIButton(strings.getKey('sidebar/scenario/background/00/button/load')).onClick(function () {

		var isVisible = background00Enabled.getValue() === true;

		var boundary = {
			n: background00Top.getValue(),
			e: background00Right.getValue(),
			s: background00Bottom.getValue(),
			w: background00Left.getValue()
		};

		signals.scenarioBackground00Changed.dispatch(
			boundary,
			isVisible,
			true
		);
	});
	background00EnabledRow.add(background00UpdateButton);
	background00Fieldset.add(background00EnabledRow);

	// Road 自動生成
	var background00GenerateRow = new UIRow().setPaddingTop('10px');;
	var background00GenerateButton = new UIButton(strings.getKey('sidebar/scenario/background/00/button/generate')).onClick(function () {
		signals.scenarioBackground00Generate.dispatch(
			{
				n: background00Top.getValue(),
				e: background00Right.getValue(),
				s: background00Bottom.getValue(),
				w: background00Left.getValue()
			}
		);
	});
	background00GenerateRow.add(background00GenerateButton);
	// background00Fieldset.add(background00GenerateRow);



	function onBackground00Changed() {

		var isVisible = background00Enabled.getValue() === true;

		var boundary = {
			n: background00Top.getValue(),
			e: background00Right.getValue(),
			s: background00Bottom.getValue(),
			w: background00Left.getValue()
		};

		signals.scenarioBackground00Changed.dispatch(
			boundary,
			isVisible,
			false
		);

	}


	container.add(background00Fieldset);

	// background01 position

	var background01Fieldset = new UIFieldset(strings.getKey('sidebar/scenario/background/01')).setMarginTop('10px');

	var background01PositionRow = new UIRow();
	var background01PositionX = new UINumber().setPrecision(3).setWidth('50px').onChange(update);
	var background01PositionY = new UINumber().setPrecision(3).setWidth('50px').onChange(update);
	var background01PositionZ = new UINumber().setPrecision(3).setWidth('50px').onChange(update);

	background01PositionRow.add(new UIText(strings.getKey('sidebar/object/position')).setWidth('75px'));
	background01PositionRow.add(background01PositionX, background01PositionY, background01PositionZ);

	background01Fieldset.add(background01PositionRow);

	// background01 rotation

	var background01RotationRow = new UIRow();
	var background01RotationX = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);
	var background01RotationY = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);
	var background01RotationZ = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);

	background01RotationRow.add(new UIText(strings.getKey('sidebar/object/rotation')).setWidth('75px'));
	background01RotationRow.add(background01RotationX, background01RotationY, background01RotationZ);

	background01Fieldset.add(background01RotationRow);

	// background01 scale

	var background01ScaleRow = new UIRow();
	var background01Scale = new UINumber(1).setPrecision(3).setRange(0.001, Infinity).setWidth('50px').onChange(update);

	background01ScaleRow.add(new UIText(strings.getKey('sidebar/object/scale')).setWidth('75px'));
	background01ScaleRow.add(background01Scale);

	background01Fieldset.add(background01ScaleRow);

	// background01

	function onBackground01Changed() {

		var isVisible = background01Enabled.getValue() === true;

		signals.scenarioBackground01Changed.dispatch(
			'Texture',
			background01Texture.getValue(),
			isVisible
		);

	}

	function onTexture01Changed(texture) {

		texture.encoding = texture.isHDRTexture ? THREE.RGBEEncoding : THREE.sRGBEncoding;

		if (texture.isCubeTexture && texture.isHDRTexture) {

			texture.format = THREE.RGBAFormat;
			texture.minFilter = THREE.NearestFilter;
			texture.magFilter = THREE.NearestFilter;
			texture.generateMipmaps = false;

		}

		onBackground01Changed();

	}

	var background01Row = new UIRow();
	background01Row.add(new UIText(strings.getKey('sidebar/scenario/background/image')).setWidth('75px'));
	var background01Enabled = new UICheckbox(false).setPosition('absolute').setLeft('75px').onChange(onTexture01Changed);
	background01Row.add(background01Enabled);

	var background01Texture = new UITexture().onChange(onTexture01Changed);
	background01Row.add(background01Texture);

	background01Fieldset.add(background01Row);
	container.add(background01Fieldset);

	// background02 position

	var background02Fieldset = new UIFieldset(strings.getKey('sidebar/scenario/background/02')).setMarginTop('10px');

	var background02PositionRow = new UIRow();
	var background02PositionX = new UINumber().setPrecision(3).setWidth('50px').onChange(update);
	var background02PositionY = new UINumber().setPrecision(3).setWidth('50px').onChange(update);
	var background02PositionZ = new UINumber().setPrecision(3).setWidth('50px').onChange(update);

	background02PositionRow.add(new UIText(strings.getKey('sidebar/object/position')).setWidth('75px'));
	background02PositionRow.add(background02PositionX, background02PositionY, background02PositionZ);

	background02Fieldset.add(background02PositionRow);

	// background02 rotation

	var background02RotationRow = new UIRow();
	var background02RotationX = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);
	var background02RotationY = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);
	var background02RotationZ = new UINumber().setStep(10).setNudge(0.1).setUnit('°').setWidth('50px').onChange(update);

	background02RotationRow.add(new UIText(strings.getKey('sidebar/object/rotation')).setWidth('75px'));
	background02RotationRow.add(background02RotationX, background02RotationY, background02RotationZ);

	background02Fieldset.add(background02RotationRow);

	// background02 scale

	var background02ScaleRow = new UIRow();
	var background02Scale = new UINumber(1).setPrecision(3).setRange(0.001, Infinity).setWidth('50px').onChange(update);

	background02ScaleRow.add(new UIText(strings.getKey('sidebar/object/scale')).setWidth('75px'));
	background02ScaleRow.add(background02Scale);

	background02Fieldset.add(background02ScaleRow);

	// background02

	function onBackground02Changed() {

		var isVisible = background02Enabled.getValue() === true;

		signals.scenarioBackground02Changed.dispatch(
			'Texture',
			background02Texture.getValue(),
			isVisible
		);

	}

	function onTexture02Changed(texture) {

		texture.encoding = texture.isHDRTexture ? THREE.RGBEEncoding : THREE.sRGBEncoding;

		if (texture.isCubeTexture && texture.isHDRTexture) {

			texture.format = THREE.RGBAFormat;
			texture.minFilter = THREE.NearestFilter;
			texture.magFilter = THREE.NearestFilter;
			texture.generateMipmaps = false;

		}

		onBackground02Changed();

	}

	var background02Row = new UIRow();
	background02Row.add(new UIText(strings.getKey('sidebar/scenario/background/image')).setWidth('75px'));
	var background02Enabled = new UICheckbox(false).setPosition('absolute').setLeft('75px').onChange(onTexture02Changed);
	background02Row.add(background02Enabled);

	var background02Texture = new UITexture().onChange(onTexture02Changed);
	background02Row.add(background02Texture);

	background02Fieldset.add(background02Row);
	container.add(background02Fieldset);


	// Road Reverse

	var roadPriorityRow = new UIRow();
	var roadPriority = new UICheckbox().onChange(update);

	roadPriorityRow.add(new UIText(strings.getKey('sidebar/scenario/road/priority')).setWidth('90px'));
	roadPriorityRow.add(roadPriority);

	container.add(roadPriorityRow);

	// Road Connection

	var roadConnectionRow = new UIRow();

	var roadConnectionOptions = {};
	roadConnectionOptions[Utils.MIN_INTEGER_STRING] = '';

	var roadConnection = new UISelect().setOptions(roadConnectionOptions).setWidth('150px');
	roadConnection.onChange(function () {

		refreshRoadConnectionUI();
		updateConnection();

	});
	roadConnection.setValue(Utils.MIN_INTEGER_STRING);

	roadConnectionRow.add(new UIText(strings.getKey('sidebar/scenario/road/connection')).setWidth('90px'));
	roadConnectionRow.add(roadConnection);

	container.add(roadConnectionRow);

	// Road Reverse

	var roadReverseRow = new UIRow();
	var roadReverse = new UICheckbox().onChange(updateConnection);

	roadReverseRow.add(new UIText(strings.getKey('sidebar/scenario/road/reverse')).setWidth('90px'));
	roadReverseRow.add(roadReverse);

	container.add(roadReverseRow);

	// Road Length

	var roadLengthRow = new UIRow();
	var roadLength = new UIInteger().setWidth('50px').onChange(update);

	roadLengthRow.add(new UIText(strings.getKey('sidebar/scenario/road/length')).setWidth('90px'));
	roadLengthRow.add(roadLength);

	container.add(roadLengthRow);

	// GORoad Length

	var goRoadLengthRow = new UIRow();
	var goRoadLength = new UIText();

	goRoadLengthRow.add(new UIText(strings.getKey('sidebar/scenario/road/length')).setWidth('90px'));
	goRoadLengthRow.add(goRoadLength);

	container.add(goRoadLengthRow);

	// Road Height

	var roadHeightRow = new UIRow();
	var roadHeight = new UINumber().setPrecision(1).setWidth('50px').onChange(update);

	roadHeightRow.add(new UIText(strings.getKey('sidebar/scenario/road/height')).setWidth('90px'));
	roadHeightRow.add(roadHeight);

	container.add(roadHeightRow);

	// Road Ramp Angle

	var roadRampAngleRow = new UIRow();
	var roadRampAngle = new UINumber().setPrecision(2).setRange(0, 90).setWidth('50px').onChange(update);

	roadRampAngleRow.add(new UIText(strings.getKey('sidebar/scenario/road/rampangle')).setWidth('90px'));
	roadRampAngleRow.add(roadRampAngle);

	container.add(roadRampAngleRow);

	// Road Type

	var roadTypeRow = new UIRow();

	var roadType = new UISelect().setOptions({

		'straight': 'Straight',
		'circular': 'Circular',
		'clothoid_in': 'Clothoid (In)',
		'clothoid_out': 'Clothoid (Out)',
		'cubic_left': 'Cubic (Left)',
		'cubic_right': 'Cubic (Right)'

	}).setWidth('150px');
	roadType.onChange(function () {

		refreshRoadTypeUI();
		update();

	});
	roadType.setValue('straight');

	roadTypeRow.add(new UIText(strings.getKey('sidebar/scenario/road/type')).setWidth('90px'));
	roadTypeRow.add(roadType);

	container.add(roadTypeRow);

	// Road Radius

	var roadRadiusRow = new UIRow();
	var roadRadius = new UINumber().setPrecision(1).setWidth('50px').onChange(update);

	roadRadiusRow.add(new UIText(strings.getKey('sidebar/scenario/road/radius')).setWidth('90px'));
	roadRadiusRow.add(roadRadius);

	container.add(roadRadiusRow);

	// Lane Type

	var laneTypeRow = new UIRow();

	var laneType = new UISelect().setOptions({

		'driving': 'Driving',
		'ramp': 'Ramp',
		'center': 'Center',
		'border': 'Border',
		'sidewalk': 'Sidewalk'

	}).setWidth('150px').onChange(update);
	laneType.setValue('driving');

	laneTypeRow.add(new UIText(strings.getKey('sidebar/scenario/lane/type')).setWidth('90px'));
	laneTypeRow.add(laneType);

	container.add(laneTypeRow);

	// GOLane Type

	var goLaneTypeRow = new UIRow();

	var goLaneType = new UISelect().setOptions({

		'driving': 'Driving',
		'center': 'Center',
		'border': 'Border',
		'sidewalk': 'Sidewalk'

	}).setWidth('150px').onChange(update);
	goLaneType.setValue('driving');

	goLaneTypeRow.add(new UIText(strings.getKey('sidebar/scenario/lane/type')).setWidth('90px'));
	goLaneTypeRow.add(goLaneType);

	container.add(goLaneTypeRow);

	// Lane Position

	var lanePositionRow = new UIRow();

	var lanePosition = new UISelect().setOptions({
		'left': 'Left',
		'right': 'Right'
	}).setWidth('150px').onChange(function () {
		var object = editor.selected;
		if (object === null || object.type !== 'Lane')
			return;

		if (object.lanePosition !== lanePosition.getValue()) {
			editor.execute(new MoveLaneCommand(editor, object, lanePosition.getValue()));
		}
	});
	lanePosition.setValue('Left');

	var lanePositionLabel = new UIText('CENTER');

	lanePositionRow.add(new UIText(strings.getKey('sidebar/scenario/lane/position')).setWidth('90px'));
	lanePositionRow.add(lanePosition, lanePositionLabel);

	container.add(lanePositionRow);

	// Lane Width

	var laneWidthRow = new UIRow();
	var laneWidth = new UINumber().setPrecision(2).setWidth('50px').setRange(0, 100).onChange(update);

	laneWidthRow.add(new UIText(strings.getKey('sidebar/scenario/lane/width')).setWidth('90px'));
	laneWidthRow.add(laneWidth);

	container.add(laneWidthRow);

	// GOLane Width

	var goLaneWidthRow = new UIRow();
	var goLaneWidth = new UINumber().setPrecision(1).setWidth('50px').setRange(0, 100).onChange(update);

	goLaneWidthRow.add(new UIText(strings.getKey('sidebar/scenario/lane/width')).setWidth('90px'));
	goLaneWidthRow.add(goLaneWidth);

	container.add(goLaneWidthRow);

	// Junction Edge Type

	var junctionEdgeTypeRow = new UIRow();

	var junctionEdgeType = new UISelect().setOptions({

		'typea': 'Type A',
		'typeb': 'Type B',
		'typec': 'Type C '

	}).setWidth('150px');
	junctionEdgeType.onChange(update);
	junctionEdgeType.setValue('typea');

	junctionEdgeTypeRow.add(new UIText(strings.getKey('sidebar/scenario/junction/edge/type')).setWidth('90px'));
	junctionEdgeTypeRow.add(junctionEdgeType);

	container.add(junctionEdgeTypeRow);

	// Junction Connections ID
	var junctionConnectionsIdRow = new UIRow().setPaddingTop('10px');
	var junctionConnectionsIdOptions = {};
	junctionConnectionsIdOptions[Utils.MIN_INTEGER_STRING] = '';
	var junctionConnectionsId = new UISelect().setOptions(junctionConnectionsIdOptions).setWidth('120px');
	junctionConnectionsId.setValue(Utils.MIN_INTEGER_STRING);

	junctionConnectionsIdRow.add(new UIText(strings.getKey('sidebar/scenario/junction/connections/id')).setWidth('90px'));
	junctionConnectionsIdRow.add(junctionConnectionsId);

	// Junction Connection Add
	var junctionConnectionAddButton = new UIButton(strings.getKey('sidebar/scenario/junction/connections/button/add')).onClick(function () {
		var object = editor.selected;
		if (object !== null && object.type === 'Junction') {
			var newObjectId = junctionConnectionsId.getValue();
			var newType = junctionConnectionsType.getValue();

			var metalData = JSON.parse(JSON.stringify(object.metalData));
			var connectionObject = editor.scene.getObjectById(parseInt(newObjectId), true);

			if (connectionObject !== undefined && connectionObject !== null && connectionObject.type === 'Road') {
				for (var connection of metalData.connections) {
					if (connection.objectId === newObjectId) {
						if (connection.type !== newType || connection.id === connectionObject.name) {
							connection.type = newType;
							connection.id = connectionObject.name;

							editor.execute(new SetJunctionConnectionCommand(editor, object, metalData.connections));
							return;
						}
					}
				}

				metalData.connections.push({
					'objectId': '' + connectionObject.id,
					'id': connectionObject.name,
					'type': junctionConnectionsType.getValue()
				});
				editor.execute(new SetJunctionConnectionCommand(editor, object, metalData.connections));
			}
		}
	}).setMarginLeft('4px');
	junctionConnectionsIdRow.add(junctionConnectionAddButton);

	container.add(junctionConnectionsIdRow);

	// Junction Connections Type
	var junctionConnectionsTypeRow = new UIRow();
	var junctionConnectionsType = new UISelect().setOptions({
		'successor': 'Successor',
		'predecessor': 'Predecessor'
	}).setWidth('120px');
	junctionConnectionsType.setValue('successor');

	junctionConnectionsTypeRow.add(new UIText(strings.getKey('sidebar/scenario/junction/connections/type')).setWidth('90px'));
	junctionConnectionsTypeRow.add(junctionConnectionsType);

	// Junction Connection Edit
	var junctionConnectionEditButton = new UIButton(strings.getKey('sidebar/scenario/junction/connections/button/edit')).onClick(function () {
		var object = editor.selected;
		if (object !== null && object.type === 'Junction') {
			var newObjectId = junctionConnectionsId.getValue();
			var newType = junctionConnectionsType.getValue();
			var selectedIndex = junctionConnectionsTable.selectedIndex;
			if (selectedIndex === Number.MIN_SAFE_INTEGER) {
				return;
			}

			var metalData = JSON.parse(JSON.stringify(object.metalData));
			var selectedConnections = metalData.connections[selectedIndex];
			if (selectedConnections !== undefined) {
				var connectionObject = editor.scene.getObjectById(parseInt(newObjectId), true);

				if (connectionObject !== undefined && connectionObject !== null && connectionObject.type === 'Road') {
					if (selectedConnections.objectId !== newObjectId || selectedConnections.type !== newType || selectedConnections.id === connectionObject.name) {
						selectedConnections.objectId = newObjectId;
						selectedConnections.id = connectionObject.name;
						selectedConnections.type = newType;

						editor.execute(new SetJunctionConnectionCommand(editor, object, metalData.connections));
						return;
					}
				} else {
					metalData.connections.splice(selectedIndex, 1);

					editor.execute(new SetJunctionConnectionCommand(editor, object, metalData.connections));
					return;
				}
			}
		}
	}).setMarginLeft('4px');
	junctionConnectionsTypeRow.add(junctionConnectionEditButton);

	container.add(junctionConnectionsTypeRow);

	// Junction connection table
	var junctionConnectionsTable = new UITable([
		{ "name": "id", "label": strings.getKey('sidebar/scenario/junction/connections/table/id'), "style": "min-width: 150px;" },
		{ "name": "type", "label": strings.getKey('sidebar/scenario/junction/connections/table/type'), "style": "min-width: 100px;" },
		{ "name": "blankcell", "label": "", "style": "min-width: 1px;" }
	]);
	junctionConnectionsTable.onDblClick(function () {

		var object = editor.selected;
		if (object !== null && object.type === 'Junction') {
			var metalData = JSON.parse(JSON.stringify(object.metalData));
			var selectedConnections = metalData.connections[junctionConnectionsTable.selectedIndex];
			if (selectedConnections !== undefined) {
				junctionConnectionsId.setValue(selectedConnections.objectId);
				junctionConnectionsType.setValue(selectedConnections.type);
			} else {
				junctionConnectionsId.setValue(Utils.MIN_INTEGER_STRING);
				junctionConnectionsType.setValue('successor');
			}
		}

	});

	container.add(junctionConnectionsTable);

	// Junction Connections Compute
	var junctionConnectionsComputeRow = new UIRow().setPaddingTop('15px');

	var junctionConnectionsComputeButton = new UIButton(strings.getKey('sidebar/scenario/junction/connections/button/compute')).onClick(function () {

		var object = editor.selected;

		if (object) {

			new SetJunctionLanesCommand(editor, object).execute();

		}

	});

	junctionConnectionsComputeRow.add(junctionConnectionsComputeButton);

	container.add(junctionConnectionsComputeRow);

	// visible

	var objectRefLineVisibleRow = new UIRow();
	var objectRefLineVisible = new UICheckbox().onChange(update);

	objectRefLineVisibleRow.add(new UIText(strings.getKey('sidebar/scenario/refline/visible')).setWidth('90px'));
	objectRefLineVisibleRow.add(objectRefLineVisible);

	// container.add(objectRefLineVisibleRow);

	// Road Add Lane

	var roadLaneAddRow = new UIRow().setPaddingTop('10px');

	var roadLaneAddButton = new UIButton(strings.getKey('sidebar/scenario/road/actions/add')).onClick(function () {

		var object = editor.selected;

		if (object === null) return;

		if (object.type === 'Road') {

			// editor.execute(new AddLaneObjectCommand(editor, object));
			editor.execute(new AddLaneCommand(editor, object));

		} else if (object.goType === 'GORoad') {

			editor.execute(new AddGOLaneCommand(editor, object));

		}

	});

	roadLaneAddRow.add(roadLaneAddButton);

	container.add(roadLaneAddRow);

	// Control Sprite
	var ctrlSpriteFieldset = new UIFieldset('Control Sprite');

	// Start position
	var ctrlSpriteStartRow = new UIRow();
	var ctrlSpriteStartS = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px')/*.onChange(update) */;
	var ctrlSpriteStartT = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	ctrlSpriteStartRow.add(new UIText('START').setWidth('75px'));
	ctrlSpriteStartRow.add(ctrlSpriteStartS, ctrlSpriteStartT);
	ctrlSpriteFieldset.add(ctrlSpriteStartRow);

	// End Start
	var ctrlSpriteEndRow = new UIRow();
	var ctrlSpriteEndS = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px')/*.onChange(update) */;
	var ctrlSpriteEndT = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	ctrlSpriteEndRow.add(new UIText('END').setWidth('75px'));
	ctrlSpriteEndRow.add(ctrlSpriteEndS, ctrlSpriteEndT);
	ctrlSpriteFieldset.add(ctrlSpriteEndRow);

	// Offset Type
	var ctrlSpriteOffsetRow = new UIRow();
	var ctrlSpriteOffsetType = new UISelect().setOptions({
		'none': 'None',
		'merge': 'Merge',
		'branch': 'branch'
	}).setWidth('150px').onChange(update);
	ctrlSpriteOffsetType.setValue('none');
	ctrlSpriteOffsetRow.add(new UIText('Offset Type').setWidth('75px'));
	ctrlSpriteOffsetRow.add(ctrlSpriteOffsetType);
	ctrlSpriteFieldset.add(ctrlSpriteOffsetRow);

	// visible
	var ctrlSpriteOffsetInwardRow = new UIRow();
	var ctrlSpriteOffsetInward = new UICheckbox().onChange(update);

	ctrlSpriteOffsetInwardRow.add(new UIText('Inward').setWidth('75px'));
	ctrlSpriteOffsetInwardRow.add(ctrlSpriteOffsetInward);
	ctrlSpriteFieldset.add(ctrlSpriteOffsetInwardRow);


	container.add(ctrlSpriteFieldset);
	//

	refreshRoadConnectionUI();
	refreshJunctionConnectionUI();


	//

	function update() {

		var scene = editor.scene;

		var object = editor.selected;

		if (object !== null) {

			var newPosition = new THREE.Vector3(objectPositionX.getValue(), objectPositionY.getValue(), objectPositionZ.getValue());

			var newRotation = new THREE.Euler(objectRotationX.getValue() * THREE.MathUtils.DEG2RAD, objectRotationY.getValue() * THREE.MathUtils.DEG2RAD, objectRotationZ.getValue() * THREE.MathUtils.DEG2RAD);

			if (object.type === 'RoadStructure') {

				var newbackground01Position = new THREE.Vector3(background01PositionX.getValue(), background01PositionY.getValue(), background01PositionZ.getValue());

				var newbackground01Rotation = new THREE.Euler(background01RotationX.getValue() * THREE.MathUtils.DEG2RAD, background01RotationY.getValue() * THREE.MathUtils.DEG2RAD, background01RotationZ.getValue() * THREE.MathUtils.DEG2RAD);

				var newbackground01Scale = new THREE.Vector3(background01Scale.getValue(), 1, background01Scale.getValue());

				signals.scenarioBackground01PositionChanged.dispatch(newbackground01Position);

				signals.scenarioBackground01RotationChanged.dispatch(newbackground01Rotation);

				signals.scenarioBackground01ScaleChanged.dispatch(newbackground01Scale);



				var newbackground02Position = new THREE.Vector3(background02PositionX.getValue(), background02PositionY.getValue(), background02PositionZ.getValue());

				var newbackground02Rotation = new THREE.Euler(background02RotationX.getValue() * THREE.MathUtils.DEG2RAD, background02RotationY.getValue() * THREE.MathUtils.DEG2RAD, background02RotationZ.getValue() * THREE.MathUtils.DEG2RAD);

				var newbackground02Scale = new THREE.Vector3(background02Scale.getValue(), 1, background02Scale.getValue());

				signals.scenarioBackground02PositionChanged.dispatch(newbackground02Position);

				signals.scenarioBackground02RotationChanged.dispatch(newbackground02Rotation);

				signals.scenarioBackground02ScaleChanged.dispatch(newbackground02Scale);


			} else {

				if (object.type === 'Road') {

					if (object.position.distanceTo(newPosition) >= 0.01) {
						editor.execute(new SetPositionCommand(editor, object, newPosition));
						return;
					}

					if (object.rotation.toVector3().distanceTo(newRotation.toVector3()) >= 0.01) {
						editor.execute(new SetRotationCommand(editor, object, newRotation));
					}


					var metalData = JSON.parse(JSON.stringify(object.metalData));
					if (metalData.priorityRoad !== roadPriority.getValue()) {
						metalData.priorityRoad = roadPriority.getValue();
					}

					if (metalData.length !== roadLength.getValue()) {
						metalData.length = roadLength.getValue();
					}

					if (metalData.height !== roadHeight.getValue()) {
						metalData.height = roadHeight.getValue();
					}

					if (metalData.ramp_angle !== roadRampAngle.getValue()) {
						metalData.ramp_angle = roadRampAngle.getValue();
					}

					if (metalData.type !== roadType.getValue()) {
						metalData.type = roadType.getValue();
					}

					if (metalData.radius !== roadRadius.getValue()) {
						metalData.radius = roadRadius.getValue();
					}

					if (JSON.stringify(object.metalData) != JSON.stringify(metalData)) {
						editor.execute(new SetMetalDataCommand(editor, object, 'metalData', metalData));
					}

				} else if (object.type === 'Lane') {

					var parent = object.parent;

					var metalData = JSON.parse(JSON.stringify(parent.metalData));

					for (var lane of metalData.lanes[object.lanePosition]) {

						if (lane.objectId === object.id) {

							if (lane.width !== laneWidth.getValue()) {
								lane.width = laneWidth.getValue();
							}

							if (lane.type !== laneType.getValue()) {
								lane.type = laneType.getValue();
							}

							if (JSON.stringify(parent.metalData) != JSON.stringify(metalData)) {
								editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
							} else {
								const ctrlSpriteObject01 = object.sprite01;
								const ctrlSpriteObject02 = object.sprite02;
								const ctrlSpriteEdge = object.spriteEdge;
								if (ctrlSpriteObject01.position2.y !== ctrlSpriteStartT.getValue()) {
									editor.execute(new SetControlSpritePosition2Command(editor, ctrlSpriteObject01, 'position2', new THREE.Vector2(ctrlSpriteStartS.getValue(), ctrlSpriteStartT.getValue())));
								}
								if (ctrlSpriteObject02.position2.y !== ctrlSpriteEndT.getValue()) {
									editor.execute(new SetControlSpritePosition2Command(editor, ctrlSpriteObject02, 'position2', new THREE.Vector2(ctrlSpriteEndS.getValue(), ctrlSpriteEndT.getValue())));
								}
								/*if (ctrlSpriteObject01.position2.x !== ctrlSpriteStartS.getValue()) {
									editor.execute(new SetValueCommand(editor, ctrlSpriteObject01.position2, 'x', ctrlSpriteStartS.getValue()));
									editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
								}
								if (ctrlSpriteObject01.position2.y !== ctrlSpriteStartT.getValue()) {
									editor.execute(new SetValueCommand(editor, ctrlSpriteObject01.position2, 'y', ctrlSpriteStartT.getValue()));
									editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
								}
								if (ctrlSpriteObject02.position2.x !== ctrlSpriteEndS.getValue()) {
									editor.execute(new SetValueCommand(editor, ctrlSpriteObject02.position2, 'x', ctrlSpriteEndS.getValue()));
									editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
								}
								if (ctrlSpriteObject02.position2.y !== ctrlSpriteEndT.getValue()) {
									editor.execute(new SetValueCommand(editor, ctrlSpriteObject02.position2, 'y', ctrlSpriteEndT.getValue()));
									editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
								}*/
								if (ctrlSpriteEdge.offsetType !== ctrlSpriteOffsetType.getValue()) {

									// editor.execute(new SetValueCommand(editor, ctrlSpriteEdge, 'offsetType', ctrlSpriteOffsetType.getValue()));
									// editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
									editor.execute(new MultiCmdsCommand(editor, [new SetOffsetTypeCommand(editor, ctrlSpriteEdge, 'offsetType', ctrlSpriteOffsetType.getValue()), new SetMetalDataCommand(editor, parent, 'metalData', metalData)]));
								} else if (ctrlSpriteEdge.offsetInward !== ctrlSpriteOffsetInward.getValue()) {
									// editor.execute(new SetValueCommand(editor, ctrlSpriteEdge, 'offsetInward', ctrlSpriteOffsetInward.getValue()));
									// editor.execute(new SetMetalDataCommand(editor, parent, 'metalData', metalData));
									editor.execute(new MultiCmdsCommand(editor, [new SetValueCommand(editor, ctrlSpriteEdge, 'offsetInward', ctrlSpriteOffsetInward.getValue()), new SetMetalDataCommand(editor, parent, 'metalData', metalData)]));
								}

							}

							break;
						}
					}
				} else if (object.type === 'Junction') {
					var metalData = object.metalData;

					if (metalData.edgeType !== junctionEdgeType.getValue()) {
						editor.execute(new SetJunctionEdgeTypeCommand(editor, object, junctionEdgeType.getValue()));
					}

				}
				if (object.goType === 'GORoad') {
					//TODO
				} else if (object.goType === 'GOLane') {
					if (object.laneType !== goLaneType.getValue()) {
						editor.execute(new SetGOLanePropertyCommand(editor, object, 'laneType', goLaneType.getValue()));
					}
					if (object.width !== goLaneWidth.getValue()) {
						editor.execute(new SetGOLanePropertyCommand(editor, object, 'width', goLaneWidth.getValue()));
					}
				}
			}
		}
	}

	//

	function updateRows(object) {

		var roadStructureProperties = {
			'background00Fieldset': background00Fieldset,
			'background01Fieldset': background01Fieldset,
			'background02Fieldset': background02Fieldset,
			'objectDescriptionRow': objectDescriptionRow
		};

		var goRoadProperties = {
			'objectPosition': objectPositionRow,
			'objectRotation': objectRotationRow,
			'length': goRoadLengthRow,
			'laneAdd': roadLaneAddRow
		};

		var goLaneProperties = {
			'type': goLaneTypeRow,
			'width': goLaneWidthRow
		};

		var roadProperties = {
			'objectPosition': objectPositionRow,
			'objectRotation': objectRotationRow,
			'roadPriority': roadPriorityRow,
			'connection': roadConnectionRow,
			'reverse': roadReverseRow,
			'length': roadLengthRow,
			'height': roadHeightRow,
			'ramp_angle': roadRampAngleRow,
			'type': roadTypeRow,
			'radius': roadRadiusRow,
			'refLine': objectRefLineVisibleRow,
			'laneAdd': roadLaneAddRow
		};

		var laneProperties = {
			'position': lanePositionRow,
			'width': laneWidthRow,
			'type': laneTypeRow,
			'refLine': objectRefLineVisibleRow,
			'ctrlSprite': ctrlSpriteFieldset
		};

		var junctionProperties = {
			'junctionEdgeType': junctionEdgeTypeRow,
			'connectionsId': junctionConnectionsIdRow,
			'connectionsType': junctionConnectionsTypeRow,
			'ConnectionsTable': junctionConnectionsTable,
			'connectionsCompute': junctionConnectionsComputeRow
		};

		var junctionLaneProperties = {
			'refLine': objectRefLineVisibleRow
		};
		if (object.type === 'RoadStructure') {
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('');
			}
		} else if (object.goType === 'GORoad') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('');
			}
		} else if (object.goType === 'GOLane') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('');
			}
		} else if (object.type === 'Road') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('');
			}
		} else if (object.type === 'Lane') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('');
			}
		} else if (object.type === 'Junction') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('');
			}
		} else if (object.type === 'JunctionLane') {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('');
			}
		} else {
			for (var property in roadStructureProperties) {
				roadStructureProperties[property].setDisplay('none');
			}
			for (var property in junctionLaneProperties) {
				junctionLaneProperties[property].setDisplay('none');
			}
			for (var property in junctionProperties) {
				junctionProperties[property].setDisplay('none');
			}
			for (var property in roadProperties) {
				roadProperties[property].setDisplay('none');
			}
			for (var property in laneProperties) {
				laneProperties[property].setDisplay('none');
			}
			for (var property in goRoadProperties) {
				goRoadProperties[property].setDisplay('none');
			}
			for (var property in goLaneProperties) {
				goLaneProperties[property].setDisplay('none');
			}
		}

	}

	// events
	signals.editorCleared.add(function () {
		background01Texture.setValue(null);
		background02Texture.setValue(null);
	});

	signals.editorCleared.add(refreshRoadConnectionUI);
	signals.editorCleared.add(refreshJunctionConnectionUI);


	signals.sceneGraphChanged.add(refreshRoadConnectionUI);
	signals.sceneGraphChanged.add(refreshJunctionConnectionUI);

	signals.objectChanged.add(refreshRoadConnectionUI);
	signals.objectChanged.add(refreshJunctionConnectionUI);

	signals.objectSelected.add(function (object) {

		if (object !== null && (object.type === 'RoadStructure' || object.goType === 'GORoad' || object.goType === 'GOLane' || object.type === 'Road' || object.type === 'Lane' || object.type === 'Junction' || object.type === 'JunctionLane')) {
			container.setDisplay('block');
			updateRows(object);
			updateUI(object);
		} else if (object !== null && object.parent !== null && object.type === 'ControlSprite') {
			container.setDisplay('block');
			updateRows(object.parent);
			updateUI(object.parent);
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

	signals.gardenRoadStructureChanged.add(function () {

		var scene = editor.scene;

		for (var child of scene.children) {

			if (child.type === 'Car') {

				if (child.name === 'EgoCar') {
					var egoCar = child.metalData;
					var egoCarRouteData = editor.computeRouteCurve4('EgoCar', '#' + new THREE.Color(child.meshColor).getHexString(), egoCar.routes[0]);
					egoCar.routeData = egoCarRouteData;
					child.setFromMetalData(egoCar);
				} else {
					var otherCar = child.metalData;
					var otherCarRouteData = editor.computeRouteCurve4(otherCar.id, '#' + new THREE.Color(child.meshColor).getHexString(), otherCar.routes[0]);
					otherCar.routeData = otherCarRouteData;
					child.setFromMetalData(otherCar);
				}

			} else if (child.type === 'Route') {
				var routeData = child.metalData;
				var routeRouteData = editor.computeRouteCurve4(routeData.id, '#' + new THREE.Color(child.color).getHexString(), routeData.routes[0]);
				routeData.routeData = routeRouteData;
				child.setFromMetalData(routeData);
			}

		}

	});

	signals.scenarioBackground01PositionChanged.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background01PositionX.setValue(editor.backgroundPlane01.position.x);
		background01PositionY.setValue(editor.backgroundPlane01.position.y);
		background01PositionZ.setValue(editor.backgroundPlane01.position.z);

	});

	signals.scenarioBackground01RotationChanged.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background01RotationX.setValue(editor.backgroundPlane01.rotation.x * THREE.MathUtils.RAD2DEG);
		background01RotationY.setValue(editor.backgroundPlane01.rotation.y * THREE.MathUtils.RAD2DEG);
		background01RotationZ.setValue(editor.backgroundPlane01.rotation.z * THREE.MathUtils.RAD2DEG);

	});

	signals.scenarioBackground01ScaleChanged.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background01Scale.setValue(editor.backgroundPlane01.scale.x);

	});

	signals.scenarioBackground01Changed.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background01Enabled.setValue(editor.backgroundPlane01.userData.is2DVisible || false);

		if (editor.backgroundPlane01.material.map !== undefined && editor.backgroundPlane01.material.map !== null) {
			background01Texture.setValue(editor.backgroundPlane01.material.map);
		}

	});

	signals.scenarioBackground02PositionChanged.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background02PositionX.setValue(editor.backgroundPlane02.position.x);
		background02PositionY.setValue(editor.backgroundPlane02.position.y);
		background02PositionZ.setValue(editor.backgroundPlane02.position.z);

	});

	signals.scenarioBackground02RotationChanged.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background02RotationX.setValue(editor.backgroundPlane02.rotation.x * THREE.MathUtils.RAD2DEG);
		background02RotationY.setValue(editor.backgroundPlane02.rotation.y * THREE.MathUtils.RAD2DEG);
		background02RotationZ.setValue(editor.backgroundPlane02.rotation.z * THREE.MathUtils.RAD2DEG);

	});

	signals.scenarioBackground02ScaleChanged.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background02Scale.setValue(editor.backgroundPlane02.scale.x);

	});

	signals.scenarioBackground02Changed.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background02Enabled.setValue(editor.backgroundPlane02.userData.is2DVisible || false);

		if (editor.backgroundPlane02.material.map !== undefined && editor.backgroundPlane02.material.map !== null) {
			background02Texture.setValue(editor.backgroundPlane02.material.map);
		}

	});

	signals.scenarioBackground00Changed.add(function () {

		var object = editor.selected;

		if (object === null || object.type !== 'RoadStructure') return;

		background00Top.setValue(editor.backgroundMap.boundary.n);
		background00Right.setValue(editor.backgroundMap.boundary.e);
		background00Bottom.setValue(editor.backgroundMap.boundary.s);
		background00Left.setValue(editor.backgroundMap.boundary.w);
		background00Enabled.setValue(editor.backgroundMap.visible || false);

	});

	function updateUI(object) {

		var scene = editor.scene;

		refreshRoadTypeUI();
		refreshLaneObjectUI();
		refreshRoadConnectionUI();
		refreshJunctionConnectionUI();

		objectType.setValue(object.type);
		objectID.setValue(object.name);

		if (object.type === 'RoadStructure') {
			objectDescription.setValue(object.description);

			background00Top.setValue(editor.backgroundMap.boundary.n);
			background00Right.setValue(editor.backgroundMap.boundary.e);
			background00Bottom.setValue(editor.backgroundMap.boundary.s);
			background00Left.setValue(editor.backgroundMap.boundary.w);
			background00Enabled.setValue(editor.backgroundMap.visible || false);


			background01PositionX.setValue(editor.backgroundPlane01.position.x);
			background01PositionY.setValue(editor.backgroundPlane01.position.y);
			background01PositionZ.setValue(editor.backgroundPlane01.position.z);

			background01RotationX.setValue(editor.backgroundPlane01.rotation.x * THREE.MathUtils.RAD2DEG);
			background01RotationY.setValue(editor.backgroundPlane01.rotation.y * THREE.MathUtils.RAD2DEG);
			background01RotationZ.setValue(editor.backgroundPlane01.rotation.z * THREE.MathUtils.RAD2DEG);

			background01Scale.setValue(editor.backgroundPlane01.scale.x);

			background01Enabled.setValue(editor.backgroundPlane01.userData.is2DVisible || false);

			if (editor.backgroundPlane01.material.map !== undefined && editor.backgroundPlane01.material.map !== null) {
				background01Texture.setValue(editor.backgroundPlane01.material.map);
			}



			background02PositionX.setValue(editor.backgroundPlane02.position.x);
			background02PositionY.setValue(editor.backgroundPlane02.position.y);
			background02PositionZ.setValue(editor.backgroundPlane02.position.z);

			background02RotationX.setValue(editor.backgroundPlane02.rotation.x * THREE.MathUtils.RAD2DEG);
			background02RotationY.setValue(editor.backgroundPlane02.rotation.y * THREE.MathUtils.RAD2DEG);
			background02RotationZ.setValue(editor.backgroundPlane02.rotation.z * THREE.MathUtils.RAD2DEG);

			background02Scale.setValue(editor.backgroundPlane02.scale.x);

			background02Enabled.setValue(editor.backgroundPlane02.userData.is2DVisible || false);


			if (editor.backgroundPlane02.material.map !== undefined && editor.backgroundPlane02.material.map !== null) {
				background02Texture.setValue(editor.backgroundPlane02.material.map);
			}

		} else {
			objectPositionX.setValue(object.position.x);
			objectPositionY.setValue(object.position.y);
			objectPositionZ.setValue(object.position.z);

			objectRotationX.setValue(object.rotation.x * THREE.MathUtils.RAD2DEG);
			objectRotationY.setValue(object.rotation.y * THREE.MathUtils.RAD2DEG);
			objectRotationZ.setValue(object.rotation.z * THREE.MathUtils.RAD2DEG);

			/*if (object.refLine !== undefined) {

				objectRefLineVisible.setValue(object.refLine.visible);

			}*/
		}

		var metalData = object.metalData || {};

		if (object.type === 'Road') {

			roadPriority.setValue(metalData.priorityRoad);

			for (var key in roadConnectionOptions) {
				if (metalData.connectionId === key) {
					const connectionObject = scene.getObjectById(+key);
					if (connectionObject === undefined) {
						metalData.connection.road = '';
						metalData.connection.lane = '';
					} else if (connectionObject.type === 'Road') {
						metalData.connection.road = connectionObject.name;
						metalData.connection.lane = '';
					} else if (connectionObject.type === 'Lane') {
						metalData.connection.road = connectionObject.parent.name;
						metalData.connection.lane = connectionObject.name;
					}
					roadConnection.setValue(key);
				}
			}

			if (metalData.connectionId === undefined || metalData.connectionId === null || metalData.connectionId === '' || metalData.connectionId === Utils.MIN_INTEGER_STRING) {
				roadConnection.setValue(Utils.MIN_INTEGER_STRING);
			}

			roadReverse.setValue(metalData.reverse || false);

			roadLength.setValue(metalData.length || 0);
			roadHeight.setValue(metalData.height || 0.0);
			roadRampAngle.setValue(metalData.ramp_angle || 0.0);
			roadType.setValue(metalData.type || 'straight');
			roadRadius.setValue(metalData.radius || 0.0);
		} else if (object.type === 'Lane') {

			lanePosition.setValue(metalData.position || 'left');
			laneWidth.setValue(metalData.width || 0.0);
			laneType.setValue(metalData.type || 'driving');

			ctrlSpriteStartS.setValue(object.sprite01.position2.x);
			ctrlSpriteStartT.setValue(object.sprite01.position2.y);
			ctrlSpriteEndS.setValue(object.sprite02.position2.x);
			ctrlSpriteEndT.setValue(object.sprite02.position2.y);
			ctrlSpriteOffsetType.setValue(object.spriteEdge.offsetType);
			ctrlSpriteOffsetInward.setValue(object.spriteEdge.offsetInward)

		} else if (object.type === 'Junction') {
			junctionEdgeType.setValue(object.metalData.edgeType || 'typea');
			junctionConnectionsTable.setItems(Object.values(object.metalData.connections || []));
		}

		if (object.goType === 'GORoad') {
			goRoadLength.setValue(object.length);
		} else if (object.goType === 'GOLane') {
			goLaneType.setValue(object.laneType);
			goLaneWidth.setValue(object.width);
		}

	}

	function refreshRoadTypeUI() {

		var object = editor.selected;

		var type = roadType.getValue();

		roadRadiusRow.setDisplay(object.type !== 'Road' || type === 'straight' || type === 'cubic_left' || type === 'cubic_right' ? 'none' : '');

	}

	function refreshLaneObjectUI() {
		var currentObject = editor.selected;
		if (currentObject !== null && currentObject.type === 'Lane') {
			objectID.setDisabled(true);
			if (currentObject.lanePosition === 'center') {
				lanePosition.setDisplay('none');
				lanePositionLabel.setDisplay('');
			} else {
				lanePositionLabel.setDisplay('none');
				lanePosition.setDisplay('');
			}
		} else {
			objectID.setDisabled(false);
		}
	}

	function refreshRoadConnectionUI() {

		var currentObject = editor.selected;

		if (currentObject !== null && currentObject.type !== 'Road') {

			objectPositionX.setDisabled(false);
			objectPositionY.setDisabled(false);
			objectPositionZ.setDisabled(false);

			objectRotationX.setDisabled(false);
			objectRotationY.setDisabled(false);
			objectRotationZ.setDisabled(false);

		} else {

			var currentSelected = roadConnection.getValue();

			roadConnectionOptions = {};

			(function addObjects(objects) {

				for (var i = 0, l = objects.length; i < l; i++) {

					var object = objects[i];

					if (currentObject === object) continue;

					if (object.type === 'Road') {

						roadConnectionOptions[object.id] = object.name;

						const laneChildren = object.children.filter(child => child.type === 'Lane');
						for (let child of laneChildren) {
							roadConnectionOptions[child.id] = `${object.name}_${child.name}`;
						}

						//addObjects(object.children);
					}

				}

			})(editor.scene.children);

			roadConnectionOptions[Utils.MIN_INTEGER_STRING] = '';

			roadConnection.setOptions(roadConnectionOptions);


			for (var key in roadConnectionOptions) {

				if (key === Utils.MIN_INTEGER_STRING) continue;

				if (currentSelected === key) {

					roadConnection.setValue(currentSelected);

					roadReverse.setDisabled(false);

					objectPositionX.setDisabled(true);
					objectPositionY.setDisabled(true);
					objectPositionZ.setDisabled(true);

					objectRotationX.setDisabled(true);
					objectRotationY.setDisabled(true);
					objectRotationZ.setDisabled(true);

					return;

				}

			}

			roadConnection.setValue(Utils.MIN_INTEGER_STRING);
			roadReverse.setValue(false);
			roadReverse.setDisabled(true);

			objectPositionX.setDisabled(false);
			objectPositionY.setDisabled(false);
			objectPositionZ.setDisabled(false);

			objectRotationX.setDisabled(false);
			objectRotationY.setDisabled(false);
			objectRotationZ.setDisabled(false);

		}
	}

	function updateConnection() {

		var object = editor.selected;

		if (object !== null) {

			var metalData = object.metalData || {};

			if (`${metalData.connection.road}_${metalData.connection.lane}` !== roadConnectionOptions[roadConnection.getValue()] || metalData.reverse !== roadReverse.getValue()) {
				editor.execute(new SetConnectionCommand(editor, editor.selected, roadConnection.getValue(), roadReverse.getValue()));
			}

		}

	}

	function refreshJunctionConnectionUI() {

		var currentObject = editor.selected;

		var currentSelected = junctionConnectionsId.getValue();

		if (currentObject === null || currentObject.type !== 'Junction') {

			return;

		}

		junctionConnectionsIdOptions = {};

		(function addObjects(objects) {

			for (var i = 0, l = objects.length; i < l; i++) {

				var object = objects[i];

				if (object.type === 'Road') {

					junctionConnectionsIdOptions[object.id] = object.name;
				}

			}

		})(editor.scene.children);

		junctionConnectionsIdOptions[Utils.MIN_INTEGER_STRING] = '';

		junctionConnectionsId.setOptions(junctionConnectionsIdOptions);

		junctionConnectionsId.setValue(Utils.MIN_INTEGER_STRING);

		junctionConnectionsType.setValue('successor');

		updateJunctionConnection();
	}

	function updateJunctionConnection() {

		var object = editor.selected;

		if (object !== null && object.type === 'Junction') {

			var metalData = JSON.parse(JSON.stringify(object.metalData));

			for (var connection of metalData.connections) {
				if (connection.objectId !== Utils.MIN_INTEGER_STRING && junctionConnectionsIdOptions[connection.objectId]) {
					connection.id = junctionConnectionsIdOptions[connection.objectId];
				} else {
					connection.objectId = Utils.MIN_INTEGER_STRING;
				}
			}

			if (JSON.stringify(object.metalData) != JSON.stringify(metalData)) {
				editor.execute(new SetMetalDataValueCommand(editor, editor.selected, 'connections', metalData.connections));
			}

		}

	}

	return container;

};

export { SidebarRoadObject };
