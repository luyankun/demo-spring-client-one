package com.demo.spring.business.controller;

import com.demo.spring.business.service.DemoService;
import com.demo.spring.common.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luyankun
 * @description:
 * @createDate 2018-12-19
 */
@Slf4j
@RequestMapping(value = "/demo/")
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "get")
    public String get() {
        String get = demoService.get();
        return get;
    }

    @GetMapping(value = "addRedis")
    public String addRedis(
            @RequestParam String key,
            @RequestParam String value){
        log.info("key:{},value:{}", key, value);
        redisUtil.addStringData(key, value);
        return "success";
    }

    @GetMapping(value = "getRedis")
    public String getRedis(@RequestParam String key){
        log.info("key:{}", key);
        return redisUtil.getStringData(key);
    }

    @GetMapping(value = "deleteRedis")
    public String deleteRedis(@RequestParam String key) {
        log.info("key:{}", key);
        redisUtil.deleteKey(key);
        return "success";
    }

    @GetMapping(value = "addRedisList")
    public String addRedisList(
            @RequestParam String key,
            @RequestParam String value){
        log.info("key:{},value:{}", key, value);
        redisUtil.addListData(key, value);
        return "success";
    }

    @GetMapping(value = "getRedisList")
    public List<String> getRedisList(@RequestParam String key){
        log.info("key:{}", key);
        return redisUtil.getListData(key);
    }

    @GetMapping(value = "deleteRedisList")
    public String deleteRedisList(@RequestParam String key) {
        log.info("key:{}", key);
        redisUtil.deleteKey(key);
        return "success";
    }
}
