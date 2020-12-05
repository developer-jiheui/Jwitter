import React, { useState, useEffect } from 'react'
import { Avatar, Button } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
import axios from "axios";
const Card = ({user}) => {
  const [followText, setFollowText] = useState("Follow")
    const [currUser, setCurrUser] = React.useState({id:0});
    const [following, setFollowing] = useState([]);
    const [follower, setFollower] = useState([]);

    useEffect(() => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios.get('/api/auth/get-profile', {
            headers: {
                Authorization: bearer
            }
        }).then((resp) => {
            setCurrUser(resp.data);
            getFollower(resp.data.id);
            getFollowing(resp.data.id);
            console.log("LOOK THIS IS USER_ID!!!!", resp.data.id);
        }).catch(error => {
            console.log(error);
        });
    },[]);


    const toggleFollow =(followText) =>{
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'put',
            url: '/api/auth/add-following/' + user.id,
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            alert(resp.data);
        }).catch(r => {
            console.log(r);
        });

        // if(followText ==='Follow'){
        //     //do something
        //     setFollowText("Following")
        // }else{
        //      //do something
        // }
    }

    const getFollowing = (user_id) => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/auth/get-following/' + user_id,
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            setFollowing(resp.data.id);
            if(following.includes(user.id)) {
                console.log("CURRENT USER IS FOLLOWING THIS DUDE")
            setFollowText("Following")
            }
            else
                setFollowText("Follow")

            console.log("GET FOLLOWING HAPPENED");
            console.log("CHECK THE DATA", resp.data);
            console.log("CHECK THE DATA ID", resp.data.id);

            console.log("FOLLOWING : ", following);
        }).catch(r => {
            console.log(r);
        });
    }

    const getFollower = (user_id) => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/auth/get-follower/' + user_id,
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            setFollower(resp.data);
        }).catch(r => {
            console.log(r);
        });
    }



     const followButtonCSS = followText.match("Following") ? {background:"white"}:{background: "primary"};



    return (
    <div className="post">
      <div className="post_avatar">
        <Avatar src={user.avatar} />
      </div>
      <div className="post_body">
        <div className="post_header">
          <div className="post_headerText">
            <h3>
              {user.username}{" "}
              <span className="post_headerSpecial">
                <VerifiedUserIcon className="post_badge" />
              </span>
              <Button variant="outlined" onClick={() => { toggleFollow() }} color="primary" style={followButtonCSS}>
                    {followText}
                </Button>
            </h3>
            <p>{user.bio}</p>
          </div>
          </div>
        </div>
    </div>
  );
};

export default Card;

