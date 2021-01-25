package com.inspire12.homepage.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "social")
@Data
public class ConfigProperties {
    private Kakao kakao;

    @Data
    public static class Kakao {
        @JsonProperty("client_id")
        private String clientId;
    }
}
