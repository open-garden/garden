// CSS
import './css/main.css';

// Common JS
import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/monokai.css';
import 'codemirror/lib/codemirror.js';
import 'codemirror/mode/javascript/javascript.js';

import '../vendors/js/libs/esprima.js';
import '../vendors/js/libs/jsonlint.js';
import '../vendors/js/libs/glslprep.min.js';

import 'codemirror/addon/dialog/dialog.css';
import 'codemirror/addon/hint/show-hint.css';
import 'codemirror/addon/tern/tern.css';

import 'codemirror/addon/dialog/dialog.js';
import 'codemirror/addon/hint/show-hint.js';
import 'codemirror/addon/tern/tern.js';
import 'acorn';
import 'acorn-loose';
import 'acorn-walk';
import 'ternjs';
import 'tern-threejs';
import '../vendors/examples/js/vr/HelioWebXRPolyfill.js';

// Module JS
import * as THREE from 'three'
import { Editor } from '../@zipc/editor/js/Editor.js';
import { Player } from '../@zipc/editor/js/Player.js';
import { LoadingScreen } from '../@zipc/editor/js/LoadingScreen.js';

import * as GARDEN from '../@zipc/garden-objects/js/roads/GardenObjects.js';
import { MapHelper } from '../@zipc/garden-objects/js/helpers/MapHelper.js';

import { OSWayUtils } from '../@zipc/garden-objects/js/objects/GOWay';


//
window.URL = window.URL || window.webkitURL;
window.BlobBuilder = window.BlobBuilder || window.WebKitBlobBuilder || window.MozBlobBuilder;

Number.prototype.format = function () {

    return this.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");

};

//

var editor = new Editor();

var mapHelper = new MapHelper(editor);
editor.backgroundMap = mapHelper;
editor.scene.add(mapHelper);

window.editor = editor; // Expose editor to Console
window.THREE = THREE; // Expose THREE to APP Scripts and Console

var loadingScreen = new LoadingScreen(editor);
document.body.appendChild(loadingScreen.dom);

var player = new Player(editor);
document.body.appendChild(player.dom);

//

editor.storage.init(function () {

    /*editor.storage.get(function (state) {

        if (isLoadingFromHash) return;

        if (state !== undefined) {

            editor.fromJSON(state);

        }

        var selected = editor.config.getKey('selected');

        if (selected !== undefined) {

            editor.selectByUuid(selected);

        }

    });*/

    //

    var timeout;

    function saveState() {

        if (editor.config.getKey('autosave') === false) {

            return;

        }

        clearTimeout(timeout);

        timeout = setTimeout(function () {

            editor.signals.savingStarted.dispatch();

            timeout = setTimeout(function () {

                editor.storage.set(editor.toJSON());

                editor.signals.savingFinished.dispatch();

            }, 100);

        }, 1000);

    }

    var signals = editor.signals;

    signals.geometryChanged.add(saveState);
    signals.objectAdded.add(saveState);
    signals.objectChanged.add(saveState);
    signals.objectRemoved.add(saveState);
    signals.materialChanged.add(saveState);
    signals.scenarioDirectionChanged.add(saveState);
    signals.sceneBackgroundChanged.add(saveState);
    signals.sceneGraphChanged.add(saveState);
    signals.scriptChanged.add(saveState);
    signals.historyChanged.add(saveState);

});

//

document.addEventListener('dragover', function (event) {

    event.preventDefault();
    event.dataTransfer.dropEffect = 'copy';

}, false);

document.addEventListener('drop', function (event) {

    event.preventDefault();

    editor.loader.loadFiles(event.dataTransfer.files);

}, false);

function onWindowResize() {

    editor.signals.windowResize.dispatch();

}

window.addEventListener('resize', onWindowResize, false);

onWindowResize();

// 

function createTrajectory(name, rawData) {
    let wayPoints = [];
    for (let key in rawData) {
        if (key === 'color') continue;
        const cur = rawData[key];
        cur.time /= 1000;
        wayPoints.push({ x: cur.x, y: cur.y + 1, z: cur.z });
    }

    if (name.toLowerCase() === 'ego') {
        rawData['color'] = 0x0080ff;
    } else {
        rawData['color'] = Math.random() * 0xffffff | 0;
    }
    // let wayPoints = OSWayUtils.computeTrajectoryTimeLine().timeLine.map(t => { return { "x": t.position.x, "y": t.position.y, "z": t.position.z } });
    var metalData = {
        "id": name,
        "routes": [
            {
                "accel": 0.0,
                "points": wayPoints
            }
        ],
        "mode": {
            "accel": 0.0,
            "points": []
        },
        "average": {
            "accel": 0.0,
            "points": []
        },
        "edge": {
            "accel": 0.0,
            "points": []
        },
        "basePointsArray": []
    }

    let trajectoryObject = new GARDEN.TrajectoryObject(rawData.color).setFromMetalData(metalData);
    if (metalData.routes !== undefined) {
        for (var entity of metalData.routes) {
            var basePoints = [];
            for (var point of entity.points) {
                basePoints.push({ x: point.x, y: point.y, z: point.z });
            }
            metalData.basePointsArray.push(basePoints);
        }
    }
    trajectoryObject.setFromMetalData(metalData);

    trajectoryObject.userData.rawData = JSON.parse(JSON.stringify(rawData));
    // trajectoryObject.userData.timeLine = OSWayUtils.computeTrajectoryTimeLine01(name, rawData);

    return trajectoryObject;
}

