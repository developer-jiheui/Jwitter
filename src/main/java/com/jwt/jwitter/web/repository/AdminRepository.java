package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.web.dto.out.AdminUsersDto;
import com.jwt.jwitter.web.dto.out.ReportsDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AdminUsersDto> users() {
        return this.jdbcTemplate.query("SELECT id,username,email,enabled from users where username not like 'admin'", (rs, rowNum) -> new AdminUsersDto(
            rs.getString("email"),
            rs.getString("username"),
            !rs.getBoolean("enabled"),
            rs.getInt("id")
        ));
    }

    public void enable(final int id) {
        this.jdbcTemplate.update("UPDATE users set enabled = true where id = ?", id);
    }

    public void disable(final int id) {
        this.jdbcTemplate.update("UPDATE users set enabled = false where id = ?", id);
    }

    public List<ReportsDto> reports() {
        return this.jdbcTemplate.query("SELECT count(*) as cnt,r.tweet_id as postId,t.content as content from reports r inner join tweet t on t.id = r.tweet_id group by r.tweet_id,t.content ", (rs, rowNum) -> new ReportsDto(
            rs.getInt("postId"),
            rs.getInt("cnt"),
            rs.getString("content")
        ));
    }
}
