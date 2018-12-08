package com.songxin.pyg.mapper;

import com.songxin.pyg.pojo.TbBrand;

import java.util.List;
import java.util.Map;

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

    /**
     * 删除品牌
     * @param id
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午10:19
     */
    void delete(Long id);

    /**
     * 根据条件查询
     * @param brand
     * @return java.util.List<com.songxin.pyg.pojo.TbBrand>
     * @author fishsx
     * @date 2018/12/4 下午11:29
     */
    List<TbBrand> findByCondition(TbBrand brand);

    /**
     * 查询品牌Json列表
     *
     * @return java.util.List<java.util.Map>
     * @author fishsx
     * @date 2018/12/8 上午11:43
     */
    public List<Map> findBrandJsonList();
}
