package com.up.day.day.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.up.day.day.server.mapper")
public class MyUserCenterBootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyUserCenterBootServerApplication.class, args);
    }

}
