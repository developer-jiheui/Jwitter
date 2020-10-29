import React from 'react'
import ProfileTabs from "./ProfileTabs";

import BackIcon from '@material-ui/icons/ArrowBack';
import CalendarIcon from '@material-ui/icons/DateRange';
import BdayIcon from '@material-ui/icons/CakeOutlined';

import {Button} from "@material-ui/core";
import axios from 'axios';

import EditProfileDialogue from "./EditProfileDialog";

function Profile(props) {
    const currUser = async () => {
        axios.get('/api/auth/profile', {
            headers: { 'content-type': 'application/x-www-form-urlencoded',
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
        }).then((resp) => {
            console.log(resp);
            setUser(resp.data);
        }).catch(error => {
            console.log(error);
        });
    }

    const [editProfileOpen, setEditProfileOpen] = React.useState(false);
    const [user, setUser] = React.useState(currUser);

    const handleClickOpen = () => {
        setEditProfileOpen(true);
    }

    const handleClose = (value) => {
        setEditProfileOpen(false);
    };

    const userAvatarURLCSS = {backgroundImage: "url('" + user.avatar + "')"};

    return (
        <div className="profile">
            <div className="profile_header">
                <BackIcon className="backArrow"/>
                <span className="userName">{user.username}</span>
                <span className="numOfTweets">{Math.floor(Math.random() * 9999)} Tweets</span>
            </div>
            <div className ="profileSubheader">
                <img alt="cover photo" className="cover-photo" src={user.avatar}/>
                <div className="profile-image" style={userAvatarURLCSS}/>
                <Button className="edit_btn" onClick={handleClickOpen}>Edit Profile</Button>
            </div>
            <div className="profileInfo">
                <div className ="userName">{user.username}</div>
                <div className ="jiterId">@{user.username}</div>
                <div className ="bio">{user.bio}</div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <BdayIcon></BdayIcon>
                        Born {user.birthday}
                    </span>
                    <span className="userProfileInfo">
                        <CalendarIcon className="iconAlignment"></CalendarIcon>
                        Joined 2019-05-22
                    </span>
                </div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <span className="followingNum">{Math.floor(Math.random() * 500)}</span>
                        Following
                    </span>
                    <span className="userProfileInfo">
                        <span className="followerNum">{Math.floor(Math.random() * 500)}</span>
                        Follower
                    </span>
                </div>
            </div>
            <ProfileTabs />
            <EditProfileDialogue open={editProfileOpen} onClose={handleClose} user={user}/>
        </div>
    )
}

export default Profile
