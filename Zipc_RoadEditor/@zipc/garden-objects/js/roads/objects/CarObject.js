import * as THREE from 'three';


import { ActorObject } from './ActorObject.js'
import { TrackRouteObject } from './TrackRouteObject.js'

function CarObject(name, color) {

    ActorObject.call(this);

    this.type = 'Car';

    this.name = name || 'Car_NEW';

    this.meshColor = color || Math.random() * 0xffffff;

    this.actorObject = new THREE.Mesh(new THREE.BoxBufferGeometry(5, 2, 2).rotateY(Math.PI / 2)/*.translate(2.5, 1, 0)*/, new THREE.MeshPhongMaterial({ color: this.meshColor, side: THREE.DoubleSide }));

    this.actorObject.castShadow = true;

    this.actorObject.receiveShadow = true;

    this.actorObject.visible = false;

    this.actorObject.name = this.name + '_Mesh01';

    this.add(this.actorObject);

    this.routeWayPoints = new TrackRouteObject(this.name, this.meshColor);

    this.routeWayPoints.translateY(-0.5);

    this.add(this.routeWayPoints);

}

CarObject.prototype = Object.assign(Object.create(ActorObject.prototype), {

    constructor: CarObject,

    computeStructure: function () {

        var routeData = this.metalData.routeData;

        if (routeData !== null) {

            if (routeData.points.length > 1) {

                this.position.copy(routeData.position);

            } else {

                this.position.copy(new THREE.Vector3());

            }

            this.routeWayPoints.setFromMetalData(routeData);
        }


        this.translateY(1);

        this.updateMatrixWorld(true);

    },

    setFromMetalData: function (data, routeData) {

        this.metalData = data;
        data.objectId = this.id;
        this.computeStructure();

        return this;

    },

    copy: function (source) {

        THREE.Object3D.prototype.copy.call(this, source, false);

        this.metalData = JSON.parse(JSON.stringify(source.metalData));
        this.type = source.type;
        this.name = source.name;
        this.meshColor = source.meshColor;

        var i = this.children.length - 1;
        for (; i >= 0; i--) {
            this.remove(this.children[i]);
        }

        this.actorObject = source.actorObject.clone();
        this.routeWayPoints = source.routeWayPoints.clone();

        this.add(this.actorObject);
        this.add(this.routeWayPoints);

        return this;

    }

});

export { CarObject };