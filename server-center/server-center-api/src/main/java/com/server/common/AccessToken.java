package com.server.common;

import lombok.Data;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/15 17:25
*/
@Data
public class AccessToken {
    private String token;
    private Long expire;
}
