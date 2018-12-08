package com.songxin.pyg.seller.service;

import com.songxin.pyg.pojo.TbSpecification;
import com.songxin.pyg.vo.PageResultVO;
import com.songxin.pyg.vo.combvo.SpecificationVO;

import java.util.List;
import java.util.Map;

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

    /**
     * 新增规格
     * @param specificationVO
     * @return void
     * @author fishsx
     * @date 2018/12/5 下午7:53
     */
    void add(SpecificationVO specificationVO);

    /**
     * 根据id查找规格
     * @param id
     * @return com.songxin.pyg.vo.combvo.SpecificationVO
     * @author fishsx
     * @date 2018/12/5 下午10:01
     */
    SpecificationVO findOneById(Long id);

    /**
     * 修改规格
     * @param specificationVO
     * @return void
     * @author fishsx
     * @date 2018/12/5 下午10:14
     */
    void update(SpecificationVO specificationVO);

    /**
     * 批量删除 
     * @param checkedIds 
     * @return void
     * @author fishsx
     * @date 2018/12/5 下午10:39
     */
    void batchDelete(Long[] checkedIds);

    /**
     * 查询规格Json列表
     * @param
     * @return java.util.List<java.util.Map>
     * @author fishsx
     * @date 2018/12/8 下午12:08
     */
    List<Map> findSpecJsonList();
}