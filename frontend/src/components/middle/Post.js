import React, { useState, useEffect } from 'react'
import { Avatar, Tooltip } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import RepeatIcon from "@material-ui/icons/Repeat";
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import BookmarkIcon from '@material-ui/icons/Bookmark';
import BlockIcon from '@material-ui/icons/Block';
import axios from 'axios';

const Post = ({ tweet_data, user, postOnClick, viewOnly }) => {
  const [like, setLike] = useState(false)
  const [share, setShare] = useState(false)
  const [tweetData, setTweetData] = useState(tweet_data)
  const [bookMarked, setBookMarked] = useState(tweetData.bookMarked)
  useEffect(() => {
    init()
  }, [tweetData]);
  const viewPost = () => {
    postOnClick(tweetData, user)
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
      alert(JSON.stringify(r.response.data));
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
  // const toggleLike=(like) =>{

  // }
  //const [like,setLike] =useState(tweet_data.likes);
  return (
    <div className="post">
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
            </h3>
          </div>
          <div className="post_headerDescription">
            <p>{tweetData.content}</p>
            {tweetData.photo && <img src={tweetData.photo} alt="" width="400px" style={{"maxHeight":"200px","objectFit":"cover" }}/>}
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
          {bookMarked ? <BlockIcon fontSize="small" />:
              <Tooltip title="BookMark"><BookmarkIcon onClick={()=>bookMark()} fontSize="small" /></Tooltip>
           }
        </div>
      </div>
    </div>
  );
};

export default Post;

