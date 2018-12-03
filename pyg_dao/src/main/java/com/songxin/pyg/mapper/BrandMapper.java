package com.songxin.pyg.mapper;

import com.songxin.pyg.pojo.TbBrand;

import java.util.List;

/**
 * 品牌的持久层接口
 * @author fishsx
 * @date 2018/12/3 下午5:28
 */
public interface BrandMapper {
    /**
     * 查询所有品牌列表
     * @return java.util.List<com.songxin.pyg.pojo.TbBrand>
     * @author fishsx
     * @date 2018/12/3 下午5:29
     */
    List<TbBrand> findAll();
}
