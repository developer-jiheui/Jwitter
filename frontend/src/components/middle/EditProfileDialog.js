import React from "react";
import { Redirect, useHistory } from "react-router-dom";

import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import CloseIcon from "@material-ui/icons/Close";
import {Button} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import AddAPhotoIcon from "@material-ui/icons/AddAPhotoOutlined";
import InputWithCounter from "./InputWithCounter";
import Input from "@material-ui/core/Input";
import * as PropTypes from "prop-types";
import axios from 'axios';

EditProfileDialogue.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired,
    user: PropTypes.object.isRequired
};

function EditProfileDialogue(props) {
    const history = useHistory();

    const {onClose, open, user} = props;
    const [updatedUser, setUpdatedUser ] = React.useState({
        username: user.username,
        avatar: user.avatar,
        bio: user.bio,
        website: user.website,
        birthday: user.birthday,
        location: user.location
    });

    function handleClose() {
        onClose(true);
        console.log("CLOSING", updatedUser);
    }

    function handleSaveProfile() {
        axios({
            method: "put",
            url: '/api/auth/update-user',
            data: {
                username: updatedUser.username ? updatedUser.username : user.username,
                avatar: user.avatar ? user.avatar : user.avatar,
                bio: updatedUser.bio ? updatedUser.bio : user.bio,
                website: updatedUser.website ? updatedUser.website : user.website,
                location: updatedUser.location ? updatedUser.location : user.location,
                birthday: new Date(updatedUser.birthday ? updatedUser.birthday : user.birthday)
            },
            headers: {
                    "Authorization": `Bearer ${localStorage.getItem("jwt")}`},
                    'content-type': 'application/json'
            }).then(resp => {
                console.log("DONE EDIT: ", resp.data);
                setUpdatedUser(resp.data);
                handleClose();

        }).catch(error => {
            // TODO: error handling for UI
                console.log(error);
            })
    }

    function handleChangeCoverPhoto(event) {
        // setUpdatedUser({...user, avatar: e.target.value});
        console.log("changed cover photo");
    }

    function handleChangeProfilePhoto(event) {
        // setUpdatedUser({...user, avatar: e.target.value});
        console.log("changed profile pic");
    }



    const userAvatarURLCSS = {backgroundImage: "url('" + user.avatar + "')"};

    return (
        <Dialog onClose={handleClose} className="edit-profile-dialog" aria-labelledby="edit-profile-dialog" open={open}>
            <DialogTitle id="edit-profile-dialog-header">
                <CloseIcon className="close-profile-dialog" onClick={handleClose}/>
                <span className="edit-profile-title">Edit Profile</span>
                <Button className="save-profile-button" onClick={handleSaveProfile}>Save</Button>
            </DialogTitle>
            <DialogContent id="edit-profile-dialog-content">
                <div className="profileSubheader">
                    <div className="cover-photo" style={userAvatarURLCSS}>
                        <input
                            accept="image/*"
                            className="hidden-input"
                            id="cover-photo-button-file"
                            type="file"
                            onChange={handleChangeCoverPhoto}
                        />
                        <label className="edit-photo-button" htmlFor="cover-photo-button-file">
                            <AddAPhotoIcon/>
                        </label>
                    </div>
                    <div className="profile-image" style={userAvatarURLCSS}>
                        <input
                            accept="image/*"
                            className="hidden-input"
                            id="profile-photo-button-file"
                            type="file"
                            onChange={handleChangeProfilePhoto}/>
                        <label className="edit-photo-button" htmlFor="profile-photo-button-file">
                            <AddAPhotoIcon/>
                        </label>
                    </div>
                </div>
                <div id="editUserForm">
                    <div className="input-form">
                        <label htmlFor="username">Name</label>
                        <InputWithCounter numChar="20"
                                          inputType="text"
                                          placeHolder="Add your name"
                                          maxChar="50"
                                          rows="1"
                                          value={user.username}
                                          name="name"
                                          updateMethod={e=> setUpdatedUser({...updatedUser, username: e.target.value}) }/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="bio">Bio</label>
                        <InputWithCounter numChar="160"
                                          inputType="text"
                                          placeHolder="Add your bio"
                                          maxChar="160"
                                          multiline="true"
                                          rows="3"
                                          value={user.bio}
                                          name="bio"
                                          updateMethod={e=> setUpdatedUser({...updatedUser, bio: e.target.value}) }/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="location">Location</label>
                        <InputWithCounter numChar="0"
                                          inputType="text"
                                          placeHolder="Add your location"
                                          maxChar="30"
                                          rows="1"
                                          value={user.location}
                                          name="location"
                                          updateMethod={e=> setUpdatedUser({...updatedUser, location: e.target.value}) }/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="website">Website</label>
                        <InputWithCounter numChar="0"
                                          inputType="text"
                                          placeHolder="Add your website"
                                          maxChar="100"
                                          rows="1"
                                          value={user.website}
                                          name="website"
                                          updateMethod={e=> setUpdatedUser({...updatedUser, website: e.target.value}) }/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="birthdate">Birth Date</label>
                        <Input name="birthdate"
                               type="date"
                               fullWidth
                               defaultValue={user.birthday}
                               onChange={e=> setUpdatedUser({...updatedUser, birthday: e.target.value}) }/>
                    </div>
                </div>
            </DialogContent>
        </Dialog>
    );
}

export default EditProfileDialogue