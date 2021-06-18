import { Client } from "pg";
import { PgConnectInfo } from "../common/const";

/**
 * MapId取得
 *
 * @export
 * @param {string} measurement 走行データ名
 * @param {(data: any, error: Error) => void} callback
 */
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export async function getMapDataByMeasurement(
  measurement: string,
  callback: (data: any, error: Error) => void
) {
  const client: Client = new Client(PgConnectInfo);
  try {
    await client.connect();

    await client.query(
      `SELECT MAPID FROM importeddata 
      WHERE measurement=$1 limit 1`,
      [measurement],
      (mapidErr, mapidRes) => {
        if (mapidErr) {
          client.end();
          callback("", mapidErr);
          return;
        }
        client.end();
        callback({ mapid: mapidRes.rows[0]["mapid"] }, mapidErr);
      }
    );
  } catch (e) {
    callback("", e);
  }
}
