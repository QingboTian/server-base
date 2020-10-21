package com.server.client.service.impl;

import com.server.client.mapper.ClientSourceMapper;
import com.server.client.pojo.ClientSourceExample;
import com.server.client.service.ClientSoruceService;
import com.server.common.ClientSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2020/10/20 21:08
 */
@Service
@Slf4j
public class ClientSourceServiceImpl implements ClientSoruceService {

    @Autowired
    private ClientSourceMapper clientSourceMapper;

    @Override
    public boolean validate(ClientSource clientSource) {
        List<ClientSource> clientSources = clientSourceMapper.selectByExample(new ClientSourceExample());
        for (ClientSource c :
                clientSources) {
            if (Objects.equals(c, clientSource))
                return true;
        }
        return false;
    }

    @Override
    public int insert(ClientSource clientSource) {
        return clientSourceMapper.insert(clientSource);
    }
}
