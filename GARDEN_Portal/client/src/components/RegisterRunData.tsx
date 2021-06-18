import React from "react";
import { useForm } from "react-hook-form";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import AlertTitle from "@material-ui/lab/AlertTitle";
import {
  Icons,
  RegistData,
  HOST_ROADEDITOR_MAP,
  Messages,
  URL_RUNDATA,
} from "../common/Const";

/**
 * 走行データ新規登録用コンポーネント
 *
 * @return {*}
 */
export const RegisterRunData: React.FC = () => {
  const { register, handleSubmit } = useForm();
  const [successSnackOpen, setSuccessSnackOpen] = React.useState(false);
  const [errorSnackOpen, setErrorSnackOpen] = React.useState(false);
  const [errorSnackText, setErrorSnackText] = React.useState(
    Messages.ERROR_DEFAULT
  );

  /**
   * 正常メッセージの消去処理
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
   * エラーメッセージ表示処理
   *
   * @param {string} message エラーメッセージ
   */
  const handleErrorSnackOpen = (message: string) => {
    setErrorSnackText(message);
    setErrorSnackOpen(true);
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
   * 走行データ新規登録実行処理
   *
   * @param {*} registData 登録情報
   */
  const onSubmit = (registData: any) => {
    const bodyData: RegistData = {
      measurement: registData["Measurement"],
      mapid: registData["MapId"],
    };
    const url = URL_RUNDATA;
    fetch(url, {
      method: "post",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bodyData),
    })
      .then((response) => {
        if (response.ok) {
          setSuccessSnackOpen(true);
        } else {
          throw new Error(`${response.status}:${response.statusText}`);
        }
      })
      .catch((e: Error) => {
        handleErrorSnackOpen(e.message);
      });
  };

  return (
    <div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <TextField
          required
          type="text"
          placeholder="Driving Data Name"
          name="Measurement"
          inputRef={register()}
          margin="dense"
          id="rundataname"
          label="Driving Data Name"
          fullWidth
        />
        <TextField
          type="text"
          placeholder="MapId"
          name="MapId"
          inputRef={register()}
          margin="dense"
          id="mapdata"
          label="Map Id"
          fullWidth
        />

        <div style={{ textAlign: "left", marginTop: "20px" }}>
          <Button
            variant="outlined"
            style={{ marginLeft: 5 }}
            size="small"
            endIcon={<Icons.Launch />}
            color="primary"
            href={HOST_ROADEDITOR_MAP}
            target="_blank"
            rel="noopener noreferrer"
          >
            Map
          </Button>
          <Button
            type="submit"
            variant="contained"
            size="small"
            color="primary"
            style={{ float: "right" }}
          >
            Register
          </Button>
        </div>
      </form>
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
    </div>
  );
};

export default RegisterRunData;
