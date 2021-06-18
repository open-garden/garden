import ReactAce from "react-ace-editor";
import React, { Component } from "react";
import GenerateConcreteScenario from "./GenerateConcreteScenario";

class ScenarioEditor extends Component {
  constructor(props) {
    super(props);
    this.state = {
      editorValue: JSON.stringify(props.editorValue, null, 4),
      id: props.id,
      gid: props.gid,
      type: props.type,
      textWidth: "100%",
      summary: "",
      used_date: props.used_date,
    };
    this.onChange = this.onChange.bind(this);
    this.ScenarioEditorRef = React.createRef();
  }

  onChange(newValue, e) {
    this.setState({ editorValue: newValue });
    this.props.onChangeEditorData(newValue);
  }

  handleGetParameterList = () => {};

  componentDidMount() {
    if (this.props.type === "functional") {
      if (this.state.editorValue !== this.state.functional) {
        this.setState({ editorValue: this.state.functional });
      }
    } else if (this.props.type === "logical") {
      let params = {};
      let col = [];
      let row = [];
      let checked = [];
      let columns = [];
      let paramDefinition = [];
      params.id = this.state.id;
      const queryParams = new URLSearchParams(params);
      let url = `/scenario_editor/api/getParameterListById?${queryParams}`;
      const requestOptions = {
        method: "GET",
        headers: { "Content-Type": "application/json; charset=utf-8" },
      };

      fetch(url, requestOptions)
        .then((response) => {
          return response.json();
        })
        .then((result) => {
          if (result !== null && result.data.length > 0) {
            col = Object.keys(result.data[0]);
            for (let i = 0; i < col.length - 1; i++) {
              columns.push({ title: col[i], field: col[i] });
            }
            for (let i = 0; i < result.data.length; i++) {
              let use = result.data[i]["rowUsed"];
              let rowdata = result.data[i];
              delete rowdata["rowUsed"];
              let rowchecked = {};
              rowchecked["id"] = i;
              rowchecked["checked"] = use;
              rowdata["tableData"] = rowchecked;
              row.push(rowdata);
            }
          }
          if (result !== null && result.param.length > 0) {
            paramDefinition = result.param;
          }
          this.setState({ editorValue: this.state.logical });
          this.setState({ textWidth: "50%" });
          this.setState({
            summary: (
              <GenerateConcreteScenario
                id={this.props.id}
                gid={this.props.gid}
                used_date={this.props.used_date}
                editorValue={this.props.editorValue}
                tableColumns={columns}
                tableRows={row}
                parameterData={paramDefinition}
                checked={checked}
                ref={this.ScenarioEditorRef}
              ></GenerateConcreteScenario>
            ),
          });
        });
    } else if (this.props.type === "concrete") {
      this.setState({ editorValue: this.state.logical });
    }
  }

  async componentDidUpdate() {
    if (this.state.type !== this.props.type) {
      if (this.props.type === "functional") {
        this.setState({ editorValue: JSON.stringify(this.props.editorValue) });
        this.setState({ type: this.props.type });
        await this.onChange(this.props.editorValue);
      } else if (this.props.type === "logical") {
        this.setState({ editorValue: this.props.editorValue });
        this.setState({ type: this.props.type });
        await this.onChange(this.props.editorValue);
      } else if (this.props.type === "concrete") {
        this.setState({ editorValue: this.props.editorValue });
        this.setState({ type: this.props.type });
        await this.onChange(this.props.editorValue);
      }
    }
  }
  generateScenario() {
    return this.ScenarioEditorRef.current.generateScenario(
      this.props.id,
      this.props.gid,
      this.props.used_date,
      this.props.editorValue
    );
  }

  render() {
    const { show, type } = this.props;
    let editor = (
      <ReactAce
        mode="json"
        theme="github"
        setValue={this.state.editorValue}
        onChange={this.onChange}
        style={{
          height: "89vh",
          width: this.state.textWidth,
          overflow: "auto",
        }}
        ref={(instance) => {
          this.ace = instance;
        }}
      />
    );
    if (!show || this.state.type !== type) {
      editor = null;
    }
    return (
      <div style={{ display: "flex" }}>
        {editor}
        {this.state.summary}
      </div>
    );
  }
}

export default ScenarioEditor;
