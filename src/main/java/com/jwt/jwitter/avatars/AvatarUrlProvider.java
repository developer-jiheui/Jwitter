package com.jwt.jwitter.avatars;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AvatarUrlProvider {

    private final String host;

    private final int port;

    /**
     * Normalize file id from database to http url.
     */
    public String normalizeUrl(final String fid) {
        return String.format("http://%s:%d/%s", this.host, this.port, fid);
    }

}
