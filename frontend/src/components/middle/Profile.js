import React, {useEffect, useState} from 'react'
import ProfileTabs from "./ProfileTabs";

import BackIcon from '@material-ui/icons/ArrowBack';
import CalendarIcon from '@material-ui/icons/DateRange';
import BdayIcon from '@material-ui/icons/CakeOutlined';
import LocationOnOutlinedIcon from '@material-ui/icons/LocationOnOutlined';
import LanguageOutlinedIcon from '@material-ui/icons/LanguageOutlined';

import {Button} from "@material-ui/core";
import axios from 'axios';

import EditProfileDialogue from "./EditProfileDialog";
import CommentDialog from "./CommentDialog";

function Profile() {
    const [editProfileOpen, setEditProfileOpen] = React.useState(false);
    const [user, setUser] = React.useState({id:0});
    const [myTweets, setTweets] = useState([]);
    const [tweetsAndReplies, setTandR] = useState([]);
    const [tweetLike, setLikes] = useState([]);
    const [following, setFollowing] = useState([]);
    const [follower, setFollower] = useState([]);


    useEffect(() => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios.get('/api/auth/get-profile', {
            headers: {
                Authorization: bearer
            }
        }).then((resp) => {
            setUser(resp.data);
            getMyTweets(resp.data.id);
            getTweetsAndReplies(resp.data.id);
            getLikes(resp.data.id);
            getFollower(resp.data.id);
            getFollowing(resp.data.id);
        }).catch(error => {
            console.log(error);
        });
    },[]);


    const getMyTweets = (user_id) => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/auth/tweets/' + user_id,
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            setTweets(resp.data);
        }).catch(r => {
            console.log(r);
        });
    }

    const getTweetsAndReplies = (user_id) => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/auth/tweetsAndReplies/' + user_id,
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            setTandR(resp.data);
        }).catch(r => {
            console.log(r);
        });
    }

    const getLikes = (user_id) => {
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: 'get',
            url: '/api/auth/tweetslike/' + user_id,
            headers: {
                Authorization: bearer
            }
        }).then(resp => {
            setLikes(resp.data);
        }).catch(r => {
            console.log(r);
        });
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
            setFollowing(resp.data);
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

    const handleClickOpen = () => {
        setEditProfileOpen(true);
    }

    const onClose = (hasChanged) => {
        setEditProfileOpen(false);
        if(hasChanged) {
            axios.get('/api/auth/get-profile', {
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
            }).then((resp) => {
                setUser(resp.data);
            }).catch(error => {
                console.log(error);
            });
        }
    }



    const userAvatarURLCSS = user.avatar ? {backgroundImage: "url('" + user.avatar + "')"} : {background: "grey"};
    const coverPhotoURLCSS = user.coverPhoto ? {backgroundImage: "url('" + user.coverPhoto + "')"} : {background: "grey"};

    return (
        <div className="main">
            <div className="profile_header">
                <BackIcon className="backArrow"/>
                <span className="userName">{user.username}</span>
                <span className="numOfTweets">{myTweets.length} Tweets</span>
            </div>
            <div className ="profileSubheader">
                <div className="cover-photo" style={coverPhotoURLCSS}/>
                <div className="profile-image" style={userAvatarURLCSS}/>
                <Button className="edit_btn" onClick={handleClickOpen}>Edit Profile</Button>
            </div>
            <div className="profileInfo">
                <div className ="userName">{user.username}</div>
                <div className ="jiterId">@{user.username}</div>
                <div className ="bio">{user.bio}</div>
                <div className ="profileInfoSection">
                    {user.location &&
                        <span className="userProfileInfo">
                        <LocationOnOutlinedIcon/>
                            {user.location}
                        </span>
                    }
                    {user.website &&
                        <span className="userProfileInfo">
                        <LanguageOutlinedIcon/>
                        <a href = {"https://"+user.website}>{user.website}</a>
                        </span>
                    }
                    <span className="userProfileInfo">
                        <BdayIcon/>
                        Born {user.birthday}
                    </span>
                    <span className="userProfileInfo">
                        <CalendarIcon className="iconAlignment"/>
                        Joined {user.joinday}
                    </span>
                                    </div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <span className="followingNum">{following.length}</span>
                        Following
                    </span>
                    <span className="userProfileInfo">
                        <span className="followerNum">{follower.length}</span>
                        Follower
                    </span>
                </div>
            </div>
            <ProfileTabs tweets={myTweets} tandR = {tweetsAndReplies} tweetLike={tweetLike} user = {user}/>
            <EditProfileDialogue onClose={onClose} open={editProfileOpen} user={user} />

        </div>
    )
}

export default Profile
