package com.demo.spring.service.biz.impl;

import com.demo.spring.service.biz.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-19
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Value(value = "${demo.spring.client.rest.demo.get.path:}${demo.spring.client.rest.demo.get.uri:}")
    private String demoGetURl;

    @Override
    public String get() {
        return demoGetURl;
    }
}
