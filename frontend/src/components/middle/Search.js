import React, {useEffect, useState} from 'react'
import SearchIcon from "@material-ui/icons/Search";
import { Tab, Tabs} from "@material-ui/core";
import axios from 'axios';



function Search() {
    const [searchWord, setSearchWord] =useState("");
    const [result, setResult] =useState({});
    const search =() =>{
        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios.get(`/api/auth/search/${searchWord}`, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
        }).then((res)=>{
            setResult(res.data)
        }).catch(r=>{
            console.log(r);
            alert(r);
        });
    }

    return (
        <div className="main">
            <div className="main_header">
                <h1>Search</h1>
            </div>
            <div className="embed_input">
                <SearchIcon className="embed_search" onClick={()=>search()}/>
                <input placeholder="Search Jitter" type="text" />
            </div>
            <div  className="user-profile-tabs">
                <Tabs
                >
                    <Tab label="Users" />
                    <Tab label="Tweets"/>

                </Tabs>
            </div>
        </div>
    );
}

export default Search;