
import * as THREE from "three";
import { Vector3 } from "three";

export const POSITION_EPS: number = 0.11;
export const INVALID_OFFSET: number = -1;
export const INVALID_DOUBLE = Number.MAX_VALUE;
export const INVALID_POSITION: THREE.Vector3 = new THREE.Vector3().addScalar(- 1024 * 1024 * 1024);

export const AVOID_WIDE_LEFT_TURN: number = 1;
export const AVOID_WIDE_RIGHT_TURN: number = 2;
export const FOUR_CONTROL_POINTS: number = 4;
export const AVOID_INTERSECTING_LEFT_TURNS: number = 8;

export const SUMO_const_laneOffset = 0;
export const SUMO_const_laneWidth = 3.2;
export const UNSPECIFIED_WIDTH = -1;

export class OSMWay {
    id: string;
    type: string;
    speed: number;
    length: number = -1;
    numLanes: number;
    laneWidth: number;
    from: THREE.Vector3;
    to: THREE.Vector3;
    points: THREE.Vector3[] = [];
    lanes: OSMLane[] = [];
    vertices: THREE.Vector3[] = [];


    constructor(
        id: string,
        type: string,
        speed: number,
        numLanes: number,
        laneWidth: number,
        from: THREE.Vector3,
        to: THREE.Vector3,
        points: THREE.Vector3[]
    ) {
        this.id = id;
        this.type = type;
        this.speed = speed;
        this.numLanes = numLanes;
        this.laneWidth = laneWidth;
        this.from = from;
        this.to = to;
        this.points = points;

        // this.init(numLanes > 0 ? numLanes : 0);
    }

    init(numLanes: number): void {
        this.length = this.from.distanceTo(this.to);
        this.points = this.computeLaneShape(0, 0);

        for (let i = 0; i < numLanes; i++) {
            this.lanes.push(new OSMLane('' + i, this.speed, this.laneWidth));
        }
        this.computeLaneShapes();
    }

    computeLaneShapes(): void {
        if (this.from.equals(this.to)) {
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
                this.lanes[i].points = this.computeLaneShape(i, offsets[i]);
            } catch (e) {
                this.lanes[i].points = this.points.map(function (p) {
                    return p.clone();
                });
                if (!this.from.equals(this.lanes[i].points[0])) {
                    this.lanes[i].points.splice(0, 0, this.from.clone());
                }
                if (!this.to.equals(this.lanes[i].points[this.lanes[i].points.length - 1])) {
                    this.lanes[i].points.splice(this.lanes[i].points.length - 1, 1, this.to.clone());
                }
            }
        }
    }

    computeLaneShape(lane: number, offset: number): THREE.Vector3[] {
        let result = this.points.map(function (p) {
            return p.clone();
        });
        if (!this.from.equals(result[0])) {
            result.splice(0, 0, this.from.clone());
        }
        if (!this.to.equals(result[result.length - 1])) {
            result.splice(result.length - 1, 1, this.to.clone());
        }
        try {
            result = OSMGeoUtils.move2side(result, offset);
        } catch (e) {
        }
        return result;
    }
}



export class OSMLane {
    id: string;
    speed: number;
    width: number;
    points: THREE.Vector3[];
    vertices: THREE.Vector3[] = [];

    constructor(
        id: string,
        speed: number,
        width: number,
        positions?: THREE.Vector3[],
    ) {
        this.id = id;
        this.speed = speed;
        this.width = width;
        this.points = positions != undefined ? positions : [];
    }
}





export class OSMGeoUtils {

