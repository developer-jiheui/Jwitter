package com.jwt.jwitter.web.service;

import com.jwt.jwitter.avatars.AvatarsUploader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarsService {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private AvatarsUploader uploader;

    @Transactional
    public boolean upload(final MultipartFile file, final int userId) throws IOException {
        final List<Map<String, Object>> users = this.template.queryForList("select id from users where id =?", userId);
        if (users.isEmpty()) {
            throw new IllegalArgumentException(String.format("No User id %d", userId));
        }
        return this.template.update(
            "UPDATE users set avatar=?",
            this.uploader.upload(file)
        ) != 1;
    }
}
