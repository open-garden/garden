import * as THREE from 'three';


function ActorObject() {

    THREE.Object3D.call(this);

    this.type = 'Actor';

    this.metalData = {};

    this.name = '';

}

ActorObject.prototype = Object.assign(Object.create(THREE.Object3D.prototype), {

    constructor: ActorObject,

    isGroup: true

});

export { ActorObject };