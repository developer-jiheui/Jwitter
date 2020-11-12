import React, {useEffect, useState} from 'react'
import { Tab, Tabs} from "@material-ui/core";
import Post from "./Post";
import Main from "./Main";
import axios from "axios";

function ProfileTabs(props) {
    const [value, setValue] = React.useState(0);

    const [postData, setPostData] = useState({tweet_data:{id:0},comment:[],main_post_user:{id:0,avatar:""}});
    const [leaveComment, setLeaveComment] = useState(false);

    const handleChange = (event, value) => {
        setValue(value);
        switch(value) {
            case 0:
             //  getMyTweets();
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }





    const getComment = (viewPost) =>{
        axios.get(`/api/auth/comments/${viewPost.tweet_data.id}`, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
        }).then((res)=>{
            viewPost.comment=res.data
            setPostData(viewPost)
        }).catch(r=>{
            console.log(r);
            alert(r);
        });
    }

    const postOnClick =(data, main_post_user) =>{
        let viewPost={tweet_data: data, main_post_user:main_post_user,comment:[]}
        setPostData(viewPost)
        getComment(viewPost)
        setLeaveComment(!leaveComment)
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
                    <Tab label="Likes" />
                </Tabs>
            </div>
            <TabPanel value={value} index={0}>
                {props.tweets.map((tweet,index)=>{
                return <Post key={index} tweet_data={tweet} user={props.user} postOnClick={postOnClick} viewOnly={false}/>
            })}
            </TabPanel>
            <TabPanel value={value} index={1}>
                {props.tandR.map((tweet,index)=>{
                    return <Post key={index} tweet_data={tweet} user={props.user} postOnClick={postOnClick} viewOnly={false}/>
                })}
            </TabPanel>
            <TabPanel value={value} index={2}>
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