var searchContent = window.location.search;
// run preview
const args = {
    "mapGid": "Kouhoku.Inter.Example",
    "drivingDataName": "01Kouhoku-Inter.scenario.csv",
    "startTime": "2021-02-16T19:03:29.017000Z",
    "endTime": "2021-02-16T19:03:29.062000Z"
};

// let u = new URLSearchParams(args).toString();
// http://localhost:8080/?preview=true&mapGid=Kouhoku.Inter.Example&drivingDataName=01Kouhoku-Inter.scenario.csv&startTime=2021-02-16T19:03:29.017000Z&endTime=2021-02-16T19:03:29.062000Z

if (searchContent.substr(1, 12) === 'preview=true') {
    var parameters = searchContent.substr(14);
    var rawData = JSON.parse('{"' + decodeURI(parameters.replace(/&/g, "\",\"").replace(/=/g, "\":\"")) + '"}')
    if (confirm(`Driving data "01Kouhoku-Inter.scenario.csv" will be play. Are you sure?`)) {
        editor.signals.loadingScreenShow.dispatch();
        fetch(`${window.location.protocol}//${window.location.hostname}:${window.location.port}/road_editor/data/driving/influx/${rawData.drivingDataName}/${rawData.startTime}/${rawData.endTime}`, {
            method: 'GET'
        }).then(response => {
            return response.json();
        }).then(async (jsonData) => {
            try {
                fetch(`${window.location.protocol}//${window.location.hostname}:${window.location.port}/road_editor/map/${rawData.mapGid}`, {
                    method: 'GET'
                }).then(response => {
                    return response.text();
                }).then(async (data) => {
                    try {
                        const mapData = JSON.parse(data);
                        editor.loader.loadFromJson(mapData);
                        editor.scene._id = jsonData.waypointId;
                        for (let key in jsonData) {
                            if (key === 'waypointId') continue;
                            let newTrajectory = createTrajectory(key, jsonData[key]);
                            editor.addObject(newTrajectory);
                        }

                        // timeline
                        var trackInfo = [];
                        var routeLayers = [];
                        for (var child of editor.scene.children) {
                            if (child.type == 'Car') {
                                Array.prototype.push.apply(trackInfo, child.metalData.routeData.trackInfo);
                                routeLayers.push(child.metalData.routeData.trackLayer);
                            } else if (child.type == 'Trajectory') {
                                let childObject = editor.scene.getObjectByName(child.name);


                                let timeLineData = OSWayUtils.computeTrajectoryTimeLine01(child.name, childObject.userData.rawData);
                                Array.prototype.push.apply(trackInfo, timeLineData.trackInfo);
                                routeLayers.push(timeLineData.trackLayer);
                                childObject.userData = { "timeLine": timeLineData.timeLine };

                                let carMesh = new THREE.Mesh(new THREE.BoxBufferGeometry(5, 2, 2).rotateY(Math.PI / 2), new THREE.MeshPhongMaterial({ color: `#${child.meshColor.getHexString()}`, side: THREE.DoubleSide }));
                                carMesh.castShadow = true;
                                carMesh.receiveShadow = true;
                                carMesh.name = child.name + '_Mesh01';
                                carMesh.position.copy(timeLineData.timeLine.length > 0 ? timeLineData.timeLine[0].position : new THREE.Vector3());
                                childObject.actorObject = carMesh;
                                childObject.add(carMesh);


                                // child.userData
                            }
                        }
                        editor.timelineData = {
                            trackInfo: trackInfo,
                            trackData: {
                                version: '1.4.0',
                                modified: new Date,
                                name: 'CSC Viewer',
                                title: 'Sample Title',
                                layers: routeLayers
                            }
                        };

                        editor.select(editor.scene);

                        var boundary = editor.backgroundMap.boundary;
                        if (mapData.boundary !== undefined && mapData.boundary !== null) {
                            if (boundary.n !== mapData.boundary.n) boundary.n = mapData.boundary.n;
                            if (boundary.e !== mapData.boundary.e) boundary.e = mapData.boundary.e;
                            if (boundary.s !== mapData.boundary.s) boundary.s = mapData.boundary.s;
                            if (boundary.w !== mapData.boundary.w) boundary.w = mapData.boundary.w;
                            editor.backgroundMap.visible = true;
                            editor.signals.objectChanged.dispatch(editor.scene);
                            editor.signals.rendererUpdated.dispatch();
                            await editor.backgroundMap.asyncLoadOverpassDataByBoundary(mapData.boundary);
                            editor.signals.startPlayer.dispatch();
                        }
                    } catch (e) {
                        console.error('Error:', e);
                        editor.signals.loadingScreenHide.dispatch();
                    }
                }).catch(err => {
                    console.error('Error:', error);
                }).finally(() => {
                    editor.signals.loadingScreenHide.dispatch();
                });
            } catch (e) {
                console.error('Error:', e);
            }
        }).catch(err => {
            console.error('Error:', err);
            editor.signals.loadingScreenHide.dispatch();
        });
    }

}

