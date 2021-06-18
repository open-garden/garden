/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { AMFLoader } from '../../../vendors/examples/jsm/loaders/AMFLoader.js';
import { ColladaLoader } from '../../../vendors/examples/jsm/loaders/ColladaLoader.js';
import { DRACOLoader } from '../../../vendors/examples/jsm/loaders/DRACOLoader.js';
import { FBXLoader } from '../../../vendors/examples/jsm/loaders/FBXLoader.js';
import { GLTFLoader } from '../../../vendors/examples/jsm/loaders/GLTFLoader.js';
import { KMZLoader } from '../../../vendors/examples/jsm/loaders/KMZLoader.js';
import { MD2Loader } from '../../../vendors/examples/jsm/loaders/MD2Loader.js';
import { MTLLoader } from '../../../vendors/examples/jsm/loaders/MTLLoader.js';
import { OBJLoader } from '../../../vendors/examples/jsm/loaders/OBJLoader.js';
import { PLYLoader } from '../../../vendors/examples/jsm/loaders/PLYLoader.js';
import { STLLoader } from '../../../vendors/examples/jsm/loaders/STLLoader.js';
import { SVGLoader } from '../../../vendors/examples/jsm/loaders/SVGLoader.js';
import { TDSLoader } from '../../../vendors/examples/jsm/loaders/TDSLoader.js';
import { VTKLoader } from '../../../vendors/examples/jsm/loaders/VTKLoader.js';
import { VRMLLoader } from '../../../vendors/examples/jsm/loaders/VRMLLoader.js';

import { SetValueCommand } from './commands/SetValueCommand.js';
import { AddObjectCommand } from './commands/AddObjectCommand.js';
import { SetSceneCommand } from './commands/SetSceneCommand.js';


import { SetConnectionCommand } from './commands/SetConnectionCommand.js';
import { SetJunctionConnectionCommand } from './commands/SetJunctionConnectionCommand.js';
import { SetRoadMarkSignValueCommand } from './commands/SetRoadMarkSignValueCommand.js';
import { SetRoadMarkCrossWalkValueCommand } from './commands/SetRoadMarkCrossWalkValueCommand.js';
import { SetTrafficLightValueCommand } from './commands/SetTrafficLightValueCommand.js';
import { SetRoadMarkTurningMarkValueCommand } from './commands/SetRoadMarkTurningMarkValueCommand.js';

import * as GARDEN from '../../garden-objects/js/roads/GardenObjects.js';

import * as GO from '../../garden-objects/js/objects/GOWay';
import * as RoadMark from '../../garden-objects/js/roadmarks/RoadMarkObjects';

