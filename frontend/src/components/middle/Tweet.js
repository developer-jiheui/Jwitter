import React, {useState} from 'react'
import { Avatar, Button } from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';
import PhotoCamera from '@material-ui/icons/PhotoCamera';
import axios from 'axios';
const Tweet= ({user}) => {
    const [tweet, setTweet] = useState({
        content:'',
        photo:null
    });
    const upload =(photo) =>{
        console.log(photo)
        let fd = new FormData();
        fd.append('file', photo);
        axios.post('/api/avatars/photoUpload', fd)
        .then(res=>{
            console.log(res)
                setTweet({...tweet, photo:`http://45.76.207.32:8081/${res.data}`})
            }
        )
    }
    const post =()=>{
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'post',
            url: '/api/auth/tweet',
            headers: {
              Authorization: bearer
            },
            data: {
                content: tweet.content,
                photo: tweet.photo
            }
          }).then(res => {
              console.log(res)
            //create an image
           // let post_id = JSON.parse(JSON.stringify(res.data))
            //let fd = new FormData()
            //fd.append('photo', tweet.photo)
            //return axios.post('/api/tweet_photo/upload/' + post_id.id, fd)
          }).then(resp => {
            alert("Succss");
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
                        <textarea id="tweet_box" placeholder="What's happening?" type="text" onChange={e=> setTweet({
                            ...tweet, 
                            content: e.target.value
                        }
                    )} />
                    <img src={tweet.photo==null?'':tweet.photo}/>
                    </div>
                </div>
                <div class="tweet_btn_area">
                    <input className="tweet_btn_box tweet_btn" id="file" type="file"
                     onChange={(e) => upload( e.target.files[0])}/>
                    <label htmlFor="file">
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

export default Tweet
