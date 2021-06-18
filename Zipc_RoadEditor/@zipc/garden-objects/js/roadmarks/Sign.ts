import * as THREE from 'three';

const AXIS = new THREE.Vector3(0, 0, -1);

const ARROW_DEFAULT_PARAMETER = { mode: 'arrow_straight', position: { 'x': 0.8, 'y': 0 }, scale: 1, skew: 0.0, color: 0xFFFFFF };

const STOPLINE_DEFAULT_PARAMETER = { mode: 'stop_line', position: { 'x': 1, 'y': 0 }, scale: 3.5, skew: 0.0, color: 0xFFFFFF };

type SignMode = 'stop_line' | 'arrow_straight' | 'arrow_left' | 'arrow_right' | 'arrow_straight_left' | 'arrow_straight_right';

interface SignOptions {
    /**
     * @default 'arrow_straight'
     */
    mode: SignMode;
    /**
     * @default (1,0)
     */
    position?: { x: number, y: number };
    /**
     * @default 1.0
     */
    scale?: number;
    /**
     * @default 0.0
     */
    skew?: number;
    /**
     * @default 0xFFFFFF
     */
    color?: THREE.Color | string | number;
}

class Sign extends THREE.Mesh {

    readonly type = 'RoadMark.Sign';

    parameters: SignOptions;

