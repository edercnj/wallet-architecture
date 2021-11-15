package com.br.edercnj.wallettimeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WalletTimelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletTimelineApplication.class, args);
    }

}
