

package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.mappers.CommentMapper;
import com.jwt.jwitter.models.mappers.PostMapper;
import com.jwt.jwitter.models.mappers.TweetAndReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TweetAndReplyMapper tnrMapper;

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

    public List<Comment> getPostByTag(String tag) {
        return this.jdbcTemplate.query(
                "select t.*, u.*, u.id as user_id from tweet t "+
                "left join users u on u.id=t.user_id "+
                "where content LIKE '%' || '#"+tag+"' || '%'  and reply_to_id=0",
                this.mapper);
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

    //for geting all the tweets a user created -- used in Profile page tweet tab
    public List<Post> getPostsByUser(final int user_id) {
        return this.jdbcTemplate.query(
            "select t.*,b.id as bookmarked from tweet t left join bookmarks b on t.id = b.twit_id and b.user_id=? where t.user_id= ? and t.reply_to_id=0 order by t.created_at desc",
            this.pMapper::postWithBookmark,
            user_id,
            user_id
        );
    }

    //get tweets that the current user commented and/or other users tweet that the current user shared
    public List<Comment> getTweetsAndReplies(final int user_id) {
        return this.jdbcTemplate.query(
            "select * from(\n" +
                    "            select * from tweet where id in\n" +
                    "                (select share_post_id id from shares\n" +
                    "                 where user_id = ? and share_post_id not in (select id from tweet where user_id = ?)\n" +
                    "                 union\n" +
                    "                 select reply_to_id from tweet as tw\n" +
                    "                 where tw.reply_to_id!=0 and tw.user_id =?)\n" +
                    "                ) as selected inner join (select id user_id, username, avatar from users) as u using (user_id)\n" +
                    "where selected.id not in (select tweet_id as id from reports where user_id =?)\n" +
                    "\n" +
                    "order by selected.created_at desc",
                new Object[]{user_id, user_id,user_id,user_id},
                this.tnrMapper);
    }

    public List<Comment> getLikes(final int user_id) {
        return this.jdbcTemplate.query(
            "select * from\n" +
                "    (select * from tweet t \n" +
                "inner join (select like_post_id id from likes where user_id = " + user_id + ") l using (id)\n" +
                "where t.reply_to_id = 0) as selected inner join (select id user_id, username, avatar from users) as u using(user_id) \n" +
                "where selected.id not in (select tweet_id as id from reports where user_id = " + user_id + ")\n" +
                "order by selected.created_at desc", this.tnrMapper);
    }

    public List<Comment> getPostsByFollow(final int user_id) {
        return this.jdbcTemplate.query(
            "select * from (\n" +
                    "    (\n" +
                    "         select id,\n" +
                    "                content,\n" +
                    "                user_id,\n" +
                    "                created_at,\n" +
                    "                modified_at,\n" +
                    "                likes,\n" +
                    "                shares,\n" +
                    "                photo,\n" +
                    "                reply_to_id,\n" +
                    "                comments\n" +
                    "         from tweet\n" +
                    "                  inner join\n" +
                    "                  (select follow_user_id user_id from follow where user_id = ?) sh using (user_id)\n" +
                    "     )\n" +
                    "         union\n" +
                    "         (select * from tweet where user_id = ?)\n" +
                    ") as selected\n" +
                    "    left join (select id user_id, username, email, birthday," +
                    "                   password, created_at, modified_at,avatar," +
                    "                   bio, location, website, coverphoto from users)as u using (user_id)\n" +
                    "    where selected.id not in (select tweet_id as id from reports where user_id = ?)\n" +
                    "    and\n" +
                    "    selected.reply_to_id =0\n" +
                    "    order by selected.created_at desc"
            , new Object[]{user_id, user_id,user_id},
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
            final Map<String, Object> map = this.jdbcTemplate.queryForMap(
                "SELECT t.id,b.id as bookmarked from tweet t left join bookmarks b " +
                    "on t.id = b.twit_id and b.user_id=? where t.id = ?",
                userId, comment.getPost().getId());
            comment.getPost().setBookMarked(map.get("bookmarked") != null);
        });
    }

    public void createBookmark(final String email, final int postId) {
        this.jdbcTemplate.update("INSERT INTO bookmarks (user_is,twit_id) values ((select id from users where email = ? limit 1),?)", email, postId);
    }


    public boolean deletePost(int tweet_id) {
        return this.jdbcTemplate.update("DELETE FROM tweet where id=" + tweet_id) == 1;
    }
    public List<Comment> searchTweet(final String tweet) {
        return this.jdbcTemplate.query(
                "select t.*,u.*,u.id as user_id from tweet t left join users u on u.id =t.user_id where content like '%"+tweet+"%'",this.mapper);

    }



}
