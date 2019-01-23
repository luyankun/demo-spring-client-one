package com.demo.spring.business.test;

import com.demo.spring.business.config.ApplicationStarter;
import com.demo.spring.business.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class DemoControllerTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void get(){
        String get = demoService.get();
    }
}
