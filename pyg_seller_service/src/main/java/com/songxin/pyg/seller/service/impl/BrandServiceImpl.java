package com.songxin.pyg.seller.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.songxin.pyg.mapper.BrandMapper;
import com.songxin.pyg.pojo.TbBrand;
import com.songxin.pyg.seller.service.BrandService;
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

}
