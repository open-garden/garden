import * as THREE from 'three';
import { OSMUtils } from '../../../garden-osm-utils/js/OSMUtils';
import { GORoad } from './GORoad';
import { GOLane } from './GOLane';


import { catarc_014 } from '../temp/tempdata'

if (THREE && THREE.Curve) {
    let _THREE_Curve_computeFrenetFrames = (THREE.Curve as any).prototype.computeFrenetFrames;
    (THREE.Curve as any).prototype.computeFrenetFrames = function (segments: any, closed: any): any {
        let frames = _THREE_Curve_computeFrenetFrames.call(this, segments, closed);
        let tangents = frames.tangents;
        let normals = frames.normals;
        let binormals = frames.binormals;
        var baseAxis = new THREE.Vector3(0, 1, 0);
        var t = new THREE.Vector3(), n = new THREE.Vector3(), b = new THREE.Vector3();
        for (var i = 0; i < tangents.length; i++) {
            t.copy(tangents[i]);
            n.copy(normals[i]);
            b.copy(binormals[i]);
            var axis = baseAxis.clone();
            var vec = new THREE.Vector3();
            vec.crossVectors(axis, t).normalize();
            normals[i].copy(vec);
            binormals[i].crossVectors(vec, t).multiply(new THREE.Vector3(1, -1, 1)).normalize();
        }
        return {
            tangents: tangents,
            normals: normals,
            binormals: binormals
        };
    }
}

let d3 = require('d3');

export const DEFAULT_SPEED = 40;
export const DEFAULT_LANE_WIDTH = 4;
export const default_straight_threshold: number = 0.00000001;
export const straightThresh = THREE.MathUtils.degToRad(default_straight_threshold);

export const GOLaneMeshColor = {
    //'driving': 0x707070,
    'driving': 0x303030,
    //'center': 0xb0f0b0,
    'center': 0x70fc70,
    //'border': 0x505050,
    'border': 0x303030,
    //'sidewalk': 0xc0c0c0,
    'sidewalk': 0x606060,
};

export namespace OSWayUtils {

