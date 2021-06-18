/**
 * @author mrdoob / http://mrdoob.com/
 */

import * as THREE from 'three';

import { TransformControls } from '../../../vendors/examples/jsm/controls/TransformControls.js';
import { MapControls } from '../../../vendors/examples/jsm/controls/OrbitControls.js';
import { DragControls } from '../../../vendors/examples/jsm/controls/DragControls.js';
import { UIPanel } from '../../../vendors/js/libs/ui.js';
import { ViewportInfo } from './Viewport.Info.js';

import { SetValueCommand } from './commands/SetValueCommand.js';
import { SetPositionCommand } from './commands/SetPositionCommand.js';
import { SetRotationCommand } from './commands/SetRotationCommand.js';
import { SetScaleCommand } from './commands/SetScaleCommand.js';
import { SetMaterialMapCommand } from './commands/SetMaterialMapCommand.js';
import { SetGeometryCommand } from './commands/SetGeometryCommand.js';
import { SetDirectionCommand } from './commands/SetDirectionCommand.js';
import { EdgeHelper } from '../../garden-objects/js/helpers/EdgeHelper.js';
import { DragHelper } from '../../garden-objects/js/helpers/DragHelper.js';
import { MapHelper } from '../../garden-objects/js/helpers/MapHelper.js';

