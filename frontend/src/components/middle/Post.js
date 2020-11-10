import React from 'react'
import { Avatar } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import RepeatIcon from "@material-ui/icons/Repeat";
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import PublishIcon from "@material-ui/icons/Publish";

const Post = ({ tweet_data ,user, postOnClick, viewOnly}) => {
  const viewPost =(func) =>{
    postOnClick(func,tweet_data,user)
  }
 // const toggleLike=(like) =>{
    
 // }
  //const [like,setLike] =useState(tweet_data.likes);
      return (
        <div className="post">
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
              <div className="post_headerDescription" onClick={()=>{viewPost("viewAll")}}>
                  <p>{tweet_data.content}</p>
                  {tweet_data.photo && <img src={tweet_data.photo} alt="" width="400px"/> }
              </div>
            </div>
            <div className={viewOnly?"hidden":"post_footer"}>
              <button type='button' onClick={()=>{viewPost("viewComment")}} >
                <ChatBubbleOutlineIcon fontSize="small"/>
                <span>{tweet_data.comments}</span>
               </button>
              <RepeatIcon fontSize="small" />
              <div className="iconText">l
              <FavoriteBorderIcon fontSize="small" />
              </div>
              <PublishIcon fontSize="small" />
            </div>
          </div>
        </div>
      );
    };
  
  export default Post;
  
