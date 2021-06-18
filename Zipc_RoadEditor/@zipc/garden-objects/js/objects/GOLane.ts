import * as THREE from 'three';

import { GOLaneMeshColor, DEFAULT_SPEED, DEFAULT_LANE_WIDTH, OSWayUtils, GOArrowLine } from './GOWay';
import { GeoUtils } from './GeoUtils';

type GOLaneType = 'driving' | 'center' | 'border' | 'sidewalk';

export class GOLane extends THREE.Object3D {

    readonly goType = 'GOLane';

    laneType: GOLaneType;
    maxSpeed: number;
    width: number;
    points: THREE.Vector3[];
    vertices: THREE.Vector3[] = [];

    planMesh: THREE.Mesh;
    refLine: GOArrowLine;

    constructor(
        name: string,
        laneType?: GOLaneType,
        maxSpeed?: number,
        width?: number,
        points?: THREE.Vector3[]
    ) {
        super();

        this.name = name;
        this.laneType = laneType !== undefined ? laneType : 'driving';
        this.maxSpeed = maxSpeed !== undefined ? maxSpeed : DEFAULT_SPEED;
        this.width = width !== undefined ? width : DEFAULT_LANE_WIDTH;
        this.points = points !== undefined ? points : [];

        if (this.points.length > 0) {
            this.position.copy(this.points[0]);
        }

        if (this.points.length < 2) {
            this.points = [new THREE.Vector3(), new THREE.Vector3()];
        } else {
            this.points.forEach((p) => {
                p.sub(this.position);
            });
        }
        this.planMesh = new THREE.Mesh(GeoUtils.createExtrudeBufferGeometry(this.points, this.width), new THREE.MeshStandardMaterial({ color: GOLaneMeshColor[this.laneType], side: THREE.DoubleSide }));
        this.planMesh.castShadow = true;
        this.planMesh.receiveShadow = true;
        this.planMesh.matrixAutoUpdate = false;
        this.add(this.planMesh);

        this.refLine = new GOArrowLine().setFromPoints(this.vertices);
        this.refLine.translateY(0.3);
        this.refLine.updateMatrix();
        this.refLine.visible = false;
        this.refLine.matrixAutoUpdate = false;
        this.add(this.refLine);
    }

    setFromPoints(points: THREE.Vector3[]): GOLane {
        this.points = points !== undefined ? points : [];
        if (points.length < 2) {
            this.points = [new THREE.Vector3(), new THREE.Vector3()];
        }
        this.position.set(0, 0, 0);
        OSWayUtils.writeLineSmooth(this);
        this.vertices.map(v => { return new THREE.Vector3(v.x, -v.z, v.y) });

        (this.planMesh.material as THREE.MeshBasicMaterial).color.setHex(GOLaneMeshColor[this.laneType]);
        this.planMesh.geometry = GeoUtils.createExtrudeBufferGeometry(this.vertices, this.width);
        this.planMesh.geometry.computeBoundingSphere();
        this.planMesh.updateMatrix();
        this.refLine.setFromPoints(this.vertices);

        return this;
    }

    copy(source: any): any {
        THREE.Object3D.prototype.copy.call(this, source, false);

        this.name = source.name;
        this.laneType = source.laneType;
        this.maxSpeed = source.maxSpeed;
        this.width = source.width;
        this.points = source.points;

        return this;
    }

    toJSON(): any {
        var data = {
            "name": this.name,
            "goType": this.goType,
            "laneType": this.laneType,
            "maxSpeed": this.maxSpeed,
            "width": this.width
        };
        return data;
    }

}