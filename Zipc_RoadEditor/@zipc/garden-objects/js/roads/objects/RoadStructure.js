import * as THREE from 'three';


function RoadStructure(editor) {

    THREE.Scene.call(this);

    this.type = 'RoadStructure';

    this._id = '';

    this.name = 'RoadStructure.New';

    this.gid = 'RoadStructure.New';

    this.description = '';

    this.direction = 'left';

    this.editor = editor;

    this.metalData = {};

}

RoadStructure.prototype = Object.assign(Object.create(THREE.Scene.prototype), {

    constructor: RoadStructure,

    isGroup: true,

    clone: function (recursive) {

        return new this.constructor().copy(this, recursive);

    },

    copy: function (source, recursive) {

        THREE.Scene.prototype.copy.call(this, source, recursive);

        this.type = source.type;
        this._id = source._id;
        this.gid = source.gid !== undefined ? source.gid : '';
        this.description = source.description !== undefined ? source.description : '';
        this.name = source.name !== undefined ? source.name : this.gid;
        this.direction = source.direction;
        this.metalData = JSON.parse(JSON.stringify(source.metalData));

        return this;

    },

});

export { RoadStructure };