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

function Sidebar({activeOne}) {
    return (
        <div className="sidebar">
            <TwitterIcon className="logo"/>
            <SidebarOption active ={activeOne=="Home"} text="Home" Icon={HomeIcon} href="/home"/>
            <SidebarOption active ={activeOne=="Explore"} text="Explore" Icon={SearchIcon}/>
            <SidebarOption active ={activeOne=="Notifications"} text="Notifications" Icon={NotificationsIcon}/>
            <SidebarOption active ={activeOne=="Messages"} text="Messages" Icon={MailOutlineIcon}/>
            <SidebarOption active ={activeOne=="Bookmarks"} text="Bookmarks" Icon={BookmarkIcon}/>
            <SidebarOption active ={activeOne=="Lists"} text="Lists" Icon={ListAltIcon}/>
            <SidebarOption active ={activeOne=="Profile"} text="Profile" Icon={PermIdentityIcon} href="/profile"/>
            <SidebarOption active ={activeOne=="More"} text="More" Icon={MoreHorizIcon}/>
            <Button variant="outlined" className="tweet_btn" fullWidth>Tweet</Button>
        </div>
    )
}

export default Sidebar
