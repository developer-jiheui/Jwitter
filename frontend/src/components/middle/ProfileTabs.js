import React from 'react'
import {AppBar, Tab, Tabs} from "@material-ui/core";
import Post from "./Post";
import Tweet from "./Tweet";

function ProfileTabs(props) {
    const [value, setValue] = React.useState(0);

    const handleChange = (event, value) => {
        setValue(value);
    }

    return (
        <div className="user-profile-tabs-container">
            <div  className="user-profile-tabs">
                <Tabs
                    value={value}
                    onChange={handleChange}
                    indicatorColor="primary"
                    textColor="primary"
                >
                    <Tab label="Tweets"  />
                    <Tab label="Tweets & Replies"  />
                    <Tab label="Media" />
                    <Tab label="Likes" />
                </Tabs>
            </div>
            <TabPanel value={value} index={0}>
                <Post />
            </TabPanel>
            <TabPanel value={value} index={1}>
                <Post />
                <Post />
            </TabPanel>
            <TabPanel value={value} index={2}>
                <Post />
                <Post />
                <Post />
            </TabPanel>
            <TabPanel value={value} index={3}>
                <Post />
                <Post />
                <Post />
                <Post />
            </TabPanel>
        </div>
    );
}

function TabPanel(props) {
    const {children, value, index} = props;

    return(
        <div>
            {
                value===index && (<h1>{children}</h1>)
            }
        </div>
    );
}

export default ProfileTabs