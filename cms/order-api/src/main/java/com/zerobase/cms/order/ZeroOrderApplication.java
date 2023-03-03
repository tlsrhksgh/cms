package com.zerobase.cms.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan
@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class ZeroOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeroOrderApplication.class, args);
    }
}
