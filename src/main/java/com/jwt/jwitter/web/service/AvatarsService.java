package com.jwt.jwitter.web.service;

import com.jwt.jwitter.avatars.AvatarsUploader;
import com.jwt.jwitter.web.repository.UsersRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarsService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private AvatarsUploader uploader;

    @Transactional
    public boolean upload(final MultipartFile file, final int userId) throws IOException {
        if (!this.repository.exists(userId)) {
            throw new IllegalArgumentException(String.format("No User id %d", userId));
        }
        final String fileId = this.uploader.upload(file);
        this.repository.updateAvatar(userId, fileId);
        return true;
    }

    @Transactional
    public String photoUpload(final MultipartFile file) throws IOException {
       final String fileId = this.uploader.upload(file);
       return  fileId;
    }
}
