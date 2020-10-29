import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import CloseIcon from "@material-ui/icons/Close";
import {Button} from "@material-ui/core";
import DialogContent from "@material-ui/core/DialogContent";
import AddAPhotoIcon from "@material-ui/icons/AddAPhotoOutlined";
import InputWithCounter from "./InputWithCounter";
import Input from "@material-ui/core/Input";
import React from "react";
import * as PropTypes from "prop-types";

EditProfileDialogue.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired,
};

function EditProfileDialogue(props) {
    const {onClose, open} = props;
    const {user, setUser} = props;

    function handleClose() {
        onClose();
    };

    function handleSaveProfile() {
        // todo: CRUD UPDATE user here
        handleClose();
    }

    function handleChangeCoverPhoto(event) {
        console.log(event.target.value);
    }

    function handleChangeProfilePhoto(event) {
        console.log(event.target.value);
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
                                          value={user.username}/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="bio">Bio</label>
                        <InputWithCounter numChar="160"
                                          inputType="text"
                                          placeHolder="Add your bio"
                                          maxChar="160"
                                          multiline="true"
                                          rows="3"
                                          value={user.bio}/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="location">Location</label>
                        <InputWithCounter numChar="0"
                                          inputType="text"
                                          placeHolder="Add your location"
                                          maxChar="30"
                                          rows="1"
                                          value={user.location}/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="website">Website</label>
                        <InputWithCounter numChar="0"
                                          inputType="text"
                                          placeHolder="Add your website"
                                          maxChar="100"
                                          rows="1"
                                          value={user.website}/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="birthdate">Birth Date</label>
                        <Input name="name"
                               type="date"
                               fullWidth
                               value={user.birthday}/>
                    </div>
                </div>
            </DialogContent>
        </Dialog>
    );
}

export default EditProfileDialogue