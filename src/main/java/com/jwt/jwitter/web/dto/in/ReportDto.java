package com.jwt.jwitter.web.dto.in;

import lombok.Data;
import javax.validation.constraints.Size;

@Data
public class ReportDto {

    private final int tweet_id;
    private final int user_id;
    private final @Size(min = 5) String report_content;
}
