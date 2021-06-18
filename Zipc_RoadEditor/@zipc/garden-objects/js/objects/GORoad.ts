import * as THREE from 'three';

import { DEFAULT_SPEED, DEFAULT_LANE_WIDTH, OSWayUtils, GOArrowLine, GOLane } from './GOWay';
import { GeoUtils } from './GeoUtils';
import { OSMGeoUtils } from '../../../garden-osm-utils/js/OSMObjects';


const SUMO_const_laneOffset = 0;
const SUMO_const_laneWidth = 3.2;
const UNSPECIFIED_WIDTH = -1;

export class GORoad extends THREE.Object3D {

    readonly goType = 'GORoad';

    length: number;
    numLanes: number;
    maxSpeed: number;
    laneWidth: number;
    width: number;
    points: THREE.Vector3[];
    vertices: THREE.Vector3[] = [];
    lanes: GOLane[] = [];

    borderLine: THREE.LineSegments;
    refLine: GOArrowLine;

    constructor(
        name: string,
        length?: number,
        numLanes?: number,
        maxSpeed?: number,
        laneWidth?: number,
        points?: THREE.Vector3[],
        lanes?: any[]
    ) {
        super();

        this.name = name;
        this.length = length !== undefined ? length : 0;
        this.numLanes = numLanes !== undefined ? numLanes : 1;
        this.laneWidth = laneWidth !== undefined ? laneWidth : DEFAULT_LANE_WIDTH;
        this.maxSpeed = maxSpeed !== undefined ? maxSpeed : DEFAULT_SPEED;
        this.width = this.laneWidth * this.numLanes;
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

        this.init(this.numLanes, lanes);
        this.vertices.map(v => { return new THREE.Vector3(v.x, -v.z, v.y) });

        this.borderLine = new THREE.LineSegments(new THREE.EdgesGeometry(GeoUtils.createExtrudeBufferGeometry(this.vertices, this.width)), new THREE.LineBasicMaterial({ color: '#303030', linewidth: 0.01, transparent: false }));
        this.borderLine.visible = false;
        this.borderLine.matrixAutoUpdate = false;
        this.add(this.borderLine);

        // this.refLine = new THREE.Line(new THREE.BufferGeometry().setFromPoints(this.vertices), new THREE.LineBasicMaterial({ color: 0x000000, opacity: 0.35 }));
        this.refLine = new GOArrowLine().setFromPoints(this.vertices);
        this.refLine.translateY(0.3);
        this.refLine.updateMatrix();
        this.refLine.visible = false;
        this.refLine.matrixAutoUpdate = false;
        this.add(this.refLine);

        this.position.set(this.position.x, -this.position.z, this.position.y);
    }

    init(numLanes: number, lanes?: any[]): void {
        this.lanes.forEach(lane => {
            this.remove(lane);
        });
        this.lanes.splice(0);
        this.vertices.splice(0);

        this.points = this.computeLaneShape(0, 0);

        if (lanes !== undefined && lanes.length > 0) {
            lanes.forEach((l) => {
                let goLane = new GOLane(l.name, l.laneType, l.maxSpeed, l.width);
                this.lanes.push(goLane);
                this.add(goLane);
            });
            this.width = this.lanes.reduce((a, b) => a + (b.width || 0), 0);
            this.numLanes = this.lanes.length;
        } else {
            for (let i = 0; i < numLanes; i++) {
                let goLane = new GOLane(`${i}`, 'driving', this.maxSpeed, this.laneWidth);
                this.lanes.push(goLane);
                this.add(goLane);
            }
        }
        this.computeLaneShapes();

        OSWayUtils.writeLineSmooth(this);
    }

    computeLaneShapes(): void {
        if (this.lanes.length < 1 || this.points.length < 1 || this.points[0].equals(this.points[this.points.length - 1])) {
            return;
        }

        // compute offset
        const offsets = new Array<number>(this.lanes.length).fill(0);
        let offset = 0;
        for (let i = this.lanes.length - 2; i >= 0; i--) {
            offset += (this.lanes[i].width + this.lanes[i + 1].width) / 2;
            offsets[i] = offset;
        }

        // LaneSpreadFunction::CENTER
        // const myLaneSpreadFunction = 'LaneSpreadFunction::CENTER';
        const myLaneSpreadFunction = 'LaneSpreadFunction::CENTER';
        /*if (myLaneSpreadFunction === 'LaneSpreadFunction::CENTER01') {
            let width = 0;
            for (let i = 0; i < this.lanes.length; i++) {
                width += this.lanes[i].width;
            }
            width += SUMO_const_laneOffset * (this.lanes.length - 1);
            offset = -width / 2. + this.lanes[this.lanes.length - 1].width / 2.;
            if (this.lanes.length % 2 === 0) {
                offset -= this.lanes[Math.floor(this.lanes.length / 2 + 1)].width / 2.
            }
        } else*/ if (myLaneSpreadFunction === 'LaneSpreadFunction::CENTER') {
            let width = 0;
            for (let i = 0; i < this.lanes.length; i++) {
                width += this.lanes[i].width;
            }
            width += SUMO_const_laneOffset * (this.lanes.length - 1);
            offset = -width / 2. + this.lanes[this.lanes.length - 1].width / 2.;
        } else {
            let laneWidth = this.lanes[this.lanes.length - 1].width != UNSPECIFIED_WIDTH ? this.lanes[this.lanes.length - 1].width : SUMO_const_laneWidth;
            offset = (laneWidth + SUMO_const_laneOffset) / 2.; // @note: offset for half of the center-line marking of the road
        }
        //if (myLaneSpreadFunction === 'LaneSpreadFunction::ROADCENTER') {

        //}

        for (let i = 0; i < this.lanes.length; i++) {
            offsets[i] += offset;
        }

        // build the shape of each lane
        for (let i = 0; i < this.lanes.length; i++) {
            try {
                this.lanes[i].setFromPoints(this.computeLaneShape(i, offsets[i]));
            } catch (e) {
                this.lanes[i].setFromPoints(this.points.map(function (p) {
                    return p.clone();
                }));
            }
        }
    }

