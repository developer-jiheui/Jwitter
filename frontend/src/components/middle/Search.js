import React, {useEffect, useState} from 'react'
import SearchIcon from "@material-ui/icons/Search";
import { Tab, Tabs} from "@material-ui/core";
import Post from '../middle/Post'
import axios from 'axios';
import Card from '../user/userCard';


function Search(props) {

    const [searchWord, setSearchWord] =useState("");
    const [value, setValue] = React.useState(0);
    const [user, setUsers] =useState([]);
    const [tweets, setTweets] =useState([]);
    const [tagResult, setTagResult] =useState([]);
    const handleSearchInputChanges = (e) => {
        setSearchWord(e.target.value);
    }

    const search_tweet =() =>{
        console.log("enter search tweet");
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios.get(`/api/auth/tweet-search/${searchWord}`, {
            headers: {
                Authorization: bearer
            }
        }).then((res)=>{
            setTweets(res.data)
        }).catch(r=>{
            console.log(r);
            alert(r);
        });
    }

    const search =() =>{
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios.get(`/api/auth/search/${searchWord}`, {
            headers: {
                Authorization: bearer
            }
        }).then((res)=>{
            console.log("SET user",res.data);
          setUsers(res.data);
        }).catch(r=>{
            console.log(r);
            alert(r);
        });
    }

    const searchTag =async() =>{
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        await axios.get(`/api/auth/searchTag/${searchWord}`, {
            headers: {
                Authorization: bearer
            }
        }).then((res)=>{
            setTagResult(res.data);
        }).catch(r=>{
            console.log(r);
            alert(r);
        });
    }

    const handleChange = (event, value) => {
        setValue(value);
        switch(value) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    const postOnClick =() =>{}

    return (
        <div className="main">
            <div className="main_header">
                <h1>Search</h1>
            </div>
            
            <div className="embed_input">
                <SearchIcon className="embed_search" />
                {/* <SearchIcon className="embed_search" value={searchWord} onChange={()=>search()}/> */}
                <input placeholder="Search Jitter" type="text" 
                onChange={e=>setSearchWord(e.target.value)} 
                onKeyPress={e =>{if (e.key === 'Enter') {
                    searchTag();
                    search_tweet();
                    search();
                }}}/>
            </div>
            <div  className="user-profile-tabs">
                <Tabs
                    value={value}
                    onChange={handleChange}
                    indicatorColor="primary"
                    textColor="primary"
                >
                    <Tab label="Tweets"  />
                    <Tab label="Users"  />
                    <Tab label="Tags"  />
                   
                </Tabs>
            </div>
            <TabPanel value={value} index={0}>
                {tweets.map((tweet,index)=>{
                return <Post key={index} tweet_data={tweet.post} user={tweet.user} postOnClick={postOnClick} viewOnly={false} currUser={props.currUser}/>
            })}
            </TabPanel>
            <TabPanel value={value} index={1}>
                {user.map((u,index)=>{
                    return <Card user={u} key={index}></Card>
                })}
            </TabPanel>
            <TabPanel value={value} index={2}>
                {tagResult.map((tweet,index)=>{
                    return <Post key={index} tweet_data={tweet.post} user={tweet.user} postOnClick={postOnClick} viewOnly={true} currUser={tweet.user}/>
                })}
            </TabPanel>
        </div>
    );

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
}

export default Search;