import React from 'react'
import { Avatar, Button } from '@material-ui/core';
function Tweet() {
    return (
        <div className="tweet">
            <form >
                <div className="tweetInput">
                    <Avatar alt="Remy Sharp" src="https://icon-library.com/icon/img-icon-14.html" />
                    <input placeholder="What's happening?" type="text" />
                </div>
                <Button className="tweet_btn_box tweet_btn">Tweet</Button>
            </form>
        </div>
    )
}

export default Tweet