    static move2side(positions: THREE.Vector3[], amount: number, maxExtension: number = 100): THREE.Vector3[] {
        if (positions.length < 2) {
            positions;
        }
        OSMGeoUtils.removeDoublePoints(positions, POSITION_EPS, true);
        if (OSMGeoUtils.length2D(positions) === 0) {
            positions;
        }
        let newPositions = [];
        for (let i = 0; i < positions.length; i++) {
            if (i === 0) {
                const from = positions[i].clone();
                const to = positions[i + 1].clone();
                if (!from.equals(to)) {
                    newPositions.push(from.sub(OSMGeoUtils.sideOffset(from, to, amount)));
                }
            } else if (i === positions.length - 1) {
                const from = positions[i - 1].clone();
                const to = positions[i].clone();
                if (!from.equals(to)) {
                    newPositions.push(to.sub(OSMGeoUtils.sideOffset(from, to, amount)));
                }
            } else {
                const from = positions[i - 1].clone();
                const me = positions[i].clone();
                const to = positions[i + 1].clone();
                const fromMe = [from.clone(), me.clone()];
                OSMGeoUtils.extrapolate2D(fromMe, me.distanceTo(to));
                const extrapolateDev = fromMe[1].distanceTo(to);
                if (Math.abs(extrapolateDev) < POSITION_EPS) {
                    newPositions.push(me.sub(OSMGeoUtils.sideOffset(from, to, amount)));
                } else if (Math.abs(extrapolateDev - 2 * me.distanceTo(to)) < POSITION_EPS) {
                    const fromMe = [from.clone(), me.clone()];
                    OSMGeoUtils.extrapolate2D(fromMe, amount);
                    newPositions.push(fromMe[1]);
                } else {
                    let offsets = OSMGeoUtils.sideOffset(from, me, amount);
                    let offsets2 = OSMGeoUtils.sideOffset(me, to, amount);
                    const l1 = [new THREE.Vector3().copy(from).sub(offsets), new THREE.Vector3().copy(me).sub(offsets)];
                    const l2 = [new THREE.Vector3().copy(me).sub(offsets2), new THREE.Vector3().copy(to).sub(offsets2)];
                    let meNew = OSMGeoUtils.intersectsPosition(l1, l2[0], l2[1], maxExtension);
                    if (meNew === INVALID_POSITION) {
                        throw ("no line intersection");
                    }
                    meNew = meNew.add(new THREE.Vector3(0, 0, me.z));
                    newPositions.push(meNew);
                }
                // copy original z value
                newPositions[newPositions.length - 1].set(newPositions[newPositions.length - 1].x, newPositions[newPositions.length - 1].y, me.z);
            }
        }
        return newPositions;
    }

    static removeDoublePoints(positions: THREE.Vector3[], minDist: number, assertLength: boolean): void {
        if (positions.length > 1) {
            let result = [];
            let p1 = new THREE.Vector3(positions[0].x, positions[0].y, positions[0].z), p2 = new THREE.Vector3();
            for (let i = 1; i < positions.length - 1; i++) {
                p2.copy(positions[i]);
                if (p1.distanceTo(p2) < minDist) {
                    if (i + 1 === positions.length - 1) {
                        result[result.length - 1].copy(p2);
                    } else {
                        continue;
                    }
                } else {
                    result.push(p1.clone());
                    p1.copy(p2);
                }
            }
        }
    }

    static length2D(positions: THREE.Vector3[]): number {
        if (positions.length === 0) {
            return 0;
        }
        let len = 0;
        for (let i = 0; i < positions.length - 1; i++) {
            len += positions[i].distanceTo(positions[i + 1]);
        }
        return len;
    }

    static sideOffset(beg: THREE.Vector3, end: THREE.Vector3, amount: number): THREE.Vector3 {
        const scale = amount / beg.distanceTo(end);
        return new THREE.Vector3((beg.y - end.y) * scale, (end.x - beg.x) * scale, 0);
    }

    static extrapolate2D(positions: THREE.Vector3[], val: number, onlyFirst: boolean = false) {
        if (positions.length > 0) {
            const p1 = positions[0];
            const p2 = positions[1];
            if (p1.distanceTo(p2) > 0) {
                const offset = new THREE.Vector3().copy(p2).sub(p1).multiplyScalar(val / p1.distanceTo(p2));
                p1.sub(offset);
                if (!onlyFirst) {
                    if (positions.length === 2) {
                        p2.add(offset);
                    } else {
                        // TODO
                    }
                }
            }
        }
    }

