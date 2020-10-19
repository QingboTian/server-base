package com.server.index;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/15 16:43
*/
@RestController
public class IndexController {

    @GetMapping({"/index", "/"})
    public Map index() {
        Map<String, String> index = new LinkedHashMap<>();
        index.put("name", "application name");
        index.put("version", "1.0");
        index.put("author", "tianqb");
        index.put("description", "Welcome to Server");
        return index;
    }
}
