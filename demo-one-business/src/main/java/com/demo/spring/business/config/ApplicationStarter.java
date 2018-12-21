package com.demo.spring.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-19
 */
@Slf4j
@ComponentScan(basePackages = {"com.demo.spring.*"})
@PropertySource(value = {"classpath:config/application.yml",
        "classpath:config/application-${spring.profiles.active}.yml"})
@SpringBootApplication
public class ApplicationStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationStarter.class);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        log.info("启动Spring boot服务......");
        SpringApplication.run(ApplicationStarter.class, args);
        log.info("服务启动完成......");
    }
}
