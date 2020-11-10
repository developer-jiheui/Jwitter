import React, { useState } from 'react'
import Button from '@material-ui/core/Button';
import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import TextField from '@material-ui/core/TextField';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';

import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
import DialogContentText from '@material-ui/core/DialogContentText';
import axios from 'axios';
// const handleClose=() =>{
//     console.log("23")
// }

function CommentDialog(props) {
    const {onClose, open, main_post,main_post_user, comment_user} = props
    console.log(props)
    const handleClose = () => {
        onClose(open);
  };
  const tweetOnChange=(func)=>{onClose(open);}
  const postOnClick=()=>{}
  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title" maxWidth="xl">
      <DialogTitle id="form-dialog-title">Add comment</DialogTitle>
      <DialogContent>
        <Post tweet_data={main_post} user={main_post_user} postOnClick={postOnClick} viewOnly={true}/>
        <Tweet user={comment_user} tweetOnChange={tweetOnChange} reply={main_post.id}/>
      </DialogContent>
      
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Cancel
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default CommentDialog