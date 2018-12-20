package com.songxin.pyg.search.service;

import java.util.Map;

/**
 * 商品搜索业务接口
 * @author fishsx
 * @date 2018/12/18 下午3:22
 */
public interface ItemSearchService {

    /**
     * 商品搜索
     * @param searchMap
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author fishsx
     * @date 2018/12/18 下午3:23
     */
    Map<String, Object> search(Map searchMap);
}
