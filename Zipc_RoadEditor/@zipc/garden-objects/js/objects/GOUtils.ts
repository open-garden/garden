
import * as THREE from "three";

import { OSMWay, OSMLane, OSMGeoUtils, POSITION_EPS } from './OSMObjects';

let d3 = require('d3');

export namespace GOUtils {

    const default_boundary: IBoundary = { n: 35.5191000, e: 139.5934900, s: 35.518860, w: 139.5933600 };

    export const default_straight_threshold: number = 0.00000001;
    export const default_lane_width: number = 4;
    export const default_scale = 1;


    export function writeNetworkaaa(ways: any, overpassData: any, boundary: IBoundary): any {
        const straightThresh: number = THREE.MathUtils.degToRad(default_straight_threshold);
        default_boundary.n = boundary.n;
        default_boundary.e = boundary.e;
        default_boundary.s = boundary.s;
        default_boundary.w = boundary.w;

        ways.forEach(function (way: any) {
            try {
                var wayData: any, wayNodes: THREE.Vector3[];
                wayData = JSON.parse(JSON.stringify(way));
                wayNodes = getNodes(overpassData, way).map(function (node: any) {
                    return nodeToVec3(node);
                });

                if (!way.tags.hasOwnProperty('maxspeed')) {
                    way.tags['maxspeed'] = 30;
                }
                if (!way.tags.hasOwnProperty('lanes')) {
                    way.tags['lanes'] = 1;
                }
                if (!way.tags.hasOwnProperty('laneWidth')) {
                    way.tags['laneWidth'] = 4;
                }
                way.tags['lanes'] = 2;

                const fromNode = new THREE.Vector3(wayNodes[0].x, wayNodes[0].y, wayNodes[0].z);
                if (way.tags.hasOwnProperty('highway') && (
                    way.tags.highway.startsWith('motorway') ||
                    way.tags.highway.startsWith('trunk') ||
                    way.tags.highway.startsWith('primary'))) {
                    const beg = way.nodes[0];
                    for (let i in overpassData.way) {
                        const w = overpassData.way[i];
                        if (w.tags === undefined) continue;
                        try {
                            if (w.tags.hasOwnProperty('highway') && (
                                w.tags.highway.startsWith('motorway') ||
                                w.tags.highway.startsWith('trunk') ||
                                w.tags.highway.startsWith('primary')) && w.nodes.length > 1 && w.nodes[w.nodes.length - 1] === beg) {
                                let v1 = nodeToVec3(overpassData.node[w.nodes[w.nodes.length - 2]]);
                                let v2 = nodeToVec3(overpassData.node[beg]);
                                fromNode.set((v1.x + v2.x) / 2, (v1.y + v2.y) / 2, (v1.z + v2.z) / 2);
                                // way.tags['lanes'] = w.tags['lanes'] !== undefined ? w.tags['lanes'] : way.tags['lanes'];
                            }
                        } catch (e) {
                            // TODO console.log(way.id);
                        }
                    }
                }

                const toNode = new THREE.Vector3(wayNodes[wayNodes.length - 1].x, wayNodes[wayNodes.length - 1].y, wayNodes[wayNodes.length - 1].z);
                if (way.tags.hasOwnProperty('highway') && (
                    way.tags.highway.startsWith('motorway') ||
                    way.tags.highway.startsWith('trunk') ||
                    way.tags.highway.startsWith('primary'))) {
                    const end = way.nodes[wayNodes.length - 1];
                    for (let i in overpassData.way) {
                        const w = overpassData.way[i];
                        if (w.tags === undefined) continue;
                        if (w.tags.hasOwnProperty('highway') && (
                            w.tags.highway.startsWith('motorway') ||
                            w.tags.highway.startsWith('trunk') ||
                            w.tags.highway.startsWith('primary')) && w.nodes.length > 0 && w.nodes[0] === end) {
                            let v1 = nodeToVec3(overpassData.node[way.nodes[wayNodes.length - 1]]);
                            let v2 = nodeToVec3(overpassData.node[way.nodes[wayNodes.length - 2]]);
                            toNode.set((v1.x + v2.x) / 2, (v1.y + v2.y) / 2, (v1.z + v2.z) / 2);
                        }
                    }
                }

                /*wayNodes.forEach(function (n) {
                    n.set(n.x, -n.z, n.y);
                });
                fromNode.set(fromNode.x, -fromNode.z, fromNode.y);
                toNode.set(toNode.x, -toNode.z, toNode.y);*/


                let wayObject = new OSMWay('' + way.id, way.type, way.tags.maxspeed / 3.6, way.tags.lanes, way.tags.laneWidth, fromNode, toNode, wayNodes);
                way.wayObject = wayObject;
            } catch (e) {
                way.wayObject = undefined;
            }
        });
    }

