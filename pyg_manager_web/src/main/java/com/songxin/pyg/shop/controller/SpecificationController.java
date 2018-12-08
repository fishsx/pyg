package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbSpecification;
import com.songxin.pyg.seller.service.SpecificationService;
import com.songxin.pyg.vo.OperateResultVO;
import com.songxin.pyg.vo.PageResultVO;
import com.songxin.pyg.vo.combvo.SpecificationVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    /**
     * 新增规格
     * @param specificationVO
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/5 下午7:51
     */
    @RequestMapping("add")
    public OperateResultVO add(@RequestBody SpecificationVO specificationVO) {
        try {
            specificationService.add(specificationVO);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "新增规格失败!");
        }
    }

    /**
     * 根据id查询规格
     * @param id
     * @return com.songxin.pyg.vo.combvo.SpecificationVO
     * @author fishsx
     * @date 2018/12/5 下午9:57
     */
    @RequestMapping("findOneById")
    public SpecificationVO findOneById(Long id) {
        return specificationService.findOneById(id);
    }

    /**
     * 修改规格
     * @param specificationVO
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/5 下午10:13
     */
    @RequestMapping("update")
    public OperateResultVO update(@RequestBody SpecificationVO specificationVO) {
        try {
            specificationService.update(specificationVO);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "修改失败");
        }
    }

    /**
     * 批量删除
     * @param checkedIds
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/5 下午10:38
     */
    @RequestMapping("batchDelete")
    public OperateResultVO batchDelete(@RequestBody Long[] checkedIds) {
        try {
            specificationService.batchDelete(checkedIds);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "删除失败");
        }

    }


    /**
     * 查询规格Json列表
     * 返回结果sample:
     *   [{
     *     "id": 1,
     *     "text": "XXXX"
     *   },
     *   {
     *     "id": 2,
     *     "text": "XX"
     *   }]
     * @return java.util.List<java.util.Map>
     * @author fishsx
     * @date 2018/12/8 上午12:07
     */
    @RequestMapping("findSpecJsonList")
    public List<Map> findSpecJsonList() {
        return specificationService.findSpecJsonList();
    }

}
