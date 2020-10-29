import React from 'react'
import Tweet from '../middle/Tweet'
import Post from '../middle/Post'
function SearchResults() {
    return (
        <div className="searchresults">
            <div className="main_header">
                <h1>Search Results</h1>
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

export default SearchResults;
