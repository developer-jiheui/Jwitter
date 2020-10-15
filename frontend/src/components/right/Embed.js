import React from 'react'
import {
    TwitterTimelineEmbed,
    TwitterTweetEmbed
  } from "react-twitter-embed";
  import SearchIcon from "@material-ui/icons/Search";

function Embed() {
    return (
        <div className="embed">
            <div className="embed_input">
                <SearchIcon className="embed_search" />
                <input placeholder="Search Twitter" type="text" />
            </div>
            <div className="embedContainer">
                <h2>What's happening</h2>
                <TwitterTweetEmbed tweetId={"1315782471783383040"} />

                <TwitterTimelineEmbed
                sourceType="profile"
                screenName="realDonaldTrump"
                options={{ height: 200 }}
                />

                {/* <TwitterShareButton
                url={"https://twitter.com/realDonaldTrump"}
                options={{ text: "#reactjs is awesome", via: "realDonaldTrump" }}
                /> */}
            </div>
        </div>
    )
}

export default Embed
