import React,{ useEffect,useState } from "react";

import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import CloseIcon from "@material-ui/icons/Close";
import {Button} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import InputWithCounter from "../middle/InputWithCounter";
import Input from "@material-ui/core/Input";
import * as PropTypes from "prop-types";
import axios from 'axios';



ReportDialog.propTypes = {
    onReportClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired,
    tweet_id: PropTypes.number.isRequired,
    user_id: PropTypes.number.isRequired
};

function ReportDialog(props) {
    const {onReportClose, open,tweet_id,user_id} = props;
    const [report_content ,setComment] = useState("");

    function handleClose(reportData) {
        onReportClose(reportData);
    }

    async function handleReport() {

        let reportDto = {
            tweet_id: tweet_id,
            user_id: user_id,
            report_content: report_content
        };


        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: "post",
            url: '/api/report',
            headers: {
                Authorization: bearer
            },
            data: reportDto
        }).then((resp)=>{
            handleClose(resp.data);
            alert("Reported!");
        }).catch(error => {
            // TODO: error handling for UI
            const alertMsg = JSON.stringify(error.response.data);
            const msg = JSON.parse(alertMsg);
            msg.message = new String(msg.message);
            alert(msg.message);
        });

    }


    return (
        <Dialog onClose={handleClose} className="edit-profile-dialog" aria-labelledby="edit-profile-dialog" open={open}>
            <DialogTitle id="report-dialog-header">
                <CloseIcon className="close-dialog" onClick={handleClose}/>
                <span className="dialog-title">Report an issue</span>

            </DialogTitle>
            <DialogContent id="report-dialog-content">

                <div id="reportCommentForm">
                    <div className="bottomMargin">
                        <h2>Help us understand the problem. What is going on with this Tweet?</h2>
                    </div>
                    <div className="input-form">
                        <label htmlFor="report_comment">we appreciate your input</label>
                        <InputWithCounter numChar="250"
                                          inputType="text"
                                          placeHolder="Please write the reason you want to report this tweet,
                            Our support team will review your concern and take and action"
                                          maxChar="255"
                                          rows="5"
                                          name="report_comment"
                                          updateMethod={e=> setComment( e.target.value)}
                                        />
                    </div>
                    <div className="bottomMargin">
                        <Button className="report-button" onClick={handleReport}>Report</Button>
                    </div>

                </div>
            </DialogContent>
        </Dialog>
    );
}

export default ReportDialog