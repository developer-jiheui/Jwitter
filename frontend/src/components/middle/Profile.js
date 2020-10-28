import React from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'

import axios from 'axios';
import BackIcon from '@material-ui/icons/ArrowBack';
import CalendarIcon from '@material-ui/icons/DateRange';
import BdayIcon from '@material-ui/icons/CakeOutlined';


import {Button} from "@material-ui/core";


function Profile() {
    return (
        <div className="main">
            <div className="profile_header">
                <BackIcon className="backArrow"/>
                <span className="userName">userName</span>
                <span className="numOfTweets">123 Tweets</span>
            </div>
            <div className ="profileSubheader">
                <div className="cover-photo"></div>
                <div className="profile-image"></div>
                <Button className="edit_btn">Edit Profile</Button>
            </div>
            <div className="profileInfo">
                <div className ="userName">userName</div>
                <div className ="jiterId">@jitter</div>
                <div className ="bio">My bio is something something</div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <BdayIcon></BdayIcon>
                        Born July 22, 1991
                    </span>
                    <span className="userProfileInfo">
                        <CalendarIcon className="iconAlignment"></CalendarIcon>
                        Joined February 2014
                    </span>
                </div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <span className="followingNum">238</span>
                        Following
                    </span>
                    <span className="userProfileInfo">
                        <span className="followerNum">1037</span>
                        Follower
                    </span>
                </div>
            </div>
            <div className ="tweetType">
                <span className="tweetTypeAlignment">Tweet</span>
                <span className="tweetTypeAlignment">Tweets& replies</span>
                <span className="tweetTypeAlignment">Media</span>
                <span className="tweetTypeAlignment">Likes</span>

            </div>
            <div className="endOfProfileSubheader">
            </div>
            <div>
                <Post/>
                <Post/>
                <Post/>
                <Post/>
            </div>
        </div>
    )
}

export default Profile
