package com.example.redisstudy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "my-redis-config")
public class MyRedisConfig {
    private String host;
    private int port;
}
