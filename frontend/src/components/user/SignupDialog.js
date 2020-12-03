import React, { useState } from 'react'
import Button from '@material-ui/core/Button';
import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import TextField from '@material-ui/core/TextField';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import axios from 'axios';
// const handleClose=() =>{
//     console.log("23")
// }

function SignupModal(props) {
  const { onClose, open } = props;
  const [user, setUser] = useState({
    name: null,
    email: null,
    birth: null,
    avatar: null,
    password: null,
  });
  const [signUpMethod, setSignUpMethod] = useState({ method: "email" });
  const handleClose = () => {
    onClose(open);
  };
  const submit = () => {
    if (user.avatar === null) {
      alert("Uploda avatar");
    } else {
      axios.post('/api/auth/sign-up', {
        email: user.email,
        username: user.name,
        birthday: user.birth,
        password: user.password
      }).then(res => {
        //create an image
        let createdUser = JSON.parse(JSON.stringify(res.data))
        console.log(createdUser)
        let fd = new FormData();
        fd.append('file', user.avatar);
        return axios.post('/api/avatars/upload/' + createdUser.id, fd)
      }).then(resp => {
        handleClose();
        alert("User was created");
      }).catch(r => {
        console.log(r)
        alert(JSON.stringify(r.response.data));
      });
    }
    /* axios.post('/sign-up', {
      email: user.email,
      username: user.name,
      birthday: user.birth,
      password: user.password
    }).then(res => {
      const body = JSON.parse(res.data);
      const formData = new FormData();
      formData.append("image", imagefile.files[0]);
      axios.post('uploadFile/'+body.id, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res=>{
        this.props.history.push("/login");
      })
    }).catch(res=>{
      alert(res.body);
    })
    onClose(open); */
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Create your account</DialogTitle>
      <DialogContent>
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Name"
          type="text"
          fullWidth
          onChange={(e) => setUser({
            ...user,
            name: e.target.value
          }
          )}
        />
        <TextField
          autoFocus
          margin="dense"
          id="password"
          label="Password"
          type="password"
          fullWidth
          onChange={(e) => setUser({
            ...user,
            password: e.target.value
          }
          )}
        />
        <TextField
          autoFocus
          margin="dense"
          id="email"
          label="Email"
          type="email"
          fullWidth
          onChange={(e) => setUser({
            ...user,
            email: e.target.value
          }
          )}
        />
        <label name="file">Avatar</label>
        <input className="fileInput"
          type="file"
          id="file"
          onChange={(e) => setUser({
            ...user,
            avatar: e.target.files[0]
          })} />
      </DialogContent>
      <DialogContent>
        <h4>Birth of date</h4>
        <DialogContentText>
          This will not be shown pubicly. Comfirm your own age, even if this account is for bussiness, a pet, or something else
        </DialogContentText>
        <TextField
          id="date"
          label="Birthday"
          type="date"
          InputLabelProps={{
            shrink: true,
          }}
          onChange={(e) => setUser({
            ...user,
            birth: e.target.value
          }
          )}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Cancel
          </Button>
        <Button onClick={submit} color="primary">
          Submit
          </Button>
      </DialogActions>
    </Dialog>
  );
}

export default SignupModal