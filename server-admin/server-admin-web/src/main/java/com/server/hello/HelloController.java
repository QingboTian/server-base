package com.server.hello;

import com.server.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/19 15:50
*/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private DemoService demoService;

    @GetMapping
    public String hello() {
        return demoService.hello();
    }

}
