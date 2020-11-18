import React,{ useEffect,useState } from 'react'
import Post from '../middle/Post'
import axios from 'axios';

function Main() {
    const [bookMarks, setBookmarks] = useState([]);
    useEffect(() => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/bookmarks',
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            setBookmarks(resp.data)
        }).catch(r => {
            console.log(r)
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
                <h1>BookMarks</h1>
            </div>
            <div>
                {bookMarks.map((bookMark,index)=>{
                    return <Post key={index} tweet_data={bookMark.tweet} user={bookMark.creator} currUser={bookMark.creator}/>
                })}

            </div>
        </div>
    )
}

export default Main