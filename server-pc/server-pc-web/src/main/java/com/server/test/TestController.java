package com.server.test;

import com.server.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/19 14:19
*/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello() {
        return demoService.hello();
    }
}
