package com.server.client.service;

import com.server.common.ClientSource;

public interface ClientSoruceService {

    boolean validate(ClientSource clientSource);

    int insert(ClientSource clientSource);

}