    export function saveMapData(mapId: string, mapDescription: string, boundary: any, roadData: string, analyzerData: string) {

        return new Promise((resolve, reject) => {
            let params = new URLSearchParams();
            //params.set('boundary', JSON.stringify(boundary));
            params.set('gid', mapId);
            params.set('description', mapDescription);
            params.set('n', boundary.n);
            params.set('e', boundary.e);
            params.set('s', boundary.s);
            params.set('w', boundary.w);
            params.set('mapdata', roadData);
            params.set('analyzerdata', analyzerData);
            let reqUrl = `${window.location.protocol}//${window.location.hostname}:${window.location.port}/road_editor/map`


            fetch(reqUrl, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
                },
                body: params.toString()
            }).then(response => {
                const status = response.status;
                switch (status) {
                    case 201:
                        return resolve(response.text());
                    case 409:
                        if (confirm(`The map '${mapId}' already exists. Do you want to replace it?`)) {
                            fetch(reqUrl, {
                                method: 'PUT',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
                                },
                                body: params.toString()
                            }).then(response => {
                                const status = response.status;
                                switch (status) {
                                    case 201:
                                        return resolve(response.text());
                                    case 200:
                                        return resolve(response.text());
                                    default:
                                        throw new Error(`${status} ${response.statusText}`);
                                }
                            }).catch(err => {
                                return reject(err);
                            });
                        }
                        break;
                    default:
                        throw new Error(`${status} ${response.statusText}`);
                }
            }).catch(err => {
                return reject(err);
            });
        });
    }

    export function findMapData(mapId: string, boundary: any) {
        return new Promise(resolve => {
            fetch(`${window.location.protocol}//${window.location.hostname}:${window.location.port}/road_editor/map/${mapId}`, {
                method: 'GET'
            }).then(response => {
                return resolve(response.text());
            }).catch(err => {
                // TODO console.log(err);
            });
        });
    }

    export function generateWay(editor: any, boundary: any, overpassData: any): void {

        let ways: any = [];
        for (let key in overpassData.way) {
            if (!isArea(overpassData.way[key])) {
                //if (key !== '375244897') continue;
                /*if (key === '243748891'
                    || key === '400526491'
                    || key === '137648021'
                    || key === '320342019'
                    || key === '137648017'
                    || key === '400529651') {*/
                ways.push(overpassData.way[key]);
                //}
            }
        }

        OSMUtils.writeNetworkaaa(ways, overpassData, boundary);

        let referenceLineVisible = editor.config.setKey('referenceLine', false);
        ways.forEach((way: any, index: number) => {
            if (way.tags !== undefined &&
                way.tags.hasOwnProperty('highway') && (
                    way.tags.highway.startsWith('motorway') ||
                    way.tags.highway.startsWith('trunk') ||
                    way.tags.highway.startsWith('primary') ||
                    way.tags.highway.startsWith('secondary'))) {
                let wayObject = way.wayObject;
                let result = wayObject.points.map(function (p: any) {
                    return p.clone();
                });
                if (!wayObject.from.equals(result[0])) {
                    result.splice(0, 0, wayObject.from.clone());
                }
                if (!wayObject.to.equals(result[result.length - 1])) {
                    result.splice(result.length - 1, 1, wayObject.to.clone());
                }


                //let goWay = new GORoad(wayObject.id, wayObject.length, +way.tags.lanes, wayObject.speed, wayObject.laneWidth, result.slice(0, 5));
                let goWay = new GORoad(wayObject.id, wayObject.length, +way.tags.lanes, wayObject.speed, wayObject.laneWidth, result);
                if (goWay !== null) {
                    // goWay.rotation.x = 90 * Math.PI / 180;
                    // goWay.scale.z = -1;
                    // goWay.refLine.visible = referenceLineVisible;
                    goWay.lanes.forEach((lane) => lane.refLine.visible = referenceLineVisible);

                    editor.addObject(goWay);
                    editor.signals.objectAdded.dispatch(goWay);
                }
            }
        });
        editor.signals.sceneGraphChanged.dispatch();
    }

    export function writeLineSmooth(way: GORoad | GOLane): void {
        let points = way.points.map(function (p) {
            return p.clone();
        });
        if (points.length == 2) {
            // foot paths may contain sharp angles
            // length = writeGeomLines(ls, planViewOSS, elevationOSS);
            OSMUtils.computeGeomLines(way, points);
        } else {
            // bool ok = writeGeomSmooth(ls, e->getSpeed(), planViewOSS, elevationOSS, straightThresh, length);
            OSMUtils.writeGeomSmooth(points, way.maxSpeed, straightThresh, length, way);
        }
    }

    export function computeTrajectoryTimeLine01(name: string, rawData: any): any {

        if (name === undefined || rawData === undefined) return null;

        // 走行データの座標変換
        let trajectories: any = [];
        let colorHex = `#${new THREE.Color(rawData['color']).getHexString()}`;

        for (let key in rawData) {
            if (key === 'color') continue;
            let cur = rawData[key];
            trajectories.push({ "time": cur.time, "position": { x: cur.x, y: cur.y, z: cur.z }, "quaternion": { roll: cur.roll, yaw: cur.yaw + Math.PI / 2, pitch: cur.pitch } });
            // trajectories.push({ "time": cur.time, "position": { x: cur.x, y: cur.y, z: cur.z }, "quaternion": { roll: 0, yaw: cur.yaw + Math.PI / 2, pitch: 0 } });
        }

        let timeLineData = {
            "position": new THREE.Vector3(),
            "timeLine": new Array<any>(),
            "trackLayer": {
                "name": name,
                "values": new Array<any>(),
                "tmpValue": 0,
                "_color": colorHex
            },
            "trackInfo": [
                {
                    type: THREE.BooleanKeyframeTrack,
                    propertyPath: `${name}_Mesh01.visible`,
                    initialValue: [false],
                    interpolation: THREE.InterpolateSmooth
                },
                {
                    type: THREE.VectorKeyframeTrack,
                    propertyPath: `${name}_Mesh01.position`,
                    initialValue: [0, 0, 0],
                    interpolation: THREE.InterpolateSmooth
                }, {
                    type: THREE.QuaternionKeyframeTrack,
                    propertyPath: `${name}_Mesh01.quaternion`,
                    initialValue: [0, 0, 0, 1],
                    interpolation: THREE.InterpolateLinear
                }
            ]
        };
        for (let i = 0; i < trajectories.length; i++) {
            let trajectory = trajectories[i];
            timeLineData.timeLine.push({
                "time": trajectory.time,
                "visible": true,
                "position": { "x": trajectory.position.x, "y": trajectory.position.y + 1, "z": trajectory.position.z },
                //"quaternion": { "x": 0, "y": 0, "z": 0, "w": 0 }
                "quaternion": new THREE.Quaternion().setFromEuler(new THREE.Euler(trajectory.quaternion.roll, trajectory.quaternion.yaw, trajectory.quaternion.pitch))
            });
            if (i === 0 || i === trajectories.length - 1) {
                //if (i === 0) timeLineData.position.set(trajectory.position.x, trajectory.position.y, trajectory.position.z);
                timeLineData.trackLayer.values.push({
                    "time": trajectory.time,
                    "value": 0,
                    "_color": colorHex,
                    "tween": 'along'
                });
            }
        }

        return timeLineData;
    }

    export function computeTrajectoryTimeLine(data: any): any {

        if (data === undefined) data = catarc_014;

        const center = centroid(data.boundary);
        const project = createProjection(center);
        const frameRate = data.frameRate !== undefined ? data.frameRate : 20;

        function lonlatToArray(_arg: any): any {
            var lat, lon;
            lon = _arg.lon, lat = _arg.lat;
            return [lon, lat];
        };
        function yxToVec3(_arg: any): THREE.Vector3 {
            var x, y;
            x = _arg[0], y = _arg[1];
            return new THREE.Vector3(x, y, 0);
        };
        function nodeToXy(node: any): any {
            return project(lonlatToArray(node));
        };
        function nodeToVec3(node: any): THREE.Vector3 {
            return yxToVec3(nodeToXy(node));
        };

        // 走行データの座標変換
        let trajectories = { "ownCar": new Array<{ time: number, position: THREE.Vector3 }>() };
        for (let i = 0; i < data.frame.length; i++) {
            let ownCar = data.frame[i].ownCar;
            // 自車の座標変換
            var vec = nodeToVec3({ "lat": ownCar.latitude, "lon": ownCar.longitude });
            trajectories["ownCar"].push({ "time": i / frameRate, "position": vec });
        }

        let timeLineData = {
            "position": new THREE.Vector3(),
            "timeLine": new Array<any>(),
            "trackLayer": {
                "name": 'OwnCar',
                "values": new Array<any>(),
                "tmpValue": 0,
                "_color": '#0080ff'
            },
            "trackInfo": [
                {
                    type: THREE.VectorKeyframeTrack,
                    propertyPath: 'OwnCar_Mesh01.position',
                    initialValue: [0, 0, 0],
                    interpolation: THREE.InterpolateSmooth
                }, {
                    type: THREE.QuaternionKeyframeTrack,
                    propertyPath: 'OwnCar_Mesh01.quaternion',
                    initialValue: [0, 0, 0, 1],
                    interpolation: THREE.InterpolateLinear
                }
            ]
        };
        for (let i = 0; i < trajectories.ownCar.length; i++) {
            let trajectory = trajectories.ownCar[i];
            timeLineData.timeLine.push({
                "time": trajectory.time,
                "position": { "x": trajectory.position.x, "y": trajectory.position.z + 1.25, "z": trajectory.position.y },
                "quaternion": { "x": 0, "y": 0, "z": 0, "w": 0 }
            });
            if (i === 0 || i === trajectories.ownCar.length - 1) {
                if (i === 0) timeLineData.position.set(trajectory.position.x, trajectory.position.y, trajectory.position.z);
                timeLineData.trackLayer.values.push({
                    "time": trajectory.time,
                    "value": 0,
                    "_color": '#0080ff',
                    "tween": 'along'
                });
            }
        }

        return timeLineData;
    }

    function isArea(way: any): boolean {
        var first, last;
        first = way.nodes[0];
        last = way.nodes[way.nodes.length - 1];
        return first === last;
    }

    function centroid(boundary: any): any {
        let p1, p2;
        p1 = [boundary.w, boundary.n];
        p2 = [boundary.e, boundary.s];
        return midpoint(p1, p2);
    };

    function midpoint(_arg: any, _arg1: any): any {
        let x, x1, x2, y, y1, y2;
        x1 = _arg[0], y1 = _arg[1];
        x2 = _arg1[0], y2 = _arg1[1];
        x = x1 - (x1 - x2) / 2;
        y = y1 - (y1 - y2) / 2;
        return [x, y];
    };

    function createProjection(center: any): any {
        return d3.geoMercator().scale(6.5 * 1000 * 1000 * OSMUtils.default_scale).center(center).translate([0, 0]);
    };

}

export * from './GORoad';
export * from './GOLane';
export * from './GOArrowLine';