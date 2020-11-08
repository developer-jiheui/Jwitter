package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.BookMark;
import com.jwt.jwitter.models.mappers.BookMarkMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookMarksService {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private BookMarkMapper bookMarkMapper;

    @Transactional(readOnly = true)
    public List<BookMark> getBookMarks(final String email) {
        return this.template.query(
            String.join(
                "\n",
                "SELECT u.username as username,u.id as user_id, u.avatar as avatar,t.content as content,t.photo as tweet_photo from bookmarks b ",
                "inner join tweet t on t.id =b.twit_id inner join users u on u.id = t.user_id ",
                "WHERE b.user_id = (SELECT id from users where email = ? LIMIT 1)"
            ),
            this.bookMarkMapper,
            email
        );
    }
}