    computeLaneShape(lane: number, offset: number): THREE.Vector3[] {
        let result = this.points.map(function (p) {
            return p.clone();
        });
        try {
            result = OSMGeoUtils.move2side(result, offset);
        } catch (e) {
            // TODO
        }
        return result;
    }

    setFromPoints(points: THREE.Vector3[]): GORoad {
        if (points.length < 2) {
            this.points = [new THREE.Vector3(), new THREE.Vector3()];
        } else {
            this.points.forEach(function (p) {
                p.set(p.x, -p.z, p.y);
            });
        }
        this.borderLine.geometry = GeoUtils.createExtrudeBufferGeometry(this.points, this.width);
        this.borderLine.geometry.computeBoundingSphere();
        // this.refLine.geometry = new THREE.BufferGeometry();
        //this.refLine.geometry.computeBoundingSphere();

        return this;
    }

    updateLanes(): void {
        if (this.lanes.length < 1) return;

        this.width = this.lanes.reduce((a, b) => a + (b.width || 0), 0);
        this.vertices.splice(0);
        this.points = this.computeLaneShape(0, 0);

        this.lanes.forEach(l => l.vertices.splice(0));
        this.computeLaneShapes();
        OSWayUtils.writeLineSmooth(this);
        this.vertices.map(v => { return new THREE.Vector3(v.x, -v.z, v.y) });

        this.borderLine.geometry.dispose();
        this.borderLine.geometry = new THREE.EdgesGeometry(GeoUtils.createExtrudeBufferGeometry(this.vertices, this.width));
        this.borderLine.geometry.computeBoundingSphere();
        this.borderLine.updateMatrix();
        this.refLine.setFromPoints(this.vertices);
    }

    addLane(lane?: GOLane, index?: number): number {
        this.numLanes += 1;
        this.width += this.laneWidth;
        this.vertices.splice(0);
        this.points = this.computeLaneShape(0, 0);

        let newLane: GOLane, newIndex: number;
        if (index !== undefined && (index >= 0 && index <= this.numLanes)) {
            newIndex = index;
        } else {
            newIndex = this.numLanes - 1;
        }
        if (lane !== undefined) {
            newLane = lane;
        } else {
            newLane = new GOLane(`${this.numLanes - 1}`, 'driving', this.maxSpeed, this.laneWidth);
        }
        this.lanes.splice(newIndex, 0, newLane);
        //this.children.splice(this.children.indexOf(this.lanes[newIndex - 1]) + 1, 0, newLane);
        //newLane.parent = this;
        this.lanes.forEach(l => l.vertices.splice(0));
        this.computeLaneShapes();
        OSWayUtils.writeLineSmooth(this);
        this.vertices.map(v => { return new THREE.Vector3(v.x, -v.z, v.y) });

        this.borderLine.geometry.dispose();
        this.borderLine.geometry = new THREE.EdgesGeometry(GeoUtils.createExtrudeBufferGeometry(this.vertices, this.width));
        this.borderLine.geometry.computeBoundingSphere();
        this.borderLine.updateMatrix();
        this.refLine.setFromPoints(this.vertices);

        return this.numLanes - 1;
    }

    removeLane(lane: GOLane): number {
        this.numLanes -= 1;
        this.width -= this.laneWidth;
        this.vertices.splice(0);
        this.points = this.computeLaneShape(0, 0);

        let index = this.lanes.indexOf(lane);
        if (!(index >= 0 && index <= this.numLanes)) {
            return -1;
        }
        this.lanes.splice(index, 1);
        //this.remove(lane);
        this.lanes.forEach(l => l.vertices.splice(0));
        this.computeLaneShapes();
        OSWayUtils.writeLineSmooth(this);
        this.vertices.map(v => { return new THREE.Vector3(v.x, -v.z, v.y) });

        this.borderLine.geometry.dispose();
        this.borderLine.geometry = new THREE.EdgesGeometry(GeoUtils.createExtrudeBufferGeometry(this.vertices, this.width));
        this.borderLine.geometry.computeBoundingSphere();
        this.borderLine.updateMatrix();
        this.refLine.setFromPoints(this.vertices);

        return index;
    }

    clone(recursive: boolean): any {
        let newRoad = new GORoad(
            this.name,
            this.length,
            this.numLanes,
            this.maxSpeed,
            this.laneWidth,
            this.points,
            this.lanes
        );
        newRoad.position.copy(this.position);
        return newRoad;
    }

    /*copy(source: any): any {
        THREE.Object3D.prototype.copy.call(this, source, false);

        this.length = source.length;
        this.numLanes = source.numLanes;
        this.maxSpeed = source.maxSpeed;
        this.laneWidth = source.laneWidth;
        this.width = source.width;
        this.points = source.points;
        this.vertices = source.vertices;
        this.lanes = source.lanes;

        return this;
    }*/

    toJSON(): any {
        var data = {
            "name": this.name,
            "goType": this.goType,
            "length": this.length,
            "numLanes": this.numLanes,
            "maxSpeed": this.maxSpeed,
            "laneWidth": this.laneWidth,
            "points": new Array<any>(),
            "lanes": new Array<any>()
        };
        this.points.forEach(p => data['points'].push(new THREE.Vector3(this.position.x, this.position.z, -this.position.y).add(p).toArray()));
        this.lanes.forEach(l => data['lanes'].push(l.toJSON()));
        return data;
    }
}