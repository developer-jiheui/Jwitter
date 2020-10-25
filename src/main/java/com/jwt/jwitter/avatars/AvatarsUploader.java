package com.jwt.jwitter.avatars;

import java.io.IOException;
import org.lokra.seaweedfs.core.FileSource;
import org.lokra.seaweedfs.core.FileTemplate;
import org.lokra.seaweedfs.core.file.FileHandleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public final class AvatarsUploader {

    @Autowired
    private FileSource source;

    public String upload(final MultipartFile file) throws IOException {
        final FileTemplate template = new FileTemplate(this.source.getConnection());
        final FileHandleStatus status = template.saveFileByStream(file.getName(), file.getInputStream());
        return status.getFileId();
    }
}
