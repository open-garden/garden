import * as THREE from 'three';

function AbstractWay() {

    THREE.Object3D.call(this);

    this.type = 'AbstractWay';

    this.connectionId = Number.MIN_SAFE_INTEGER;

    this.metalData = {};

    this.name = '';

    this.connectedTo = new Set();

}

AbstractWay.MESH_COLOR = {
    'driving': '#0a0a0a',
    'ramp': '#0a0a0a',
    'center': '#1d4d1d',
    'border': '#010101',
    'sidewalk': '#1a1a1a',
}

AbstractWay.prototype = Object.assign(Object.create(THREE.Object3D.prototype), {

    constructor: AbstractWay,

    isGroup: true,

    getCurrentHand: function () {

        let currentDirection = this.getCurrentDirection();

        return currentDirection === 'left' ? 'right' : 'left';
    },

    getCurrentDirection: function () {

        let currentDirection = 'left';

        if (this.type === 'Road' || this.type === 'Junction') {
            if (this.parent !== null && this.parent.type === 'RoadStructure') {
                currentDirection = this.parent.direction;
            }
        } else if (this.type === 'Lane' || this.type === 'JunctionLane') {
            if (this.parentRoad !== undefined && this.parentRoad !== null && this.parentRoad.parent !== null && this.parentRoad.parent.type === 'RoadStructure') {
                currentDirection = this.parentRoad.parent.direction;
            }
        }

        return currentDirection;
    },

    getRoadStructureObject: function () {
        let roadStructureObject;
        if (this.type === 'Road' || this.type === 'Junction') {
            if (this.parent !== null && this.parent.type === 'RoadStructure') {
                roadStructureObject = this.parent;
            }
        } else if (this.type === 'Lane' || this.type === 'JunctionLane') {
            if (this.parentRoad !== undefined && this.parentRoad !== null && this.parentRoad.parent !== null && this.parentRoad.parent.type === 'RoadStructure') {
                roadStructureObject = this.parentRoad.parent;
            }
        }
        return roadStructureObject;
    },

    isWireframeVisible: function () {
        const roadStructureObject = this.getRoadStructureObject();
        if (roadStructureObject !== undefined && roadStructureObject.editor !== undefined) {
            return roadStructureObject.editor.config.getKey('wireframe');
        }
        return false;
    },

    isRefLineVisible: function () {
        const roadStructureObject = this.getRoadStructureObject();
        if (roadStructureObject !== undefined && roadStructureObject.editor !== undefined) {
            return roadStructureObject.editor.config.getKey('referenceLine');
        }
        return false;
    },

    isCtrlSpriteVisible: function () {
        const roadStructureObject = this.getRoadStructureObject();
        if (roadStructureObject !== undefined && roadStructureObject.editor !== undefined) {
            return roadStructureObject.editor.config.getKey('ctrlSprite');
        }
        return false;
    },

    clone: function (recursive) {

        return new this.constructor().copy(this, recursive);

    },

    copy: function (source, recursive) {

        if (recursive === undefined) recursive = true;

        this.name = source.name;

        this.up.copy(source.up);

        this.position.copy(source.position);
        this.quaternion.copy(source.quaternion);
        this.scale.copy(source.scale);

        this.matrix.copy(source.matrix);
        this.matrixWorld.copy(source.matrixWorld);

        this.matrixAutoUpdate = source.matrixAutoUpdate;
        this.matrixWorldNeedsUpdate = source.matrixWorldNeedsUpdate;

        this.layers.mask = source.layers.mask;
        this.visible = source.visible;

        this.castShadow = source.castShadow;
        this.receiveShadow = source.receiveShadow;

        this.frustumCulled = source.frustumCulled;
        this.renderOrder = source.renderOrder;

        this.userData = JSON.parse(JSON.stringify(source.userData));

        if (recursive === true) {

            for (var i = 0; i < source.children.length; i++) {

                var child = source.children[i];
                if (child.type === 'ReferenceLine') continue;
                this.add(child.clone());

            }

        }

        return this;

    },

});

export { AbstractWay };