    static intersectsPosition(l1: THREE.Vector3[], p1: THREE.Vector3, p2: THREE.Vector3, withinDist: number = 0) {
        for (let i = 0; i < l1.length - 1; i++) {
            let v = OSMGeoUtils.intersects(l1[i], l1[i + 1], p1, p2, withinDist);
            if (!v.equals(INVALID_POSITION)) {
                return v;
            }
        }
        return INVALID_POSITION;
    }

    static intersects(p11: THREE.Vector3, p12: THREE.Vector3, p21: THREE.Vector3, p22: THREE.Vector3, withinDist: number = 0): THREE.Vector3 {
        let result = new THREE.Vector3();

        const eps = Number.EPSILON;
        const denominator = (p22.y - p21.y) * (p12.x - p11.x) - (p22.x - p21.x) * (p12.y - p11.y);
        const numera = (p22.x - p21.x) * (p11.y - p21.y) - (p22.y - p21.y) * (p11.x - p21.x);
        const numerb = (p12.x - p11.x) * (p11.y - p21.y) - (p12.y - p11.y) * (p11.x - p21.x);
        if (Math.abs(numera) < eps && Math.abs(numerb) < eps && Math.abs(denominator) < eps) {
            let a1, a2, a3, a4;
            let a = -1e12;
            if (p11.x !== p12.x) {
                a1 = p11.x < p12.x ? p11.x : p12.x;
                a2 = p11.x < p12.x ? p12.x : p11.x;
                a3 = p21.x < p22.x ? p21.x : p22.x;
                a4 = p21.x < p22.x ? p22.x : p21.x;
            } else {
                a1 = p11.y < p12.y ? p11.y : p12.y;
                a2 = p11.y < p12.y ? p12.y : p11.y;
                a3 = p21.y < p22.y ? p21.y : p22.y;
                a4 = p21.y < p22.y ? p22.y : p21.y;
            }
            if (a1 <= a3 && a3 <= a2) {
                if (a4 < a2) {
                    a = (a3 + a4) / 2;
                } else {
                    a = (a2 + a3) / 2;
                }
            }
            if (a3 <= a1 && a1 <= a4) {
                if (a2 < a4) {
                    a = (a1 + a2) / 2;
                } else {
                    a = (a1 + a4) / 2;
                }
            }
            if (a !== -1e12) {
                if (result !== null) {
                    if (p11.x != p12.x) {
                        let mu = (a - p11.x) / (p12.x - p11.x);
                        result.x = a;
                        result.y = p11.y + mu * (p12.y - p11.y);
                    } else {
                        result.x = p11.x;
                        result.y = a;
                        if (p12.y == p11.y) {
                            let mu = 0;
                        } else {
                            let mu = (a - p11.y) / (p12.y - p11.y);
                        }
                    }
                }
                return result;
            }
            return INVALID_POSITION;
        }
        /* Are the lines parallel */
        if (Math.abs(denominator) < eps) {
            return INVALID_POSITION;
        }
        /* Is the intersection along the segments */
        let mua = numera / denominator;
        /* reduce rounding errors for lines ending in the same point */
        if (Math.abs(p12.x - p22.x) < eps && Math.abs(p12.y - p22.y) < eps) {
            mua = 1.;
        } else {
            const offseta = withinDist / p11.distanceTo(p12);
            const offsetb = withinDist / p21.distanceTo(p22);
            const mub = numerb / denominator;
            if (mua < -offseta || mua > 1 + offseta || mub < -offsetb || mub > 1 + offsetb) {
                return INVALID_POSITION;
            }
        }
        if (result !== null) {
            result.x = p11.x + mua * (p12.x - p11.x);
            result.y = p11.y + mua * (p12.y - p11.y);
            let mu = mua;
        }
        return result;
    }

