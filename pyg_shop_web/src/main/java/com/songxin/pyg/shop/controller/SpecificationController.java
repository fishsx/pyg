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
     * 根据模板ID查询规格及选项Json列表
     * 返回结果sample:
     * [
     *     {
     *         "attributeName": "网络制式",
     *         "attributeValue": [
     *             "移动3G",
     *             "移动4G"
     *         ]
     *     },
     *     {
     *         "attributeName": "屏幕尺寸",
     *         "attributeValue": [
     *             "6寸",
     *             "5.5寸"
     *         ]
     *     }
     * ]
     * @return java.util.List<java.util.Map>
     * @author fishsx
     * @date 2018/12/8 上午12:07
     */
    @RequestMapping("findSpecOptionsJsonList")
    public List<Map> findSpecOptionsJsonList(Long typeTemplateId) {
        return specificationService.findSpecOptionsJsonList(typeTemplateId);
    }

}
