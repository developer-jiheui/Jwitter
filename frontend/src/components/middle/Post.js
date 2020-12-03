import React, { useState, useEffect } from 'react'
import { Avatar, Tooltip } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import RepeatIcon from "@material-ui/icons/Repeat";
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import BookmarkBorderOutlinedIcon from '@material-ui/icons/BookmarkBorderOutlined';
import BookmarkIcon from '@material-ui/icons/Bookmark';
import MoreHorizOutlinedIcon from '@material-ui/icons/MoreHorizOutlined';
import DeleteForeverOutlinedIcon from '@material-ui/icons/DeleteForeverOutlined';
import FlagOutlinedIcon from '@material-ui/icons/FlagOutlined';
import axios from 'axios';
import IconButton from "@material-ui/core/IconButton";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import ReportDialog from "../admin/ReportDialog";
import ReactHashtag from "react-hashtag";
import EditProfileDialogue from "./EditProfileDialog";

const Post = ({ tweet_data, user, postOnClick, viewOnly, currUser }) => {
  const [like, setLike] = useState(false)
  const [share, setShare] = useState(false)
  const [tweetData, setTweetData] = useState(tweet_data)
  const [bookMarked, setBookMarked] = useState(tweet_data.bookMarked)
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [reportDialogOpen,setReportDialogOpen] = React.useState(false);
  // const [currentUser, setCurrentUser] = useState();

  useEffect(() => {
    init()
  }, [tweetData]);
  const viewPost = () => {
    postOnClick(tweetData, user)
  }

  const deleteBookMark = () => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios({
      method: 'delete',
      url: `/api/bookmarks/${tweet_data.id}`,
      headers: {
        Authorization: bearer
      },
      data: {
      }
    }).then(() => {
      setBookMarked(false)
    }).catch(r => {
      console.log(r)
    });
  }
  const bookMark = () => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios({
      method: 'post',
      url: `/api/bookmarks/${tweet_data.id}`,
      headers: {
        Authorization: bearer
      },
      data: {
      }
    }).then(() => {
      setBookMarked(true)
    }).catch(r => {
      console.log(r)
    });
  }


  const toggleShare = (share) => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios.get(`/api/auth/toggleShare/${tweetData.id}/${!share}`, {
      headers: {
        Authorization: bearer
      }
    }).then((res) => {
      setShare(!share)
      setTweetData({ ...tweetData, shares: res.data })
    }).catch(r => {
      console.log(r);
      alert(r);
    });
  }
  const toggleLike = (like) => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios.get(`/api/auth/toggleLike/${tweetData.id}/${!like}`, {
      headers: {
        Authorization: bearer
      }
    }).then((res) => {
      setLike(!like)
      setTweetData({ ...tweetData, likes: res.data })
    }).catch(r => {
      console.log(r);
      alert(r);
    });
  }
  const init =() =>{
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios.get(`/api/auth/likeNShare/${tweetData.id}`,{
      headers: {
          Authorization: bearer
      }
    }).then((res)=>{
          setLike(res.data.like)
          setShare(res.data.share)
      }).catch(r=>{
          console.log(r);
          alert(r);
      });
  }
  //const showMenu=() =>{}
  //const [like,setLike] =useState(tweet_data.likes);


  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const deletePost = (e) =>{
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'delete',
      url: '/api/auth/tweets/' + tweet_data.id,
      headers: {
        Authorization: bearer
      }
    }).then(resp => {
      if(resp.data==true){

      }
    }).catch(r => {
      console.log(r);
    });
  }
  const reportPost = () => {
    setReportDialogOpen(true);
  }

  const onReportClose = (hasChanged) => {
    setReportDialogOpen(false);
  }

  return (
    <div className="preact-hashtagost">
      <div className="post_avatar">
        <Avatar src={user.avatar} />
      </div>
      <div className="post_body">
        <div className="post_header">
          <div className="post_headerText">
            <h3>
              {user.username}{" "}
              <span className="post_headerSpecial">
                <VerifiedUserIcon className="post_badge" />
              </span>
              {(!viewOnly ) &&
              <IconButton className="postOptions"  aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                <MoreHorizOutlinedIcon/>
              </IconButton>
               }
              <Menu
                  id="simple-menu"
                  anchorEl={anchorEl}
                  keepMounted
                  open={Boolean(anchorEl)}
                  onClose={handleClose}
              >
                {
                  ( currUser.id === tweet_data.user_id)
                  &&
                  <MenuItem className="postMenu" onClick={deletePost}>
                    <DeleteForeverOutlinedIcon style={{"marginRight": "5px"}}/>
                    Delete
                  </MenuItem>
                }
                {
                  (currUser.id != tweet_data.user_id)
                  &&
                    <MenuItem className="postMenu" onClick={reportPost} >
                      <FlagOutlinedIcon style={{"marginRight": "5px"}}/>
                      Report
                    </MenuItem>}
              </Menu>
            </h3>
          </div>
          <div className="post_headerDescription">
            <ReactHashtag onHashtagClick={val => alert(val)}>{tweetData.content}</ReactHashtag>
            {tweetData.photo && <img className="tweet_photo" src={tweetData.photo} alt=""/>}
          </div>
        </div>
        <div className={viewOnly ? "hidden" : "post_footer"}>
          <button type='button' onClick={() => { viewPost("viewComment") }} >
            <ChatBubbleOutlineIcon fontSize="small" />
            <span>{tweetData.comments}</span>
          </button>
          <button type='button' onClick={() => toggleShare(share)} >
            <RepeatIcon fontSize="small" className={share ? "share" : ""} />
            <span>{tweetData.shares}</span>
          </button>
          <button type='button' onClick={() => toggleLike(like)} >
            <FavoriteBorderIcon fontSize="small" className={like ? "like" : ""} />
            <span>{tweetData.likes}</span>
          </button>
          {bookMarked ? <Tooltip title="Delete bookmark"><BookmarkIcon  fontSize="small" onClick={()=>deleteBookMark()} /></Tooltip>:
              <Tooltip title="BookMark"><BookmarkBorderOutlinedIcon onClick={()=>bookMark()} fontSize="small" /></Tooltip>
           }
        </div>
        <ReportDialog onReportClose={onReportClose} open={reportDialogOpen} tweet_id={tweet_data.id} user_id={currUser.id} />

      </div>
    </div>
  );
};

export default Post;

