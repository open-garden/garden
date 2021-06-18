/* TOPページ */
import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import Grid from "@material-ui/core/Grid";
import { Dialog, Typography } from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import Divider from "@material-ui/core/Divider";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import { TextField, ListItemIcon } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import { Box } from "@material-ui/core";

import AddIcon from "@material-ui/icons/Add";
import CloseIcon from "@material-ui/icons/Close";
import CreateIcon from "@material-ui/icons/Create";
import DeleteIcon from "@material-ui/icons/Delete";
import FilterListIcon from "@material-ui/icons/FilterList";
import OpenInNewIcon from "@material-ui/icons/OpenInNew";
import SearchIcon from "@material-ui/icons/Search";
import Warning from "@material-ui/icons/Warning";

import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

import * as Template from "./template";
import ScenarioDialog from "./ScenarioDialog";

import Pagination from "@material-ui/lab/Pagination";

const styles = (theme) => ({
  root: {
    //backgroundColor: theme.palette.background.paper
  },
  paper: {
    maxHeight: "88.5vh",
    padding: "10px",
  },
  content: {
    margin: "10px",
  },
  title: {
    padding: theme.spacing(2, 4, 3),
    align: "justify",
  },
  functional: {
    textAlign: "center",
    backgroundColor: "rgb(222,235,247)",
  },
  logical: {
    textAlign: "center",
    backgroundColor: "rgb(226,240,217)",
  },
  concrete: {
    textAlign: "center",
    backgroundColor: "rgb(255,242,204)",
  },
  buttonSpace: {
    margin: theme.spacing(1),
    width: 100,
  },
});

class Home extends React.Component {
  // コンストラクタ
  constructor() {
    super();
    this.state = {
      lists: [],
      functionX: null, // Functional Scenario List の右クリック位置
      functionY: null, // Functional Scenario List の右クリック位置
      logicalX: null, // Logical Scenario List の右クリック位置
      logicalY: null, // Logical Scenario List の右クリック位置
      concreteX: null, // Concrete Scenario List の右クリック位置
      concreteY: null, // Concrete Scenario List の右クリック位置
      originalFunctionalList: [], // MongoDBより取得したFunctional Scenario
      originalLogicalList: [], // MongoDBより取得したLogical Scenario
      originalConcreteList: [], // MongoDBより取得したConcrete Scenario
      type: "",
      functionalID: "", // 選択中の Functional Scenario の ObjectID
      functionalGID: "", // 選択中の Functional Scenario の GID
      functionalTitle: "", // 選択中の Functional Scenario の タイトル
      logicalID: "", // 選択中の Logical Scenario の ObjectID
      logicalGID: "", // 選択中の Logical Scenario の GID
      logicalTitle: "", // 選択中の Logical Scenario の タイトル
      concreteID: "", // 選択中の Concrete Scenario の ObjectID
      concreteGID: "", // 選択中の Concrete Scenario の GID
      concreteTitle: "", // 選択中の Concrete Scenario の タイトル
      functionalFilterValue: "", // Functional Scenarioのフィルタ条件（Logical）
      logicalFilterValue: "", // Logical Scenarioのフィルタ条件（Functional）
      logicalFilterValue2: "", // Logical Scenarioのフィルタ条件（Concrete）
      concreteFilterValue: "", // Concrete Scenarioのフィルタ条件（Logical）
      selectedData: "",
      showOpen: false,
      title: "",
      showDeleteDialog: false,
      selectedType: "",

      newFS: false, // Functional Scenarioの新規シナリオ作成フラグ
      newLS: false, // Logical Scenarioの新規シナリオ作成フラグ
      newCS: false, // Concrete Scenarioの新規シナリオ作成フラグ
      newFSGID: "", // Functional Scenarioの新規シナリオGID
      newLSGID: "", // Logical Scenarioの新規シナリオGID
      newCSGID: "", // Concrete Scenarioの新規シナリオGID
      newFSTitle: "", // Functional Scenarioの新規シナリオタイトル
      newLSTitle: "", // Logical Scenarioの新規シナリオタイトル
      newCSTitle: "", // Concrete Scenarioの新規シナリオタイトル

      showCreateLogical: false, // Logical Scenarioの作成フラグ（Create Logical Scenario メニュー）
      showCreateConcrete: false, // Concrete Scenarioの作成フラグ（Create Concrete Scenario メニュー）

      functionalPage: 1,
      logicalPage: 1,
      concretePage: 1,

      isDelete: false, // Scenarioを削除する際のフラグ

      functionalListCount: 0,
      logicalListCount: 0,
      concreteListCount: 0,
      // 改ページ単位
      pageBreak: 5,
      isAutoPageChange: false,

      used_date: "",
    };
    this.handleClearLogicalFilter = this.handleClearLogicalFilter.bind(this);
    this.handleClearConcreteFilter = this.handleClearConcreteFilter.bind(this);
    this.handleClearFunctionalFilter =
      this.handleClearFunctionalFilter.bind(this);
  }

  // コンポーネント生成時
  componentDidMount() {
    this.handleRefresh();
  }

  // Functional Scenario の Newボタンクリック時
  handleNewFS = (event) => {
    this.setState({ newFS: true });
    this.clearScenarioRecordInfo();
  };

  // Logical Scenario の Newボタンクリック時
  handleNewLS = (event) => {
    this.setState({ newLS: true });
    this.clearScenarioRecordInfo();
  };

