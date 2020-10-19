package com.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/19 11:06
*/
@Configuration
@ImportResource(value = {"classpath:dubbo/dubbo-provider.xml"})
public class DubboConfig {
}
