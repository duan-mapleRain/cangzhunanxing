package com.steafan.cangzhu.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ConfigurationPropertiesScan
@MapperScan("com.steafan.cangzhu.api.mapper")
public class CangzhuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CangzhuApplication.class, args);
    }

}