  // Concrete Scenario の Newボタンクリック時
  handleNewCS = (event) => {
    this.setState({ newCS: true });
    this.clearScenarioRecordInfo();
  };

  // New画面 or Create画面 の GID変更時
  setDialogGID = (event) => {
    if (this.state.newFS) {
      this.setState({ newFSGID: event.target.value });
    } else if (this.state.newLS || this.state.showCreateLogical) {
      this.setState({ newLSGID: event.target.value });
    } else if (this.state.newCS || this.state.showCreateConcrete) {
      this.setState({ newCSGID: event.target.value });
    }
  };

  checkDialogGID = () => {
    if (this.state.newFS) {
      return this.state.newFSGID;
    } else if (this.state.newLS || this.state.showCreateLogical) {
      return this.state.newLSGID;
    } else if (this.state.newCS || this.state.showCreateConcrete) {
      return this.state.newCSGID;
    }
  };

  // New画面 or Create画面 の タイトル変更時
  setDialogTitle = (event) => {
    if (this.state.newFS) {
      this.setState({ newFSTitle: event.target.value });
    } else if (this.state.newLS || this.state.showCreateLogical) {
      this.setState({ newLSTitle: event.target.value });
    } else if (this.state.newCS || this.state.showCreateConcrete) {
      this.setState({ newCSTitle: event.target.value });
    }
  };

  checkDialogTitle = () => {
    if (this.state.newFS) {
      return this.state.newFSTitle;
    } else if (this.state.newLS || this.state.showCreateLogical) {
      return this.state.newLSTitle;
    } else if (this.state.newCS || this.state.showCreateConcrete) {
      return this.state.newCSTitle;
    }
  };

  // New画面 の OKクリック時
  handleNewOK = (event) => {
    event.preventDefault();

    // Functional Scenario の new
    if (this.state.newFS) {
      this.setState({ newFS: false });
      this.createFunctionalScenario();
    }
    // Logical Scenario の new
    else if (this.state.newLS) {
      this.setState({ newLS: false });
      this.createLogicalScenario("");
    }
    // Concrete Scenario の new
    else if (this.state.newCS) {
      this.setState({ newCS: false });
      this.createConcreteScenario("");
    }
  };

  // Newボタンキャンセル時
  handleNewClose = (event) => {
    this.setState({
      newFS: false,
      newLS: false,
      newCS: false,
      newFSGID: "",
      newLSGID: "",
      newCSGID: "",
      newFSTitle: "",
      newLSTitle: "",
      newCSTitle: "",
    });
  };

  // Scenario List 行選択時の情報をクリア
  clearScenarioRecordInfo = () => {
    this.setState({
      functionalID: "",
      functionalGID: "",
      functionalTitle: "",
      logicalID: "",
      logicalGID: "",
      logicalTitle: "",
      concreteID: "",
      concreteGID: "",
      concreteTitle: "",
    });
  };

