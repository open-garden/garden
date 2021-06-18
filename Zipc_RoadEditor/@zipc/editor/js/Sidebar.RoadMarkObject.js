/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { UIPanel, UIRow, UIInput, UISelect, UIColor, UIText, UINumber, UIButton, UIInteger, UICheckbox } from '../../../vendors/js/libs/ui.js';

import { SetRoadMarkValueCommand } from './commands/SetRoadMarkValueCommand.js';
import { SetRoadMarkSignValueCommand } from './commands/SetRoadMarkSignValueCommand.js';
import { SetRoadMarkCrossWalkValueCommand } from './commands/SetRoadMarkCrossWalkValueCommand.js';
import { SetTrafficLightValueCommand } from './commands/SetTrafficLightValueCommand.js';
import { AddLineSegmentObjectCommand } from './commands/AddLineSegmentObjectCommand.js';
import { SetRoadMarkTurningMarkValueCommand } from './commands/SetRoadMarkTurningMarkValueCommand.js';



var SidebarRoadMarkObject = function (editor) {

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

	var objectIdRow = new UIRow();
	var objectId = new UIInput().setWidth('150px').setFontSize('12px').onChange(function () {
		if (editor.selected.type === 'RoadStructure') {
			if (objectId.isCorrectFormat()) {
				editor.execute(new SetMetalDataValueCommand(editor, editor.selected, 'id', objectId.getValue()));
			} else {
				alert('Inserted data for ID is invalid.\nStart with only Alphabet.\nAnd alphabet, number and \'.\' only are allowed.');
				objectId.setValue(editor.selected.gid);
			}
		} else {
			editor.execute(new SetRoadMarkValueCommand(editor, editor.selected, 'name', objectId.getValue()));
		}
	});

	objectIdRow.add(new UIText(strings.getKey('sidebar/scenario/id')).setWidth('90px'));
	objectIdRow.add(objectId);
	container.add(objectIdRow);

	/*
	 * RoadMark Properties
	 */
	// Line Type
	var lineModeRow = new UIRow();
	var lineMode = new UISelect().setOptions({
		'none': 'none',
		'solid': 'solid',
		'broken': 'broken',
		'broken2-2': 'broken2-2',
		'center5-5': 'center 5-5',
		'center8-12': 'center 8-12'
	}).setWidth('150px').onChange(update);
	lineMode.setValue('solid');
	lineModeRow.add(new UIText(strings.getKey('sidebar/scenario/line/type')).setWidth('90px'));
	lineModeRow.add(lineMode);
	container.add(lineModeRow);

	// Line Position
	var linePositionRow = new UIRow();
	var linePosition = new UISelect().setOptions({
		'left': 'left',
		'right': 'right'
	}).setWidth('150px').onChange(update);
	linePosition.setValue('right');
	linePositionRow.add(new UIText(strings.getKey('sidebar/scenario/line/position')).setWidth('90px'));
	linePositionRow.add(linePosition);
	container.add(linePositionRow);

	// Line Width
	var lineWidthRow = new UIRow();
	var lineWidth = new UINumber(0.15).setPrecision(2).setWidth('50px').setRange(0, 1).onChange(update);
	lineWidthRow.add(new UIText(strings.getKey('sidebar/scenario/line/width')).setWidth('90px'));
	lineWidthRow.add(lineWidth);
	container.add(lineWidthRow);

	// Line Offset
	var lineOffsetRow = new UIRow();
	var lineOffset = new UINumber(0.0).setPrecision(2).setWidth('50px').setRange(-10.0, 10.0).onChange(update);
	lineOffsetRow.add(new UIText(strings.getKey('sidebar/scenario/line/offset')).setWidth('90px'));
	lineOffsetRow.add(lineOffset);
	container.add(lineOffsetRow);

	// Line Color
	var lineColorRow = new UIRow();
	var lineColor = new UIColor().onChange(update);
	lineColorRow.add(new UIText(strings.getKey('sidebar/scenario/line/color')).setWidth('90px'));
	lineColorRow.add(lineColor);
	container.add(lineColorRow);

	// Line Add Button
	var lineAddRow = new UIRow();
	var lineAddButton = new UIButton(strings.getKey('sidebar/scenario/line/actions/add')).onClick(function () {
		const object = editor.selected;
		if (object !== null && object.type === 'RoadMark.Line') {
			editor.execute(new AddLineSegmentObjectCommand(editor, editor.selected));
		}
	});
	lineAddRow.add(lineAddButton);
	container.add(lineAddRow);

	// LineSegment Lane
	let laneOptions = {};
	laneOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	var lineSegmentLaneRow = new UIRow();
	var lineSegmentLane = new UISelect().setOptions(laneOptions).setWidth('150px').onChange(update);
	lineSegmentLane.setValue(`${Number.MIN_SAFE_INTEGER}`);
	lineSegmentLaneRow.add(new UIText(strings.getKey('sidebar/scenario/linesegment/lane')).setWidth('90px'));
	lineSegmentLaneRow.add(lineSegmentLane);
	container.add(lineSegmentLaneRow);

	// LineSegment Start
	var lineSegmentStartRow = new UIRow();
	var lineSegmentStart = new UINumber(-1.0).setPrecision(1).setWidth('50px').onChange(update);
	lineSegmentStartRow.add(new UIText(strings.getKey('sidebar/scenario/linesegment/start')).setWidth('90px'));
	lineSegmentStartRow.add(lineSegmentStart);
	container.add(lineSegmentStartRow);

	// LineSegment End
	var lineSegmentEndRow = new UIRow();
	var lineSegmentEnd = new UINumber(-1.0).setPrecision(1).setWidth('50px').onChange(update);
	lineSegmentEndRow.add(new UIText(strings.getKey('sidebar/scenario/linesegment/end')).setWidth('90px'));
	lineSegmentEndRow.add(lineSegmentEnd);
	container.add(lineSegmentEndRow);

	/*
	 * RoadMark Sign
	 */
	// Sign Lane
	let drivingLaneOptions = {};
	drivingLaneOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	var signLaneRow = new UIRow();
	var signLane = new UISelect().setOptions(drivingLaneOptions).setWidth('150px').onChange(update);
	signLane.setValue(`${Number.MIN_SAFE_INTEGER}`);
	signLaneRow.add(new UIText(strings.getKey('sidebar/scenario/sign/lane')).setWidth('90px'));
	signLaneRow.add(signLane);
	container.add(signLaneRow);

	let roadOptions = {};
	roadOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	var signRoadRow = new UIRow();
	var signRoad = new UISelect().setOptions(roadOptions).setWidth('150px').onChange(update);
	signRoad.setValue(`${Number.MIN_SAFE_INTEGER}`);
	signRoadRow.add(new UIText(strings.getKey('sidebar/scenario/sign/road')).setWidth('90px'));
	signRoadRow.add(signRoad);
	container.add(signRoadRow);

	// Sign Type
	var signModeRow = new UIRow();
	var signMode = new UISelect().setOptions({
		'arrow_straight': 'straight',
		'arrow_left': 'left',
		'arrow_right': 'right',
		'arrow_straight_left': 'straight left',
		'arrow_straight_right': 'straight right',
		'stop_line': 'stop line',
		'diamond_mark': 'diamond mark',
		'stop_mark': 'stop_mark'
	}).setWidth('150px').onChange(update);
	signMode.setValue('arrow_straight');
	signModeRow.add(new UIText(strings.getKey('sidebar/scenario/sign/type')).setWidth('90px'));
	signModeRow.add(signMode);
	container.add(signModeRow);

	// Sign Position
	var signPositionRow = new UIRow();
	var signPositionS = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	var signPositionT = new UINumber().setRange(-1.0, 1.0).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	signPositionRow.add(new UIText(strings.getKey('sidebar/scenario/sign/position')).setWidth('90px'));
	signPositionRow.add(signPositionS, signPositionT);
	container.add(signPositionRow);

	// Sign Rotation
	var signRotationRow = new UIRow();
	var signRotation = new UIInteger(0).setWidth('50px').setRange(-180, 180).onChange(update);
	signRotationRow.add(new UIText(strings.getKey('sidebar/scenario/sign/rotation')).setWidth('90px'));
	signRotationRow.add(signRotation);
	container.add(signRotationRow);

	// Sign Scale
	var signScaleRow = new UIRow();
	var signScale = new UINumber(0.0).setPrecision(2).setWidth('50px').setRange(0.0, 3.0).onChange(update);
	signScaleRow.add(new UIText(strings.getKey('sidebar/scenario/sign/scale')).setWidth('90px'));
	signScaleRow.add(signScale);
	container.add(signScaleRow);

	// Sign Length
	var signLengthRow = new UIRow();
	var signLength = new UINumber(0.0).setPrecision(2).setWidth('50px').setRange(0.0, 30.0).onChange(update);
	signLengthRow.add(new UIText(strings.getKey('sidebar/scenario/sign/length')).setWidth('90px'));
	signLengthRow.add(signLength);
	container.add(signLengthRow);

	// Sign Color
	var signColorRow = new UIRow();
	var signColor = new UIColor().onChange(update);
	signColorRow.add(new UIText(strings.getKey('sidebar/scenario/sign/color')).setWidth('90px'));
	signColorRow.add(signColor);
	container.add(signColorRow);

	/*
	 * RoadMark Closswalk
	 */
	// Crosswalk road
	let crosswalkRoadOptions = {};
	crosswalkRoadOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	var crosswalkRoadRow = new UIRow();
	var crosswalkRoad = new UISelect().setOptions(crosswalkRoadOptions).setWidth('150px').onChange(update);
	crosswalkRoad.setValue(`${Number.MIN_SAFE_INTEGER}`);
	crosswalkRoadRow.add(new UIText(strings.getKey('sidebar/scenario/crosswalk/road')).setWidth('90px'));
	crosswalkRoadRow.add(crosswalkRoad);
	container.add(crosswalkRoadRow);

	// Crosswalk Position
	var crosswalkPositionRow = new UIRow();
	var crosswalkPositionS = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	var crosswalkPositionT = new UINumber().setRange(-1.0, 1.0).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	crosswalkPositionRow.add(new UIText(strings.getKey('sidebar/scenario/crosswalk/position')).setWidth('90px'));
	crosswalkPositionRow.add(crosswalkPositionS, crosswalkPositionT);
	container.add(crosswalkPositionRow);

	// CrossWalk Width
	var crosswalkWidthRow = new UIRow();
	var crosswalkWidth = new UINumber(0.0).setPrecision(2).setWidth('50px').onChange(update);
	crosswalkWidthRow.add(new UIText(strings.getKey('sidebar/scenario/crosswalk/width')).setWidth('90px'));
	crosswalkWidthRow.add(crosswalkWidth);
	container.add(crosswalkWidthRow);

	// CrossWalk Skew
	var crosswalkSkewRow = new UIRow();
	var crosswalkSkew = new UIInteger(0).setWidth('50px').setRange(-50, 50).onChange(update);
	crosswalkSkewRow.add(new UIText(strings.getKey('sidebar/scenario/crosswalk/skew')).setWidth('90px'));
	crosswalkSkewRow.add(crosswalkSkew);
	container.add(crosswalkSkewRow);

	// CrossWalk Color
	var crosswalkColorRow = new UIRow();
	var crosswalkColor = new UIColor().onChange(update);
	crosswalkColorRow.add(new UIText(strings.getKey('sidebar/scenario/crosswalk/color')).setWidth('90px'));
	crosswalkColorRow.add(crosswalkColor);
	container.add(crosswalkColorRow);

	/*
	 * Traffic Light
	 */
	// Traffic Light road
	let trafficlightRoadOptions = {};
	trafficlightRoadOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	var trafficlightRoadRow = new UIRow();
	var trafficlightRoad = new UISelect().setOptions(trafficlightRoadOptions).setWidth('150px').onChange(update);
	trafficlightRoad.setValue(`${Number.MIN_SAFE_INTEGER}`);
	trafficlightRoadRow.add(new UIText(strings.getKey('sidebar/scenario/trafficlight/road')).setWidth('90px'));
	trafficlightRoadRow.add(trafficlightRoad);
	container.add(trafficlightRoadRow);

	// trafficlight Position
	var trafficlightPositionRow = new UIRow();
	var trafficlightPositionS = new UINumber().setRange(0, 1).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	var trafficlightPositionT = new UINumber().setRange(-1.0, 1.0).setPrecision(3).setStep(0.1).setWidth('50px').onChange(update);
	trafficlightPositionRow.add(new UIText(strings.getKey('sidebar/scenario/trafficlight/position')).setWidth('90px'));
	trafficlightPositionRow.add(trafficlightPositionS, trafficlightPositionT);
	container.add(trafficlightPositionRow);

	// trafficlight orientation
	var trafficlightOrientationRow = new UIRow();
	var trafficlightOrientation = new UICheckbox().onChange(update);
	trafficlightOrientationRow.add(new UIText(strings.getKey('sidebar/scenario/trafficlight/orientation')).setWidth('90px'));
	trafficlightOrientationRow.add(trafficlightOrientation);
	container.add(trafficlightOrientationRow);

	// trafficlight Roatation
	var trafficlightRotationRow = new UIRow();
	var trafficlightRotation = new UIInteger(0).setWidth('50px').setRange(-50, 50).onChange(update);
	trafficlightRotationRow.add(new UIText(strings.getKey('sidebar/scenario/trafficlight/rotation')).setWidth('90px'));
	trafficlightRotationRow.add(trafficlightRotation);
	container.add(trafficlightRotationRow);

	/**
	 * TurningMark
	 */
	// TurningMark junction
	let turningmarkJunctionOptions = {};
	turningmarkJunctionOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	var turningmarkJunctionRow = new UIRow();
	var turningmarkJunction = new UISelect().setOptions(turningmarkJunctionOptions).setWidth('150px').onChange(update);
	turningmarkJunction.setValue(`${Number.MIN_SAFE_INTEGER}`);
	turningmarkJunctionRow.add(new UIText(strings.getKey('sidebar/scenario/turningmark/junction')).setWidth('90px'));
	turningmarkJunctionRow.add(turningmarkJunction);
	container.add(turningmarkJunctionRow);

	// TurningMark Type
	var turningmarkModeRow = new UIRow();
	var turningmarkMode = new UISelect().setOptions({
		'turning_mark': 'turning_mark',
		'half_turning_mark': 'half_turning_mark'
	}).setWidth('150px').onChange(update);
	turningmarkMode.setValue('turning_mark');
	turningmarkModeRow.add(new UIText(strings.getKey('sidebar/scenario/turningmark/type')).setWidth('90px'));
	turningmarkModeRow.add(turningmarkMode);
	container.add(turningmarkModeRow);

	// TurningMark Scale
	var turningmarkScaleRow = new UIRow();
	var turningmarkScale = new UINumber(0.0).setPrecision(2).setWidth('50px').onChange(update);
	turningmarkScaleRow.add(new UIText(strings.getKey('sidebar/scenario/turningmark/scale')).setWidth('90px'));
	turningmarkScaleRow.add(turningmarkScale);
	container.add(turningmarkScaleRow);

	// TurningMark Rotation
	var turningmarkRotationRow = new UIRow();
	var turningmarkRotation = new UIInteger(0).setWidth('50px').setRange(-50, 50).onChange(update);
	turningmarkRotationRow.add(new UIText(strings.getKey('sidebar/scenario/turningmark/rotation')).setWidth('90px'));
	turningmarkRotationRow.add(turningmarkRotation);
	container.add(turningmarkRotationRow);

	// TurningMark Color
	var turningmarkColorRow = new UIRow();
	var turningmarkColor = new UIColor().onChange(update);
	turningmarkColorRow.add(new UIText(strings.getKey('sidebar/scenario/turningmark/color')).setWidth('90px'));
	turningmarkColorRow.add(turningmarkColor);
	container.add(turningmarkColorRow);

	updateLaneUI();
	//

	function update() {

		var scene = editor.scene;

		var object = editor.selected;

		if (object !== null) {

			if (object.type === 'RoadStructure') {

				// TODO

			} else {
				if (object.type === 'RoadMark.Line') {
					if (object.mode !== lineMode.getValue()) {
						editor.execute(new SetRoadMarkValueCommand(editor, object, 'mode', lineMode.getValue()));
					}

					const parameters = JSON.parse(JSON.stringify(object.parameters));
					if (parameters.position !== linePosition.getValue()) {
						parameters.position = linePosition.getValue()
					}
					if (parameters.width !== lineWidth.getValue()) {
						parameters.width = lineWidth.getValue()
					}
					if (parameters.offset !== lineOffset.getValue()) {
						parameters.offset = lineOffset.getValue()
					}
					if (parameters.color !== lineColor.getHexValue()) {
						parameters.color = lineColor.getHexValue()
					}

					if (JSON.stringify(object.parameters) != JSON.stringify(parameters)) {
						editor.execute(new SetRoadMarkValueCommand(editor, object, 'parameters', parameters));
					}
				} else if (object.type === 'RoadMark.LineSegment') {
					if (object.lane[0] !== +lineSegmentLane.getValue()) {
						var newObject = editor.scene.getObjectById(+lineSegmentLane.getValue(), true);
						if (newObject !== undefined && newObject !== null && newObject.type === 'Lane') {
							editor.execute(new SetRoadMarkValueCommand(editor, object, 'lane', [newObject.id, { "road": newObject.parent.name, "lane": newObject.name }]));
						}
					}
					if (object.start !== lineSegmentStart.getValue()) {
						editor.execute(new SetRoadMarkValueCommand(editor, object, 'start', lineSegmentStart.getValue()));
					}
					if (object.end !== lineSegmentEnd.getValue()) {
						editor.execute(new SetRoadMarkValueCommand(editor, object, 'end', lineSegmentEnd.getValue()));
					}
				} else if (object.type === 'RoadMark.Sign') {
					const parameters = JSON.parse(JSON.stringify(object.parameters));
					if (parameters.mode === 'stop_line') {
						if (((object.parent === null || object.parent.type !== 'Road') ? Number.MIN_SAFE_INTEGER : object.parent.id) !== +signRoad.getValue()) {
							var newObject = editor.scene.getObjectById(+signRoad.getValue(), true);
							if (newObject !== undefined && newObject !== null && newObject.type === 'Road') {
								editor.execute(new SetRoadMarkSignValueCommand(editor, object, 'road', newObject));
							}
						}
					} else {
						if (((object.parent === null || object.parent.type !== 'Lane') ? Number.MIN_SAFE_INTEGER : object.parent.id) !== +signLane.getValue()) {
							var newObject = editor.scene.getObjectById(+signLane.getValue(), true);
							if (newObject !== undefined && newObject !== null && newObject.type === 'Lane') {
								editor.execute(new SetRoadMarkSignValueCommand(editor, object, 'lane', newObject));
							}
						}
					}

					if (parameters.mode !== signMode.getValue()) {
						parameters.mode = signMode.getValue();
					}
					let newPosition = new THREE.Vector2(signPositionS.getValue(), signPositionT.getValue());
					if (newPosition.distanceTo(parameters.position) >= 0.001) {
						parameters.position = { 'x': newPosition.x, 'y': newPosition.y };
					}
					if (parameters.rotation !== signRotation.getValue()) {
						parameters.rotation = signRotation.getValue()
					}
					if (parameters.mode === 'stop_line') {
						if (parameters.scale !== signLength.getValue()) {
							parameters.scale = signLength.getValue()
						}
					} else /* Arrow */ {
						if (parameters.scale !== signScale.getValue()) {
							parameters.scale = signScale.getValue()
						}
					}
					if (parameters.color !== signColor.getHexValue()) {
						parameters.color = signColor.getHexValue()
					}

					if (JSON.stringify(object.parameters) != JSON.stringify(parameters)) {
						editor.execute(new SetRoadMarkSignValueCommand(editor, object, 'parameters', parameters));
					}

				} else if (object.type === 'RoadMark.Crosswalk') {
					const parameters = JSON.parse(JSON.stringify(object.parameters));
					if (((object.parent === null || object.parent.type !== 'Road') ? Number.MIN_SAFE_INTEGER : object.parent.id) !== +crosswalkRoad.getValue()) {
						var newObject = editor.scene.getObjectById(+crosswalkRoad.getValue(), true);
						if (newObject !== undefined && newObject !== null && newObject.type === 'Road') {
							editor.execute(new SetRoadMarkCrossWalkValueCommand(editor, object, 'road', newObject));
						}
					}
					if (((object.parent === null || object.parent.type !== 'Junction') ? Number.MIN_SAFE_INTEGER : object.parent.id) !== +crosswalkRoad.getValue()) {
						var newObject = editor.scene.getObjectById(+crosswalkRoad.getValue(), true);
						if (newObject !== undefined && newObject !== null && newObject.type === 'Junction') {
							editor.execute(new SetRoadMarkCrossWalkValueCommand(editor, object, 'junction', newObject));
						}
					}
					let newPosition = new THREE.Vector2(crosswalkPositionS.getValue(), crosswalkPositionT.getValue());
					if (newPosition.distanceTo(parameters.position) >= 0.001) {
						parameters.position = { 'x': newPosition.x, 'y': newPosition.y };
					}
					if (parameters.width !== crosswalkWidth.getValue()) {
						parameters.width = crosswalkWidth.getValue()
					}
					if (parameters.skew !== crosswalkSkew.getValue()) {
						parameters.skew = crosswalkSkew.getValue()
					}
					if (parameters.color !== crosswalkColor.getHexValue()) {
						parameters.color = crosswalkColor.getHexValue()
					}
					if (JSON.stringify(object.parameters) != JSON.stringify(parameters)) {
						editor.execute(new SetRoadMarkCrossWalkValueCommand(editor, object, 'parameters', parameters));
					}
				} else if (object.type === 'RoadMark.Signal.TrafficLight') {
					const parameters = JSON.parse(JSON.stringify(object.parameters));
					if (((object.parent === null || object.parent.type !== 'Road') ? Number.MIN_SAFE_INTEGER : object.parent.id) !== +trafficlightRoad.getValue()) {
						var newObject = editor.scene.getObjectById(+trafficlightRoad.getValue(), true);
						if (newObject !== undefined && newObject !== null && newObject.type === 'Road') {
							editor.execute(new SetTrafficLightValueCommand(editor, object, 'road', newObject));
						}
					}

					let newPosition = new THREE.Vector2(trafficlightPositionS.getValue(), trafficlightPositionT.getValue());
					if (newPosition.distanceTo(parameters.position) >= 0.001) {
						parameters.position = { 'x': newPosition.x, 'y': newPosition.y };
					}
					if (parameters.orientation !== trafficlightOrientation.getValue()) {
						parameters.orientation = trafficlightOrientation.getValue()
					}
					if (parameters.rotation !== trafficlightRotation.getValue()) {
						parameters.rotation = trafficlightRotation.getValue()
					}
					if (JSON.stringify(object.parameters) != JSON.stringify(parameters)) {
						editor.execute(new SetTrafficLightValueCommand(editor, object, 'parameters', parameters));
					}
				} else if (object.type === 'RoadMark.TurningMark') {
					const parameters = JSON.parse(JSON.stringify(object.parameters));
					if (((object.parent === null || object.parent.type !== 'Junction') ? Number.MIN_SAFE_INTEGER : object.parent.id) !== +turningmarkJunction.getValue()) {
						var newObject = editor.scene.getObjectById(+turningmarkJunction.getValue(), true);
						if (newObject !== undefined && newObject !== null && newObject.type === 'Junction') {
							editor.execute(new SetRoadMarkTurningMarkValueCommand(editor, object, 'road', newObject));
						}
					}

					if (parameters.mode !== turningmarkMode.getValue()) {
						parameters.mode = turningmarkMode.getValue();
					}

					if (parameters.scale !== turningmarkScale.getValue()) {
						parameters.scale = turningmarkScale.getValue()
					}
					if (parameters.rotation !== turningmarkRotation.getValue()) {
						parameters.rotation = turningmarkRotation.getValue()
					}
					if (parameters.color !== turningmarkColor.getHexValue()) {
						parameters.color = turningmarkColor.getHexValue()
					}

					if (JSON.stringify(object.parameters) != JSON.stringify(parameters)) {
						editor.execute(new SetRoadMarkTurningMarkValueCommand(editor, object, 'parameters', parameters));
					}
				}
			}
		}
	}

	//

	function updateRows(object) {
		var commonProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow
		};
		var roadStructureProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow
		};

		var roadMarkLineProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow,
			'lineMode': lineModeRow,
			'linePosition': linePositionRow,
			'lineWidth': lineWidthRow,
			'lineOffset': lineOffsetRow,
			'lineColor': lineColorRow,
			'lineAdd': lineAddRow
		};

		var roadMarkLineSegmentProperties = {
			'objectType': objectTypeRow,
			'lineSegmentLane': lineSegmentLaneRow,
			'lineSegmentStart': lineSegmentStartRow,
			'lineSegmentEnd': lineSegmentEndRow
		};

		var roadMarkSignProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow,
			'signRoad': signRoadRow,
			'signLane': signLaneRow,
			'signMode': signModeRow,
			'signPosition': signPositionRow,
			'signRotation': signRotationRow,
			'signScale': signScaleRow,
			'signLength': signLengthRow,
			'signColor': signColorRow
		};

		var roadMarkCrosswalkProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow,
			'crosswalkRoad': crosswalkRoadRow,
			'crosswalkPosition': crosswalkPositionRow,
			'crosswalkWidth': crosswalkWidthRow,
			'crosswalkSkew': crosswalkSkewRow,
			'crosswalkColor': crosswalkColorRow
		};

		var roadMarkTrafficlightProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow,
			'trafficlightRoad': trafficlightRoadRow,
			'trafficlightPosition': trafficlightPositionRow,
			'trafficlightOrientation': trafficlightOrientationRow,
			'trafficlightRotation': trafficlightRotationRow,
		};

		var roadMarkTurningmarkProperties = {
			'objectType': objectTypeRow,
			'objectId': objectIdRow,
			'turningmarkMode': turningmarkModeRow,
			'turningmarkJunction': turningmarkJunctionRow,
			'turningmarkScale': turningmarkScaleRow,
			'turningmarkRotation': turningmarkRotationRow,
			'turningmarkColor': turningmarkColorRow
		};

		var propertyCollections = {
			'RoadStructure': roadStructureProperties,
			'RoadMark.Line': roadMarkLineProperties,
			'RoadMark.LineSegment': roadMarkLineSegmentProperties,
			'RoadMark.Sign': roadMarkSignProperties,
			'RoadMark.Signal.TrafficLight': roadMarkTrafficlightProperties,
			'RoadMark.Crosswalk': roadMarkCrosswalkProperties,
			'RoadMark.TurningMark': roadMarkTurningmarkProperties
		}

		function setPropertiesDisplay(type, value) {
			Object.values(propertyCollections[type]).forEach(property => property.setDisplay(value));
		}

		function showProperties(type) {
			Object.keys(propertyCollections).filter(e => e !== type).forEach(key => setPropertiesDisplay(key, 'none'));
			setPropertiesDisplay(type, '')
		}

		showProperties(object.type);

	}

	// events

	signals.objectChanged.add(updateLaneUI);
	signals.editorCleared.add(updateLaneUI);
	signals.sceneGraphChanged.add(updateLaneUI);

	signals.objectSelected.add(function (object) {
		var roadMarkTypes = [
			'RoadStructure',
			'RoadMark.Line',
			'RoadMark.LineSegment',
			'RoadMark.Sign',
			'RoadMark.Crosswalk',
			'RoadMark.Signal.TrafficLight',
			'RoadMark.TurningMark'
		]

		if (object !== null && (roadMarkTypes.indexOf(object.type) !== -1)) {

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

	function updateLaneOptions() {
		laneOptions = {};
		(function addObjects(objects) {
			for (let i = 0, l = objects.length; i < l; i++) {
				const object = objects[i];
				if (object.type === 'Road') {
					const laneChildren = object.children.filter(child => child.type === 'Lane');
					for (let child of laneChildren) {
						laneOptions[child.id] = `${object.name}_${child.name}`;
					}
				}
			}
		})(editor.scene.children);
		laneOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
		(function addObjects(objects) {
			for (let i = 0, l = objects.length; i < l; i++) {
				const object = objects[i];
				if (object.type === 'RoadMark.Line') {
					addObjects(object.children);
				} else if (object.type === 'RoadMark.LineSegment') {
					if (object.lane[0] === Number.MIN_SAFE_INTEGER) {
						object.lane[1] = { "road": "", "lane": "" };
					} else if (`${object.lane[1].road}_${object.lane[1].lane}` !== laneOptions[object.lane[0]]) {
						var laneObject = editor.scene.getObjectById(object.lane[0], true);
						if (laneObject !== null) {
							object.lane[1].road = laneObject.parent.name;
							object.lane[1].lane = laneObject.name;
							signals.objectChanged.dispatch(object);
						}
					}
				}
			}
		})(editor.scene.children);
	}

	function updateDrivingLaneOptions() {
		drivingLaneOptions = {};
		(function addObjects(objects) {
			for (let i = 0, l = objects.length; i < l; i++) {
				const object = objects[i];
				if (object.type === 'Road') {
					const laneChildren = object.children.filter(child => child.type === 'Lane' && (child.metalData.type === 'driving' || child.metalData.type === 'ramp'));
					for (let child of laneChildren) {
						drivingLaneOptions[child.id] = `${object.name}_${child.name}`;
					}
				}
			}
		})(editor.scene.children);
		drivingLaneOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	}

	function updateRoadOptions() {
		roadOptions = {};
		(function addObjects(objects) {
			for (let i = 0, l = objects.length; i < l; i++) {
				const object = objects[i];
				if (object.type === 'Road' || object.type === 'Junction') {
					roadOptions[object.id] = `${object.name}`;
				}
			}
		})(editor.scene.children);
		roadOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	}

	//親がJunctionだけの表示
	function updateJunctionOptions() {
		turningmarkJunctionOptions = {};
		(function addObjects(objects) {
			for (let i = 0, l = objects.length; i < l; i++) {
				const object = objects[i];
				if (object.type === 'Junction') {
					turningmarkJunctionOptions[object.id] = `${object.name}`;
				}
			}
		})(editor.scene.children);
		turningmarkJunctionOptions[`${Number.MIN_SAFE_INTEGER}`] = '';
	}

	function updateLaneUI() {
		const selectedObject = editor.selected;
		if (!selectedObject) {
			return;
		}

		if (selectedObject.type === 'RoadMark.LineSegment') {
			updateLaneOptions();
			var currentSelected = lineSegmentLane.getValue();
			lineSegmentLane.setOptions(laneOptions);

			for (let key in laneOptions) {
				if (key === `${Number.MIN_SAFE_INTEGER}`) continue;
				if (currentSelected === key) {
					lineSegmentLane.setValue(currentSelected);
					return;
				}

			}
			lineSegmentLane.setValue(`${Number.MIN_SAFE_INTEGER}`);
		} else if (selectedObject.type === 'RoadMark.Sign') {
			if (selectedObject.parameters.mode === 'stop_line') {
				updateRoadOptions();
				var currentSelected = signRoad.getValue();
				signRoad.setOptions(roadOptions);

				for (let key in roadOptions) {
					if (key === `${Number.MIN_SAFE_INTEGER}`) continue;
					if (currentSelected === key) {
						signRoad.setValue(currentSelected);
						return;
					}

				}
				signLane.setValue(`${Number.MIN_SAFE_INTEGER}`);
			} else {
				updateDrivingLaneOptions();
				var currentSelected = signLane.getValue();
				signLane.setOptions(drivingLaneOptions);

				for (let key in drivingLaneOptions) {
					if (key === `${Number.MIN_SAFE_INTEGER}`) continue;
					if (currentSelected === key) {
						signLane.setValue(currentSelected);
						return;
					}

				}
				signLane.setValue(`${Number.MIN_SAFE_INTEGER}`);
			}
		} else if (selectedObject.type === 'RoadMark.Crosswalk') {
			updateRoadOptions();
			var currentSelected = crosswalkRoad.getValue();
			crosswalkRoad.setOptions(roadOptions);

			for (let key in roadOptions) {
				if (key === `${Number.MIN_SAFE_INTEGER}`) continue;
				if (currentSelected === key) {
					crosswalkRoad.setValue(currentSelected);
					return;
				}

			}
		} else if (selectedObject.type === 'RoadMark.Signal.TrafficLight') {
			updateRoadOptions();
			var currentSelected = trafficlightRoad.getValue();
			trafficlightRoad.setOptions(roadOptions);

			for (let key in roadOptions) {
				if (key === `${Number.MIN_SAFE_INTEGER}`) continue;
				if (currentSelected === key) {
					trafficlightRoad.setValue(currentSelected);
					return;
				}

			}
		} else if (selectedObject.type === 'RoadMark.TurningMark') {
			updateJunctionOptions();
			var currentSelected = turningmarkJunction.getValue();
			turningmarkJunction.setOptions(turningmarkJunctionOptions);

			for (let key in turningmarkJunctionOptions) {
				if (key === `${Number.MIN_SAFE_INTEGER}`) continue;
				if (currentSelected === key) {
					turningmarkJunction.setValue(currentSelected);
					return;
				}

			}
		}
	}

	function updateUI(object) {

		var scene = editor.scene;

		updateLaneUI();

		objectType.setValue(object.type);
		objectId.setValue(object.name);

		if (object.type === 'RoadStructure') {

			// TODO

		} else if (object.type === 'RoadMark.Line') {
			const parameters = object.parameters;
			lineMode.setValue(object.mode);
			linePosition.setValue(parameters.position);
			lineWidth.setValue(parameters.width);
			lineOffset.setValue(parameters.offset);
			lineColor.setHexValue(parameters.color);
		} else if (object.type === 'RoadMark.LineSegment') {
			lineSegmentLane.setValue(`${object.lane[0]}`);
			lineSegmentStart.setValue(object.start);
			lineSegmentEnd.setValue(object.end);
		} else if (object.type === 'RoadMark.Sign') {
			const parameters = object.parameters;
			if (parameters.mode === 'stop_line') {
				signLaneRow.setDisplay('none');
				signRoadRow.setDisplay('');
				if (object.parent === null || object.parent.type !== 'Road') {
					signRoad.setValue(`${Number.MIN_SAFE_INTEGER}`);
				} else {
					signRoad.setValue(`${object.parent.id}`);
				}
				signScaleRow.setDisplay('none');
				signLengthRow.setDisplay('');
				signLength.setValue(parameters.scale);
			}
			else {
				signRoadRow.setDisplay('none');
				signLaneRow.setDisplay('');
				if (object.parent === null || object.parent.type !== 'Lane') {
					signLane.setValue(`${Number.MIN_SAFE_INTEGER}`);
				} else {
					signLane.setValue(`${object.parent.id}`);
				}
				signLengthRow.setDisplay('none');
				signScaleRow.setDisplay('');
				signScale.setValue(parameters.scale);
			}
			signMode.setValue(parameters.mode);
			signPositionS.setValue(parameters.position.x);
			signPositionT.setValue(parameters.position.y);
			signRotation.setValue(parameters.rotation);
			signColor.setHexValue(parameters.color);
		} else if (object.type === 'RoadMark.Crosswalk') {
			const parameters = object.parameters;
			crosswalkRoadRow.setDisplay('');
			if (object.parent === null || object.parent.type !== 'Road' && object.parent.type !== 'Junction') {
				crosswalkRoad.setValue(`${Number.MIN_SAFE_INTEGER}`);
			} else {
				crosswalkRoad.setValue(`${object.parent.id}`);
			}
			crosswalkPositionS.setValue(parameters.position.x);
			crosswalkPositionT.setValue(parameters.position.y);
			crosswalkWidth.setValue(parameters.width);
			crosswalkSkew.setValue(parameters.skew);
			crosswalkColor.setHexValue(parameters.color);
		} else if (object.type === 'RoadMark.Signal.TrafficLight') {
			const parameters = object.parameters;
			trafficlightRoadRow.setDisplay('');
			if (object.parent === null || object.parent.type !== 'Road' && object.parent.type !== 'Junction') {
				trafficlightRoad.setValue(`${Number.MIN_SAFE_INTEGER}`);
			} else {
				trafficlightRoad.setValue(`${object.parent.id}`);
			}
			trafficlightPositionS.setValue(parameters.position.x);
			trafficlightPositionT.setValue(parameters.position.y);
			trafficlightOrientation.setValue(parameters.orientation);
			trafficlightRotation.setValue(parameters.rotation);
		} else if (object.type === 'RoadMark.TurningMark') {
			const parameters = object.parameters;
			turningmarkJunctionRow.setDisplay('');
			if (object.parent === null || object.parent.type !== 'Junction') {
				turningmarkJunction.setValue(`${Number.MIN_SAFE_INTEGER}`);
			} else {
				turningmarkJunction.setValue(`${object.parent.id}`);
			}
			turningmarkScale.setValue(parameters.scale);
			turningmarkRotation.setValue(parameters.rotation);
			turningmarkColor.setHexValue(parameters.color);
		}

	}

	return container;

};

export { SidebarRoadMarkObject };
