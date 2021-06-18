import * as THREE from 'three';

import { RampCurve4 } from './curves/RampCurve4';
import { CircleCurve4 } from './curves/CircleCurve4';
import { StraightCurve4 } from './curves/StraightCurve4';
import { ClothoidCurve4 } from './curves/ClothoidCurve4';
import { CubicBezierCurve4 } from './curves/CubicBezierCurve4';


/*
 * Curves
 */
export * from './curves/RampCurve4';
export * from './curves/CircleCurve4';
export * from './curves/StraightCurve4';
export * from './curves/ClothoidCurve4';
export * from './curves/CubicBezierCurve4';

/*
 * Common Functions
 */
export function createCurve3(data: any): any {
    let width = 4;
    return createPathCurve3(data.type, data.length, width, data.height, data.ramp_angle, data.radius, data.reverse, data.rotationZ);
}

export function createPathCurve3(
    type: string,
    length: number,
    width: number,
    height: number,
    ramp_angle: number,
    radius: number,
    reverse: boolean,
    rotationZ: number
) {
    let offsetX = length - Math.abs(height) / Math.atan(ramp_angle * THREE.MathUtils.DEG2RAD);
    offsetX = isNaN(offsetX) ? 0 : offsetX;

    if (type === "circular") {
        let v0, angle, startAngle, endAngle, clockwise = true;
        let rampCurve3 = createRampCurve3(length, height, ramp_angle, rotationZ, reverse);

        if (radius === undefined || radius === 0) {
            radius = 80;
        }

        if (reverse) {
            v0 = new THREE.Vector3(0, 0, -radius);
            angle = length / Math.abs(radius) * THREE.MathUtils.RAD2DEG;
            endAngle = radius < 0 ? -90 : 90;
            startAngle = endAngle + (radius < 0 ? -angle : angle);
            clockwise = radius < 0 ? false : true;
        } else {
            v0 = new THREE.Vector3(0, 0, -radius);
            angle = length / Math.abs(radius) * THREE.MathUtils.RAD2DEG;
            startAngle = radius < 0 ? -90 : 90;
            endAngle = startAngle + (radius < 0 ? angle : -angle);
            clockwise = radius < 0 ? false : true;
        }
        let curve3 = new CircleCurve4(v0, radius, startAngle * THREE.MathUtils.DEG2RAD, endAngle * THREE.MathUtils.DEG2RAD, clockwise, rampCurve3);
        curve3.arcLengthDivisions = length;
        return curve3;
    } else if (type === "cubic_left" || type === "cubic_right") {
        let p1, p2, c1, c2;
        let rampCurve3 = createRampCurve3(length, height, ramp_angle, rotationZ, reverse);

        if (reverse) {
            p1 = new THREE.Vector3(-length, 0, type === "cubic_right" ? -width : width);
            p2 = new THREE.Vector3(0, 0, 0);
            c1 = new THREE.Vector3((p1.x + p2.x) / 2, p1.y, p1.z);
            c2 = new THREE.Vector3((p1.x + p2.x) / 2, p1.y, p2.z);
        } else {
            p1 = new THREE.Vector3(0, 0, 0);
            p2 = new THREE.Vector3(length, 0, type === "cubic_left" ? -width : width);
            c1 = new THREE.Vector3((p1.x + p2.x) / 2, p1.y, p1.z);
            c2 = new THREE.Vector3((p1.x + p2.x) / 2, p1.y, p2.z);
        }
        let curve3 = new CubicBezierCurve4(p1, c1, c2, p2, rampCurve3);
        curve3.arcLengthDivisions = length;
        return curve3;
    } else if (type === "clothoid_in" || type === "clothoid_out") {
        let v0, geoType = false;
        let rampCurve3 = createRampCurve3(length, height, ramp_angle, reverse ? -rotationZ : rotationZ, false);

        if (reverse) {
            v0 = new THREE.Vector3(0, 0, -radius);
            geoType = (type === "clothoid_out") !== reverse;
        } else {
            v0 = new THREE.Vector3(0, 0, -radius);
            geoType = (type === "clothoid_out") !== reverse;
        }

        let curve3 = new ClothoidCurve4(v0, geoType, reverse, false, length, -radius, rampCurve3);
        curve3.arcLengthDivisions = length;
        return curve3;
    } else { // 直線道路"straight" と その他
        let p1, p2;
        let rampCurve3 = createRampCurve3(length, height, ramp_angle, rotationZ, reverse);

        if (reverse) {
            p1 = new THREE.Vector3(-length, 0, 0);
            p2 = new THREE.Vector3(0, 0, 0);
        } else {
            p1 = new THREE.Vector3(0, 0, 0);
            p2 = new THREE.Vector3(length, 0, 0);
        }
        let curve3 = new StraightCurve4(p1, p2, rampCurve3);
        curve3.arcLengthDivisions = length;
        return curve3;
    }
}

