
//import * as THREE from 'three';
import * as THREE from 'three';

import { OSMUtils } from '../../../garden-osm-utils/js/OSMUtils';

import { catarc_014 } from '../temp/tempdata'


import { GORoad, GOLane, OSWayUtils } from '../objects/GOWay';



const wayPointVertexshaderSource = `
void main() {
    vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );
    gl_PointSize = 1.0 * (300.0 / - mvPosition.z);
    gl_Position = projectionMatrix * mvPosition;
}
`;

// ピクセルシェーダーのソース
const wayPointFragmentshaderSource = `
uniform vec3 color;
void main() {
    if ( length( gl_PointCoord - vec2( 0.5, 0.5 ) ) > 0.475 ) discard;
    gl_FragColor = vec4( color, 1.0 );
}
`;

// MapHelper
function MapHelper(editor) {

    THREE.Object3D.call(this);

    this.type = 'Group';

    this.visible = false;

    this.editor = editor;

    this.boundary = { n: 0, e: 0, s: 0, w: 0 };

    /* 港北インター */

    // this.boundary = { n: 35.5231, e: 139.5981, s: 35.515, w: 139.5883 };
    // this.boundary = { n: 35.52035, e: 139.59413, s: 35.52026, w: 139.59397 };
    // this.boundary = { n: 0, e: 0, s: 0, w: 0 };
    // this.boundary = { n: 35.5281, e: 139.6101, s: 35.5101, w: 139.5764 };
    // this.boundary = { n: 35.5191000, e: 139.5934900, s: 35.518860, w: 139.5933600 };
    // this.boundary = { n: 35.5193000, e: 139.5933900, s: 35.5187400, w: 139.5929400 };
    // this.boundary = { n: 35.5202900, e: 139.5936500, s: 35.5201500, w: 139.5935400 };
    // this.boundary = { n: 35.52033, e: 139.59412, s: 35.52026, w: 139.59396 };
    /* 渋谷 */
    // this.boundary = { n: 35.66076, e: 139.70264, s: 35.65850, w: 139.69843 };
    /* CATAC */
    // this.boundary = { n: 31.30006, e: 121.50067, s: 31.29058, w: 121.48382 };
    // this.boundary = { n: 31.29915, e: 121.50065, s: 31.29318, w: 121.48792 };
    /* 北九州空港 */
    // this.boundary = { n: 33.8586, e: 131.0562, s: 33.7849, w: 130.9214 };
    // this.boundary = { n: 33.8122000, e: 130.9879000, s: 33.8002000, w: 130.9629000 };
    // this.boundary = { n: 33.8128, e: 130.9877, s: 33.7944, w: 130.9540 };

    // 浅間町交番
    // this.boundary = { n: 35.46635, e: 139.61489, s: 35.46417, w: 139.61067 };

}

MapHelper.prototype = Object.assign(Object.create(THREE.Object3D.prototype), {

    constructor: MapHelper,

    isGroup: true,

    asyncLoadOverpassDataByBoundary: async function (boundary) {

        var mapGroup, signals, boundary, center, loading, project;

        mapGroup = this.editor.backgroundMap;
        signals = this.editor.signals;

        for (var i = this.children.length - 1; i >= 0; i--) {
            this.remove(this.children[i]);
        }

        this.boundary = {
            n: boundary.n,
            e: boundary.e,
            s: boundary.s,
            w: boundary.w
        };


        center = centroid(boundary);
        project = createProjection(center);
        loading = loadOverpassData(boundary);
        await loading.then((overpassData) => {
            var geoObject = createGeoObject(boundary, project, overpassData);
            mapGroup.add(geoObject);
            signals.sceneGraphChanged.dispatch();
        }).then((result) => {
        }).catch((result) => {
        });


    },

    loadOverpassDataByBoundary: function (boundary) {

        var mapGroup, signals, boundary, center, loading, project;

        mapGroup = this.editor.backgroundMap;
        signals = this.editor.signals;

        for (var i = this.children.length - 1; i >= 0; i--) {
            this.remove(this.children[i]);
        }

        this.boundary = {
            n: boundary.n,
            e: boundary.e,
            s: boundary.s,
            w: boundary.w
        };


        center = centroid(boundary);
        project = createProjection(center);
        loading = loadOverpassData(boundary);
        loading.then((overpassData) => {
            var geoObject = createGeoObject(boundary, project, overpassData);
            mapGroup.add(geoObject);
            signals.sceneGraphChanged.dispatch();
        }).then((result) => {
        }).catch((result) => {
        });

    },

    generateOverpassDataByBoundary: async function (boundary) {

        var boundary, center, loading, project;

        this.boundary = {
            n: boundary.n,
            e: boundary.e,
            s: boundary.s,
            w: boundary.w
        };


        center = centroid(boundary);
        project = createProjection(center);
        loading = loadOverpassData(boundary);
        await loading.then((overpassData) => {
            OSWayUtils.generateWay(this.editor, boundary, overpassData);
            this.editor.signals.sceneGraphChanged.dispatch();
        }).then((result) => {
        }).catch((result) => {
        });

    }

});


