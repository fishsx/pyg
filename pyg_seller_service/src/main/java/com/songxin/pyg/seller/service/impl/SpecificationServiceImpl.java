package com.songxin.pyg.seller.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.songxin.pyg.mapper.TbSpecificationMapper;
import com.songxin.pyg.pojo.TbSpecification;
import com.songxin.pyg.pojo.TbSpecificationExample;
import com.songxin.pyg.seller.service.SpecificationService;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 规格的业务层实现
 * @author fishsx
 * @date 2018/12/5 下午5:01
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    /**
     * 查询所有规格
     *
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/5 下午5:07
     * @param pageNum
     * @param pageSize
     */
    @Override
    public PageResultVO findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

    /**
     * 根据条件查询规格
     *
     * @param pageNum
     * @param pageSize
     * @param specification
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/5 下午7:15
     */
    @Override
    public PageResultVO findByCondition(Integer pageNum, Integer pageSize, TbSpecification specification) {
        TbSpecificationExample example = new TbSpecificationExample();
        //模糊查询规格名称
        example.or().andSpecNameLike("%"+specification.getSpecName()+"%");
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResultVO(page.getTotal(), page.getResult());
    }
}
