import React from 'react';
import TwitterIcon from '@material-ui/icons/Twitter';
import SidebarOption from "../left/SidebarOption"
import HomeIcon from '@material-ui/icons/Home';
import SearchIcon from '@material-ui/icons/Search';
import NotificationsIcon from '@material-ui/icons/Notifications';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import BookmarkIcon from '@material-ui/icons/Bookmark';
import ListAltIcon from '@material-ui/icons/ListAlt';
import PermIdentityIcon from '@material-ui/icons/PermIdentity';
import MoreHorizIcon from '@material-ui/icons/MoreHoriz';
import { Button } from "@material-ui/core";

function Sidebar() {
    return (
        <div className="sidebar">
            <TwitterIcon className="logo"/>
            <SidebarOption active text="Home" Icon={HomeIcon}/>
            <SidebarOption text="Explore" Icon={SearchIcon}/>
            <SidebarOption text="Notifications" Icon={NotificationsIcon}/>
            <SidebarOption text="Messages" Icon={MailOutlineIcon}/>
            <SidebarOption text="Bookmarks" Icon={BookmarkIcon}/>
            <SidebarOption text="Lists" Icon={ListAltIcon}/>
            <SidebarOption text="Profile" Icon={PermIdentityIcon}/>
            <SidebarOption text="More" Icon={MoreHorizIcon}/>
            <Button variant="outlined" className="tweet_btn" fullWidth>Tweet</Button>
        </div>
    )
}

export default Sidebar
