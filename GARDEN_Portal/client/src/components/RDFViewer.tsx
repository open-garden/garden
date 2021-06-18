import React from "react";
import { HOST_RDFVIEWER } from "../common/Const";

const divblockStyle = {
  height: "900px",
};

const divframeStyle = {
  height: "100%",
};

const iframestyle = {
  border: "soild",
  width: "100%",
  height: "95%",
  padding: "0",
  margin: "0",
  overflow: "hidden",
};

/**
 * RDFViewr表示用コンポーネント
 *
 * @return {*}
 */
export const RDFGraphViewr: React.FC = () => {
  return (
    <div style={divblockStyle}>
      <div style={divframeStyle}>
        <iframe src={HOST_RDFVIEWER} title="RDF Viewer" style={iframestyle} />
      </div>
    </div>
  );
};

export default RDFGraphViewr;
