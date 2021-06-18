import React, { forwardRef } from "react";
import Input from "@material-ui/core/Input";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import MaterialTable, { MTableToolbar } from "material-table";
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
import InsertDriveFile from "@material-ui/icons/InsertDriveFile";
import Warning from "@material-ui/icons/Warning";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";

const Icons = {
  Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
  Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
  Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
  DetailPanel: forwardRef((props, ref) => (
    <ChevronRight {...props} ref={ref} />
  )),
  Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
  Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
  Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
  FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
  LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
  NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
  PreviousPage: forwardRef((props, ref) => (
    <ChevronLeft {...props} ref={ref} />
  )),
  ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
  SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
  ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
  ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />),
  Launch: forwardRef((props, ref) => <Launch {...props} ref={ref} />),
  Visibility: forwardRef((props, ref) => <Visibility {...props} ref={ref} />),
  Refresh: forwardRef((props, ref) => <Refresh {...props} ref={ref} />),
  Map: forwardRef((props, ref) => <Map {...props} ref={ref} />),
  InsertDriveFile: forwardRef((props, ref) => (
    <InsertDriveFile {...props} ref={ref} />
  )),
  EmojiTransportation: forwardRef((props, ref) => (
    <EmojiTransportation {...props} ref={ref} />
  )),
  Warning: forwardRef((props, ref) => <Warning {...props} ref={ref} />),
  ArrowDownward: forwardRef((props, ref) => (
    <ArrowDownward {...props} ref={ref} />
  )),
};

/**
 * Concrete Scenarioの自動生成のためのコンポーネント
 *
 * @class GenerateConcreteScenario
 * @extends {React.Component}
 */
class GenerateConcreteScenario extends React.Component {
  classes = this.props;
  constructor(props) {
    super(props);
    this.state = {
      id: props.id,
      gid: props.gid,
      used_date: this.props.used_date,
      editorValue: this.props.editorValue,
      tableData: this.props.tableRows,
      tableColumn: this.props.tableColumns,
      concreteGid: this.props.gid,
      checked: this.props.checked,
      tableRef: React.createRef(),
      parameterColumn: [
        { title: "Name", field: "name" },
        {
          title: "Value",
          field: "value",
          require: true,
          validate: (rowData) => {
            if (Object.keys(rowData).length === 0) {
              return { isValid: true };
            }
            if (rowData.value === undefined || rowData.value === "") {
              return {
                isValid: false,
                helperText: "This field is required.",
              };
            }
            let pattern = /^([1-9]\d*|0)(\.\d+)?$/;
            if (rowData.increase !== undefined && rowData.increase !== "") {
              if (rowData.value.indexOf(",") > -1) {
                let v = rowData.value.split(",");
                for (let i = 0; i < v.length; i++) {
                  if (pattern.test(v[i]) === false) {
                    return {
                      isValid: false,
                      helperText: "Only numbers can be set.",
                    };
                  }
                }
              }
            }
            return { isValid: true };
          },
        },
        {
          title: "Increase",
          field: "increase",
          type: "numeric",
        },
      ],
      parameterData: this.props.parameterData,
      columnDialogOpen: false,
      columnsList: [],
      editedColumns: {},
      deletedColumns: [],
    };
  }

  /**
   * ファイル選択ダイアログでファイルが選択された時の処理
   *
   * @param {*} e 選択ファイルオブジェクト
   * @memberof GenerateConcreteScenario
   */
  handleFileSelect = (e) => {
    var fileData = e.target.files[0];

    if (
      fileData.type === ".csv" ||
      fileData.type === "application/vnd.ms-excel" ||
      fileData.type === "text/csv"
    ) {
    } else {
      alert("File Type Error.");
      return;
    }

    var reader = new FileReader();
    reader.onload = () => {
      let cols = reader.result.replace(/\r\n?/g, "\n").split("\n");
      let data = [];
      let col = cols[0].split(",");
      let columns = [];
      for (let i = 0; i < col.length; i++) {
        columns.push({ title: col[i], field: col[i] });
      }

      this.setState({ tableColumn: columns });

      for (var i = 1; i < cols.length; i++) {
        if (cols[i].length > 0) {
          let row = {};
          let values = cols[i].split(",");
          for (let j = 0; j < columns.length; j++) {
            row[columns[j].field] =
              Number.isNaN(parseFloat(values[j])) === true
                ? values[j]
                : parseFloat(values[j]);
          }
          data.push(row);
        }
      }

      this.setState({ tableData: data });
    };
    reader.readAsText(fileData);
  };

