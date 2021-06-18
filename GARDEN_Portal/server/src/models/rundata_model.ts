import { Client } from "pg";
import { RunDataRow } from "../common/types";
import { PgConnectInfo } from "../common/const";

/**
 * 走行データ削除
 *
 * @export
 * @param {RunDataRow[]} deletedateRow 削除対象行の配列
 * @param {(data: any, error: Error) => void} callback
 */
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export async function deleteRunData(
  deletedateRow: RunDataRow[],
  callback: (data: any, error: Error) => void
) {
  try {
    const client: Client = new Client(PgConnectInfo);
    await client.connect();
    await client.query("BEGIN");
    await client.query(
      `DELETE FROM importeddata WHERE id IN(${[...deletedateRow]
        .map((d) => d.id)
        .join(",")}) RETURNING drivingdataid`,
      (ierr, ires: any) => {
        if (ierr) {
          client.end();
          callback("", ierr);
          return;
        }

        client.query(
          `DELETE FROM drivingdata WHERE id IN (${[...ires["rows"]]
            .map((v: any) => v.drivingdataid)
            .join(",")})`,
          (derr, dres) => {
            if (derr) {
              client.end();
              callback("", derr);
              return;
            }

            client.query("COMMIT");
            client.end();
            callback(`${dres.command}:${dres.rowCount}`, derr);
          }
        );
      }
    );
  } catch (e) {
    callback("", e);
  }
}

/**
 * 走行データ登録
 *
 * @export
 * @param {RunDataRow} registRow 登録対象行
 * @param {(data: any, error: Error) => void} callback
 */
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export async function registerRunData(
  registRow: RunDataRow,
  callback: (data: any, error: Error) => void
) {
  try {
    const client: Client = new Client(PgConnectInfo);
    await client.connect();
    await client.query(
      `INSERT INTO drivingdata(sourcetype) VALUES($1) RETURNING id`,
      [""],
      (derr, dres) => {
        if (derr) {
          client.end();
          callback("", derr);
          return;
        }

        client.query(
          `INSERT INTO importeddata(drivingdataid, lat, latmax, latmin, lon, lonmax, lonmin, mapid, measurement, status)
          VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9,$10)`,
          [
            dres["rows"][0]["id"],
            "NaN",
            "NaN",
            "NaN",
            "NaN",
            "NaN",
            "NaN",
            registRow.mapid,
            registRow.measurement,
            0,
          ],
          (ierr, ires) => {
            if (ierr) {
              client.end();
              callback("", ierr);
              return;
            }

            client.end();
            callback(`${ires.command}:${ires.rowCount}`, ierr);
          }
        );
      }
    );
  } catch (e) {
    callback("", e);
  }
}

/**
 * 走行データ更新
 *
 * @export
 * @param {RunDataRow[]} updateRow 更新対象行の配列
 * @param {(data: any, error: Error) => void} callback
 */
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export async function updateRunData(
  updateRow: RunDataRow[],
  callback: (data: any, error: Error) => void
) {
  try {
    const client: Client = new Client(PgConnectInfo);
    await client.connect();
    await client.query("BEGIN");

    const updateArray: string[] = [];
    for (let i = 0; i < updateRow.length; i++) {
      updateArray.push(
        `UPDATE importeddata SET measurement='${updateRow[i].measurement}', mapid='${updateRow[i].mapid}' where id=${updateRow[i].id}`
      );
    }
    const promises = updateArray.map((sql) => client.query(sql));
    await Promise.all(promises)
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      .then((v) => {
        client.query("COMMIT");
        client.end();
        callback(`UPDATE:${updateRow.length}`, new Error());
      })
      .catch((error) => {
        client.end();
        callback("", error);
      });
  } catch (e) {
    callback("", e);
  }
}

/**
 * 走行データ取得
 *
 * @export
 * @param {number} skip スキップ数
 * @param {number} limit 取得数
 * @param {string} sort 並べ替え条件
 * @param {(data: any, error: Error) => void} callback
 */
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export async function getRunData(
  skip: number,
  limit: number,
  sort: string,
  callback: (data: any, error: Error) => void
) {
  const client: Client = new Client(PgConnectInfo);
  try {
    await client.connect();

    await client.query(
      `SELECT COUNT(*) as allcount
        FROM importeddata`,
      (countErr, countRes) => {
        if (countErr) {
          client.end();
          callback("", countErr);
          return;
        }
        client.query(
          `SELECT id, measurement, mapid 
            FROM importeddata 
            ORDER BY ${sort} offset $1 limit $2`,
          [skip, limit],
          (err, res) => {
            if (err) {
              client.end();
              callback("", err);
              return;
            }
            const ret: any = {};
            client.end();
            ret["allcount"] = countRes.rows[0]["allcount"];
            ret["result"] = res.rows;
            callback(ret, err);
          }
        );
      }
    );
  } catch (e) {
    callback("", e);
  }
}
