import * as THREE from 'three';


import { AbstractWay } from './AbstractWay.js';
import { ReferenceLineObject } from './ReferenceLineObject.js'

function JunctionLaneObject(parentRoad, name) {

    AbstractWay.call(this);

    this.type = 'JunctionLane';

    this.parentRoad = parentRoad;

    this.name = name;

    this.curve3 = new THREE.CatmullRomCurve3();

    this.refLine = new ReferenceLineObject(new THREE.BufferGeometry(), new THREE.LineBasicMaterial({ color: 0x303030, opacity: 0.35 }));

    this.refLine.translateY(0.5);

    this.add(this.refLine);

}

JunctionLaneObject.prototype = Object.assign(Object.create(AbstractWay.prototype), {

    constructor: JunctionLaneObject,

    setFromMetalData: function (data) {

        this.metalData = data;
        this.name = data.id;
        this.update();

        return this;

    },

    update: function () {

        var data = this.metalData;

        if (data.curve !== undefined) {

            this.curve3 = data.curve.clone();

            this.refLine.setFromCurve3(this.curve3);

        }

        this.refLine.visible = this.isRefLineVisible();

        this.updateMatrixWorld(true);

    }

});

export { JunctionLaneObject };