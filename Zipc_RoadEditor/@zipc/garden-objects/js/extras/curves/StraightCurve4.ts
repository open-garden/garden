import * as THREE from 'three';
import { RampCurve4 } from './RampCurve4';


export class StraightCurve4 extends THREE.Curve<THREE.Vector3> {

    readonly type = 'StraightCurve4';

    v1: THREE.Vector3;
    v2: THREE.Vector3;
    ramp: RampCurve4;

    constructor(
        v1: THREE.Vector3 = new THREE.Vector3(),
        v2: THREE.Vector3 = new THREE.Vector3(),
        ramp: RampCurve4 = new RampCurve4()
    ) {
        super();
        this.v1 = v1;
        this.v2 = v2;
        this.ramp = ramp;
    }

    negate(): this {
        let v1 = new THREE.Vector3().copy(this.v2);
        let v2 = new THREE.Vector3().copy(this.v1);

        this.v1.copy(v1);
        this.v2.copy(v2);
        this.ramp.negate();

        return this;
    }

    getPoint(t: number, optionalTarget: THREE.Vector3 = new THREE.Vector3()): THREE.Vector3 {
        let point = optionalTarget;
        let v1 = this.v1, v2 = this.v2, ramp = this.ramp;

        point.subVectors(v2, v1).multiplyScalar(t).add(v1);
        point.setY(ramp.getPointAt(t).y);

        return point;
    }

    copy(source: StraightCurve4, recursive?: boolean): this {
        THREE.Curve.prototype.copy.call(this, source);

        this.v1.copy(source.v1);
        this.v2.copy(source.v2);
        this.ramp.copy(source.ramp);

        return this;
    }

    toJSON(): object {
        let data = THREE.Curve.prototype.toJSON.call(this) as any;

        data.v1 = this.v1.toArray();
        data.v2 = this.v2.toArray();
        data.ramp = this.ramp.toJSON();

        return data;
    }

    fromJSON(json: any): this {
        THREE.Curve.prototype.fromJSON.call(this, json);

        this.v1.fromArray(json.v1);
        this.v2.fromArray(json.v2);
        this.ramp.fromJSON(json.ramp);

        return this;
    }

    clone(recursive?: boolean): StraightCurve4 {
        return new StraightCurve4().copy(this, recursive);
    }

}