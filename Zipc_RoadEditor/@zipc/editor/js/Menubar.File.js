/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { ColladaExporter } from '../../../vendors/examples/jsm/exporters/ColladaExporter.js';
import { GLTFExporter } from '../../../vendors/examples/jsm/exporters/GLTFExporter.js';
import { OBJExporter } from '../../../vendors/examples/jsm/exporters/OBJExporter.js';
import { PLYExporter } from '../../../vendors/examples/jsm/exporters/PLYExporter.js';
import { STLExporter } from '../../../vendors/examples/jsm/exporters/STLExporter.js';

import { UIPanel, UIRow, UIHorizontalRule } from '../../../vendors/js/libs/ui.js';

import { DSLUtils } from '../../garden-objects/js/DSLUtils.js';
import { OSWayUtils } from '../../garden-objects/js/objects/GOWay';

var MenubarFile = function (editor) {

	function parseNumber(key, value) {

		var precision = config.getKey('exportPrecision');

		return typeof value === 'number' ? parseFloat(value.toFixed(precision)) : value;

	}

	//

	var config = editor.config;
	var strings = editor.strings;

	var container = new UIPanel();
	container.setClass('menu');

	var title = new UIPanel();
	title.setClass('title');
	title.setTextContent(strings.getKey('menubar/file'));
	container.add(title);

	var options = new UIPanel();
	options.setClass('options');
	container.add(options);

	// New

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/new'));
	option.onClick(function () {

		if (confirm('Any unsaved data will be lost. Are you sure?')) {

			editor.clear();

		}

	});
	options.add(option);

	//

	options.add(new UIHorizontalRule());

	// Export JSON

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/save'));
	option.onClick(function () {

		var scene = editor.scene;

		if (scene === null) {

			alert('No object selected.');
			return;

		}

		try {

			var scenarioData = {};
			scenarioData._id = scene._id;
			scenarioData.gid = scene.gid;
			scenarioData.description = scene.description;
			scenarioData.direction = scene.direction;
			scenarioData.roads = [];
			scenarioData.roadMarks = [];
			scenarioData.junctions = [];
			scenarioData.routes = [];

			var objects = scene.children;

			scenarioData = getRoadStructureData(scene).map;
			if (editor.backgroundMap !== undefined) {
				scenarioData['backgroundMap'] = {
					"visible": editor.backgroundMap.visible,
					"boundary": {
						n: editor.backgroundMap.boundary.n,
						e: editor.backgroundMap.boundary.e,
						s: editor.backgroundMap.boundary.s,
						w: editor.backgroundMap.boundary.w
					}
				};
			}

			if (editor.backgroundPlane01 !== undefined) {
				scenarioData['backgroundPlane01'] = editor.backgroundPlane01.toJSON();
			}

			if (editor.backgroundPlane02 !== undefined) {
				scenarioData['backgroundPlane02'] = editor.backgroundPlane02.toJSON();
			}

			objects.filter(object => object.type === 'Route')
				.forEach(object => {
					var metalData = JSON.parse(JSON.stringify(object.metalData));
					if (metalData.hasOwnProperty('objectId')) delete metalData['objectId'];
					if (metalData.hasOwnProperty('routeData')) delete metalData['routeData'];
					if (metalData.hasOwnProperty('wayPoints')) delete metalData['wayPoints'];
					metalData.color = object.color;
					if (scenarioData['routes'] === undefined) scenarioData['routes'] = [];
					scenarioData.routes.push(metalData);
				});

			var output = JSON.stringify(scenarioData, parseNumber, '\t');
			output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');

		} catch (e) {

			output = '{}';

		}

		saveString(output, scene.gid + '.json');

	});
	options.add(option);

	// Import JSON

	var form = document.createElement('form');
	form.style.display = 'none';
	document.body.appendChild(form);

	var fileInput = document.createElement('input');
	fileInput.multiple = true;
	fileInput.type = 'file';
	fileInput.addEventListener('change', function () {


		if (confirm('Any unsaved data will be lost. Are you sure?')) {

			editor.clear();

			editor.loader.loadFiles(fileInput.files);
			form.reset();

			editor.select(editor.scene);

		}



	});
	form.appendChild(fileInput);


	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/open'));
	option.onClick(function () {

		fileInput.click();

	});
	options.add(option);


	// ##############################################################################

	options.add(new UIHorizontalRule());


	// Export MongoDB
	var option = new UIRow();
	option.setClass('option');
	// option.setClass('inactive');
	option.setTextContent('Save to DB');
	option.onClick(function () {
		var scene = editor.scene;
		if (scene === null) {
			alert('No object selected.');
			return;
		}

		try {
			let roadStructureData = getRoadStructureData(scene);
			var roadStructureOutput = JSON.stringify(roadStructureData, parseNumber, '\t');
			roadStructureOutput = roadStructureOutput.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');
			let analyzerData = getAnalyzerData(scene);
			var analyzerOutput = JSON.stringify(analyzerData, parseNumber, '\t');
			analyzerOutput = analyzerOutput.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');
			OSWayUtils.saveMapData(roadStructureData.map.gid, roadStructureData.map.description, roadStructureData.map.boundary, roadStructureOutput, analyzerOutput)
				.then(result => {
					alert('Saved');
				}).catch(err => {
					alert(err.message);
				});
		} catch (e) {
		}
	});
	options.add(option);


	// Import MongoDB
	var option = new UIRow();
	option.setClass('option');
	// option.setClass('inactive');
	option.setTextContent('Open from DB');
	option.onClick(function () {
		editor.signals.mapManageScreenShow.dispatch();
	});
	options.add(option);


	// ##############################################################################


	//

	options.add(new UIHorizontalRule());


	// Import OBJ

	var importFileInput = document.createElement('input');
	importFileInput.multiple = true;
	importFileInput.type = 'file';
	importFileInput.addEventListener('change', function () {
		if (confirm('Any unsaved data will be lost. Are you sure?')) {
			editor.loader.loadFiles(importFileInput.files);
			form.reset();
			editor.select(editor.scene);

		}
	});
	form.appendChild(importFileInput);
	var option = new UIRow();
	option.setClass('option');
	option.setTextContent('Import OBJ');
	option.onClick(function () {
		importFileInput.click();
	});
	options.add(option);

	options.add(new UIHorizontalRule());

	// Import DSL

	var form = document.createElement('form');
	form.style.display = 'none';
	document.body.appendChild(form);

	var fileInput = document.createElement('input');
	fileInput.multiple = true;
	fileInput.type = 'file';
	fileInput.addEventListener('change', function () {


		if (confirm('Any unsaved data will be lost. Are you sure?')) {

			editor.clear();

			editor.loader.loadFiles(fileInput.files);
			form.reset();

			editor.select(editor.scene);

		}



	});
	form.appendChild(fileInput);

	// Export Waypoint

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/waypoint'));
	option.onClick(function () {
		var scene = editor.scene;
		if (scene === null) {
			alert('No object selected.');
			return;
		}

		try {
			var scenarioData = { 'id': `WayPoint_${scene.name}`, 'mapId': `${!scene._id ? scene.name : scene._id}` };
			scenarioData.waypoints = [];
			scenarioData.properties = [];
			var objects = scene.children;
			for (var i = 0, l = objects.length; i < l; i++) {
				var object = objects[i];
				if (!object.metalData) continue;

				var metalData = JSON.parse(JSON.stringify(object.metalData));
				if (object.type === 'Route') {
					const wayPoints = !metalData['wayPoints'] ? [] : metalData['wayPoints'];
					const pointProperties = !metalData['pointProperties'] ? [] : metalData['pointProperties'];
					if (wayPoints.length > 0) {
						scenarioData.waypoints.push({ 'id': object.name, 'points': [...wayPoints] });
					}
					for (let pp of pointProperties) {
						pp['waypointId'] = object.name;
						scenarioData.properties.push(pp);
					}
				}
			}
			var output = JSON.stringify(scenarioData, parseNumber, '\t');
			output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');
		} catch (e) {
			output = '{}';
		}
		saveString(output, scene.name + '.waypoint.json');

	});
	options.add(option);

	//

	// Export Analyzer

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/analyser'));
	option.onClick(function () {
		const scene = editor.scene;
		if (scene === null) {
			alert('No object selected.');
			return;
		}
		let analyzerData = getAnalyzerData(scene);
		var output = JSON.stringify(analyzerData, parseNumber, '\t');
		output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');
		saveString(output, scene.gid + '.analyser.json');

	});
	options.add(option);

	//

	options.add(new UIHorizontalRule());

	// Export OBJ

	var option = new UIRow();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/obj'));
	option.onClick(function () {

		const scene = editor.scene;

		var exporter = new OBJExporter();

		if (scene.type === 'RoadStructure') {

			saveString(exporter.parse(scene), `${scene.name}.obj`);

		}

	});
	options.add(option);

	//

	var link = document.createElement('a');
	function save(blob, filename) {

		link.href = URL.createObjectURL(blob);
		link.download = filename || 'data.json';
		link.dispatchEvent(new MouseEvent('click'));

		// URL.revokeObjectURL( url ); breaks Firefox...

	}

	function saveArrayBuffer(buffer, filename) {

		save(new Blob([buffer], { type: 'application/octet-stream' }), filename);

	}

	function saveString(text, filename) {

		save(new Blob([text], { type: 'text/plain' }), filename);

	}

	function getRoadStructureData(scene) {
		let roadData = {
			"map": {
				"gid": scene.gid,
				"description": scene.description,
				"direction": scene.direction,
				'boundary': { 'n': 0, 'e': 0, 's': 0, 'w': 0 },
				"roads": [],
				"roadMarks": [],
				"junctions": [],
				"routes": []
			},
			"waypoints": []
		};
		try {
			var boundary = {};

			if (editor.backgroundMap !== undefined) {
				boundary = {
					n: editor.backgroundMap.boundary.n,
					e: editor.backgroundMap.boundary.e,
					s: editor.backgroundMap.boundary.s,
					w: editor.backgroundMap.boundary.w
				};
				roadData.map.boundary = { 'n': boundary.n, 'e': boundary.e, 's': boundary.s, 'w': boundary.w };
			}

			let targetObjects = new Array();

			scene.traverseVisible(function (child) {
				if (child.type === 'Road' || child.type === 'Junction'
					|| child.type === 'Lane'
					|| child.type === 'RoadMark.Line'
					|| child.type === 'RoadMark.Sign' || child.type === 'RoadMark.Crosswalk'
					|| child.type === 'RoadMark.Signal.TrafficLight'
					|| child.type === 'RoadMark.TurningMark') {
					targetObjects.push(child);
				}


			});

			targetObjects.forEach((child) => {
				try {
					var position = new THREE.Vector3();
					var rotation = new THREE.Euler();
					if (child.metalData !== undefined) {
						var metalData = JSON.parse(JSON.stringify(child.metalData));
						if (metalData.hasOwnProperty('objectId')) delete metalData['objectId'];
						if (child.type === 'Road') {
							if (metalData.hasOwnProperty('connectionType')) delete metalData['connectionType'];
							if (metalData.hasOwnProperty('connectionId')) delete metalData['connectionId'];
							if (metalData.hasOwnProperty('rotationZ')) delete metalData['rotationZ'];
							for (var lane of metalData.lanes.left) {
								if (lane.hasOwnProperty('objectId')) delete lane['objectId'];
								if (lane.hasOwnProperty('childIndex')) delete lane['childIndex'];
								if (lane.hasOwnProperty('laneIndex')) delete lane['laneIndex'];
								if (lane.hasOwnProperty('refLineVisible')) delete lane['refLineVisible'];

								let laneObject = child.getObjectByName(lane.id, true);
								if (laneObject.spriteEdge.offsetType === 'none') {
									if (lane.hasOwnProperty('sprite01')) delete lane['sprite01'];
									if (lane.hasOwnProperty('sprite02')) delete lane['sprite02'];
									if (lane.hasOwnProperty('spriteEdge')) delete lane['spriteEdge'];
								} else {
									lane.sprite01 = {
										'basePoint': laneObject.sprite01.basePoint,
										'position2': { 'x': laneObject.sprite01.position2.x, 'y': laneObject.sprite01.position2.y },
										'tangent': { 'x': laneObject.sprite01.tangent.x, 'y': laneObject.sprite01.tangent.y }
									};
									lane.sprite02 = {
										'basePoint': laneObject.sprite02.basePoint,
										'position2': { 'x': laneObject.sprite02.position2.x, 'y': laneObject.sprite02.position2.y },
										'tangent': { 'x': laneObject.sprite02.tangent.x, 'y': laneObject.sprite02.tangent.y }
									};
									lane.spriteEdge = {
										'offsetType': laneObject.spriteEdge.offsetType,
										'offsetInward': laneObject.spriteEdge.offsetInward,
									};
								}
							}
							for (var lane of metalData.lanes.center) {
								if (lane.hasOwnProperty('objectId')) delete lane['objectId'];
								if (lane.hasOwnProperty('childIndex')) delete lane['childIndex'];
								if (lane.hasOwnProperty('laneIndex')) delete lane['laneIndex'];
								if (lane.hasOwnProperty('refLineVisible')) delete lane['refLineVisible'];
							}
							for (var lane of metalData.lanes.right) {
								if (lane.hasOwnProperty('objectId')) delete lane['objectId'];
								if (lane.hasOwnProperty('childIndex')) delete lane['childIndex'];
								if (lane.hasOwnProperty('laneIndex')) delete lane['laneIndex'];
								if (lane.hasOwnProperty('refLineVisible')) delete lane['refLineVisible'];

								let laneObject = child.getObjectByName(lane.id, true);
								if (laneObject.spriteEdge.offsetType === 'none') {
									if (lane.hasOwnProperty('sprite01')) delete lane['sprite01'];
									if (lane.hasOwnProperty('sprite02')) delete lane['sprite02'];
									if (lane.hasOwnProperty('spriteEdge')) delete lane['spriteEdge'];
								} else {
									lane.sprite01 = {
										'basePoint': laneObject.sprite01.basePoint,
										'position2': { 'x': laneObject.sprite01.position2.x, 'y': laneObject.sprite01.position2.y },
										'tangent': { 'x': laneObject.sprite01.tangent.x, 'y': laneObject.sprite01.tangent.y }
									};
									lane.sprite02 = {
										'basePoint': laneObject.sprite02.basePoint,
										'position2': { 'x': laneObject.sprite02.position2.x, 'y': laneObject.sprite02.position2.y },
										'tangent': { 'x': laneObject.sprite02.tangent.x, 'y': laneObject.sprite02.tangent.y }
									};
									lane.spriteEdge = {
										'offsetType': laneObject.spriteEdge.offsetType,
										'offsetInward': laneObject.spriteEdge.offsetInward,
									};
								}
							}
							position.copy(child.position);
							rotation.copy(child.rotation);
							metalData.point = { x: position.x, y: position.y, z: position.z, roll: rotation.x * THREE.MathUtils.RAD2DEG, yaw: rotation.y * THREE.MathUtils.RAD2DEG, pitch: rotation.z * THREE.MathUtils.RAD2DEG };
							roadData.map.roads.push(metalData);
						} else if (child.type === 'Junction') {
							if (metalData.hasOwnProperty('junctionShapePoints')) delete metalData['junctionShapePoints'];
							if (metalData.hasOwnProperty('junctionIntersects')) delete metalData['junctionIntersects'];
							var junctionLanes = [];
							for (var laneObject of child.children) {
								if (laneObject.type !== 'JunctionLane') continue;
								junctionLanes.push({ 'id': laneObject.name, 'refLineVisible': laneObject.refLine.visible });
							}
							metalData['lanes'] = junctionLanes;
							roadData.map.junctions.push(metalData);
						} else if (child.type === 'Route') {
							const wayPoints = !metalData['wayPoints'] ? [] : metalData['wayPoints'];
							if (wayPoints.length > 0) {
								roadData.waypoints.push({ 'gid': child.gid, 'description': child.description, 'points': [...wayPoints] });
							}
							roadData.map.routes.push(metalData);
						}
					} else {
						if (child.goType === 'GORoad') {
							let metalData = child.toJSON();
							roadData.map.roads.push(metalData);
						} else if (child.type === 'RoadMark.Line' || child.type === 'RoadMark.Sign' || child.type === 'RoadMark.Crosswalk'
							|| child.type === 'RoadMark.Signal.TrafficLight' || child.type === 'RoadMark.TurningMark') {
							roadData.map.roadMarks.push(child.toJSON());
						}
					}
				} catch (e) {
					// TODO
				}
			});
		} catch (e) {
			roadData = {
				"map": {
					"gid": scene.gid,
					"description": scene.description,
					"direction": scene.direction,
					'boundary': { 'n': 0, 'e': 0, 's': 0, 'w': 0 },
					"roads": [],
					"roadMarks": [],
					"junctions": [],
					"routes": []
				},
				"waypoints": []
			};
		}
		return roadData;
	}

	function getAnalyzerData(scene) {
		let analyserData = {
			'gid': `${scene.gid}`,
			'direction': `${scene.direction}`,
			'roads': []
		};
		try {
			let connectionData = [];
			const objects = scene.children;
			for (let i = 0, l = objects.length; i < l; i++) {
				const object = objects[i];
				if (object.type === 'Road') {
					let roadData = { 'name': object.name, 'connection': [], 'lanes': [] };
					for (let child of object.children) {
						if (child.type === 'Lane' && (child.metalData.type === 'driving' || child.metalData.type === 'ramp')) {
							//let extensionInfo = JSON.parse(JSON.stringify(child.extensionInfo));
							child.computeWaypoint();
							roadData.lanes.push({ 'name': child.name, 'width': child.metalData.width, 'connection': [], 'waypoints': child.extensionInfo });
						}
					}
					analyserData.roads.push(roadData);

					// connection info
					let tempData = { 'name': object.name, 'connection': object.metalData.connection, 'reverse': object.metalData.reverse };
					connectionData.push(tempData);
				}
			}

			// add connection info
			const direction = scene.direction;
			for (let connectionInfo of connectionData) {
				// r1 -> [r2]
				try {
					if (connectionInfo.connection !== undefined && !connectionInfo.connection.road === false) {
						const r1 = analyserData.roads.find(road => road.name === connectionInfo.connection.road);
						const r2 = analyserData.roads.find(road => road.name === connectionInfo.name);
						if (r1 === undefined || r2 === undefined) {
							continue;
						}

						if (connectionInfo.reverse) {
							// predecessor {'type': 'predecessor', 'road': '', 'lane': ''}
							r1.connection.push({ 'type': 'predecessor', 'road': connectionInfo.name, 'lane': '' });
							r2.connection.push({ 'type': 'successor', 'road': connectionInfo.connection.road, 'lane': !connectionInfo.connection.lane ? '' : connectionInfo.connection.lane });
							let v1 = new THREE.Vector3(), v2 = new THREE.Vector3();
							for (let l2 of r2.lanes) {
								v2.copy((l2.name.startsWith('L-') ? 'left' : 'right') === direction ? l2.waypoints[l2.waypoints.length - 1].point : l2.waypoints[0].point);
								for (let l1 of r1.lanes) {
									v1.copy((l1.name.startsWith('L-') ? 'left' : 'right') === direction ? l1.waypoints[0].point : l1.waypoints[l1.waypoints.length - 1].point);
									if (v2.distanceTo(v1) <= 0.01) {
										l1.connection.push({ 'type': 'predecessor', 'road': r2.name, 'lane': l2.name });
										l2.connection.push({ 'type': 'successor', 'road': r1.name, 'lane': l1.name });
									}
								}
							}
						} else {
							// successor {'type': 'successor', 'road': '', 'lane': ''}
							r1.connection.push({ 'type': 'successor', 'road': connectionInfo.name, 'lane': '' });
							r2.connection.push({ 'type': 'predecessor', 'road': connectionInfo.connection.road, 'lane': !connectionInfo.connection.lane ? '' : connectionInfo.connection.lane });
							let v1 = new THREE.Vector3(), v2 = new THREE.Vector3();
							for (let l2 of r2.lanes) {
								v2.copy((l2.name.startsWith('L-') ? 'left' : 'right') === direction ? l2.waypoints[0].point : l2.waypoints[l2.waypoints.length - 1].point);
								for (let l1 of r1.lanes) {
									v1.copy((l1.name.startsWith('L-') ? 'left' : 'right') === direction ? l1.waypoints[l1.waypoints.length - 1].point : l1.waypoints[0].point);
									if (v2.distanceTo(v1) <= 0.01) {
										l1.connection.push({ 'type': 'successor', 'road': r2.name, 'lane': l2.name });
										l2.connection.push({ 'type': 'predecessor', 'road': r1.name, 'lane': l1.name });
									}
								}
							}
						}
					}
				} catch (e) {
					continue
				}
			}
		} catch (e) {
			analyserData = {
				'gid': `${scene.gid}`,
				'direction': `${scene.direction}`,
				'roads': []
			};
		}
		return analyserData;
	}

	return container;

};

export { MenubarFile };
