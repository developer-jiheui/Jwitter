package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.jwt.jwitter.models.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AvatarUrlProvider avatarUrlProvider;
    @Autowired
    private UserMapper userMapper;


    public boolean exists(final int userId) {
        System.out.println("USER EXISTS?? + " + userId);
        return this.jdbcTemplate.queryForObject("SELECT count(*) from users where id = ?", Integer.class, userId) != 0;
    }

    public boolean isSameUserName(final User user) {
        return this.jdbcTemplate.queryForObject("SELECT count(*) from users where username = ? AND id = "+user.getId(), Integer.class,user.getUsername())>1;
    }

    public User getUserByEmail(final String email) {
        return jdbcTemplate.queryForObject("Select * from users where email =?", new Object[]{email}, (rs, rowNum) ->
            new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("username"),
                rs.getDate("birthday"),
                this.avatarUrlProvider.normalizeUrl(rs.getString("avatar")),
                rs.getString("bio"),
                rs.getString("location"),
                rs.getString("website"),
                rs.getDate("created_at"),
                this.avatarUrlProvider.normalizeUrl(rs.getString("coverPhoto"))
            ));

    }

    public void updateAvatar(final int userId, final String fileId) {
        this.jdbcTemplate.update("UPDATE users set avatar=? where id=?", fileId, userId);
    }

    public List<User> getFollowing(final int userId){
        return this.jdbcTemplate.query("select * from\n" +
                "    (select follow_user_id id from follow where user_id = "+userId+"\n" +
                ") as selected inner join (select * from users) as u using(id)",this.userMapper);
    }

    public List<User> getFollower(final int userId){
        return this.jdbcTemplate.query("select * from\n" +
                "    (select user_id id from follow where follow_user_id = "+userId+"\n" +
                ") as selected inner join (select * from users) as u using(id) ",this.userMapper);
    }


    public User save(final User user) {
        final KeyHolder holder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (email,password,username,birthday) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
            return preparedStatement;
        }, holder);
        user.setId(Integer.parseInt(holder.getKeys().get("id").toString()));
        return user;
    }

    public User updateUser(final User user) {
        this.jdbcTemplate.update(
            "UPDATE users SET username=?,birthday=?,bio=?,location=?,website=?,avatar =?, coverPhoto =? WHERE id = ?",
            user.getUsername(),
            new Date(user.getBirthday().getTime()),
            user.getBio(),
            user.getLocation(),
            user.getWebsite(),
            this.avatarUrlProvider.denormalizeUrl(user.getAvatar()),
            this.avatarUrlProvider.denormalizeUrl(user.getCoverPhoto()),
            user.getId()
        );
        return getUser(user.getEmail());
    }

    public User getUser(String email) {
        return this.jdbcTemplate.queryForObject("Select * from users where email = ?", new Object[]{email}, (rs, rowNum) ->
            new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("username"),
                rs.getDate("birthday"),
                this.avatarUrlProvider.normalizeUrl(rs.getString("avatar")),
                rs.getString("bio"),
                rs.getString("location"),
                rs.getString("website"),
                rs.getDate("created_at"),
                this.avatarUrlProvider.normalizeUrl(rs.getString("coverPhoto"))
            ));
    }
}
