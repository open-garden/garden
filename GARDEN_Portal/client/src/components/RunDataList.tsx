import React from "react";
import MaterialTable, { MTableToolbar } from "material-table";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogContent from "@material-ui/core/DialogContent";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import AlertTitle from "@material-ui/lab/AlertTitle";
import RegistRunData from "./RegisterRunData";
import {
  Icons,
  HOST_ROADEDITOR_MAP,
  HOST_CRONOGRAF,
  CHRONOGRAF_DB,
  RowData,
  Messages,
  URL_RUNDATA,
  HOST_AIRFLOW,
} from "../common/Const";
import CommonDialog from "./CommonDialog";

/**
 * 走行データ一覧コンポーネント
 *
 * @return {*}
 */
export const RunDataList: React.FC = () => {
  const [registerOpen, setRegisterOpen] = React.useState(false);
  const [selectedRows, setSelectedRows] = React.useState([]);
  const [successSnackOpen, setSuccessSnackOpen] = React.useState(false);
  const [errorSnackOpen, setErrorSnackOpen] = React.useState(false);
  const [errorSnackText, setErrorSnackText] = React.useState(
    Messages.ERROR_DEFAULT
  );
  const [pageMove, setPageMove] = React.useState(0);
  const [deleteConfirmOpen, setDeleteConfirmOpen] = React.useState(false);
  const [mapclearConfirmOpen, setMapClearConfirmOpen] = React.useState(false);

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
   * 新規登録画面表示
   *
   */
  const handleRegisterOpen = () => {
    setRegisterOpen(true);
  };

  /**
   * 削除確認ダイアログ表示
   *
   */
  const handleDeleteConfirmOpen = () => {
    if (selectedRows.length > 0) {
      setDeleteConfirmOpen(true);
    } else {
      handleErrorSnackOpen("No selected data.");
    }
  };

  /**
   * 削除確認ダイアログ消去
   *
   */
  const handleDeleteConfirmClose = () => {
    setDeleteConfirmOpen(false);
  };

  /**
   * MAPID消去確認ダイアログ表示
   *
   */
  const handleMapClearConfirmOpen = () => {
    if (selectedRows.length > 0) {
      setMapClearConfirmOpen(true);
    } else {
      handleErrorSnackOpen("No selected data.");
    }
  };

  /**
   * MAPID消去確認ダイアログ消去
   *
   */
  const handleMapClearConfirmClose = () => {
    setMapClearConfirmOpen(false);
  };

  const handleRegisterClose = () => {
    setRegisterOpen(false);
    tableRefCurrent && tableRefCurrent.onQueryChange();
  };

  /**
   * MapId消去処理
   *
   */
  const handleMapClear = () => {
    handleMapClearConfirmClose();
    const mapClearArray: any[] = [];
    for (let i = 0; i < selectedRows.length; i++) {
      mapClearArray.push({
        id: selectedRows[i]["id"],
        measurement: selectedRows[i]["measurement"],
        mapid: "",
      });
    }
    new Promise((resolve: any, reject) => {
      const url = URL_RUNDATA;
      fetch(url, {
        method: "put",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(mapClearArray),
      })
        .then((response) => {
          if (response.ok) {
            setSuccessSnackOpen(true);
            tableRefCurrent && tableRefCurrent.onQueryChange();
          } else {
            throw new Error(`${response.status}:${response.statusText}`);
          }
        })
        .catch((e: Error) => {
          handleErrorSnackOpen(e.message);
          reject();
        });
    });
  };

  /**
   * 走行データ削除処理
   *
   */
  const handleDelete = () => {
    handleDeleteConfirmClose();
    const deleteArray: any[] = [];
    for (let i = 0; i < selectedRows.length; i++) {
      deleteArray.push({ id: selectedRows[i]["id"] });
    }

    new Promise((resolve: any, reject) => {
      const url = URL_RUNDATA;
      fetch(url, {
        method: "delete",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(deleteArray),
      })
        .then((response) => {
          if (response.ok) {
            setSuccessSnackOpen(true);
            if (
              tableRefCurrent.dataManager.data.length === selectedRows.length
            ) {
              setPageMove(-1);
            } else {
              setPageMove(0);
            }
            tableRefCurrent && tableRefCurrent.onQueryChange();
          } else {
            throw new Error(`${response.status}:${response.statusText}`);
          }
        })
        .catch((e: Error) => {
          handleErrorSnackOpen(e.message);
          reject();
        });
    });
  };

  /**
   * Chronograf表示処理
   *
   */
  function openChronograf(): void {
    if (selectedRows.length > 0) {
      selectedRows.forEach((d: RowData) =>
        window.open(
          HOST_CRONOGRAF +
            "/sources/1/chronograf/data-explorer?query=select * from " +
            CHRONOGRAF_DB +
            "." +
            d.measurement,
          "_blank",
          "noopener noreferrer"
        )
      );
    } else {
      window.open(HOST_CRONOGRAF, "_blank", "noopener noreferrer");
    }
  }

  /**
   * RoadEditor表示処理
   *
   */
  function openRoadEditor(): void {
    if (selectedRows.length > 0) {
      selectedRows.forEach((d: RowData) =>
        window.open(
          HOST_ROADEDITOR_MAP + "?mapid=" + d.mapid,
          "_blank",
          "noopener noreferrer"
        )
      );
    } else {
      window.open(HOST_ROADEDITOR_MAP, "_blank", "noopener noreferrer");
    }
  }

  /**
   * AirFlow表示処理
   *
   */
  function openAirflow(): void {
    window.open(HOST_AIRFLOW, "_blank", "noopener noreferrer");
  }

  return (
    <div>
      <MaterialTable
        title="Driving Data List"
        tableRef={tableRef}
        icons={Icons}
        columns={[
          {
            title: "Id",
            field: "id",
            cellStyle: {
              width: 100,
              maxWidth: 100,
            },
            headerStyle: {
              width: 100,
              maxWidth: 100,
            },
            editable: "never",
          },
          { title: "Driving Data Name", field: "measurement" },
          { title: "Map Id", field: "mapid" },
        ]}
        data={(query: any) =>
          new Promise((resolve, reject) => {
            const params: any = {};
            let newPage = 0;
            const sort: string[] = [];

            newPage =
              query.page + pageMove < 0
                ? (newPage = 0)
                : (newPage = query.page + pageMove);
            params.pageSize = query.pageSize;
            params.page = newPage + 1;
            params.sort = sort;
            const queryParams = new URLSearchParams(params);
            const url = `${URL_RUNDATA}?${queryParams}`;
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
                  totalCount: parseInt(result.allcount.toString()),
                });
                setSelectedRows([]);
                setPageMove(0);
              })
              .catch((e: Error) => {
                handleErrorSnackOpen(e.message);
                reject();
              });
          })
        }
        editable={{
          onRowUpdate: (newData: any, oldData: any) =>
            new Promise((resolve: any, reject) => {
              const newDataArray: any[] = [];
              newDataArray.push(newData);
              const url = URL_RUNDATA;
              fetch(url, {
                method: "put",
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                },
                body: JSON.stringify(newDataArray),
              })
                .then((response) => {
                  if (response.ok) {
                    return;
                  } else {
                    throw new Error(
                      `${response.status}:${response.statusText}`
                    );
                  }
                })
                .then((result) => {
                  resolve();
                })
                .catch((e: Error) => {
                  handleErrorSnackOpen(e.message);
                  reject();
                });
            }),
          onRowDelete: (oldData) =>
            new Promise((resolve: any, reject) => {
              const url = URL_RUNDATA;
              const deleteArray: any[] = [];
              deleteArray.push(oldData);
              fetch(url, {
                method: "delete",
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                },
                body: JSON.stringify(deleteArray),
              })
                .then((response) => {
                  if (response.ok) {
                    return;
                  } else {
                    throw new Error(
                      `${response.status}:${response.statusText}`
                    );
                  }
                })
                .then((result) => {
                  if (tableRefCurrent.dataManager.data.length === 1) {
                    setPageMove(-1);
                  } else {
                    setPageMove(0);
                  }
                  resolve();
                })
                .catch((e: Error) => {
                  handleErrorSnackOpen(e.message);
                  reject();
                });
            }),
        }}
        options={{
          selection: true,
          sorting: false,
          showTextRowsSelected: false,
          actionsColumnIndex: -1,
          search: false,
          filtering: false,
          headerStyle: { backgroundColor: "#dfdfdf" },
          exportButton: true,
          exportAllData: true,
          exportFileName: "ExportDrivingData",
        }}
        onSelectionChange={(rows: any) => {
          setSelectedRows(
            rows.map((d) => {
              const row: RowData = {
                id: d.id,
                mapid: d.mapid,
                measurement: d.measurement,
              };
              return row;
            })
          );
        }}
        actions={[
          {
            // eslint-disable-next-line react/display-name
            icon: () => <Icons.Refresh />,
            tooltip: "Refresh Data",
            isFreeAction: true,
            onClick: () => tableRefCurrent && tableRefCurrent.onQueryChange(),
          },
        ]}
        components={{
          // eslint-disable-next-line react/display-name
          Toolbar: (props) => (
            <div style={{ marginBottom: "5px" }}>
              <MTableToolbar {...props} />
              <div style={{ textAlign: "left" }}>
                <Button
                  variant="outlined"
                  style={{ marginLeft: 5 }}
                  size="small"
                  endIcon={<Icons.Launch />}
                  color="primary"
                  onClick={openChronograf}
                >
                  Cronograf
                </Button>
                <Button
                  variant="outlined"
                  style={{ marginLeft: 5 }}
                  size="small"
                  onClick={openRoadEditor}
                  endIcon={<Icons.Launch />}
                  color="primary"
                >
                  Road Editor
                </Button>
                <Button
                  variant="outlined"
                  style={{ marginLeft: 5 }}
                  size="small"
                  onClick={openAirflow}
                  endIcon={<Icons.Launch />}
                  color="primary"
                >
                  Airflow
                </Button>
                <Button
                  variant="outlined"
                  style={{ marginLeft: 5 }}
                  size="small"
                  endIcon={<Icons.Launch />}
                  color="primary"
                  disabled={true}
                >
                  RDF Graph Viewer
                </Button>
                <Button
                  variant="contained"
                  style={{ marginRight: 5, float: "right" }}
                  size="small"
                  startIcon={<Icons.ThirdStateCheck />}
                  color="secondary"
                  onClick={handleMapClearConfirmOpen}
                >
                  Map Clear
                </Button>
                <Button
                  variant="contained"
                  style={{ marginRight: 5, float: "right" }}
                  size="small"
                  color="secondary"
                  startIcon={<Icons.Clear />}
                  onClick={handleDeleteConfirmOpen}
                >
                  Delete
                </Button>
                <Button
                  variant="contained"
                  style={{ marginRight: 5, float: "right" }}
                  size="small"
                  startIcon={<Icons.Add />}
                  color="primary"
                  onClick={handleRegisterOpen}
                >
                  Add
                </Button>
              </div>
            </div>
          ),
        }}
      ></MaterialTable>
      <Dialog
        open={registerOpen}
        onClose={handleRegisterClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogContent>
          <RegistRunData />
        </DialogContent>
      </Dialog>
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
      <CommonDialog
        msg="Are you sure you want to delete the selected data?"
        isOpen={deleteConfirmOpen}
        doYes={handleDelete}
        doNo={handleDeleteConfirmClose}
      ></CommonDialog>
      <CommonDialog
        msg="Are you sure you want to clear the selected map id?"
        isOpen={mapclearConfirmOpen}
        doYes={handleMapClear}
        doNo={handleMapClearConfirmClose}
      ></CommonDialog>
    </div>
  );
};

export default RunDataList;
