import React from "react";
import { HOST_SCENARIOEDITOR } from "../common/Const";

const divblockStyle = {
  height: "1000px",
};

const divframeStyle = {
  height: "100%",
};

const iframestyle = {
  border: "none",
  width: "100%",
  height: "100%",
  padding: "0",
  margin: "0",
  overflow: "hidden",
};

/**
 * ScenarioEditor表示用コンポーネント
 *
 * @return {*}
 */
export const ScenarioEditor: React.FC = () => {
  return (
    <div style={divblockStyle}>
      <div style={divframeStyle}>
        <iframe
          src={HOST_SCENARIOEDITOR}
          title="ScenarioEditor"
          style={iframestyle}
        />
      </div>
    </div>
  );
};

export default ScenarioEditor;
