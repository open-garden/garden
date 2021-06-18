/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIButton, UISpan } from '../../../vendors/js/libs/ui.js';

var ViewportCamera = function (editor) {

	var signals = editor.signals;
	var strings = editor.strings;
	var camera = editor.viewportCamera;


	var is3D = true;
	//
	var container = new UIPanel();
	container.setId('viewport-camera');

	//container.setPosition('absolute');
	//container.setLeft('calc(100% - 350px)');
	//container.setBottom('10px');
	/*cameraSelect.onChange(function () {

		editor.setViewportCamera(this.getValue());

	});*/
	var buttons = new UIPanel();
	buttons.setId("viewport-camera_controls");
	container.add(buttons);

	// compass / tilt
	var compassButton = new UIButton();

	var compass = new UISpan();
	compass.dom.className = 'compass-button';
	compass.setId("viewport-camera_controls_compass");
	compassButton.add(compass);

	compassButton.onClick(function () {

		var quaternion = new THREE.Quaternion(-0.7, 0, 0, 0.7);
		var position = new THREE.Vector3(100, 100, 100);

		//signals.viewportCameraPointToNorth.dispatch(position);

	});
	buttons.add(compassButton);

	var tiltButton = new UIButton(strings.getKey('toolbar/tilt/3d')).setMarginTop('2px');
	tiltButton.dom.className = 'Button';
	tiltButton.onClick(function () {

		var cameras = editor.cameras;

		if (is3D === false) {

			is3D = true;
			tiltButton.setTextContent(strings.getKey('toolbar/tilt/3d'));
			for (var key in cameras) {

				var temp = cameras[key];
				if (temp.isPerspectiveCamera) {
					camera = temp;
					editor.setViewportCamera(temp.uuid);
					break;
				}

			}

		} else {

			is3D = false;
			tiltButton.setTextContent(strings.getKey('toolbar/tilt/2d'));
			for (var key in cameras) {

				var temp = cameras[key];
				if (temp.isOrthographicCamera) {
					camera = temp;
					editor.setViewportCamera(temp.uuid);
					break;
				}

			}

		}

	});
	buttons.add(tiltButton);

	update();

	//

	function update() {

		/*var options = {};

		var cameras = editor.cameras;

		for (var key in cameras) {

			var camera = cameras[key];
			options[camera.uuid] = camera.name;

		}

		cameraSelect.setOptions(options);
		cameraSelect.setValue(editor.viewportCamera.uuid);*/

	}

	signals.sceneRendered.add(updateCompass);

	function updateCompass(frametime) {

		compass.dom.style.transform = `rotate(${camera.rotation.z * THREE.MathUtils.RAD2DEG}deg)`;

	}

	return container;

};

export { ViewportCamera };
