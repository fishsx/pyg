package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbSpecification;
import com.songxin.pyg.pojo.TbSpecificationExample;
import com.songxin.pyg.seller.service.SpecificationService;
import com.songxin.pyg.vo.PageResultVO;
import org.omg.CORBA.INTERNAL;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规格的控制器
 * @author fishsx
 * @date 2018/12/5 下午5:02
 */
@RestController
@RequestMapping("specification")
public class SpecificationController {
    @Reference
    private SpecificationService specificationService;

    /**
     * 查询所有规格
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/5 下午5:06
     */
    @RequestMapping("findByPage")
    public PageResultVO findByPage(Integer pageNum, Integer pageSize) {
        return specificationService.findByPage(pageNum, pageSize);
    }

    /**
     * 根据条件分页查询
     * @param pageNum
     * @param pageSize
     * @param specification 查询实体类
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/5 下午7:14
     */
    @RequestMapping("findByCondition")
    public PageResultVO findByCondition(Integer pageNum, Integer pageSize, @RequestBody TbSpecification specification){
        return specificationService.findByCondition(pageNum, pageSize, specification);
    }
}
