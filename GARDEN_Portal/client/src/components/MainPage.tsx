import React from "react";
import clsx from "clsx";
import {
  makeStyles,
  useTheme,
  Theme,
  createStyles,
} from "@material-ui/core/styles";
import Drawer from "@material-ui/core/Drawer";
import CssBaseline from "@material-ui/core/CssBaseline";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import List from "@material-ui/core/List";
import Typography from "@material-ui/core/Typography";
import Divider from "@material-ui/core/Divider";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";
import ChevronLeftIcon from "@material-ui/icons/ChevronLeft";
import ChevronRightIcon from "@material-ui/icons/ChevronRight";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import {
  BlurLinear,
  Build,
  DriveEta,
  MenuBook,
  SupervisorAccount,
  Timeline,
  FindInPage,
} from "@material-ui/icons";
import Contents from "./Contents";
import { MenuItem, HOST_SCENARIOEDITOR } from "../common/Const";

const drawerWidth = 240;

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: "flex",
    },
    appBar: {
      transition: theme.transitions.create(["margin", "width"], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
    },
    appBarShift: {
      width: `calc(100% - ${drawerWidth}px)`,
      marginLeft: drawerWidth,
      transition: theme.transitions.create(["margin", "width"], {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
      }),
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    hide: {
      display: "none",
    },
    drawer: {
      width: drawerWidth,
      flexShrink: 0,
    },
    drawerPaper: {
      width: drawerWidth,
    },
    drawerHeader: {
      display: "flex",
      alignItems: "center",
      padding: theme.spacing(0, 1),
      // necessary for content to be below app bar
      ...theme.mixins.toolbar,
      justifyContent: "flex-end",
    },
    content: {
      flexGrow: 1,
      padding: theme.spacing(3),
      transition: theme.transitions.create("margin", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
      marginLeft: -drawerWidth,
    },
    contentShift: {
      transition: theme.transitions.create("margin", {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
      }),
      marginLeft: 0,
    },
  })
);

/**
 * メインページ用コンポーネント
 *
 * @return {*}
 */
export const MainPage: React.FC = () => {
  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);
  const [sender, setSender] = React.useState<MenuItem>("");

  /**
   * サイドメニューの表示
   *
   */
  const handleDrawerOpen = () => {
    setOpen(true);
  };

  /**
   * サイドメニューの非表示
   *
   */
  const handleDrawerClose = () => {
    setOpen(false);
  };

  /**
   * 走行データ一覧メニュー押下時の処理
   *
   */
  const handleRunDataListClick = () => {
    setSender("RunDataList");
  };

  /**
   * シナリオエディタメニュー押下時の処理
   *
   */
  const handleScenarioListClick = () => {
    window.open(HOST_SCENARIOEDITOR, "_blank", "noopener noreferrer");
  };

  /**
   * 走行データ検索メニュー押下時の処理
   *
   */
  const handleRdfListClick = () => {
    setSender("DrivingDataSearch");
  };

  /**
   * RDFViewerメニュー押下時の処理
   *
   */
  const handleRDFViewerClick = () => {
    setSender("RDFViewer");
  };

  /**
   * パラメータリストメニュー押下時の処理
   *
   */
  const handleParameterListClick = () => {
    setSender("ParameterList");
  };

  /**
   * アクターリストメニュー押下時の処理
   *
   */
  const handleActorListClick = () => {
    setSender("ActorList");
  };

  /**
   * コンバートツールメニュー押下時の処理
   *
   */
  const handleConvertToolClick = () => {
    setSender("ConvertTool");
  };

  /**
   * シミュレーションメニュー押下時の処理
   *
   */
  const handleSimulationClick = () => {
    setSender("Simulation");
  };

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar
        position="fixed"
        className={clsx(classes.appBar, {
          [classes.appBarShift]: open,
        })}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            edge="start"
            className={clsx(classes.menuButton, open && classes.hide)}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h5" noWrap>
            Garden Portal
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer
        className={classes.drawer}
        variant="persistent"
        anchor="left"
        open={open}
        classes={{
          paper: classes.drawerPaper,
        }}
      >
        <div className={classes.drawerHeader}>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === "ltr" ? (
              <ChevronLeftIcon />
            ) : (
              <ChevronRightIcon />
            )}
          </IconButton>
        </div>
        <Divider />
        <List>
          <ListItem button onClick={handleRunDataListClick}>
            <ListItemIcon>
              <DriveEta />
            </ListItemIcon>
            <ListItemText>Driving Data List</ListItemText>
          </ListItem>
          <ListItem button onClick={handleScenarioListClick}>
            <ListItemIcon>
              <MenuBook />
            </ListItemIcon>
            <ListItemText>Scenario Editor</ListItemText>
          </ListItem>
          <ListItem button onClick={handleRdfListClick}>
            <ListItemIcon>
              <FindInPage />
            </ListItemIcon>
            <ListItemText>Driving Data Search</ListItemText>
          </ListItem>
          <ListItem button onClick={handleRDFViewerClick}>
            <ListItemIcon>
              <Timeline />
            </ListItemIcon>
            <ListItemText>RDF Viewer</ListItemText>
          </ListItem>
          <ListItem button onClick={handleParameterListClick} disabled={true}>
            <ListItemIcon>
              <BlurLinear />
            </ListItemIcon>
            <ListItemText>Parameter List</ListItemText>
          </ListItem>
          <ListItem button onClick={handleActorListClick} disabled={true}>
            <ListItemIcon>
              <SupervisorAccount />
            </ListItemIcon>
            <ListItemText>Actor List</ListItemText>
          </ListItem>
          <ListItem button onClick={handleConvertToolClick} disabled={true}>
            <ListItemIcon>
              <Build />
            </ListItemIcon>
            <ListItemText>Convert Tool</ListItemText>
          </ListItem>
          <ListItem button onClick={handleSimulationClick} disabled={true}>
            <ListItemIcon>
              <Timeline />
            </ListItemIcon>
            <ListItemText>Simulation progress</ListItemText>
          </ListItem>
        </List>

        <Divider />
      </Drawer>
      <main
        className={clsx(classes.content, {
          [classes.contentShift]: open,
        })}
      >
        <div className={classes.drawerHeader} />
        <Contents menuitem={sender}></Contents>
      </main>
    </div>
  );
};

export default MainPage;