    export function writeNetwork(ways: any, overpassData: any, boundary: IBoundary): any {
        const straightThresh: number = THREE.MathUtils.degToRad(default_straight_threshold);
        default_boundary.n = boundary.n;
        default_boundary.e = boundary.e;
        default_boundary.s = boundary.s;
        default_boundary.w = boundary.w;

        ways.forEach(function (way: any) {
            try {
                var wayData: any, wayNodes: THREE.Vector3[];
                wayData = JSON.parse(JSON.stringify(way));
                wayNodes = getNodes(overpassData, way).map(function (node: any) {
                    return nodeToVec3(node);
                });

                if (!way.tags.hasOwnProperty('maxspeed')) {
                    way.tags['maxspeed'] = 30;
                }
                if (!way.tags.hasOwnProperty('lanes')) {
                    way.tags['lanes'] = 1;
                }
                if (!way.tags.hasOwnProperty('laneWidth')) {
                    way.tags['laneWidth'] = 4;
                }

                const fromNode = new THREE.Vector3(wayNodes[0].x, wayNodes[0].y, wayNodes[0].z);
                if (way.tags.hasOwnProperty('highway') && (
                    way.tags.highway.startsWith('motorway') ||
                    way.tags.highway.startsWith('trunk') ||
                    way.tags.highway.startsWith('primary'))) {
                    const beg = way.nodes[0];
                    for (let i in overpassData.way) {
                        const w = overpassData.way[i];
                        if (w.tags === undefined) continue;
                        try {
                            if (w.tags.hasOwnProperty('highway') && (
                                w.tags.highway.startsWith('motorway') ||
                                w.tags.highway.startsWith('trunk') ||
                                w.tags.highway.startsWith('primary')) && w.nodes.length > 1 && w.nodes[w.nodes.length - 1] === beg) {
                                let v1 = nodeToVec3(overpassData.node[w.nodes[w.nodes.length - 2]]);
                                let v2 = nodeToVec3(overpassData.node[beg]);
                                fromNode.set((v1.x + v2.x) / 2, (v1.y + v2.y) / 2, (v1.z + v2.z) / 2);
                                // way.tags['lanes'] = w.tags['lanes'] !== undefined ? w.tags['lanes'] : way.tags['lanes'];
                            }
                        } catch (e) {
                            // TODO console.log(way.id);
                        }
                    }
                }

                const toNode = new THREE.Vector3(wayNodes[wayNodes.length - 1].x, wayNodes[wayNodes.length - 1].y, wayNodes[wayNodes.length - 1].z);
                if (way.tags.hasOwnProperty('highway') && (
                    way.tags.highway.startsWith('motorway') ||
                    way.tags.highway.startsWith('trunk') ||
                    way.tags.highway.startsWith('primary'))) {
                    const end = way.nodes[wayNodes.length - 1];
                    for (let i in overpassData.way) {
                        const w = overpassData.way[i];
                        if (w.tags === undefined) continue;
                        if (w.tags.hasOwnProperty('highway') && (
                            w.tags.highway.startsWith('motorway') ||
                            w.tags.highway.startsWith('trunk') ||
                            w.tags.highway.startsWith('primary')) && w.nodes.length > 0 && w.nodes[0] === end) {
                            let v1 = nodeToVec3(overpassData.node[way.nodes[wayNodes.length - 1]]);
                            let v2 = nodeToVec3(overpassData.node[way.nodes[wayNodes.length - 2]]);
                            toNode.set((v1.x + v2.x) / 2, (v1.y + v2.y) / 2, (v1.z + v2.z) / 2);
                        }
                    }
                }

                if (way.id === 47097487 || way.id === 47052980 || way.id === 177047469 || way.id === 177051741 || way.id === 47052979 || way.id === 177047476) {
                    way.tags['lanes'] = 4;
                }

                if (way.id === 74424206) {
                    way.tags['lanes'] = 3;
                } else if (way.id === 177047473) {
                    //way.tags['lanes'] = 2;
                    //way.tags['laneWidth'] = 4;
                } else if (way.id === 254915556) {
                    //way.tags['lanes'] = 1;
                } else if (way.id === 254915559) {
                    //way.tags['lanes'] = 4;
                    //wayNodes = wayNodes.slice(0, 6);
                    //toNode.copy(wayNodes[wayNodes.length - 1]);
                }


                //way.tags['lanes'] = 1;
                /*
                        bicycle:'no'
                        bridge:'yes'
                        foot:'no'
                        highway:'motorway_link'
                        layer:'1'
                        maxspeed:'40'
                        motorcar:'designated'
                        motorcycle:'designated'
                        oneway:'yes'
                        smoothness:'excellent'
                        source:'Bing 2012'
                        surface:'paved'
                        toll:'yes'
    
                        id: string,
                        type: string,
                        speed: number,
                        laneWidth: number,
                        from: THREE.Vector3,
                        to: THREE.Vector3,
                        positions: THREE.Vector3[],
                        numLanes: number,
                        ``
                */
                let wayObject = new OSMWay('' + way.id, way.type, way.tags.maxspeed / 3.6, way.tags.lanes, way.tags.laneWidth, fromNode, toNode, wayNodes);
                //let wayObject = new OSMWay('' + way.id, way.type, way.tags.maxspeed / 3.6, way.tags.lanes, way.tags.laneWidth, wayNodes[0].clone(), wayNodes[wayNodes.length - 1].clone(), wayNodes);
                writeNormalEdge(wayObject, straightThresh);
                way.wayObject = wayObject;
            } catch (e) {
                way.wayObject = undefined;
            }
        });
    }

