package com.songxin.pyg.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商品搜索控制器
 * @author fishsx
 * @date 2018/12/18 下午3:59
 */
@RestController
@RequestMapping("itemSearch")
public class ItemSearchController {

    @Reference
    private ItemSearchService searchService;

    /**
     * 商品搜索
     * @param searchMap
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author fishsx
     * @date 2018/12/18 下午4:01
     */
    @RequestMapping("search")
    public Map<String,Object> search(@RequestBody Map searchMap) {
        return searchService.search(searchMap);
    }
}
