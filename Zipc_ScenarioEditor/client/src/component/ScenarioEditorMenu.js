import React from "react";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import ScenarioEditor from "./ScenarioEditor";
import { TextField, withStyles } from "@material-ui/core";
import { Box, Typography, ListItemIcon } from "@material-ui/core";
import NoteAddIcon from "@material-ui/icons/NoteAdd";
import SaveIcon from "@material-ui/icons/Save";
import CloseIcon from "@material-ui/icons/Close";
import DescriptionIcon from "@material-ui/icons/Description";

import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";

import * as Template from "./template";
import InputAdornment from "@material-ui/core/InputAdornment";

import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

import { Dialog } from "@material-ui/core";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

const useStyles = (theme) => ({
  root: {
    width: "100%",
    backgroundColor: "rgb(222,235,247)",
  },
  functional: {
    width: "100%",
    backgroundColor: "rgb(222,235,247)",
  },
  logical: {
    width: "100%",
    backgroundColor: "rgb(226,240,217)",
  },
  concrete: {
    width: "100%",
    backgroundColor: "rgb(255,242,204)",
  },
  dialog: {
    position: "absolute",
    width: "30%",
    height: "70px",
    top: "40%",
    left: "30%",
    backgroundColor: "rgb(222,235,247)",
    border: "2px solid #000",
    padding: theme.spacing(2, 4, 3),
  },
  buttonSpace: {
    margin: theme.spacing(1),
    width: 100,
  },
});

