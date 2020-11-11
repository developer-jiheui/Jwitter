

package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.mappers.CommentMapper;
import com.jwt.jwitter.models.mappers.PostMapper;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("ALL")
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Stop creating beans with new operand , use Autowire
    @Autowired
    private CommentMapper mapper;
    @Autowired
    private PostMapper pMapper;

    public void updatePhoto(final int postId, final String fileId) {
        this.jdbcTemplate.update("UPDATE tweet set photo=? where id=?", fileId, postId);
    }

    public Post save(final Post post) {
        this.jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tweet (user_id,content,photo,reply_to_id) values (?,?,?,?)");
            preparedStatement.setInt(1, post.getUser_id());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getPhoto());
            preparedStatement.setInt(4, post.getReply_to_id());
            return preparedStatement;
        });
        return post;
    }

    public Map getLikeNShare(int user_id, int tweet_id) {
        boolean isShare = this.jdbcTemplate.queryForObject("SELECT count(*) from shares where user_id = ? and share_post_id=?", Integer.class, user_id, tweet_id) != 0;
        boolean isLike = this.jdbcTemplate.queryForObject("SELECT count(*) from likes where user_id = ? and like_post_id=?", Integer.class, user_id, tweet_id) != 0;
        Map rs = new HashMap();
        rs.put("like", isLike);
        rs.put("share", isShare);
        return rs;
    }

    public int toggleShare(int user_id, int tweet_id, boolean toggle) {
        if (toggle) {
            this.jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO shares (user_id,share_post_id) values (?,?)");
                preparedStatement.setInt(1, user_id);
                preparedStatement.setInt(2, tweet_id);
                return preparedStatement;
            });
            this.jdbcTemplate.update("update tweet set shares=(select shares+1 from tweet where id=?) where id=?", tweet_id, tweet_id);
        } else {
            this.jdbcTemplate.update("delete from shares where user_id =? and share_post_id=?", user_id, tweet_id);
            this.jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement("update tweet set shares=(select \n" +
                    " CASE WHEN shares-1<0 THEN 0" +
                    " ELSE shares-1" +
                    " END from tweet where id=?) where id=?");
                preparedStatement.setInt(1, tweet_id);
                preparedStatement.setInt(2, tweet_id);
                return preparedStatement;
            });
        }
        return this.jdbcTemplate.queryForObject("Select shares from tweet where id = ?", new Object[]{tweet_id}, Integer.class);
    }

    public int toggleLike(int user_id, int tweet_id, boolean toggle) {
        if (toggle) {
            this.jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO likes (user_id,like_post_id) values (?,?)");
                preparedStatement.setInt(1, user_id);
                preparedStatement.setInt(2, tweet_id);
                return preparedStatement;
            });
            this.jdbcTemplate.update("update tweet set likes=(select likes+1 from tweet where id=?) where id=?", tweet_id, tweet_id);
        } else {
            this.jdbcTemplate.update("delete from likes where user_id =? and like_post_id=?", user_id, tweet_id);
            this.jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement("update tweet set likes=(select \n" +
                    " CASE WHEN likes-1<0 THEN 0" +
                    " ELSE likes-1" +
                    " END from tweet where id=?) where id=?");
                preparedStatement.setInt(1, tweet_id);
                preparedStatement.setInt(2, tweet_id);
                return preparedStatement;
            });
        }
        return this.jdbcTemplate.queryForObject("Select likes from tweet where id = ?", new Object[]{tweet_id}, Integer.class);
    }

    public List<Comment> getPostsByUser(final int user_id) {
        return this.jdbcTemplate.query(
            "select * from tweet  where user_id=" + user_id,
            this.mapper);

    }

    public List<Comment> getPostsByFollow(final int user_id) {
        return this.jdbcTemplate.query(
            "select * from tweet t left join users u on u.id =t.user_id where t.user_id in " +
                "(select follow_user_id from follow where user_id=?)\n" +
                "union \n" +
                "select * from tweet t left join users u on u.id =t.user_id where  user_id=? order by 1 desc"
            , new Object[]{user_id, user_id},
            this.mapper);
    }

    public List<Comment> getCommentsByTweetId(final int tweet_id) {
        return this.jdbcTemplate.query(
            "select * from tweet t left join users u on u.id =t.user_id\n" +
                "where reply_to_id =" + tweet_id, this.mapper);
    }

    public Post getPostById(final int id) {
        return this.jdbcTemplate.queryForObject("Select * from tweet where id = ?", new Object[]{id}, this.pMapper);
    }

    public void addComment(int id, int comment) {
        this.jdbcTemplate.update("update tweet set comments=? where id=?", comment, id);
    }

    public void checkBookMarks(final List<Comment> comments, final int userId) {
        comments.forEach(comment -> {
            final Map<String, Object> map = this.jdbcTemplate.queryForMap("SELECT t.id,b.id as bookmarked from tweet t left join bookmarks b on t.id = b.twit_id and b.user_id=? where t.id = ?", userId, comment.getPost().getId());
            comment.getPost().setBookMarked(map.get("bookmarked") != null);
        });
    }

    public void createBookmark(final String email, final int postId) {
        this.jdbcTemplate.update("INSERT INTO bookmarks (user_is,twit_id) values ((select id from users where email = ? limit 1),?)", email, postId);
    }
}
