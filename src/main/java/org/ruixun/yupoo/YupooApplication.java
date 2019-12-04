package org.ruixun.yupoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = "org.ruixun.yupoo.*")
public class YupooApplication {

    public static void main(String[] args) {
        SpringApplication.run(YupooApplication.class, args);
    }
}
