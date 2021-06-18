import React, { useEffect } from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContentText from "@material-ui/core/DialogContentText";

/**
 * 確認／エラー表示ダイアログコンポーネント
 *
 * @return {*}
 */
export const CommonDialog: React.FC<{
  msg: string;
  isOpen: boolean;
  doYes: any;
  doNo: any;
}> = ({ msg, isOpen, doYes, doNo }) => {
  const [open, setOpen] = React.useState(false);

  /**
   * ダイアログクローズ
   *
   */
  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    setOpen(isOpen);
  }, [isOpen]);

  return (
    <div>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"Confirm Dialog"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {msg}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => doYes()}
            color="primary"
            variant="contained"
            size="small"
          >
            Yes
          </Button>
          <Button
            onClick={() => doNo()}
            color="primary"
            variant="contained"
            size="small"
            autoFocus
          >
            No
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default CommonDialog;
