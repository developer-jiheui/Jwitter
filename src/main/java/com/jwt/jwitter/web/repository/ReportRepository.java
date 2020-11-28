package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.Report;
import com.jwt.jwitter.models.mappers.ReportMapper;
import com.jwt.jwitter.web.dto.in.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository

public class ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ReportMapper reportMapper;

    public Report reportPost(ReportDto reportDto) {
            this.jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO reports (tweet_id,user_id,report_content) values (?,?,?)");
                preparedStatement.setInt(1, reportDto.getTweet_id());
                preparedStatement.setInt(2, reportDto.getUser_id());
                preparedStatement.setString(3, reportDto.getReport_content());
                return preparedStatement;
            });
        return this.jdbcTemplate.queryForObject("Select tweet_id,user_id,report_content from reports where tweet_id = ? AND user_id =?", new Object[]{reportDto.getTweet_id(),reportDto.getUser_id()},this.reportMapper);

    }
}
