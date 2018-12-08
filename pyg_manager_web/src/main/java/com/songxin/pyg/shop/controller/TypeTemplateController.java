package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbTypeTemplate;
import com.songxin.pyg.seller.service.TypeTemplateService;
import com.songxin.pyg.vo.OperateResultVO;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbTypeTemplate> findAll() {
        return typeTemplateService.findAll();
    }


    /**
     * 分页返回全部列表
     *
     * @return
     */
    @RequestMapping("/findByPage")
    public PageResultVO findByPage(Integer pageNum, Integer pageSize) {
        return typeTemplateService.findByPage(pageNum, pageSize);
    }

    /**
     * 增加
     *
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/add")
    public OperateResultVO add(@RequestBody TbTypeTemplate typeTemplate) {
        try {
            typeTemplateService.add(typeTemplate);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/update")
    public OperateResultVO update(@RequestBody TbTypeTemplate typeTemplate) {
        try {
            typeTemplateService.update(typeTemplate);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOneById")
    public TbTypeTemplate findOneById(Long id) {
        return typeTemplateService.findOneById(id);
    }

    /**
     * 批量删除
     *
     * @param checkedIds
     * @return
     */
    @RequestMapping("/batchDelete")
    public OperateResultVO batchDelete(@RequestBody Long[] checkedIds) {
        try {
            typeTemplateService.batchDelete(checkedIds);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "删除失败");
        }
    }

    /**
     * 根据条件分页查询
     *
     * @param typeTemplate
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findByCondition")
    public PageResultVO findByCondition(@RequestBody TbTypeTemplate typeTemplate, Integer pageNum, Integer pageSize) {
        return typeTemplateService.findByCondition(typeTemplate, pageNum, pageSize);
    }



}
