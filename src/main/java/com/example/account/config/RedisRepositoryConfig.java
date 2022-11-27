package com.example.account.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisRepositoryConfig {
    // 시작된 레디스를 사용하는 빈
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean   // 메소드의 이름이 Bean으로 등록이 됨
    public RedissonClient redissonClient() {
        // 환경에 따라 다른 값이 들어가게 설정값을 따로 뜬 다음에(application.yml) 그 값을 가져와서 넣어 줌
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        return Redisson.create(config);
    }
}
