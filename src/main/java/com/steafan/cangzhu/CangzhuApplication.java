package com.steafan.cangzhu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan("com.steafan.cangzhu.mapper")
public class CangzhuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CangzhuApplication.class, args);
    }

}
