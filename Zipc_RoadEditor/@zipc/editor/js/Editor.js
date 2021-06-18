/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { Config } from './Config.js';
import { Loader } from './Loader.js';
import { History as _History } from './History.js';
import { Strings } from './Strings.js';
import { Storage as _Storage } from './Storage.js';

import * as GARDEN from '../../garden-objects/js/roads/GardenObjects.js';
import * as RoadMark from '../../garden-objects/js/roadmarks/RoadMarkObjects';

var Editor = function () {

	this.DEFAULT_PERSPECTIVE_CAMERA = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 10, 50000);
	this.DEFAULT_PERSPECTIVE_CAMERA.name = 'PerspectiveCamera';
	this.DEFAULT_PERSPECTIVE_CAMERA.position.set(0, 500, 200);
	//this.DEFAULT_PERSPECTIVE_CAMERA.lookAt(new THREE.Vector3());

	this.DEFAULT_ORTHOGRAPHIC_CAMERA = new THREE.OrthographicCamera(window.innerWidth / - 2, window.innerWidth / 2, window.innerHeight / 2, window.innerHeight / - 2, 10, 50000);
	this.DEFAULT_ORTHOGRAPHIC_CAMERA.name = 'OrthographicCamera';
	this.DEFAULT_ORTHOGRAPHIC_CAMERA.position.set(0, 500, 200);
	//this.DEFAULT_ORTHOGRAPHIC_CAMERA.lookAt(new THREE.Vector3());

	var Signal = signals.Signal;

	this.signals = {

		startPreview: new Signal(),

		searchScreenShow: new Signal(),
		searchScreenHide: new Signal(),

		mapManageScreenShow: new Signal(),
		mapManageScreenHide: new Signal(),

		loadingScreenShow: new Signal(),
		loadingScreenHide: new Signal(),

		// GARDEN
		scenarioDirectionChanged: new Signal(),

		scenarioBackground00Changed: new Signal(),
		scenarioBackground00Generate: new Signal(),

		scenarioBackground01Changed: new Signal(),
		scenarioBackground01PositionChanged: new Signal(),
		scenarioBackground01RotationChanged: new Signal(),
		scenarioBackground01ScaleChanged: new Signal(),

		scenarioBackground02Changed: new Signal(),
		scenarioBackground02PositionChanged: new Signal(),
		scenarioBackground02RotationChanged: new Signal(),
		scenarioBackground02ScaleChanged: new Signal(),

		gardenRoadStructureChanged: new Signal(),

		// script

		editScript: new Signal(),

		// player

		startPlayer: new Signal(),
		stopPlayer: new Signal(),

		// notifications

		editorCleared: new Signal(),

		savingStarted: new Signal(),
		savingFinished: new Signal(),

		transformModeChanged: new Signal(),
		snapChanged: new Signal(),
		spaceChanged: new Signal(),
		rendererChanged: new Signal(),
		rendererUpdated: new Signal(),


		sceneBackgroundChanged: new Signal(),
		sceneGraphChanged: new Signal(),
		sceneRendered: new Signal(),

		cameraChanged: new Signal(),

		geometryChanged: new Signal(),

		objectSelected: new Signal(),
		objectFocused: new Signal(),

		objectAdded: new Signal(),
		objectChanged: new Signal(),
		objectRemoved: new Signal(),

		cameraAdded: new Signal(),
		cameraRemoved: new Signal(),

		helperAdded: new Signal(),
		helperRemoved: new Signal(),

		materialAdded: new Signal(),
		materialChanged: new Signal(),
		materialRemoved: new Signal(),

		scriptAdded: new Signal(),
		scriptChanged: new Signal(),
		scriptRemoved: new Signal(),

		windowResize: new Signal(),

		showGridChanged: new Signal(),
		refreshSidebarObject3D: new Signal(),
		historyChanged: new Signal(),

		viewportCameraChanged: new Signal()

	};

	this.config = new Config();
	this.history = new _History(this);
	this.storage = new _Storage();
	this.strings = new Strings(this.config);

	this.loader = new Loader(this);

	this.camera = this.DEFAULT_PERSPECTIVE_CAMERA.clone();

	this.scene = new GARDEN.RoadStructure(this);
	this.scene.type = 'RoadStructure';
	this.scene.gid = 'RoadStructure.NEW';
	this.scene.background = new THREE.Color(0xeeeeee);

	var ambientLight = new THREE.AmbientLight(0xf0f0f0);
	ambientLight.name = 'AmbientLight';
	this.scene.add(ambientLight);
	var spotLight = new THREE.SpotLight(0xffffff, 1);
	spotLight.name = 'SpotLight';
	spotLight.position.set(0, 1500, 200);
	spotLight.angle = Math.PI * 0.2;
	spotLight.castShadow = true;
	spotLight.shadow.camera.near = 200;
	spotLight.shadow.camera.far = 2000;
	spotLight.shadow.bias = - 0.000222;
	spotLight.shadow.mapSize.width = 1024;
	spotLight.shadow.mapSize.height = 1024;
	this.scene.add(spotLight);

	this.sceneHelpers = new THREE.Scene();

	this.object = {};
	this.geometries = {};
	this.materials = {};
	this.textures = {};
	this.scripts = {};

	this.materialsRefCounter = new Map(); // tracks how often is a material used by a 3D object

	this.animations = {};
	this.mixer = new THREE.AnimationMixer(this.scene);

	this.selected = null;
	this.helpers = {};

	this.cameras = {};
	this.viewportCamera = this.camera;

	this.addCamera(this.camera);
	this.addCamera(this.DEFAULT_ORTHOGRAPHIC_CAMERA.clone());
};