function lonlatToTile(lon, lat, zoom) {
    let lonDegreesPerTile, numOfTiles, sinLat, tx, ty;
    numOfTiles = Math.pow(2, zoom);
    lonDegreesPerTile = 360 / numOfTiles;
    sinLat = Math.sin(lat * Math.PI / 180);
    tx = (lon + 180) / lonDegreesPerTile;
    ty = (0.5 + -0.5 * Math.log((1 + sinLat) / (1 - sinLat)) / (2 * Math.PI)) * numOfTiles;
    return [Math.floor(tx), Math.floor(ty)];
};

function tileToLonlat(tx, ty, zoom) {
    let lat, latRadians, lon, numOfTiles, x, y;
    numOfTiles = Math.pow(2, zoom);
    x = tx / numOfTiles;
    y = ty / numOfTiles;
    lon = (x - (1 / 2)) / (1 / 360);
    latRadians = (y - (1 / 2)) / -(1 / (2 * Math.PI));
    lat = (2 * Math.atan(Math.exp(latRadians)) - Math.PI / 2) / Math.PI * 180;
    return [lon, lat];
};

function tileToBoundary(x, y, zoom) {
    let p1, p2;
    p1 = tileToLonlat(x, y, zoom);
    p2 = tileToLonlat(x + 1, y + 1, zoom);
    return {
        n: p1[1],
        w: p1[0],
        s: p2[1],
        e: p2[0]
    };
};

function midpoint(_arg, _arg1) {
    let x, x1, x2, y, y1, y2;
    x1 = _arg[0], y1 = _arg[1];
    x2 = _arg1[0], y2 = _arg1[1];
    x = x1 - (x1 - x2) / 2;
    y = y1 - (y1 - y2) / 2;
    return [x, y];
};

function centroid(boundary) {
    let p1, p2;
    p1 = [boundary.w, boundary.n];
    p2 = [boundary.e, boundary.s];
    return midpoint(p1, p2);
};

function createProjection(center) {
    return d3.geoMercator().scale(6.5 * 1000 * 1000 * OSMUtils.default_scale).center(center).translate([0, 0]);
};

function loadOverpassData(boundary) {
    return new Promise((resolve, reject) => {
        let baseUrl = "//overpass-api.de/api/interpreter?data=[out:json];\n(\n  node({s},{w},{n},{e});\n  way(bn);\n);\n(\n  ._;\n  node(w);\n);\nout;";
        let url = baseUrl.replace(/\{([swne])\}/g, (match, key) => {
            return boundary[key];
        });

        d3.json(url).then(function (root) {
            resolve(root);
        }).catch(function (error) {
            reject(error);
        });
    }).then((rawData) => {
        var acc;
        acc = {
            node: {},
            way: {},
            relation: {}
        };
        rawData.elements.forEach(function (elem) {
            return acc[elem.type][elem.id] = elem;
        });
        return acc;
    });
};

