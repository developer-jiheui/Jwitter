package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.mappers.NotificationMapper;
import com.jwt.jwitter.web.dto.out.Notifications;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationsService {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NotificationMapper mapper;

    @Transactional(readOnly = true)
    @SuppressWarnings("ALL")
    public List<Notifications> notifications(final String email) {
        return this.template.query("SELECT u.username as username,t.content as content from likes l inner join users u on u.id = l.user_id and u.id != (select id from users where email like ? limit 1) inner join tweet t on t.id = l.like_post_id where t.user_id = (select id from users where email like ? limit 1)", this.mapper,email, email);
    }
}
