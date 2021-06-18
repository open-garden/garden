
import * as THREE from 'three';

const _texture = new THREE.TextureLoader().load("textures/circle.png");
const _material = new THREE.SpriteMaterial({ map: _texture, color: '#69f' });

// SpriteHelper
function SpriteHelper(object, color) {

    this.object = object;

    this.color = color || '#69f';

    this.invertColor = new THREE.Color().setRGB(1 - this.color.r, 1 - this.color.g, 1 - this.color.b);

    THREE.Group.call(this);

    this.type = 'SpriteHelper';

    this.matrixAutoUpdate = false;

    this.update();

}

SpriteHelper.prototype = Object.assign(Object.create(THREE.Group.prototype), {

    constructor: SpriteHelper,

    update: function (object) {

        for (let i = this.children.length - 1; i >= 0; i--) {
            if (this.children[i].type === 'Sprite') this.remove(this.children[i]);
        }

        if (object !== undefined) {

            console.warn('THREE.SpriteHelper: .update() has no longer arguments.');

        }

        if (this.object === undefined || this.object === null) return;

        var position = [];

        if (Array.isArray(this.object)) {

            var material = _material.clone();
            material.color.set(this.color);

            for (var i = 0, l = this.object.length; i < l; i++) {

                var sprite = new THREE.Sprite(material.clone());
                sprite.index = i;
                sprite.position.copy(this.object[i]);
                sprite.scale.set(0.5, 0.5, 0.5);
                this.add(sprite);

            }

        } else if (this.object.type === 'Route') {

            var points = this.object.metalData.routeData.points;
            var propts = this.object.metalData.pointProperties;

            var material = _material.clone();

            for (var i = 0, l = points.length; i < l; i++) {
                propts.findIndex(p => p.index === i) >= 0 ?
                    material.color.setRGB(this.invertColor)
                    :
                    material.color.set(this.color);

                var sprite = new THREE.Sprite(material.clone());
                sprite.index = i;
                sprite.position.copy(points[i]);
                sprite.scale.set(0.5, 0.5, 0.5);
                this.add(sprite);

            }

        } else if (this.object.type === 'Lane') {

            var array = this.object.refLine.geometry.attributes.position.array;

            for (var i = 0, l = array.length; i < l; i += 3) {

                position.push(array[i]);

                var sprite = new THREE.Sprite(_material.clone());
                sprite.index = i;
                sprite.position.copy(this.object.localToWorld(new THREE.Vector3(array[i], array[i + 1], array[i + 2])));
                sprite.scale.set(0.5, 0.5, 0.5);
                this.add(sprite);

            }

        } else if (this.object.goType === 'GOLane') {

            var array = new THREE.EdgesGeometry(this.object.planMesh.geometry).attributes.position.array;

            for (var i = 0, l = array.length; i < l; i++) {

                position.push(array[i]);

            }

        } else {

            // TODO

        }

    },

    setFromObject: function (object) {

        this.object = object;
        this.update();

        return this;

    },

    copy: function (source) {

        THREE.Group.prototype.copy.call(this, source);

        this.object = source.object;

        return this;

    },

    clone: function () {

        return new this.constructor().copy(this);

    }

});

export { SpriteHelper };