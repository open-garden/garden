/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel } from '../../../vendors/js/libs/ui.js';
import { APP } from '../../../vendors/js/libs/app.js';

import { MapControls } from '../../../vendors/examples/jsm/controls/OrbitControls.js';

function Player(editor) {

	const DEFAULT_CAMERA = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 10, 50000);
	DEFAULT_CAMERA.name = 'Camera';
	DEFAULT_CAMERA.position.set(0, 500, 20);
	DEFAULT_CAMERA.lookAt(new THREE.Vector3());

	var signals = editor.signals;

	var container = new UIPanel();
	container.setId('previewPlayer');
	container.setPosition('absolute');
	container.setDisplay('none');

	var controls = new MapControls(DEFAULT_CAMERA, container.dom);
	controls.screenSpacePanning = false;
	controls.minDistance = 10;
	controls.maxDistance = 1000;
	controls.maxPolarAngle = 85 * THREE.MathUtils.DEG2RAD;
	//controls.enableRotate = false;

	//

	var player = new APP.Player();
	container.dom.appendChild(player.dom);

	window.addEventListener('resize', function () {

		player.setSize(container.dom.clientWidth, container.dom.clientHeight);

	});

	signals.startPlayer.add(function () {

		container.setDisplay('');

		player.setScene(editor.scene);
		player.setSceneHelpers(editor.sceneHelpers);
		player.setCamera(DEFAULT_CAMERA.clone());
		player.setTimelineData(editor.timelineData);
		player.setSize(container.dom.clientWidth, container.dom.clientHeight);
		player.play();

	});

	signals.stopPlayer.add(function () {

		container.setDisplay('none');

		player.stop();
		player.dispose();

	});

	return container;

}

export { Player };