  // Functional Scenario の作成（MongoDBへ登録）
  createFunctionalScenario = () => {
    var scenario = JSON.parse(Template.FUNCTIONAL_TEMPLATE);
    scenario.title = this.state.newFSTitle;
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=utf-8" },
      body: JSON.stringify(scenario),
    };
    fetch(
      "/scenario_editor/api/saveFunctionalScenario?fs_gid=" +
        this.state.newFSGID,
      requestOptions
    )
      .then((response) => response.json())
      .then((id) => {
        this.setState({ newFSGID: "", newFSTitle: "" });
        this.setState({ functionalPage: 1 });
        this.searchFunctionalScenario();
      });
  };

  // Logical Scenario の作成（MongoDBへ登録）
  createLogicalScenario = (fs_gid) => {
    var scenario = JSON.parse(Template.FUNCTIONAL_TEMPLATE);
    scenario.title = this.state.newLSTitle;
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=utf-8" },
      body: JSON.stringify(scenario),
    };
    fetch(
      "/scenario_editor/api/saveLogicalScenario?fs_id=" +
        this.state.functionalID +
        "&ls_gid=" +
        this.state.newLSGID,
      requestOptions
    )
      .then((response) => response.json())
      .then((id) => {
        this.setState({ newLSGID: "", newLSTitle: "" });
        this.getAllLogical();
      });
  };

  // Concrete Scenario の作成（MongoDBへ登録）
  createConcreteScenario = (ls_gid) => {
    var scenario = JSON.parse(Template.CONCRETE_TEMPLATE);
    scenario.title = this.state.newCSTitle;
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=utf-8" },
      body: JSON.stringify(scenario),
    };
    fetch(
      "/scenario_editor/api/saveConcreteScenario?ls_id=" +
        this.state.logicalID +
        "&cs_gid=" +
        this.state.newCSGID,
      requestOptions
    )
      .then((response) => response.json())
      .then((id) => {
        this.setState({ newCSGID: "", newCSTitle: "" });
        this.getAllConcrete();
      });
  };

  // Functional Scenario の Create Logical Scenario メニュー
  handleCreateLogicalScenario = () => {
    this.handleFunctionalClose();
    this.setState({
      showCreateLogical: true,
      selectedType: "functional",
    });
  };

  // Logical Scenario の Create Concrete Scenario メニュー
  handleCreateConcreteScenario = () => {
    this.handleLogicalClose();
    this.setState({
      showCreateConcrete: true,
      selectedType: "logical",
    });
  };

  // Create XXXX Scenario コンテキストメニュー の ダイアログ の OK処理
  handleCreateOK = () => {
    this.handleCreateClose();
    if (this.state.selectedType === "functional") {
      // タイトルが未入力の場合、Functionalのタイトルをデフォルトで使用する
      if (!this.state.newLSTitle) {
        this.setState({ newLSTitle: this.state.functionalTitle }, () => {
          this.createLogicalScenario(this.state.functionalGID);
        });
      } else {
        this.createLogicalScenario(this.state.functionalGID);
      }
    } else if (this.state.selectedType === "logical") {
      if (!this.state.newCSTitle) {
        this.setState({ newCSTitle: this.state.logicalTitle }, () => {
          this.createConcreteScenario(this.state.logicalGID);
        });
      } else {
        this.createConcreteScenario(this.state.logicalGID);
      }
    }
    this.setState({ selectedType: "" });
  };

  // Create XXXX Scenario コンテキストメニュー の ダイアログ の Close処理
  handleCreateClose = () => {
    this.setState({ showCreateLogical: false, showCreateConcrete: false });
  };

  // Functional Scenario List レコードの右クリック時
  handleFunctionClick = (id, gid, title) => (event) => {
    event.preventDefault();
    this.setState({
      functionalID: id,
      functionalGID: gid,
      functionalTitle: title,
      functionX: event.clientX - 2,
      functionY: event.clientY - 4,
    });
  };

  // Logical Scenario List レコードの右クリック時
  handleLogicalClick = (id, gid, title) => (event) => {
    event.preventDefault();
    this.setState({
      logicalID: id,
      logicalGID: gid,
      logicalTitle: title,
      logicalX: event.clientX - 2,
      logicalY: event.clientY - 4,
    });
  };

  // Concrete Scenario List レコードの右クリック時
  handleConcreteClick = (id, gid, title) => (event) => {
    event.preventDefault();
    this.setState({
      concreteID: id,
      concreteGID: gid,
      concreteTitle: title,
      concreteX: event.clientX - 2,
      concreteY: event.clientY - 4,
    });
  };

  // Open Scenario コンテキストメニュー 選択時の処理
  handleOpenScenario = () => {
    if (this.state.functionY != null) {
      this.handleFunctionalClose();
      let filterValue = this.state.originalFunctionalList.filter(
        (list) => list._id === this.state.functionalID
      );
      this.setState({
        showOpen: true,
        type: "functional",
        selectedData: filterValue,
      });
    } else if (this.state.logicalY !== null) {
      this.handleLogicalClose();
      let filterValue = this.state.originalLogicalList.filter(
        (list) => list._id === this.state.logicalID
      );
      this.setState({
        showOpen: true,
        type: "logical",
        used_date: filterValue.used_date,
        selectedData: filterValue,
      });
    } else if (this.state.concreteY !== null) {
      this.handleConcreteClose();
      let filterValue = this.state.originalConcreteList.filter(
        (list) => list._id === this.state.concreteID
      );
      this.setState({
        showOpen: true,
        type: "concrete",
        selectedData: filterValue,
      });
    }
  };

  // Delete Scenario コンテキストメニュー 選択時の処理
  handleDeleteScenario = () => {
    if (this.state.functionY != null) {
      this.handleFunctionalClose();
      this.setState({ selectedType: "functional" });
    } else if (this.state.logicalY !== null) {
      this.handleLogicalClose();
      this.setState({ selectedType: "logical" });
    } else if (this.state.concreteY !== null) {
      this.handleConcreteClose();
      this.setState({ selectedType: "concrete" });
    }
    this.setState({ showDeleteDialog: true });
  };

  // Delete Scenario コンテキストメニュー の ダイアログ の OK処理
  handleDeleteOK = () => {
    this.handleDeleteClose();
    this.setState({ isDelete: true });
    if (this.state.selectedType === "functional") {
      this.handleDeleteFunctionalScenario();
    } else if (this.state.selectedType === "logical") {
      this.handleDeleteLogicalScenario();
    } else if (this.state.selectedType === "concrete") {
      this.handleDeleteConcreteScenario();
    }
    this.setState({ selectedType: "" });
  };

  // Delete Scenario コンテキストメニュー の ダイアログ の Close処理
  handleDeleteClose = () => this.setState({ showDeleteDialog: false });

  // Functional Scenario の 削除処理をServerサイドに依頼
  handleDeleteFunctionalScenario = () => {
    this.handleFunctionalClose();
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: this.state.functionalID,
    };
    fetch("/scenario_editor/api/deleteFunctionalScenario", requestOptions)
      .then((response) => response.json())
      .then((id) => {
        this.getAllFunctional();
      });
  };

  // Logical Scenario の 削除処理をServerサイドに依頼
  handleDeleteLogicalScenario = () => {
    this.handleLogicalClose();
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: this.state.logicalID,
    };

    fetch("/scenario_editor/api/deleteLogicalScenario", requestOptions)
      .then((response) => response.json())
      .then((id) => {
        this.getAllLogical();
      });
  };

  // Concrete Scenario の 削除処理をServerサイドに依頼
  handleDeleteConcreteScenario = () => {
    this.handleConcreteClose();

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: this.state.concreteID,
    };

    fetch("/scenario_editor/api/deleteConcreteScenario", requestOptions)
      .then((response) => response.json())
      .then((id) => {
        this.getAllConcrete();
      });
  };

  // Scenario編集画面 クローズ時
  handleCloseScenario = () => {
    this.setState({ showOpen: false });
    this.setState({ type: "" });
    this.handleRefresh();
  };

  // Functional Scenario コンテキストメニュー クローズ時
  handleFunctionalClose = () => {
    this.setState({
      functionY: null,
      functionX: null,
    });
  };

  // Logical Scenario コンテキストメニュー クローズ時
  handleLogicalClose = () => {
    this.setState({
      logicalY: null,
      logicalX: null,
    });
  };

  // Concrete Scenario コンテキストメニュー クローズ時
  handleConcreteClose = () => {
    this.setState({
      concreteY: null,
      concreteX: null,
    });
  };

  // Logical Scenario コンテキストメニュー（Set Filter For Functional Scenario）
  handleFilterFunctionalScenario = () => {
    this.handleLogicalClose();
    this.setState({ functionalFilterValue: this.state.logicalGID });
  };

  // Functional Scenario と Concrete Scenario の コンテキストメニュー（Set Filter For Logical Scenario）
  handleFilterLogicalScenario = () => {
    if (this.state.functionY != null) {
      this.handleFunctionalClose();
      this.setState({ logicalFilterValue: this.state.functionalGID });
    } else if (this.state.concreteY != null) {
      this.handleConcreteClose();
      this.setState({ logicalFilterValue2: this.state.concreteGID });
    }
  };

  // Logical Scenario コンテキストメニュー（Set Filter For Concrete Scenario）
  handleFilterConcreteScenario = () => {
    this.handleLogicalClose();
    this.setState({ concreteFilterValue: this.state.logicalGID });
  };

  // Searchボタン（Functional Scenario）
  searchFunctionalScenario = () => {
    // 検索の結果は１ページ から表示、削除した結果は最後のページから表示
    if (!this.state.isDelete) {
      this.getFunctionalListByRange(
        0,
        this.state.pageBreak,
        this.state.title,
        this.state.functionalFilterValue,
        () => {
          this.setState({ functionalPage: 1 });
        }
      );
    } else {
      this.getFunctionalListByRange(
        -1,
        this.state.pageBreak,
        this.state.title,
        this.state.functionalFilterValue,
        () => {
          if (this.state.functionalListCount <= this.state.pageBreak) {
            this.setState({ functionalPage: 1 });
          } else {
            let mod = this.state.functionalListCount % this.state.pageBreak;
            if (mod === 0) {
              this.setState({
                functionalPage:
                  this.state.functionalListCount / this.state.pageBreak,
              });
            } else {
              this.setState({
                functionalPage:
                  Math.floor(
                    this.state.functionalListCount / this.state.pageBreak
                  ) + 1,
              });
            }
          }
        }
      );
    }
    this.setState({ isDelete: false });
  };

  // Searchボタン（Logical Scenario）
  searchLogicalScenario = () => {
    // 検索の結果は１ページ から表示、削除した結果は最後のページから表示
    if (!this.state.isDelete) {
      this.getLogicalListByRange(
        0,
        this.state.pageBreak,
        this.state.logicalFilterValue,
        this.state.logicalFilterValue2,
        () => {
          this.setState({ logicalPage: 1 });
        }
      );
    } else {
      this.getLogicalListByRange(
        -1,
        this.state.pageBreak,
        this.state.logicalFilterValue,
        this.state.logicalFilterValue2,
        () => {
          if (this.state.logicalListCount <= this.state.pageBreak) {
            this.setState({ logicalPage: 1 });
          } else {
            let mod = this.state.logicalListCount % this.state.pageBreak;
            if (mod === 0) {
              this.setState({
                logicalPage: this.state.logicalListCount / this.state.pageBreak,
              });
            } else {
              this.setState({
                logicalPage:
                  Math.floor(
                    this.state.logicalListCount / this.state.pageBreak
                  ) + 1,
              });
            }
          }
        }
      );
    }

    this.setState({ isDelete: false });
  };

  // Searchボタン（Concrete Scenario）
  searchConcreteScenario = () => {
    // 検索の結果は１ページ から表示、削除した結果は最後のページから表示
    if (!this.state.isDelete) {
      this.getConcreteListByRange(
        0,
        this.state.pageBreak,
        this.state.concreteFilterValue,
        () => {
          this.setState({ concretePage: 1 });
        }
      );
    } else {
      this.getConcreteListByRange(
        -1,
        this.state.pageBreak,
        this.state.concreteFilterValue,
        () => {
          if (this.state.concreteListCount <= this.state.pageBreak) {
            this.setState({ concretePage: 1 });
          } else {
            let mod = this.state.concreteListCount % this.state.pageBreak;
            if (mod === 0) {
              this.setState({
                concretePage:
                  this.state.concreteListCount / this.state.pageBreak,
              });
            } else {
              this.setState({
                concretePage:
                  Math.floor(
                    this.state.concreteListCount / this.state.pageBreak
                  ) + 1,
              });
            }
          }
        }
      );
    }
    this.setState({ isDelete: false });
  };

  // Functional Scenario の Title 変更時
  setTitle = (event) => {
    this.setState({ title: event.target.value });
  };

  // Functional Scenario の Logical 変更時
  setFunctionalFilterValue = (event) => {
    this.setState({ functionalFilterValue: event.target.value });
  };

  // Logical Scenario の Functional 変更時
  setLogicalFilterValue = (event) =>
    this.setState({ logicalFilterValue: event.target.value });

  // Logical Scenario の Concrete 変更時
  setLogicalFilterValue2 = (event) =>
    this.setState({ logicalFilterValue2: event.target.value });

  // Concrete Scenario の Logical 変更時
  setConcreteFilterValue = (event) =>
    this.setState({ concreteFilterValue: event.target.value });

  // TOP画面のシナリオリストのリフレッシュ
  handleRefresh = () => {
    this.getAllFunctional();
    this.getAllLogical();
    this.getAllConcrete();
  };

  // MONGO DBより、全てのFunctional Scenarioを取得
  getAllFunctional = () => {
    this.searchFunctionalScenario();
  };

  // MONGO DBより、指定範囲のFunctional Scenarioを取得
  getFunctionalListByRange = (skip, limit, title, lsid, callback) => {
    fetch(
      "/scenario_editor/api/getFunctionalListByRange?skip=" +
        skip +
        "&limit=" +
        limit +
        "&title=" +
        title +
        "&lsid=" +
        lsid
    )
      .then((res) => res.json())
      .then((allFunctionalList) => {
        this.setState({
          functionalListCount: allFunctionalList.allcount,
        });
        this.setState(
          {
            originalFunctionalList: allFunctionalList.result,
          },
          () => {
            callback && callback();
          }
        );
      });
  };

  // MONGO DBより、全てのLogical Scenarioを取得
  getAllLogical = () => {
    this.searchLogicalScenario();
  };

  // MONGO DBより、指定範囲のLogical Scenarioを取得
  getLogicalListByRange = (skip, limit, fnid, cnid, callback) => {
    fetch(
      "/scenario_editor/api/getLogicalListByRange?skip=" +
        skip +
        "&limit=" +
        limit +
        "&fnid=" +
        fnid +
        "&cnid=" +
        cnid
    )
      .then((res) => res.json())
      .then((allLogicalList) => {
        this.setState({
          logicalListCount: allLogicalList.allcount,
        });
        this.setState(
          {
            originalLogicalList: allLogicalList.result,
          },
          () => {
            callback && callback();
          }
        );
      });
  };

  // MONGO DBより、全てのConcrete Scenarioを取得
  getAllConcrete = () => {
    this.searchConcreteScenario();
  };

  // MONGO DBより、指定範囲のConcreate Scenarioを取得
  getConcreteListByRange = (skip, limit, lsid, callback) => {
    fetch(
      "/scenario_editor/api/getConcreteListByRange?skip=" +
        skip +
        "&limit=" +
        limit +
        "&lsid=" +
        lsid
    )
      .then((res) => res.json())
      .then((allConcreteList) => {
        this.setState({
          concreteListCount: allConcreteList.allcount,
        });
        this.setState(
          {
            originalConcreteList: allConcreteList.result,
          },
          () => {
            callback && callback();
          }
        );
      });
  };

  // Functional Scenario のフィルタ解除
  handleClearFunctionalFilter() {
    this.setState({ title: "", functionalFilterValue: "" });
  }

  // Logical Scenario のフィルタ解除
  handleClearLogicalFilter() {
    this.setState({ logicalFilterValue: "", logicalFilterValue2: "" });
  }

  // Concrete Scenario のフィルタ解除
  handleClearConcreteFilter() {
    this.setState({ concreteFilterValue: "" });
  }

  handlePageChange = (type) => (event, value) => {
    event.preventDefault();
    if (type === "functional") {
      this.setState({ functionalPage: value }, () => {
        if (this.state.isAutoPageChange === false) {
          this.getFunctionalListByRange(
            this.state.pageBreak * (this.state.functionalPage - 1),
            this.state.pageBreak,
            this.state.title,
            this.state.functionalFilterValue
          );
        } else {
          this.setState({ isAutoPageChange: false });
        }
      });
    } else if (type === "logical") {
      this.setState({ logicalPage: value }, () => {
        if (this.state.isAutoPageChange === false) {
          this.getLogicalListByRange(
            this.state.pageBreak * (this.state.logicalPage - 1),
            this.state.pageBreak,
            this.state.logicalFilterValue,
            this.state.logicalFilterValue2
          );
        } else {
          this.setState({ isAutoPageChange: false });
        }
      });
    } else if (type === "concrete") {
      this.setState({ concretePage: value }, () => {
        if (this.state.isAutoPageChange === false) {
          this.getConcreteListByRange(
            this.state.pageBreak * (this.state.concretePage - 1),
            this.state.pageBreak,
            this.state.concreteFilterValue
          );
        } else {
          this.setState({ isAutoPageChange: false });
        }
      });
    }
  };

  render() {
    const { classes } = this.props;
    var funcLength = this.state.functionalListCount;
    var logicLength = this.state.logicalListCount;
    var concreteLength = this.state.concreteListCount;

    // 改ページ単位
    //var pageBreak = 5;

    // ページ数
    var funcCount = Math.ceil(funcLength / this.state.pageBreak);
    var logicCount = Math.ceil(logicLength / this.state.pageBreak);
    var concreteCount = Math.ceil(concreteLength / this.state.pageBreak);

    // 指定したページに表示される、１ページ分のシナリオリストの取得
    var newFuncList = this.state.originalFunctionalList;
    var newLogicList = this.state.originalLogicalList;
    var newConcreteList = this.state.originalConcreteList;

    var list = (
      <div className={classes.root}>
        <AppBar position="static">
          <Toolbar variant="dense">
            <Typography variant="h6" color="inherit">
              Scenario Explorer
            </Typography>
          </Toolbar>
        </AppBar>

        {/* content start */}
        <div className={classes.content}>
          <Grid container spacing={3}>
            {/* functional scenario start */}
            <Grid item xs={4}>
              <Paper className={classes.paper}>
                <Typography
                  className={classes.title}
                  color="primary"
                  align="center"
                >
                  Functional Scenario
                </Typography>
                <Grid container justify="flex-start">
                  <Button
                    onClick={this.handleNewFS}
                    color="primary"
                    variant="contained"
                    startIcon={<AddIcon />}
                  >
                    New
                  </Button>
                </Grid>
                <Grid>
                  <TextField
                    autoFocus
                    margin="dense"
                    id="title"
                    label="Title"
                    type="text"
                    onChange={this.setTitle}
                    value={this.state.title}
                    fullWidth
                  />
                </Grid>
                <Grid>
                  <TextField
                    margin="dense"
                    id="logical"
                    label="Logical"
                    type="text"
                    onChange={this.setFunctionalFilterValue}
                    value={this.state.functionalFilterValue}
                    fullWidth
                  />
                </Grid>
                <Grid container justify="flex-end">
                  <Button
                    className={classes.searchButtonStyle}
                    onClick={this.searchFunctionalScenario}
                    color="primary"
                    variant="contained"
                    startIcon={<SearchIcon />}
                  >
                    Search
                  </Button>
                </Grid>
                <Box m="2rem" />
                <Box display="flex" justifyContent="center">
                  <Pagination
                    page={this.state.functionalPage}
                    count={funcCount}
                    color="primary"
                    onChange={this.handlePageChange("functional")}
                    showFirstButton
                    showLastButton
                    size="small"
                  />
                </Box>
                <Typography
                  className={this.props.classes.functional}
                  variant="h6"
                >
                  Scenario List
                </Typography>
                {newFuncList.length === 0 ? (
                  <Typography>There is no result</Typography>
                ) : (
                  <Paper style={{ maxHeight: "50vh", overflow: "auto" }}>
                    <List component="nav">
                      {newFuncList.map(({ _id, gid, data }, i) => {
                        return (
                          <div
                            key={_id}
                            onContextMenu={this.handleFunctionClick(
                              _id,
                              gid,
                              data.title
                            )}
                            style={{ cursor: "context-menu" }}
                          >
                            <ListItem button>
                              {data.title}（{gid}）
                            </ListItem>
                            {funcLength !== i + 1 ? <Divider /> : null}
                          </div>
                        );
                      })}
                    </List>
                  </Paper>
                )}
              </Paper>
            </Grid>
            {/* functional scenario end */}
            {/* logical scenario start */}
            <Grid item xs={4}>
              <Paper className={classes.paper}>
                <Typography
                  className={classes.title}
                  color="primary"
                  align="center"
                >
                  Logical Scenario
                </Typography>
                <Grid container justify="flex-start">
                  <Button
                    onClick={this.handleNewLS}
                    color="primary"
                    variant="contained"
                    startIcon={<AddIcon />}
                  >
                    New
                  </Button>
                </Grid>
                <Grid>
                  <TextField
                    margin="dense"
                    id="functional"
                    label="Functional"
                    type="text"
                    onChange={this.setLogicalFilterValue}
                    value={this.state.logicalFilterValue}
                    fullWidth
                  />
                </Grid>
                <Grid>
                  <TextField
                    margin="dense"
                    id="concrete"
                    label="Concrete"
                    type="text"
                    onChange={this.setLogicalFilterValue2}
                    value={this.state.logicalFilterValue2}
                    fullWidth
                  />
                </Grid>
                <Grid container justify="flex-end">
                  <Button
                    className={classes.searchButtonStyle}
                    onClick={this.searchLogicalScenario}
                    color="primary"
                    variant="contained"
                    startIcon={<SearchIcon />}
                  >
                    Search
                  </Button>
                </Grid>
                <Box m="2rem" />
                <Box display="flex" justifyContent="center">
                  <Pagination
                    page={this.state.logicalPage}
                    count={logicCount}
                    color="primary"
                    onChange={this.handlePageChange("logical")}
                    showFirstButton
                    showLastButton
                    size="small"
                  />
                </Box>
                <Typography className={this.props.classes.logical} variant="h6">
                  Scenario List
                </Typography>
                {newLogicList.length === 0 ? (
                  <Typography>There is no result</Typography>
                ) : (
                  <Paper style={{ maxHeight: "50vh", overflow: "auto" }}>
                    <List component="nav">
                      {newLogicList.map(({ _id, gid, data, used_date }, i) => {
                        return (
                          <div
                            key={_id}
                            onContextMenu={this.handleLogicalClick(
                              _id,
                              gid,
                              data.title
                            )}
                            style={{ cursor: "context-menu" }}
                          >
                            <ListItem button>
                              {used_date === undefined || used_date === "" ? (
                                ""
                              ) : (
                                <ListItemIcon>
                                  <Warning />
                                </ListItemIcon>
                              )}{" "}
                              {data.title}（{gid}）
                            </ListItem>
                            {logicLength !== i + 1 ? <Divider /> : null}
                          </div>
                        );
                      })}
                    </List>
                  </Paper>
                )}
              </Paper>
            </Grid>
            {/* logical scenario end */}
            {/* concrete scenario start */}
            <Grid item xs={4}>
              <Paper className={classes.paper}>
                <Typography
                  className={classes.title}
                  color="primary"
                  align="center"
                >
                  Concrete Scenario
                </Typography>
                <Grid container justify="flex-start">
                  <Button
                    onClick={this.handleNewCS}
                    color="primary"
                    variant="contained"
                    startIcon={<AddIcon />}
                  >
                    New
                  </Button>
                </Grid>
                <Grid>
                  <TextField
                    margin="dense"
                    id="logical"
                    label="Logical"
                    type="text"
                    onChange={this.setConcreteFilterValue}
                    value={this.state.concreteFilterValue}
                    fullWidth
                  />
                </Grid>
                <Box m="3.5rem" />
                <Grid container justify="flex-end">
                  <Button
                    className={classes.searchButtonStyle}
                    onClick={this.searchConcreteScenario}
                    color="primary"
                    variant="contained"
                    startIcon={<SearchIcon />}
                  >
                    Search
                  </Button>
                </Grid>
                <Box m="2rem" />
                <Box display="flex" justifyContent="center">
                  <Pagination
                    page={this.state.concretePage}
                    count={concreteCount}
                    color="primary"
                    onChange={this.handlePageChange("concrete")}
                    showFirstButton
                    showLastButton
                    size="small"
                  />
                </Box>
                <Typography
                  className={this.props.classes.concrete}
                  variant="h6"
                >
                  Scenario List
                </Typography>
                {newConcreteList.length === 0 ? (
                  <Typography>There is no result</Typography>
                ) : (
                  <Paper style={{ maxHeight: "50vh", overflow: "auto" }}>
                    <List component="nav">
                      {newConcreteList.map(({ _id, gid, data }, i) => {
                        return (
                          <div
                            key={_id}
                            onContextMenu={this.handleConcreteClick(
                              _id,
                              gid,
                              data.title
                            )}
                            style={{ cursor: "context-menu" }}
                          >
                            <ListItem button>
                              {data.title}（{gid}）
                            </ListItem>
                            {concreteLength !== i + 1 ? <Divider /> : null}
                          </div>
                        );
                      })}
                    </List>
                  </Paper>
                )}
              </Paper>
            </Grid>
            {/* concrete scenario end */}
          </Grid>
        </div>

        {/* content end */}
        {/* menu start */}
        <Menu
          keepMounted
          open={this.state.functionY !== null}
          onClose={this.handleFunctionalClose}
          anchorReference="anchorPosition"
          anchorPosition={
            this.state.functionY !== null && this.state.functionX !== null
              ? { top: this.state.functionY, left: this.state.functionX }
              : undefined
          }
        >
          <MenuItem onClick={this.handleFilterLogicalScenario}>
            <ListItemIcon>
              <FilterListIcon />
            </ListItemIcon>
            Set Filter For Logical Scenario
          </MenuItem>
          <MenuItem onClick={this.handleOpenScenario}>
            <ListItemIcon>
              <OpenInNewIcon />
            </ListItemIcon>
            Open Scenario
          </MenuItem>
          <MenuItem onClick={this.handleDeleteScenario}>
            <ListItemIcon>
              <DeleteIcon />
            </ListItemIcon>
            Delete Scenario
          </MenuItem>
          <MenuItem onClick={this.handleCreateLogicalScenario}>
            <ListItemIcon>
              <CreateIcon />
            </ListItemIcon>
            Create Logical Scenario
          </MenuItem>
        </Menu>

        <Menu
          keepMounted
          open={this.state.logicalY !== null}
          onClose={this.handleLogicalClose}
          anchorReference="anchorPosition"
          anchorPosition={
            this.state.logicalY !== null && this.state.logicalX !== null
              ? { top: this.state.logicalY, left: this.state.logicalX }
              : undefined
          }
        >
          <MenuItem onClick={this.handleFilterFunctionalScenario}>
            <ListItemIcon>
              <FilterListIcon />
            </ListItemIcon>
            Set Filter For Functional Scenario
          </MenuItem>
          <MenuItem onClick={this.handleFilterConcreteScenario}>
            <ListItemIcon>
              <FilterListIcon />
            </ListItemIcon>
            Set Filter For Concrete Scenario
          </MenuItem>
          <MenuItem onClick={this.handleOpenScenario}>
            <ListItemIcon>
              <OpenInNewIcon />
            </ListItemIcon>
            Open Scenario
          </MenuItem>
          <MenuItem onClick={this.handleDeleteScenario}>
            <ListItemIcon>
              <DeleteIcon />
            </ListItemIcon>
            Delete Scenario
          </MenuItem>
          <MenuItem onClick={this.handleCreateConcreteScenario}>
            <ListItemIcon>
              <CreateIcon />
            </ListItemIcon>
            Create Concrete Scenario
          </MenuItem>
        </Menu>
        <Menu
          keepMounted
          open={this.state.concreteY !== null}
          onClose={this.handleConcreteClose}
          anchorReference="anchorPosition"
          anchorPosition={
            this.state.concreteY !== null && this.state.concreteX !== null
              ? { top: this.state.concreteY, left: this.state.concreteX }
              : undefined
          }
        >
          <MenuItem onClick={this.handleFilterLogicalScenario}>
            <ListItemIcon>
              <FilterListIcon />
            </ListItemIcon>
            Set Filter For Logical Scenario
          </MenuItem>
          <MenuItem onClick={this.handleOpenScenario}>
            <ListItemIcon>
              <OpenInNewIcon />
            </ListItemIcon>
            Open Scenario
          </MenuItem>
          <MenuItem onClick={this.handleDeleteScenario}>
            <ListItemIcon>
              <DeleteIcon />
            </ListItemIcon>
            Delete Scenario
          </MenuItem>
        </Menu>
        {/* menu end */}
        {/* dialog start */}
        {/* シナリオ削除時に表示されるダイアログ */}
        <Dialog
          open={this.state.showDeleteDialog}
          aria-labelledby="delete-scenario"
          disableBackdropClick
          disableEscapeKeyDown
          fullWidth
          maxWidth="xs"
        >
          <DialogTitle
            id="delete-scenario"
            style={{ paddingTop: 0, paddingRight: 0, paddingLeft: 0 }}
          >
            <AppBar position="static">
              <Toolbar variant="dense">
                <Typography variant="h6" color="inherit" style={{ flex: 1 }}>
                  {this.state.selectedType === "functional"
                    ? "Delete Functional Scenario"
                    : this.state.selectedType === "logical"
                    ? "Delete Logical Scenario"
                    : this.state.selectedType === "concrete"
                    ? "Delete Concrete Scenario"
                    : ""}
                </Typography>
                <Button
                  aria-label="close"
                  color="inherit"
                  onClick={this.handleDeleteClose}
                >
                  <CloseIcon />
                </Button>
              </Toolbar>
            </AppBar>
          </DialogTitle>
          <DialogContent>Do you really want to delete this?</DialogContent>
          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              className={classes.buttonSpace}
              variant="contained"
              aria-controls="simple-menu"
              color="primary"
              onClick={this.handleDeleteOK}
            >
              OK
            </Button>
            <Button
              className={classes.buttonSpace}
              variant="contained"
              aria-controls="simple-menu"
              color="primary"
              onClick={this.handleDeleteClose}
            >
              Cancel
            </Button>
          </DialogActions>
        </Dialog>

        {/* Newボタン押下時に表示されるダイアログ */}
        <Dialog
          open={this.state.newFS || this.state.newLS || this.state.newCS}
          aria-labelledby="new-scenario"
          disableBackdropClick
          disableEscapeKeyDown
          fullWidth
          maxWidth="md"
        >
          <DialogTitle id="new-scenario" style={{ padding: 0 }}>
            <AppBar position="static">
              <Toolbar variant="dense">
                <Typography variant="h6" color="inherit" style={{ flex: 1 }}>
                  {this.state.newFS
                    ? "New Functional Scenario"
                    : this.state.newLS
                    ? "New Logical Scenario"
                    : this.state.newCS
                    ? "New Concrete Scenario"
                    : ""}
                </Typography>
                <Button
                  aria-label="close"
                  color="inherit"
                  onClick={this.handleNewClose}
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
              id="newGID"
              label="GID"
              type="text"
              onChange={this.setDialogGID}
              fullWidth
            />
            <TextField
              margin="dense"
              id="newTitle"
              label="Title"
              type="text"
              onChange={this.setDialogTitle}
              fullWidth
            />
          </DialogContent>
          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              disabled={
                this.checkDialogGID() === "" || this.checkDialogTitle() === ""
              }
              className={classes.buttonSpace}
              aria-controls="simple-menu"
              variant="contained"
              color="primary"
              onClick={this.handleNewOK}
            >
              OK
            </Button>
            <Button
              className={classes.buttonSpace}
              aria-controls="simple-menu"
              variant="contained"
              color="primary"
              onClick={this.handleNewClose}
            >
              Cancel
            </Button>
          </DialogActions>
        </Dialog>

        {/* Create XXXX Scenarioメニュー押下時に表示されるダイアログ */}
        <Dialog
          open={this.state.showCreateLogical || this.state.showCreateConcrete}
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
                  {this.state.showCreateLogical
                    ? "Create Logical Scenario"
                    : this.state.showCreateConcrete
                    ? "Create Concrete Scenario"
                    : ""}
                </Typography>
                <Button
                  aria-label="close"
                  color="inherit"
                  onClick={this.handleCreateClose}
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
              defaultValue={
                this.state.showCreateLogical
                  ? this.state.functionalTitle
                  : this.state.showCreateConcrete
                  ? this.state.logicalTitle
                  : ""
              }
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
              onClick={this.handleCreateOK}
            >
              OK
            </Button>
            <Button
              className={classes.buttonSpace}
              aria-controls="simple-menu"
              variant="contained"
              color="primary"
              onClick={this.handleCreateClose}
            >
              Cancel
            </Button>
          </DialogActions>
        </Dialog>
        {/* dialog end */}
        {/* 遷移先の画面 start */}
        {this.state.showOpen ? (
          <ScenarioDialog
            type={this.state.type}
            data={this.state.selectedData}
            onClose={this.handleCloseScenario}
          ></ScenarioDialog>
        ) : null}
        {/* 遷移先の画面 end */}
      </div>
    );
    return list;
  }
}

Home.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Home);
