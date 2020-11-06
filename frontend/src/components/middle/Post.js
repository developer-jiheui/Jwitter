import React from 'react'
import { Avatar } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import RepeatIcon from "@material-ui/icons/Repeat";
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import PublishIcon from "@material-ui/icons/Publish";

const Post = ({ tweet_data ,user, handleOnChange}) => {
  const viewPost =() =>{

  }
      return (
        <div className="post" onClick={e=>{viewPost}}>
          <div className="post_avatar">
            <Avatar src={user.avatar}/>
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
                  <p>{tweet_data.content}</p>
              </div>
            </div>
            {tweet_data.photo && <img src={tweet_data.photo} alt="" width="400px"/> }
            <div className="post_footer">
              <div className="iconText">
                <ChatBubbleOutlineIcon fontSize="small" onClick={comment}/>
                <span>{tweet_data.comments}</span>
              </div>
              <RepeatIcon fontSize="small" />
              <FavoriteBorderIcon fontSize="small" />
              <PublishIcon fontSize="small" />
            </div>
          </div>
        </div>
      );
    };
  
  export default Post;
  
