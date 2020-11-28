package com.jwt.jwitter.models;

import lombok.Data;
import java.util.Date;

@Data
public class Report {
    private int id;
    private int tweet_id;
    private int user_id;
    private Date report_date;
    private String report_comment;


    public Report(int tweet_id, int user_id, String report_comment){
        this.tweet_id = tweet_id;
        this.user_id = user_id;
        this.report_comment = report_comment;
    }
}
