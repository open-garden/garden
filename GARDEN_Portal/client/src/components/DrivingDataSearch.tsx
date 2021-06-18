import React from "react";
import { makeStyles, Theme, createStyles } from "@material-ui/core/styles";
import MaterialTable from "material-table";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import AlertTitle from "@material-ui/lab/AlertTitle";
import {
  Icons,
  Messages,
  URL_RDF,
  URL_MAPDATA,
  HOST_ROADEDITOR_VIEW,
} from "../common/Const";
import IconButton from "@material-ui/core/IconButton";
import SearchIcon from "@material-ui/icons/Search";
import TextField from "@material-ui/core/TextField";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      padding: "2px 4px",
      display: "flex",
      alignItems: "center",
      width: 400,
    },
    input: {
      marginLeft: theme.spacing(1),
      flex: 1,
      paddingRight: 15,
    },
    iconButton: {
      paddingTop: 25,
    },
    divider: {
      height: 28,
      margin: 4,
    },
  })
);

/**
 * 走行データ検索用コンポーネント
 *
 * @return {*}
 */
export const DrivingDataSearch: React.FC = () => {
  const [successSnackOpen, setSuccessSnackOpen] = React.useState(false);
  const [errorSnackOpen, setErrorSnackOpen] = React.useState(false);
  const [errorSnackText, setErrorSnackText] = React.useState(
    Messages.ERROR_DEFAULT
  );
  const [pageMove, setPageMove] = React.useState(0);
  const [searchTagText, setSearchTagText] = React.useState("");
  const [searchMeasurementText, setSearchMeasurementText] = React.useState("");
  const [searchStartTimeText, setSearchStartTimeText] = React.useState("");
  const [searchEndTimeText, setSearchEndTimeText] = React.useState("");

  const classes = useStyles();

  const tableRef = React.useRef();
  const tableRefCurrent: any = tableRef.current;

  /**
   * エラーメッセージ表示処理
   *
   * @param {string} message エラーメッセージ
   */
  const handleErrorSnackOpen = (message: string) => {
    setErrorSnackText(message);
    setErrorSnackOpen(true);
  };

  /**
   * 正常メッセージ消去処理
   *
   * @param {React.SyntheticEvent} [event]
   * @param {string} [reason]
   * @return {*}
   */
  const handleSuccessSnackClose = (
    event?: React.SyntheticEvent,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }
    setSuccessSnackOpen(false);
  };

  /**
   * エラーメッセージ消去処理
   *
   * @param {React.SyntheticEvent} [event]
   * @param {string} [reason]
   * @return {*}
   */
  const handleErrorSnackClose = (
    event?: React.SyntheticEvent,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }
    setErrorSnackOpen(false);
  };

  /**
   * 走行データ検索実行処理
   *
   * @param {*} e
   */
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const handleSearchExecute = (e: any) => {
    setPageMove(-1);
    tableRefCurrent && tableRefCurrent.onQueryChange();
  };

  /**
   * タグ検索文字列変更処理
   *
   * @param {*} e
   */
  const searchTagTextChange = (e) => {
    setSearchTagText(e.target.value);
  };

  /**
   * 走行データ名称変更処理
   *
   * @param {*} e
   */
  const searchMeasurementTextChange = (e) => {
    setSearchMeasurementText(e.target.value);
  };

  /**
   * 走行データ開始時間変更処理
   *
   * @param {*} e
   */
  const searchStartTimeTextChange = (e) => {
    setSearchStartTimeText(e.target.value);
  };

  /**
   * 走行データ終了時間変更処理
   *
   * @param {*} e
   */
  const searchEndTimeTextChange = (e) => {
    setSearchEndTimeText(e.target.value);
  };

  /**
   * 地図情報取得用画面への遷移
   *
   * @param {*} row
   */
  const openRoadMapView = (row: any) => {
    const mapParams: any = {};
    mapParams.measurement = row.measurement;
    const mapQueryParams = new URLSearchParams(mapParams);
    const mapUrl = `${URL_MAPDATA}?${mapQueryParams}`;
    fetch(mapUrl)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error(`${response.status}:${response.statusText}`);
        }
      })
      .then((mapResult) => {
        const params: any = {};
        params.preview = "true";
        params.mapGid = mapResult.mapid;
        params.drivingDataName = row.measurement;
        params.startTime = row.startTime;
        params.endTime = row.endTime;
        const queryParams = new URLSearchParams(params);
        const url = `${HOST_ROADEDITOR_VIEW}?${queryParams}`;
        window.open(url, "_blank", "noopener noreferrer");
      })
      .catch((e: Error) => {
        handleErrorSnackOpen(e.message);
      });
  };

  return (
    <div>
      <MaterialTable
        title={
          <div style={{ textAlign: "start", paddingTop: "15px" }}>
            <h6
              className="MuiTypography-root MuiTypography-h6"
              style={{
                fontSize: "1.25rem",
                fontFamily: "'Roboto', 'Helvetica', 'Arial', 'sans-serif'",
                fontWeight: 400,
              }}
            >
              Driving Data Search
            </h6>
            <div
              style={{
                paddingTop: 15,
                paddingBottom: 0,
              }}
            >
              <TextField
                className={classes.input}
                placeholder="Search Tag"
                onChange={searchTagTextChange}
                label="Search Tag"
              />
              <TextField
                className={classes.input}
                placeholder="Search Driving Data Name"
                onChange={searchMeasurementTextChange}
                label="Search Driving Data Name"
              />
              <TextField
                className={classes.input}
                placeholder="Search Start Time"
                onChange={searchStartTimeTextChange}
                label="Search Start Time"
              />
              <TextField
                className={classes.input}
                style={{ paddingRight: 0 }}
                placeholder="Search End Time"
                onChange={searchEndTimeTextChange}
                label="Search End Time"
              />
              <IconButton
                type="button"
                className={classes.iconButton}
                onClick={handleSearchExecute}
              >
                <SearchIcon />
              </IconButton>
            </div>
          </div>
        }
        tableRef={tableRef}
        icons={Icons}
        columns={[
          {
            title: "Tag",
            field: "tag",
          },
          { title: "Driving Data Name", field: "measurement" },
          { title: "Start Time", field: "startTime" },
          { title: "End Time", field: "endTime" },
        ]}
        data={(query: any) =>
          new Promise((resolve, reject) => {
            const params: any = {};
            let newPage = 0;

            newPage =
              pageMove < 0 ? (newPage = 0) : (newPage = query.page + pageMove);
            params.pageSize = query.pageSize;
            params.page = newPage + 1;
            if (query.orderBy === undefined) {
              params.sort = "tag asc";
            } else {
              params.sort = `${query.orderBy.field} ${query.orderDirection}`;
            }
            params.tagfilter = searchTagText;
            params.measurementfilter = searchMeasurementText;
            params.starttimefilter = searchStartTimeText;
            params.endtimefilter = searchEndTimeText;
            const queryParams = new URLSearchParams(params);
            const url = `${URL_RDF}?${queryParams}`;
            fetch(url)
              .then((response) => {
                if (response.ok) {
                  return response.json();
                } else {
                  throw new Error(`${response.status}:${response.statusText}`);
                }
              })
              .then((result) => {
                resolve({
                  data: result.result,
                  page: newPage,
                  totalCount: parseInt(result.allcount),
                });
                setPageMove(0);
              })
              .catch((e: Error) => {
                handleErrorSnackOpen(e.message);
                reject();
              });
          })
        }
        options={{
          selection: false,
          showTextRowsSelected: false,
          actionsColumnIndex: -1,
          search: false,
          filtering: false,
          headerStyle: { backgroundColor: "#dfdfdf" },
          toolbar: true,
          sorting: true,
          pageSizeOptions: [5, 10, 20, 50, 100],
          exportButton: true,
          exportAllData: true,
          exportFileName: "ExportDrivingSearch",
        }}
        actions={[
          {
            icon: () => <Icons.Refresh />,
            tooltip: "Refresh Data",
            isFreeAction: true,
            onClick: () => tableRefCurrent && tableRefCurrent.onQueryChange(),
          },
          {
            icon: () => <Icons.EmojiTransportation />,
            tooltip: "Preview",
            onClick: (_, rowData) => {
              openRoadMapView(rowData as any);
            },
          },
        ]}
        localization={{
          header: {
            actions: "Preview",
          },
        }}
      ></MaterialTable>
      <Snackbar
        open={successSnackOpen}
        autoHideDuration={6000}
        onClose={handleSuccessSnackClose}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
      >
        <Alert onClose={handleSuccessSnackClose} severity="success">
          <AlertTitle>Success</AlertTitle>
          The operation has been completed successfully.
        </Alert>
      </Snackbar>
      <Snackbar
        open={errorSnackOpen}
        autoHideDuration={6000}
        onClose={handleErrorSnackClose}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
      >
        <Alert onClose={handleErrorSnackClose} severity="error">
          <AlertTitle>Error</AlertTitle>
          {errorSnackText}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default DrivingDataSearch;
