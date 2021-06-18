
import * as THREE from 'three';

// EdgeHelper
function EdgeHelper(object, color) {

    this.object = object;

    if (color === undefined) color = 0xffff00;

    var geometry = new THREE.BufferGeometry();

    THREE.LineSegments.call(this, geometry, new THREE.LineBasicMaterial({ color: color, toneMapped: false }));

    this.type = 'EdgeHelper';

    this.matrixAutoUpdate = false;

    this.update();

}

EdgeHelper.prototype = Object.assign(Object.create(THREE.LineSegments.prototype), {

    constructor: EdgeHelper,

    update: function (object) {

        if (object !== undefined) {

            console.warn('THREE.EdgeHelper: .update() has no longer arguments.');

        }

        if (this.object === undefined || this.object === null) return;

        var index = null;

        var position = [];

        if (this.object.type === 'Road' || this.object.type === 'Junction') {

            var array = this.object.edge.geometry.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.type === 'Lane') {

            var array = this.object.edgeGeometry.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.type === 'Sprite') {

            var geometry = new THREE.SphereBufferGeometry(0.4, 32, 32);

            position = geometry.attributes.position.array;

        } else if (this.object.type === 'ControlSprite') {

            var geometry = new THREE.SphereBufferGeometry(0.4, 32, 32);

            position = geometry.attributes.position.array;

        } else if (this.object.type === 'RoadMark.Line') {

            index = this.object.geometry.getIndex().clone();

            var array = this.object.geometry.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.type === 'RoadMark.Sign') {

            const edges = new THREE.EdgesGeometry(this.object.geometry);

            var array = edges.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.type === 'RoadMark.Crosswalk') {

            const edges = new THREE.EdgesGeometry(this.object.geometry);

            var array = edges.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.type === 'RoadMark.TurningMark') {

            const edges = new THREE.EdgesGeometry(this.object.geometry);

            var array = edges.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.type === 'JunctionLane1111') {

            var array = this.object.geometry.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.goType === 'GORoad') {

            var array = this.object.borderLine.geometry.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else if (this.object.goType === 'GOLane') {

            var array = new THREE.EdgesGeometry(this.object.planMesh.geometry).attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else {

            // TODO

        }

        if (index !== null) {
            this.geometry.setIndex(index);
        } else {
            this.geometry.setIndex(null);
        }

        this.geometry.setAttribute('position', new THREE.Float32BufferAttribute(position, 3));

        this.geometry.computeBoundingSphere();

        this.geometry.applyMatrix4(this.object.matrixWorld);


    },

    setFromObject: function (object) {

        this.object = object;
        this.update();

        return this;

    },

    copy: function (source) {

        THREE.LineSegments.prototype.copy.call(this, source);

        this.object = source.object;

        return this;

    },

    clone: function () {

        return new this.constructor().copy(this);

    }

});

export { EdgeHelper };