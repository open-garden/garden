import express from "express";
import { RunDataRow, RDFWhereClause } from "../common/types";
import {
  getRunData,
  updateRunData,
  registerRunData,
  deleteRunData,
} from "../models/rundata_model";
import { getRdfData } from "../models/rdfdata_model";
import { API_URI_INFO } from "../common/const";
import { appLogger } from "../common/logger";
import { getMapDataByMeasurement } from "../models/mapdata_model";

const router = express.Router();

router.get(
  API_URI_INFO.mapdata,
  (req: express.Request, res: express.Response) => {
    appLogger.info(`${req.method} : ${req.originalUrl}`);

    try {
      if (req.query.measurement === undefined || req.query.measurement === "") {
        appLogger.error("Measurement parameter is reuired.");
        res.status(400).json({});
        return;
      }

      getMapDataByMeasurement(
        req.query.measurement as string,
        (result: any, error: Error) => {
          if (error) {
            appLogger.error(error);
            res.status(500).json({});
          } else {
            appLogger.info(JSON.stringify(result));
            res.status(200).json(result);
          }
        }
      );
    } catch (error) {
      appLogger.fatal(error);
      res.status(500).json({});
    }
  }
);

router.get(
  API_URI_INFO.rdfdata,
  (req: express.Request, res: express.Response) => {
    appLogger.info(`${req.method} : ${req.originalUrl}`);

    try {
      const pagesize: any = req.query.pageSize;
      const page: any = req.query.page;
      const sort: any =
        req.query.sort === undefined || req.query.sort === ""
          ? "tag asc"
          : req.query.sort;
      const tagFilter: any = req.query.tagfilter;
      const measurementFilter: any = req.query.measurementfilter;
      const startTimeFilter: any = req.query.starttimefilter;
      const endTimeFilter: any = req.query.endtimefilter;
      const filter: RDFWhereClause = {
        tag: [],
        measurement: [],
        startTime: [],
        endTime: [],
      };
      const tagFilters: string[] =
        tagFilter == undefined || tagFilter == "" ? [] : tagFilter.split(" ");
      filter.tag = tagFilters;
      const measurementFilters: string[] =
        measurementFilter == undefined || measurementFilter == ""
          ? []
          : measurementFilter.split(" ");
      filter.measurement = measurementFilters;
      const starttimeFilters: string[] =
        startTimeFilter == undefined || startTimeFilter == ""
          ? []
          : startTimeFilter.split(" ");
      filter.startTime = starttimeFilters;
      const endtimeFilters: string[] =
        endTimeFilter == undefined || endTimeFilter == ""
          ? []
          : endTimeFilter.split(" ");
      filter.endTime = endtimeFilters;

      getRdfData(
        (page - 1) * pagesize,
        +pagesize,
        sort,
        filter,
        (result: any, error: Error) => {
          if (error.message !== "") {
            appLogger.error(error);
            res.status(500).json({});
          } else {
            appLogger.info(JSON.stringify(result));
            res.status(200).json(result);
          }
        }
      );
    } catch (error) {
      appLogger.fatal(error);
      res.status(500).json({});
    }
  }
);

router.get(
  API_URI_INFO.rundata,
  (req: express.Request, res: express.Response) => {
    appLogger.info(`${req.method} : ${req.originalUrl}`);

    try {
      const pagesize: any = req.query.pageSize;
      const page: any = req.query.page;
      const sort: any =
        req.query.sort === undefined || req.query.sort === ""
          ? "id"
          : req.query.sort;

      getRunData(
        (page - 1) * pagesize,
        +pagesize,
        sort,
        (result: any, error: Error) => {
          if (error) {
            appLogger.error(error);
            res.status(500).json({});
          } else {
            appLogger.info(JSON.stringify(result));
            res.status(200).json(result);
          }
        }
      );
    } catch (error) {
      appLogger.fatal(error);
      res.status(500).json({});
    }
  }
);

router.put(
  API_URI_INFO.rundata,
  (req: express.Request, res: express.Response) => {
    appLogger.info(`${req.method} : ${req.originalUrl}`);
    appLogger.info(
      `BODY : ${req.body.map((d: any) => JSON.stringify(d)).join(",")}`
    );

    try {
      const reqData: any[] = req.body;
      const rowDataArray: RunDataRow[] = [];
      for (let i = 0; i < reqData.length; i++) {
        const rowData: RunDataRow = {
          id: reqData[i]["id"],
          measurement:
            reqData[i]["measurement"] === undefined
              ? ""
              : reqData[i]["measurement"],
          mapid: reqData[i]["mapid"] === undefined ? "" : reqData[i]["mapid"],
        };
        rowDataArray.push(rowData);
      }
      updateRunData(rowDataArray, (result: any, error: Error) => {
        if (error.message !== "") {
          appLogger.error(error);
          res.status(500).json({});
        } else {
          appLogger.info(JSON.stringify(result));
          res.sendStatus(200);
        }
      });
    } catch (error) {
      appLogger.fatal(error);
      res.status(500).json({});
    }
  }
);

router.post(
  API_URI_INFO.rundata,
  (req: express.Request, res: express.Response) => {
    appLogger.info(`${req.method} : ${req.originalUrl}`);
    appLogger.info(`BODY : ${JSON.stringify(req.body)}`);

    try {
      const reqData: any = req.body;
      const rowData: RunDataRow = {
        id: 0,
        measurement:
          reqData["measurement"] === undefined ? "" : reqData["measurement"],
        mapid: reqData["mapid"] === undefined ? "" : reqData["mapid"],
      };
      registerRunData(rowData, (result: any, error: Error) => {
        if (error) {
          appLogger.error(error);
          res.status(500).json({});
        } else {
          appLogger.info(JSON.stringify(result));
          res.sendStatus(200);
        }
      });
    } catch (error) {
      appLogger.fatal(error);
      res.status(500).json({});
    }
  }
);

router.delete(
  API_URI_INFO.rundata,
  (req: express.Request, res: express.Response) => {
    appLogger.info(`${req.method} : ${req.originalUrl}`);
    appLogger.info(
      `BODY : ${req.body.map((d: any) => JSON.stringify(d)).join(",")}`
    );

    try {
      const reqData: any[] = req.body;
      const rowDataArray: RunDataRow[] = [];
      for (let i = 0; i < reqData.length; i++) {
        const rowData: RunDataRow = {
          id: reqData[i]["id"],
          measurement: "",
          mapid: "",
        };
        rowDataArray.push(rowData);
      }
      try {
        deleteRunData(rowDataArray, (result: any, error: Error) => {
          if (error) {
            appLogger.error(error);
            res.status(500).json({});
          } else {
            appLogger.info(JSON.stringify(result));
            res.sendStatus(200);
          }
        });
      } catch (error) {
        appLogger.fatal(error);
        res.status(500).json({});
      }
    } catch (error) {
      appLogger.fatal(error);
      res.status(500).json({});
    }
  }
);

export default router;
