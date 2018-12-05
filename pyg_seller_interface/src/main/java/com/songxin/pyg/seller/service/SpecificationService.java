package com.songxin.pyg.seller.service;

import com.songxin.pyg.pojo.TbSpecification;
import com.songxin.pyg.vo.PageResultVO;

/**
 * 规格的业务层接口
 * @author fishsx
 * @date 2018/12/5 下午4:58
 */
public interface SpecificationService {
    /**
     * 查询所有规格
     * @param
     * @param pageNum
     * @param pageSize
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/5 下午5:07
     */
    PageResultVO findByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据条件查询规格
     * @param pageNum
     * @param pageSize
     * @param specification
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/5 下午7:15
     */
    PageResultVO findByCondition(Integer pageNum, Integer pageSize, TbSpecification specification);
}