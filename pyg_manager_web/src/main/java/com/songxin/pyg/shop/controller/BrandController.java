package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbBrand;
import com.songxin.pyg.seller.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌的控制器
 * @author fishsx
 * @date 2018/12/3 下午5:52
 */
@RequestMapping("brand")
@RestController
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return java.util.List<com.songxin.pyg.pojo.TbBrand>
     * @author fishsx
     * @date 2018/12/3 下午5:55
     */
    @RequestMapping("findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }
}