    export function writeNormalEdge(way: any, straightThresh: number): any {

        let rp = way.points.map(function (p: any) {
            return p.clone();
        });
        if (rp.length == 2) {
            // foot paths may contain sharp angles
            // length = writeGeomLines(ls, planViewOSS, elevationOSS);
            computeGeomLines(way, rp);
        } else {
            // bool ok = writeGeomSmooth(ls, e->getSpeed(), planViewOSS, elevationOSS, straightThresh, length);
            writeGeomSmooth(rp, way.speed, straightThresh, length, way);
        }


        for (let lane of way.lanes) {
            let length = 0;
            let lp = lane.points.map(function (p: any) {
                return p.clone();
            });

            if (lp.length == 2) {
                // foot paths may contain sharp angles
                // length = writeGeomLines(ls, planViewOSS, elevationOSS);
                computeGeomLines(lane, lp);
            } else {
                // bool ok = writeGeomSmooth(ls, e->getSpeed(), planViewOSS, elevationOSS, straightThresh, length);
                writeGeomSmooth(lp, lane.speed, straightThresh, length, lane);
            }
        }
    }

    export function writeGeomSmooth(positions: THREE.Vector3[], speed: number, straightThresh: number, length: number, way: any): boolean {
        let ok = true;
        let longThresh = speed;
        let curveCutout = longThresh / 2;

        let positions2 = positions.map(function (p) {
            return p.clone();
        });
        /*if (!way.from.equals(positions2[0])) {
            positions2.splice(0, 0, way.from.clone());
        }
        if (!way.to.equals(positions2[positions2.length - 1])) {
            positions2.splice(positions2.length - 1, 1, way.to.clone());
        }*/

        let maxAngleDiff = 0;
        let offset = 0;
        for (let j = 1; j < positions.length - 1; j++) {
            const p0 = positions[j - 1].clone();
            const p1 = positions[j].clone();
            const p2 = positions[j + 1].clone();
            const dAngle = Math.abs(OSMGeoUtils.angleDiff(OSMGeoUtils.angleTo2D(p0, p1), OSMGeoUtils.angleTo2D(p1, p2)));
            // const dAngle = Math.abs(OSMGeoUtils.angleDiff(p0.angleTo(p1), p1.angleTo(p2)));
            const length1 = p0.distanceTo(p1);
            const length2 = p1.distanceTo(p2);
            maxAngleDiff = Math.max(maxAngleDiff, dAngle);

            if (dAngle > straightThresh
                && (length1 > longThresh || j == 1)
                && (length2 > longThresh || j == positions.length - 2)) {
                OSMGeoUtils.insertAtClosest(positions2, OSMGeoUtils.positionAtOffset(positions, offset + length1 - Math.min(length1 - POSITION_EPS, curveCutout)), false);
                OSMGeoUtils.insertAtClosest(positions2, OSMGeoUtils.positionAtOffset(positions, offset + length1 + Math.min(length2 - POSITION_EPS, curveCutout)), false);
                OSMGeoUtils.removeClosest(positions2, p1);
                OSMGeoUtils.removeDoublePoints(positions2, POSITION_EPS, true);
            }
            offset += length1;
        }
        const numPoints = positions2.length;
        if (maxAngleDiff < straightThresh) {
            // length = writeGeomLines(shape2, device, elevationDevice, 0);

            return ok;
        }

        // write the long segments as lines, short segments as curves
        offset = 0;
        for (let j = 0; j < numPoints - 1; j++) {
            const p0 = positions2[j];
            const p1 = positions2[j + 1];
            let line = [];
            line.push(p0.clone());
            line.push(p1.clone());
            const lineLength = OSMGeoUtils.length2D(line);
            if (lineLength >= longThresh) {
                // offset = writeGeomLines(line, device, elevationDevice, offset);
                computeGeomLines(way, line);
            } else {
                // find control points
                let begPositions = [];
                let endPositions = [];
                // if (!way.from.equals(positions2[0])) {
                /*if (j === 0) {
                    // keep the angle of the first/last segment but end at the front of the shape
                    if (!way.from.equals(line[0])) {
                        // begPositions = [way.from.clone(), line[1].clone()];
                        begPositions = [way.from.clone(), line[0].clone()];
                    } else {
                        begPositions = [line[0].clone(), line[1].clone()];
                    }
                    //OSMGeoUtils.add(begPositions, p0.clone().sub(begPositions[begPositions.length - 1]));
                    //OSMGeoUtils.add(begPositions, way.from.clone().sub(begPositions[begPositions.length - 1]));
                } else*/ if (j === 0 || j === numPoints - 2) {
                    // keep the angle of the first/last segment but end at the front of the shape
                    begPositions = [line[0].clone(), line[1].clone()];
                    OSMGeoUtils.add(begPositions, p0.clone().sub(begPositions[begPositions.length - 1]));
                } else if (j === 1 || p0.distanceTo(positions2[j - 1]) > longThresh) {
                    // use the previous segment if it is long or the first one
                    begPositions.push(positions2[j - 1].clone());
                    begPositions.push(p0.clone());
                } else {
                    // end at p0 with mean angle of the previous and current segment
                    begPositions.push(positions2[j - 1].clone());
                    begPositions.push(p1.clone());
                    OSMGeoUtils.add(begPositions, p0.clone().sub(begPositions[begPositions.length - 1]));
                }

                /*if (j === 0) {
                    // keep the angle of the first/last segment but start at the end of the shape
                    if (!way.from.equals(line[0])) {
                        //endPositions = [line[0].clone(), positions2[j + 2].clone()];
                        endPositions = [line[1].clone(), positions2[j + 2].clone()];
                    } else {
                        endPositions = [line[0].clone(), line[1].clone()];
                    }
                    //OSMGeoUtils.add(endPositions, p1.clone().sub(endPositions[0]));
                    //OSMGeoUtils.add(endPositions, p1.clone().sub(endPositions[0]));
                } else*/ if (j === 0 || j === numPoints - 2) {
                    // keep the angle of the first/last segment but start at the end of the shape
                    endPositions = [line[0].clone(), line[1].clone()];
                    OSMGeoUtils.add(endPositions, p1.clone().sub(endPositions[0]));
                } else if (j === numPoints - 3 || p1.distanceTo(positions2[j + 2]) > longThresh) {
                    // use the next segment if it is long or the final one
                    endPositions.push(p1.clone());
                    endPositions.push(positions2[j + 2].clone());
                } else {
                    // start at p1 with mean angle of the current and next segment
                    endPositions.push(p0.clone());
                    endPositions.push(positions2[j + 2].clone());
                    OSMGeoUtils.add(endPositions, p1.clone().sub(endPositions[0]));
                }
                const extrapolateLength = Math.min(25, lineLength / 4);
                let init = OSMGeoUtils.bezierControlPoints(begPositions, endPositions, false, extrapolateLength, extrapolateLength, ok, straightThresh);

                if (init.length === 0) {
                    // could not compute control points, write line
                    // offset = writeGeomLines(line, device, elevationDevice, offset);
                    computeGeomLines(way, line);
                } else {
                    // write bezier
                    const curveLength = OSMGeoUtils.length2D(OSMGeoUtils.bezier(init, 12));
                    //offset = writeGeomPP3(init, curveLength, offset);
                    /*if (j === 0 && !way.from.equals(line[0])) {
                        const ray = new THREE.Ray(begPositions[0].clone(), new THREE.Vector3().copy(begPositions[0]).sub(begPositions[1]).normalize()).recast(-50);
                        const ray2 = new THREE.Ray(endPositions[1].clone(), new THREE.Vector3().copy(endPositions[1]).sub(endPositions[0]).normalize()).recast(-50);
    
                        let v1 = OSMGeoUtils.intersects(begPositions[1].clone(), ray.origin.clone(), endPositions[0].clone(), ray2.origin.clone());
    
                        let init2 = [p0.clone(), v1.clone(), p1.clone()];
                        computeGeomPP3(way, init2, curveLength);
                        console.log();
                    } else {
                        computeGeomPP3(way, init, curveLength);
                        console.log();
                    }*/
                    //computeGeomLines(way, line);
                    computeGeomPP3(way, init, curveLength);
                }
            }
        }
        length = offset;
        return ok;
    }