Editor.prototype = {

	setScene: function (scene) {

		this.scene.uuid = scene.uuid;
		this.scene.name = scene.name;

		this.scene.background = (scene.background !== undefined && scene.background !== null) ? scene.background.clone() : null;

		if (scene.fog !== undefined && scene.fog !== null) this.scene.fog = scene.fog.clone();

		this.scene.userData = JSON.parse(JSON.stringify(scene.userData));

		// avoid render per object

		this.signals.sceneGraphChanged.active = false;

		while (scene.children.length > 0) {

			this.addObject(scene.children[0]);

		}

		this.signals.sceneGraphChanged.active = true;
		this.signals.sceneGraphChanged.dispatch();

	},

	//

	addObject: function (object, parent, index) {

		var scope = this;

		object.traverse(function (child) {

			if (child.type === 'Sprite') return;

			if (child.geometry !== undefined) scope.addGeometry(child.geometry);
			if (child.material !== undefined) scope.addMaterial(child.material);

			scope.addCamera(child);
			scope.addHelper(child);

		});

		if (parent === undefined) {

			this.scene.add(object);

		} else {

			parent.children.splice(index, 0, object);
			object.parent = parent;

		}

		this.signals.objectAdded.dispatch(object);
		this.signals.sceneGraphChanged.dispatch();

	},

	moveObject: function (object, parent, before) {

		if (parent === undefined) {

			parent = this.scene;

		}

		parent.add(object);

		// sort children array

		if (before !== undefined) {

			var index = parent.children.indexOf(before);
			parent.children.splice(index, 0, object);
			parent.children.pop();

		}

		this.signals.sceneGraphChanged.dispatch();

	},

	nameObject: function (object, name) {

		object.name = name;
		this.signals.sceneGraphChanged.dispatch();

	},

	removeObject: function (object) {

		if (object.parent === null) return; // avoid deleting the camera or scene

		var scope = this;

		object.traverse(function (child) {

			scope.removeCamera(child);
			scope.removeHelper(child);

			if (child.material !== undefined) scope.removeMaterial(child.material);

		});

		object.parent.remove(object);

		this.signals.objectRemoved.dispatch(object);
		this.signals.sceneGraphChanged.dispatch();

	},

	addGeometry: function (geometry) {

		this.geometries[geometry.uuid] = geometry;

	},

	setGeometryName: function (geometry, name) {

		geometry.name = name;
		this.signals.sceneGraphChanged.dispatch();

	},

	addMaterial: function (material) {

		if (Array.isArray(material)) {

			for (var i = 0, l = material.length; i < l; i++) {

				this.addMaterialToRefCounter(material[i]);

			}

		} else {

			this.addMaterialToRefCounter(material);

		}

		this.signals.materialAdded.dispatch();

	},

	addMaterialToRefCounter: function (material) {

		var materialsRefCounter = this.materialsRefCounter;

		var count = materialsRefCounter.get(material);

		if (count === undefined) {

			materialsRefCounter.set(material, 1);
			this.materials[material.uuid] = material;

		} else {

			count++;
			materialsRefCounter.set(material, count);

		}

	},

	removeMaterial: function (material) {

		if (Array.isArray(material)) {

			for (var i = 0, l = material.length; i < l; i++) {

				this.removeMaterialFromRefCounter(material[i]);

			}

		} else {

			this.removeMaterialFromRefCounter(material);

		}

		this.signals.materialRemoved.dispatch();

	},

	removeMaterialFromRefCounter: function (material) {

		var materialsRefCounter = this.materialsRefCounter;

		var count = materialsRefCounter.get(material);
		count--;

		if (count === 0) {

			materialsRefCounter.delete(material);
			delete this.materials[material.uuid];

		} else {

			materialsRefCounter.set(material, count);

		}

	},

	getMaterialById: function (id) {

		var material;
		var materials = Object.values(this.materials);

		for (var i = 0; i < materials.length; i++) {

			if (materials[i].id === id) {

				material = materials[i];
				break;

			}

		}

		return material;

	},

	setMaterialName: function (material, name) {

		material.name = name;
		this.signals.sceneGraphChanged.dispatch();

	},

	addTexture: function (texture) {

		this.textures[texture.uuid] = texture;

	},

	addAnimation: function (object, animations) {

		if (animations.length > 0) {

			this.animations[object.uuid] = animations;

		}

	},

	//

	addCamera: function (camera) {

		if (camera.isCamera) {

			this.cameras[camera.uuid] = camera;

			this.signals.cameraAdded.dispatch(camera);

		}

	},

	removeCamera: function (camera) {

		if (this.cameras[camera.uuid] !== undefined) {

			delete this.cameras[camera.uuid];

			this.signals.cameraRemoved.dispatch(camera);

		}

	},

	//

	addHelper: function () {

		var geometry = new THREE.SphereBufferGeometry(2, 4, 2);
		var material = new THREE.MeshBasicMaterial({ color: 0xff0000, visible: false });

		return function (object) {

			var helper;

			if (object.isCamera) {

				helper = new THREE.CameraHelper(object);

			} else if (object.isPointLight) {

				helper = new THREE.PointLightHelper(object, 1);

			} else if (object.isDirectionalLight) {

				helper = new THREE.DirectionalLightHelper(object, 1);

			} else if (object.isSpotLight) {

				helper = new THREE.SpotLightHelper(object, 1);

			} else if (object.isHemisphereLight) {

				helper = new THREE.HemisphereLightHelper(object, 1);

			} else if (object.isSkinnedMesh) {

				helper = new THREE.SkeletonHelper(object.skeleton.bones[0]);

			} else {

				// no helper for this object type
				return;

			}

			var picker = new THREE.Mesh(geometry, material);
			picker.name = 'picker';
			picker.userData.object = object;
			helper.add(picker);

			this.sceneHelpers.add(helper);
			this.helpers[object.id] = helper;

			this.signals.helperAdded.dispatch(helper);

		};

	}(),

	removeHelper: function (object) {

		if (this.helpers[object.id] !== undefined) {

			var helper = this.helpers[object.id];
			helper.parent.remove(helper);

			delete this.helpers[object.id];

			this.signals.helperRemoved.dispatch(helper);

		}

	},

	//

	addScript: function (object, script) {

		if (this.scripts[object.uuid] === undefined) {

			this.scripts[object.uuid] = [];

		}

		this.scripts[object.uuid].push(script);

		this.signals.scriptAdded.dispatch(script);

	},

	removeScript: function (object, script) {

		if (this.scripts[object.uuid] === undefined) return;

		var index = this.scripts[object.uuid].indexOf(script);

		if (index !== - 1) {

			this.scripts[object.uuid].splice(index, 1);

		}

		this.signals.scriptRemoved.dispatch(script);

	},

	getObjectMaterial: function (object, slot) {

		var material = object.material;

		if (Array.isArray(material) && slot !== undefined) {

			material = material[slot];

		}

		return material;

	},

	setObjectMaterial: function (object, slot, newMaterial) {

		if (Array.isArray(object.material) && slot !== undefined) {

			object.material[slot] = newMaterial;

		} else {

			object.material = newMaterial;

		}

	},

	setViewportCamera: function (uuid) {

		this.camera = this.cameras[uuid];
		this.viewportCamera = this.cameras[uuid];
		//this.viewportCamera.up.copy(new THREE.Vector3(0, 1, 0));
		this.signals.viewportCameraChanged.dispatch(this.viewportCamera);

	},

	//

	select: function (object) {

		if (this.selected === object) return;

		var uuid = null;

		if (object !== null) {

			uuid = object.uuid;

		}

		this.selected = object;

		this.config.setKey('selected', uuid);
		this.signals.objectSelected.dispatch(object);

	},

	selectById: function (id) {

		/*if (id === this.camera.id) {

			this.select(this.camera);
			return;

		}*/

		this.select(this.scene.getObjectById(id, true));

	},

	selectByUuid: function (uuid) {

		var scope = this;

		this.scene.traverse(function (child) {

			if (child.uuid === uuid) {

				scope.select(child);

			}

		});

	},

	deselect: function () {

		this.select(null);

	},

	focus: function (object) {

		if (object !== undefined) {

			this.signals.objectFocused.dispatch(object);

		}

	},

	focusById: function (id) {

		this.focus(this.scene.getObjectById(id, true));

	},

	clear: function () {

		this.history.clear();
		this.storage.clear();

		if (this.camera.isPerspectiveCamera) {

			this.camera.copy(this.DEFAULT_PERSPECTIVE_CAMERA);
			//controls.enableRotate = true;

		} else if (this.camera.isOrthographicCamera) {

			this.camera.copy(this.DEFAULT_ORTHOGRAPHIC_CAMERA);
			//controls.enableRotate = false;

		}
		this.scene.gid = "RoadStructure.NEW";
		this.scene.direction = "left";
		this.scene.userData = {};
		this.scene.background = new THREE.Color(0xeeeeee);
		this.scene.fog = null;

		var objects = this.scene.children;

		while (objects.length > 0) {

			this.removeObject(objects[0]);

		}


		var mapHelper = this.backgroundMap;
		mapHelper.loadOverpassDataByBoundary({ n: 0, e: 0, s: 0, w: 0 });
		mapHelper.visible = false;

		this.signals.scenarioBackground01Changed.dispatch('Texture', null, false);
		this.signals.scenarioBackground02Changed.dispatch('Texture', null, false);

		this.timelineData = null;

		var ambientLight = new THREE.AmbientLight(0xf0f0f0);
		ambientLight.name = 'AmbientLight';
		this.scene.add(ambientLight);
		var spotLight = new THREE.SpotLight(0xffffff, 1.5);
		spotLight.name = 'SpotLight';
		spotLight.position.set(0, 1500, 200);
		spotLight.angle = Math.PI * 0.2;
		spotLight.castShadow = true;
		spotLight.shadow.camera.near = 200;
		spotLight.shadow.camera.far = 2000;
		spotLight.shadow.bias = - 0.000222;
		spotLight.shadow.mapSize.width = 1024;
		spotLight.shadow.mapSize.height = 1024;
		this.scene.add(spotLight);

		this.geometries = {};
		this.materials = {};
		this.textures = {};
		this.scripts = {};

		this.materialsRefCounter.clear();

		this.animations = {};
		this.mixer.stopAllAction();

		this.deselect();

		this.signals.editorCleared.dispatch();

	},

	//

	fromJSON: function (json) {

		var scope = this;

		var loader = new THREE.ObjectLoader();
		var camera = loader.parse(json.camera);

		this.camera.copy(camera);
		this.camera.aspect = this.DEFAULT_PERSPECTIVE_CAMERA.aspect;
		this.camera.updateProjectionMatrix();

		this.history.fromJSON(json.history);
		this.scripts = json.scripts;

		loader.parse(json.scene, function (scene) {

			scope.setScene(scene);

		});

	},

	toJSON: function () {

		// scripts clean up

		var scene = this.scene;
		var scripts = this.scripts;

		for (var key in scripts) {

			var script = scripts[key];

			if (script.length === 0 || scene.getObjectByProperty('uuid', key) === undefined) {

				delete scripts[key];

			}

		}

		//

		return {

			metadata: {},
			project: {
				shadows: this.config.getKey('project/renderer/shadows'),
				vr: this.config.getKey('project/vr')
			},
			camera: this.camera.toJSON(),
			scene: this.scene.toJSON(),
			scripts: this.scripts,
			history: this.history.toJSON()

		};

	},

	objectByUuid: function (uuid) {

		return this.scene.getObjectByProperty('uuid', uuid, true);

	},

	execute: function (cmd, optionalName) {

		this.history.execute(cmd, optionalName);

	},

	undo: function () {

		this.history.undo();

	},

	redo: function () {

		this.history.redo();

	},

	// ##############################################
	computeRouteCurve4: function (name, color, routeData) {

		const scene = this.scene;

		const fragments = [];
		const positions = [];
		const velocities = [];

		var startEntities = [];
		var midEntities = [];
		var goalEntities = [];

		if (routeData !== undefined && routeData.entities.length > 0) {
			startEntities = routeData.entities.filter(route => route !== null && route.lane !== null && route.type === 'start');
			midEntities = routeData.entities.filter(route => route !== null && route.lane !== null && route.type === 'mid');
			goalEntities = routeData.entities.filter(route => route !== null && route.lane !== null && route.type === 'goal');
			if (startEntities.length === 0 || goalEntities === 0) {
				return { fragments: [], points: [], velocities: [], wayPoints: [] };
			}
		}


		var lane, laneShift, laneChangeStart, laneChangeEnd, laneVelocity;
		// Start
		for (var routeEntity of startEntities) {
			lane = scene.getObjectByName(routeEntity.lane.road).getObjectByName(routeEntity.lane.lane);

			if (lane === undefined || lane.type !== 'Lane') {
				break;
			}

			laneShift = routeEntity.shift || -1;
			laneChangeStart = routeEntity.lanechange_start || -1;
			laneChangeEnd = routeEntity.lanechange_end || -1;
			laneVelocity = routeEntity.velocity || 0.0;

			if (laneShift !== -1) {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: laneShift, velocity: laneVelocity });
			} else {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: 0, velocity: laneVelocity });
			}
			if (laneChangeStart !== -1) {
				fragments.push({ type: "left_change", road: lane.parent.id, lane: lane.name, index: laneChangeStart, velocity: laneVelocity });
			}
			if (laneChangeEnd !== -1) {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: laneChangeEnd, velocity: laneVelocity });
			}

			break;
		}

		// Mid
		var routeEntity;
		for (var i = 0; i < midEntities.length; i++) {
			routeEntity = midEntities[i];
			lane = scene.getObjectByName(routeEntity.lane.road);
			if (routeEntity.lane.lane !== "") {
				lane = lane.getObjectByName(routeEntity.lane.lane);
			}

			if (lane === undefined || !(lane.type === 'Lane' || lane.type === 'Junction')) {
				break;
			}

			if (lane.type === 'Junction') {
				var currentIndex = routeData.entities.indexOf(routeEntity);
				const prevOne = routeData.entities[currentIndex - 1].lane;
				const nextOne = routeData.entities[currentIndex + 1].lane;
				var laneName = `${prevOne.road}_${prevOne.lane}-${nextOne.road}_${nextOne.lane}`;
				lane = scene.getObjectByName(routeEntity.lane.road).getObjectByName(laneName);

				if (lane === undefined || lane.type !== 'JunctionLane') {
					break;
				}
			}

			laneChangeStart = routeEntity.lanechange_start || -1;
			laneChangeEnd = routeEntity.lanechange_end || -1;
			laneVelocity = routeEntity.velocity || 0.0;

			if (laneChangeStart !== -1) {
				fragments.push({ type: "left_change", road: lane.parent.id, lane: lane.name, index: laneChangeStart, velocity: laneVelocity });
			}
			if (laneChangeEnd !== -1) {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: laneChangeEnd, velocity: laneVelocity });
			}
			if (laneChangeStart === -1 && laneChangeEnd === -1) {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: 0, velocity: laneVelocity });
			}
		}

		// Goal
		for (var routeEntity of goalEntities) {
			lane = scene.getObjectByName(routeEntity.lane.road).getObjectByName(routeEntity.lane.lane);

			if (lane === undefined || lane.type !== 'Lane') {
				break;
			}

			laneShift = routeEntity.shift || -1;
			laneChangeStart = routeEntity.lanechange_start || -1;
			laneChangeEnd = routeEntity.lanechange_end || -1;
			laneVelocity = routeEntity.velocity || 0.0;

			if (laneChangeStart !== -1) {
				fragments.push({ type: "left_change", road: lane.parent.id, lane: lane.name, index: laneChangeStart, velocity: laneVelocity });
			}
			if (laneChangeEnd !== -1) {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: laneChangeEnd, velocity: laneVelocity });
			}
			if (laneShift !== -1) {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: laneShift, velocity: laneVelocity });
			} else {
				fragments.push({ type: "along", road: lane.parent.id, lane: lane.name, index: Number.MAX_SAFE_INTEGER, velocity: laneVelocity });
			}

			break;
		}

		// Compute
		var f1, f2;
		var i1, i2;
		var l1, l2, bc1, bc2, c1, c2, bpts1, bpts2, pts1, pts2;
		var lm1, lm2;
		var fp = null;
		for (var i = 0; i < fragments.length - 1; i++) {
			f1 = fragments[i];
			f2 = fragments[i + 1];
			//l1 = scene.getObjectById(f1.lane, true);
			//l2 = scene.getObjectById(f2.lane, true);
			l1 = scene.getObjectById(f1.road).getObjectByName(f1.lane);
			l2 = scene.getObjectById(f2.road).getObjectByName(f2.lane);
			// c1 = l1.curve3.clone();
			// c2 = l2.curve3.clone();
			bc1 = l1.curve3.clone();
			bc2 = l2.curve3.clone();
			c1 = l1.type === 'JunctionLane' ? l1.curve3.clone() : l1.waypointLineCurve3.points.length > 0 ? l1.waypointLineCurve3.clone() : l1.roadMarkLineCurve3.clone();
			c2 = l2.type === 'JunctionLane' ? l2.curve3.clone() : l2.waypointLineCurve3.points.length > 0 ? l2.waypointLineCurve3.clone() : l2.roadMarkLineCurve3.clone();
			bpts1 = bc1.points === undefined || bc1.points.length === 0 ? bc1.getPoints(bc1.arcLengthDivisions) : bc1.points;
			bpts2 = bc2.points === undefined || bc2.points.length === 0 ? bc2.getPoints(bc2.arcLengthDivisions) : bc2.points;
			pts1 = c1.points === undefined || c1.points.length === 0 ? c1.getPoints(c1.arcLengthDivisions) : c1.points;
			pts2 = c2.points === undefined || c2.points.length === 0 ? c2.getPoints(c2.arcLengthDivisions) : c2.points;
			lm1 = l1.matrixWorld;
			lm2 = l2.matrixWorld;
			i1 = Math.min(f1.index, c1.arcLengthDivisions);
			i2 = Math.min(f2.index, c2.arcLengthDivisions) + (fragments.length - i === 2 ? 1 : 0);
			if (f1.road !== f2.road) {
				if (f1.type === 'along') {
					f1.index = positions.length;

					for (var k = i1; k < c1.arcLengthDivisions; k++) {
						if (fp === null) {
							fp = new THREE.Vector3().copy(pts1[k].clone().applyMatrix4(lm1));
						}
						positions.push(pts1[k].clone().applyMatrix4(lm1).sub(fp));
						velocities.push(f1.velocity);
					}
					for (var k = 0; k < i2; k++) {
						if (fp === null) {
							fp = new THREE.Vector3().copy(pts1[i]).applyMatrix4(lm1);
						}
						positions.push(pts2[k].clone().applyMatrix4(lm2).sub(fp));
						velocities.push(f1.velocity);
					}
				} else { // type === 'change'

					var vec1 = pts1[i1].clone().applyMatrix4(lm1);
					var vec2 = pts2[i2].clone().applyMatrix4(lm2);
					if (vec1.distanceTo(vec2) <= 0.1) {
						f1.type === 'along'
						f1.index = positions.length;

						var normal2 = new THREE.Vector3();
						var position2 = new THREE.Vector3();
						var splineTube = c1.computeFrenetFrames(c1.arcLengthDivisions);
						for (var k = i2; k < f2.index; k++) {
							if (fp === null) {
								fp = new THREE.Vector3().copy(pts2[k]).applyMatrix4(lm2);
							}
							position2.copy(pts2[k].clone().applyMatrix4(lm2));
							positions.push(new THREE.Vector3(position2.x, position2.y + 1, position2.z).sub(fp));
							velocities.push(f2.velocity);
						}
					}
				}
			} else {
				if (f1.type === 'along' && f1.lane === f2.lane) {
					f1.index = positions.length;

					for (var k = i1; k < i2; k++) {
						if (fp === null) {
							fp = new THREE.Vector3().copy(pts1[k]).applyMatrix4(lm1);
						}
						positions.push(pts1[k].clone().applyMatrix4(lm1).sub(fp));
						velocities.push(f1.velocity);
					}
				} else {
					var w1 = l1.laneOffset;
					var w2 = l2.laneOffset;
					var offset1 = pts1[i2 - 1].z - bpts1[i2 - 1].z;
					var offset2 = pts2[i2 - 1].z - bpts2[i2 - 1].z;
					var widthOffset = Math.abs(offset1 + offset2) * (w2 - w1 < 0 ? -1 : 1) * (scene.direction === l1.lanePosition ? -1 : 1);

					var lengthOffset = i2 - i1;
					var xv0 = new THREE.Vector3(0, 0, 0);
					var xv3 = new THREE.Vector3(lengthOffset, 0, widthOffset);
					var xv1 = new THREE.Vector3((xv0.x + xv3.x) / 2, xv0.y, xv0.z);
					var xv2 = new THREE.Vector3((xv0.x + xv3.x) / 2, xv0.y, xv3.z);
					var xlaneChangeCurve3 = new THREE.CubicBezierCurve3(xv0, xv1, xv2, xv3);

					var w1 = l1.laneOffset;
					var w2 = l2.laneOffset;
					if (w1 < 0 && w2 < 0) {
						f1.type = w1 > w2 ? "left_change" : "right_change";
					}
					if (w1 > 0 && w2 > 0) {
						f1.type = w1 > w2 ? "right_change" : "left_change";
					}
					f1.index = positions.length;

					var normal2 = new THREE.Vector3();
					var position2 = new THREE.Vector3();
					var splineTube = c1.computeFrenetFrames(c1.arcLengthDivisions);
					for (var k = i1; k < i2; k++) {
						if (fp === null) {
							fp = new THREE.Vector3().copy(pts1[k]).applyMatrix4(lm1);
						}
						normal2.copy(splineTube.normals[k]).multiplyScalar(xlaneChangeCurve3.getPointAt((k - i1) / lengthOffset).z);
						position2.copy(pts1[k].clone().add(normal2).applyMatrix4(lm1));
						positions.push(new THREE.Vector3(position2.x, position2.y, position2.z).sub(fp));
						velocities.push(f1.velocity);
					}
				}
			}
		}

		if (positions.length === 0) return null;

		var curve3 = new THREE.CatmullRomCurve3(positions);
		const splineFrames = curve3.computeFrenetFrames(positions.length, false);

		splineFrames.position = fp;
		splineFrames.points = positions;
		splineFrames.velocities = velocities;
		splineFrames.wayPoints = [];
		splineFrames.fragments = fragments;
		splineFrames.trackInfo = [
			{
				type: THREE.BooleanKeyframeTrack,
				propertyPath: `${name}_Mesh01.visible`,
				initialValue: [false],
				interpolation: THREE.InterpolateSmooth
			},
			{
				type: THREE.VectorKeyframeTrack,
				propertyPath: name + '_Mesh01.position',
				initialValue: [0, 0, 0],
				interpolation: THREE.InterpolateSmooth
			}, {
				type: THREE.QuaternionKeyframeTrack,
				propertyPath: name + '_Mesh01.quaternion',
				initialValue: [0, 0, 0, 1],
				interpolation: THREE.InterpolateLinear
			}
		];
		splineFrames.trackLayer = {
			"name": name,
			"values": [],
			"tmpValue": 0,
			"_color": color
		};
		splineFrames.timeLine = [];

		if (splineFrames.points.length > 0) {

			var p1 = new THREE.Vector3(), p2 = new THREE.Vector3();
			var l, v, t = 0, prev_t = Math.trunc(t);

			var tan2 = new THREE.Vector3();
			var ptl = new THREE.Vector3();
			var qtl = new THREE.Quaternion();
			tan2 = splineFrames.tangents[0];
			ptl.copy(splineFrames.points[0]);
			qtl.copy(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(1, 0, 0), splineFrames.tangents[0]));
			//qtl.copy(new THREE.Quaternion().setFromUnitVectors(splineFrames.tangents[0], splineFrames.tangents[1]));
			//qtl.copy(CurveUtils.setQuaternionFromDirection(splineFrames.tangents[0]));
			splineFrames.wayPoints.push({ 'x': splineFrames.points[0].x + fp.x, 'y': splineFrames.points[0].y + fp.y, 'z': splineFrames.points[0].z + fp.z, 'v': splineFrames.velocities[0] });
			splineFrames.timeLine.push({ time: t, position: { x: ptl.x, y: ptl.y, z: ptl.z }, quaternion: { x: qtl.x, y: qtl.y, z: qtl.z, w: qtl.w } });

			splineFrames.trackLayer["values"].push({ "time": t, "value": ptl.x, "_color": color, "tween": splineFrames.fragments[0].type });

			for (var i = 1; i < splineFrames.points.length; i++) {
				p1.set(splineFrames.points[i].x, splineFrames.points[i].y, splineFrames.points[i].z);
				p2.set(splineFrames.points[i - 1].x, splineFrames.points[i - 1].y, splineFrames.points[i - 1].z);
				l = p1.distanceTo(p2);
				v = splineFrames.velocities[i] / 3.6;

				// 経過時間を求める
				if (v > 0) {
					t += l / v;
				} else {
					t = 0;
				}

				tan2 = splineFrames.tangents[i];
				ptl.copy(splineFrames.points[i]);
				qtl.copy(new THREE.Quaternion().setFromUnitVectors(new THREE.Vector3(1, 0, 0), splineFrames.tangents[i]));
				//qtl.copy(new THREE.Quaternion().setFromUnitVectors(splineFrames.tangents[i], splineFrames.tangents[i - 1]));
				//qtl.copy(CurveUtils.setQuaternionFromDirection(splineFrames.tangents[i]));
				splineFrames.wayPoints.push({ 'x': splineFrames.points[i].x + fp.x, 'y': splineFrames.points[i].y + fp.y, 'z': splineFrames.points[i].z + fp.z, 'v': splineFrames.velocities[i] });
				splineFrames.timeLine.push({ time: t, position: { x: ptl.x, y: ptl.y, z: ptl.z }, quaternion: { x: qtl.x, y: qtl.y, z: qtl.z, w: qtl.w } });

				for (var fragment of splineFrames.fragments) {
					if (i === Math.min(fragment.index, splineFrames.points.length - 1)) {
						splineFrames.trackLayer["values"].push({ "time": t, "value": ptl.x, "_color": color, "tween": fragment.type });
					}
				}

				prev_t = Math.trunc(t);
			}
		}

		return splineFrames;
	},

	computeRoadMarkLineGeometry: function (lineObject) {
		if (lineObject !== undefined) {
			const indices = [];
			const vertices = [];
			if (lineObject.mode === 'none') {
				// TODO
			} else {
				let nbVertex = 0;
				let globalIndex = 0;
				const parameters = lineObject.parameters;
				const mode = RoadMark.LINE_MODE[lineObject.mode];
				const dashSize = mode.dashSize === Number.MAX_VALUE ? mode.dashSize : mode.dashSize / mode.precRate;
				const gapSize = mode.dashSize === Number.MAX_VALUE ? mode.dashSize : (mode.dashSize - mode.gapSize) / mode.precRate;

				var lane = null;
				for (let segment of lineObject.segments) {
					lane = this.scene.getObjectById(segment.lane[0], true);
					if (lane !== undefined && lane !== null) {
						const length = lane.roadMarkLineCurve3.arcLengthDivisions / mode.precRate;
						const start = segment.start < 0 ? 0 : Math.min(Math.max(segment.start / mode.precRate, 0), length);
						const end = segment.end < 0 ? length : Math.min(segment.end / mode.precRate, length);
						const curve3 = lane.getRoadMarkLineExtrudeGeometry(parameters.position);

						let lcPts = [];
						let rcPts = [];
						let curveSegments = 0;
						const arcLengthDivisions = curve3.arcLengthDivisions / mode.precRate;
						const lc3 = GARDEN.positionAtOffset(curve3, curve3.arcLengthDivisions, -parameters.width / 2 + parameters.offset, 0);
						const rc3 = GARDEN.positionAtOffset(curve3, curve3.arcLengthDivisions, parameters.width / 2 + parameters.offset, 0);
						if (start > Math.floor(start)) {
							lcPts.push(lc3.getPointAt(start / arcLengthDivisions).add(new THREE.Vector3(0, 0.001, 0)).applyMatrix4(lane.matrixWorld));
							rcPts.push(rc3.getPointAt(start / arcLengthDivisions).add(new THREE.Vector3(0, 0.001, 0)).applyMatrix4(lane.matrixWorld));
							curveSegments++;
						}
						for (let i = Math.ceil(start), ii = Math.floor(end); i <= ii; i++) {
							lcPts.push(lc3.getPointAt(i / arcLengthDivisions).add(new THREE.Vector3(0, 0.001, 0)).applyMatrix4(lane.matrixWorld));
							rcPts.push(rc3.getPointAt(i / arcLengthDivisions).add(new THREE.Vector3(0, 0.001, 0)).applyMatrix4(lane.matrixWorld));
							curveSegments++;
						}
						if (end > Math.floor(end)) {
							lcPts.push(lc3.getPointAt(end / arcLengthDivisions).add(new THREE.Vector3(0, 0.001, 0)).applyMatrix4(lane.matrixWorld));
							rcPts.push(rc3.getPointAt(end / arcLengthDivisions).add(new THREE.Vector3(0, 0.001, 0)).applyMatrix4(lane.matrixWorld));
							curveSegments++;
						}

						let p0 = new THREE.Vector3(), p1 = new THREE.Vector3(), p2 = new THREE.Vector3(), p3 = new THREE.Vector3();
						for (let i = 0; i < curveSegments; i++) {
							let dashDiv = globalIndex++ % dashSize;
							if (dashDiv > gapSize) {
								continue
							} else if (dashDiv === 0 || i === 0) {
								vertices.push(lcPts[i].x, lcPts[i].y, lcPts[i].z);
								vertices.push(rcPts[i].x, rcPts[i].y, rcPts[i].z);
								nbVertex += 2;
							} else {
								p0.copy(lcPts[i - 1]);
								p1.copy(rcPts[i - 1]);
								p2.copy(lcPts[i]);
								p3.copy(rcPts[i]);

								vertices.push(p2.x, p2.y, p2.z);
								vertices.push(p3.x, p3.y, p3.z);
								nbVertex += 2;

								indices.push(nbVertex - 2, nbVertex - 3, nbVertex - 4);
								indices.push(nbVertex - 2, nbVertex - 1, nbVertex - 3);
							}
						}
						globalIndex--;
					}
				}
			}

			let newGeometry = new THREE.BufferGeometry();
			newGeometry.setIndex(indices);
			newGeometry.setAttribute('position', new THREE.Float32BufferAttribute(vertices, 3));

			lineObject.geometry.dispose();
			lineObject.geometry = newGeometry;

			this.signals.geometryChanged.dispatch(this.object);
			this.signals.sceneGraphChanged.dispatch();
		}
	}

};

export { Editor };
