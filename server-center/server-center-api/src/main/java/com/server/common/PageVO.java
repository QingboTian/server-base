package com.server.common;

import lombok.Data;

/**
 * @Description:
 * @Author tianqb
 * @Mail tianqingbo@tianqb.cn
 * @Date 2019/12/23 16:53
 * @Version v1.0
 */
@Data
public class PageVO {
    private Integer currentPage = 1;

    private Integer pageSize = 5;
}
