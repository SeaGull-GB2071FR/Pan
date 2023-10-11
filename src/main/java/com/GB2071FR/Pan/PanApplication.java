package com.GB2071FR.Pan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class PanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanApplication.class, args);
    }

}
