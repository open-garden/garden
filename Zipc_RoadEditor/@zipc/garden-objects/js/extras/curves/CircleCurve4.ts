import * as THREE from 'three';
import { RampCurve4 } from './RampCurve4';


export class CircleCurve4 extends THREE.Curve<THREE.Vector3> {

    readonly type = 'CircleCurve4';

    v0: THREE.Vector3;
    radius: number;
    startAngle: number;
    endAngle: number;
    clockwise: boolean;
    ramp: RampCurve4;

    constructor(
        v0: THREE.Vector3 = new THREE.Vector3(),
        radius: number = 80,
        startAngle: number = 0,
        endAngle: number = Math.PI / 2,
        clockwise: boolean = false,
        ramp: RampCurve4 = new RampCurve4()
    ) {
        super();
        this.v0 = v0;
        this.radius = radius;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.clockwise = clockwise;
        this.ramp = ramp;
    }

    negate(): this {
        var startAngle = this.endAngle;
        var endAngle = this.startAngle;
        var clockwise = !this.clockwise;

        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.clockwise = clockwise;
        this.ramp.negate();

        return this;
    }

    getPoint(t: number, optionalTarget: THREE.Vector3 = new THREE.Vector3()): THREE.Vector3 {
        let point = optionalTarget;
        let v0 = this.v0,
            ramp = this.ramp,
            radius = Math.abs(this.radius),
            startAngle = this.startAngle,
            endAngle = this.endAngle;
        let twoPi = 2 * Math.PI;
        let deltaAngle = endAngle - startAngle;
        let samePoints = Math.abs(deltaAngle) < Number.EPSILON;

        while (deltaAngle < 0) deltaAngle += twoPi;
        while (deltaAngle > twoPi) deltaAngle -= twoPi;

        if (deltaAngle < Number.EPSILON) {
            if (samePoints) {
                deltaAngle = 0;
            } else {
                deltaAngle = twoPi;
            }
        }
        if (this.clockwise === true && !samePoints) {
            if (deltaAngle === twoPi) {
                deltaAngle = -twoPi;
            } else {
                deltaAngle = deltaAngle - twoPi;
            }
        }

        let t2 = startAngle + t * deltaAngle;
        point.set(
            v0.x + Math.cos(t2) * radius,
            ramp.getPointAt(t).y,
            v0.z + Math.sin(t2) * radius
        );

        return point;
    }

    copy(source: CircleCurve4, recursive?: boolean): this {
        THREE.Curve.prototype.copy.call(this, source);

        this.v0.copy(source.v0);
        this.radius = source.radius;
        this.startAngle = source.startAngle;
        this.endAngle = source.endAngle;
        this.clockwise = source.clockwise;
        this.ramp.copy(source.ramp);

        return this;
    }

    toJSON(): object {
        let data = THREE.Curve.prototype.toJSON.call(this) as any;

        data.v0 = this.v0.toArray();
        data.radius = this.radius;
        data.startAngle = this.startAngle;
        data.endAngle = this.endAngle;
        data.clockwise = this.clockwise;
        data.ramp = this.ramp.toJSON();

        return data;
    }

    fromJSON(json: any): this {
        THREE.Curve.prototype.fromJSON.call(this, json);

        this.v0.fromArray(json.v0);
        this.radius = json.radius;
        this.startAngle = json.startAngle;
        this.endAngle = json.endAngle;
        this.clockwise = json.clockwise;
        this.ramp.fromJSON(json.ramp);

        return this;
    }

    clone(recursive?: boolean): CircleCurve4 {
        return new CircleCurve4().copy(this, recursive);
    }

}