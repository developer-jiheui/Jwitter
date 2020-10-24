import React from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
function Main() {
    return (
        <div className="main">
            <div className="main_header"> 
            <h1>Home</h1>
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

export default Main