    static angleDiff(angle1: number, angle2: number): number {
        let dtheta = angle2 - angle1;
        while (dtheta > Math.PI) {
            dtheta -= 2.0 * Math.PI;
        }
        while (dtheta < -Math.PI) {
            dtheta += 2.0 * Math.PI;
        }
        return dtheta;
    }

    static insertAtClosest(positions: THREE.Vector3[], p: THREE.Vector3, interpolateZ: boolean) {
        if (positions.length == 0) {
            return;
        }
        let minDist = Number.MAX_VALUE;
        let insertionIndex = 1;
        for (let i = 0; i < positions.length - 1; i++) {
            const length = OSMGeoUtils.nearest_offset_on_line_to_point2D(positions[i], positions[i + 1], p, false);
            const outIntersection = OSMGeoUtils.positionAtOffset2D(positions[i], positions[i + 1], length);
            const dist = p.distanceTo(outIntersection);
            if (dist < minDist) {
                insertionIndex = i + 1;
                minDist = dist;
            }
        }
        // check if we have to adjust Position Z
        if (interpolateZ) {
            // obtain previous and next Z
            const previousZ = positions[insertionIndex - 1].z;
            const nextZ = positions[insertionIndex].z;
            // insert new position using x and y of p, and the new z
            positions.splice(insertionIndex, 0, new THREE.Vector3(p.x, p.y, (previousZ + nextZ) / 2.0));
        } else {
            positions.splice(insertionIndex, 0, p);
        }
        return insertionIndex;
    }

    static nearest_offset_on_line_to_point2D(lineStart: THREE.Vector3, lineEnd: THREE.Vector3, p: THREE.Vector3, perpendicular: boolean): number {
        const lineLength2D = lineStart.distanceTo(lineEnd);
        if (lineLength2D === 0) {
            return 0;
        } else {
            const u = (((p.x - lineStart.x) * (lineEnd.x - lineStart.x)) + ((p.y - lineStart.y) * (lineEnd.y - lineStart.y))) / (lineLength2D * lineLength2D);
            if (u < 0 || u > 1) {
                if (perpendicular) {
                    return INVALID_OFFSET;
                }
                if (u < 0) {
                    return 0;
                }
                return lineLength2D;
            }
            return u * lineLength2D;
        }
    }

    static positionAtOffset(positions: THREE.Vector3[], pos: number, lateralOffset: number = 0): THREE.Vector3 {
        if (positions.length === 0) {
            return INVALID_POSITION.clone();
        }
        if (positions.length === 1) {
            return positions[0].clone();
        }
        let seenLength = 0;
        for (let i = 0; i < positions.length - 1; i++) {
            const nextLength = positions[i].distanceTo(positions[i + 1]);
            if (seenLength + nextLength > pos) {
                return OSMGeoUtils.positionAtOffset2D(positions[i], positions[i + 1], pos - seenLength, lateralOffset);
            }
            seenLength += nextLength;
        }
        return positions[positions.length - 1].clone();
    }

    static positionAtOffset2D(p1: THREE.Vector3, p2: THREE.Vector3, pos: number, lateralOffset: number = 0): THREE.Vector3 {
        let result = new THREE.Vector3(p1.x, p1.y, p1.z);

        const dist = p1.distanceTo(p2);
        if (pos < 0 || dist < pos) {
            return INVALID_POSITION;
        }
        if (lateralOffset !== 0) {
            const offset = OSMGeoUtils.sideOffset(p1, p2, -lateralOffset);
            if (pos == 0) {
                return result.add(offset);
            }
            return result.add(p2.clone().sub(p1).multiplyScalar(pos / dist)).add(offset);
        }
        if (pos === 0) {
            return result;
        }
        return result.add(p2.clone().sub(p1).multiplyScalar(pos / dist));
    }

