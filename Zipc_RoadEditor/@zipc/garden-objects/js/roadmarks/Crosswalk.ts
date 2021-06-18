import * as THREE from 'three';

const _texture = new THREE.TextureLoader().load("textures/circle.png");
const _material = new THREE.SpriteMaterial({ map: _texture, depthTest: false, color: 0x0000ff });
const _material2 = new THREE.SpriteMaterial({ map: _texture, depthTest: false, color: 0x0000ff });

// TODO
const DEFAULT_VERTICES = [
    new THREE.Vector3(-0.500000, 0.000000, -0.250000),
    new THREE.Vector3(0.500000, 0.000000, -0.250000),
    new THREE.Vector3(0.500000, 0.000000, -0.750000),
    new THREE.Vector3(-0.500000, 0.000000, -0.750000)
];
const DEFAULT_VERTICES2 = [
    new THREE.Vector3(-0.500000, 0.000000, -0.250000),
    new THREE.Vector3(0.500000, 0.000000, -0.250000),
    new THREE.Vector3(0.500000, 0.000000, -0.750000),
    new THREE.Vector3(-0.500000, 0.000000, -0.750000)
];
const DEFAULT_INDICES = [1, 3, 0, 1, 2, 3];

const AXIS = new THREE.Vector3(0, 0, -1);

interface CrosswalkOptions {
    /**
     * @default (1,0)
     */
    position: { x: number, y: number };
    /**
     * @default (0,0,0)
     */
    startPosition: { x: number, y: number, z: number };
    /**
     * @default (0,0,10)
     */
    endPosition: { x: number, y: number, z: number };
    /**
     * @default 1.0
     */
    width: number;
    /**
     * @default 0.0
     */
    skew: number;
    /**
     * @default 0xFFFFFF
     */
    color: THREE.Color | string | number;
}

class Crosswalk extends THREE.Mesh {

    readonly type = 'RoadMark.Crosswalk';

    parameters: CrosswalkOptions;

    sprite01: THREE.Sprite;

    sprite02: THREE.Sprite;

    oldPositionS1 : THREE.Vector3;

    oldPositionS2 : THREE.Vector3;

    oldWidth : number;

    oldSkew : number;

