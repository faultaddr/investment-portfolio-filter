package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StringRedisController {  
      
    protected static Logger logger=LoggerFactory.getLogger(StringRedisController.class);
      
    @Autowired
    StringRedisTemplate stringRedisTemplate;
      
    @Resource(name="stringRedisTemplate")
    ValueOperations<String,String> valOpsStr;
      
    @RequestMapping("set")
    public String setKeyAndValue(String key,String value){  
        logger.debug("访问set:key={},value={}",key,value);  
        valOpsStr.set(key, value);  
        return "Set Ok";  
    }  
      
    @RequestMapping("get")
    public String getKey(String key){  
        logger.debug("访问get:key={}",key);  
        return valOpsStr.get(key);  
    }  
}  