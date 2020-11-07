package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.models.BookMark;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public final class BookMarkMapper implements RowMapper<BookMark> {

    @Override
    public BookMark mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        throw new UnsupportedOperationException("#mapRow()");
    }
}