var Loader = function (editor) {

	var scope = this;

	this.texturePath = '';

	this.loadFiles = function (files) {

		if (files.length > 0) {

			var filesMap = createFileMap(files);

			var manager = new THREE.LoadingManager();
			manager.setURLModifier(function (url) {

				var file = filesMap[url];

				if (file) {

					console.log('Loading', url);

					return URL.createObjectURL(file);

				}

				return url;

			});

			for (var i = 0; i < files.length; i++) {

				scope.loadFile(files[i], manager);

			}

		}

	};

	this.loadFile = function (file, manager) {

		var filename = file.name;
		var extension = filename.split('.').pop().toLowerCase();

		var reader = new FileReader();
		reader.addEventListener('progress', function (event) {

			var size = '(' + Math.floor(event.total / 1000).format() + ' KB)';
			var progress = Math.floor((event.loaded / event.total) * 100) + '%';

			console.log('Loading', filename, size, progress);

		});

		let loadFromJson = this.loadFromJson;

		switch (extension) {

			case '3ds':

				reader.addEventListener('load', function (event) {

					var loader = new TDSLoader();
					var object = loader.parse(event.target.result);

					editor.execute(new AddObjectCommand(editor, object));

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'amf':

				reader.addEventListener('load', function (event) {

					var loader = new AMFLoader();
					var amfobject = loader.parse(event.target.result);

					editor.execute(new AddObjectCommand(editor, amfobject));

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'dae':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var loader = new ColladaLoader(manager);
					var collada = loader.parse(contents);

					collada.scene.name = filename;

					editor.addAnimation(collada.scene, collada.animations);
					editor.execute(new AddObjectCommand(editor, collada.scene));

				}, false);
				reader.readAsText(file);

				break;

			case 'drc':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var loader = new DRACOLoader();
					loader.setDecoderPath('../js/libs/draco/');
					loader.decodeDracoFile(contents, function (geometry) {

						var material = new THREE.MeshStandardMaterial();

						var mesh = new THREE.Mesh(geometry, material);
						mesh.name = filename;

						editor.execute(new AddObjectCommand(editor, mesh));

					});

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'fbx':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var loader = new FBXLoader(manager);
					var object = loader.parse(contents);

					editor.addAnimation(object, object.animations);
					editor.execute(new AddObjectCommand(editor, object));

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'glb':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var dracoLoader = new DRACOLoader();
					dracoLoader.setDecoderPath('../js/libs/draco/gltf/');

					var loader = new GLTFLoader();
					loader.setDRACOLoader(dracoLoader);
					loader.parse(contents, '', function (result) {

						var scene = result.scene;
						scene.name = filename;

						editor.addAnimation(scene, result.animations);
						editor.execute(new AddObjectCommand(editor, scene));

					});

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'gltf':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var loader;

					if (isGLTF1(contents)) {

						alert('Import of glTF asset not possible. Only versions >= 2.0 are supported. Please try to upgrade the file to glTF 2.0 using glTF-Pipeline.');

					} else {

						loader = new GLTFLoader(manager);

					}

					loader.parse(contents, '', function (result) {

						var scene = result.scene;
						scene.name = filename;

						editor.addAnimation(scene, result.animations);
						editor.execute(new AddObjectCommand(editor, scene));

					});

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'json':


				reader.addEventListener('load', function (event) {

					let contents = event.target.result;

					var data;

					try {

						data = JSON.parse(contents);
						loadFromJson(data);

					} catch (error) {

						alert(error);
						return;

					}

				}, false);
				reader.readAsText(file);

				break;

			case 'js':
			case '3geo':
			case '3mat':
			case '3obj':
			case '3scn':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					// 2.0

					if (contents.indexOf('postMessage') !== - 1) {

						var blob = new Blob([contents], { type: 'text/javascript' });
						var url = URL.createObjectURL(blob);

						var worker = new Worker(url);

						worker.onmessage = function (event) {

							event.data.metadata = { version: 2 };
							handleJSON(event.data);

						};

						worker.postMessage(Date.now());

						return;

					}

					// >= 3.0

					var data;

					try {

						data = JSON.parse(contents);

					} catch (error) {

						alert(error);
						return;

					}

					handleJSON(data);

				}, false);
				reader.readAsText(file);

				break;


			case 'kmz':

				reader.addEventListener('load', function (event) {

					var loader = new KMZLoader();
					var collada = loader.parse(event.target.result);

					collada.scene.name = filename;

					editor.execute(new AddObjectCommand(editor, collada.scene));

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'md2':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var geometry = new MD2Loader().parse(contents);
					var material = new THREE.MeshStandardMaterial({
						morphTargets: true,
						morphNormals: true
					});

					var mesh = new THREE.Mesh(geometry, material);
					mesh.mixer = new THREE.AnimationMixer(mesh);
					mesh.name = filename;

					editor.addAnimation(mesh, geometry.animations);
					editor.execute(new AddObjectCommand(editor, mesh));

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'obj':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var object = new OBJLoader().parse(contents);
					object.name = filename;
					for (let mesh of object.children) {
						if (mesh.material === undefined) {
							mesh.material = new THREE.MeshBasicMaterial()
						}
						mesh.material.wireframe = true;
						mesh.material.color.setHex(0x0703fc);
					}

					editor.execute(new AddObjectCommand(editor, object));

				}, false);
				reader.readAsText(file);

				break;

			case 'ply':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var geometry = new PLYLoader().parse(contents);
					geometry.sourceType = "ply";
					geometry.sourceFile = file.name;

					var material = new THREE.MeshStandardMaterial();

					var mesh = new THREE.Mesh(geometry, material);
					mesh.name = filename;

					editor.execute(new AddObjectCommand(editor, mesh));

				}, false);
				reader.readAsArrayBuffer(file);

				break;

			case 'stl':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var geometry = new STLLoader().parse(contents);
					geometry.sourceType = "stl";
					geometry.sourceFile = file.name;

					var material = new THREE.MeshStandardMaterial();

					var mesh = new THREE.Mesh(geometry, material);
					mesh.name = filename;

					editor.execute(new AddObjectCommand(editor, mesh));

				}, false);

				if (reader.readAsBinaryString !== undefined) {

					reader.readAsBinaryString(file);

				} else {

					reader.readAsArrayBuffer(file);

				}

				break;

			case 'svg':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var loader = new SVGLoader();
					var paths = loader.parse(contents).paths;

					//

					var group = new THREE.Group();
					group.scale.multiplyScalar(0.1);
					group.scale.y *= - 1;

					for (var i = 0; i < paths.length; i++) {

						var path = paths[i];

						var material = new THREE.MeshBasicMaterial({
							color: path.color,
							depthWrite: false
						});

						var shapes = path.toShapes(true);

						for (var j = 0; j < shapes.length; j++) {

							var shape = shapes[j];

							var geometry = new THREE.ShapeBufferGeometry(shape);
							var mesh = new THREE.Mesh(geometry, material);

							group.add(mesh);

						}

					}

					editor.execute(new AddObjectCommand(editor, group));

				}, false);
				reader.readAsText(file);

				break;

			case 'vtk':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var geometry = new VTKLoader().parse(contents);
					geometry.sourceType = "vtk";
					geometry.sourceFile = file.name;

					var material = new THREE.MeshStandardMaterial();

					var mesh = new THREE.Mesh(geometry, material);
					mesh.name = filename;

					editor.execute(new AddObjectCommand(editor, mesh));

				}, false);
				reader.readAsText(file);

				break;

			case 'wrl':

				reader.addEventListener('load', function (event) {

					var contents = event.target.result;

					var result = new VRMLLoader().parse(contents);

					editor.execute(new SetSceneCommand(editor, result));

				}, false);
				reader.readAsText(file);

				break;

			case 'zip':

				reader.addEventListener('load', function (event) {

					handleZIP(event.target.result);

				}, false);
				reader.readAsBinaryString(file);

				break;

			case 'sc':
			case 'csc':

				reader.addEventListener('load', function (event) {

					let contents = event.target.result;

					let params = new URLSearchParams();
					params.set('resource', 'newsample.sc');
					params.set('fullText', contents);

					let scGeneratorUrl = window.location.protocol + '//' + window.location.host + '/com.zipc.garden.webplatform.dsl.sc.web/xtext-service/generate-all'

					fetch(scGeneratorUrl, {
						method: 'POST',
						//mode: 'no-cors',
						headers: {
							'Accept': 'application/json',
							'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
						},
						body: params.toString()
					}).then(response => {
						return response.text();
					}).then(jsonBody => {
						return JSON.parse(jsonBody);
					}).then(result => {
						if (result.artifacts.length > 0 && result.artifacts[0].content !== undefined) {
							// console.log(result.artifacts[0].content);
							var jsonData = JSON.parse(result.artifacts[0].content);
							loadFromJson(jsonData.Scenario);
						}
					});

				}, false);
				reader.readAsText(file);

				break;

			default:

				// alert( 'Unsupported file format (' + extension +  ').' );

				break;

		}

	};

	function handleJSON(data) {

		if (data.metadata === undefined) { // 2.0

			data.metadata = { type: 'Geometry' };

		}

		if (data.metadata.type === undefined) { // 3.0

			data.metadata.type = 'Geometry';

		}

		if (data.metadata.formatVersion !== undefined) {

			data.metadata.version = data.metadata.formatVersion;

		}

		switch (data.metadata.type.toLowerCase()) {

			case 'buffergeometry':

				var loader = new THREE.BufferGeometryLoader();
				var result = loader.parse(data);

				var mesh = new THREE.Mesh(result);

				editor.execute(new AddObjectCommand(editor, mesh));

				break;

			case 'geometry':

				console.error('Loader: "Geometry" is no longer supported.');

				break;

			case 'object':

				var loader = new THREE.ObjectLoader();
				loader.setResourcePath(scope.texturePath);

				loader.parse(data, function (result) {

					if (result.isScene) {

						editor.execute(new SetSceneCommand(editor, result));

					} else {

						editor.execute(new AddObjectCommand(editor, result));

					}

				});

				break;

			case 'app':

				editor.fromJSON(data);

				break;

		}

	}

	function createFileMap(files) {

		var map = {};

		for (var i = 0; i < files.length; i++) {

			var file = files[i];
			map[file.name] = file;

		}

		return map;

	}

	function handleZIP(contents) {

		var zip = new JSZip(contents);

		// Poly

		if (zip.files['model.obj'] && zip.files['materials.mtl']) {

			var materials = new MTLLoader().parse(zip.file('materials.mtl').asText());
			var object = new OBJLoader().setMaterials(materials).parse(zip.file('model.obj').asText());
			editor.execute(new AddObjectCommand(editor, object));

		}

		//

		zip.filter(function (path, file) {

			var manager = new THREE.LoadingManager();
			manager.setURLModifier(function (url) {

				var file = zip.files[url];

				if (file) {

					console.log('Loading', url);

					var blob = new Blob([file.asArrayBuffer()], { type: 'application/octet-stream' });
					return URL.createObjectURL(blob);

				}

				return url;

			});

			var extension = file.name.split('.').pop().toLowerCase();

			switch (extension) {

				case 'fbx':

					var loader = new FBXLoader(manager);
					var object = loader.parse(file.asArrayBuffer());

					editor.execute(new AddObjectCommand(editor, object));

					break;

				case 'glb':

					var loader = new GLTFLoader();
					loader.parse(file.asArrayBuffer(), '', function (result) {

						var scene = result.scene;

						editor.addAnimation(scene, result.animations);
						editor.execute(new AddObjectCommand(editor, scene));

					});

					break;

				case 'gltf':

					var loader = new GLTFLoader(manager);
					loader.parse(file.asText(), '', function (result) {

						var scene = result.scene;

						editor.addAnimation(scene, result.animations);
						editor.execute(new AddObjectCommand(editor, scene));

					});

					break;

			}

		});

	}

	function isGLTF1(contents) {

		var resultContent;

		if (typeof contents === 'string') {

			// contents is a JSON string
			resultContent = contents;

		} else {

			var magic = THREE.LoaderUtils.decodeText(new Uint8Array(contents, 0, 4));

			if (magic === 'glTF') {

				// contents is a .glb file; extract the version
				var version = new DataView(contents).getUint32(4, true);

				return version < 2;

			} else {

				// contents is a .gltf file
				resultContent = THREE.LoaderUtils.decodeText(new Uint8Array(contents));

			}

		}

		var json = JSON.parse(resultContent);

		return (json.asset != undefined && json.asset.version[0] < 2);

	}

	this.loadFromJson = function (jsonData) {

		// editor.execute(new SetValueCommand(editor, editor.scene, 'type', 'RoadStructure'));
		// editor.execute(new SetValueCommand(editor, editor.scene, 'name', jsonData.id));
		// editor.execute(new SetValueCommand(editor, editor.scene, 'direction', jsonData.direction));
		editor.scene.type = 'RoadStructure';
		editor.scene.gid = jsonData.gid;
		editor.scene.name = jsonData.gid;
		editor.scene.description = jsonData.description;
		editor.scene.direction = jsonData.direction;

		const wireframe = editor.config.getKey('wireframe')
		// Road
		for (var roadData of jsonData.roads) {
			if (roadData === null) continue;

			if (roadData.goType === 'GORoad') {
				let goRoad = new GO.GORoad(roadData.name, roadData.length, roadData.numLanes, roadData.maxSpeed, roadData.laneWidth, roadData.points.map(p => { return new THREE.Vector3().fromArray(p) }), roadData.lanes);
				editor.addObject(goRoad);
				// editor.execute(new AddObjectCommand(editor, goRoad));
			} else {
				var roadObject = new GARDEN.RoadObject();
				roadObject.parent = editor.scene;
				roadObject.setFromMetalData(roadData);
				// editor.execute(new AddObjectCommand(editor, roadObject));
				new AddObjectCommand(editor, roadObject).execute();
				for (let child of roadObject.children) {
					if (child.type === 'Lane') {
						editor.signals.objectAdded.dispatch(child);
					}
				}
			}
		}
		for (var roadData of jsonData.roads) {
			if (roadData === null || roadData.goType === 'GORoad') continue;

			var roadObject = editor.scene.getObjectByName(roadData.id, true);
			if (roadObject.type === 'Road') {
				if (roadData !== null && roadData.connection !== undefined) {
					var connectionObject = editor.scene.getObjectByName(roadData.connection.road, true);
					if (roadData.connection.lane !== "") {
						connectionObject = connectionObject.getObjectByName(roadData.connection.lane, true);
					}
					if (connectionObject !== null && connectionObject.type === 'Road' || connectionObject.type === 'Lane') {
						// editor.execute(new SetConnectionCommand(editor, roadObject, '' + connectionObject.id, roadData.reverse || false));
						new SetConnectionCommand(editor, roadObject, '' + connectionObject.id, roadData.reverse || false).execute();
					}
				} else {
					roadObject.setFromMetalData(roadData);
				}
			}
		}

		// Junction
		if (jsonData.junctions !== undefined) {
			for (var junction of jsonData.junctions) {
				if (junction.edgeType === undefined)
					junction.edgeType = 'typeb';
				for (var entry of junction.connections) {
					var connectionObject = editor.scene.getObjectByName(entry.id);
					if (connectionObject !== undefined && connectionObject !== null) {
						entry.objectId = connectionObject.id;
					}
				}
				var junctionObject = new GARDEN.JunctionObject();
				junctionObject.parent = editor.scene;
				junctionObject.setFromMetalData(junction);

				// editor.execute(new AddObjectCommand(editor, junctionObject));
				new AddObjectCommand(editor, junctionObject).execute();


				// editor.execute(new SetJunctionConnectionCommand(editor, junctionObject, junctionObject.metalData.connections));
				new SetJunctionConnectionCommand(editor, junctionObject, junctionObject.metalData.connections).execute();
			}
		}

		// RoadMark
		if (jsonData.roadMarks !== undefined) {
			for (var roadMarkData of jsonData.roadMarks) {
				if (roadMarkData === null) continue;

				let lineSegments = [];
				if (roadMarkData.type === 'RoadMark.Line') {
					for (let s of roadMarkData.segments) {
						const roadObject = editor.scene.getObjectByName(s.lane.road, true);
						const laneObject = roadObject.getObjectByName(s.lane.lane, true);
						if (!laneObject || laneObject.type !== 'Lane') {
							lineSegments.push(new RoadMark.LineSegment([Number.MIN_SAFE_INTEGER, s.lane], s.start, s.end));
						} else {
							lineSegments.push(new RoadMark.LineSegment([laneObject.id, s.lane], s.start, s.end));
						}
					}
					let roadMarkLine = new RoadMark.Line(roadMarkData.mode, roadMarkData.parameters, lineSegments);
					roadMarkLine.name = roadMarkData.name;
					editor.computeRoadMarkLineGeometry(roadMarkLine);
					// editor.execute(new AddObjectCommand(editor, roadMarkLine));
					new AddObjectCommand(editor, roadMarkLine).execute();
				} else if (roadMarkData.type === 'RoadMark.Sign') {
					let roadMarkSign = new RoadMark.Sign(roadMarkData.parameters);
					roadMarkSign.name = roadMarkData.name;
					if (roadMarkData.parameters.mode === 'stop_line') {
						const roadObject = editor.scene.getObjectByName(roadMarkData.lane.road, true);
						// editor.execute(new SetRoadMarkSignValueCommand(editor, roadMarkSign, 'road', roadObject));
						new SetRoadMarkSignValueCommand(editor, roadMarkSign, 'road', roadObject).execute();
					} else {
						const roadObject = editor.scene.getObjectByName(roadMarkData.lane.road, true);
						const laneObject = roadObject.getObjectByName(roadMarkData.lane.lane, true);
						// editor.execute(new SetRoadMarkSignValueCommand(editor, roadMarkSign, 'lane', laneObject));
						new SetRoadMarkSignValueCommand(editor, roadMarkSign, 'lane', laneObject).execute();
					}
				} else if (roadMarkData.type === 'RoadMark.Signal.TrafficLight') {
					let trafficLight = new RoadMark.TrafficLight(roadMarkData.parameters);
					trafficLight.name = roadMarkData.name;
					// editor.execute(new AddObjectCommand(editor, trafficLight));
					new AddObjectCommand(editor, trafficLight).execute();

					if (roadMarkData.lane.road !== '') {
						const roadObject = editor.scene.getObjectByName(roadMarkData.lane.road, true);
						// editor.execute(new SetTrafficLightValueCommand(editor, trafficLight, 'road', roadObject));
						new SetTrafficLightValueCommand(editor, trafficLight, 'road', roadObject).execute();
					}
				} else if (roadMarkData.type === 'RoadMark.Crosswalk') {
					let roadMarkCrosswalk = new RoadMark.Crosswalk(roadMarkData.parameters);
					roadMarkCrosswalk.name = roadMarkData.name;
					// editor.execute(new AddObjectCommand(editor, roadMarkCrosswalk));
					new AddObjectCommand(editor, roadMarkCrosswalk).execute();
					roadMarkCrosswalk.sprite01.visible = false;
					roadMarkCrosswalk.sprite02.visible = false;

					if (roadMarkData.lane.road !== '') {
						const roadObject = editor.scene.getObjectByName(roadMarkData.lane.road, true);
						// editor.execute(new SetRoadMarkCrossWalkValueCommand(editor, roadMarkCrosswalk, 'road', roadObject));
						new SetRoadMarkCrossWalkValueCommand(editor, roadMarkCrosswalk, 'road', roadObject).execute();
					}
				} else if (roadMarkData.type === 'RoadMark.TurningMark') {
					let roadMarkTurningMark = new RoadMark.TurningMark(roadMarkData.parameters);
					roadMarkTurningMark.name = roadMarkData.name;
					new AddObjectCommand(editor, roadMarkTurningMark).execute();

					if (roadMarkData.lane.road !== '') {
						const roadObject = editor.scene.getObjectByName(roadMarkData.lane.road, true);
						new SetRoadMarkTurningMarkValueCommand(editor, roadMarkTurningMark, 'road', roadObject).execute();
					}
				}
			}
		}

		if (jsonData.routes !== undefined) {
			for (var route of jsonData.routes) {
				var color = route.color;
				var routeRouteData = editor.computeRouteCurve4(route.gid, '#' + new THREE.Color(color).getHexString(), route.routes[0]);
				route.routeData = routeRouteData;
				var routeObject = new GARDEN.RouteObject(route.gid, color).setFromMetalData(route);

				// editor.execute(new AddObjectCommand(editor, routeObject));
				new AddObjectCommand(editor, routeObject).execute();
			}
		}

		if (jsonData.trajectories !== undefined) {
			for (var trajectory of jsonData.trajectories) {
				var trajectoryObject = new GARDEN.TrajectoryObject();

				if (trajectory.rawData !== undefined) {
					let rawData = JSON.parse(JSON.stringify(trajectory.rawData));;
					let wayPoints = [];
					for (let key in rawData) {
						if (key === 'color') continue;
						const cur = rawData[key];
						wayPoints.push({ x: cur.x, y: cur.y + 1, z: cur.z });
					}
					trajectory.routes.push({ "accel": 0.0, "points": wayPoints });
					delete trajectory['rawData'];
					trajectoryObject.userData.rawData = rawData;
				} else {
					trajectory.basePointsArray = [];
					if (trajectory.routes !== undefined) {
						for (var entity of trajectory.routes) {
							var basePoints = [];
							for (var point of entity.points) {
								basePoints.push({ x: point.x, y: point.y, z: point.z });
							}
							trajectory.basePointsArray.push(basePoints);
						}
					}
				}
				trajectoryObject.setFromMetalData(trajectory);

				// editor.execute(new AddObjectCommand(editor, trajectoryObject));
				new AddObjectCommand(editor, trajectoryObject).execute();
			}
		}

		var loader = new THREE.ObjectLoader();
		loader.setResourcePath(scope.texturePath);

		if (jsonData.backgroundMap !== undefined && jsonData.backgroundMap !== null) {
			var isVisible = jsonData.backgroundMap.visible === true;

			var boundary = {
				n: jsonData.backgroundMap.boundary.n,
				e: jsonData.backgroundMap.boundary.e,
				s: jsonData.backgroundMap.boundary.s,
				w: jsonData.backgroundMap.boundary.w
			};

			editor.signals.scenarioBackground00Changed.dispatch(
				boundary,
				isVisible,
				isVisible
			);
		}

		if (jsonData.backgroundPlane01 !== undefined && jsonData.backgroundPlane01 !== null) {
			loader.parse(jsonData.backgroundPlane01, function (result) {
				editor.signals.scenarioBackground01Changed.dispatch(
					'Texture',
					result.material.map,
					result.userData.is2DVisible || false
				);

				var newbackground01Position = result.position.clone();
				var newbackground01Rotation = result.rotation.clone();
				var newbackground01Scale = result.scale;
				editor.signals.scenarioBackground01PositionChanged.dispatch(newbackground01Position);
				editor.signals.scenarioBackground01RotationChanged.dispatch(newbackground01Rotation);
				editor.signals.scenarioBackground01ScaleChanged.dispatch(newbackground01Scale);
			});
		}

		if (jsonData.backgroundPlane02 !== undefined && jsonData.backgroundPlane02 !== null) {
			loader.parse(jsonData.backgroundPlane02, function (result) {
				editor.signals.scenarioBackground02Changed.dispatch(
					'Texture',
					result.material.map,
					result.userData.is2DVisible || false
				);

				var newbackground02Position = result.position.clone();
				var newbackground02Rotation = result.rotation.clone();
				var newbackground02Scale = result.scale;
				editor.signals.scenarioBackground02PositionChanged.dispatch(newbackground02Position);
				editor.signals.scenarioBackground02RotationChanged.dispatch(newbackground02Rotation);
				editor.signals.scenarioBackground02ScaleChanged.dispatch(newbackground02Scale);
			});
		}

	}

};

export { Loader };
