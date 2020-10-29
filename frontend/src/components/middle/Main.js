import React,{ useEffect,useState } from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
import axios from 'axios';
//import { useStateValue } from "../../utils/StateProvider";
function Main() {
    const [tweets, setTweets] = useState([]);
    const [user, setUser] = useState();
    //const [{user}] = useStateValue();
    useEffect(() => {
        console.log("HERE");
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'post',
            url: '/api/auth/userInfo',
            headers: {
              Authorization: bearer
            }
          }).then(resp => {
            setUser(resp.data)
            axios.get(`/api/auth/tweets/${resp.data.id}`)
                .then((res)=>{
                    setTweets(res.data)
                }).catch(r=>{
                    console.log(r);
                    alert(r);
                });
          }).catch(r => {
            console.log(r)
            alert(JSON.stringify(r.response.data));
          });  
      },[]);
/*
.then(res => {
              console.log(res)
            //create an image
           // let post_id = JSON.parse(JSON.stringify(res.data))
            //let fd = new FormData()
            //fd.append('photo', tweet.photo)
            //return axios.post('/api/tweet_photo/upload/' + post_id.id, fd)
          }) */
    return (
        <div className="main">
            <div className="main_header"> 
            <h1>Home</h1>
            </div>
            <div>
                <Tweet user={user}/>
            </div>
            <div>
                {tweets.map((tweet,index)=>{
                   return <Post key={index} tweet_data={tweet} user={user}/>
                })}
     
            </div>
        </div>
    )
}

export default Main
