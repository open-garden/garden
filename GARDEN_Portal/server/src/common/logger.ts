import { configure, getLogger, connectLogger, Logger } from "log4js";
import { PATH_LOGFILE } from "./const";

configure({
  appenders: {
    consoleLog: {
      type: "console",
    },
    appLog: {
      type: "dateFile",
      filename: PATH_LOGFILE,
      alwaysIncludePattern: true,
      maxLogSize: 5000000,
      pattern: ".yyyy-MM-dd",
      daysToKeep: 7,
      keepFileExt: true,
    },
  },
  categories: {
    default: {
      appenders: ["consoleLog", "appLog"],
      level: "INFO",
    },
  },
});

export const appLogger: Logger = getLogger();
export const conLogger = connectLogger(appLogger, { level: "info" });
