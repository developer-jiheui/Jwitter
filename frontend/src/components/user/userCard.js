import React, { useState, useEffect } from 'react'
import { Avatar, Button } from "@material-ui/core";
import VerifiedUserIcon from "@material-ui/icons/VerifiedUser";
const Card = ({user}) => {
  const [followText, setFollowText] = useState("Follow")
        const toggleFollow =(followText) =>{
            if(followText ==='Follow'){
                //do something
                setFollowText("UnFollow")
            }else{
                 //do something
            }
        }

  return (
    <div className="post">
      <div className="post_avatar">
        <Avatar src={user.avatar} />
      </div>
      <div className="post_body">
        <div className="post_header">
          <div className="post_headerText">
            <h3>
              {user.username}{" "}
              <span className="post_headerSpecial">
                <VerifiedUserIcon className="post_badge" />
              </span>
              <Button variant="outlined" onClick={() => { toggleFollow() }} color="primary">
                    {followText}
                </Button>
            </h3>
            <p>{user.bio}</p>
          </div>
          </div>
        </div>
    </div>
  );
};

export default Card;