    export function computeGeomLines(way: any, positions: THREE.Vector3[]): void {
        if (positions.length === 2) {
            const line3 = new THREE.Line3(positions[0], positions[1]);
            const length = Math.ceil(line3.distance());

            let position = new THREE.Vector3();
            for (let i = 0; i <= length; i++) {
                if (i % 2 === 0 || i === length) {
                    line3.at(i / length, position);
                    way.vertices.push(new THREE.Vector3(position.x, -position.z, position.y));
                }
            }
        }
    }

    export function computeGeomPP3(way: any, positions: THREE.Vector3[], curveLength: number): void {
        if (positions.length === 3) {
            let curve = new THREE.QuadraticBezierCurve3(positions[0], positions[1], positions[2]);

            let position = new THREE.Vector3();
            for (let i = 0; i <= curveLength; i++) {
                if (i % 2 === 0 || i === curveLength) {
                    curve.getPointAt(i / curveLength, position);
                    way.vertices.push(new THREE.Vector3(position.x, -position.z, position.y));
                }
            }
        } else if (positions.length === 4) {
            let curve = new THREE.CubicBezierCurve3(positions[0], positions[1], positions[2], positions[3]);

            let position = new THREE.Vector3();
            for (let i = 0; i <= curveLength; i++) {
                if (i % 2 === 0 || i === curveLength) {
                    curve.getPointAt(i / curveLength, position);
                    way.vertices.push(new THREE.Vector3(position.x, -position.z, position.y));
                }
            }
        }
    }

