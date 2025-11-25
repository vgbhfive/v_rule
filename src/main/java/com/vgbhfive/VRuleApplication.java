package com.vgbhfive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
@EnableScheduling
@MapperScan("com.vgbhfive.mapper")
public class VRuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VRuleApplication.class, args);
    }

}