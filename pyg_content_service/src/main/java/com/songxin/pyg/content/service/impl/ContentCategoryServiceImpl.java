package com.songxin.pyg.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.songxin.pyg.content.service.ContentCategoryService;
import com.songxin.pyg.mapper.TbContentCategoryMapper;
import com.songxin.pyg.pojo.TbContentCategory;
import com.songxin.pyg.pojo.TbContentCategoryExample;
import com.songxin.pyg.pojo.TbContentCategoryExample.Criteria;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbContentCategory> findAll() {
        return contentCategoryMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResultVO findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContentCategory> page = (Page<TbContentCategory>) contentCategoryMapper.selectByExample(null);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbContentCategory contentCategory) {
        contentCategoryMapper.insert(contentCategory);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbContentCategory contentCategory) {
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbContentCategory findOneById(Long id) {
        return contentCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void batchDelete(Long[] ids) {
        for (Long id : ids) {
            contentCategoryMapper.deleteByPrimaryKey(id);
        }
    }


    /**
     * 分页查找
     */
    @Override
    public PageResultVO findByCondition(TbContentCategory contentCategory, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();

        if (contentCategory != null) {
            if (contentCategory.getName() != null && contentCategory.getName().length() > 0) {
                criteria.andNameLike("%" + contentCategory.getName() + "%");
            }

        }

        Page<TbContentCategory> page = (Page<TbContentCategory>) contentCategoryMapper.selectByExample(example);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

}
