import React,{ useEffect,useState } from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
import axios from 'axios';
import CommentDialog from './CommentDialog';
//import { useStateValue } from "../../utils/StateProvider";
function Main() {
    const [tweets, setTweets] = useState([]);
    const [user, setUser] = useState();
    const [viewComments, setViewComments] = useState(false);
    const [leaveComment, setLeaveComment] = useState(false);
    const [postData, setPostData] = useState({tweet_data:{id:""}});
    let viewPost ={type:"", tweet_data:{}, comment:[],main_post_user:{avatar:""}}
    //const [{user}] = useStateValue();
    const tweetOnChange =(func) =>{
        if (func == "tweet"){
            getTweets();
        }
    }

    const postOnClick =(func,data, main_post_user) =>{
        viewPost={type: func, tweet_data: data, main_post_user:main_post_user}
        setPostData(viewPost)
        if(func == "viewAll"){
            //setViewComment({type: func, tweet_data: data, main_post_user:main_post_user})
            getComment(viewPost);
        }else if(func == "viewComment"){
            setLeaveComment(!leaveComment)
            console.log(postData)
        }
    }

    const handleClose=()=>{
        setLeaveComment(!leaveComment)
      }

    useEffect(() => {
        getTweets();
      },[]);

    const getTweets = () =>{
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'post',
            url: '/api/auth/userInfo',
            headers: {
              Authorization: bearer
            }
          }).then(resp => {
            setUser(resp.data)
            axios.get(`/api/auth/posts/${resp.data.id}`,{
                headers: {
                    Authorization: bearer
                }
            }).then((res)=>{
                    setTweets(res.data)
                }).catch(r=>{
                    console.log(r);
                    alert(r);
                });
          }).catch(r => {
            console.log(r)
            alert(JSON.stringify(r.response.data));
          });  
    }

    const getComment = (viewPost) =>{
        axios.get(`/api/auth/comments/${viewPost.tweet_data.id}`, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
        }).then((res)=>{
            //setViewComment({...viewComment ,comment:res.data})
            viewPost.comment=res.data
            setViewComments(true)
        }).catch(r=>{
            console.log(r);
            alert(r);
        });
    }
    return (
        <div className="main">
            <div className="main_header"> 
            <h1>Home</h1>
            </div>
            <div className={!viewComments?"show":"hidden"}>
                <Tweet user={user} tweetOnChange={tweetOnChange} reply={null}/>
            </div>
            <div>
            {tweets.map((tweet,index)=>{
                   return <Post key={index} tweet_data={tweet.post} user={tweet.user} postOnClick={postOnClick}/>
                })}
                {/* {viewComment.type=="viewAll"?
                tweets.map((tweet,index)=>{
                   return <Post key={index} tweet_data={tweet} user={user} postOnClick={postOnClick}/>
                }):  
                <Post key={index} tweet_data={viewComment.tweet_data} user={user} postOnClick={postOnClick}/> 
                viewComment.comment.map((comment,index)=>{
                    return <Post key={index} tweet_data={comment} user={user} postOnClick={postOnClick}/>
                 })
                } */}
            </div>
            
            <div className={viewComments? "show":"hidden"}> 
                <Post tweet_data={viewPost.tweet_data} user={viewPost.main_post_user} postOnClick={postOnClick}/>
                {viewPost.comment.map((c,index)=>{
                    return <Post key={index} tweet_data={c.post} user={c.user} postOnClick={postOnClick}/>
                 })
                }
            </div>
            <CommentDialog 
            open={leaveComment} 
            main_post={postData.tweet_data} 
            main_post_user={postData.main_post_user} 
            comment_user={user} 
            onClose={handleClose} />
        </div>
    )
}

export default Main