export function createRampCurve3(
    length: number,
    height: number,
    angle: number,
    rz: number,
    reverse: boolean
) {
    let p1, p2, c1, rotationHeight = 0;

    let rotationZ = rz || 0;
    let offsetX = length - Math.abs(height) / Math.tan(angle * THREE.MathUtils.DEG2RAD);
    offsetX = isNaN(offsetX) ? 0 : offsetX;

    if (reverse) {
        p1 = new THREE.Vector3(-length, height, 0);
        p2 = new THREE.Vector3(0, 0, 0);
        c1 = new THREE.Vector3(p2.x - offsetX, p2.y, 0);
        if (rotationZ !== 0) {
            rotationHeight = (p1.x - p2.x) * Math.sin(rotationZ);

            let euler = new THREE.Euler(0, 0, rotationZ, 'XYZ');
            p1.applyEuler(euler);
            p2.applyEuler(euler);
            c1.applyEuler(euler);
        }
    } else {
        p1 = new THREE.Vector3(0, 0, 0);
        p2 = new THREE.Vector3(length, height, 0);
        c1 = new THREE.Vector3(p1.x + offsetX, p1.y, 0);
        if (rotationZ !== 0) {
            rotationHeight = (p2.x - p1.x) * Math.sin(rotationZ);

            let euler = new THREE.Euler(0, 0, rotationZ, 'XYZ');
            p1.applyEuler(euler);
            p2.applyEuler(euler);
            c1.applyEuler(euler);
        }
    }

    let curve3 = new RampCurve4(p1, c1, p2);
    curve3.arcLengthDivisions = length;
    return curve3;
}

export function createEulerFromQuaternion(q1: THREE.Quaternion): THREE.Euler {
    let e = new THREE.Euler(0, 0, 0, 'XYZ');

    let sqw = q1.w * q1.w;
    let sqx = q1.x * q1.x;
    let sqy = q1.y * q1.y;
    let sqz = q1.z * q1.z;
    let unit = sqx + sqy + sqz + sqw; // if normalised is one, otherwise is correction factor
    let test = q1.x * q1.y + q1.z * q1.w;
    if (test > 0.499 * unit) { // singularity at north pole
        return e;
    }
    if (test < -0.499 * unit) { // singularity at south pole
        return e;
    }

    e.y = Math.atan2(2 * q1.y * q1.w - 2 * q1.x * q1.z, sqx - sqy - sqz + sqw);
    e.x = Math.asin(2 * test / unit);
    e.z = Math.atan2(2 * q1.x * q1.w - 2 * q1.y * q1.z, -sqx + sqy - sqz + sqw)

    return e;
}

