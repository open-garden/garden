/**
 * @author zz85 / https://github.com/zz85
 * @author mrdoob / http://mrdoob.com
 * Running this will allow you to drag three.js objects around the screen.
 */

import {
	EventDispatcher,
	Matrix4,
	Plane,
	Raycaster,
	Vector2,
	Vector3
} from 'three';

var DragHelper = function (_objects, _camera, _domElement) {

	var _plane = new Plane();
	var _raycaster = new Raycaster();

	var _mouse = new Vector2();
	var _offset = new Vector3();
	var _intersection = new Vector3();
	var _worldPosition = new Vector3();
	var _inverseMatrix = new Matrix4();
	var _intersections = [];

	var _selected = null, _hovered = null;

	//

	var scope = this;

	function activate() {

		_domElement.addEventListener('mousemove', onDocumentMouseMove, false);
		_domElement.addEventListener('mousedown', onDocumentMouseDown, false);
		_domElement.addEventListener('mouseup', onDocumentMouseCancel, false);
		_domElement.addEventListener('mouseleave', onDocumentMouseCancel, false);
		_domElement.addEventListener('touchmove', onDocumentTouchMove, false);
		_domElement.addEventListener('touchstart', onDocumentTouchStart, false);
		_domElement.addEventListener('touchend', onDocumentTouchEnd, false);

	}

	function deactivate() {

		_domElement.removeEventListener('mousemove', onDocumentMouseMove, false);
		_domElement.removeEventListener('mousedown', onDocumentMouseDown, false);
		_domElement.removeEventListener('mouseup', onDocumentMouseCancel, false);
		_domElement.removeEventListener('mouseleave', onDocumentMouseCancel, false);
		_domElement.removeEventListener('touchmove', onDocumentTouchMove, false);
		_domElement.removeEventListener('touchstart', onDocumentTouchStart, false);
		_domElement.removeEventListener('touchend', onDocumentTouchEnd, false);

	}

	function dispose() {

		deactivate();

	}

	function getObjects() {

		return _objects;

	}

	function onDocumentMouseMove(event) {

		event.preventDefault();

		var rect = _domElement.getBoundingClientRect();

		_mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
		_mouse.y = - ((event.clientY - rect.top) / rect.height) * 2 + 1;

		_raycaster.setFromCamera(_mouse, _camera);

		if (_selected && scope.enabled) {

			if (_raycaster.ray.intersectPlane(_plane, _intersection)) {
				const offsetType = _selected.parent.spriteEdge.offsetType;
				if (offsetType === 'none') {
					return;
				}


				let pos = new THREE.Vector3().copy(_intersection.sub(_offset).applyMatrix4(_inverseMatrix));
				let direction = new THREE.Vector3().subVectors(pos, _selected.position).normalize();
				let distance = _selected.position.distanceTo(pos);
				let detal = _selected.tangent.angleTo(direction);
				let arcLength = _selected.parent.curve3.arcLengthDivisions * 2;
				let distanceAlongDirection = 0;
				const halfPI = Math.PI / 2;
				if (offsetType === 'merge') {
					if (detal < halfPI) {
						distanceAlongDirection = Math.cos(detal) * distance;
						_selected.position2.x += distanceAlongDirection / arcLength;
						if (_selected.position2.x > 1) {
							_selected.position2.x = 1;
						}
						//if (_selected.position3.x < _selected.position2.x + 0.1) {
						//	_selected.position3.x = _selected.position2.x + 0.1;
						//}
					} else if (detal > halfPI) {
						distanceAlongDirection = Math.cos(detal - halfPI) * distance;
						_selected.position2.x -= distanceAlongDirection / arcLength;
						if (_selected.position2.x < 0) {
							_selected.position2.x = 0;
						}
					}
				} else {
					if (detal < halfPI) {
						distanceAlongDirection = Math.cos(detal) * distance;
						_selected.position2.x += distanceAlongDirection / arcLength;
						if (_selected.position2.x > 1) {
							_selected.position2.x = 1;
						}
					} else if (detal > halfPI) {
						distanceAlongDirection = Math.cos(detal - halfPI) * distance;
						_selected.position2.x -= distanceAlongDirection / arcLength;
						if (_selected.position2.x < 0) {
							_selected.position2.x = 0;
						}
						//if (_selected.position3.x > _selected.position2.x - 0.1) {
						//	_selected.position3.x = _selected.position2.x - 0.1;
						//}
					}
				}

				//_selected.tangent.copy(_selected.parent.curve3.getTangent(_selected.position2.x));
				//_selected.position.copy(_selected.parent.curve3.getPoint(_selected.position2.x));
				_selected.tangent.copy(_selected.parent.curve02.getTangent(_selected.position2.x));
				_selected.position.copy(_selected.parent.curve02.getPoint(_selected.position2.x));
				_selected.quaternion.setFromUnitVectors(new THREE.Vector3(1, 0, 0), _selected.tangent);
			}

			scope.dispatchEvent({ type: 'drag', object: _selected });

			return;

		}

		_intersections.length = 0;

		_raycaster.setFromCamera(_mouse, _camera);
		_raycaster.intersectObjects(_objects, true, _intersections);

		_intersections = _intersections.filter(cs => cs.object.type === 'ControlSprite');
		if (_intersections.length > 0) {

			var object = _intersections[0].object;

			_plane.setFromNormalAndCoplanarPoint(_camera.getWorldDirection(_plane.normal), _worldPosition.setFromMatrixPosition(object.matrixWorld));

			if (_hovered !== object) {

				scope.dispatchEvent({ type: 'hoveron', object: object });

				_domElement.style.cursor = 'pointer';
				_hovered = object;

			}

		} else {

			if (_hovered !== null) {

				scope.dispatchEvent({ type: 'hoveroff', object: _hovered });

				_domElement.style.cursor = 'auto';
				_hovered = null;

			}

		}

	}

	function onDocumentMouseDown(event) {

		event.preventDefault();

		switch (event.button) {

			case 0:

				// mouseAction = scope.mouseButtons.LEFT;
				break;

			case 1:

				// mouseAction = scope.mouseButtons.MIDDLE;
				break;

			case 2:

				// mouseAction = scope.mouseButtons.RIGHT;

				_intersections.length = 0;

				_raycaster.setFromCamera(_mouse, _camera);
				_raycaster.intersectObjects(_objects, true, _intersections);

				_intersections = _intersections.filter(cs => cs.object.type === 'ControlSprite');
				if (_intersections.length > 0) {

					_selected = (scope.transformGroup === true) ? _objects[0] : _intersections[0].object;

					if (_raycaster.ray.intersectPlane(_plane, _intersection)) {

						_inverseMatrix.getInverse(_selected.parent.matrixWorld);
						_offset.copy(_intersection).sub(_worldPosition.setFromMatrixPosition(_selected.matrixWorld));

					}

					_domElement.style.cursor = 'move';

					scope.dispatchEvent({ type: 'dragstart', object: _selected });

				}

				break;

			default:

			// mouseAction = - 1;

		}

	}

	function onDocumentMouseCancel(event) {

		event.preventDefault();

		if (_selected) {

			scope.dispatchEvent({ type: 'dragend', object: _selected });

			_selected = null;

		}

		_domElement.style.cursor = _hovered ? 'pointer' : 'auto';

	}

	function onDocumentTouchMove(event) {

		event.preventDefault();
		event = event.changedTouches[0];

		var rect = _domElement.getBoundingClientRect();

		_mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
		_mouse.y = - ((event.clientY - rect.top) / rect.height) * 2 + 1;

		_raycaster.setFromCamera(_mouse, _camera);

		if (_selected && scope.enabled) {

			if (_raycaster.ray.intersectPlane(_plane, _intersection)) {

				_selected.position.copy(_intersection.sub(_offset).applyMatrix4(_inverseMatrix));

			}

			scope.dispatchEvent({ type: 'drag', object: _selected });

			return;

		}

	}

	function onDocumentTouchStart(event) {

		event.preventDefault();
		event = event.changedTouches[0];

		var rect = _domElement.getBoundingClientRect();

		_mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
		_mouse.y = - ((event.clientY - rect.top) / rect.height) * 2 + 1;

		_intersections.length = 0;

		_raycaster.setFromCamera(_mouse, _camera);
		_raycaster.intersectObjects(_objects, true, _intersections);

		_intersections = _intersections.filter(cs => cs.object.type === 'ControlSprite');
		if (_intersections.length > 0) {

			_selected = (scope.transformGroup === true) ? _objects[0] : _intersections[0].object;

			_plane.setFromNormalAndCoplanarPoint(_camera.getWorldDirection(_plane.normal), _worldPosition.setFromMatrixPosition(_selected.matrixWorld));

			if (_raycaster.ray.intersectPlane(_plane, _intersection)) {

				_inverseMatrix.getInverse(_selected.parent.matrixWorld);
				_offset.copy(_intersection).sub(_worldPosition.setFromMatrixPosition(_selected.matrixWorld));

			}

			_domElement.style.cursor = 'move';

			scope.dispatchEvent({ type: 'dragstart', object: _selected });

		}


	}

	function onDocumentTouchEnd(event) {

		event.preventDefault();

		if (_selected) {

			scope.dispatchEvent({ type: 'dragend', object: _selected });

			_selected = null;

		}

		_domElement.style.cursor = 'auto';

	}

	activate();

	// API

	this.enabled = true;
	this.transformGroup = false;

	this.activate = activate;
	this.deactivate = deactivate;
	this.dispose = dispose;
	this.getObjects = getObjects;

};

DragHelper.prototype = Object.create(EventDispatcher.prototype);
DragHelper.prototype.constructor = DragHelper;

export { DragHelper };
