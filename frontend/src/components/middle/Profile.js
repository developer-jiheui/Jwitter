import React from 'react'
import ProfileTabs from "./ProfileTabs";

import axios from 'axios';
import BackIcon from '@material-ui/icons/ArrowBack';
import CalendarIcon from '@material-ui/icons/DateRange';
import BdayIcon from '@material-ui/icons/CakeOutlined';
import CloseIcon from '@material-ui/icons/Close';
import AddAPhotoIcon from '@material-ui/icons/AddAPhotoOutlined';

import {Button} from "@material-ui/core";
import * as PropTypes from "prop-types";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import Input from '@material-ui/core/Input';
import InputWithCounter from "./InputWithCounter";
import Fab from "@material-ui/core/Fab";

function Profile() {
    const [editProfileOpen, setEditProfileOpen] = React.useState(true);

    function handleClickOpen() {
        setEditProfileOpen(true);
    }

    const handleClose = (value) => {
        setEditProfileOpen(false);
    };

    return (
        <div className="profile">
            <div className="profile_header">
                <BackIcon className="backArrow"/>
                <span className="userName">userName</span>
                <span className="numOfTweets">123 Tweets</span>
            </div>
            <div className ="profileSubheader">
                <div className="cover-photo"></div>
                <div className="profile-image"></div>
                <Button className="edit_btn" onClick={handleClickOpen}>Edit Profile</Button>
            </div>
            <div className="profileInfo">
                <div className ="userName">userName</div>
                <div className ="jiterId">@jitter</div>
                <div className ="bio">My bio is something something</div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <BdayIcon></BdayIcon>
                        Born July 22, 1991
                    </span>
                    <span className="userProfileInfo">
                        <CalendarIcon className="iconAlignment"></CalendarIcon>
                        Joined February 2014
                    </span>
                </div>
                <div className ="profileInfoSection">
                    <span className="userProfileInfo">
                        <span className="followingNum">238</span>
                        Following
                    </span>
                    <span className="userProfileInfo">
                        <span className="followerNum">1037</span>
                        Follower
                    </span>
                </div>
            </div>
            <ProfileTabs />
            <EditProfileDialogue open={editProfileOpen} onClose={handleClose} />
        </div>
    )
}
EditProfileDialogue.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired,
};

function EditProfileDialogue(props) {
    const { onClose, open } = props;

    function handleClose() {
        onClose();
    };

     function handleSaveProfile() {
         // todo: CRUD UPDATE user here
         handleClose();
     }

    function handleChangeCoverPhoto() {

    }

    function handleChangeProfilePhoto() {

    }

    return (
        <Dialog onClose={handleClose} className="edit-profile-dialog" aria-labelledby="edit-profile-dialog" open={open}>
            <DialogTitle id="edit-profile-dialog-header">
                <CloseIcon className="close-profile-dialog" onClick={handleClose}/>
                <span className="edit-profile-title">Edit Profile</span>
                <Button className="save-profile-button" onClick={handleSaveProfile}>Save</Button>
            </DialogTitle>
            <DialogContent id="edit-profile-dialog-content">
                <div className ="profileSubheader">
                    <div className="cover-photo">
                        <input
                            accept="image/*"
                            className="hidden-input"
                            id="cover-photo-button-file"
                            type="file"
                            onChange={handleChangeCoverPhoto}
                            />
                        <label className="edit-photo-button" htmlFor="cover-photo-button-file">
                            <AddAPhotoIcon />
                        </label>
                    </div>
                    <div className="profile-image">
                        <input
                            accept="image/*"
                            className="hidden-input"
                            id="profile-photo-button-file"
                            type="file"
                            onClick={handleChangeProfilePhoto} />
                        <label className="edit-photo-button" htmlFor="profile-photo-button-file">
                                <AddAPhotoIcon  />
                        </label>
                    </div>
                </div>
                <form action="/" method="" id="editUserForm">
                    <div className="input-form">
                        <label for="username">Name</label>
                        <InputWithCounter numChar="20"
                                          inputType="text"
                                          placeHolder="Add your name"
                                          maxChar="50"/>
                    </div>
                    <div className="input-form">
                        <label for="bio">Bio</label>
                        <InputWithCounter numChar="160"
                                          inputType="text"
                                          placeHolder="Add your bio"
                                          maxChar="160"
                                          multiline="true"
                                          rows="3"/>
                    </div>
                    <div className="input-form">
                        <label for="location">Location</label>
                        <InputWithCounter numChar="0"
                                          inputType="text"
                                          placeHolder="Add your location"
                                          maxChar="30"/>
                    </div>
                    <div className="input-form">
                        <label for="website">Website</label>
                        <InputWithCounter numChar="0"
                                          inputType="text"
                                          placeHolder="Add your website"
                                          maxChar="100"/>
                    </div>
                    <div className="input-form">
                        <label for="birthdate">Birth Date</label>
                        <Input name="name"
                               type="date"
                               fullWidth/>
                    </div>
                </form>
            </DialogContent>
        </Dialog>
    );
}

export default Profile
