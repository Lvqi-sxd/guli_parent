package com.sxd.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Administrator
 * 2021-8-4
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.sxd"})
public class VodApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(VodApplicaton.class,args);
    }
}
