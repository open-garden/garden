import * as THREE from 'three';
import { SpriteHelper } from '../../../../garden-objects/js/helpers/SpriteHelper.js';

import { AbstractWay } from './AbstractWay.js';

function TrackRouteObject(name, color) {

    AbstractWay.call(this);

    this.type = 'TrackRoute';

    this.name = name + '_TrackRoute';

    this.color = color;

    this.routeWayPoints = new SpriteHelper(null, this.color);

    /*this.routeWayPoints = new THREE.Points(
        new THREE.BufferGeometry(),
        new THREE.ShaderMaterial({
            uniforms: {
                color: {
                    value: new THREE.Color(this.meshColor)
                },
            },
            vertexShader: wayPointVertexshaderSource,
            fragmentShader: wayPointFragmentshaderSource
        }));

    this.routeWayPoints.geometry.setAttribute('scale', new THREE.BufferAttribute(new Float32Array(0), 1));
    this.routeWayPoints.geometry.setAttribute('invert', new THREE.BufferAttribute(new Float32Array(0), 1));*/

    this.add(this.routeWayPoints);

}

TrackRouteObject.prototype = Object.assign(Object.create(AbstractWay.prototype), {

    constructor: TrackRouteObject,

    update: function () {

        var data = this.metalData;

        this.routeWayPoints.setFromObject(data.points);

        /*data.points.forEach((p) => {
            var sprite = new THREE.Sprite(new THREE.SpriteMaterial({ color: this.meshColor }));
            sprite.position.copy(p);
            sprite.scale.set(0.5, 0.5, 0.5);
            this.add(sprite);
        });*/

        /*this.routeWayPoints.geometry.setFromPoints(data.points);

        this.routeWayPoints.geometry.setAttribute('scale', new THREE.BufferAttribute(new Float32Array(data.points.length).fill(2), 1));

        this.routeWayPoints.geometry.setAttribute('invert', new THREE.BufferAttribute(new Float32Array(data.points.length).fill(0), 1));

        this.routeWayPoints.geometry.attributes.position.needsUpdate = true;

        this.routeWayPoints.geometry.attributes.scale.needsUpdate = true;*/

        if (this.name === 'EgoCar_TrackRoute') {

            this.renderOrder = Number.MAX_SAFE_INTEGER - 1;

        }

        this.updateMatrixWorld(true);

    },

    setFromMetalData: function (data) {

        this.metalData = data;
        data.objectId = this.id;
        this.update();

        return this;

    },

    copy: function (source) {

        THREE.Object3D.prototype.copy.call(this, source, false);

        this.metalData = JSON.parse(JSON.stringify(source.metalData));
        this.type = source.type;
        this.name = source.name;
        this.color = source.color;

        for (var child of this.children) {
            child.remove();
        }

        this.routeWayPoints = source.routeWayPoints.clone();

        this.add(this.routeWayPoints);

        return this;

    }

});

export { TrackRouteObject };