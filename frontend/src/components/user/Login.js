
import React, {useState} from "react";
import { Redirect, useHistory } from "react-router-dom";
import { Button } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import SignupDialog from "../user/SignupDialog";
import { useStateValue } from "../../utils/StateProvider";
import axios from 'axios';
function Login() {
  
  const history = useHistory();
  
  const [user, setUser] = useState({
      account:null,
      password:null
  });
  const forgetPwd=() =>{
    history.push("/forgetPwd");
  }
  const [signDialog, setSignDialog] = useState(false);
  const Login = () =>{
    axios.post('/api/auth/sign-in',{
      email:user.account,
      password:user.password
    }).then((resp)=>{
      console.log(resp.data);
      localStorage.setItem('jwt',resp.data);
      history.push("/home")
      //todo go to home page
    }).catch(r=>{
      console.log(r);
      alert("Bad credentials");
    });
  }
  const handleClose=()=>{
    console.log(signDialog)
    setSignDialog(!signDialog)
  }
  return (
    <div className="login">
        <div className="paper">
          <Paper elevation={3} className="login_ad">
            <div className="login_sign">
                <h1>Join Twitter today</h1>
                <div className="login_input">
                  <input placeholder="Phone, Account, or Email" type="text" onChange={e=> setUser({
                            ...user, 
                            account: e.target.value
                        }
                    )}/>
                  <input placeholder="password" type="text" onChange={e=> setUser({
                            ...user, 
                            password: e.target.value
                        }
                    )}/>
                  <Button className="tweet_btn" onClick={Login}>Log in</Button>
                  <Button className="tweet_btn" onClick={handleClose}>Sign up</Button>
                  <Button color="primary" onClick={forgetPwd}>Forget Password</Button>
                </div>
              </div>
          </Paper> 
        </div>
        <SignupDialog open={signDialog} onClose={handleClose} />
    </div>
  )
}

export default Login;