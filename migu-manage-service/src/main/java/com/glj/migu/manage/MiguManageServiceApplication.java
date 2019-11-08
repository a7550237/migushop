package com.glj.migu.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.glj.migu"})
@MapperScan(basePackages = "com.glj.migu.manage.mapper")
public class MiguManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiguManageServiceApplication.class, args);
    }

}
