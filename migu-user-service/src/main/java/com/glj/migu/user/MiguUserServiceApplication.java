package com.glj.migu.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.glj.migu.user.dao")
public class MiguUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiguUserServiceApplication.class, args);
    }

}
