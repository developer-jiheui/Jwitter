import React from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
import axios from 'axios';

function Profile() {
    return (
        <div className="profile">
            <div className="profile_header">
                <h1>userName</h1>
                <p>number of Tweets</p>
            </div>
            <div>
                <Tweet/>
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
