function ExtrudeRouteGroup(name, routes, roads, options, trackInfo, layers) {

	THREE.Object3D.call(this);

	this.timeLine = [];

	this.type = 'ExtrudeRouteGroup';

	this.name = name;

	this.parameters = {
		routes: routes,
		roads: roads,
		options: options
	};

	if (trackInfo) {
		trackInfo.push({
			type: THREE.VectorKeyframeTrack,
			propertyPath: this.name + '.position',
			initialValue: [0, 0, 0],
			interpolation: THREE.InterpolateSmooth
		});
		trackInfo.push({
			type: THREE.QuaternionKeyframeTrack,
			propertyPath: this.name + '.quaternion',
			initialValue: [0, 0, 0, 1],
			interpolation: THREE.InterpolateLinear

		});
	}

	this.color = new THREE.Color(options && options.color ? options.color : 0x0080ff);
	this.colorHexString = "#" + this.color.getHexString();
	this.trackMesh = new THREE.Mesh(new THREE.BoxBufferGeometry(5, 2, 2), new THREE.MeshLambertMaterial({ color: this.color }));
	this.trackMesh.name = this.name;
	this.trackMesh.castShadow = true;
	this.trackMesh.receiveShadow = true;

	const material = new THREE.ShaderMaterial({
		uniforms: {
			color: {
				value: this.color
			},
		},
		vertexShader: document.getElementById('vertexshader').textContent,
		fragmentShader: document.getElementById('fragmentshader').textContent
	});



	this.layer = {
		"name": this.name,
		"values": [],
		"tmpValue": 0,
		"_color": this.colorHexString
	};

	var routes = Array.isArray(routes) ? routes : [routes];
	var roads = Array.isArray(roads) ? roads : [roads];

	for (var route of routes) {

		var routeCurve = new Curves.RouteCurve3(route, roads);
		var routeFragments = routeCurve.routeFragments;

		if (routeCurve.points.length > 0) {

			// ########################################################
			// 大きいウェイポイントを描画
			var bigWayPointGroup = new THREE.Group();
			var scales = new Float32Array(routeCurve.points.length).fill(1);

			var p1 = new THREE.Vector3(), p2 = new THREE.Vector3();
			var l, v, t = 0, prev_t = Math.trunc(t);

			bigWayPointGroup.add(createPointLabel(routeCurve.points[0].x, routeCurve.points[0].y, routeCurve.points[0].z, 45, 90, Math.trunc(t), this.color));

			var offsetCurve = getOffsetCurve3(routeCurve, routeCurve.points.length - 1, 0, -0.5);
			var tan1 = new THREE.Vector3(-1, 0, 0);
			var tan2 = new THREE.Vector3();
			var ptl = new THREE.Vector3();
			var qtl = new THREE.Quaternion();
			tan2 = offsetCurve.getTangent(0);
			ptl.copy(offsetCurve.points[0]);
			//qtl.setFromUnitVectors(tan1, tan2);
			qtl.copy(setQuaternionFromDirection(tan2));
			this.timeLine.push({ time: t, position: ptl.clone(), quaternion: qtl.clone() });

			this.layer["values"].push({ "time": t, "value": ptl.clone().x, "_color": this.colorHexString, "tween": routeFragments[0].type });

			for (var i = 1; i < routeCurve.points.length; i++) {
				p1.set(routeCurve.points[i].x, routeCurve.points[i].y, routeCurve.points[i].z);
				p2.set(routeCurve.points[i - 1].x, routeCurve.points[i - 1].y, routeCurve.points[i - 1].z);
				l = p1.distanceTo(p2);
				v = routeCurve.velocities[i] / 3.6;

				// 経過時間を求める
				if (v > 0) {
					t += l / v;
				} else {
					t = 0;
				}

				if (prev_t !== Math.trunc(t)) {
					scales[i] = 0;
					bigWayPointGroup.add(createPointLabel(routeCurve.points[i].x, routeCurve.points[i].y, routeCurve.points[i].z, 45, 90, Math.trunc(t), this.color));
				} else {
					scales[i] = 1;
				}

				tan2 = offsetCurve.getTangent(i / offsetCurve.points.length);
				ptl.copy(offsetCurve.points[i]);
				//qtl.setFromUnitVectors(tan1, tan2);
				qtl.copy(setQuaternionFromDirection(tan2));
				this.timeLine.push({ time: t, position: ptl.clone(), quaternion: qtl.clone() });

				for (var fragment of routeFragments) {
					if (i === Math.min(fragment.index, routeCurve.points.length - 1)) {
						this.layer["values"].push({ "time": t, "value": ptl.clone().x, "_color": this.colorHexString, "tween": fragment.type });
					}
				}

				prev_t = Math.trunc(t);
			}

			// ########################################################

			var geometry = new THREE.BufferGeometry().setFromPoints(routeCurve.points);
			geometry.setAttribute('scale', new THREE.BufferAttribute(scales, 1));

			var routePoints = new THREE.Points(geometry, material);
			// routePoints.geometry.attributes.position.needsUpdate = true;
			// routePoints.geometry.attributes.scale.needsUpdate = true;
			routePoints.add(bigWayPointGroup);
			this.add(routePoints);
		}

	}

	function createPointLabel(x, y, z, labelWidth, size, name, color) {
		const canvas = createLabelCanvas(labelWidth, size, name, color);
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
		label.center = new THREE.Vector2(0.5, 0.5);

		const labelBaseScale = 0.01;
		label.scale.x = canvas.width * labelBaseScale;
		label.scale.y = canvas.height * labelBaseScale;

		return label;
	}

	function createLabelCanvas(baseWidth, size, name, color) {
		const ctx = document.createElement('canvas').getContext('2d');
		const font = `${size}px bold sans-serif`;
		ctx.font = font;

		const textWidth = ctx.measureText(name).width;
		const width = baseWidth * 2;
		const height = baseWidth * 2;
		ctx.canvas.width = width;
		ctx.canvas.height = height;

		ctx.font = font;
		ctx.textBaseline = 'middle';
		ctx.textAlign = 'center';

		ctx.strokeStyle = 'white';
		ctx.lineWidth = 0;
		ctx.arc(baseWidth, baseWidth, baseWidth, 0, 2 * Math.PI);
		ctx.fillStyle = '#' + color.getHexString();
		ctx.fill();

		const scaleFactor = Math.min(1, baseWidth / textWidth);
		ctx.translate(width / 2, height / 2);
		ctx.scale(scaleFactor, 1);
		ctx.fillStyle = 'white';
		ctx.fillText(name, 0, 0);

		return ctx.canvas;
	}
}

ExtrudeRouteGroup.prototype = Object.assign(Object.create(THREE.Object3D.prototype), {

	constructor: ExtrudeRouteGroup,

	isGroup: true

});