    static removeClosest(positions: THREE.Vector3[], p: THREE.Vector3): number {
        if (positions.length === 0) {
            return -1;
        }
        let minDist = Number.MAX_VALUE;
        let removalIndex = 0;
        for (let i = 0; i < positions.length; i++) {
            const dist = p.distanceTo(positions[i]);
            if (dist < minDist) {
                removalIndex = i;
                minDist = dist;
            }
        }
        positions.splice(removalIndex, 1);
        return removalIndex;
    }

    static add(positions: THREE.Vector3[], offset: THREE.Vector3): void {
        for (let i = 0; i < positions.length; i++) {
            positions[i].add(offset);
        }
    }

    static bezierControlPoints(
        begPositions: THREE.Vector3[],
        endPositions: THREE.Vector3[],
        isTurnaround: boolean,
        extrapolateBeg: number,
        extrapolateEnd: number,
        ok: boolean,
        straightThresh: number = THREE.MathUtils.degToRad(5),
        shapeFlag: number = 0
    ): THREE.Vector3[] {
        const beg = begPositions[begPositions.length - 1];
        const end = endPositions[0];
        const dist = beg.distanceTo(end);
        let init = new Array<THREE.Vector3>();
        if (dist < POSITION_EPS || beg.distanceTo(begPositions[begPositions.length - 2]) < POSITION_EPS || end.distanceTo(endPositions[1]) < POSITION_EPS) {
            // typically, this node a is a simpleContinuation. see also #2539
            return init;
        } else {
            init.push(beg.clone());
            if (isTurnaround) {
                // turnarounds:
                //  - end of incoming lane
                //  - position between incoming/outgoing end/begin shifted by the distance orthogonally
                //  - begin of outgoing lane
                let center = OSMGeoUtils.positionAtOffset2D(beg, end, beg.distanceTo(end) / 2);
                center.sub(new THREE.Vector3(beg.y - end.y, end.x - beg.x, 0));
                init.push(center.clone());
            } else {
                const angle = OSMGeoUtils.angleDiff(OSMGeoUtils.angleAt2D(begPositions, begPositions.length - 2), OSMGeoUtils.angleAt2D(endPositions, 0));
                let endShapeBegLine = [endPositions[0].clone(), endPositions[1].clone()];
                let begShapeEndLineRev = [begPositions[begPositions.length - 1].clone(), begPositions[begPositions.length - 2].clone()];
                OSMGeoUtils.extrapolate2D(endShapeBegLine, 100, true);
                OSMGeoUtils.extrapolate2D(begShapeEndLineRev, 100, true);
                if (Math.abs(angle) < Math.PI / 4) {
                    // very low angle: could be an s-shape or a straight line
                    const displacementAngle = OSMGeoUtils.angleDiff(OSMGeoUtils.angleAt2D(begPositions, begPositions.length - 2), OSMGeoUtils.angleTo2D(beg, end));
                    const bendDeg = THREE.MathUtils.radToDeg(Math.abs(displacementAngle - angle));
                    const halfDistance = dist / 2;
                    if (Math.abs(displacementAngle) <= straightThresh && Math.abs(angle) <= straightThresh) {
                        return new Array<THREE.Vector3>();
                    } else if (bendDeg > 22.5 && Math.pow(bendDeg / 45, 2) / dist > 0.13) {
                        // do not allow s-curves with extreme bends
                        // (a linear dependency is to restrictive at low displacementAngles and too permisive at high angles)
                        ok = false;
                        return new Array<THREE.Vector3>();
                    } else {
                        const endLength = begPositions[begPositions.length - 2].distanceTo(begPositions[begPositions.length - 1]);
                        const off1 = endLength + Math.min(extrapolateBeg, halfDistance);
                        init.push(OSMGeoUtils.positionAtOffset2D(begShapeEndLineRev[1], begShapeEndLineRev[0], off1));
                        const off2 = 100 - Math.min(extrapolateEnd, halfDistance);
                        init.push(OSMGeoUtils.positionAtOffset2D(endShapeBegLine[0], endShapeBegLine[1], off2));
                    }
                } else {
                    // turning
                    //  - end of incoming lane
                    //  - intersection of the extrapolated lanes
                    //  - begin of outgoing lane
                    // attention: if there is no intersection, use a straight line
                    let intersect = OSMGeoUtils.intersectsPosition(endShapeBegLine, begShapeEndLineRev[0], begShapeEndLineRev[1]);
                    if (intersect.equals(INVALID_POSITION)) {
                        ok = false;
                        return new Array<THREE.Vector3>();
                    }
                    const minControlLength = Math.min(1.0, dist / 2);
                    const distBeg = intersect.distanceTo(beg);
                    const distEnd = intersect.distanceTo(end);
                    const lengthenBeg = distBeg <= minControlLength;
                    const lengthenEnd = distEnd <= minControlLength;
                    if (lengthenBeg && lengthenEnd) {
                        ok = false;
                        return new Array<THREE.Vector3>();
                    } else if ((shapeFlag & FOUR_CONTROL_POINTS)) {
                        init.push(OSMGeoUtils.positionAtOffset(begShapeEndLineRev, 100 - extrapolateBeg));
                        init.push(OSMGeoUtils.positionAtOffset(endShapeBegLine, 100 - extrapolateEnd));
                    } else if (lengthenBeg || lengthenEnd) {
                        init.push(OSMGeoUtils.positionAtOffset(begShapeEndLineRev, 100 - minControlLength));
                        init.push(OSMGeoUtils.positionAtOffset(endShapeBegLine, 100 - minControlLength));
                    } else if ((shapeFlag & AVOID_WIDE_LEFT_TURN) != 0
                        // there are two reasons for enabling special geometry rules:
                        // 1) sharp edge angles which could cause overshoot
                        // 2) junction geometries with a large displacement between opposite left turns
                        //    which would cause the default geometry to overlap
                        && ((shapeFlag & AVOID_INTERSECTING_LEFT_TURNS) != 0
                            || (angle > THREE.MathUtils.degToRad(95) && (distBeg > 20 || distEnd > 20)))) {
                        const factor = ((shapeFlag & AVOID_INTERSECTING_LEFT_TURNS) == 0 ? 1 : Math.min(0.6, 16 / dist));
                        init.push(OSMGeoUtils.positionAtOffset(begShapeEndLineRev, 100 - Math.min(distBeg * factor / 1.2, dist * factor / 1.8)));
                        init.push(OSMGeoUtils.positionAtOffset(endShapeBegLine, 100 - Math.min(distEnd * factor / 1.2, dist * factor / 1.8)));
                    } else if ((shapeFlag & AVOID_WIDE_RIGHT_TURN) != 0 && angle < THREE.MathUtils.degToRad(-95) && (distBeg > 20 || distEnd > 20)) {
                        init.push(OSMGeoUtils.positionAtOffset(begShapeEndLineRev, 100 - Math.min(distBeg / 1.4, dist / 2)));
                        init.push(OSMGeoUtils.positionAtOffset(endShapeBegLine, 100 - Math.min(distEnd / 1.4, dist / 2)));
                    } else {
                        let z: number;
                        const z1 = OSMGeoUtils.positionAtOffset(begShapeEndLineRev, OSMGeoUtils.nearest_offset_to_point2D(begShapeEndLineRev, intersect)).z;
                        const z2 = OSMGeoUtils.positionAtOffset(endShapeBegLine, OSMGeoUtils.nearest_offset_to_point2D(endShapeBegLine, intersect)).z;
                        const z3 = 0.5 * (beg.z + end.z);
                        // if z1 and z2 are on the same side in regard to z3 then we
                        // can use their avarage. Otherwise, the intersection in 3D
                        // is not good and we are better of using z3
                        if ((z1 <= z3 && z2 <= z3) || (z1 >= z3 && z2 >= z3)) {
                            z = 0.5 * (z1 + z2);
                        } else {
                            z = z3;
                        }
                        intersect.set(intersect.x, intersect.y, z);
                        init.push(intersect.clone());
                    }
                }
            }
            init.push(end.clone());
        }
        return init;
    }