    export function writeGeomPP3(init: THREE.Vector3[], length: number, offset: number): number {
        // avoid division by 0
        length = Math.max(POSITION_EPS, length);

        const p = init[0].clone();
        const hdg = OSMGeoUtils.angleAt2D(init, 0);

        // backup elevation values
        const initZ = init.map(function (p) {
            return p.clone();
        });
        // translate to u,v coordinates
        OSMGeoUtils.add(init, new THREE.Vector3(-p.x, -p.y, -p.z));
        OSMGeoUtils.rotate2D(init, -hdg);

        // parametric coefficients
        let aU: number, bU: number, cU: number, dU: number;
        let aV: number, bV: number, cV: number, dV: number;
        let aZ: number, bZ: number, cZ: number, dZ: number;

        // unfactor the Bernstein polynomials of degree 2 (or 3) and collect the coefficients
        if (init.length == 3) {
            //f(x, a, b ,c) = a + (2*b - 2*a)*x + (a - 2*b + c)*x*x
            aU = init[0].x;
            bU = 2 * init[1].x - 2 * init[0].x;
            cU = init[0].x - 2 * init[1].x + init[2].x;
            dU = 0;

            aV = init[0].y;
            bV = 2 * init[1].y - 2 * init[0].y;
            cV = init[0].y - 2 * init[1].y + init[2].y;
            dV = 0;

            // elevation is not parameteric on [0:1] but on [0:length]
            aZ = initZ[0].z;
            bZ = (2 * initZ[1].z - 2 * initZ[0].z) / length;
            cZ = (initZ[0].z - 2 * initZ[1].z + initZ[2].z) / (length * length);
            dZ = 0;
        } else {
            // f(x, a, b, c, d) = a + (x*((3*b) - (3*a))) + ((x*x)*((3*a) + (3*c) - (6*b))) + ((x*x*x)*((3*b) - (3*c) - a + d))
            aU = init[0].x;
            bU = 3 * init[1].x - 3 * init[0].x;
            cU = 3 * init[0].x - 6 * init[1].x + 3 * init[2].x;
            dU = -init[0].x + 3 * init[1].x - 3 * init[2].x + init[3].x;

            aV = init[0].y;
            bV = 3 * init[1].y - 3 * init[0].y;
            cV = 3 * init[0].y - 6 * init[1].y + 3 * init[2].y;
            dV = -init[0].y + 3 * init[1].y - 3 * init[2].y + init[3].y;

            // elevation is not parameteric on [0:1] but on [0:length]
            aZ = initZ[0].z;
            bZ = (3 * initZ[1].z - 3 * initZ[0].z) / length;
            cZ = (3 * initZ[0].z - 6 * initZ[1].z + 3 * initZ[2].z) / (length * length);
            dZ = (-initZ[0].z + 3 * initZ[1].z - 3 * initZ[2].z + initZ[3].z) / (length * length * length);
        }

        let ssssss = `s="${offset}" x="${p.x}" y="${p.y}" hdg="${hdg}" length="${length}"`;
        // TODO console.log(ssssss);
        let tttttt = `aU="${aU}" bU="${bU}" cU="${cU}" dU="${dU}" aV="${aV}" bV="${bV}" cV="${cV}" dV="${dV}"`;
        // TODO console.log(tttttt);

        return offset + length;
    }

