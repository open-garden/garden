import React from "react";
import { withStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import ScenarioEditorMenu from "./ScenarioEditorMenu";

const useStyles = (theme) => ({
  paper: {
    position: "absolute",
    width: "50%",
    height: "50%",
    top: "10%",
    backgroundColor: "#ffffff",
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  backDrop: {
    backgroundColor: "rgb(255,255,255,0.2)",
  },
});

class ScenarioDialog extends React.Component {
  classes = this.props;
  constructor(props) {
    super(props);
    this.state = {
      open: false,
      close: false,
      type: props.type,
      editorValue: props.data[0].data,
      id: props.data[0]._id,
      gid: props.data[0].gid,
      used_date: props.data[0].used_date,
    };
  }

  handleOpen = () => {
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
    this.setState({ close: true });
    this.props.onClose();
  };

  componentDidUpdate() {
    var { type } = this.props;
    if (!this.state.open) {
      this.setState({ open: true });
    }

    if (type !== "") {
      if (this.state.type === "") {
        this.setState({ type: type });
      }
    }
  }

  componentDidMount() {
    var { type } = this.props;
    if (!this.state.open) {
      this.setState({ open: true });
    }

    if (type !== "") {
      if (this.state.type === "") {
        this.setState({ type: type });
      }
    }
  }
  render() {
    const { type } = this.props;
    var dialog = null;
    if (type !== "" && type !== undefined && !this.state.close) {
      dialog = (
        <div>
          <Modal
            open={this.state.open}
            onClose={this.handleClose}
            aria-labelledby="simple-modal-title"
            aria-describedby="simple-modal-description"
            hideBackdrop
          >
            <div className={this.classes.paper}>
              <ScenarioEditorMenu
                onClose={this.handleClose}
                type={this.state.type}
                editorValue={this.props.data[0].data}
                id={this.state.id}
                gid={this.state.gid}
                used_date={this.state.used_date}
              ></ScenarioEditorMenu>
            </div>
          </Modal>
        </div>
      );
    }

    if (this.state.close) {
      dialog = null;
    }
    return <div>{dialog}</div>;
  }
}

export default withStyles(useStyles, { withTheme: true })(ScenarioDialog);
