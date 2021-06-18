
/**
 * @author mrdoob / http://mrdoob.com/
 */

import { MapControls } from '../../examples/jsm/controls/OrbitControls.js';
import { TimelinerController } from '../../examples/jsm/animation/TimelinerController.js';

var APP = {

	Player: function () {

		var renderer = new THREE.WebGLRenderer({ antialias: true });
		renderer.autoClear = false;
		renderer.autoUpdateScene = false;
		renderer.setPixelRatio(window.devicePixelRatio);
		renderer.outputEncoding = THREE.sRGBEncoding;

		var loader = new THREE.ObjectLoader();
		var camera, scene, sceneHelpers, timelineData;
		var controls, timeliner;

		var dom = document.createElement('div');
		dom.appendChild(renderer.domElement);

		this.dom = dom;

		this.width = 500;
		this.height = 500;

		this.load = function (json) {

			var project = json.project;

			renderer.shadowMap.enabled = project.shadows === true;
			renderer.xr.enabled = project.vr === true;

			this.setScene(loader.parse(json.scene));
			this.setCamera(loader.parse(json.camera));

		};

		this.setCamera = function (value) {

			camera = value;
			camera.aspect = this.width / this.height;
			camera.updateProjectionMatrix();

			controls = new MapControls(camera, renderer.domElement);
			controls.screenSpacePanning = false;
			controls.minDistance = 50;
			controls.maxDistance = 500;
			controls.maxPolarAngle = Math.PI / 2;

			controls.addEventListener('change', animate);

		};

		this.setScene = function (value) {

			scene = value;

			(function setUnvisibleObjects(objects) {

				for (var i = 0, l = objects.length; i < l; i++) {

					var object = objects[i];

					if (object.type === 'Road') {

						object.remove(object.refLine);
						object.refLine = undefined;
					}

				}

			})(scene.children);

		};

		this.setSceneHelpers = function (value) {

			sceneHelpers = value;

		};

		this.setTimelineData = function (value) {

			if (value !== undefined && value !== null) {

				timelineData = value;

				var timelinerController = new TimelinerController(scene, timelineData.trackInfo, animate);
				timeliner = new window.Timeliner(null, timelinerController);
				timeliner.load(timelineData.trackData);

				(function initTrackRouteObject(objects) {
					for (var i = 0, l = objects.length; i < l; i++) {
						var object = objects[i];
						if (object.type === 'Car' && object.metalData.routeData !== null) {
							var timeLine = object.metalData.routeData.timeLine;
							var trackMesh = object.actorObject;
							trackMesh.name = object.name + '_Mesh01';
							trackMesh.visible = false;
							trackMesh.position.copy(timeLine[0].position);
							trackMesh.quaternion.copy(timeLine[0].quaternion);
							createTrackObjectOrigin(trackMesh, timelinerController, trackMesh.name, timeLine);
						} else if (object.type === 'Trajectory') {
							var timeLine = object.userData.timeLine;
							if (!timeLine) continue;
							var trackMesh = object.actorObject;
							trackMesh.name = object.name + '_Mesh01';
							trackMesh.visible = false;
							trackMesh.position.copy(timeLine[0].position);
							trackMesh.quaternion.copy(timeLine[0].quaternion);
							createTrackObject(trackMesh, timelinerController, trackMesh.name, timeLine, timeliner);
						}
					}
				})(scene.children);

			} else {
				timelineData = {};
			}

		};

		this.setSize = function (width, height) {

			this.width = width;
			this.height = height;

			if (camera) {

				camera.aspect = this.width / this.height;
				camera.updateProjectionMatrix();

			}

			if (renderer) {

				renderer.setSize(width, height);

			}

		};

		function animate() {

			scene.updateMatrixWorld();
			renderer.render(scene, camera);

			if (sceneHelpers !== undefined) {
				sceneHelpers.updateMatrixWorld();
				renderer.render(sceneHelpers, camera);
			}

		}

		this.play = function () {

			if (timeliner !== undefined) {
				timeliner.startPlay();
			}
			renderer.setAnimationLoop(animate);

		};

		this.stop = function () {

			if (timeliner !== undefined) {
				timeliner.stopPlay();
			}
			renderer.setAnimationLoop(null);

		};

		this.dispose = function () {

			renderer.dispose();

			camera = undefined;
			scene = undefined;
			//sceneHelpers = undefined;
			controls = undefined;
			if (timeliner !== undefined) {
				timeliner.dispose();
			}
			timeliner = undefined;

		};

		//

		function createTrackObjectOrigin(trackMesh, timelinerController, objectName, objectTimeline, timeliner) {
			for (var i = 0; i < objectTimeline.length; i++) {
				var track = objectTimeline[i];
				trackMesh.visible = true;
				timelinerController.setKeyframe(trackMesh.name + '.visible', track.time);

				trackMesh.position.copy(track.position);
				timelinerController.setKeyframe(trackMesh.name + '.position', track.time);
				if (i < objectTimeline.length - 2) {
					var dir = trackMesh.parent.localToWorld(new THREE.Vector3(objectTimeline[i + 1].position.x, objectTimeline[i + 1].position.y, objectTimeline[i + 1].position.z));
					trackMesh.lookAt(dir);
				}
				timelinerController.setKeyframe(trackMesh.name + '.quaternion', track.time);
			}

			trackMesh.visible = true;
			trackMesh.position.copy(objectTimeline[0].position);
			trackMesh.quaternion.copy(objectTimeline[0].quaternion);
		}

		function createTrackObject(trackMesh, timelinerController, objectName, objectTimeline, timeliner) {
			for (var i = 0; i < objectTimeline.length; i++) {
				var track = objectTimeline[i];
				trackMesh.visible = true;
				timelinerController.setKeyframe(trackMesh.name + '.visible', track.time);

				trackMesh.position.copy(track.position);
				timelinerController.setKeyframe(trackMesh.name + '.position', track.time);

				trackMesh.quaternion.copy(track.quaternion);
				timelinerController.setKeyframe(trackMesh.name + '.quaternion', track.time);

				if (i === 0) timeliner.setCurrentTime(track.time);
			}

			trackMesh.visible = true;
			trackMesh.position.copy(objectTimeline[0].position);
			trackMesh.quaternion.copy(objectTimeline[0].quaternion);
		}

	}

};

export { APP };
