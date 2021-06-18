import express, { Request, Response } from "express";
import mongoose from 'mongoose';
import MapData from '../models/MapData';
import Waypoint from "../models/Waypoint";

/**
 * Router Definition
 */
export const mapRouter = express.Router()

/**
 * REST API
 * Controller Definitions
 */

// GET map/
mapRouter.get('/', async function (req: Request, res: Response) {
    try {
        // TODO
        res.sendStatus(405);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// GET map/:gid
mapRouter.get('/:gid', async function (req: Request, res: Response) {
    const gid: string = req.params.gid;
    try {
        MapData.findOne({ "gid": gid, }, function (err, result) {
            if (err) {
                res.status(500).send(err.message);
            } else if (!result) {
                res.sendStatus(404);
            } else {
                res.status(200).send(result.data);
            }
        });
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// GET map/boundary/:n/:s/:w/:e
mapRouter.get('/boundary/:lon/:lat/:width/:height', async function (req: Request, res: Response) {
    const lon: number = +req.params.lon;
    const lat: number = +req.params.lat;
    const width: number = +req.params.width;
    const height: number = +req.params.height;
    try {
        let boundary = { n: lat, e: lon, s: lat, w: lon };
        let offsetX = width / 10 * (0.0000449 / Math.cos(lat * Math.PI / 180));
        let offsetY = height / 10 * 0.0000449;

        boundary.n = Number((boundary.n + offsetY).toFixed(7));
        boundary.s = Number((boundary.s - offsetY).toFixed(7));
        boundary.w = Number((boundary.w - offsetX).toFixed(7));
        boundary.e = Number((boundary.e + offsetX).toFixed(7));

        let conditions = width === 0 && height === 0 ? {} : { $nor: [{ w: { $gt: boundary.e } }, { e: { $lt: boundary.w } }, { s: { $gt: boundary.n } }, { n: { $lt: boundary.s } }] };

        MapData.find(
            conditions,
            { data: 0 },
            function (err, result) {
                if (err) {
                    res.status(500).send(err.message);
                } else {
                    let mapDatas = [];
                    for (let data of result) {
                        /*mapDatas.push({
                            'title': data.id,
                            'description': `Min Lat=${data.n} Min Lon=${data.s} Max Lat=${data.e} Max Lon=${data.w}`
                        });*/
                        // ###########################################################################
                        const minLon = Math.min(boundary.w, data.w);
                        const minLat = Math.min(boundary.s, data.s);

                        const mapWidth = (data.e - data.w) / (0.0000449 / Math.cos((data.n + data.s) / 2 * Math.PI / 180)) * 5;
                        const mapHeight = (data.n - data.s) / 0.0000449 * 5;
                        // TODO console.log(`${mapWidth}, ${mapHeight}`);

                        const left = (boundary.w - data.w) / (0.0000449 / Math.cos((data.n + data.s) / 2 * Math.PI / 180)) * 5;
                        const top = (data.n - boundary.n) / 0.0000449 * 5;
                        // TODO console.log(`${left}, ${top}`);
                        // ###########################################################################
                        mapDatas.push({
                            'gid': data.gid,
                            'title': data.gid,
                            'description': `Min Lat=${data.n} Min Lon=${data.s} Max Lat=${data.e} Max Lon=${data.w}`,
                            'rectangle1': {
                                'width': Math.round(mapWidth / 10),
                                'height': Math.round(mapHeight / 10)
                            },
                            'rectangle2': {
                                'width': Math.round(width / 10),
                                'height': Math.round(height / 10),
                                'top': Math.round(top / 10),
                                'left': Math.round(left / 10)
                            },
                        });
                    }
                    res.status(200).send(JSON.stringify(mapDatas));
                }
            } as any).limit(50);;
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// GET map/boundary/:n/:s/:w/:e/:keywords
mapRouter.get('/boundary/:lon/:lat/:width/:height/:keywords', async function (req: Request, res: Response) {
    const lon: number = +req.params.lon;
    const lat: number = +req.params.lat;
    const width: number = +req.params.width;
    const height: number = +req.params.height;
    const keywords: string = req.params.keywords;
    try {
        let boundary = { n: lat, e: lon, s: lat, w: lon };
        let offsetX = width / 10 * (0.0000449 / Math.cos(lat * Math.PI / 180));
        let offsetY = height / 10 * 0.0000449;

        boundary.n = Number((boundary.n + offsetY).toFixed(7));
        boundary.s = Number((boundary.s - offsetY).toFixed(7));
        boundary.w = Number((boundary.w - offsetX).toFixed(7));
        boundary.e = Number((boundary.e + offsetX).toFixed(7));


        let con1 = keywords.length === 0 ? undefined : { gid: { $in: keywords.split(',').map(k => new RegExp(k, 'i')) } };
        let con2 = width === 0 && height === 0 ? undefined : { $nor: [{ w: { $gt: boundary.e } }, { e: { $lt: boundary.w } }, { s: { $gt: boundary.n } }, { n: { $lt: boundary.s } }] };

        let conditions: any = {};
        if (!con1 || !con2) {
            conditions = !con1 ? (!con2 ? {} : con2) : con1;
        } else {
            conditions = { $and: [con1, con2] }
        }


        MapData.find(
            conditions,
            { data: 0 },
            function (err, result) {
                if (err) {
                    res.status(500).send(err.message);
                } else {
                    let mapDatas = [];
                    for (let data of result) {
                        /*mapDatas.push({
                            'title': data.id,
                            'description': `Min Lat=${data.n} Min Lon=${data.s} Max Lat=${data.e} Max Lon=${data.w}`
                        });*/
                        // ###########################################################################
                        const minLon = Math.min(boundary.w, data.w);
                        const minLat = Math.min(boundary.s, data.s);

                        const mapWidth = (data.e - data.w) / (0.0000449 / Math.cos((data.n + data.s) / 2 * Math.PI / 180)) * 5;
                        const mapHeight = (data.n - data.s) / 0.0000449 * 5;
                        // TODO console.log(`${mapWidth}, ${mapHeight}`);

                        const left = (boundary.w - data.w) / (0.0000449 / Math.cos((data.n + data.s) / 2 * Math.PI / 180)) * 5;
                        const top = (data.n - boundary.n) / 0.0000449 * 5;
                        // TODO console.log(`${left}, ${top}`);
                        // ###########################################################################
                        mapDatas.push({
                            'gid': data.gid,
                            'title': data.gid,
                            'description': `Min Lat=${data.n} Min Lon=${data.s} Max Lat=${data.e} Max Lon=${data.w}`,
                            'rectangle1': {
                                'width': Math.round(mapWidth / 10),
                                'height': Math.round(mapHeight / 10)
                            },
                            'rectangle2': {
                                'width': Math.round(width / 10),
                                'height': Math.round(height / 10),
                                'top': Math.round(top / 10),
                                'left': Math.round(left / 10)
                            },
                        });
                    }
                    res.status(200).send(JSON.stringify(mapDatas));
                }
            } as any).limit(50);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// POST map/
mapRouter.post("/", async (req: Request, res: Response) => {
    try {
        MapData.findOne({ "gid": req.body.gid, }, function (err, result) {
            if (err) {
                res.status(500).send(err.message);
            } else if (result !== null) {
                res.sendStatus(409);
            } else {
                let mapData = JSON.parse(req.body.mapdata);
                let newMapData = new MapData({
                    _id: new mongoose.Types.ObjectId(),
                    gid: req.body.gid,
                    description: req.body.description,
                    n: req.body.n,
                    e: req.body.e,
                    s: req.body.s,
                    w: req.body.w,
                    data: mapData.map
                    //data: JSON.stringify(mapData.map)
                });
                newMapData.save(async function (err) {
                    if (err) {
                        res.status(500).send(err.message);
                    } else {
                        let analyzerData = JSON.parse(req.body.analyzerdata);
                        let newWaypoint = new Waypoint({
                            gid: analyzerData.gid,
                            direction: analyzerData.direction,
                            roads: analyzerData.roads
                        });
                        let wayPoint = await Waypoint.findOne({ $and: [{ gid: newWaypoint.gid }] });
                        if (!wayPoint) {
                            newWaypoint._id = new mongoose.Types.ObjectId();
                        } else {
                            wayPoint.direction = newWaypoint.direction;
                            wayPoint.roads = newWaypoint.roads;
                            newWaypoint = wayPoint;
                        }
                        newWaypoint.save(function (err) {
                            if (err) {
                                res.status(500).send(err.message);
                            } else {
                                res.sendStatus(201);
                            }
                        });
                    }
                });
            }
        });
    } catch (err) {
        res.status(500).send(err.message);
    }
});

// PUT map/
mapRouter.put("/", async (req: Request, res: Response) => {
    try {
        MapData.findOne({ "gid": req.body.gid, }, function (err, result) {
            if (err) {
                res.status(500).send(err.message);
            } else if (result === null) {
                res.sendStatus(404);
            } else {
                let mapData = JSON.parse(req.body.mapdata);
                result.description = req.body.description;
                result.n = req.body.n;
                result.e = req.body.e;
                result.s = req.body.s;
                result.w = req.body.w;
                result.data = mapData.map;
                // result.data = JSON.stringify(mapData.map);

                result.save(async function (err) {
                    if (err) {
                        res.status(500).send(err.message);
                    } else {
                        let analyzerData = JSON.parse(req.body.analyzerdata);
                        let newWaypoint = new Waypoint({
                            _id: new mongoose.Types.ObjectId(),
                            gid: analyzerData.gid,
                            direction: analyzerData.direction,
                            roads: analyzerData.roads
                        });
                        await Waypoint.deleteMany({ gid: newWaypoint.gid });
                        newWaypoint.save(function (err) {
                            if (err) {
                                return res.status(500).send(err.message);
                            } else {
                                res.sendStatus(200);
                            }
                        });
                    }
                });
            }
        });
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// DELETE map/:gid
mapRouter.delete("/:gid", async (req: Request, res: Response) => {
    const gid: String = req.params.gid;
    try {
        // TODO
        res.sendStatus(405);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

