// expressモジュールを読み込む
import express, { Application } from 'express';
import path from 'path';
import connect from './connect';

import { dataDrivingRdfRouter } from './routes/data_driving_rdf_router';
import { dataDrivingInfluxRouter } from './routes/data_driving_influx_router';
import { mapRouter } from './routes/map_router';

// expressアプリを生成する
const app: Application = express();

// resuest bodyのサイズ変更（デフォルトサイズは100kb）
app.use(express.urlencoded({ extended: true, limit: '10mb' }));

// staticディレクトリを指定する
// app.use('/road_editor', express.static('static'));
app.use("/road_editor", express.static(__dirname + "/static"));

// CORSの許可
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
  res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
  next();
});

// body-parserに基づいた着信リクエストの解析
var bodyParser = require('body-parser');
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies

// GetとPostのルーティング
app.use('/road_editor/map', mapRouter)
app.use('/road_editor/data/driving/rdf', dataDrivingRdfRouter)
app.use('/road_editor/data/driving/influx', dataDrivingInfluxRouter)

// ポート38001でサーバを立てる
app.listen(38001, () => console.log('Listening on port 38001'));

// const db = 'mongodb://172.16.0.94:27017/garden_doc';
const db = 'mongodb://127.0.0.1:27017/garden_doc';
connect({ db });