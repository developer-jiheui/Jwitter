
import React from "react";
import { TextField,Button,Input } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import { forgetPwdUrl } from "../../utils/data";

function Login() {
  return (
    <>
    <div className="login">

        <div className="paper">
          <Paper elevation={3} className="login_ad">
            <div className="login_sign">
                <h1>Join Twitter today</h1>
                <div className="login_input">
                  <input placeholder="Phone, Account, or Email" type="text"/>
                  <input placeholder="password" type="text" />
                  <Button className="tweet_btn">Log in</Button>
                  <Button className="tweet_btn">Sign up</Button>
                  <a href={forgetPwdUrl}>Forget Password</a>
                </div>
              </div>
          </Paper> 
        </div>
    </div>
    </>
  )
}

export default Login;