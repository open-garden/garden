import * as THREE from 'three';

const TURNINGMARK_DEFAULT_PARAMETER = { mode: 'turning_mark', position: { 'x': 0.8, 'y': 0 }, scale: 1.5, rotation: 0.0, color: 0xFFFFFF };
const HALF_TURNINGMARK_DEFAULT_PARAMETER = { mode: 'half_turning_mark', position: { 'x': 0.8, 'y': 0 }, scale: 1.5, rotation: 0.0, color: 0xFFFFFF };

type TurningMarkMode = 'turning_mark' | 'half_turning_mark';

interface TurningMarkOptions {
    /**
     * @default 'turning_mark'
     */
    mode: TurningMarkMode;
    /**
     *  @default (1,0)
     */
    position: { x: number, y: number };
    /**
     * @default 1.0
     */
    scale?: number;
    /**
     * @default 0.0
     */
    rotation?: number;
    /**
     * @default 0xFFFFFF
     */
    color?: THREE.Color | string | number;
}

class TurningMark extends THREE.Mesh {

    readonly type = 'RoadMark.TurningMark';

    parameters: TurningMarkOptions;

    constructor(
        parameters?: TurningMarkOptions
    ) {
        super();
        this.parent = null;
        this.parameters = parameters !== undefined ? parameters : { mode: 'turning_mark', position: { 'x': 0.8, 'y': 0 }, scale: 1.5, rotation: 0.0, color: 0xFFFFFF };

        let vertices = (this.geometry as THREE.BufferGeometry).attributes.position;
        if (vertices === undefined) {
            (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute(0, 3);
        }

        if (this.parameters.color !== undefined) {
            (this.material as THREE.MeshBasicMaterial).color.setHex(this.parameters.color as number);
        }

        this.computeGeometry();
        this.position.x = this.parameters.position.x;
        this.position.z = this.parameters.position.y;
    }

    computeGeometry(): void {
        // TODO
        switch (this.parameters.mode) {
            case 'half_turning_mark':
                (this.geometry as THREE.BufferGeometry).setIndex([2, 1, 24, 11, 10, 16, 3, 2, 24, 3, 24, 23, 28, 5, 4, 23, 27, 3, 27, 23, 25, 27, 28, 4, 12, 14, 29, 26, 27, 25, 28, 22, 5, 25, 1, 26, 25, 24, 1, 22, 28, 29, 6, 22, 21, 14, 22, 29, 7, 6, 21, 29, 26, 13, 8, 7, 21, 8, 21, 20, 8, 20, 19, 9, 8, 19, 9, 19, 18, 9, 18, 17, 10, 9, 17, 10, 17, 16, 15, 11, 16, 27, 4, 3, 13, 12, 29, 11, 15, 14, 11, 14, 12, 1, 13, 26, 22, 6, 5].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([0.000000, 0.000000, -1.250000, 0.146172, 0.000000, -1.059581, 0.335034, 0.000000, -0.835967, 0.553217, 0.000000, -0.598680, 0.787352, 0.000000, -0.367242, 1.024069, 0.000000, -0.161175, 1.250000, 0.000000, 0.000000, 1.022233, 0.000000, 0.168074, 0.785124, 0.000000, 0.370483, 0.551508, 0.000000, 0.593552, 0.334224, 0.000000, 0.823610, 0.146109, 0.000000, 1.046984, 0.000000, 0.000000, 1.250000, 0.207771, 0.000000, 0.793027, 0.275096, 0.000000, 0.714495, 0.410315, 0.000000, 0.564647, 0.545344, 0.000000, 0.424105, 0.679044, 0.000000, 0.292487, 0.810275, 0.000000, 0.169416, 0.874609, 0.000000, 0.110966, 1.000000, 0.000000, 0.000000, 0.753888, 0.000000, -0.209651, 0.296484, 0.000000, -0.693688, 0.103874, 0.000000, -0.892961, 0.111975, 0.000000, -0.309324, 0.107504, 0.000000, -0.019142, 0.402838, 0.000000, -0.582465, 0.676408, 0.000000, -0.300803, 0.113086, 0.000000, 0.689113], 3);
                break;
            case 'turning_mark':
            default:
                (this.geometry as THREE.BufferGeometry).setIndex([18, 19, 39, 43, 22, 23, 7, 15, 35, 39, 17, 18, 21, 47, 20, 47, 38, 39, 40, 17, 39, 47, 21, 46, 16, 17, 34, 20, 47, 39, 48, 38, 47, 37, 34, 40, 9, 4, 5, 22, 45, 21, 22, 44, 45, 44, 22, 43, 15, 16, 35, 37, 33, 34, 11, 1, 2, 43, 23, 42, 14, 15, 7, 7, 35, 6, 6, 35, 36, 34, 17, 40, 8, 14, 7, 24, 49, 41, 42, 24, 41, 8, 13, 14, 10, 2, 3, 25, 49, 24, 26, 49, 25, 13, 8, 12, 28, 38, 27, 26, 48, 49, 12, 1, 11, 27, 48, 26, 12, 8, 1, 27, 38, 48, 37, 38, 28, 11, 2, 10, 29, 37, 28, 42, 23, 24, 4, 10, 3, 10, 4, 9, 33, 37, 29, 32, 6, 36, 31, 36, 30, 6, 9, 5, 29, 36, 33, 9, 6, 32, 30, 36, 29, 21, 45, 46, 32, 36, 31, 39, 19, 20, 35, 16, 34].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([0.805294, 0.000000, 0.139973, 0.583851, 0.000000, 0.322974, 0.440412, 0.000000, 0.455257, 0.373878, 0.000000, 0.521747, 0.257733, 0.000000, 0.650861, 0.210741, 0.000000, 0.711661, 0.654666, 0.000000, -0.279759, 1.002844, 0.000000, -0.002625, 0.357643, 0.000000, 0.813701, 0.584837, 0.000000, 0.573576, 0.820373, 0.000000, 0.344296, 1.046978, 0.000000, 0.145637, 1.247375, 0.000000, -0.002625, 1.046978, 0.000000, -0.166733, 0.820373, 0.000000, -0.370222, 0.584837, 0.000000, -0.596825, 0.357643, 0.000000, -0.830272, 0.156064, 0.000000, -1.054295, -0.002625, 0.000000, -1.252625, -0.161313, 0.000000, -1.054295, -0.362892, 0.000000, -0.830272, -0.590087, 0.000000, -0.596825, -0.825623, 0.000000, -0.370222, -1.052227, 0.000000, -0.166733, -1.252625, 0.000000, -0.002625, -1.052227, 0.000000, 0.145637, -0.825623, 0.000000, 0.344296, -0.590087, 0.000000, 0.573576, -0.362892, 0.000000, 0.813700, -0.161313, 0.000000, 1.044892, -0.002625, 0.000000, 1.247375, 0.156064, 0.000000, 1.044892, -0.249688, 0.000000, 0.686997, 0.342107, 0.000000, -0.589240, 0.551876, 0.000000, -0.352508, -0.048832, 0.000000, 0.949460, -0.356141, 0.000000, 0.564904, -0.582054, 0.000000, 0.321572, 0.063448, 0.000000, -0.953699, 0.256991, 0.000000, -0.693960, -0.942997, 0.000000, -0.043647, -0.820296, 0.000000, -0.142566, -0.688122, 0.000000, -0.256544, -0.477656, 0.000000, -0.454583, -0.332913, 0.000000, -0.603883, -0.260165, 0.000000, -0.683517, -0.187589, 0.000000, -0.766385, -0.675993, 0.000000, 0.238402, -1.000000, 0.000000, 0.000000], 3);
                break;
        }

        (this.geometry as THREE.BufferGeometry).rotateY(!this.parameters.rotation ? 0 : this.parameters.rotation * THREE.MathUtils.DEG2RAD);
        (this.geometry as THREE.BufferGeometry).scale(this.parameters.scale||1, 1, this.parameters.scale||1);
        (this.geometry as THREE.BufferGeometry).translate(0, 0.001, 0);
        (this.geometry as THREE.BufferGeometry).computeBoundingSphere();
        
    }

    updateMatrixWorld(force?: boolean): void {
        super.updateMatrixWorld(force);
    }

    toJSON(): any {
        let laneData = {'road':'', 'lane':'' };
        if (this.parent !== null && this.parent.type === 'Junction') {
            const junction = this.parent as any;
            laneData.road = junction.name;
        }else {
            if (this.parent !== null && this.parent.type === 'Lane') {
                const lane = this.parent as any;
                laneData.road = lane.parent.name;
                laneData.lane = lane.name;
            }
        }
       this.parameters.position = { x: this.position.x, y:this.position.z };
        const data = {
            'name': this.name,
            'type': this.type,
            'lane': laneData,
            'parameters': this.parameters
        };

        return data;
    }

    copy(source: this, recursive?: boolean): this {
        super.copy(source, recursive);

        this.parameters = source.parameters;

        this.material = source.material;
        this.geometry = source.geometry;

        return this;
    }
}

export { TurningMark, TURNINGMARK_DEFAULT_PARAMETER, HALF_TURNINGMARK_DEFAULT_PARAMETER }