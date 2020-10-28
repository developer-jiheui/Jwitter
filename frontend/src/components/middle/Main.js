import React,{ useEffect } from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
import axios from 'axios';
function Main() {
    const [tweets, setTweets] = useState({});
    useEffect(() => {
        axios.get(`/api/auth/tweets/1`)
        .then((resp)=>{
            setTweets(resp);
            console.log(resp.data);
          }).catch(r=>{
            console.log(r);
            alert(r);
          });
      });

    return (
        <div className="main">
            <div className="main_header"> 
            <h1>Home</h1>
            </div>
            <div>
                <Tweet/>
            </div>
            <div>
                {tweets.map(tweet =>{
                    <Post tweet_data={tweet}/>
                })}
            </div>
        </div>
    )
}

export default Main