export function positionAtOffset(
    curve: any,
    length: number,
    offset: number,
    reverse: boolean
): THREE.CatmullRomCurve3 {
    let extrudePts = curve.points !== undefined ? curve.points : curve.getSpacedPoints(length);
    let splineTube = curve.computeFrenetFrames(length, false);

    let newPoints = [];
    let position2 = new THREE.Vector3();
    let normal = new THREE.Vector3();
    let binormal = new THREE.Vector3();
    for (let ii = 0; ii < extrudePts.length; ii++) {
        normal.copy(splineTube.normals[ii]).multiplyScalar(offset);
        binormal.copy(splineTube.binormals[ii]).multiplyScalar(0);
        position2.copy(extrudePts[ii]).add(normal).add(binormal);
        newPoints.push(position2.clone());
    }

    let curve3 = new THREE.CatmullRomCurve3(reverse ? newPoints.reverse() : newPoints);
    curve3.arcLengthDivisions = newPoints.length - 1;
    return curve3;
}

export function positionAtOffset111test(
    dir: boolean,
    isreverse: boolean,
    curve: any,
    length: number,
    width: number,
    offsetType: string,
    s0: number,
    t0: number,
    s1: number,
    t1: number
): THREE.CatmullRomCurve3 {
    const lllPoints = new Array();

    let extrudePts = curve.points !== undefined ? curve.points : curve.getSpacedPoints(length);
    let splineTube = curve.computeFrenetFrames(length, false);
    let newPoints = [];
    let position2 = new THREE.Vector3();
    let normal = new THREE.Vector3();
    let binormal = new THREE.Vector3();

    if (offsetType === 'merge') {
        let x1 = Math.floor(length * s0) + 1;
        let x2 = Math.floor(length * s1) - 1;
        let y1 = width * t0;
        let y2 = width * t1;
        let v0 = new THREE.Vector2(0, y1);
        let v1 = new THREE.Vector2(x1, y1);
        let v2 = new THREE.Vector2((x1 + x2) / 2, y1);
        let v3 = new THREE.Vector2((x1 + x2) / 2, y2);
        let v4 = new THREE.Vector2(x2, y2);
        let v5 = new THREE.Vector2(length, y2);
        let offsetCurve01 = new THREE.LineCurve(v0, v1);
        if (v1.x - v0.x > 0) {
            Array.prototype.push.apply(lllPoints, offsetCurve01.getPoints(v1.x - v0.x));
        }
        let offsetCurve02 = new THREE.CubicBezierCurve(v1, v2, v3, v4);
        if (v4.x - v1.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve02.getPoints(v4.x - v1.x));
        }
        let offsetCurve03 = new THREE.LineCurve(v4, v5);
        if (v5.x - v4.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve03.getPoints(v5.x - v4.x));
        }
        for (let ii = 0; ii < lllPoints.length; ii++) {
            normal.copy(splineTube.normals[ii]).multiplyScalar(lllPoints[ii].y);
            position2.copy(extrudePts[ii]).add(normal);
            newPoints.push(position2.clone());
        }
    } else if (offsetType === 'branch') {
        let x1 = Math.floor(length * s0) + 1;
        let x2 = Math.floor(length * s1) - 1;
        let y1 = width * t0;
        let y2 = width * t1;
        let v0 = new THREE.Vector2(0, y1);
        let v1 = new THREE.Vector2(x1, y1);
        let v2 = new THREE.Vector2((x1 + x2) / 2, y1);
        let v3 = new THREE.Vector2((x1 + x2) / 2, y2);
        let v4 = new THREE.Vector2(x2, y2);
        let v5 = new THREE.Vector2(length, y2);
        let offsetCurve01 = new THREE.LineCurve(v0, v1);
        if (v1.x - v0.x > 0) {
            Array.prototype.push.apply(lllPoints, offsetCurve01.getPoints(v1.x - v0.x));
        }
        let offsetCurve02 = new THREE.CubicBezierCurve(v1, v2, v3, v4);
        if (v4.x - v1.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve02.getPoints(v4.x - v1.x));
        }
        let offsetCurve03 = new THREE.LineCurve(v4, v5);
        if (v5.x - v4.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve03.getPoints(v5.x - v4.x));
        }
        for (let ii = 0; ii < extrudePts.length; ii++) {
            normal.copy(splineTube.normals[ii]).multiplyScalar(lllPoints[ii].y);
            position2.copy(extrudePts[ii]).add(normal);
            newPoints.push(position2.clone());
        }
    } else {
        for (let ii = 0, ll = extrudePts.length; ii < ll; ii++) {
            if (ii === 0) {
                normal.copy(dir !== isreverse ?
                    new THREE.Vector3(+splineTube.normals[ii].x.toFixed(), +splineTube.normals[ii].y.toFixed(), +splineTube.normals[ii].z.toFixed())
                    : splineTube.normals[ii]).multiplyScalar(width);
            } else if (ii === ll - 1) {
                normal.copy(dir !== isreverse ?
                    splineTube.normals[ii]
                    : new THREE.Vector3(+splineTube.normals[ii].x.toFixed(), +splineTube.normals[ii].y.toFixed(), +splineTube.normals[ii].z.toFixed())).multiplyScalar(width);
            } else {
                normal.copy(splineTube.normals[ii]).multiplyScalar(width);
            }
            position2.copy(extrudePts[ii]).add(normal)/*.add(binormal)*/;
            newPoints.push(position2.clone());
        }
    }

    let curve3 = new THREE.CatmullRomCurve3(newPoints);
    curve3.arcLengthDivisions = newPoints.length - 1;
    return curve3;
}

