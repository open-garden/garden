import * as THREE from 'three';
import * as mathjs from 'mathjs';

import { RampCurve4 } from './RampCurve4';


export class ClothoidCurve4 extends THREE.Curve<THREE.Vector3> {

    readonly type = 'ClothoidCurve4';

    v0: THREE.Vector3;
    geoType: boolean;
    reverse: boolean;
    mirror: boolean;
    length: number;
    radius: number;
    ramp: RampCurve4;

    constructor(
        v0: THREE.Vector3 = new THREE.Vector3(),
        geoType: boolean = false,
        reverse: boolean = false,
        mirror: boolean = false,
        length: number = 50,
        radius: number = 80,
        ramp: RampCurve4 = new RampCurve4()
    ) {
        super();
        this.v0 = v0;
        this.geoType = geoType;
        this.reverse = reverse;
        this.mirror = mirror;
        this.length = length;
        this.radius = radius;
        this.ramp = ramp;
    }

    negate(): this {
        this.mirror = !this.mirror;

        return this;
    }

    getPoint(t: number, optionalTarget: THREE.Vector3 = new THREE.Vector3()): THREE.Vector3 {
        let point = optionalTarget;
        let v0 = this.v0,
            geoType = this.geoType,
            reverse = this.reverse,
            mirror = this.mirror,
            length = this.length,
            radius = this.radius,
            ramp = this.ramp;

        let curveStart = 0, curveEnd = 0;
        if (geoType) {
            curveStart = 1 / radius;
            curveEnd = 1 / radius / length;
        } else {
            curveStart = 1 / radius / length;
            curveEnd = 1 / radius;
        }

        let dt = mirror !== reverse ? (1 - t) : t;
        let current = new THREE.Vector3();
        current.copy(this.clothoid(curveStart, curveEnd, length, length * dt));
        point.set(
            (reverse ? -current.x : current.x),
            ramp.getPointAt(dt).y,
            current.z
        );

        return point;
    }

    copy(source: ClothoidCurve4, recursive?: boolean): this {
        THREE.Curve.prototype.copy.call(this, source);

        this.v0.copy(source.v0);
        this.geoType = source.geoType;
        this.reverse = source.reverse;
        this.mirror = source.mirror;
        this.length = source.length;
        this.radius = source.radius;
        this.ramp.copy(source.ramp);

        return this;
    }

    toJSON(): object {
        let data = THREE.Curve.prototype.toJSON.call(this) as any;

        data.v0 = this.v0.toArray();
        data.geoType = this.geoType;
        data.reverse = this.reverse;
        data.mirror = this.mirror;
        data.length = this.length;
        data.radius = this.radius;
        data.ramp = this.ramp.toJSON();

        return data;
    }

    fromJSON(json: any): this {
        THREE.Curve.prototype.fromJSON.call(this, json);

        this.v0.fromArray(json.v0);
        this.geoType = json.geoType;
        this.reverse = json.reverse;
        this.mirror = json.mirror;
        this.length = json.length;
        this.radius = json.radius;
        this.ramp.fromJSON(json.ramp);

        return this;
    }

    clone(recursive?: boolean): ClothoidCurve4 {
        return new ClothoidCurve4().copy(this, recursive);
    }

    private clothoid(
        curveStart: number,
        curveEnd: number,
        curveLength: number,
        distance: number
    ): THREE.Vector3 {
        const A = (curveEnd - curveStart) / curveLength;
        const s0 = curveStart / A;

        let v0 = this.clothoidcooridnate(A, s0);
        const X0 = v0.x;
        const Y0 = v0.y;
        const heading0 = A / 2 * Math.pow(s0, 2);

        const transferMatrix = mathjs.inv(this.transfercoordinate(heading0));
        let v = this.clothoidcooridnate(A, distance + s0);
        const X = v.x;
        const Y = v.y;


        let local = mathjs.multiply(transferMatrix, mathjs.subtract([X, Y], [X0, Y0])) as mathjs.Matrix;

        // var heading = A / 2 * distance ** 2 + curveStart * distance;

        return new THREE.Vector3(local.get([0]), 0, local.get([1]));
    }

    private clothoidcooridnate(A: number, distance: number): THREE.Vector2 {
        let x = 0.0;
        let y = 0.0;

        for (let n = 0; n < 30; n++) {
            x += Math.pow(-1, n) * Math.pow(A, 2 * n) * Math.pow(distance, 4 * n + 1) / (mathjs.factorial(2 * n) * (4 * n + 1) * Math.pow(2, 2 * n));
            y += Math.pow(-1, n) * Math.pow(A, 2 * n + 1) * Math.pow(distance, 4 * n + 3) / (mathjs.factorial(2 * n + 1) * (4 * n + 3) * Math.pow(2, 2 * n + 1))
        }

        return new THREE.Vector2(x, y);
    }

    private transfercoordinate(heading: number): mathjs.Matrix {
        return mathjs.matrix([[mathjs.cos(heading), -mathjs.sin(heading)], [mathjs.sin(heading), mathjs.cos(heading)]]);
    }

}