  /**
   * Concrete ScenarioのGidの変更時の処理
   *
   * @param {*} e イベントオブジェクト
   * @memberof GenerateConcreteScenario
   */
  handleGidChanged = (e) => {
    this.setState({ concreteGid: e.target.value });
  };

  /**
   * Concrete Scenarioの自動生成処理
   *
   * @param {*} id Logical Scenario id
   * @param {*} gid Logical Scenario gid
   * @param {*} used_date Logical Scenario used_date
   * @param {*} editValue Logical Scenarioのシナリオ
   * @return {*}
   * @memberof GenerateConcreteScenario
   */
  generateScenario(id, gid, used_date, editValue) {
    let allRows = [];
    let postData = {};
    let params = {};
    let allParamList = [];
    for (let i = 0; i < this.state.tableData.length; i++) {
      let row = {};
      this.state.tableColumn.forEach(
        (v, index) =>
          (row[v.field] =
            Number.isFinite(this.state.tableData[i][v.field]) === true
              ? Number(this.state.tableData[i][v.field])
              : this.state.tableData[i][v.field])
      );
      row["rowUsed"] =
        this.state.tableData[i].tableData["checked"] === undefined ||
        this.state.tableData[i].tableData["checked"] === false
          ? false
          : true;
      allRows.push(row);
    }

    if (allRows.filter((d) => d.rowUsed === true).length === 0) {
      return "";
    }

    for (let i = 0; i < this.state.parameterData.length; i++) {
      let paramData = {};
      for (let j = 0; j < this.state.parameterColumn.length; j++) {
        paramData[this.state.parameterColumn[j].field] =
          this.state.parameterData[i][this.state.parameterColumn[j].field] ===
          undefined
            ? ""
            : this.state.parameterData[i][this.state.parameterColumn[j].field];
      }
      allParamList.push(paramData);
    }

    postData.logicalScenario = editValue;
    postData.parameterList = JSON.stringify(allRows);
    postData.parameterDefinition = JSON.stringify(allParamList);
    params.id = id;
    params.used_date = used_date;
    params.concreteGid = this.state.concreteGid;
    params.logicalGid = gid;
    const queryParams = new URLSearchParams(params);
    let url = `/scenario_editor/api/autoGenerateConcreteScenario?${queryParams}`;
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=utf-8" },
      body: JSON.stringify(postData),
    };

    return fetch(url, requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((logicalId) => {
        return logicalId;
      });
  }

  /**
   * Parameter List 生成ボタン押下時の処理
   *
   * @memberof GenerateConcreteScenario
   */
  handleGenerateParameter = () => {
    let allData = [];
    for (let i = 0; i < this.state.parameterData.length; i++) {
      if (this.state.parameterData[i]["value"].indexOf(",") > -1) {
        if (
          this.state.parameterData[i]["increase"] === undefined ||
          this.state.parameterData[i]["increase"] === null ||
          this.state.parameterData[i]["increase"] === ""
        ) {
          allData.push(this.state.parameterData[i]["value"].split(","));
        } else {
          let rangeArray = this.state.parameterData[i]["value"].split(",");
          let cur = parseFloat(rangeArray[0]);
          let end = parseFloat(rangeArray[1]);
          let inc = parseFloat(this.state.parameterData[i]["increase"]);
          let incArray = [];
          while (cur <= end) {
            incArray.push(cur);
            cur = cur + inc;
          }
          allData.push(incArray);
        }
      } else {
        allData.push(this.state.parameterData[i]["value"].split(","));
      }
    }

    let allParameter = [...allData].reduce((a1, a2) => {
      if (a1.length === 0) {
        return a2;
      }
      return a1.reduce((arr, v1) => {
        a2.forEach((v2) => {
          const g = [].concat(v1, v2);
          arr.push(g);
        });
        return arr;
      }, []);
    }, []);

    let cols = [];
    let rows = [];
    for (let i = 0; i < this.state.parameterData.length; i++) {
      cols.push({
        title: this.state.parameterData[i]["name"],
        field: this.state.parameterData[i]["name"],
      });
    }
    this.setState({ tableColumn: [...cols] });

    if (Array.isArray(allParameter[0]) === true) {
      for (let i = 0; i < allParameter.length; i++) {
        let row = {};
        for (let j = 0; j < allParameter[i].length; j++) {
          row[this.state.parameterData[j]["name"]] = allParameter[i][j];
        }
        rows.push(row);
      }
    } else {
      for (let i = 0; i < allParameter.length; i++) {
        let row = {};
        row[this.state.parameterData[0]["name"]] = allParameter[i];
        rows.push(row);
      }
    }
    this.setState({ tableData: [...rows] });
  };

