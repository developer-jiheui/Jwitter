import React,{useState,useEffect} from 'react'
import { Avatar } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import RepeatIcon from "@material-ui/icons/Repeat";
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import PublishIcon from "@material-ui/icons/Publish";
import axios from 'axios';

const Post = ({ tweet_data ,user, postOnClick, viewOnly}) => {
  const [like, setLike]= useState(false)
  const [share, setShare]= useState(false)
  useEffect(() => {
    init()
    console.log(tweet_data)
    console.log(user)
  },[user]);
  const viewPost =(func) =>{
    postOnClick(func,tweet_data,user)
  }
  const toggleShare=(share)=>{
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios.get(`/api/auth/toggleShare/${tweet_data.id}/${!share}`,{
      headers: {
          Authorization: bearer
      }
    }).then((res)=>{
          setShare(!share)
          tweet_data.shares=res.data
      }).catch(r=>{
          console.log(r);
          alert(r);
      });
  }
  const toggleLike=(like)=>{
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios.get(`/api/auth/toggleLike/${tweet_data.id}/${!like}`,{
      headers: {
          Authorization: bearer
      }
    }).then((res)=>{
          setShare(!like)
          tweet_data.likes=res.data
          console.log(share)
          console.log(tweet_data)
      }).catch(r=>{
          console.log(r);
          alert(r);
      });
  }
  const init =() =>{
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')))
    axios.get(`/api/auth/likeNShare/${tweet_data.id}`,{
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
               <button type='button' onClick={()=>toggleShare(share)} >
                  <RepeatIcon fontSize="small" className={share?"share":""}/>
                <span>{tweet_data.shares}</span>
               </button>
               <button type='button' onClick={()=>toggleLike(like)} >
                  <FavoriteBorderIcon fontSize="small" className={like?"like":""}/>
                <span>{tweet_data.likes}</span>
               </button>
              <PublishIcon fontSize="small" />
            </div>
          </div>
        </div>
      );
    };
  
  export default Post;
  