export function positionAtOffset222test(
    curve: any,
    length: number,
    width: number,
    offsetType: string,
    s0: number,
    t0: number,
    s1: number,
    t1: number,
    start: number,
    end: number
): THREE.CatmullRomCurve3 {
    const lllPoints = new Array();

    let extrudePts = curve.points !== undefined ? curve.points : curve.getSpacedPoints(length);
    let splineTube = curve.computeFrenetFrames(length, false);
    let newPoints = [];
    let position2 = new THREE.Vector3();
    let normal = new THREE.Vector3();
    let binormal = new THREE.Vector3();

    if (offsetType === 'merge') {
        let x1 = Math.floor(length * s0);
        let x2 = Math.floor(length * s1);
        let v0 = new THREE.Vector2(0, 0);
        let v1 = new THREE.Vector2(x1, 0);
        let v2 = new THREE.Vector2((x1 + x2) / 2, 0);
        let v3 = new THREE.Vector2((x1 + x2) / 2, width);
        let v4 = new THREE.Vector2(x2, width);
        let v5 = new THREE.Vector2(length, width);
        let offsetCurve01 = new THREE.LineCurve(v0, v1);
        if (v1.x - v0.x > 0) {
            Array.prototype.push.apply(lllPoints, offsetCurve01.getPoints(v1.x - v0.x));
        }
        let offsetCurve02 = new THREE.CubicBezierCurve(v1, v2, v3, v4);
        if (v4.x - v1.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve02.getPoints(v4.x - v1.x));
        }
        let offsetCurve03 = new THREE.LineCurve(v4, v5);
        if (v5.x - v4.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve03.getPoints(v5.x - v4.x));
        }
        for (let ii = 0; ii < lllPoints.length; ii++) {
            normal.copy(splineTube.normals[ii]).multiplyScalar(lllPoints[ii].y);
            binormal.copy(splineTube.binormals[ii]).multiplyScalar(0);
            position2.copy(extrudePts[ii]).add(normal).add(binormal);
            newPoints.push(position2.clone());
        }
    } else if (offsetType === 'branch') {
        let x1 = Math.floor(length * s0);
        let x2 = Math.floor(length * s1);
        let v0 = new THREE.Vector2(0, 0);
        let v1 = new THREE.Vector2(x1, 0);
        let v2 = new THREE.Vector2((x1 + x2) / 2, 0);
        let v3 = new THREE.Vector2((x1 + x2) / 2, width);
        let v4 = new THREE.Vector2(x2, width);
        let v5 = new THREE.Vector2(length, width);
        let offsetCurve01 = new THREE.LineCurve(v0, v1);
        if (v1.x - v0.x > 0) {
            Array.prototype.push.apply(lllPoints, offsetCurve01.getPoints(v1.x - v0.x));
        }
        let offsetCurve02 = new THREE.CubicBezierCurve(v1, v2, v3, v4);
        if (v4.x - v1.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve02.getPoints(v4.x - v1.x));
        }
        let offsetCurve03 = new THREE.LineCurve(v4, v5);
        if (v5.x - v4.x > 0) {
            if (lllPoints.length > 0) lllPoints.splice(lllPoints.length - 1, 1);
            Array.prototype.push.apply(lllPoints, offsetCurve03.getPoints(v5.x - v4.x));
        }
        for (let ii = 0; ii < extrudePts.length; ii++) {
            normal.copy(splineTube.normals[ii]).multiplyScalar(lllPoints[ii].y);
            binormal.copy(splineTube.binormals[ii]).multiplyScalar(0);
            position2.copy(extrudePts[ii]).add(normal).add(binormal);
            newPoints.push(position2.clone());
        }
    } else {
        for (let ii = 0; ii < extrudePts.length; ii++) {
            normal.copy(splineTube.normals[ii]).multiplyScalar(width);
            binormal.copy(splineTube.binormals[ii]).multiplyScalar(0);
            position2.copy(extrudePts[ii]).add(normal).add(binormal);
            newPoints.push(position2.clone());
        }
    }

    newPoints = newPoints.slice(Math.max(0, Math.floor(newPoints.length * (start || 0) - 1)), Math.min(newPoints.length, Math.floor(newPoints.length * (end || 1) + 1)));
    let curve3 = new THREE.CatmullRomCurve3(newPoints);
    curve3.arcLengthDivisions = newPoints.length - 1;
    return curve3;
}

