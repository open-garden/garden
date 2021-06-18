import * as THREE from 'three';


function ReferenceLineObject(geometry, material, mode) {

    THREE.Line.call(this, geometry, material, mode);

    this.arrow = new THREE.ArrowHelper(new THREE.Vector3(1, 0, 0), new THREE.Vector3(), 0, material.color.getHex(), 0, 0);

    this.add(this.arrow);

    this.type = 'ReferenceLine';

    this.visible = false;

}

ReferenceLineObject.prototype = Object.assign(Object.create(THREE.Line.prototype), {

    constructor: ReferenceLineObject,

    setFromCurve3: function (curve3) {

        let points = curve3.getPoints(curve3.arcLengthDivisions);

        this.geometry.setFromPoints(points);

        this.geometry.attributes.position.needsUpdate = true;

        this.remove(this.arrow);

        this.arrow = new THREE.ArrowHelper(curve3.getTangentAt(1), curve3.getPoint(1), 0, this.material.color.getHex(), 2, 1);

        this.add(this.arrow);

    },

});

export { ReferenceLineObject };