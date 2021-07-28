package com.sxd.eduservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Administrator
 * 2021-7-28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxd"})
public class SpringApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class,args);
    }
}