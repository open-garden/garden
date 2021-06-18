import * as THREE from 'three';
import { SpriteHelper } from '../../../../garden-objects/js/helpers/SpriteHelper.js';


function RouteObject(gid, color) {

    THREE.Object3D.call(this);

    this.type = 'Route';

    this.gid = gid || 'Route.NEW';

    this.description = '';

    this.name = this.gid;

    this.color = color || Math.random() * 0xffffff;

    this.routeWayPoints = new SpriteHelper(null, this.color);

    // this.routeWayPoints.translateY(-0.5);

    this.add(this.routeWayPoints);

}

RouteObject.prototype = Object.assign(Object.create(THREE.Object3D.prototype), {

    constructor: RouteObject,

    computeStructure: function () {

        var routeData = this.metalData.routeData;

        if (routeData !== null) {

            if (routeData.points.length > 1) {

                this.position.copy(routeData.position);

            } else {

                this.position.copy(new THREE.Vector3());

            }

            this.routeWayPoints.setFromObject(this);
        }


        this.translateY(1);

        this.updateMatrixWorld(true);

    },

    setFromMetalData: function (data) {

        this.metalData = data;
        this.metalData.objectId = this.id;
        this.gid = data.gid !== undefined ? data.gid : 'Route.New';
        this.description = data.description !== undefined ? data.description : '';
        this.name = data.name !== undefined ? data.name : this.gid;
        this.metalData.wayPoints = (!this.metalData.routeData || !this.metalData.routeData.wayPoints) ? [] : this.metalData.routeData.wayPoints;
        if (!this.metalData.pointProperties) this.metalData.pointProperties = [];
        this.computeStructure();

        return this;

    },

    copy: function (source) {

        THREE.Object3D.prototype.copy.call(this, source, false);

        this.metalData = JSON.parse(JSON.stringify(source.metalData));
        this.type = source.type;
        this.gid = source.gid !== undefined ? source.gid : '';
        this.description = source.description !== undefined ? source.description : '';
        this.name = source.name !== undefined ? source.name : this.gid;
        this.color = source.color;

        var i = this.children.length - 1;
        for (; i >= 0; i--) {
            this.remove(this.children[i]);
        }

        this.routeWayPoints = source.routeWayPoints.clone();

        this.add(this.routeWayPoints);

        return this;

    }

});

export { RouteObject };