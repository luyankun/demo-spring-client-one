package com.demo.spring.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-20
 */
@MapperScan(basePackages = {"com.demo.spring.business.persistence"})
@EnableTransactionManagement
@Configuration
public class PersistenceConfig {
}
