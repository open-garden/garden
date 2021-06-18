export const PgConnectInfo = {
  host: "localhost",
  database: "garden",
  user: "postgres",
  password: "postgres",
  port: 5432,
};

export const PORT_SERVER = "3000";
export const DB_NAME_FUSEKI = "garden_rdf";
export const HOST_FUSEKI = "http://localhost:3030/";
export const FUSEKI_URL = HOST_FUSEKI + DB_NAME_FUSEKI;
export const API_URI_INFO = {
  rundata: "/portal/api/rundata",
  rdfdata: "/portal/api/rdfdata",
  mapdata: "/portal/api/mapdata",
};

export const PATH_LOGFILE = "./log/app.log";