class ScenarioEditorMenu extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      anchorFileEl: null,
      showSearch: false,
      showNew: true,
      showCreateScenario: false, // Create Logical (or Concrete) Scenario メニュー選択時にTrue
      editorValue: props.editorValue,
      originalEditorValue: props.editorValue,
      id: props.id,
      gid: props.gid,
      defaultGid: props.gid,
      functionalId: [],
      logicalId: [],
      concreteId: [],
      type: props.type,
      functional: Template.FUNCTIONAL_TEMPLATE,
      logical: Template.LOGICAL_TEMPLATE,
      concrete: Template.CONCRETE_TEMPLATE,
      classesName: props.classes.root,
      propertyType: "",
      stateType: "",
      showGenerate: false,
      showInfo: false,
      showWarning: false,
      title: "",
      editorGid: "", // エディタで入力されているシナリオのGID
      editorTitle: "", // エディタで入力されているシナリオのタイトル
      used_date: props.used_date,
    };
    this.ScenarioEditorRef = React.createRef();
  }

  classes = this.props.classes;

  name = this.classes.root;

  handleFileClick = (event) => {
    this.setState({ anchorFileEl: event.currentTarget });
  };

  handleFileClose = () => this.setState({ anchorFileEl: null });

  handleClose = () => {
    this.setState({ anchorFileEl: null });
    this.setState({ editorValue: "" });
    this.props.onClose();
  };

  handleCloseGenerate = () => {
    this.setState({ showGenerate: false });
  };

  handleChangeEditorData = (value) => {
    this.setState({ editorValue: value });
  };

  // Create Logical(or Concrete) Scenario メニュー選択時
  handleNewScenario = () => {
    if (this.checkModify()) {
      this.setState({ showWarning: true });
      this.setState({ title: "Data have been modified! Please save first." });
      this.setState({ anchorFileEl: null });
    } else {
      var scenario = JSON.parse(this.state.editorValue);
      this.setState({ showCreateScenario: true });
      this.setState({ editorTitle: scenario.title });
      this.setState({ anchorFileEl: null });
    }
  };

  // Create画面 の GID変更時
  setDialogGID = (event) => this.setState({ editorGid: event.target.value });

  checkDialogGID = () => {
    return this.state.editorGid;
  };

  // Create画面 の タイトル変更時
  setDialogTitle = (event) =>
    this.setState({ editorTitle: event.target.value });

  handleCreateScenarioOK = () => {
    // Logical Scenarioの作成
    if (this.state.type === "functional") {
      let scenario = JSON.parse(
        this.state.editorValue.slice(0, -2) + Template.LOGICAL_TEMPLATE
      );
      scenario.title = this.state.editorTitle;
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json; charset=utf-8" },
        body: JSON.stringify(scenario),
      };
      fetch(
        "/scenario_editor/api/saveLogicalScenario?fs_id=" +
          this.state.id +
          "&ls_gid=" +
          this.state.editorGid,
        requestOptions
      )
        .then((response) => response.json())
        .then((resId) => {
          this.setState({
            id: resId,
            gid: this.state.editorGid,
            defaultGid: this.state.editorGid,
            editorGid: "",
            editorTitle: "",
            showCreateScenario: false,
            showSearch: false,
            showNew: true,
            anchorFileEl: null,
            type: "logical",
            editorValue: JSON.stringify(scenario, undefined, 4),
          });
          this.name = this.classes.logical;
        });
    }
    // Concrete Scenarioの作成
    else if (this.state.type === "logical") {
      let scenario = JSON.parse(
        this.state.editorValue.slice(0, -2) + Template.CONCRETE_TEMPLATE
      );
      scenario.title = this.state.editorTitle;
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json; charset=utf-8" },
        body: JSON.stringify(scenario),
      };
      fetch(
        "/scenario_editor/api/saveConcreteScenario?ls_id=" +
          this.state.id +
          "&cs_gid=" +
          this.state.editorGid,
        requestOptions
      )
        .then((response) => response.json())
        .then((resId) => {
          this.setState({
            id: resId,
            gid: this.state.editorGid,
            defaultGid: this.state.editorGid,
            editorGid: "",
            editorTitle: "",
            showCreateScenario: false,
            showSearch: false,
            showNew: true,
            anchorFileEl: null,
            type: "concrete",
            editorValue: JSON.stringify(scenario, undefined, 4),
          });
          this.name = this.classes.concrete;
        });
    }
  };

  handleCreateScenarioClose = () => {
    this.setState({ editorGid: "" });
    this.setState({ showCreateScenario: false });
  };

  handleGenerateConcrete = () => {
    this.setState({ showSearch: false });
    this.setState({ showNew: true });
    this.setState({ anchorFileEl: null });
    var jsonObject = JSON.parse(this.state.editorValue);
    var keys = Object.keys(jsonObject);
    if (this.checkFieldName(jsonObject, keys) === null) {
      this.setState({ showWarning: true });
      this.setState({
        title: "Can't saved scenario. FieldName can't start with '$'",
      });
    } else {
      this.setState({
        originalEditorValue: JSON.parse(this.state.editorValue),
      });
      try {
        this.ScenarioEditorRef.current
          .generateScenario(
            this.state.id,
            this.state.gid,
            this.state.used_date,
            this.state.editorValue
          )
          .then((newId) => {
            if (newId !== "") {
              this.setState({ logicalId: newId });
              this.setState({ showInfo: true });
              this.setState({
                title: "Successfully Generate Concrete Scenario",
              });
              this.setState({ defaultGid: this.state.gid });
            } else {
              this.setState({ showWarning: true });
              this.setState({
                title: "No selected parameters.",
              });
            }
          });
      } catch (e) {
        this.setState({ showWarning: true });
        this.setState({
          title: "Concrete Scenario Genarate Error.",
        });
      }
    }
  };

  handleInformationDialgoClose = () => {
    this.setState({ showWarning: false });
    this.setState({ showInfo: false });
    this.setState({ title: "" });
  };

  checkFieldName = (jsonObject, keys) => {
    for (var property in keys) {
      if (jsonObject[keys[property]] instanceof Array) {
        if (keys[property].startsWith("$")) {
          return null;
        }
        for (var count in jsonObject[keys[property]]) {
          var item = jsonObject[keys[property]][count];
          var key = Object.keys(jsonObject[keys[property]][count]);
          if (this.checkFieldName(item, key) === null) {
            return null;
          }
        }
      } else if (jsonObject[keys[property]] instanceof Object) {
        if (keys[property].startsWith("$")) {
          return null;
        }
        let key = Object.keys(jsonObject[keys[property]]);
        if (this.checkFieldName(jsonObject[keys[property]], key) === null) {
          return null;
        }
      } else {
        if (keys[property].startsWith("$")) {
          return null;
        }
      }
    }
  };

  checkModify = () => {
    var jsonObject = JSON.parse(this.state.editorValue);
    if (
      JSON.stringify(this.state.originalEditorValue) ===
      JSON.stringify(jsonObject)
    ) {
      return false;
    } else {
      return true;
    }
  };

  handleSave = () => {
    this.setState({ showSearch: false });
    this.setState({ showNew: true });
    this.setState({ anchorFileEl: null });
    var jsonObject = JSON.parse(this.state.editorValue);
    var keys = Object.keys(jsonObject);
    if (this.checkFieldName(jsonObject, keys) === null) {
      this.setState({ showWarning: true });
      this.setState({
        title: "Can't saved scenario. FieldName can't start with '$'",
      });
    } else {
      this.setState({
        originalEditorValue: JSON.parse(this.state.editorValue),
      });
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json; charset=utf-8" },
        body: this.state.editorValue,
      };

      if (this.state.type === "functional") {
        fetch(
          "/scenario_editor/api/updateFunctionalScenario?id=" +
            this.state.id +
            "&gid=" +
            this.state.gid,
          requestOptions
        )
          .then((response) => response.json())
          .then((functionalId) => {
            this.setState({ functionalId });
            this.setState({ showInfo: true });
            this.setState({
              title: "Successfully Updated Functional Scenario",
            });
            this.setState({ defaultGid: this.state.gid });
          });
      } else if (this.state.type === "logical") {
        fetch(
          "/scenario_editor/api/updateLogicalScenario?id=" +
            this.state.id +
            "&gid=" +
            this.state.gid,
          requestOptions
        )
          .then((response) => response.json())
          .then((logicalId) => {
            this.setState({ logicalId });
            this.setState({ showInfo: true });
            this.setState({ title: "Successfully Updated Logical Scenario" });
            this.setState({ defaultGid: this.state.gid });
          });
      } else if (this.state.type === "concrete") {
        fetch(
          "/scenario_editor/api/updateConcreteScenario?id=" +
            this.state.id +
            "&gid=" +
            this.state.gid,
          requestOptions
        )
          .then((response) => response.json())
          .then((concreteId) => {
            this.setState({ concreteId });
            this.setState({ showInfo: true });
            this.setState({ title: "Successfully Updated Concrete Scenario" });
            this.setState({ defaultGid: this.state.gid });
          });
      }
    }
  };

  async componentDidUpdate() {
    var { type, classes } = this.props;
    if (
      this.state.propertyType !== type ||
      this.state.stateType !== this.state.type
    ) {
      var changeType = "";
      if (this.state.propertyType !== type) {
        changeType = type;
      } else {
        changeType = this.state.type;
      }

      if (changeType === "functional") {
        this.name = classes.functional;
      } else if (changeType === "logical") {
        this.name = classes.logical;
      } else if (changeType === "concrete") {
        this.name = classes.concrete;
      }
    }
  }

  componentDidMount() {
    var { type, classes } = this.props;
    this.savedId = this.props.id;

    if (type === "functional") {
      this.setState({ className: classes.functional });
      this.setState({ propertyType: type });
      this.setState({ stateType: type });
      this.name = classes.functional;
    } else if (type === "logical") {
      this.setState({ className: classes.logical });
      this.setState({ propertyType: type });
      this.setState({ stateType: type });
      this.name = classes.logical;
    } else if (type === "concrete") {
      this.setState({ className: classes.concrete });
      this.setState({ propertyType: type });
      this.setState({ stateType: type });
      this.name = classes.concrete;
    }
  }

  setGID = (event) => {
    this.setState({ gid: event.target.value });
  };

  render() {
    const { anchorFileEl } = this.state;
    var { classes } = this.props;

    return (
      <div>
        <AppBar position="static">
          <Toolbar variant="dense">
            <IconButton
              edge="start"
              aria-label="menu"
              color="inherit"
              onClick={this.handleFileClick}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" color="inherit" style={{ flex: 1 }}>
              {this.state.type === "functional"
                ? "Functional Scenario Editor"
                : this.state.type === "logical"
                ? "Logical Scenario Editor"
                : this.state.type === "concrete"
                ? "Concrete Scenario Editor"
                : ""}
            </Typography>
            <Button
              aria-label="close"
              color="inherit"
              onClick={this.handleClose}
            >
              <CloseIcon />
            </Button>
          </Toolbar>
        </AppBar>
        <div className={this.name}>
          <TextField
            margin="dense"
            id="gid"
            type="text"
            onChange={this.setGID}
            value={this.state.gid}
            defaultValue={this.props.gid}
            style={{ width: 400, paddingLeft: 10 }}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">GID：</InputAdornment>
              ),
            }}
          />
        </div>

        {/* 画面上部の MenuIcon 押下時に表示されるメニュー */}
        <Menu
          id="simple-menu"
          anchorEl={anchorFileEl}
          keepMounted
          open={Boolean(anchorFileEl)}
          onClose={this.handleFileClose}
        >
          <Box clone display={this.state.type === "functional" ? "" : "none"}>
            <MenuItem onClick={this.handleNewScenario}>
              <ListItemIcon>
                <NoteAddIcon />
              </ListItemIcon>
              Create Logical Scenario
            </MenuItem>
          </Box>
          <Box clone display={this.state.type === "logical" ? "" : "none"}>
            <MenuItem
              onClick={this.handleNewScenario}
              disabled={this.state.type !== "logical"}
            >
              <ListItemIcon>
                <NoteAddIcon />
              </ListItemIcon>
              Create Concrete Scenario
            </MenuItem>
          </Box>
          {/* Generate Concrete Scenarioは資料変更可能 */}
          <Box clone display={this.state.type === "logical" ? "" : "none"}>
            <MenuItem
              onClick={this.handleGenerateConcrete}
              disabled={this.state.type !== "logical"}
            >
              <ListItemIcon>
                <DescriptionIcon />
              </ListItemIcon>
              Generate Concrete Scenario
            </MenuItem>
          </Box>
          <Box
            clone
            display={
              this.state.used_date === undefined || this.state.used_date === ""
                ? ""
                : "none"
            }
          >
            <MenuItem onClick={this.handleSave} disabled={!this.state.showNew}>
              <ListItemIcon>
                <SaveIcon />
              </ListItemIcon>
              Save
            </MenuItem>
          </Box>
          <MenuItem onClick={this.handleClose}>
            <ListItemIcon>
              <CloseIcon />
            </ListItemIcon>
            Close
          </MenuItem>
        </Menu>
        <ScenarioEditor
          show={this.state.showNew}
          type={this.state.type}
          editorValue={this.state.editorValue}
          id={this.state.id}
          gid={this.state.gid}
          used_date={this.state.used_date}
          onChangeEditorData={this.handleChangeEditorData}
          ref={this.ScenarioEditorRef}
        ></ScenarioEditor>

        {/* Saveメニュー押下時に表示されるダイアログ */}
        <Dialog
          open={this.state.showInfo}
          onClose={this.handleInformationDialgoClose}
          aria-labelledby="scenario-save-title"
          disableBackdropClick
          disableEscapeKeyDown
          fullWidth
        >
          <DialogTitle
            id="create-scenario"
            style={{ paddingTop: 0, paddingLeft: 0, paddingRight: 0 }}
          >
            <AppBar position="static">
              <Toolbar variant="dense">
                <Typography variant="h6" color="inherit" style={{ flex: 1 }}>
                  Saved{" "}
                  {this.state.type.charAt(0).toUpperCase() +
                    this.state.type.slice(1).toLowerCase()}{" "}
                  Scenario
                </Typography>
                <Button
                  aria-label="close"
                  color="inherit"
                  onClick={this.handleInformationDialgoClose}
                >
                  <CloseIcon />
                </Button>
              </Toolbar>
            </AppBar>
          </DialogTitle>
          <DialogContent>{this.state.title}</DialogContent>
          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              className={classes.buttonSpace}
              variant="contained"
              aria-controls="simple-menu"
              color="primary"
              onClick={this.handleInformationDialgoClose}
            >
              OK
            </Button>
          </DialogActions>
        </Dialog>

        {/* エラーの時に表示されるダイアログ */}
        <Dialog
          open={this.state.showWarning}
          onClose={this.handleInformationDialgoClose}
          aria-labelledby="scenario-warning-title"
          disableBackdropClick
          disableEscapeKeyDown
          fullWidth
        >
          <DialogTitle
            id="create-scenario"
            style={{ paddingTop: 0, paddingLeft: 0, paddingRight: 0 }}
          >
            <AppBar position="static">
              <Toolbar variant="dense">
                <Typography variant="h6" color="inherit" style={{ flex: 1 }}>
                  Warning
                </Typography>
                <Button
                  aria-label="close"
                  color="inherit"
                  onClick={this.handleInformationDialgoClose}
                >
                  <CloseIcon />
                </Button>
              </Toolbar>
            </AppBar>
          </DialogTitle>
          <DialogContent>{this.state.title}</DialogContent>
          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              className={classes.buttonSpace}
              variant="contained"
              aria-controls="simple-menu"
              color="primary"
              onClick={this.handleInformationDialgoClose}
            >
              OK
            </Button>
          </DialogActions>
        </Dialog>

        {/* Create Logical (or Concrete) Scenario 押下時に表示されるメニュー */}
        <Dialog
          open={this.state.showCreateScenario}
          aria-labelledby="create-scenario"
          disableBackdropClick
          disableEscapeKeyDown
          fullWidth
          maxWidth="md"
        >
          <DialogTitle id="create-scenario" style={{ padding: 0 }}>
            <AppBar position="static">
              <Toolbar variant="dense">
                <Typography variant="h6" color="inherit" style={{ flex: 1 }}>
                  {this.state.type === "functional"
                    ? "Create Logical Scenario"
                    : this.state.type === "logical"
                    ? "Create Concrete Scenario"
                    : ""}
                </Typography>
                <Button
                  aria-label="close"
                  color="inherit"
                  onClick={this.handleCreateScenarioClose}
                >
                  <CloseIcon />
                </Button>
              </Toolbar>
            </AppBar>
          </DialogTitle>
          <DialogContent>
            <TextField
              autoFocus
              margin="dense"
              id="createGID"
              label="GID"
              type="text"
              onChange={this.setDialogGID}
              fullWidth
            />
            <TextField
              margin="dense"
              id="createTitle"
              label="Title"
              type="text"
              onChange={this.setDialogTitle}
              defaultValue={this.state.editorTitle}
              fullWidth
            />
          </DialogContent>
          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              disabled={this.checkDialogGID() === ""}
              className={classes.buttonSpace}
              aria-controls="simple-menu"
              variant="contained"
              color="primary"
              onClick={this.handleCreateScenarioOK}
            >
              OK
            </Button>
            <Button
              className={classes.buttonSpace}
              aria-controls="simple-menu"
              variant="contained"
              color="primary"
              onClick={this.handleCreateScenarioClose}
            >
              Cancel
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}
export default withStyles(useStyles, { withTheme: true })(ScenarioEditorMenu);
