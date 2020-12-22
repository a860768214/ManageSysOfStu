package com.hsy.dbexp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hsy.dbexp.dao")
public class DbexpApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DbexpApplication.class, args);
    }

}
