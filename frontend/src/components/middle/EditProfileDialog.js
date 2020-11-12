import React,{ useEffect,useState } from "react";

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
    const {onClose, open} = props;
    const [user, setUser ] = useState({...props.user});


    useEffect(() => {
        setUser(props.user);
    }, [props.user])


    function handleClose() {
        onClose(true);
    }

    async function handleSaveProfile() {
        let bday = new Date(user.birthday );//? user.birthday : user.birthday);
        bday.setDate(bday.getDate() + 1);

        let userDto = {
                email: user.email,
                username: user.username,
                bio: user.bio,
                birthday: bday,
                location: user.location,
                website: user.website,
                avatar : user.avatar,
                coverPhoto : user.coverPhoto
        };



        let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
        axios({
            method: "put",
            url: '/api/auth/update-user',
            headers: {
                Authorization: bearer
              },
            data: userDto
        }).then((resp)=>{
            handleClose();
        }).catch(error => {
            // TODO: error handling for UI
            const alertMsg = JSON.stringify(error.response.data);
            const msg = JSON.parse(alertMsg);
            msg.message = new String(msg.message);
            alert(msg.message);


            // console.log(error);
        });

    }

    function handleChangeCoverPhoto(event) {

        const file = event.target.files[0];
        let fd = new FormData();

        fd.append('file', file);
        axios.post('/api/avatars/photoUpload', fd)
            .then(res=>{
                const rawData = res.data.split("/");
                const fid = rawData[rawData.length-1]
                    setUser({...user, coverPhoto:`http://45.76.207.32:8081/`+fid})
                }
            )
    }

    function handleChangeProfilePhoto(event) {
        const file = event.target.files[0];
        let fd = new FormData();
        fd.append('file',file);
        axios.post('/api/avatars/photoUpload', fd)
            .then(res=>{
                const rawData = res.data.split("/");
                const fid = rawData[rawData.length-1]
                setUser({...user, avatar:`http://45.76.207.32:8081/`+fid})
                }
            )
    }

    const userAvatarURLCSS = user.avatar ? {backgroundImage: "url('" +user.avatar + "')"} : {background: "grey"};
    const coverPhotoURLCSS = user.coverPhoto ? {backgroundImage: "url('" + user.coverPhoto + "')"} : {background: "grey"};


    return (
        <Dialog onClose={handleClose} className="edit-profile-dialog" aria-labelledby="edit-profile-dialog" open={open}>
            <DialogTitle id="edit-profile-dialog-header">
                <CloseIcon className="close-profile-dialog" onClick={handleClose}/>
                <span className="edit-profile-title">Edit Profile</span>
                <Button className="save-profile-button" onClick={handleSaveProfile}>Save</Button>
            </DialogTitle>
            <DialogContent id="edit-profile-dialog-content">
                <div className="profileSubheader">
                    <div className="cover-photo" style={coverPhotoURLCSS}>
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
                                          updateMethod={e=> setUser({...user, username: e.target.value}) }/>
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
                                          updateMethod={e=> setUser({...user, bio: e.target.value}) }/>
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
                                          updateMethod={e=> setUser({...user, location: e.target.value}) }/>
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
                                          updateMethod={e=> setUser({...user, website: e.target.value}) }/>
                    </div>
                    <div className="input-form">
                        <label htmlFor="birthdate">Birth Date</label>
                        <Input name="birthdate"
                               type="date"
                               fullWidth
                               defaultValue={user.birthday}
                               onChange={e=> setUser({...user, birthday: e.target.value}) }/>
                    </div>
                </div>
            </DialogContent>
        </Dialog>
    );
}

export default EditProfileDialogue