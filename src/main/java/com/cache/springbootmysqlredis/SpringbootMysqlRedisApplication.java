package com.cache.springbootmysqlredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootMysqlRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMysqlRedisApplication.class, args);
    }

}
