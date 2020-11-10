import React, {useState} from 'react'
import { Avatar, Button } from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';
import PhotoCamera from '@material-ui/icons/PhotoCamera';
import axios from 'axios';
const Tweet= ({user,tweetOnChange, reply}) => {
    const [tweet, setTweet] = useState({
        content:'',
        photo:null,
        reply_to_id:null
    });
    const upload =(photo) =>{
        let fd = new FormData();
        fd.append('file', photo);
        axios.post('/api/avatars/photoUpload', fd)
        .then(res=>{
                setTweet({...tweet, photo:`http://45.76.207.32:8081/${res.data}`})
            }
        )
    }
    const post =()=>{
        console.log(reply)
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'post',
            url: '/api/auth/tweet',
            headers: {
              Authorization: bearer
            },
            data: {
                content: tweet.content,
                photo: tweet.photo,
                reply_to_id: reply
            }
          }).then(resp => {
            setTweet({...tweet,
                content:'',
                photo:null,
                reply_to_id:null
            })
            tweetOnChange('tweet')
          }).catch(r => {
            console.log(r)
            alert(JSON.stringify(r.response.data));
          });

    }
    return (
        <div className="tweet">
            <form >
                <div className="tweetInput">
                    <Avatar alt="Remy Sharp" src={user==undefined?'':user.avatar} />
                    <div className="tweet_text">
                        <textarea id="tweet_box" placeholder="What's happening?" type="text" value={tweet.content} onChange={e=> setTweet({
                            ...tweet,
                            content: e.target.value
                        }
                    )} />
                    <img src={tweet.photo==null?'':tweet.photo} width="400px"/>
                    </div>
                </div>
                <div className="tweet_btn_area">
                    <input className="tweet_btn_box tweet_btn" id="file" type="file"
                     onChange={(e) => upload( e.target.files[0])}/>
                    <label htmlFor="file" className={!reply?"" :"hidden"}>
                        <IconButton color="primary" aria-label="upload picture" component="span">
                        <PhotoCamera />
                        </IconButton>
                    </label>
                    <Button className="tweet_btn_box tweet_btn" onClick={post}>Tweet</Button>
                </div>
            </form>
        </div>
    )
}

export default Tweet;
