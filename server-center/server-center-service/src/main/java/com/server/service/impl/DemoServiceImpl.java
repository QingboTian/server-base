package com.server.service.impl;

import com.server.api.DemoService;
import org.springframework.stereotype.Service;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/19 11:38
*/
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String hello() {
        return "Hello";
    }
}