var Viewport = function (editor) {

	var signals = editor.signals;

	var container = new UIPanel();
	container.setId('viewport');
	container.setPosition('absolute');

	// container.add(new ViewportCamera(editor));
	container.add(new ViewportInfo(editor));

	//

	var renderer = null;
	var pmremGenerator = null;

	var camera = editor.camera;
	var scene = editor.scene;
	var sceneHelpers = editor.sceneHelpers;

	var ambientLight = new THREE.AmbientLight(0x9b9baa);
	ambientLight.name = 'AmbientLight';
	sceneHelpers.add(ambientLight);
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
	sceneHelpers.add(spotLight);

	var objects = [];

	// helpers

	var grid = new THREE.GridHelper(2000, 200, 0x444444, 0x888888);
	grid.position.y = 0;
	grid.material.opacity = 0.25;
	grid.material.transparent = true;
	grid.scale.setScalar(2);
	sceneHelpers.add(grid);




	var geometry01 = new THREE.PlaneBufferGeometry(0, 0, 1, 1);
	var plane01 = new THREE.Mesh(geometry01, new THREE.MeshStandardMaterial({ color: 0xf0f0f0 }));
	plane01.visible = false;
	plane01.position.set(0, -1, 0);
	sceneHelpers.add(plane01);
	editor.backgroundPlane01 = plane01;

	var geometry02 = new THREE.PlaneBufferGeometry(0, 0, 1, 1);
	var plane02 = new THREE.Mesh(geometry02, new THREE.MeshStandardMaterial({ color: 0xf0f0f0 }));
	plane02.visible = false;
	plane02.position.set(0, -1, 0);
	sceneHelpers.add(plane02);
	editor.backgroundPlane02 = plane02;

	var mapHelper = new MapHelper(editor);
	editor.backgroundMap = mapHelper;
	sceneHelpers.add(mapHelper);


	//#############################################################




	var array = grid.geometry.attributes.color.array;

	for (var i = 0; i < array.length; i += 60) {

		for (var j = 0; j < 12; j++) {

			array[i + j] = 0.26;

		}

	}

	//

	var box = new THREE.Box3();

	var selectionBox = new THREE.BoxHelper();
	selectionBox.material.depthTest = false;
	selectionBox.material.transparent = true;
	selectionBox.visible = false;
	//sceneHelpers.add(selectionBox);

	var objectPositionOnDown = null;
	var objectRotationOnDown = null;
	var objectScaleOnDown = null;

	// ########################################################################
	var selectionEdge = new EdgeHelper();
	selectionEdge.material.depthTest = false;
	selectionEdge.material.transparent = true;
	selectionEdge.visible = false;
	sceneHelpers.add(selectionEdge);


	/*var spriteHelper = new SpriteHelper();
	spriteHelper.visible = false;
	sceneHelpers.add(spriteHelper);*/


	// var multiselectionBox = new SelectionBox(camera, /*sceneHelpers*/spriteHelper);
	// ########################################################################
	var dragControls = new DragControls([], camera, container.dom);

	var transformControls = new TransformControls(camera, container.dom);
	transformControls.addEventListener('change', function () {

		var object = transformControls.object;

		if (object !== undefined) {

			selectionEdge.setFromObject(object);
			// spriteHelper.setFromObject(object);

			//selectionBox.setFromObject(object);

			var helper = editor.helpers[object.id];

			if (helper !== undefined && helper.isSkeletonHelper !== true) {

				helper.update();

			}

			signals.refreshSidebarObject3D.dispatch(object);

		}

		render();

	});
	transformControls.addEventListener('mouseDown', function () {

		var object = transformControls.object;

		objectPositionOnDown = object.position.clone();
		objectRotationOnDown = object.rotation.clone();
		objectScaleOnDown = object.scale.clone();

		controls.enabled = false;

	});
	transformControls.addEventListener('mouseUp', function () {

		var object = transformControls.object;

		if (object !== undefined) {

			switch (transformControls.getMode()) {

				case 'translate':

					if (!objectPositionOnDown.equals(object.position)) {

						editor.execute(new SetPositionCommand(editor, object, object.position, objectPositionOnDown));

					}

					break;

				case 'rotate':

					if (!objectRotationOnDown.equals(object.rotation)) {

						editor.execute(new SetRotationCommand(editor, object, object.rotation, objectRotationOnDown));

					}

					break;

				case 'scale':

					if (!objectScaleOnDown.equals(object.scale)) {

						editor.execute(new SetScaleCommand(editor, object, object.scale, objectScaleOnDown));

					}

					break;

			}

		}

		controls.enabled = true;

	});

	sceneHelpers.add(transformControls);

	// ################################################

	let dragHelper = new DragHelper([], camera, container.dom);

	// ################################################

	// object picking

	var raycaster = new THREE.Raycaster();
	var mouse = new THREE.Vector2();

	// events

	function getIntersects(point, objects) {

		mouse.set((point.x * 2) - 1, - (point.y * 2) + 1);

		raycaster.setFromCamera(mouse, camera);

		let targets = scene.children
			.filter(child => child.type === 'Route' && child.visible === true)
			.map(child => {
				return child.routeWayPoints;
			}).flatMap(child => {
				return child.children;
			});

		let intersects = raycaster.intersectObjects([...objects, ...targets]);

		if (editor.config.getKey('wayPointEditMode')) {

			// intersects = [...raycaster.intersectObjects(spriteHelper.children), ...intersects];

		}

		return intersects;

	}

	var onDownPosition = new THREE.Vector2();
	var onUpPosition = new THREE.Vector2();
	var onDoubleClickPosition = new THREE.Vector2();

	function getMousePosition(dom, x, y) {

		var rect = dom.getBoundingClientRect();
		return [(x - rect.left) / rect.width, (y - rect.top) / rect.height];

	}

	function handleClick() {

		// let selected = editor.selected;
		// if (selected !== null && selected.type === 'RoadMark.Crosswalk') {
		// 	selected.sprite01.visible = false;
		// 	selected.sprite02.visible = false;
		// }

		if (onDownPosition.distanceTo(onUpPosition) === 0) {

			var intersects = getIntersects(onUpPosition, objects);

			if (intersects.length > 0) {

				let ctrlSprites = intersects.filter(cs =>
					cs.object.type === 'ControlSprite'
					&& cs.object.parent.spriteEdge.offsetType !== 'none'
				);

				let targetObject;
				if (ctrlSprites.length > 0) {
					targetObject = ctrlSprites[0].object;
				} else {
					targetObject = intersects[0].object;
				}

				if (targetObject.type === 'Sprite') {

					editor.select(targetObject);

				} else if (targetObject.type === 'ControlSprite') {
					if (targetObject.visible === true) editor.select(targetObject);

				} else if (targetObject.type === 'Crosswalk.Sprite') {

					editor.select(targetObject.parent);
					targetObject.parent.sprite01.visible = true;
					targetObject.parent.sprite02.visible = true;
				} else if (targetObject.type === 'RoadMark.Crosswalk') {

					editor.select(targetObject);
					targetObject.sprite01.visible = true;
					targetObject.sprite02.visible = true;
				} else {

					var object = targetObject.parent;

					if (object.userData.object !== undefined) {

						// helper

						editor.select(object.userData.object);

					} else {

						editor.select(object);

					}
				}

			} else {

				editor.select(null);

			}

			render();

		}

	}

	function onMouseDown(event) {

		// event.preventDefault();
		var array = getMousePosition(container.dom, event.clientX, event.clientY);
		onDownPosition.fromArray(array);

		document.addEventListener('mouseup', onMouseUp, false);

	}

	function onMouseUp(event) {

		var array = getMousePosition(container.dom, event.clientX, event.clientY);
		onUpPosition.fromArray(array);

		handleClick();

		document.removeEventListener('mouseup', onMouseUp, false);

	}

	function onTouchStart(event) {

		var touch = event.changedTouches[0];

		var array = getMousePosition(container.dom, touch.clientX, touch.clientY);
		onDownPosition.fromArray(array);

		document.addEventListener('touchend', onTouchEnd, false);

	}

	function onTouchEnd(event) {

		var touch = event.changedTouches[0];

		var array = getMousePosition(container.dom, touch.clientX, touch.clientY);
		onUpPosition.fromArray(array);

		handleClick();

		document.removeEventListener('touchend', onTouchEnd, false);

	}

	function onDoubleClick(event) {

		var array = getMousePosition(container.dom, event.clientX, event.clientY);
		onDoubleClickPosition.fromArray(array);

		var intersects = getIntersects(onDoubleClickPosition, objects);

		if (intersects.length > 0) {

			var intersect = intersects[0];

			signals.objectFocused.dispatch(intersect.object);

		}

	}

	container.dom.addEventListener('mousedown', onMouseDown, false);
	container.dom.addEventListener('touchstart', onTouchStart, false);
	container.dom.addEventListener('dblclick', onDoubleClick, false);

	// controls need to be added *after* main logic,
	// otherwise controls.enabled doesn't work.

	var controls = new MapControls(camera, container.dom);
	controls.screenSpacePanning = false;
	controls.minDistance = 10;
	controls.maxDistance = 1000;
	controls.maxPolarAngle = 85 * THREE.MathUtils.DEG2RAD;
	//controls.enableRotate = false;

	controls.addEventListener('change', function () {

		signals.cameraChanged.dispatch(camera);

	});

	signals.editorCleared.add(function () {

		/*if (this.camera.isPerspectiveCamera) {

			//this.camera.copy(this.DEFAULT_PERSPECTIVE_CAMERA);
			controls.enableRotate = true;

		} else if (this.camera.isOrthographicCamera) {

			//this.camera.copy(this.DEFAULT_ORTHOGRAPHIC_CAMERA);
			controls.enableRotate = false;

		}*/
		//controls.center.set(0, 0, 0);
		objects.splice(0);
		controls.reset();
		currentBackgroundType = null;
		render();

	});

	signals.transformModeChanged.add(function (mode) {

		transformControls.setMode(mode);

	});

	signals.snapChanged.add(function (dist) {

		transformControls.setTranslationSnap(dist);

	});

	signals.spaceChanged.add(function (space) {

		transformControls.setSpace(space);

	});

	signals.rendererUpdated.add(function () {

		render();

	});

	signals.rendererChanged.add(function (newRenderer, newPmremGenerator) {

		if (renderer !== null) {

			container.dom.removeChild(renderer.domElement);

		}

		renderer = newRenderer;
		pmremGenerator = newPmremGenerator;

		renderer.autoClear = false;
		renderer.autoUpdateScene = false;
		renderer.outputEncoding = THREE.sRGBEncoding;
		renderer.setPixelRatio(window.devicePixelRatio);
		renderer.setSize(container.dom.offsetWidth, container.dom.offsetHeight);

		container.dom.appendChild(renderer.domElement);

		render();

	});

	signals.sceneGraphChanged.add(function () {

		render();

	});

	signals.cameraChanged.add(function () {

		render();

	});

	signals.objectSelected.add(function (object) {

		for (let construction of scene.children) {
			if (construction.type === 'Road') {
				for (let roadConstruction of construction.children) {
					if (roadConstruction.type === 'RoadMark.Crosswalk') {
						roadConstruction.sprite01.visible = false;
						roadConstruction.sprite02.visible = false;
					}
				}

			} else if (construction.type === 'Junction') {
				for (let roadConstruction of construction.children) {
					if (roadConstruction.type === 'RoadMark.Crosswalk') {
						roadConstruction.sprite01.visible = false;
						roadConstruction.sprite02.visible = false;
					}
				}
			} else if (construction.type === 'RoadMark.Crosswalk') {
				construction.sprite01.visible = false;
				construction.sprite02.visible = false;
			}
		}

		//selectionBox.visible = false;
		selectionEdge.visible = false;
		//spriteHelper.visible = false;
		transformControls.detach();

		const draggableObjects = dragHelper.getObjects();
		draggableObjects.length = 0;


		const draggableSprites = dragControls.getObjects();
		draggableSprites.length = 0;
		if (object !== null && object !== scene && object !== camera) {

			box.setFromObject(object);

			if (box.isEmpty() === false) {

				//selectionBox.setFromObject(object);
				//selectionBox.visible = true;



				selectionEdge.setFromObject(object);
				selectionEdge.visible = true;

				// spriteHelper.setFromObject(object);
				// spriteHelper.visible = true;

			}

			// if (object.type === 'Road' || object.type === 'Junction') {
			if (object.type === 'Road') {

				transformControls.attach(object);

			} else if (object.goType === 'GORoad') {

				transformControls.attach(object);

			} else if (object.type === 'RoadMark.Crosswalk') {

				// transformControls.attach(object);
				draggableSprites.push(object.sprite01);
				draggableSprites.push(object.sprite02);

				for (let construction of scene.children) {
					if (construction.type === 'Road') {
						for (let roadConstruction of construction.children) {
							if (roadConstruction.type === 'RoadMark.Crosswalk') {
								roadConstruction.sprite01.visible = false;
								roadConstruction.sprite02.visible = false;
							}
						}

					} else if (construction.type === 'Junction') {
						for (let roadConstruction of construction.children) {
							if (roadConstruction.type === 'RoadMark.Crosswalk') {
								roadConstruction.sprite01.visible = false;
								roadConstruction.sprite02.visible = false;
							}
						}
					} else if (construction.type === 'RoadMark.Crosswalk') {
						construction.sprite01.visible = false;
						construction.sprite02.visible = false;
					}
				}

				object.sprite01.visible = true;
				object.sprite02.visible = true;

			} else if (object.type === 'ControlSprite') {
				draggableObjects.push(object);

			} else if (object.type === 'Crosswalk.Sprite') {
				draggableSprites.push(object);

			} else if (object.type === 'RoadMark.Signal.TrafficLight') {
				transformControls.attach(object);
			}

		}

		render();

	});

	signals.objectFocused.add(function (object) {

		//controls.focus(object);

	});

	signals.geometryChanged.add(function (object) {

		if (object !== undefined) {

			//selectionBox.setFromObject(object);

			selectionEdge.setFromObject(object);
			// spriteHelper.setFromObject(object);

		}

		render();

	});

	signals.objectAdded.add(function (object) {
		object.traverse(function (child) {
			if (child.type === 'Junction') {
				objects.push(child.plane);
			} else if (child.type === 'Road') {
				objects.push(child.edge);
			} else if (child.type === 'Lane') {
				objects.push(child.plane);
				objects.push(child.sprite01);
				objects.push(child.sprite02);
			} else if (child.goType === 'GORoad') {
				objects.push(child.borderLine);
			} else if (child.goType === 'GOLane') {
				objects.push(child.planMesh);
			} else if (child.type === 'RoadMark.Crosswalk') {
				objects.push(child);
				objects.push(child.sprite01);
				objects.push(child.sprite02);
			}
		});
	});

	signals.objectChanged.add(function (object) {

		if (editor.selected === object) {

			//selectionBox.setFromObject(object);

			selectionEdge.setFromObject(object);
			// spriteHelper.setFromObject(object);

		} else if (editor.selected !== null && editor.selected.parent === object) {

			selectionEdge.setFromObject(editor.selected);
			// spriteHelper.setFromObject(editor.selected);

		}

		if (object.isPerspectiveCamera) {

			object.updateProjectionMatrix();

		}

		if (editor.helpers[object.id] !== undefined) {

			editor.helpers[object.id].update();

		}

		render();

	});

	signals.objectRemoved.add(function (object) {

		// controls.enabled = true; // see #14180
		if (object === transformControls.object) {

			transformControls.detach();

		}

		object.traverse(function (child) {

			var targetObject = child;
			var helperObject = [];

			if (child.type === 'Road' || child.type === 'Junction') {

				targetObject = child.plane;

			} else if (child.type === 'Lane' || child.type === 'JunctionLane') {

				targetObject = null;
				helperObject.push(child.plane);
				helperObject.push(child.sprite01);
				helperObject.push(child.sprite02);

			}

			if (child.goType === 'GORoad') {
				targetObject = child.borderLine;
			} else if (child.goType === 'GOLane') {
				targetObject = child.planMesh;
			}

			if (helperObject.length > 0) {
				for (let hObj of helperObject) {
					if (objects.indexOf(hObj) !== -1) {
						objects.splice(objects.indexOf(hObj), 1);
					}
				}
			} else if (objects.indexOf(targetObject) !== -1) {
				objects.splice(objects.indexOf(targetObject), 1);
			}

		});

	});

	signals.helperAdded.add(function (object) {

		objects.push(object.getObjectByName('picker'));

	});

	signals.helperRemoved.add(function (object) {

		objects.splice(objects.indexOf(object.getObjectByName('picker')), 1);

	});

	signals.materialChanged.add(function () {

		render();

	});

	// direction

	var currentDirectionType = null;

	signals.scenarioDirectionChanged.add(function (directionType) {

		if (directionType !== editor.scene.direction) {

			editor.execute(new SetDirectionCommand(editor, editor.scene, directionType));
		}

	});

	// plane01 background


	signals.scenarioBackground01PositionChanged.add(function (newPosition) {

		if (plane01.position.distanceTo(newPosition) >= 0.01) {

			editor.execute(new SetPositionCommand(editor, plane01, newPosition));

		}

	});

	signals.scenarioBackground01RotationChanged.add(function (newRotation) {

		if (plane01.rotation.toVector3().distanceTo(newRotation.toVector3()) >= 0.01) {

			editor.execute(new SetRotationCommand(editor, plane01, newRotation));

		}

	});

	signals.scenarioBackground01ScaleChanged.add(function (newScale) {

		if (plane01.scale.distanceTo(newScale) >= 0.001) {

			editor.execute(new SetScaleCommand(editor, plane01, newScale));

		}

	});

	signals.scenarioBackground00Changed.add(async function (backgroundBoundary, backgroundVisible, backgroundUpdate) {

		var boundary = mapHelper.boundary;

		if (backgroundBoundary !== null) {
			if (boundary.n !== backgroundBoundary.n) boundary.n = backgroundBoundary.n;
			if (boundary.e !== backgroundBoundary.e) boundary.e = backgroundBoundary.e;
			if (boundary.s !== backgroundBoundary.s) boundary.s = backgroundBoundary.s;
			if (boundary.w !== backgroundBoundary.w) boundary.w = backgroundBoundary.w;
		}

		if (backgroundVisible !== mapHelper.visible) {

			editor.execute(new SetValueCommand(editor, mapHelper, 'visible', backgroundVisible));

		}

		if (backgroundUpdate) mapHelper.loadOverpassDataByBoundary(backgroundBoundary);

		render();

	});

	signals.scenarioBackground00Generate.add(function (backgroundBoundary) {

		var boundary = mapHelper.boundary;

		if (boundary.n !== backgroundBoundary.n) boundary.n = backgroundBoundary.n;
		if (boundary.e !== backgroundBoundary.e) boundary.e = backgroundBoundary.e;
		if (boundary.s !== backgroundBoundary.s) boundary.s = backgroundBoundary.s;
		if (boundary.w !== backgroundBoundary.w) boundary.w = backgroundBoundary.w;

		mapHelper.generateOverpassDataByBoundary(backgroundBoundary);

		render();

	});

	var currentPlaneBackgroundType = null;

	signals.scenarioBackground01Changed.add(function (backgroundType, backgroundTexture, backgroundVisible) {

		if (currentPlaneBackgroundType !== backgroundType) {

			switch (backgroundType) {

				case 'None':
					plane01.background = null;
					break;
				case 'Color':
					scene.background = new THREE.Color();
					break;

			}

		}

		if (backgroundType === 'Color') {

			// TODO

		} else if (backgroundType === 'Texture') {

			var material = plane01.material;

			if (material.map !== backgroundTexture) {

				if (backgroundTexture !== null) {
					backgroundTexture.minFilter = THREE.LinearFilter;
					// backgroundTexture.anisotropy = renderer.capabilities.getMaxAnisotropy();
				}

				var width = backgroundTexture !== null ? backgroundTexture.image.width : 0;
				var height = backgroundTexture !== null ? backgroundTexture.image.height : 0;

				var newGeometry = new THREE.PlaneBufferGeometry(width, height, 1, 1).rotateX(-Math.PI / 2).translate(width / 2, 0, 0);

				editor.execute(new SetGeometryCommand(editor, plane01, newGeometry));

				editor.execute(new SetMaterialMapCommand(editor, plane01, 'map', backgroundTexture, 0));

			}

			if (plane01.userData.is2DVisible !== backgroundVisible) {
				plane01.userData.is2DVisible = backgroundVisible;
			}

			if (camera.isOrthographicCamera) {
				editor.execute(new SetValueCommand(editor, plane01, 'visible', plane01.userData.is2DVisible));
			}

		}

		render();

	});

	// plane02 background


	signals.scenarioBackground02PositionChanged.add(function (newPosition) {

		if (plane02.position.distanceTo(newPosition) >= 0.01) {

			editor.execute(new SetPositionCommand(editor, plane02, newPosition));

		}

	});

	signals.scenarioBackground02RotationChanged.add(function (newRotation) {

		if (plane02.rotation.toVector3().distanceTo(newRotation.toVector3()) >= 0.01) {

			editor.execute(new SetRotationCommand(editor, plane02, newRotation));

		}

	});

	signals.scenarioBackground02ScaleChanged.add(function (newScale) {

		if (plane02.scale.distanceTo(newScale) >= 0.001) {

			editor.execute(new SetScaleCommand(editor, plane02, newScale));

		}

	});

	var currentPlaneBackgroundType = null;

	signals.scenarioBackground02Changed.add(function (backgroundType, backgroundTexture, backgroundVisible) {

		if (currentPlaneBackgroundType !== backgroundType) {

			switch (backgroundType) {

				case 'None':
					plane02.background = null;
					break;
				case 'Color':
					scene.background = new THREE.Color();
					break;

			}

		}

		if (backgroundType === 'Color') {

			// TODO

		} else if (backgroundType === 'Texture') {

			var material = plane02.material;

			if (material.map !== backgroundTexture) {

				if (backgroundTexture !== null) {
					backgroundTexture.minFilter = THREE.LinearFilter;
					// backgroundTexture.anisotropy = renderer.capabilities.getMaxAnisotropy();
				}

				var width = backgroundTexture !== null ? backgroundTexture.image.width : 0;
				var height = backgroundTexture !== null ? backgroundTexture.image.height : 0;

				var newGeometry = new THREE.PlaneBufferGeometry(width, height, 1, 1).rotateX(-Math.PI / 2).translate(width / 2, 0, 0);

				editor.execute(new SetGeometryCommand(editor, plane02, newGeometry));

				editor.execute(new SetMaterialMapCommand(editor, plane02, 'map', backgroundTexture, 0));

			}

			if (plane02.userData.is2DVisible !== backgroundVisible) {
				plane02.userData.is2DVisible = backgroundVisible;
			}

			if (camera.isOrthographicCamera) {
				editor.execute(new SetValueCommand(editor, plane02, 'visible', plane02.userData.is2DVisible));
			}

		}

		render();

	});

	// background

	var currentBackgroundType = null;

	signals.sceneBackgroundChanged.add(function (backgroundType, backgroundColor, backgroundTexture, backgroundCubeTexture, backgroundEquirectTexture) {

		if (currentBackgroundType !== backgroundType) {

			switch (backgroundType) {

				case 'None':
					scene.background = null;
					break;
				case 'Color':
					scene.background = new THREE.Color();
					break;

			}

		}

		if (backgroundType === 'Color') {

			scene.background.set(backgroundColor);
			scene.environment = null;

		} else if (backgroundType === 'Texture') {

			scene.background = backgroundTexture;
			scene.environment = null;

		} else if (backgroundType === 'CubeTexture') {

			if (backgroundCubeTexture && backgroundCubeTexture.isHDRTexture) {

				var texture = pmremGenerator.fromCubemap(backgroundCubeTexture).texture;
				texture.isPmremTexture = true;

				scene.background = texture;
				scene.environment = texture;

			} else {

				scene.background = backgroundCubeTexture;
				scene.environment = null;

			}

		} else if (backgroundType === 'Equirect') {

			if (backgroundEquirectTexture && backgroundEquirectTexture.isHDRTexture) {

				var texture = pmremGenerator.fromEquirectangular(backgroundEquirectTexture).texture;
				texture.isPmremTexture = true;

				scene.background = texture;
				scene.environment = texture;

			} else {

				scene.background = null;
				scene.environment = null;

			}

		}

		render();

	});

	//

	signals.viewportCameraChanged.add(function (newCamera) {

		if (newCamera.isPerspectiveCamera) {

			grid.visible = true;
			plane01.visible = false;
			plane02.visible = false;

			newCamera.position.copy(camera.position);
			newCamera.updateProjectionMatrix();
			//controls.target.copy(new THREE.Vector3(camera.position.x, 0, camera.position.z));

			controls.enableRotate = true;
			controls.saveState();

			dragHelper = new DragHelper([], newCamera, container.dom);
			dragHelper.enabled = false;

			dragControls = new DragControls([], newCamera, container.dom);
			dragControls.enabled = false;

		} else if (newCamera.isOrthographicCamera) {

			grid.visible = true;
			plane01.visible = plane01.userData.is2DVisible || false;
			plane02.visible = plane02.userData.is2DVisible || false;

			newCamera.position.copy(camera.position);
			newCamera.updateProjectionMatrix();
			controls.target.copy(new THREE.Vector3(camera.position.x, 0, camera.position.z));

			controls.enableRotate = false;
			controls.saveState();


			dragHelper = new DragHelper([], newCamera, container.dom);
			dragHelper.enabled = true;
			dragHelper.addEventListener('drag', function (event) {
				event.object.parent.update();
				selectionEdge.setFromObject(event.object);
				render();
			});
			dragHelper.addEventListener('dragstart', function () {
			});
			dragHelper.addEventListener('dragend', function (event) {
				event.object.parent.parent.computeStructure();
				render();
			});



			dragControls = new DragControls([], newCamera, container.dom);
			dragControls.enabled = true;
			dragControls.addEventListener('drag', function (event) {
				event.object.parent.computeGeometry();
				selectionEdge.setFromObject(event.object.parent);

				editor.signals.geometryChanged.dispatch(this.object);
				editor.signals.sceneGraphChanged.dispatch();
			});
			dragControls.addEventListener('dragstart', function (event) {
				if (event.object.parent.type === 'RoadMark.Crosswalk') {
					event.object.parent.sprite01.visible = true;
					event.object.parent.sprite02.visible = true;
				}
			});
			dragControls.addEventListener('dragend', function (event) {
				event.object.parent.computeGeometry();

				editor.signals.geometryChanged.dispatch(this.object);
				editor.signals.sceneGraphChanged.dispatch();
			});

		} else {

			throw "Invalid camera set as viewport";

		}

		camera = newCamera;
		transformControls.camera = camera;
		controls.object = camera;
		/*if (multiselectionBox !== undefined) {
			multiselectionBox.camera = camera;
		}*/
		controls.reset();

		render();

	});

	//

	signals.windowResize.add(function () {

		// TODO: Move this out?

		editor.DEFAULT_PERSPECTIVE_CAMERA.aspect = container.dom.offsetWidth / container.dom.offsetHeight;
		editor.DEFAULT_PERSPECTIVE_CAMERA.updateProjectionMatrix();

		editor.DEFAULT_ORTHOGRAPHIC_CAMERA.left = container.dom.offsetWidth / -2;
		editor.DEFAULT_ORTHOGRAPHIC_CAMERA.right = container.dom.offsetWidth / 2;
		editor.DEFAULT_ORTHOGRAPHIC_CAMERA.top = container.dom.offsetHeight / 2;
		editor.DEFAULT_ORTHOGRAPHIC_CAMERA.bottom = container.dom.offsetHeight / -2;
		editor.DEFAULT_ORTHOGRAPHIC_CAMERA.updateProjectionMatrix();

		for (let key in editor.cameras) {

			const camera = editor.cameras[key];

			if (camera.isPerspectiveCamera) {

				camera.aspect = container.dom.offsetWidth / container.dom.offsetHeight;
				camera.updateProjectionMatrix();

			} else if (camera.isOrthographicCamera) {

				camera.left = container.dom.offsetWidth / -2;
				camera.right = container.dom.offsetWidth / 2;
				camera.top = container.dom.offsetHeight / 2;
				camera.bottom = container.dom.offsetHeight / -2;
				camera.updateProjectionMatrix();

			} else {

				throw "Invalid camera set as viewport";

			}

		}

		renderer.setSize(container.dom.offsetWidth, container.dom.offsetHeight);

		render();

	});

	signals.showGridChanged.add(function (showGrid) {

		grid.visible = showGrid;
		render();

	});

	// animations

	var clock = new THREE.Clock(); // only used for animations

	function animate() {

		requestAnimationFrame(animate);

		var mixer = editor.mixer;

		if (mixer.stats.actions.inUse > 0) {

			mixer.update(clock.getDelta());
			render();

		}

	}

	requestAnimationFrame(animate);

	//

	var startTime = 0;
	var endTime = 0;

	function render() {

		startTime = performance.now();

		scene.updateMatrixWorld();
		renderer.render(scene, camera);

		if (camera === editor.camera) {

			sceneHelpers.updateMatrixWorld();
			renderer.render(sceneHelpers, camera);

		}

		endTime = performance.now();
		var frametime = endTime - startTime;
		editor.signals.sceneRendered.dispatch(frametime);

	}

	return container;

};

export { Viewport };
