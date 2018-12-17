package com.songxin.pyg.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.songxin.pyg.content.service.ContentService;
import com.songxin.pyg.mapper.TbContentMapper;
import com.songxin.pyg.pojo.TbContent;
import com.songxin.pyg.pojo.TbContentExample;
import com.songxin.pyg.pojo.TbContentExample.Criteria;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询全部
     */
    @Override
    public List<TbContent> findAll() {
        return contentMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResultVO findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(null);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbContent content) {
        contentMapper.insert(content);
        //清除新增的广告类别redis缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());


    }


    /**
     * 修改
     */
    @Override
    public void update(TbContent content) {
        contentMapper.updateByPrimaryKey(content);
        //获取修改前的广告对象
        TbContent beforeUpdate = contentMapper.selectByPrimaryKey(content.getId());
        if (!beforeUpdate.getCategoryId().equals(content.getCategoryId())) {
            //修改前后的广告类别变动过,清除2类缓存
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        }
        redisTemplate.boundHashOps("content").delete(beforeUpdate.getCategoryId());
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbContent findOneById(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void batchDelete(Long[] ids) {
        for (Long id : ids) {
            //清除当前广告类别的缓存
            TbContent delContent = contentMapper.selectByPrimaryKey(id);
            contentMapper.deleteByPrimaryKey(id);
            redisTemplate.boundHashOps("content").delete(delContent.getCategoryId());
        }
    }


    /**
     * 分页查找
     */
    @Override
    public PageResultVO findByCondition(TbContent content, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();

        if (content != null) {
            if (content.getTitle() != null && content.getTitle().length() > 0) {
                criteria.andTitleLike("%" + content.getTitle() + "%");
            }
            if (content.getUrl() != null && content.getUrl().length() > 0) {
                criteria.andUrlLike("%" + content.getUrl() + "%");
            }
            if (content.getPic() != null && content.getPic().length() > 0) {
                criteria.andPicLike("%" + content.getPic() + "%");
            }
            if (content.getStatus() != null && content.getStatus().length() > 0) {
                criteria.andStatusLike("%" + content.getStatus() + "%");
            }

        }

        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(example);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

    /**
     * 根据id查找所有已启动的广告列表
     *
     * @param id
     * @return java.util.List<com.songxin.pyg.pojo.TbContent>
     * @author fishsx
     * @date 2018/12/15 下午8:32
     */
    @Override
    public List<TbContent> findAllEnabledByCateId(Long id) {
        List<TbContent> contentList = (List<TbContent>) redisTemplate.boundHashOps("content").get(id);

        if (contentList == null) {
            //数据库查询
            System.out.println("数据库查询...");
            TbContentExample example = new TbContentExample();
            example.setOrderByClause("sort_order");
            example.or().andStatusEqualTo("1").andCategoryIdEqualTo(id);
            contentList = contentMapper.selectByExample(example);
            redisTemplate.boundHashOps("content").put(id, contentList);
        } else {
            System.out.println("redis查询...");
        }
        return contentList;
    }
}
