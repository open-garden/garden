import express from "express";
import router from "./routes/route";
import { PORT_SERVER } from "./common/const";
import { conLogger, appLogger } from "./common/logger";

const app = express();
const bodyParser = require("body-parser");
const cors = require("cors");

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use('/portal', express.static('portal'));
app.use("/", router);
app.use(cors());
router.use(cors());

app.use(conLogger);
router.use(conLogger);

const port = process.env.PORT || PORT_SERVER;
app.listen(port);
appLogger.info("GARDEN_Portal Server listening on port " + port);
