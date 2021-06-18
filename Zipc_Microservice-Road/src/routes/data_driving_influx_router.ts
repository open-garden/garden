import express, { Request, Response } from "express";
// const Influx = require('influx');

import * as Influx from 'influx';

/**
 * Router Definition
 */
export const dataDrivingInfluxRouter = express.Router()

/**
 * Influx Client
 */
const influxClient = new Influx.InfluxDB({
    database: 'garden_ts',
    host: 'localhost',
    // host: '172.16.11.43',
    port: 8086
});

const OBJ_KEYS = ['x', 'y', 'z', 'roll', 'yaw', 'pitch',]

const groupByTime = (array, getKey) =>
    array.reduce((obj, cur, idx, src) => {
        // const time = cur['time'].toNanoISOString();
        const time = cur['timestamp'];
        for (let objKey in cur) {
            if (objKey === 'time' || objKey === 'timestamp')
                continue;

            const key = getKey(objKey);
            if (!(key.length < 1 || !key[0] || OBJ_KEYS.indexOf(key[1]) < 0)) {
                if (!obj[key[0]])
                    obj[key[0]] = {};
                if (!obj[key[0]][time])
                    obj[key[0]][time] = { time: time };

                if (key[1] === 'y') {
                    obj[key[0]][time]['z'] = -cur[objKey];
                } else if (key[1] === 'z') {
                    obj[key[0]][time]['y'] = cur[objKey];
                } else {
                    obj[key[0]][time][key[1]] = cur[objKey];
                }
            }
        }
        return obj;
    }, {});

// GET data/driving/influx/:nameSpace/:tableName/:startTime/:endTime
dataDrivingInfluxRouter.get('/:measurement/:startTime/:endTime', async function (req, res) {
    const measurement = req.params.measurement;
    const startTime = req.params.startTime;
    const endTime = req.params.endTime;;
    let resultData = await influxClient.query(
        /*`SELECT *
         FROM "${measurement}"
         ORDER BY time ASC`*/
        `SELECT *
         FROM "${measurement}"
         WHERE time >= '${startTime}' - 3s
           AND time <= '${endTime}' + 5s
         ORDER BY time ASC`
    ).then((results: any) => {
        return results;
    });
    // ##########################################
    if (resultData.length > 0) {
        const baseTime = resultData[0].time.getTime();
        for (let data of resultData) {
            data['timestamp'] = data.time.getTime() - baseTime;
        }
    }
    // ##########################################
    const drivingData = groupByTime(resultData, name => name.split('_'));
    res.status(200).json(drivingData);
});