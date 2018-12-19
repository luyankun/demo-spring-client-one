package com.demo.spring.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-19
 */
@Configuration
@PropertySource(value = {"classpath:service-config/application.yml",
        "classpath:service-config/application-${spring.profiles.active}.yml"})
public class ServiceConfig {
}