export function curveOffset(
    curve: any,
    offset: number,
    hand: string
): THREE.CatmullRomCurve3 {
    offset = isNaN(offset) ? 0 : offset;
    if (hand !== undefined) {
        offset = hand !== 'left' ? Math.abs(offset) : -Math.abs(offset);
    } else {
        offset *= -1;
    }

    let p1 = curve.getPointAt(0);
    let p2 = curve.getPointAt(1);
    let d1 = p1.distanceTo(new THREE.Vector3());
    let d2 = p2.distanceTo(new THREE.Vector3());


    let extrudePts = curve.getSpacedPoints(curve.arcLengthDivisions);
    let splineTube = curve.computeFrenetFrames(curve.arcLengthDivisions, false);

    let points = [];

    let position2 = new THREE.Vector3();
    let normal = new THREE.Vector3();
    let binormal = new THREE.Vector3();
    for (let ii = 0; ii < extrudePts.length; ii++) {
        //normal.copy(splineTube.normals[ii]).multiplyScalar(offset);
        //binormal.copy(splineTube.binormals[ii]).multiplyScalar(0);
        normal.copy(splineTube.normals[ii]).multiplyScalar(offset);
        binormal.copy(splineTube.binormals[ii]).multiplyScalar(0);
        position2.copy(extrudePts[ii]).add(normal).add(binormal);
        points.push(position2.clone());
    }

    let vec = new THREE.Vector3();
    if (d1 <= d2) {
        vec.copy(points[0]);
    } else {
        vec.copy(points[points.length - 1]);
    }

    for (let ppp of points) {
        ppp.sub(vec);
    }

    let curve3 = new THREE.CatmullRomCurve3(points);
    curve3.arcLengthDivisions = curve.arcLengthDivisions;

    return curve3;
}

export function createExtrudeShape(
    left: number,
    right: number,
    deep: number
): THREE.Shape {

    var a = left || 0;
    var b = right || 0;
    var c = deep || 0;

    return new THREE.Shape()
        .moveTo(a, -c)
        .lineTo(a, 0)
        .lineTo(b, 0)
        .lineTo(b, -c)
        .lineTo(a, -c);
}