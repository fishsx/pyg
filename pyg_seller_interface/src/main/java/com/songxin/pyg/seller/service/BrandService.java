package com.songxin.pyg.seller.service;

import com.songxin.pyg.pojo.TbBrand;
import com.songxin.pyg.vo.PageResultVO;

import java.util.List;

/**
 * 品牌的业务层接口
 * @author fishsx
 * @date 2018/12/3 下午5:32
 */
public interface BrandService {
    /**
     * 查询所有品牌列表
     * @return java.util.List<com.songxin.pyg.pojo.TbBrand>
     * @author fishsx
     * @date 2018/12/3 下午5:33
     */
    List<TbBrand> findAll();

    /**
     * @param pageNum
	 * @param pageSize
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/4 下午4:32
     */
    PageResultVO findAllByPage(Integer pageNum, Integer pageSize);

    /**
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
     * @date 2018/12/4 下午8:22
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
     * 批量删除品牌
     * @param checkedIds
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午10:17
     */
    void batchDelete(Long[] checkedIds);

    /**
     * 根据条件查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/4 下午11:25
     */
    PageResultVO findByCondition(Integer pageNum, Integer pageSize, TbBrand brand);
}
