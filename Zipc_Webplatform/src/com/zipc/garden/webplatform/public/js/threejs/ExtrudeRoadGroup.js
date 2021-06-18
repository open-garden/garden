function ExtrudeRoadGroup(roads, options) {

	THREE.Object3D.call(this);

	this.type = 'ExtrudeRoadGroup';

	this.parameters = {
		roads: roads,
		options: options
	};

	/*var textureLoader = new THREE.TextureLoader();
	var bricks = textureLoader.load('files/textures/material_764.png');
	bricks.wrapS = THREE.RepeatWrapping;
	bricks.wrapT = THREE.RepeatWrapping;
	bricks.repeat.set(9, 1);*/

	const DEFAULT_OFFSET_HEIGHT = -1;
	const materials = new Map([
		["driving_line_material", new THREE.LineBasicMaterial({ color: 0x999999, linewidth: 0.01, transparent: false })],
		["driving_mesh_material", new THREE.MeshMatcapMaterial({ color: 0xcccccc, side: THREE.DoubleSide, flatShading: true })],
		["border_line_material", new THREE.LineBasicMaterial({ color: 0x999999, transparent: false })],
		["border_mesh_material", new THREE.MeshMatcapMaterial({ color: 0x888888, side: THREE.DoubleSide, flatShading: true })]
	]);

	var worldPosition = new THREE.Vector3();
	var worldRotation = new THREE.Euler(0, 0, 0, "XYZ");
	// var wayPointTexture = textureLoader.load('files/textures/sprites/circle.png');

	var roads = Array.isArray(roads) ? roads : [roads];

	for (var road of roads) {
		if (road === null)
			continue;

		road.lanes = road.lanes.filter(lane => lane !== null && ["center", "left", "right"].indexOf(lane.position) != -1).sort(function (a, b) {
			if (a.position > b.position) {
				return 1;
			} else if (a.position < b.position) {
				return -1;
			}
			return 0;
		});

		const connectionObject = findConnectionObject(roads, road.connection);
		if (connectionObject) {
			if (road.reverse) {
				worldPosition.copy(connectionObject.connectionInfo.negative.position);
				worldRotation.copy(connectionObject.connectionInfo.negative.rotation);
			} else {
				worldPosition.copy(connectionObject.connectionInfo.positive.position);
				worldRotation.copy(connectionObject.connectionInfo.positive.rotation);
			}
		} else {
			worldPosition = new THREE.Vector3();
			worldRotation = new THREE.Euler(0, 0, 0, "XYZ");
		}

		road.connectionInfo = {};
		road.connectionInfo.world = {
			position: worldPosition.clone(),
			rotation: worldRotation.clone(),
		};

		initBaseCurvePath(road);

		var leftLaneOffset = 0;
		var rightLaneOffset = 0;
		var roadGroup = new THREE.Group();
		for (var lane of road.lanes) {
			if (lane.position === "left") {
				leftLaneOffset += lane.width / 2;
				lane.widthOffset = leftLaneOffset;
				leftLaneOffset += lane.width / 2;
			} else if (lane.position === "right") {
				rightLaneOffset -= lane.width / 2;
				lane.widthOffset = rightLaneOffset;
				rightLaneOffset -= lane.width / 2;
			} else if (lane.position === "center") {
				lane.widthOffset = 0;
			}

			lane.connectionInfo = {};
			lane.connectionInfo.world = {
				position: road.connectionInfo.world.position.clone(),
				rotation: road.connectionInfo.world.rotation.clone()
			};
			var group = new THREE.Group();
			var geometry = new THREE.BufferGeometry();
			geometry.setAttribute('position', new THREE.Float32BufferAttribute([], 3));
			group.add(new THREE.LineSegments(geometry, materials.get(lane.type + "_line_material")));
			group.add(new THREE.Mesh(geometry, materials.get(lane.type + "_mesh_material")));
			createLane(group, road, lane);
			roadGroup.add(group);
		}

		this.add(roadGroup);
	}

	//#####################################



	function setConnectionInfo(target, path, reverse, mirrored) {
		target.connectionInfo.positive = {
			position: new THREE.Vector3(),
			rotation: new THREE.Euler(0, 0, 0, "XYZ")
		};
		target.connectionInfo.negative = {
			position: new THREE.Vector3(),
			rotation: new THREE.Euler(0, 0, 0, "XYZ")
		};

		// self position, direction
		var startPosition = path.getPointAt(0);
		var startDirection = path.getTangentAt(0);
		var endPosition = path.getPointAt(1);
		var endDirection = path.getTangentAt(1);

		var baseAxis = XAxis.clone();
		if (target.position && target.position === "right") {
			target.connectionInfo.positive.position.copy(startPosition);
			target.connectionInfo.positive.rotation.y = baseAxis.clone().angleTo(new THREE.Vector3(-startDirection.x, 0, -startDirection.z)) * (-startDirection.z < 0 ? -1 : 1);
			target.connectionInfo.positive.rotation.z = new THREE.Vector3(-startDirection.x, 0, -startDirection.z).angleTo(startDirection.negate()) * (-startDirection.y > 0 ? -1 : 1);
			target.connectionInfo.negative.position.copy(endPosition);
			target.connectionInfo.negative.rotation.y = baseAxis.clone().negate().angleTo(new THREE.Vector3(endDirection.x, 0, endDirection.z)) * (endDirection.z < 0 ? 1 : -1);
			target.connectionInfo.negative.rotation.z = new THREE.Vector3(endDirection.x, 0, endDirection.z).angleTo(endDirection) * (endDirection.y > 0 ? -1 : 1);
		} else {
			target.connectionInfo.positive.position.copy(endPosition);
			target.connectionInfo.positive.rotation.y = baseAxis.clone().angleTo(new THREE.Vector3(endDirection.x, 0, endDirection.z)) * (endDirection.z < 0 ? -1 : 1);
			target.connectionInfo.positive.rotation.z = new THREE.Vector3(endDirection.x, 0, endDirection.z).angleTo(endDirection) * (endDirection.y > 0 ? 1 : -1);
			target.connectionInfo.negative.position.copy(startPosition);
			target.connectionInfo.negative.rotation.y = baseAxis.clone().negate().angleTo(new THREE.Vector3(-startDirection.x, 0, -startDirection.z)) * (-startDirection.z < 0 ? 1 : -1);
			target.connectionInfo.negative.rotation.z = new THREE.Vector3(-startDirection.x, 0, -startDirection.z).angleTo(startDirection.negate()) * (-startDirection.y > 0 ? -1 : 1);
		}
	}

	function findConnectionObject(roads, connectionId) {
		if (connectionId !== undefined && connectionId !== null && connectionId !== "") {
			for (var road of roads) {
				if (road === null) continue;
				if (road !== null && road.id === connectionId) {
					return road;
				}
				for (var lane of road.lanes) {
					if (lane !== null && lane.id === connectionId) {
						return lane;
					}
				}
			}
		}
	}

	function offsetCurve(target, curve, steps, offset, reverse) {
		var tempPoints = [];
		var tempPoint = new THREE.Vector3();
		var extrudePts1 = curve.getSpacedPoints(steps);
		for (var s = 0; s <= steps; s++) {
			tempPoint.copy(extrudePts1[s]);
			tempPoints.push(new THREE.Vector3(tempPoint.x, 0, tempPoint.z));
		}
		var tempCurve = new THREE.CatmullRomCurve3(tempPoints);

		var points3D = new Array();
		var normal = new THREE.Vector3();
		var position2 = new THREE.Vector3();
		var extrudePts = tempCurve.getSpacedPoints(steps);
		var splineTube = tempCurve.computeFrenetFrames(steps);

		for (var s = 0; s <= steps; s++) {
			normal.copy(splineTube.normals[s]).multiplyScalar(offset);
			position2.copy(extrudePts[s]).add(normal);
			points3D.push(new THREE.Vector3(position2.x, extrudePts1[s].y, position2.z));
		}

		return new THREE.CatmullRomCurve3(points3D);
	}

	function initBaseCurvePath(road) {
		if (road.lanes.length < 1) {
			// Laneないので、道路作れない
		} else {
			// 円の軌跡を作成する
			const baseLane = road.lanes[0];

			// center曲線の情報登録
			if (road.type === "circular") {
				road.curve00 = new Curves.CircleRoadCurve3(-road.radius, road.length, road.height, road.angle, road.reverse, false, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
				road.curve10 = new Curves.CircleRoadCurve3(-road.radius, road.length, road.height, road.angle, road.reverse, true, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
			} else if (road.type === "cubic_left" || road.type === "cubic_right") {
				road.curve00 = new Curves.CubicRoadCurve3(road.type === "cubic_right", road.length, baseLane.width / 2, road.height, road.angle, road.reverse, false, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
				road.curve10 = new Curves.CubicRoadCurve3(road.type === "cubic_right", road.length, baseLane.width / 2, road.height, road.angle, road.reverse, true, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
			} else if (road.type === "clothoid_in" || road.type === "clothoid_out") {
				road.curve00 = new Curves.ClothoidRoadCurve3(road.type === "clothoid_out" ^ road.reverse, -road.radius, road.length, road.height, road.angle, road.reverse, false, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
				road.curve10 = new Curves.ClothoidRoadCurve3(road.type === "clothoid_out" ^ road.reverse, -road.radius, road.length, road.height, road.angle, road.reverse, true, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
			} else { // 直線道路"straight" と その他
				road.curve00 = new Curves.StraightRoadCurve3(road.length, road.height, road.angle, road.reverse, false, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
				road.curve10 = new Curves.StraightRoadCurve3(road.length, road.height, road.angle, road.reverse, true, road.connectionInfo.world.position, road.connectionInfo.world.rotation);
			}

			// left曲線の情報登録
			road.curve01 = offsetCurve(road, road.curve00, road.length * 2, -(baseLane.position === "center" ? baseLane.width / 2 : 0), road.reverse);

			// right曲線の情報登録
			road.curve11 = offsetCurve(road, road.curve10, road.length * 2, -(baseLane.position === "center" ? baseLane.width / 2 : 0), !road.reverse);

			// RoadのConnection情報登録
			setConnectionInfo(road, road.curve00.clone(), road.reverse);
		}
	}

	function createLaneLabel(x, y, z, labelWidth, size, name, mirror) {
		const canvas = createLabelCanvas(labelWidth, size, name);
		const texture = new THREE.CanvasTexture(canvas);
		texture.minFilter = THREE.LinearFilter;
		texture.wrapS = THREE.ClampToEdgeWrapping;
		texture.wrapT = THREE.ClampToEdgeWrapping;

		const labelMaterial = new THREE.SpriteMaterial({
			map: texture,
			transparent: true,
		});

		const label = new THREE.Sprite(labelMaterial);
		label.position.x = x;
		label.position.y = y + 0.15;
		label.position.z = z;
		label.center = new THREE.Vector2(mirror ? 1 : 0, 0.5);

		const labelBaseScale = 0.01;
		label.scale.x = canvas.width * labelBaseScale;
		label.scale.y = canvas.height * labelBaseScale;

		return label;
	}

	function createLabelCanvas(baseWidth, size, name) {
		const borderSize = 2;
		const ctx = document.createElement('canvas').getContext('2d');
		const font = `${size}px bold sans-serif`;
		ctx.font = font;

		const textWidth = ctx.measureText(name).width;
		const doubleBorderSize = borderSize * 2;
		const width = baseWidth + doubleBorderSize;
		const height = size + doubleBorderSize;
		ctx.canvas.width = width;
		ctx.canvas.height = height;

		ctx.font = font;
		ctx.textBaseline = 'middle';
		ctx.textAlign = 'center';

		ctx.strokeStyle = 'white';
		ctx.lineWidth = 0;
		ctx.fillStyle = '#3399ff';
		roundRect(ctx, 0, 0, width, height, 5);

		const scaleFactor = Math.min(1, baseWidth / textWidth);
		ctx.translate(width / 2, height / 2);
		ctx.scale(scaleFactor, 1);
		ctx.fillStyle = 'white';
		ctx.fillText(name, 0, 0);

		return ctx.canvas;
	}

	function roundRect(ctx, x, y, w, h, r) {
		ctx.beginPath();
		ctx.moveTo(x + r, y);
		ctx.lineTo(x + w - r, y);
		ctx.quadraticCurveTo(x + w, y, x + w, y + r);
		ctx.lineTo(x + w, y + h - r);
		ctx.quadraticCurveTo(x + w, y + h, x + w - r, y + h);
		ctx.lineTo(x + r, y + h);
		ctx.quadraticCurveTo(x, y + h, x, y + h - r);
		ctx.lineTo(x, y + r);
		ctx.quadraticCurveTo(x, y, x + r, y);
		ctx.closePath();
		ctx.fill();
		ctx.stroke();
	}

	function createLane(mesh, road, lane) {
		var curve;

		if (lane.position === "center") {
			curve = road.curve00.clone();
			lane.curve00 = getOffsetCurve3(road.curve00.clone(), road.curve00.points.length - 1, 0, DEFAULT_OFFSET_HEIGHT);
		} else if (lane.position === "left") {
			setConnectionInfo(lane, road.curve01.clone(), road.reverse);
			road.curve01 = offsetCurve(lane, road.curve01, road.length, -lane.width / 2, road.reverse);
			curve = road.curve01.clone();
			lane.curve00 = getOffsetCurve3(road.curve01.clone(), road.curve01.points.length - 1, 0, DEFAULT_OFFSET_HEIGHT);
			road.curve01 = offsetCurve(lane, road.curve01, road.length, -lane.width / 2, road.reverse);
		} else if (lane.position === "right") {
			setConnectionInfo(lane, road.curve11.clone(), road.reverse);
			road.curve11 = offsetCurve(lane, road.curve11, road.length, -lane.width / 2, !road.reverse);
			curve = road.curve11.clone();
			lane.curve00 = getOffsetCurve3(road.curve11.clone(), road.curve11.points.length - 1, 0, DEFAULT_OFFSET_HEIGHT);
			road.curve11 = offsetCurve(lane, road.curve11, road.length, -lane.width / 2, !road.reverse);
		}
		// var length = lane.width, width = 0.1;
		var shape = new THREE.Shape();
		var length = lane.width, width = 0.5;

		shape.moveTo(-length / 2, -width / 2);
		shape.lineTo(-length / 2, width / 2);
		shape.lineTo(length / 2, width / 2);
		shape.lineTo(length / 2, -width / 2);
		shape.lineTo(-length / 2, -width / 2);

		var data = {
			steps: road.length,
			extrudePath: curve,
			bevelEnabled: false
		};

		function generateGeometry() {
			var geometry = new THREE.ExtrudeBufferGeometry(shape, data);

			var laneStart = lane.curve00.getPointAt(0);
			var laneLabel = createLaneLabel(laneStart.x, laneStart.y, laneStart.z, 320, 60, lane.id, lane.position === 'right');

			updateLaneGeometry(mesh, geometry, laneLabel);
		}
		generateGeometry();
	}

	function updateLaneGeometry(mesh, geometry, label) {
		if (geometry.isGeometry) {
			geometry = new THREE.BufferGeometry().fromGeometry(geometry);
			console.warn('THREE.GeometryBrowser: Converted Geometry to BufferGeometry.');
		}
		mesh.children[0].geometry.dispose();
		mesh.children[1].geometry.dispose();
		mesh.children[0].geometry = new THREE.EdgesGeometry(geometry);
		mesh.children[1].geometry = geometry;
		mesh.add(label);
	}
}

ExtrudeRoadGroup.prototype = Object.assign(Object.create(THREE.Object3D.prototype), {

	constructor: ExtrudeRoadGroup,

	isGroup: true

});