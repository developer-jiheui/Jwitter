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
        else{
            String rawData[] = fid.split("/");
            String cleanData = rawData[rawData.length-1];
            return String.format("http://%s:%d/%s", this.host, this.port, cleanData);
        }
    }

    /**
     * Denormalize file id from database to http url.
     */
    public String denormalizeUrl(final String fid) {
        if(fid==null){
            return "";
        }
            String rawData[] = fid.split("/");
            String cleanData = rawData[rawData.length-1];
            return cleanData;
    }

}
