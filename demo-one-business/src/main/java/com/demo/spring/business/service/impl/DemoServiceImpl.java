package com.demo.spring.business.service.impl;

import com.demo.spring.business.persistence.DemoMapper;
import com.demo.spring.business.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-20
 */
@Slf4j
@Service(value = "demoService")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public String get() {
        log.info("");
        String get = demoMapper.get();
        return get;
    }
}