    static angleAt2D(positions: THREE.Vector3[], pos: number): number {
        if (pos + 1 < positions.length) {
            return OSMGeoUtils.angleTo2D(positions[pos], positions[pos + 1]);
        } else {
            return INVALID_DOUBLE;
        }
    }

    static rotate2D(positions: THREE.Vector3[], angle: number): void {
        const s = Math.sin(angle);
        const c = Math.cos(angle);
        for (let i = 0; i < positions.length; i++) {
            const x = positions[i].x;
            const y = positions[i].y;
            const z = positions[i].z;
            const xnew = x * c - y * s;
            const ynew = x * s + y * c;
            positions[i].set(xnew, ynew, z);
        }
    }

    static nearest_offset_to_point2D(positions: THREE.Vector3[], p: THREE.Vector3, perpendicular: boolean = true): number {
        if (positions.length === 0) {
            return INVALID_DOUBLE;
        }
        let minDist = Number.MAX_VALUE;
        let nearestPos = INVALID_OFFSET;
        let seen = 0;
        for (let i = 0; i < positions.length - 1; i++) {
            const pos = OSMGeoUtils.nearest_offset_on_line_to_point2D(positions[i], positions[i + 1], p, perpendicular);
            const dist = pos === INVALID_OFFSET ? minDist : p.distanceTo(OSMGeoUtils.positionAtOffset2D(positions[i], positions[i + 1], pos));
            if (dist < minDist) {
                nearestPos = pos + seen;
                minDist = dist;
            }
            if (perpendicular /*&& i != begin()*/ && pos === INVALID_OFFSET) {
                // TODO
            }
            seen += positions[i].distanceTo(positions[i + 1]);
        }
        return nearestPos;
    }