  /**
   * Parameter Listの列名変更ボタンの押下処理
   *
   * @memberof GenerateConcreteScenario
   */
  handleColumns = () => {
    let col = [];
    for (let i = 0; i < this.state.tableColumn.length; i++) {
      col.push({ name: this.state.tableColumn[i]["title"] });
    }
    this.setState({ columnsList: [...col] });
    this.setState({ editedColumns: {} });
    this.setState({ deletedColumns: [] });
    this.setState({ columnDialogOpen: true });
  };

  /**
   * 列名変更ダイアログのキャンセルボタン押下処理
   *
   * @param {*} e
   * @memberof GenerateConcreteScenario
   */
  handleCancel = (e) => {
    this.setState({ columnDialogOpen: false });
  };

  /**
   * 列名変更ダイアログのOKボタン押下処理
   *
   * @param {*} e
   * @memberof GenerateConcreteScenario
   */
  handleOk = (e) => {
    let col = [];
    for (let i = 0; i < this.state.columnsList.length; i++) {
      col.push({
        title: this.state.columnsList[i]["name"],
        field: this.state.columnsList[i]["name"],
      });
    }
    this.setState({ tableColumn: [...col] });

    if (this.state.editedColumns !== {}) {
      let rows = this.state.tableData;
      for (let j = 0; j < this.state.tableData.length; j++) {
        const keyValues = Object.keys(this.state.tableData[j]).map((key) => {
          const newKey = this.state.editedColumns[key] || key;
          return { [newKey]: this.state.tableData[j][key] };
        });
        rows[j] = Object.assign({}, ...keyValues);
      }
      this.setState({ tableData: rows });
    }

    for (let i = 0; i < this.state.deletedColumns.length; i++) {
      for (let j = 0; j < this.state.tableData.length; j++) {
        delete this.state.tableData[j][this.state.deletedColumns[i]];
      }
    }

    this.setState({ columnDialogOpen: false });
  };

