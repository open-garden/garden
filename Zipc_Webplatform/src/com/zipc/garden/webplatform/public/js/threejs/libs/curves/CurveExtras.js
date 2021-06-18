Curves = (function () {

	// ################################
	// ######    Road Curve    ########
	// ################################

	// CubicRoadCurve3

	function CubicRoadCurve3(rType, rLength, rWidth, rHeight, rAngle, rReverse, rMirror, rPosition, rRotation) {

		THREE.CatmullRomCurve3.call(this);

		this.rType = rType || false;
		this.rLength = rLength || 10;
		this.rWidth = rWidth || 1;
		this.rHeight = rHeight || 0;
		this.rAngle = rAngle || 0;
		this.rReverse = rReverse || false;
		this.rMirror = rMirror || false;
		this.rPosition = rPosition || new THREE.Vector3();
		this.rRotation = rRotation || new THREE.Euler(0, 0, 0, "XYZ");

		this.initPoint();
	}

	CubicRoadCurve3.prototype = Object.create(THREE.CatmullRomCurve3.prototype);
	CubicRoadCurve3.prototype.constructor = CubicRoadCurve3;

	CubicRoadCurve3.prototype.initPoint = function () {

		this.points = [];
		var v0, v1, v2, v3;
		var x, y, z;

		if (this.rReverse) {
			v0 = new THREE.Vector3(-this.rLength, 0, (this.rType ? -this.rWidth : this.rWidth));
			v3 = new THREE.Vector3(v0.x + this.rLength, v0.y, v0.z + (this.rType ? this.rWidth : -this.rWidth));
			v1 = new THREE.Vector3((v0.x + v3.x) / 2, v0.y, v0.z);
			v2 = new THREE.Vector3((v0.x + v3.x) / 2, v0.y, v3.z);
		} else {
			v0 = new THREE.Vector3(0, 0, 0);
			v3 = new THREE.Vector3(v0.x + this.rLength, v0.y, v0.z + (this.rType ? this.rWidth : -this.rWidth));
			v1 = new THREE.Vector3((v0.x + v3.x) / 2, v0.y, v0.z);
			v2 = new THREE.Vector3((v0.x + v3.x) / 2, v0.y, v3.z);
		}

		var point = new THREE.Vector3();
		var pathCurve3 = new THREE.CubicBezierCurve3(v0, v1, v2, v3);
		var rampCurve3 = new RampRoadCurve3(this.rLength, this.rHeight, this.rAngle, this.rRotation.z);

		var t1, t2;
		for (var t = 0; t <= this.rLength; t++) {
			t1 = this.rMirror ? this.rLength - t : t;
			t2 = this.rReverse ? (1 - t1 / this.rLength) : (t1 / this.rLength);

			point.copy(pathCurve3.getPointAt(t1 / this.rLength));
			x = this.rPosition.x + point.x;
			y = this.rPosition.y + rampCurve3.getPointAt(t2).y;
			z = this.rPosition.z + point.z;

			if (this.rRotation.y !== 0) {

				var cos = Math.cos(this.rRotation.y);
				var sin = Math.sin(this.rRotation.y);

				var tx = x - this.rPosition.x;
				var tz = z - this.rPosition.z;

				x = tx * cos - tz * sin + this.rPosition.x;
				z = tx * sin + tz * cos + this.rPosition.z;

			}

			this.points.push(new THREE.Vector3(x, y, z));
		}

	};

	// ClothoidRoadCurve3

	function ClothoidRoadCurve3(rType, rRadius, rLength, rHeight, rAngle, rReverse, rMirror, rPosition, rRotation) {

		THREE.CatmullRomCurve3.call(this);

		this.rType = rType || false;
		this.rRadius = rRadius || 10;
		this.rLength = rLength || 10;
		this.rHeight = rHeight || 0;
		this.rAngle = rAngle || 0;
		this.rReverse = rReverse || false;
		this.rMirror = rMirror || false;
		this.rPosition = rPosition || new THREE.Vector3();
		this.rRotation = rRotation || new THREE.Euler(0, 0, 0, "XYZ");

		this.initPoint();
	}

	ClothoidRoadCurve3.prototype = Object.create(THREE.CatmullRomCurve3.prototype);
	ClothoidRoadCurve3.prototype.constructor = ClothoidRoadCurve3;

	ClothoidRoadCurve3.prototype.initPoint = function () {

		this.points = [];
		var tempPoints = [];
		var rampCurve3 = new RampRoadCurve3(this.rLength, this.rHeight, this.rAngle, this.rRotation.z);

		var L0 = this.rLength, R0 = Math.abs(this.rRadius);
		var dL, dx, dz, A2;
		var x, y, z, dtheta, R, L;
		var len;
		len = Math.trunc(L0);
		// 計算間隔(1m刻み)
		dL = 1;
		x = 0;
		y = 0;
		z = 0;
		dx = 1;
		dz = 0;
		A2 = L0 * Math.abs(R0);
		// 線の長さ
		L = dL;

		tempPoints.push(new THREE.Vector3(x, y, z));

		for (var i = 1; i < len; i++) {
			R = A2 / L;
			if ((R < 1.0) || (L > 1000)) {
				break;
			}

			dtheta = dL / R;
			x += dx * dL;
			y = rampCurve3.getPointAt(i / len).y;
			z += dz * dL;

			// 半径がマイナスの場合逆巻きにする
			tempPoints.push(new THREE.Vector3(x, y, this.rRadius < 0 ? -z : z));
			dx = dx - dz * dtheta;
			dz = dz + dx * dtheta;

			L = L + dL;
		}

		if (this.rType) {

			var tempPoints2 = JSON.parse(JSON.stringify(tempPoints));
			for (var t = 0; t < this.rLength; t++) {
				tempPoints[t].x = tempPoints2[this.rLength - 1 - t].x;
				tempPoints[t].z = -tempPoints2[this.rLength - 1 - t].z;
			}

			var tempPoint;
			var p1 = tempPoints[0].clone(), p2 = tempPoints[1].clone();
			var offsetX = -p1.x;
			var offsetY = -p1.y;
			var offsetZ = -p1.z;
			var theta = Math.atan2(p1.z - p2.z, p2.x - p1.x);
			var x, y, z;
			for (var t = 0; t < this.rLength; t++) {
				tempPoint = tempPoints[t];
				x = offsetX + tempPoint.x;
				y = offsetY + tempPoint.y;
				z = offsetZ + tempPoint.z;

				if (theta !== 0) {

					var cos = Math.cos(theta);
					var sin = Math.sin(theta);

					var tx = x;
					var tz = z;

					x = tx * cos - tz * sin;
					z = tx * sin + tz * cos;
				}

				tempPoint.x = x;
				tempPoint.y = y;
				tempPoint.z = z;
			}
		}

		var t1;
		for (var t = 0; t < this.rLength; t++) {
			t1 = this.rMirror ^ this.rReverse ? this.rLength - t - 1 : t;
			tempPoint = tempPoints[t1];
			x = this.rPosition.x + (this.rReverse ? -tempPoint.x : tempPoint.x);
			y = this.rPosition.y + tempPoint.y;
			z = this.rPosition.z + tempPoint.z;

			if (this.rRotation.y !== 0) {

				var cos = Math.cos(this.rRotation.y);
				var sin = Math.sin(this.rRotation.y);

				var tx = x - this.rPosition.x;
				var tz = z - this.rPosition.z;

				x = tx * cos - tz * sin + this.rPosition.x;
				z = tx * sin + tz * cos + this.rPosition.z;

			}
			this.points.push(new THREE.Vector3(x, y, z));
		}
	};

	// StraightRoadCurve3

	function StraightRoadCurve3(rLength, rHeight, rAngle, rReverse, rMirror, rPosition, rRotation) {

		THREE.CatmullRomCurve3.call(this);

		this.rLength = rLength || 10;
		this.rHeight = rHeight || 0;
		this.rAngle = rAngle || 0;
		this.rReverse = rReverse || false;
		this.rMirror = rMirror || false;
		this.rPosition = rPosition || new THREE.Vector3();
		this.rRotation = rRotation || new THREE.Euler(0, 0, 0, "XYZ");

		this.initPoint();
	}

	StraightRoadCurve3.prototype = Object.create(THREE.CatmullRomCurve3.prototype);
	StraightRoadCurve3.prototype.constructor = StraightRoadCurve3;

	StraightRoadCurve3.prototype.initPoint = function () {

		this.points = [];
		var v1, v2;
		var x, y, z;

		if (this.rReverse) {
			v1 = new THREE.Vector3(-this.rLength, 0, 0);
			v2 = new THREE.Vector3(0, 0, 0);
		} else {
			v1 = new THREE.Vector3(0, 0, 0);
			v2 = new THREE.Vector3(this.rLength, 0, 0);
		}

		var point = new THREE.Vector3();
		var pathCurve3 = new THREE.LineCurve3(v1, v2);
		var rampCurve3 = new RampRoadCurve3(this.rLength, this.rHeight, this.rAngle, this.rRotation.z);

		var t1, t2;
		for (var t = 0; t <= this.rLength; t++) {
			t1 = this.rMirror ? this.rLength - t : t;
			t2 = this.rReverse ? (1 - t1 / this.rLength) : (t1 / this.rLength);

			point.copy(pathCurve3.getPointAt(t1 / this.rLength));
			x = this.rPosition.x + point.x;
			y = this.rPosition.y + rampCurve3.getPointAt(t2).y;
			z = this.rPosition.z + point.z;

			if (this.rRotation.y !== 0) {

				var cos = Math.cos(this.rRotation.y);
				var sin = Math.sin(this.rRotation.y);

				var tx = x - this.rPosition.x;
				var tz = z - this.rPosition.z;

				x = tx * cos - tz * sin + this.rPosition.x;
				z = tx * sin + tz * cos + this.rPosition.z;

			}

			this.points.push(new THREE.Vector3(x, y, z));
		}
	};

	// CircleRoadCurve3

	function CircleRoadCurve3(rRadius, rLength, rHeight, rAngle, rReverse, rMirror, rPosition, rRotation) {

		THREE.CatmullRomCurve3.call(this);

		this.rRadius = rRadius || 10;
		this.rLength = rLength || 10;
		this.rHeight = rHeight || 0;
		this.rAngle = rAngle || 0;
		this.rReverse = rReverse || false;
		this.rMirror = rMirror || false;
		this.rPosition = rPosition || new THREE.Vector3();
		this.rRotation = rRotation || new THREE.Euler(0, 0, 0, "XYZ");

		this.initPoint();
	}

	CircleRoadCurve3.prototype = Object.create(THREE.CatmullRomCurve3.prototype);
	CircleRoadCurve3.prototype.constructor = CircleRoadCurve3;

	CircleRoadCurve3.prototype.initPoint = function () {

		this.points = [];
		var a = this.rRadius;
		var b = this.rLength;
		var c = this.rHeight;
		var t1, t2, t3, x, y, z;

		var arcLength = Math.abs(twoPi * this.rRadius);

		var rampCurve3 = new RampRoadCurve3(this.rLength, this.rHeight, this.rAngle, this.rRotation.z);

		var sIndex = this.rReverse ? arcLength - b : 0;
		var eIndex = this.rReverse ? arcLength : b;
		for (var t = sIndex; t <= eIndex; t++) {
			t1 = this.rMirror ? eIndex - t + sIndex : t;
			t2 = startAngle + t1 / a;
			t3 = this.rReverse ? (1 - (t1 - sIndex) / b) : ((t1 - sIndex) / b);
			x = this.rPosition.x + Math.cos(t2) * a;
			y = this.rPosition.y + rampCurve3.getPointAt(t3).y;
			z = this.rPosition.z + Math.sin(t2) * a + a;

			if (this.rRotation.y !== 0) {

				var cos = Math.cos(this.rRotation.y);
				var sin = Math.sin(this.rRotation.y);

				var tx = x - this.rPosition.x;
				var tz = z - this.rPosition.z;

				x = tx * cos - tz * sin + this.rPosition.x;
				z = tx * sin + tz * cos + this.rPosition.z;

			}

			this.points.push(new THREE.Vector3(x, y, z));
		}
	};

	// RampRoadCurve3

	function RampRoadCurve3(rLength, rHeight, rAngle, rRotationZ) {

		THREE.Curve.call(this);

		this.rLength = rLength || 10;
		this.rHeight = rHeight || 0;
		this.rAngle = rAngle || 0;
		this.rRotationZ = rRotationZ || 0;

		if (this.rHeight !== 0 && this.rAngle !== 0) {
			this.type = 'CubicBezierCurve3';
		} else {
			this.type = 'LineCurve3';
		}

		this.initPoint();
	}

	RampRoadCurve3.prototype = Object.create(THREE.Curve.prototype);
	RampRoadCurve3.prototype.constructor = RampRoadCurve3;

	RampRoadCurve3.prototype.initPoint = function () {

		var cx = this.rLength - Math.abs(this.rHeight) / Math.tan(this.rAngle * THREE.MathUtils.DEG2RAD);

		this.v0 = new THREE.Vector3(0, 0, 0);
		this.v1 = new THREE.Vector3(cx, 0, 0);
		this.v2 = new THREE.Vector3(cx, 0, 0);
		this.v3 = new THREE.Vector3(this.rLength, this.rHeight, 0);
	};

	RampRoadCurve3.prototype.getPoint = function (t, optionalTarget) {

		var point = optionalTarget || new THREE.Vector3();

		if (this.type === 'CubicBezierCurve3') {
			var v0 = this.v0, v1 = this.v1, v2 = this.v2, v3 = this.v3;
			point.set(
				CubicBezier(t, v0.x, v1.x, v2.x, v3.x),
				CubicBezier(t, v0.y, v1.y, v2.y, v3.y),
				CubicBezier(t, v0.z, v1.z, v2.z, v3.z)
			);
		} else {
			if (t === 1) {
				point.copy(this.v3);
			} else {
				point.copy(this.v3).sub(this.v0);
				point.multiplyScalar(t).add(this.v1);
			}
		}

		if (this.rRotationZ !== 0) {

			var cos = Math.cos(this.rRotationZ);
			var sin = Math.sin(this.rRotationZ);

			var tx = point.x;
			var ty = point.y;

			point.setX(tx * cos - ty * sin);
			point.setY(tx * sin + ty * cos);

		}

		return point;
	};


	// ################################
	// ######  WayPoint Curve  ########
	// ################################

	// RouteCurve3

	function RouteCurve3(rRoute, rRoads) {

		THREE.CatmullRomCurve3.call(this);

		this.rRoute = rRoute;
		this.rRoads = rRoads || [];
		this.rAccel = rRoute.accel || 0.0;
		this.routeFragments = [];

		this.initPoint();
	}

	RouteCurve3.prototype = Object.create(THREE.CatmullRomCurve3.prototype);
	RouteCurve3.prototype.constructor = RouteCurve3;

	RouteCurve3.prototype.initPoint = function () {

		this.points = [];
		this.velocities = [];

		var positions2 = [];
		if (this.rRoute.entities) {
			var laneParentMap = new Map();
			for (var road of this.rRoads) {
				if (road !== null) {
					for (var lane of road.lanes) {
						laneParentMap.set(lane.id, road.id);
					}
				}
			}

			var temp01 = this.rRoute.entities.filter(route => route !== null && route.lane !== null && route.type === 'start');
			var temp02 = this.rRoute.entities.filter(route => route !== null && route.lane !== null && route.type === 'mid');
			var temp03 = this.rRoute.entities.filter(route => route !== null && route.lane !== null && route.type === 'goal');
			if (temp01.length === 0 || temp03 === 0) {
				return;
			}

			var currentRoad;
			var laneShift, laneChangeStart, laneChangeEnd;
			// Start
			for (var routeEntity of temp01) {
				lane = routeEntity.lane;
				laneShift = routeEntity.shift || -1;
				laneChangeStart = routeEntity.lanechange_start || -1;
				laneChangeEnd = routeEntity.lanechange_end || -1;
				laneVelocity = routeEntity.velocity || 0.0;
				currentRoad = laneParentMap.get(lane);

				if (laneShift !== -1) {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: laneShift, velocity: laneVelocity });
				} else {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: 0, velocity: laneVelocity });
				}
				if (laneChangeStart !== -1) {
					this.routeFragments.push({ type: "left_change", road: currentRoad, lane: lane, index: laneChangeStart, velocity: laneVelocity });
				}
				if (laneChangeEnd !== -1) {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: laneChangeEnd, velocity: laneVelocity });
				}

				break;
			}

			// Mid
			var routeEntity;
			for (var i = 0; i < temp02.length; i++) {
				routeEntity = temp02[i];
				lane = routeEntity.lane;
				laneChangeStart = routeEntity.lanechange_start || -1;
				laneChangeEnd = routeEntity.lanechange_end || -1;
				laneVelocity = routeEntity.velocity || 0.0;
				currentRoad = laneParentMap.get(lane);

				if (laneChangeStart !== -1) {
					this.routeFragments.push({ type: "left_change", road: currentRoad, lane: lane, index: laneChangeStart, velocity: laneVelocity });
				}
				if (laneChangeEnd !== -1) {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: laneChangeEnd, velocity: laneVelocity });
				}
				if (laneChangeStart === -1 && laneChangeEnd === -1) {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: 0, velocity: laneVelocity });
				}

			}

			// Goal
			for (var routeEntity of temp03) {
				lane = routeEntity.lane;
				laneShift = routeEntity.shift || -1;
				laneChangeStart = routeEntity.lanechange_start || -1;
				laneChangeEnd = routeEntity.lanechange_end || -1;
				laneVelocity = routeEntity.velocity || 0.0;
				currentRoad = laneParentMap.get(lane);

				if (laneChangeStart !== -1) {
					this.routeFragments.push({ type: "left_change", road: currentRoad, lane: lane, index: laneChangeStart, velocity: laneVelocity });
				}
				if (laneChangeEnd !== -1) {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: laneChangeEnd, velocity: laneVelocity });
				}
				if (laneShift !== -1) {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: laneShift, velocity: laneVelocity });
				} else {
					this.routeFragments.push({ type: "along", road: currentRoad, lane: lane, index: Number.MAX_SAFE_INTEGER, velocity: laneVelocity });
				}

				break;
			}

			var f1, f2;
			var i1, i2;
			var l1, l2, c1, c2, a1, a2;
			for (var i = 0; i < this.routeFragments.length - 1; i++) {
				f1 = this.routeFragments[i];
				f2 = this.routeFragments[i + 1];
				l1 = findLaneById(this.rRoads, f1.lane);
				l2 = findLaneById(this.rRoads, f2.lane);
				c1 = l1.curve00.clone();
				c2 = l2.curve00.clone();
				i1 = Math.min(f1.index, c1.points.length - 1);
				i2 = Math.min(f2.index, c2.points.length - 1);
				if (f1.road !== f2.road) {
					if (f1.type === 'along') {
						a1 = c1.points.slice(i1);
						a2 = c2.points.slice(0, i2);
						f1.index = positions2.length;

						for (var a of a1) {
							if (positions2.length > 1) {
								if (!ttttt(positions2[positions2.length - 2], positions2[positions2.length - 1], new THREE.Vector3(a.x, a.y, a.z))) {
									continue;
								}
							}
							positions2.push(new THREE.Vector3(a.x, a.y, a.z));
							this.velocities.push(f1.velocity);
						}
						for (var a of a2) {
							if (positions2.length > 1) {
								if (!ttttt(positions2[positions2.length - 2], positions2[positions2.length - 1], new THREE.Vector3(a.x, a.y, a.z))) {
									continue;
								}
							}
							positions2.push(new THREE.Vector3(a.x, a.y, a.z));
							this.velocities.push(f2.velocity);
						}
					} else { // type === 'change'
						// TODO
					}
				} else {
					if (f1.type === 'along' && f1.lane === f2.lane) {
						a1 = c1.points.slice(i1, i2)
						f1.index = positions2.length;
						for (var a of a1) {
							if (positions2.length > 1) {
								if (!ttttt(positions2[positions2.length - 2], positions2[positions2.length - 1], new THREE.Vector3(a.x, a.y, a.z))) {
									continue;
								}
							}
							positions2.push(new THREE.Vector3(a.x, a.y, a.z));
							this.velocities.push(f1.velocity);
						}
					} else {
						var w1 = l1.widthOffset;
						var w2 = l2.widthOffset;
						var lengthOffset = i2 - i1 + 1;
						var widthOffset = w2 - w1;
						var xv0 = new THREE.Vector3(0, 0, 0);
						var xv3 = new THREE.Vector3(lengthOffset, 0, widthOffset);
						var xv1 = new THREE.Vector3((xv0.x + xv3.x) / 2, xv0.y, xv0.z);
						var xv2 = new THREE.Vector3((xv0.x + xv3.x) / 2, xv0.y, xv3.z);
						var xlaneChangeCurve3 = new THREE.CubicBezierCurve3(xv0, xv1, xv2, xv3);

						if (w1 < 0 && w2 < 0) {
							f1.type = w1 > w2 ? "left_change" : "right_change";
						}
						if (w1 > 0 && w2 > 0) {
							f1.type = w1 > w2 ? "right_change" : "left_change";
						}
						a1 = c1.points.slice(i1, i2 + 1);
						f1.index = positions2.length;

						// ##################################################################
						// var chord = v3.distanceTo(v0);
						// var cont_net = v0.distanceTo(v1) + v2.distanceTo(v1) + v3.distanceTo(v2);
						// var app_arc_length = (cont_net + chord) / 2;
						// var laneOriginCurve3 = new THREE.CatmullRomCurve3(a1);
						// ##################################################################

						var curveA1 = new THREE.CatmullRomCurve3(a1);
						var tempCurve = offsetCurvennn(curveA1, a1.length, xlaneChangeCurve3);

						var tempPosition = new THREE.Vector3();
						for (var kk = 0; kk < a1.length; kk++) {
							tempPosition.copy(tempCurve.getPointAt(kk / a1.length));
							positions2.push(new THREE.Vector3(tempPosition.x, a1[kk].y, tempPosition.z));
							this.velocities.push(f1.velocity);
						}
					}
				}
			}
		} else if (this.rRoute.points) {
			for (var i = 1; i < this.rRoute.points.length - 1; i++) {
				this.routeFragments.push({ type: "way_point", road: "", lane: "", index: 0, velocity: 60 });
				this.routeFragments.push({ type: "way_point", road: "", lane: "", index: Number.MAX_SAFE_INTEGER, velocity: 60 });
				positions2.push(new THREE.Vector3(this.rRoute.points[i].x, this.rRoute.points[i].y, this.rRoute.points[i].z));
				this.velocities.push(0.0);
			}
		} else {
			// TODO
		}

		for (var i = 0; i < positions2.length; i++) {
			this.points.push(new THREE.Vector3(positions2[i].x, positions2[i].y, positions2[i].z));
		}

		if (this.rAccel > 0) {
			var accel = this.rAccel / 1000;
			var new_v = Math.sqrt(2 * accel);
			var v = this.velocities[0] / 3.6;
			new_v = Math.min(new_v, v);
			this.velocities[0] = new_v * 3.6;

			var l;
			for (var i = 1; i < this.velocities.length; i++) {
				l = this.points[i].distanceTo(this.points[i - 1]);
				new_v = Math.sqrt(2 * accel * l + Math.pow(new_v, 2));
				v = this.velocities[i] / 3.6;
				new_v = Math.min(new_v, v);
				this.velocities[i] = new_v * 3.6;
			}
		}

	};

	function offsetCurvennn(curve, steps, offsetCurve) {
		var tempPoints = [];
		var tempPoint = new THREE.Vector3();
		var extrudePts1 = curve.getPoints(steps);
		for (var s = 0; s <= steps; s++) {
			tempPoint.copy(extrudePts1[s]);
			tempPoints.push(new THREE.Vector3(tempPoint.x, 0, tempPoint.z));
		}
		var tempCurve = new THREE.CatmullRomCurve3(tempPoints);

		var points3D = new Array();
		var normal = new THREE.Vector3();
		var position2 = new THREE.Vector3();
		var extrudePts = tempCurve.getPoints(steps);
		var splineTube = tempCurve.computeFrenetFrames(steps);

		for (var s = 0; s <= steps; s++) {
			normal.copy(splineTube.normals[s]).multiplyScalar(offsetCurve.getPointAt(s / steps).z);
			position2.copy(extrudePts[s]).add(normal);
			points3D.push(new THREE.Vector3(position2.x, extrudePts1[s].y, position2.z));
		}

		return new THREE.CatmullRomCurve3(points3D);
	}

	//

	const twoPi = Math.PI * 2;
	const ZAxis = new THREE.Vector3(0, 0, 1);
	const startAngle = -90 * THREE.MathUtils.DEG2RAD;

	function CubicBezierP0(t, p) {

		var k = 1 - t;
		return k * k * k * p;

	};

	function CubicBezierP1(t, p) {

		var k = 1 - t;
		return 3 * k * k * t * p;

	};

	function CubicBezierP2(t, p) {

		return 3 * (1 - t) * t * t * p;

	};

	function CubicBezierP3(t, p) {

		return t * t * t * p;

	};

	function CubicBezier(t, p0, p1, p2, p3) {

		return CubicBezierP0(t, p0) + CubicBezierP1(t, p1) + CubicBezierP2(t, p2) +
			CubicBezierP3(t, p3);

	};

	function findLaneById(roads, landId) {
		if (landId !== undefined && landId !== null && landId !== "") {
			for (var road of roads) {
				if (road === null) continue;
				if (road !== null && road.id === landId) {
					return road;
				}
				for (var lane of road.lanes) {
					if (lane !== null && lane.id === landId) {
						return lane;
					}
				}
			}
		}
	};

	function ttttt(v1, v2, v) {
		var d1 = v1.distanceTo(v);
		var d2 = v2.distanceTo(v);
		var result = d2 > 0.9 && d1 > d2;

		return result;
	}

	return {
		// Road Curve
		CubicRoadCurve3: CubicRoadCurve3,
		CircleRoadCurve3: CircleRoadCurve3,
		StraightRoadCurve3: StraightRoadCurve3,
		ClothoidRoadCurve3: ClothoidRoadCurve3,
		RampRoadCurve3: RampRoadCurve3,

		// WayPoint Curve
		RouteCurve3: RouteCurve3
	};

})();
