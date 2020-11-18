package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.web.dto.out.Notifications;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public final class NotificationMapper implements RowMapper<Notifications> {

    @Override
    public Notifications mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Notifications(
            rs.getString("username"),
            rs.getString("content")
        );
    }
}