let materialOptions = {
    railway: {
        platform: {
            color: 0x555500,
            depth: 1
        },
        rail: {
            color: 0xffff00,
            linewidth: 1
        }
    },
    highway: {
        pedestrian: {
            color: 0x00cccc,
            depth: 1
        },
        primary: {
            color: 0xffaa555,
            linewidth: 1000
        },
        secondary: {
            color: 0xaa5500,
            linewidth: 1
        },
        residential: {
            color: 0xffffff,
            linewidth: 1
        },
        "default": {
            color: 0xf5021b,
            linewidth: 1
        }
    },
    waterway: {
        "default": {
            color: 0x0000ff,
            linewidth: 10
        }
    },
    amenity: {
        school: {
            color: 0x00aa00,
            depth: 10
        },
        theatre: {
            color: 0xcc5500,
            depth: 10
        },
        parking: {
            color: 0xffffaa,
            depth: 1
        },
        bus_station: {
            color: 0xcc0000,
            depth: 1
        },
        "default": {
            color: 0xffffff,
            depth: 10
        }
    },
    building: {
        commercial: {
            color: 0x615c5c,
            depth: 40
        },
        house: {
            color: 0x615c5c,
            depth: 5
        },
        yes: {
            color: 0x615c5c,
            depth: 40
        },
        "default": {
            color: 0x615c5c,
            depth: 40
        }
    },
    natural: {
        wood: {
            color: 0x00ff00,
            depth: 5
        },
        water: {
            color: 0x0000cc,
            depth: 1
        },
        "default": {
            color: 0x00ff00,
            depth: 2
        }
    },
    leisure: {
        pitch: {
            color: 0xcc5500,
            depth: 1
        },
        golf_course: {
            color: 0x00cc55,
            depth: 1
        },
        "default": {
            color: 0x00cc55,
            depth: 1
        }
    },
    landuse: {
        forest: {
            color: 0x00ff00,
            depth: 5
        },
        old_forest: {
            color: 0x005500,
            depth: 10
        },
        "default": {
            color: 0x005500,
            depth: 1
        }
    }
};

