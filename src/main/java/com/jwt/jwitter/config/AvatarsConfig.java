package com.jwt.jwitter.config;

import java.io.IOException;
import lombok.Data;
import org.lokra.seaweedfs.core.FileSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("avatar")
@Data
public class AvatarsConfig {

    private final String host;

    private final int port;

    @Bean
    public FileSource fileSource() throws IOException {
        final FileSource fileSource = new FileSource();
        fileSource.setHost(this.host);
        fileSource.setPort(this.port);
        fileSource.startup();
        return fileSource;
    }
}
