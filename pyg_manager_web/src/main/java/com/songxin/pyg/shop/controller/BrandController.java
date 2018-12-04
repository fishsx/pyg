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
    public TbBrand findOneById(Integer id) {
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
}
