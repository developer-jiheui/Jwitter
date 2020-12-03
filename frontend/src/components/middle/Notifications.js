import React,{ useEffect,useState } from 'react'
import Post from '../middle/Post'
import axios from 'axios';

function Main() {
    const [notifications, setNotifications] = useState([]);
    useEffect(() => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/notifications',
            headers: {
              Authorization: bearer
            }
          }).then(resp => {
            setNotifications(resp.data)
          }).catch(r => {
          console.log(r)
          });  
      },[]);

    return (
        <div className="main">
            <div className="main_header"> 
            <h1>Notifications</h1>
            </div>
            <div>
                {notifications.map((notification,_)=>{
                   return <div className="post">
						User &#160;<a href="">@{notification.username} </a> 
						&#160;liked your Tweet&#160;
						<a href=""> {notification.tweet} </a>
								<hr/>
				   </div>
                })}
     
            </div>
        </div>
    )
}

export default Main
