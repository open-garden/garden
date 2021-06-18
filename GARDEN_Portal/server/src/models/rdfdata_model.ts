import axios from "axios";
import { FUSEKI_URL } from "../common/const";
import { RDFWhereClause, RDFSearchResult } from "../common/types";

/**
 * RDFデータ取得
 *
 * @export
 * @param {number} skip スキップ数
 * @param {number} limit 取得数
 * @param {string} sort 並べ替え条件
 * @param {RDFWhereClause} filter フィルター条件
 * @param {(data: any, error: Error) => void} callback
 */
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export async function getRdfData(
  skip: number,
  limit: number,
  sort: string,
  filter: RDFWhereClause,
  callback: (data: any, error: Error) => void
) {
  const orderMap: any = {
    tag: "?value",
    measurement: "?measurement",
    startTime: "?startTime",
    endTime: "?endTime",
  };
  const tagfilters =
    filter.tag.length > 0
      ? // eslint-disable-next-line @typescript-eslint/no-unused-vars
        filter.tag.map((v, i, a) => {
          return `CONTAINS(?value ,"${v}" )`;
        })
      : [`CONTAINS(?value ,"" )`];
  const measurementfilters =
    filter.measurement.length > 0
      ? // eslint-disable-next-line @typescript-eslint/no-unused-vars
        filter.measurement.map((v, i, a) => {
          return `CONTAINS(?measurement ,"${v}" )`;
        })
      : [`CONTAINS(?measurement ,"" )`];
  const starttimefilters =
    filter.startTime.length > 0
      ? // eslint-disable-next-line @typescript-eslint/no-unused-vars
        filter.startTime.map((v, i, a) => {
          return `CONTAINS(?startTime ,"${v}" )`;
        })
      : [`CONTAINS(?startTime ,"" )`];
  const endtimefilters =
    filter.endTime.length > 0
      ? // eslint-disable-next-line @typescript-eslint/no-unused-vars
        filter.endTime.map((v, i, a) => {
          return `CONTAINS(?endTime ,"${v}" )`;
        })
      : [`CONTAINS(?endTime ,"" )`];

  const contains = `FILTER ((${tagfilters.join(
    "||"
  )}) && (${measurementfilters.join("||")}) && (${starttimefilters.join(
    "||"
  )}) && (${endtimefilters.join("||")}))`;

  const items = "?measurement ?value ?startTime ?endTime";
  const counts = "(COUNT(*) AS ?allcount)";
  const limits = `LIMIT ${limit} OFFSET ${skip}`;
  const sortOrder: string[] = sort.split(" ");
  const orderItemName: string = orderMap[sortOrder[0]];
  const order = `ORDER BY ${sortOrder[1]}(${orderItemName})`;
  const countQuery = `prefix : <http://www.zipc.com/model/scenario#>
    prefix owl: <http://www.w3.org/2002/07/owl#>
    prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    prefix xml: <http://www.w3.org/XML/1998/namespace>
    prefix xsd: <http://www.w3.org/2001/XMLSchema#>
    prefix zss: <http://www.zipc.com/schema/scenario-schema#>
    prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    base <http://www.zipc.com/model/scenario>
    SELECT ${counts} WHERE {
      GRAPH <http://www.zipc.com/model/scenario> {
        ?scenario a zss:RawData;
        rdfs:label ?measurement;
        zss:tag ?tag.
        ?tag a zss:Tag;
        rdfs:label ?value;
        zss:start [ a zss:Time ;
                       rdfs:label ?startTime ] ;
           zss:end [ a zss:Time ;
                     rdfs:label ?endTime ] .
      }
    ${contains}
    }`;

  const dataQuery = `prefix : <http://www.zipc.com/model/scenario#>
    prefix owl: <http://www.w3.org/2002/07/owl#>
    prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    prefix xml: <http://www.w3.org/XML/1998/namespace>
    prefix xsd: <http://www.w3.org/2001/XMLSchema#>
    prefix zss: <http://www.zipc.com/schema/scenario-schema#>
    prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    base <http://www.zipc.com/model/scenario>
    SELECT ${items} WHERE {
      GRAPH <http://www.zipc.com/model/scenario> {
        ?scenario a zss:RawData;
        rdfs:label ?measurement;
        zss:tag ?tag.
        ?tag a zss:Tag;
        rdfs:label ?value;
        zss:start [ a zss:Time ;
                       rdfs:label ?startTime ] ;
           zss:end [ a zss:Time ;
                     rdfs:label ?endTime ] .
      }
    ${contains}
    } ${order} ${limits}`;

  const url = `${FUSEKI_URL}/query`;
  const databody: string = "query=" + encodeURIComponent(dataQuery);
  const countbody: string = "query=" + encodeURIComponent(countQuery);

  axios
    .post(url, countbody)
    .then((rescount) => {
      axios
        .post(url, databody)
        .then((resdata) => {
          const retArray: RDFSearchResult[] = [];
          for (let i = 0; i < resdata.data.results.bindings.length; i++) {
            const rowdata: RDFSearchResult = {
              measurement: resdata.data.results.bindings[i].measurement.value,
              tag: resdata.data.results.bindings[i].value.value,
              startTime: resdata.data.results.bindings[i].startTime.value,
              endTime: resdata.data.results.bindings[i].endTime.value,
            };
            retArray.push(rowdata);
          }
          callback &&
            callback(
              {
                allcount: rescount.data.results.bindings[0].allcount.value,
                result: retArray,
              },
              new Error("")
            );
        })
        .catch((e) => {
          callback && callback("", e);
        });
    })
    .catch((e) => {
      callback("", e);
    });
}
