import * as THREE from 'three';
import { Interpolations } from '../../../../../vendors/examples/jsm/extras/core/Interpolations';
import { RampCurve4 } from './RampCurve4';


export class CubicBezierCurve4 extends THREE.Curve<THREE.Vector3> {

    readonly type = 'CubicBezierCurve4';

    v0: THREE.Vector3;
    v1: THREE.Vector3;
    v2: THREE.Vector3;
    v3: THREE.Vector3;
    ramp: RampCurve4;

    constructor(
        v0: THREE.Vector3 = new THREE.Vector3(),
        v1: THREE.Vector3 = new THREE.Vector3(),
        v2: THREE.Vector3 = new THREE.Vector3(),
        v3: THREE.Vector3 = new THREE.Vector3(),
        ramp: RampCurve4 = new RampCurve4()
    ) {
        super();
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.ramp = ramp;
    }

    negate(): this {
        let v0 = new THREE.Vector3().copy(this.v3);
        let v1 = new THREE.Vector3().copy(this.v2);
        let v2 = new THREE.Vector3().copy(this.v1);
        let v3 = new THREE.Vector3().copy(this.v0);

        this.v0.copy(v0);
        this.v1.copy(v1);
        this.v2.copy(v2);
        this.v3.copy(v3);
        this.ramp.negate();

        return this;
    }

    getPoint(t: number, optionalTarget: THREE.Vector3 = new THREE.Vector3()): THREE.Vector3 {
        let point = optionalTarget;
        let v0 = this.v0, v1 = this.v1, v2 = this.v2, v3 = this.v3, ramp = this.ramp;

        point.set(
            Interpolations.CubicBezier(t, v0.x, v1.x, v2.x, v3.x),
            ramp.getPointAt(t).y,
            Interpolations.CubicBezier(t, v0.z, v1.z, v2.z, v3.z)
        );

        return point;
    }

    copy(source: CubicBezierCurve4, recursive?: boolean): this {
        THREE.Curve.prototype.copy.call(this, source);

        this.v0.copy(source.v0);
        this.v1.copy(source.v1);
        this.v2.copy(source.v2);
        this.v3.copy(source.v3);
        this.ramp.copy(source.ramp);

        return this;
    }

    toJSON(): object {
        let data = THREE.Curve.prototype.toJSON.call(this) as any;

        data.v0 = this.v0.toArray();
        data.v1 = this.v1.toArray();
        data.v2 = this.v2.toArray();
        data.v3 = this.v3.toArray();
        data.ramp = this.ramp.toJSON();

        return data;
    }

    fromJSON(json: any): this {
        THREE.Curve.prototype.fromJSON.call(this, json);

        this.v0.fromArray(json.v0);
        this.v1.fromArray(json.v1);
        this.v2.fromArray(json.v2);
        this.v3.fromArray(json.v3);
        this.ramp.fromJSON(json.ramp);

        return this;
    }

    clone(recursive?: boolean): CubicBezierCurve4 {
        return new CubicBezierCurve4().copy(this, recursive);
    }

}