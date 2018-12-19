package com.demo.spring.rest.controller;

import com.demo.spring.service.biz.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-19
 */
@RequestMapping(value = "/demo/")
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "get")
    public String get() {
        return demoService.get();
    }
}
