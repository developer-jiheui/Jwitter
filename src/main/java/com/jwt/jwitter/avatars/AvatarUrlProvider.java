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
        if(fid==null){
            return "";
        }
        else
            return String.format("http://%s:%d/%s", this.host, this.port, fid);
    }

    /**
     * Denormalize file id from database to http url.
     */
    public String denormalizeUrl(final String fid) {
        if(fid==null){
            return "";
        }
            String cleanData = fid.replace("http://45.76.207.32:8081/","");
            System.out.println(cleanData);
            return cleanData;
    }

}