function createGeoObject(boundary, project, overpassData) {

    function getNodes(overpassData, way) {
        return way.nodes.map(function (id) {
            return overpassData.node[id];
        });
    };

    function isArea(way) {
        var first, last;
        first = way.nodes[0];
        last = way.nodes[way.nodes.length - 1];
        return first === last;
    };
    function lonlatToArray(_arg) {
        var lat, lon;
        lon = _arg.lon, lat = _arg.lat;
        return [lon, lat];
    };
    function yxToVec3(_arg) {
        var x, y;
        x = _arg[0], y = _arg[1];
        return new THREE.Vector3(x, y, 0);
    };
    function nodeToXy(node) {
        return project(lonlatToArray(node));
    };
    function nodeToVec3(node) {
        return yxToVec3(nodeToXy(node));
    };



    // #######################################################################################################
    function createWay(way, opts) {

        if (way.wayObject !== undefined &&
            way.tags.hasOwnProperty('highway') && (
                way.tags.highway.startsWith('motorway') ||
                way.tags.highway.startsWith('trunk') ||
                way.tags.highway.startsWith('primary'))) {

            let wayObject = way.wayObject;
            return new GORoad(wayObject.id, wayObject.speed, wayObject.laneWidth, wayObject.vertices, wayObject.lanes);

        }

        return null;
    };

    function createTrajectory(trajectory, id) {
        var create;
        create = (function (_this) {
            return function (trajectory) {
                var geometry;
                geometry = new THREE.Geometry();
                geometry.vertices = trajectory.map(function (v) {
                    return new THREE.Vector3(v.x, v.y, v.z);
                });
                return geometry;
            };
        })(this);

        return new THREE.Points(
            create(trajectory),
            new THREE.ShaderMaterial({
                uniforms: {
                    color: {
                        value: new THREE.Color(id === 'ownCar' ? 0x000000 : 0xbc42f5)
                    },
                },
                vertexShader: wayPointVertexshaderSource,
                fragmentShader: wayPointFragmentshaderSource
            }));
        // return new THREE.Line(create(trajectory), new THREE.LineBasicMaterial({ color: id === 'ownCar' ? 0x000000 : 0xbc42f5 }));
    };
    // #######################################################################################################

    function createLine(way, opts) {
        var create, line;
        create = (function (_this) {
            return function (way) {
                var geometry, nodes;
                nodes = getNodes(overpassData, way);
                geometry = new THREE.Geometry();
                geometry.vertices = nodes.map(function (node) {
                    var vec3 = nodeToVec3(node);
                    vec3.z = 0;
                    return vec3;
                });
                return geometry;
            };
        })(this);

        /*if (opts === null) {
            opts = { "depthTest": false, "transparent": true };
        } else {
            opts.depthTest = false;
            opts.transparent = true;
        }*/
        return line = new THREE.Line(create(way), new THREE.LineBasicMaterial(opts));
    };
    function createPolygon(area, opts) {

        if (opts == null) {
            opts = {
                color: 0xffffff,
                opacity: 0.8,
                transparent: true
            };
        }

        function createShape(nodes) {
            var shape;
            shape = new THREE.Shape();
            shape.moveTo.apply(shape, nodeToXy(nodes[0]));
            nodes.slice(1).forEach((function (_this) {
                return function (node) {
                    return shape.lineTo.apply(shape, nodeToXy(node));
                };
            })(this));
            return shape;
        };

        var create = (function (_this) {
            return function (area, opts) {
                var geometry, nodes, shape;
                nodes = getNodes(overpassData, area);
                shape = createShape(nodes);
                if (!('depth' in opts)) opts.depth = 1;
                //console.log(opts.depth);
                if (!('bevelEnabled' in opts)) opts.bevelEnabled = false;
                opts.bevelEnabled = false;
                //opts.UVGenerator = new BoundingUVGenerator(shape, opts);
                //opts.extrudeMaterial = 0;
                //opts.material = 1;
                geometry = new THREE.ExtrudeGeometry(shape, opts);
                geometry.computeFaceNormals();
                geometry.uvsNeedUpdate = true;
                return geometry;
            };
        })(this);

        //if (!('side' in opts)) opts.side = THREE.BackSide;

        //return new THREE.Mesh(create(area, opts), new THREE.MeshPhongMaterial(opts));

        opts.side = THREE.DoubleSide;
        if (opt2 !== null) {
            var opt1 = JSON.parse(JSON.stringify(opts));
            var opt2 = JSON.parse(JSON.stringify(opts));
            if (opt2.hasOwnProperty('depth')) delete opt2['depth'];
            if (opt2.hasOwnProperty('linewidth')) delete opt2['linewidth'];
            if (opt2.hasOwnProperty('bevelEnabled')) delete opt2['bevelEnabled'];
        }
        return new THREE.Mesh(create(area, opt1), new THREE.MeshPhongMaterial(opt2));
    };

    function findMaterialOptions(tags) {
        let category, key, mkeys, tkeys, tvalue, _ref;
        if (tags == null) {
            tags = {};
        }
        mkeys = new Set(Object.keys(materialOptions));
        tkeys = new Set(Object.keys(tags));
        let is =
            [...mkeys].filter(x => tkeys.has(x));

        key = is ? is[0] : null;

        if (key) {
            category = materialOptions[key];
            tvalue = tags[key];
            if (key === 'highway' && (tvalue === 'footway' || tvalue === 'steps')) {
                return null;
            }
            if (category[tvalue]) {
                _ref = Object.assign({}, category[tvalue]);
            } else {
                _ref = Object.assign({}, category["default"]);
            }

            if (_ref && _ref.depth) {
                if ('height' in tags) {
                    _ref.depth = parseFloat(tags.height);
                    //      console.log('height',tvalue,_ref,tags.name,parseFloat(tags.height),tags.height,tags );
                } else
                    if ('building:levels' in tags) {
                        _ref.depth = parseFloat(tags['building:levels']) * 5;
                        //          console.log(key,tvalue,tags.name,parseFloat(tags['building:levels']),'階',tags);
                    } else {
                        _ref.depth = _ref.depth * Math.random() * 0.75 + _ref.depth * 0.25;
                        //        console.log(key,tvalue,tags.name,_ref.depth,tags);          
                    }
            }
            return _ref;
        } else {
            return null;
        }
    };

    function createAndAddTrajectories(root) {
        let trajectories = { "ownCar": [] };

        /*let v01 = new THREE.Vector3(114.57863526418805, -217.5352480239235, 0);
        let m01 = new THREE.Matrix4().makeRotationZ(194.12 * THREE.MathUtils.DEG2RAD);
        let e01 = new THREE.Euler(0, 0, 0 * THREE.MathUtils.DEG2RAD, 'XYZ');


        m01.setPosition(v01.x, v01.y, v01.z);
        let v02 = new THREE.Vector3(-16.304190508505, 0, 0).applyEuler(e01).add(v01);

        console.log(v02);*/

        //return;

        let transferMatrix = new THREE.Matrix4().setPosition(0, 0, 0);


        for (let frame of catarc_014.frame) {
            let ownCar = frame.ownCar;
            // 自車の座標変換
            var vec = nodeToVec3({ "lat": ownCar.latitude, "lon": ownCar.longitude });

            // 自車の座標と向き（軸）で、他車の座標変換Matrix作成
            let matrix = new THREE.Matrix4().makeRotationZ(-ownCar.direction * THREE.MathUtils.DEG2RAD);
            matrix.setPosition(vec.x, vec.y, vec.z);

            // 他車の座標変換
            for (let obj of frame.object) {
                let v = new THREE.Vector3(obj.x_3D, -obj.z_3D, 0).applyMatrix4(matrix);

                if (obj.trackId !== "1318") {
                    // continue;
                }

                if (trajectories.hasOwnProperty(obj.trackId)) {
                    trajectories[obj.trackId].push(v.applyMatrix4(transferMatrix));
                } else {
                    trajectories[obj.trackId] = [v.applyMatrix4(transferMatrix)];
                }
            }

            trajectories["ownCar"].push(vec.applyMatrix4(transferMatrix));
        }

        Object.keys(trajectories).forEach(function (key) {
            // console.log(trajectories[key].length);
            root.add(createTrajectory(trajectories[key], key));
        });
    }

    function createAndAddLines(root) {
        let ways = [];


        for (let i in overpassData.way) {
            if (!isArea(overpassData.way[i])) ways.push(overpassData.way[i]);
        }

        // overpassData.way.filter((way)=>{
        //   return !isArea(way);
        // });
        // #TODO
        //OSMUtils.writeNetwork(ways, overpassData, boundary);

        var iii = 0;
        ways.forEach(function (way) {
            /*if (way.tags.highway === 'primary' || way.tags.highway === 'primary_link' || way.tags.highway === 'trunk') return;
            if (way.tags.highway === 'trunk_link') {
                iii++;
                if (iii !== 24) return;
                else console.log();
            }*/
            // #TODO
            /*if (way.tags !== undefined &&
                way.tags.hasOwnProperty('highway') && (
                    way.tags.highway.startsWith('motorway') ||
                    way.tags.highway.startsWith('trunk') ||
                    way.tags.highway.startsWith('primary'))) {

                var opts;
                opts = findMaterialOptions(way.tags);
                root.add(createLine(way, opts));

                let goWay = createWay(way, opts);
                if (goWay !== null) {
                    root.add(goWay);
                }
                if (0 === iii++) {
                    //root.add(createTrajectory(way, opts));
                }
            } else {
                var opts;
                opts = findMaterialOptions(way.tags);
                root.add(createLine(way, opts));
            }*/
            var opts;
            opts = findMaterialOptions(way.tags);
            root.add(createLine(way, opts));

        });
    };

    function createAndAddPolygons(root) {
        var areas = [];
        for (let i in overpassData.way) {
            if (isArea(overpassData.way[i])) areas.push(overpassData.way[i]);
        }
        //areas = overpassData.way.filter(isArea);
        areas.forEach(function (area) {
            var opts;
            opts = findMaterialOptions(area.tags);
            if (opts === null) return;
            root.add(createPolygon(area, opts));
        });
    };

    let root = new THREE.Object3D();
    root.position.y = -0.5;
    root.rotation.x = 90 * Math.PI / 180;
    root.scale.z = -1;
    createAndAddLines(root);
    createAndAddPolygons(root);
    //createAndAddTrajectories(root)
    return root;
};

export { MapHelper };