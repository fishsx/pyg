package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbBrand;
import com.songxin.pyg.seller.service.BrandService;
import com.songxin.pyg.vo.OperateResultVO;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 品牌的控制器
 * @author fishsx
 * @date 2018/12/3 下午5:52
 */
@RequestMapping("brand")
@RestController
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return java.util.List<com.songxin.pyg.pojo.TbBrand>
     * @author fishsx
     * @date 2018/12/3 下午5:55
     */
    @RequestMapping("findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }


    /**
     * @param pageNum   当前页数
	 * @param pageSize  每页条数
     * @return PageResult
     * @author fishsx
     * @date 2018/12/4 下午4:25
     */
    @RequestMapping("findAllByPage")
    public PageResultVO findAllByPage(Integer pageNum, Integer pageSize) {
        return brandService.findAllByPage(pageNum, pageSize);
    }


    /**
     * @param brand
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/4 下午7:31
     */
    @RequestMapping("add")
    public OperateResultVO add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "操作失败");
        }
    }

    /**
     * 根据id查找品牌
     * @param id    品牌id
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/4 下午8:21
     */
    @RequestMapping("findOneById")
    public TbBrand findOneById(Long id) {
        return brandService.findOneById(id);
    }

    /**
     * 修改品牌
     * @param brand
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/4 下午8:35
     */
    @RequestMapping("update")
    public OperateResultVO update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "修改失败");
        }
    }

    /**
     * 批量删除品牌
     * @param checkedIds
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/4 下午10:09
     */
    @RequestMapping("batchDelete")
    public OperateResultVO batchDelete(@RequestBody Long[] checkedIds) {
        if (checkedIds == null || checkedIds.length < 1) {
            return new OperateResultVO(false, "参数异常!参数为空");
        }

        try {
            brandService.batchDelete(checkedIds);
            return new OperateResultVO(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "删除失败");
        }
    }

    /**
     * 根据条件查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/4 下午11:22
     */
    @RequestMapping("findByCondition")
    public PageResultVO findByCondition(Integer pageNum, Integer pageSize,
                                        @RequestBody TbBrand brand) {
        return brandService.findByCondition(pageNum, pageSize, brand);
    }

    /**
     * 查询品牌Json列表
     * 返回结果sample:
     *   [{
     *     "id": 1,
     *     "text": "联想"
     *   },
     *   {
     *     "id": 2,
     *     "text": "华为"
     *   }]
     * @param
     * @return java.util.List<java.util.Map>
     * @author fishsx
     * @date 2018/12/8 上午11:41
     */
    @RequestMapping("findBrandJsonList")
    public List<Map> findBrandJsonList() {
        return brandService.findBrandJsonList();
    }
}
