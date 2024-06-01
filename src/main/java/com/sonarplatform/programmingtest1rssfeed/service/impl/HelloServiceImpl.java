package com.sonarplatform.programmingtest1rssfeed.service.impl;

import com.sonarplatform.programmingtest1rssfeed.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    /**
     * @param name
     * @return
     */
    @Override
    public String hello(String name) {
        if (name == null) return "Hello Sonar!";
        return "Hello " + name + "!";
    }
}
