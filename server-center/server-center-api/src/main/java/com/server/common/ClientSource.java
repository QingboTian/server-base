package com.server.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/20 20:48
*/
@Data
public class ClientSource implements Serializable {

    private static final long serialVersionUID = 9204501259849539059L;

    private Integer id;
    private String appId;
    private String appName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSource that = (ClientSource) o;
        return Objects.equals(appId, that.appId) &&
                Objects.equals(appName, that.appName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, appName);
    }
}
