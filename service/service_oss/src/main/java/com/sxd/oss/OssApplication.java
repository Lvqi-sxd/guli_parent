package com.sxd.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Administrator
 * 2021-7-26
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.sxd")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);

    }
}

