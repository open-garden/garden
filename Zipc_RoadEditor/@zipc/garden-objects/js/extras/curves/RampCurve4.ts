import * as THREE from 'three';


export class RampCurve4 extends THREE.QuadraticBezierCurve3 {

    readonly type = 'RampCurve4';

    constructor(v0?: THREE.Vector3, v1?: THREE.Vector3, v2?: THREE.Vector3) {
        super(
            v0 || new THREE.Vector3(),
            v1 || new THREE.Vector3(),
            v2 || new THREE.Vector3()
        );
    }

    negate(): RampCurve4 {
        let v0 = new THREE.Vector3().copy(this.v2);
        let v2 = new THREE.Vector3().copy(this.v0);

        this.v0.copy(v0);
        this.v2.copy(v2);

        return this;
    }

}