    constructor(
        parameters?: CrosswalkOptions
    ) {
        super();
        this.parameters = parameters !== undefined ? parameters : { position: { 'x': 1, 'y': 0 }, startPosition: { 'x': 0, 'y': 0, 'z': 0 }, endPosition: { 'x': 0, 'y': 0, 'z': 10 }, width: 4, skew: 0.0, color: 0xFFFFFF };

        let vertices = (this.geometry as THREE.BufferGeometry).attributes.position;
        if (vertices === undefined) {
            (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute(0, 3);
        }

        if (this.parameters.color !== undefined) {
            (this.material as THREE.MeshBasicMaterial).color.setHex(this.parameters.color as number);
        }

        this.sprite01 = new THREE.Sprite(_material.clone());
        this.sprite01.type = 'Crosswalk.Sprite' as any;
        this.sprite01.position.set(this.parameters.startPosition.x, this.parameters.startPosition.y, this.parameters.startPosition.z);

        this.sprite02 = new THREE.Sprite(_material2.clone());
        this.sprite02.type = 'Crosswalk.Sprite' as any;
        this.sprite02.position.set(this.parameters.endPosition.x, this.parameters.endPosition.y, this.parameters.endPosition.z);

        this.oldPositionS1 = new THREE.Vector3;
        this.oldPositionS2 = new THREE.Vector3;
        this.oldWidth = 0;
        this.oldSkew = 0;

        this.add(this.sprite01);
        this.add(this.sprite02);

        this.computeGeometry();
    }

    computeGeometry(): void {
        // Start -> End の向きを求めて、座標変換のMatrixを作成する
        let quaternionE = new THREE.Quaternion();
        let quaternionS = new THREE.Quaternion();
        this.sprite01.position.set(this.parameters.startPosition.x, /*this.parameters.startPosition.y*/0, this.parameters.startPosition.z);
        this.sprite02.position.set(this.parameters.endPosition.x, /*this.parameters.endPosition.y*/0, this.parameters.endPosition.z);
        this.parameters.startPosition = this.sprite01.position;
        this.parameters.endPosition = this.sprite02.position;
        let spriteS = new THREE.Vector3().copy(this.sprite01.position);
        let spriteE = new THREE.Vector3().copy(this.sprite02.position);
        let width = new THREE.Vector3(this.parameters.width, 1, 1);
        // end → start startが正しい
        let directionS = spriteS.clone().sub(spriteE);
        // start → end endが正しい
        let directionE = spriteE.clone().sub(spriteS);
        directionS = new THREE.Vector3().copy(directionS).normalize();
        directionE = new THREE.Vector3().copy(directionE).normalize();
        let distance = Math.floor(spriteE.distanceTo(spriteS));
        quaternionE.setFromUnitVectors(AXIS, directionE);
        quaternionS.setFromUnitVectors(AXIS, directionS);
        let m1 = new THREE.Matrix4().makeRotationY(this.parameters.skew * THREE.MathUtils.DEG2RAD);
        let maxtrixE = new THREE.Matrix4().compose(new THREE.Vector3, quaternionE, new THREE.Vector3(1, 1, 1));
        let maxtrixS = new THREE.Matrix4().compose(new THREE.Vector3, quaternionS, new THREE.Vector3(1, 1, 1));
         
        // 頂点 と インデックスの情報を計算する
        let tempV = new THREE.Vector3();
        let indexes = [] as any;
        let newVertices = [] as any;
        let n = 1 / Math.cos(this.parameters.skew * THREE.MathUtils.DEG2RAD);

        if (this.oldPositionS1.distanceTo(this.sprite01.position) >= 0.001) {
            for (let i = 0; i * n < distance; i++) {
            
                Array.prototype.push.apply(newVertices, DEFAULT_VERTICES.map((v) => {
                    maxtrixS.setPosition(tempV.copy(directionS).multiplyScalar(i * n));
                    let newV = new THREE.Vector3().copy(v);
                    newV.multiply(width).applyMatrix4(m1).applyMatrix4(maxtrixS);
                    return newV;
                }));
                Array.prototype.push.apply(indexes, DEFAULT_INDICES.map(n => n + i * 4));
            }

            let newGeometry = new THREE.BufferGeometry();
            newGeometry.setIndex(indexes);
            newGeometry.attributes.position = new THREE.Float32BufferAttribute(DEFAULT_VERTICES.length * 3 * distance, 3).copyVector3sArray(newVertices);
 
            // Geometryの更新
            this.geometry.dispose();
            this.geometry = newGeometry;
            this.geometry.translate(spriteE.x,0.001,spriteE.z);
            this.geometry.computeBoundingSphere();
        
        
        } else if (this.oldPositionS2.distanceTo(this.sprite02.position) >= 0.001 || this.oldWidth !== this.parameters.width || this.oldSkew !== this.parameters.skew ) {
            for (let i = 0; i * n < distance; i++) {
            
                Array.prototype.push.apply(newVertices, DEFAULT_VERTICES2.map((v) => {
                    maxtrixE.setPosition(tempV.copy(directionE).multiplyScalar(i * n));
                    let newV = new THREE.Vector3().copy(v);
                    newV.multiply(width).applyMatrix4(m1).applyMatrix4(maxtrixE);
                    return newV;
                }));
                Array.prototype.push.apply(indexes, DEFAULT_INDICES.map(n => n + i * 4));
            }

            let newGeometry = new THREE.BufferGeometry();
            newGeometry.setIndex(indexes);
            newGeometry.attributes.position = new THREE.Float32BufferAttribute(DEFAULT_VERTICES2.length * 3 * distance, 3).copyVector3sArray(newVertices);
 
            // Geometryの更新
            this.geometry.dispose();
            this.geometry = newGeometry;
            this.geometry.translate(spriteS.x,0.001,spriteS.z);
            this.geometry.computeBoundingSphere();
        }

        this.oldPositionS1 = new THREE.Vector3().copy(this.sprite01.position);
        this.oldPositionS2 = new THREE.Vector3().copy(this.sprite02.position);
        this.oldWidth = this.parameters.width;
        this.oldSkew = this.parameters.skew;
        this.sprite01.position.y = 0.001;
        this.sprite02.position.y = 0.001;
        
        // ##################
        let curve3 = null;
        curve3 = this.parent === null || this.parent.type !== 'Road' ? null : (this.parent as any).curve3;
        if (curve3 === null) {
            return;
        }


        let v = new THREE.Vector3(), g = new THREE.Vector3(), t = new THREE.Vector3()
        let sOffset = this.parameters.position ? this.parameters.position.x : 1;
        let tOffset = this.parameters.position ? this.parameters.position.y : 0;
        let iOffset = sOffset * 1000;
        var extrudePts = curve3.getSpacedPoints(1000);
        var splineTube = curve3.computeFrenetFrames(1000, false);

        g.copy(splineTube.normals[iOffset]).multiplyScalar(tOffset * 30);
        v.copy(extrudePts[iOffset]).add(g);

        t.copy(splineTube.tangents[iOffset]);
        this.position.copy(v);

    }

    updateMatrixWorld(force?: boolean): void {
        super.updateMatrixWorld(force);
    }

    toJSON(): any {
        let laneData = { 'road': ''};
        if (this.parent !== null && this.parent.type === 'Road') {
            const road = this.parent as any;
            laneData.road = road.name;
        } else if (this.parent !== null && this.parent.type === 'Junction') {
            const junction = this.parent as any;
            laneData.road = junction.name;
        }
        const data = {
            'name': this.name,
            'type': this.type,
            'lane': laneData,
            'parameters': this.parameters,
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

export { Crosswalk }