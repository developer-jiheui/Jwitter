package com.jwt.jwitter.config;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import java.io.IOException;
import lombok.Data;
import org.lokra.seaweedfs.core.FileSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:weedfs.properties")
@ConfigurationProperties("avatar")
@Data
public class AvatarsConfig {

    private String host;

    private int port;

    /**
     * Saves avatars in remote seaweedfs server.
     */
    @Bean
    public FileSource fileSource() throws IOException {
        final FileSource fileSource = new FileSource();
        fileSource.setHost(this.host);
        fileSource.setPort(this.port);
        fileSource.setConnectionTimeout(10_000);
        fileSource.startup();
        return fileSource;
    }

    @Bean
    public AvatarUrlProvider avatarUrlProvider() {
        return new AvatarUrlProvider(this.host, this.port);
    }
}
