package com.jwt.jwitter.web.dto.out;

import lombok.Data;

@Data
public final class ReportsDto {

    private final int postId;

    private final int reports;

    private final String content;

    private final boolean disabled;
}