    export function getLeftLaneBorder(way: OSMWay, laneIndex: number = -1, widthOffset: number = 0): THREE.Vector3[] {
        let result: THREE.Vector3[] = new Array<THREE.Vector3>();

        const lefthand: boolean = true;
        if (laneIndex == -1) {
            laneIndex = lefthand ? 0 : way.numLanes - 1;
        }
        const leftmost: number = lefthand ? 0 : way.numLanes - 1;
        widthOffset -= (way.lanes[leftmost].width) / 2;

        if (lefthand) {
            for (let i = leftmost; i < laneIndex; i++) {
                widthOffset += way.lanes[i].width;
            }
        } else {
            for (let i = leftmost; i > laneIndex; i--) {
                widthOffset += way.lanes[i].width;
            }
        }
        result = way.lanes[leftmost].points;
        try {
            result = OSMGeoUtils.move2side(result, widthOffset);
        } catch (e) { }

        return result;
    }

    export function getRightLaneBorder(way: OSMWay, laneIndex: number = -1, widthOffset: number = 0): THREE.Vector3[] {
        let result: THREE.Vector3[] = new Array<THREE.Vector3>();

        const lefthand: boolean = true;
        if (laneIndex == -1) {
            laneIndex = lefthand ? 0 : way.numLanes - 1;
        }
        const leftmost: number = lefthand ? 0 : way.numLanes - 1;
        widthOffset += (way.lanes[leftmost].width) / 2;

        if (lefthand) {
            for (let i = leftmost; i < laneIndex; i++) {
                widthOffset += way.lanes[i].width;
            }
        } else {
            for (let i = leftmost; i > laneIndex; i--) {
                widthOffset += way.lanes[i].width;
            }
        }
        result = way.lanes[leftmost].points;
        try {
            result = OSMGeoUtils.move2side(result, widthOffset);
        } catch (e) { }

        return result;
    }



