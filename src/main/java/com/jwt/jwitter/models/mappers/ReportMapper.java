package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.models.Report;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ReportMapper implements RowMapper<Report> {

    @Override
    public Report mapRow(final ResultSet rs, final int rowNum) throws SQLException {
       Report report = new Report(
                rs.getInt("tweet_id"),
                rs.getInt("user_id"),
                rs.getString("report_content")
       );
        return report;
    }
}
