import React, { forwardRef } from "react";
import AddBox from "@material-ui/icons/AddBox";
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import Check from "@material-ui/icons/Check";
import ChevronLeft from "@material-ui/icons/ChevronLeft";
import ChevronRight from "@material-ui/icons/ChevronRight";
import Clear from "@material-ui/icons/Clear";
import DeleteOutline from "@material-ui/icons/DeleteOutline";
import Edit from "@material-ui/icons/Edit";
import FilterList from "@material-ui/icons/FilterList";
import FirstPage from "@material-ui/icons/FirstPage";
import LastPage from "@material-ui/icons/LastPage";
import Remove from "@material-ui/icons/Remove";
import SaveAlt from "@material-ui/icons/SaveAlt";
import Search from "@material-ui/icons/Search";
import ViewColumn from "@material-ui/icons/ViewColumn";
import Launch from "@material-ui/icons/Launch";
import Visibility from "@material-ui/icons/Visibility";
import Refresh from "@material-ui/icons/Refresh";
import Map from "@material-ui/icons/Map";
import EmojiTransportation from "@material-ui/icons/EmojiTransportation";

export type MenuItem =
  | ""
  | "RunDataList"
  | "ScenarioList"
  | "DrivingDataSearch"
  | "RDFViewer"
  | "ParameterList"
  | "ActorList"
  | "ConvertTool"
  | "Simulation";

const HOST_NAME = window.location.hostname || "localhost";
const HOST_RUNDATA_SERVER = `http://${HOST_NAME}`;

const API_URI_INFO = {
  rundata: "/portal/api/rundata",
  rdfdata: "/portal/api/rdfdata",
  mapdata: "/portal/api/mapdata",
};

export const NAME_GARDEN = "garden";
export const CHRONOGRAF_DB = "garden_ts.autogen";
export const HOST_ROADEDITOR_MAP = `http://${HOST_NAME}/road_editor`;
export const HOST_ROADEDITOR_VIEW = `http://${HOST_NAME}/road_editor`;
export const HOST_CRONOGRAF = `http://${HOST_NAME}/chronograf`;
export const HOST_SCENARIOEDITOR = `http://${HOST_NAME}/scenario_editor`;
export const HOST_AIRFLOW = `http://${HOST_NAME}/airflow`;
export const HOST_RDFVIEWER = `http://${HOST_NAME}/rdf_viewer`;

export const URL_RUNDATA = `${HOST_RUNDATA_SERVER}${API_URI_INFO.rundata}`;
export const URL_MAPDATA = `${HOST_RUNDATA_SERVER}${API_URI_INFO.mapdata}`;
export const URL_RDF = `${HOST_RUNDATA_SERVER}${API_URI_INFO.rdfdata}`;

export const Messages = {
  ERROR_DEFAULT: "An error has occurred.",
  SUCCESS_DEFAULT: "The operation has been completed successfully.",
  ERROR_TITLE: "Error",
  SUCCESS_TITLE: "Success",
};

export const Icons = {
  Add: forwardRef<SVGSVGElement>((props, ref) => (
    <AddBox {...props} ref={ref} />
  )),
  Check: forwardRef<SVGSVGElement>((props, ref) => (
    <Check {...props} ref={ref} />
  )),
  Clear: forwardRef<SVGSVGElement>((props, ref) => (
    <Clear {...props} ref={ref} />
  )),
  Delete: forwardRef<SVGSVGElement>((props, ref) => (
    <DeleteOutline {...props} ref={ref} />
  )),
  DetailPanel: forwardRef<SVGSVGElement>((props, ref) => (
    <ChevronRight {...props} ref={ref} />
  )),
  Edit: forwardRef<SVGSVGElement>((props, ref) => (
    <Edit {...props} ref={ref} />
  )),
  Export: forwardRef<SVGSVGElement>((props, ref) => (
    <SaveAlt {...props} ref={ref} />
  )),
  Filter: forwardRef<SVGSVGElement>((props, ref) => (
    <FilterList {...props} ref={ref} />
  )),
  FirstPage: forwardRef<SVGSVGElement>((props, ref) => (
    <FirstPage {...props} ref={ref} />
  )),
  LastPage: forwardRef<SVGSVGElement>((props, ref) => (
    <LastPage {...props} ref={ref} />
  )),
  NextPage: forwardRef<SVGSVGElement>((props, ref) => (
    <ChevronRight {...props} ref={ref} />
  )),
  PreviousPage: forwardRef<SVGSVGElement>((props, ref) => (
    <ChevronLeft {...props} ref={ref} />
  )),
  ResetSearch: forwardRef<SVGSVGElement>((props, ref) => (
    <Clear {...props} ref={ref} />
  )),
  Search: forwardRef<SVGSVGElement>((props, ref) => (
    <Search {...props} ref={ref} />
  )),
  SortArrow: forwardRef<SVGSVGElement>((props, ref) => (
    <ArrowDownward {...props} ref={ref} />
  )),
  ThirdStateCheck: forwardRef<SVGSVGElement>((props, ref) => (
    <Remove {...props} ref={ref} />
  )),
  ViewColumn: forwardRef<SVGSVGElement>((props, ref) => (
    <ViewColumn {...props} ref={ref} />
  )),
  Launch: forwardRef<SVGSVGElement>((props, ref) => (
    <Launch {...props} ref={ref} />
  )),
  Visibility: forwardRef<SVGSVGElement>((props, ref) => (
    <Visibility {...props} ref={ref} />
  )),
  Refresh: forwardRef<SVGSVGElement>((props, ref) => (
    <Refresh {...props} ref={ref} />
  )),
  Map: forwardRef<SVGSVGElement>((props, ref) => <Map {...props} ref={ref} />),
  EmojiTransportation: forwardRef<SVGSVGElement>((props, ref) => (
    <EmojiTransportation {...props} ref={ref} />
  )),
};

export type RegistData = {
  measurement: string;
  mapid: string;
};

export type DeleteData = {
  id: number;
};

export type RowData = {
  id: string;
  measurement: string;
  mapid: string;
};