  render() {
    return (
      <div
        style={{
          height: "89vh",
          width: "100%",
          maxWidth: "50%",
          backgroundColor: "white",
          padding: "5px",
          overflow: "scroll",
        }}
      >
        <div style={{ marginBottom: "10px" }}>
          <TextField
            id="gid"
            label="gid"
            fullWidth
            required
            value={this.state.concreteGid}
            onChange={this.handleGidChanged}
          />
        </div>
        <div style={{ marginBottom: "30px" }}>
          <MaterialTable
            title="Parameter Definition"
            icons={Icons}
            columns={this.state.parameterColumn}
            data={this.state.parameterData}
            options={{
              selection: false,
              sorting: true,
              showTextRowsSelected: false,
              search: false,
              filtering: false,
              headerStyle: { backgroundColor: "#dfdfdf" },
              padding: "dense",
              paging: false,
            }}
            editable={{
              onRowAdd: (newData) =>
                new Promise((resolve, reject) => {
                  this.setState({
                    parameterData: [...this.state.parameterData, newData],
                  });
                  resolve();
                }),
              onRowUpdate: (newData, oldData) =>
                new Promise((resolve, reject) => {
                  const dataUpdate = [...this.state.parameterData];
                  const index = oldData.tableData.id;
                  dataUpdate[index] = newData;
                  this.setState({ parameterData: [...dataUpdate] });
                  resolve();
                }),
              onRowDelete: (oldData) =>
                new Promise((resolve, reject) => {
                  const dataDelete = [...this.state.parameterData];
                  const index = oldData.tableData.id;
                  dataDelete.splice(index, 1);
                  this.setState({ parameterData: [...dataDelete] });
                  resolve();
                }),
            }}
          />
        </div>
        <div style={{ marginBottom: "30px", textAlign: "center" }}>
          <Button
            variant="outlined"
            color="primary"
            size="small"
            startIcon={<Icons.ArrowDownward />}
            endIcon={<Icons.ArrowDownward />}
            onClick={this.handleGenerateParameter}
          >
            Genarate Parameter
          </Button>
        </div>
        <div>
          <MaterialTable
            title="Parameter List"
            tableRef={this.tableRef}
            icons={Icons}
            columns={this.state.tableColumn}
            data={this.state.tableData}
            options={{
              selection: true,
              sorting: true,
              showTextRowsSelected: false,
              search: true,
              filtering: true,
              headerStyle: { backgroundColor: "#dfdfdf" },
              exportButton: true,
              exportAllData: true,
              exportFileName: "ExportParameterList",
              padding: "dense",
              paging: false,
            }}
            editable={{
              onRowAdd: (newData) =>
                new Promise((resolve, reject) => {
                  this.setState({
                    tableData: [...this.state.tableData, newData],
                  });
                  resolve();
                }),
              onRowUpdate: (newData, oldData) =>
                new Promise((resolve, reject) => {
                  const dataUpdate = [...this.state.tableData];
                  const index = oldData.tableData.id;
                  dataUpdate[index] = newData;
                  this.setState({ tableData: [...dataUpdate] });
                  resolve();
                }),
              onRowDelete: (oldData) =>
                new Promise((resolve, reject) => {
                  const dataDelete = [...this.state.tableData];
                  const index = oldData.tableData.id;
                  dataDelete.splice(index, 1);
                  this.setState({ tableData: [...dataDelete] });
                  resolve();
                }),
            }}
            components={{
              Toolbar: (props) => (
                <div style={{ marginBottom: "10px", marginLeft: "5px" }}>
                  <MTableToolbar {...props} />
                  <div style={{ textAlign: "left" }}>
                    <label
                      style={{
                        color: "white",
                        backgroundColor: "#3f51b5",
                        paddingLeft: "15px",
                        paddingRight: "15px",
                        paddingTop: "5px",
                        paddingBottom: "5px",
                        borderRadius: "4px",
                        cursor: "pointer",
                      }}
                    >
                      File...
                      <Input
                        type="file"
                        id="files"
                        name="files[]"
                        multiple
                        style={{ display: "none" }}
                        accept=".csv"
                        onChange={this.handleFileSelect}
                      ></Input>
                    </label>
                    <Button
                      variant="contained"
                      color="primary"
                      size="small"
                      style={{
                        marginBottom: "6px",
                        marginLeft: "8px",
                      }}
                      onClick={this.handleColumns}
                    >
                      Columns...
                    </Button>
                  </div>
                </div>
              ),
            }}
          />
        </div>
        <Dialog
          disableBackdropClick
          disableEscapeKeyDown
          open={this.state.columnDialogOpen}
          fullWidth
        >
          <DialogContent>
            <MaterialTable
              title="Columns List"
              icons={Icons}
              columns={[{ title: "Column Name", field: "name" }]}
              data={this.state.columnsList}
              options={{
                selection: false,
                sorting: false,
                showTextRowsSelected: false,
                search: false,
                filtering: false,
                headerStyle: { backgroundColor: "#dfdfdf" },
                padding: "dense",
                paging: false,
              }}
              editable={{
                onRowAdd: (newData) =>
                  new Promise((resolve, reject) => {
                    this.setState({
                      columnsList: [...this.state.columnsList, newData],
                    });
                    resolve();
                  }),
                onRowUpdate: (newData, oldData) =>
                  new Promise((resolve, reject) => {
                    const dataUpdate = [...this.state.columnsList];
                    const index = oldData.tableData.id;
                    dataUpdate[index] = newData;
                    const edited = this.state.editedColumns;
                    edited[oldData["name"]] = newData["name"];
                    this.setState({ editedColumns: edited });
                    this.setState({ columnsList: [...dataUpdate] });
                    resolve();
                  }),
                onRowDelete: (oldData) =>
                  new Promise((resolve, reject) => {
                    const dataDelete = [...this.state.columnsList];
                    const index = oldData.tableData.id;
                    dataDelete.splice(index, 1);
                    this.setState({
                      deletedColumns: [
                        [...this.state.deletedColumns],
                        oldData["name"],
                      ],
                    });
                    this.setState({ columnsList: [...dataDelete] });
                    resolve();
                  }),
              }}
            />
          </DialogContent>
          <DialogActions>
            <Button
              onClick={this.handleOk}
              size="small"
              variant="contained"
              color="primary"
            >
              Ok
            </Button>
            <Button
              autoFocus
              onClick={this.handleCancel}
              size="small"
              variant="contained"
              color="primary"
            >
              Cancel
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

export default GenerateConcreteScenario;