    // ###################################
    // ######                       ######
    // ######    Common Functions   ######
    // ######                       ######
    // ###################################
    export interface IBoundary {
        n: number;
        w: number;
        s: number;
        e: number;
    }

    export function createProjection(center: any): any {
        return d3.geoMercator().scale(6.5 * 1000 * 1000 * default_scale).center(center).translate([0, 0]);
    };
    export function midpoint(_arg: [number, number], _arg1: [number, number]): [number, number] {
        let x, x1, x2, y, y1, y2;
        x1 = _arg[0], y1 = _arg[1];
        x2 = _arg1[0], y2 = _arg1[1];
        x = x1 - (x1 - x2) / 2;
        y = y1 - (y1 - y2) / 2;
        return [x, y];
    };
    export function centroid(boundary: IBoundary): [number, number] {
        let p1: [number, number], p2: [number, number];
        p1 = [boundary.w, boundary.n];
        p2 = [boundary.e, boundary.s];
        return midpoint(p1, p2);
    };

    export function getNodes(overpassData: any, way: any): any {
        return way.nodes.map(function (id: number) {
            return overpassData.node[id];
        });
    };
    export function lonlatToArray(_arg: any): [number, number] {
        var lat, lon;
        lon = _arg.lon, lat = _arg.lat;
        return [lon, lat];
    };
    export function yxToVec3(_arg: any): THREE.Vector3 {
        var x, y;
        x = _arg[0], y = _arg[1];
        return new THREE.Vector3(x, y, 0);
    };
    export function nodeToXy(node: any): any {
        let center = centroid(default_boundary);
        let project = createProjection(center);
        return project(lonlatToArray(node));
    };
    export function nodeToVec3(node: any): THREE.Vector3 {
        return yxToVec3(nodeToXy(node));
    };

}