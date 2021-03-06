package com.songxin.pyg.seller.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.songxin.pyg.mapper.BrandMapper;
import com.songxin.pyg.pojo.TbBrand;
import com.songxin.pyg.seller.service.BrandService;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 品牌的业务层的实现类
 * @author fishsx
 * @date 2018/12/3 下午5:33
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有品牌列表
     * @return java.util.List<com.songxin.pyg.pojo.TbBrand>
     * @author fishsx
     * @date 2018/12/3 下午5:33
     */
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.findAll();
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/4 下午4:32
     */
    @Override
    public PageResultVO findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> list = (Page<TbBrand>) brandMapper.findAll();
        return new PageResultVO(list.getTotal(), list.getResult());
    }

    /**
     * 添加品牌
     * @param brand
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午7:33
     */
    @Override
    public void add(TbBrand brand) {
        brandMapper.add(brand);
    }

    /**
     * 根据id查找品牌
     *
     * @param id
     * @return com.songxin.pyg.pojo.TbBrand
     * @author fishsx
     * @date 2018/12/4 下午8:22
     */
    @Override
    public TbBrand findOneById(Long id) {
        return brandMapper.findOneById(id);
    }

    /**
     * 修改品牌
     *
     * @param brand
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午8:37
     */
    @Override
    public void update(TbBrand brand) {
        brandMapper.update(brand);
    }

    /**
     * 批量删除品牌
     *
     * @param checkedIds
     * @return void
     * @author fishsx
     * @date 2018/12/4 下午10:17
     */
    @Override
    public void batchDelete(Long[] checkedIds) {
        for (Long id : checkedIds) {
            brandMapper.delete(id);
        }
    }

    /**
     * 根据条件查询
     *
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return com.songxin.pyg.vo.PageResultVO
     * @author fishsx
     * @date 2018/12/4 下午11:25
     */
    @Override
    public PageResultVO findByCondition(Integer pageNum, Integer pageSize, TbBrand brand) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.findByCondition(brand);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

    /**
     * 查询品牌Json列表
     *
     * @return java.util.List<java.util.Map>
     * @author fishsx
     * @date 2018/12/8 上午11:43
     */
    @Override
    public List<Map> findBrandJsonList() {
        return brandMapper.findBrandJsonList();
    }
}
