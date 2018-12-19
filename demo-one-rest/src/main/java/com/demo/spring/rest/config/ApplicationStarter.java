package com.demo.spring.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-19
 */
@Slf4j
@ComponentScan(basePackages = {"com.demo.spring"})
@EnableEurekaClient
@EnableAutoConfiguration
@PropertySource(value = {"classpath:config/application.yml",
        "classpath:config/application-${spring.profiles.active}.yml"})
@SpringBootApplication
public class ApplicationStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationStarter.class);
    }

    public static void main(String[] args) {
        log.info("启动Spring boot服务......");
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
