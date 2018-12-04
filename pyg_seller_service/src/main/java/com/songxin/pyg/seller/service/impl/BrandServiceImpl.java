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
}
