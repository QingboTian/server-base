package com.server.client.controller;

import com.server.client.service.ClientSoruceService;
import com.server.common.ClientSource;
import com.server.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/21 9:57
*/
@RestController
@RequestMapping("/clientSource")
@Slf4j
public class ClientSourceController {

    private static final String TOKEN = "b764d299-3698-49ba-ae37-6961196105d7";

    @Autowired
    private ClientSoruceService clientSoruceService;

    /**
     * 添加授权应用信息
     * @return
     */
    @PostMapping
    public Result authorization(@RequestBody ClientSource clientSource, @RequestParam String token) {
        String appId = clientSource.getAppId();
        String appName = clientSource.getAppName();
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appName)) {
            return Result.build(HttpStatus.FORBIDDEN.value(), "appId and appName should not be null");
        }

        if (!Objects.equals(token, TOKEN)) {
            return Result.build(HttpStatus.FORBIDDEN.value(), "Token check fails");
        }

        return Result.ok(clientSoruceService.insert(clientSource) != 0);
    }

}
