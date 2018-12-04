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
}
