/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { UIPanel, UIRow, UIHorizontalRule } from '../../../vendors/js/libs/ui.js';

import { AddObjectCommand } from './commands/AddObjectCommand.js';

import * as GARDEN from '../../garden-objects/js/roads/GardenObjects.js';
import * as RoadMark from '../../garden-objects/js/roadmarks/RoadMarkObjects';

import Delaunator from 'delaunator';

var MenubarAdd = function (editor) {

	var strings = editor.strings;

	var container = new UIPanel();
	container.setClass('menu');

	var title = new UIPanel();
	title.setClass('title');
	title.setTextContent(strings.getKey('menubar/add'));
	container.add(title);

	var options = new UIPanel();
	options.setClass('options');
	container.add(options);

	// Test

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent('Test');
	option.onClick(function () {


		// let mesh = new THREE.Test();
		// mesh.name = 'Test';
		// editor.execute(new AddObjectCommand(editor, mesh));

	});
	// options.add(option);

	// Road

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/road'));
	option.onClick(function () {

		var roadData = {
			'id': 'R100',
			'length': 50,
			//'height': -10.0,
			//'ramp_angle': 6.0,
			'height': 0,
			'ramp_angle': 0,
			'type': 'straight',
			//'type': 'cubic_left',
			//'type': 'circular',
			//'type': 'clothoid_in',
			'radius': 80.0,
			'start_radius': 0.0,
			'end_radius': 0.0,
			'reverse': false,
			'lanes': {
				'left': [
					{
						'id': 'L-01',
						'type': 'driving',
						'position': 'left',
						'width': 4,
						'refLineVisible': false
					}
				],
				'center': [
					{
						'id': 'C-00',
						'type': 'center',
						'position': 'center',
						'width': 4,
						'refLineVisible': false
					}
				],
				'right': [
					{
						'id': 'R-01',
						'type': 'driving',
						'position': 'right',
						'width': 4,
						'refLineVisible': false
					}
				]
			},
			'refLineVisible': false
		}

		var roadObject = new GARDEN.RoadObject();
		roadObject.parent = editor.scene;
		roadObject.setFromMetalData(roadData);
		editor.execute(new AddObjectCommand(editor, roadObject));
	});
	options.add(option);

	// Junction

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/junction'));
	option.onClick(function () {

		var metalData = {
			'id': 'Junction_NEW',
			'connections': [],
			'edgeType': 'typea',
			'lanes': []
		}
		const wireframe = editor.config.getKey('wireframe');

		var junction = new GARDEN.JunctionObject();
		junction.parent = editor.scene;
		junction.setFromMetalData(metalData);

		editor.execute(new AddObjectCommand(editor, junction));

	});
	options.add(option);

	//

	options.add(new UIHorizontalRule());

	// RoadMarkLine

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/line'));
	option.onClick(function () {

		var selectedLane = editor.selected;

		var roadMarkLine = new RoadMark.Line();
		roadMarkLine.name = 'Line_New';

		if (selectedLane !== null && selectedLane.type === 'Lane') {
			var roadMarkLineSegment = new RoadMark.LineSegment();
			roadMarkLineSegment.name = selectedLane.name;
			roadMarkLineSegment.parent = roadMarkLine;
			roadMarkLineSegment.lane = [selectedLane.id, selectedLane.name];

			roadMarkLine.segments.push(roadMarkLineSegment);
			roadMarkLine.children.push(roadMarkLineSegment);

			editor.computeRoadMarkLineGeometry(roadMarkLine);
		}
		editor.execute(new AddObjectCommand(editor, roadMarkLine));
	});
	options.add(option);

	// RoadMark.Sign -> Arrow

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/arrow'));
	option.onClick(function () {
		let roadMarkSign = new RoadMark.Sign(RoadMark.ARROW_DEFAULT_PARAMETER);
		roadMarkSign.name = 'Arrow_New';
		editor.execute(new AddObjectCommand(editor, roadMarkSign));
	});
	options.add(option);

	// RoadMark.Sign -> StopLine

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/stopline'));
	option.onClick(function () {
		let roadMarkSign = new RoadMark.Sign(RoadMark.STOPLINE_DEFAULT_PARAMETER);
		roadMarkSign.name = 'StopLine_New';
		editor.execute(new AddObjectCommand(editor, roadMarkSign));
	});
	options.add(option);

	// RoadMark.Sign -> StopMark

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/charactermark'));
	option.onClick(function () {
		let roadMarkSign = new RoadMark.Sign(RoadMark.STOPMARK_DEFAULT_PARAMETER);
		roadMarkSign.name = 'CharacterMark_New';
		editor.execute(new AddObjectCommand(editor, roadMarkSign));
	});
	options.add(option);

	// RoadMark.Sign -> DiamondMark

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/diamondmark'));
	option.onClick(function () {
		let roadMarkSign = new RoadMark.Sign(RoadMark.DIAMONDMARK_DEFAULT_PARAMETER);
		roadMarkSign.name = 'DiamondMark_New';
		editor.execute(new AddObjectCommand(editor, roadMarkSign));
	});
	options.add(option);

	// RoadMark.turningmark
	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/turningmark'));
	option.onClick(function () {

		let turningmark = new RoadMark.TurningMark(RoadMark.TURNINGMARK_DEFAULT_PARAMETER);
		turningmark.name = 'TurningMark_New';
		editor.execute(new AddObjectCommand(editor, turningmark));

	});
	options.add(option);

	// ClossWalk

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/crosswalk'));
	option.onClick(function () {

		let crosswalk = new RoadMark.Crosswalk();
		crosswalk.name = 'Crosswalk_New';
		editor.execute(new AddObjectCommand(editor, crosswalk));

	});
	options.add(option);

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/roadmark/trafficlight'));
	option.onClick(function () {

		let trafficlight = new RoadMark.TrafficLight();
		trafficlight.name = 'TrafficLight_New';
		editor.execute(new AddObjectCommand(editor, trafficlight));

	});
	//options.add(option);

	options.add(new UIHorizontalRule());

	//

	options.add(new UIHorizontalRule());

	// Route

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/add/route'));
	option.onClick(function () {

		var metalData = {
			"gid": "Route.NEW",
			"routes": [
				{
					"accel": 2000,
					"entities": [
					]
				}
			],
			"wayPoints": [],
			"pointProperties": []
		};
		var color = Math.random() * 0xffffff;
		metalData.routeData = editor.computeRouteCurve4(metalData.gid, '#' + new THREE.Color(color).getHexString(), metalData.routes[0]);
		var routeObject = new GARDEN.RouteObject(metalData.gid, color).setFromMetalData(metalData);
		editor.execute(new AddObjectCommand(editor, routeObject));

	});
	options.add(option);

	return container;

};

export { MenubarAdd };
