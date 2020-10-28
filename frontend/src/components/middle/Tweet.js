import React from 'react'
import { Avatar, Button } from '@material-ui/core';
function Tweet() {
    return (
        <div className="tweet">
            <form >
                <div className="tweetInput">
                    <Avatar alt="Remy Sharp" src="https://icon-library.com/icon/img-icon-14.html" />
                    <div className="tweet_text">
                        <textarea id="tweet_box" placeholder="What's happening?" type="text" />
                    </div>
                </div>
                <div className="input_icon"></div>
                <Button className="tweet_btn_box tweet_btn">Tweet</Button>
            </form>
        </div>
    )
}

export default Tweet
