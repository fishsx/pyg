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

    /**
     * 添加品牌
     * @param brand
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午7:34
     */
    void add(TbBrand brand);

    /**
     * 根据id查找品牌
     * @param id
     * @return com.songxin.pyg.pojo.TbBrand
     * @author fishsx
     * @date 2018/12/4 下午8:23
     */
    TbBrand findOneById(Integer id);

    /**
     * 修改品牌
     * @param brand
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午8:37
     */
    void update(TbBrand brand);
}
