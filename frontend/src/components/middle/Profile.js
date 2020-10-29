import React from 'react'
import ProfileTabs from "./ProfileTabs";

import BackIcon from '@material-ui/icons/ArrowBack';
import CalendarIcon from '@material-ui/icons/DateRange';
import BdayIcon from '@material-ui/icons/CakeOutlined';

import {Button} from "@material-ui/core";
import axios from 'axios';

import EditProfileDialogue from "./EditProfileDialog";

function Profile(props) {
    let currUser = async () => {
        axios.get('/api/auth/get-profile', {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
        }).then((resp) => {
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

    const userAvatarURLCSS = user.avatar ? {background: "url('" + user.avatar + "')"} : {background: "grey"};

    return (
        <div className="main">
            <div className="profile_header">
                <BackIcon className="backArrow"/>
                <span className="userName">{user.username}</span>
                <span className="numOfTweets">{Math.floor(Math.random() * 9999)} Tweets</span>
            </div>
            <div className ="profileSubheader">
                <div className="cover-photo" style={userAvatarURLCSS}/>
                <div className="profile-image" style={userAvatarURLCSS}/>
                <Button className="edit_btn" onClick={handleClickOpen}>Edit Profile</Button>
            </div>
            <div className="profileInfo">
                <div className ="userName">{user.username}</div>
                <div className ="jiterId">@{user.username}</div>
                <div className ="bio">{user.bio}</div>
                <div className ="profileInfoSection">
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
            <EditProfileDialogue onClose={onClose} open={editProfileOpen} user={user} />
        </div>
    )
}

export default Profile