    constructor(
        parameters?: SignOptions
    ) {
        super();
        this.parent = null;
        this.parameters = parameters !== undefined ? parameters : { mode: 'arrow_straight', position: { 'x': 0.8, 'y': 0 }, scale: 1, skew: 0.0, color: 0xFFFFFF };

        let vertices = (this.geometry as THREE.BufferGeometry).attributes.position;
        if (vertices === undefined) {
            (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute(0, 3);
        }

        if (this.parameters.color !== undefined) {
            (this.material as THREE.MeshBasicMaterial).color.setHex(this.parameters.color as number);
            // (this.material as THREE.MeshBasicMaterial).side = THREE.DoubleSide;
        }

        this.computeGeometry();
    }

    computeGeometry(): void {
        // TODO
        switch (this.parameters.mode) {
            case 'stop_line':
                (this.geometry as THREE.BufferGeometry).setIndex([2, 4, 1, 2, 3, 4].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([-1.000000, 0.000000, 0.200000, 0.000000, 0.000000, 0.200000, 0.000000, 0.000000, -0.200000, -1.000000, 0.000000, -0.200000], 3);
                break;
            case 'arrow_left':
                (this.geometry as THREE.BufferGeometry).setIndex([24, 3, 23, 32, 1, 2, 2, 3, 31, 3, 4, 19, 31, 3, 30, 4, 5, 19, 5, 6, 19, 6, 7, 19, 7, 8, 12, 8, 9, 11, 9, 10, 11, 11, 12, 8, 12, 13, 19, 13, 14, 19, 15, 16, 17, 14, 15, 19, 12, 19, 7, 32, 2, 31, 30, 3, 29, 15, 17, 19, 17, 18, 19, 19, 20, 3, 29, 3, 28, 28, 3, 27, 20, 21, 3, 27, 3, 26, 26, 3, 25, 21, 22, 3, 22, 23, 3, 25, 3, 24].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([-0.075000, 0.000000, 2.500000, 0.075000, 0.000000, 2.500000, 0.075000, 0.000000, -1.300000, 0.074077, 0.000000, -1.348765, 0.070886, 0.000000, -1.395005, 0.064795, 0.000000, -1.438634, 0.055170, 0.000000, -1.479570, 0.041378, 0.000000, -1.517728, 0.022787, 0.000000, -1.553024, -0.001237, 0.000000, -1.585375, -0.031327, 0.000000, -1.614696, -0.068115, 0.000000, -1.640903, -0.112234, 0.000000, -1.663912, -0.164319, 0.000000, -1.683640, -0.225000, 0.000000, -1.700000, -0.225000, 0.000000, -2.500000, -0.675000, 0.000000, -1.400000, -0.225000, 0.000000, -0.300000, -0.225000, 0.000000, -1.100000, -0.192856, 0.000000, -1.091357, -0.165637, 0.000000, -1.081192, -0.142936, 0.000000, -1.069523, -0.124349, 0.000000, -1.056370, -0.109468, 0.000000, -1.041752, -0.097888, 0.000000, -1.025687, -0.089203, 0.000000, -1.008196, -0.083008, 0.000000, -0.989296, -0.078895, 0.000000, -0.969007, -0.076461, 0.000000, -0.947349, -0.075298, 0.000000, -0.924340, -0.075000, 0.000000, -0.900000, -0.075000, 0.000000, 2.500000], 3);
                break;
            case 'arrow_right':
                (this.geometry as THREE.BufferGeometry).setIndex([24, 23, 3, 32, 2, 1, 2, 31, 3, 3, 19, 4, 31, 30, 3, 4, 19, 5, 5, 19, 6, 6, 19, 7, 7, 12, 8, 8, 11, 9, 9, 11, 10, 11, 8, 12, 12, 19, 13, 13, 19, 14, 15, 17, 16, 14, 19, 15, 12, 7, 19, 32, 31, 2, 30, 29, 3, 15, 19, 17, 17, 19, 18, 19, 3, 20, 29, 28, 3, 28, 27, 3, 20, 3, 21, 27, 26, 3, 26, 25, 3, 21, 3, 22, 22, 3, 23, 25, 24, 3].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([0.075000, 0.000000, 2.500000, -0.075000, 0.000000, 2.500000, -0.075000, 0.000000, -1.300000, -0.074077, 0.000000, -1.348765, -0.070886, 0.000000, -1.395005, -0.064795, 0.000000, -1.438634, -0.055170, 0.000000, -1.479570, -0.041378, 0.000000, -1.517728, -0.022787, 0.000000, -1.553024, 0.001237, 0.000000, -1.585375, 0.031327, 0.000000, -1.614696, 0.068115, 0.000000, -1.640903, 0.112234, 0.000000, -1.663912, 0.164319, 0.000000, -1.683640, 0.225000, 0.000000, -1.700000, 0.225000, 0.000000, -2.500000, 0.675000, 0.000000, -1.400000, 0.225000, 0.000000, -0.300000, 0.225000, 0.000000, -1.100000, 0.192856, 0.000000, -1.091357, 0.165637, 0.000000, -1.081192, 0.142936, 0.000000, -1.069523, 0.124349, 0.000000, -1.056370, 0.109468, 0.000000, -1.041752, 0.097888, 0.000000, -1.025687, 0.089203, 0.000000, -1.008196, 0.083008, 0.000000, -0.989296, 0.078895, 0.000000, -0.969007, 0.076461, 0.000000, -0.947349, 0.075298, 0.000000, -0.924340, 0.075000, 0.000000, -0.900000, 0.075000, 0.000000, 2.500000], 3);
                break;
            case 'arrow_straight_left':
                (this.geometry as THREE.BufferGeometry).setIndex([27, 28, 8, 36, 1, 2, 3, 4, 5, 36, 2, 35, 2, 3, 8, 35, 2, 34, 34, 2, 8, 5, 6, 7, 8, 9, 20, 9, 10, 19, 19, 10, 18, 5, 7, 3, 7, 8, 3, 10, 11, 18, 33, 34, 8, 11, 12, 17, 18, 11, 17, 12, 13, 16, 13, 14, 16, 14, 15, 16, 16, 17, 12, 19, 20, 9, 20, 21, 22, 22, 23, 24, 22, 24, 8, 24, 25, 8, 20, 22, 8, 32, 33, 8, 31, 32, 8, 25, 26, 8, 31, 8, 30, 30, 8, 29, 29, 8, 28, 26, 27, 8].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([-0.075000, 0.000000, 2.500000, 0.075000, 0.000000, 2.500000, 0.075000, 0.000000, 0.000000, 0.225000, 0.000000, 0.000000, 0.000000, 0.000000, -2.500000, -0.225000, 0.000000, 0.000000, -0.075000, 0.000000, 0.000000, -0.075000, 0.000000, 1.200000, -0.075165, 0.000000, 1.175683, -0.075978, 0.000000, 1.152735, -0.077919, 0.000000, 1.131163, -0.081464, 0.000000, 1.110974, -0.087093, 0.000000, 1.092173, -0.095284, 0.000000, 1.074768, -0.106514, 0.000000, 1.058765, -0.121262, 0.000000, 1.044170, -0.140006, 0.000000, 1.030989, -0.163225, 0.000000, 1.019230, -0.191397, 0.000000, 1.008898, -0.225000, 0.000000, 1.000000, -0.225000, 0.000000, 0.200000, -0.675000, 0.000000, 1.300000, -0.225000, 0.000000, 2.400000, -0.225000, 0.000000, 1.600000, -0.192578, 0.000000, 1.608898, -0.165178, 0.000000, 1.619230, -0.142379, 0.000000, 1.630989, -0.123761, 0.000000, 1.644170, -0.108905, 0.000000, 1.658765, -0.097392, 0.000000, 1.674768, -0.088801, 0.000000, 1.692173, -0.082714, 0.000000, 1.710974, -0.078710, 0.000000, 1.731163, -0.076369, 0.000000, 1.752735, -0.075272, 0.000000, 1.775683, -0.075000, 0.000000, 1.800000], 3);
                break;
            case 'arrow_straight_right':
                (this.geometry as THREE.BufferGeometry).setIndex([27, 8, 28, 36, 2, 1, 3, 5, 4, 36, 35, 2, 2, 8, 3, 35, 34, 2, 34, 8, 2, 5, 7, 6, 8, 20, 9, 9, 19, 10, 19, 18, 10, 5, 3, 7, 7, 3, 8, 10, 18, 11, 33, 8, 34, 11, 17, 12, 18, 17, 11, 12, 16, 13, 13, 16, 14, 14, 16, 15, 16, 12, 17, 19, 9, 20, 20, 22, 21, 22, 24, 23, 22, 8, 24, 24, 8, 25, 20, 8, 22, 32, 8, 33, 31, 8, 32, 25, 8, 26, 31, 30, 8, 30, 29, 8, 29, 28, 8, 26, 8, 27].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([0.075000, 0.000000, 2.500000, -0.075000, 0.000000, 2.500000, -0.075000, 0.000000, 0.000000, -0.225000, 0.000000, 0.000000, 0.000000, 0.000000, -2.500000, 0.225000, 0.000000, 0.000000, 0.075000, 0.000000, 0.000000, 0.075000, 0.000000, 1.200000, 0.075165, 0.000000, 1.175683, 0.075978, 0.000000, 1.152735, 0.077919, 0.000000, 1.131163, 0.081464, 0.000000, 1.110974, 0.087093, 0.000000, 1.092173, 0.095284, 0.000000, 1.074768, 0.106514, 0.000000, 1.058765, 0.121262, 0.000000, 1.044170, 0.140006, 0.000000, 1.030989, 0.163225, 0.000000, 1.019230, 0.191397, 0.000000, 1.008898, 0.225000, 0.000000, 1.000000, 0.225000, 0.000000, 0.200000, 0.675000, 0.000000, 1.300000, 0.225000, 0.000000, 2.400000, 0.225000, 0.000000, 1.600000, 0.192578, 0.000000, 1.608898, 0.165178, 0.000000, 1.619230, 0.142379, 0.000000, 1.630989, 0.123761, 0.000000, 1.644170, 0.108905, 0.000000, 1.658765, 0.097392, 0.000000, 1.674768, 0.088801, 0.000000, 1.692173, 0.082714, 0.000000, 1.710974, 0.078710, 0.000000, 1.731163, 0.076369, 0.000000, 1.752735, 0.075272, 0.000000, 1.775683, 0.075000, 0.000000, 1.800000], 3);
                break;
            case 'arrow_straight':
            default:
                (this.geometry as THREE.BufferGeometry).setIndex([3, 5, 7, 7, 1, 2, 3, 4, 5, 7, 2, 3, 5, 6, 7].map(i => i - 1));
                (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute([-0.075000, 0.000000, 2.500000, 0.075000, 0.000000, 2.500000, 0.075000, 0.000000, 0.000000, 0.225000, 0.000000, 0.000000, 0.000000, 0.000000, -2.500000, -0.225000, 0.000000, 0.000000, -0.075000, 0.000000, 0.000000], 3);
                break;
        }
        (this.geometry as THREE.BufferGeometry).translate(0, 0.001, 0);
        (this.geometry as THREE.BufferGeometry).computeBoundingSphere();


        // ##################
        let curve3 = null;
        if (this.parameters.mode === 'stop_line') {
            curve3 = this.parent === null || this.parent.type !== 'Road' ? null : (this.parent as any).curve3;
        } else {
            curve3 = this.parent === null || this.parent.type !== 'Lane' ? null : (this.parent as any).waypointLineCurve3;
        }
        if (curve3 === null) {
            return;
        }


        let v = new THREE.Vector3(), n = new THREE.Vector3(), t = new THREE.Vector3(), q = new THREE.Quaternion();
        let sOffset = this.parameters.position ? this.parameters.position.x : 0.8;
        let tOffset = this.parameters.position ? this.parameters.position.y : 0;
        let iOffset = sOffset * 1000;
        var extrudePts = curve3.getSpacedPoints(1000);
        var splineTube = curve3.computeFrenetFrames(1000, false);

        n.copy(splineTube.normals[iOffset]).multiplyScalar(tOffset * 30);
        v.copy(extrudePts[iOffset]).add(n);

        t.copy(splineTube.tangents[iOffset]);
        //t.copy(this.parameters.mode === 'stop_line' ? splineTube.normals[iOffset] : splineTube.tangents[iOffset]);
        q.setFromUnitVectors(AXIS, t);
        this.quaternion.copy(q);
        this.rotateY(!this.parameters.skew ? 0 : this.parameters.skew * THREE.MathUtils.DEG2RAD);
        this.scale.copy(new THREE.Vector3(this.parameters.scale, 1, this.parameters.mode === 'stop_line' ? 1 : this.parameters.scale));
        this.position.copy(v);
    }

    updateMatrixWorld(force?: boolean): void {
        super.updateMatrixWorld(force);
    }

    toJSON(): any {
        let laneData = { 'road': '', 'lane': '' };
        if (this.parameters.mode === 'stop_line') {
            if (this.parent !== null && this.parent.type === 'Road') {
                const road = this.parent as any;
                laneData.road = road.name;
            }
        } else {
            if (this.parent !== null && this.parent.type === 'Lane') {
                const lane = this.parent as any;
                laneData.road = lane.parent.name;
                laneData.lane = lane.name;
            }
        }
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

export { Sign, ARROW_DEFAULT_PARAMETER, STOPLINE_DEFAULT_PARAMETER }