    static bezier(positions: THREE.Vector3[], numPoints: number): THREE.Vector3[] {

        const fac = [
            1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0, 3628800.0, 39916800.0, 479001600.0,
            6227020800.0, 87178291200.0, 1307674368000.0, 20922789888000.0, 355687428096000.0, 6402373705728000.0,
            121645100408832000.0, 2432902008176640000.0, 51090942171709440000.0, 1124000727777607680000.0,
            25852016738884976640000.0, 620448401733239439360000.0, 15511210043330985984000000.0,
            403291461126605635584000000.0, 10888869450418352160768000000.0, 304888344611713860501504000000.0,
            8841761993739701954543616000000.0, 265252859812191058636308480000000.0,
            8222838654177922817725562880000000.0, 263130836933693530167218012160000000.0
        ];
        let ret: THREE.Vector3[] = [];
        const npts = positions.length;
        // calculate the points on the Bezier curve
        const step = 1.0 / (numPoints - 1);
        let t = 0.;
        let prev = new THREE.Vector3();
        for (let i1 = 0; i1 < numPoints; i1++) {
            if ((1.0 - t) < 5e-6) {
                t = 1.0;
            }
            let x = 0., y = 0., z = 0.;
            for (let i = 0; i < npts; i++) {
                const ti = (i == 0) ? 1.0 : Math.pow(t, i);
                const tni = (npts == i + 1) ? 1.0 : Math.pow(1 - t, npts - i - 1);
                const basis = fac[npts - 1] / (fac[i] * fac[npts - 1 - i]) * ti * tni;
                x += basis * positions[i].x;
                y += basis * positions[i].y;
                z += basis * positions[i].z;
            }
            t += step;
            let current = new THREE.Vector3(x, y, z);
            if (!prev.equals(current) && !isNaN(x) && !isNaN(y) && !isNaN(z)) {
                ret.push(current.clone());
            }
            prev.copy(current);
        }
        return ret;
    }

    static angleTo2D(p1: THREE.Vector3, p2: THREE.Vector3): number {
        return Math.atan2(p2.y - p1.y, p2.x - p1.x)
    }
}