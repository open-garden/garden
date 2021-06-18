import React from "react";
import RunDataList from "./RunDataList";
import ScenarioEditor from "./ScenarioEditor";
import { MenuItem } from "../common/Const";
import { DrivingDataSearch } from "./DrivingDataSearch";
import RDFViewr from "./RDFViewer";

interface IContentsInterface {
  menuitem: MenuItem;
}

/**
 * メニューの処理振り分けコンポーネント
 *
 * @param {props} props
 * @return {*}
 */
export const Contents: React.FC<IContentsInterface> = (props) => {
  switch (props.menuitem) {
    case "RunDataList":
      return <RunDataList></RunDataList>;
    case "ScenarioList":
      return <ScenarioEditor></ScenarioEditor>;
    case "DrivingDataSearch":
      return <DrivingDataSearch></DrivingDataSearch>;
    case "RDFViewer":
      return <RDFViewr></RDFViewr>;
    default:
      return (
        <img
          src={`${process.env.PUBLIC_URL}/background.png`}
          alt="icon"
          style={{
            alignContent: "center",
            paddingTop: "100px",
            //opacity: "0.1",
          }}
        />
      );
  }
};

export default Contents;
