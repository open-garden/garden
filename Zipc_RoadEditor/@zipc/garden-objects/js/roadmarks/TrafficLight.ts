import * as THREE from 'three';
import { Vector3 } from 'three';
import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader.js';

const AXIS = new Vector3(0, 0, -1);

interface TrafficLightOptions {
    /**
     * @default (1,0)
     */
    position?: { x: number, y: number };
    /**
     * @default (0,0,0)
     */
    positionVec?: { x: number, y: number, z: number };
    /**
     * @default false
     */
    orientation?: boolean;
    /**
     * @default 0
     */
    rotation?: number;
}

let  MODEL : THREE.Object3D;
new FBXLoader().load('model/trafficLight.fbx', (fbx) => {
    (fbx as THREE.Object3D).scale.set(0.03, 0.03, 0.03);
    MODEL = fbx;
});

class TrafficLight extends THREE.Mesh {

    readonly type = 'RoadMark.Signal.TrafficLight';

    parameters: TrafficLightOptions;

    constructor(
        parameters?: TrafficLightOptions
    ) {
        super();
        this.parent = null;
        this.parameters = parameters !== undefined ? parameters : { position: { 'x': 0, 'y': 0 }, positionVec: { x: 0, y: 0, z: 0 }, orientation: false, rotation: 0 };

        let vertices = (this.geometry as THREE.BufferGeometry).attributes.position;
        if (vertices === undefined) {
            (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute(0, 3);
        }
        const model = new THREE.Object3D().copy(MODEL);
        this.add(model);

        this.computeGeometry();
    }

    computeGeometry(): void {
        (this.geometry as THREE.BufferGeometry).setAttribute('position', new THREE.Float32BufferAttribute([0, 0, 0], 3));

        (this.geometry as THREE.BufferGeometry).translate(0, 0.02, 0);
        (this.geometry as THREE.BufferGeometry).computeBoundingSphere();


        // ##################
        let curve3 = null;
        curve3 = this.parent === null || this.parent.type !== 'Road' ? null : (this.parent as any).curve3;
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
        q.setFromUnitVectors(AXIS, t);
        this.quaternion.copy(q);
        this.rotateY((this.parameters.rotation || 0) * THREE.MathUtils.DEG2RAD + (this.parameters.orientation ? Math.PI * 1 : 0));
        this.position.copy(v);
        const p = this.parameters.positionVec;
        this.position.add(new THREE.Vector3(p?.x, p?.y, p?.z));
    }

    updateMatrixWorld(force?: boolean): void {
        super.updateMatrixWorld(force);
    }

    toJSON(): any {
        let laneData = { 'road': '', 'lane': '' };
        if (this.parent !== null && this.parent.type === 'Road') {
            const road = this.parent as any;
            laneData.road = road.name;
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

export { TrafficLight as TrafficLight }