package com.songxin.pyg.seller.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.songxin.pyg.mapper.*;
import com.songxin.pyg.pojo.*;
import com.songxin.pyg.seller.service.GoodsService;
import com.songxin.pyg.vo.combvo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.songxin.pyg.pojo.TbGoodsExample.Criteria;

import com.songxin.pyg.vo.PageResultVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper goodsMapper;

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private TbSellerMapper sellerMapper;

    @Autowired
    private TbItemMapper itemMapper;


    /**
     * 添加货物[组合实体]
     *
     * @param goodsVO
     * @return void
     * @author fishsx
     * @date 2018/12/11 下午8:19
     */
    @Override
    public void addCombGoods(GoodsVO goodsVO) {
        if (goodsVO.getGoods() != null) {
            TbGoods goods = goodsVO.getGoods();
            // 设置未审核状态 -- 0
            goods.setAuditStatus("0");
            goodsMapper.insert(goods);
            //获取刚插入的货物ID,用于从表的新增记录
            Long id = goodsVO.getGoods().getId();
            if (goodsVO.getGoodsDesc() != null) {
                goodsVO.getGoodsDesc().setGoodsId(id);
                goodsDescMapper.insert(goodsVO.getGoodsDesc());
            }



                //启用规格时, 组装标题格式
                if ("1".equals(goodsVO.getGoods().getIsEnableSpec())) {
                    for (TbItem item : goodsVO.getItemList()) {
                        // 标题
                        StringBuilder title = new StringBuilder(goodsVO.getGoods().getGoodsName());
                        Map<String, Object> specMap = JSON.parseObject(item.getSpec());
                        for (String key : specMap.keySet()) {
                            title.append(" ").append(specMap.get(key));
                        }
                        item.setTitle(title.toString());
                        setItemValue(goodsVO, item);
                        itemMapper.insert(item);
                    }
                } else {
                    //没有启用规格，生一条默认的item数据
                    TbItem item = new TbItem();
                    item.setTitle(goods.getGoodsName());
                    setItemValue(goodsVO,item);
                    /*为启用规格时，组装页面需要提交的数据
                    `spec` varchar(200) DEFAULT NULL,
                     `price` decimal(20,2) NOT NULL COMMENT '商品价格，单位为：元',
                     `num` int(10) NOT NULL COMMENT '库存数量',
                     `status` varchar(1) NOT NULL COMMENT '商品状态，1-正常，2-下架，3-删除',
                     `is_default` varchar(1) DEFAULT NULL,*/
                    item.setSpec("{}");
                    item.setPrice(goods.getPrice());
                    item.setNum(99999);
                    item.setStatus("1");
                    item.setIsDefault("1");
                    itemMapper.insert(item);
                }
            }

    }

    /**
     * 设置除了商品标题以外的属性
     * @param goodsVO
	 * @param item
     * @return void
     * @author fishsx
     * @date 2018/12/17 下午11:18
     */
    private void setItemValue(GoodsVO goodsVO, TbItem item) {
        // 商品SPU编号
        item.setGoodsId(goodsVO.getGoods().getId());
        // 商家编号
        item.setSellerId(goodsVO.getGoods().getSellerId());
        // 商品分类编号（3级）
        item.setCategoryid(goodsVO.getGoods().getCategory3Id());
        // 创建日期
        item.setCreateTime(new Date());
        // 修改日期
        item.setUpdateTime(new Date());
        // 品牌名称
        TbBrand brand = brandMapper.findOneById(goodsVO.getGoods().getBrandId());
        item.setBrand(brand.getName());
        // 分类名称
        TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goodsVO.getGoods().getCategory3Id());
        item.setCategory(itemCat.getName());
        // 商家名称
        TbSeller seller = sellerMapper.selectByPrimaryKey(goodsVO.getGoods().getSellerId());
        item.setSeller(seller.getNickName());
        // 图片地址（取spu的第一个图片）
        List<Map> imageList = JSON.parseArray(goodsVO.getGoodsDesc().getItemImages(), Map.class);
        if (imageList.size() > 0) {
            item.setImage((String) imageList.get(0).get("url"));
        }
    }

    /**
     * 查询全部
     */
    @Override
    public List<TbGoods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResultVO findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(null);
        return new PageResultVO(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbGoods goods) {
        goodsMapper.insert(goods);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbGoods goods) {
        goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbGoods findOneById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void batchDelete(Long[] ids) {
        for (Long id : ids) {
            goodsMapper.deleteByPrimaryKey(id);
        }
    }


    /**
     * 分页查找
     */
    @Override
    public PageResultVO findByCondition(TbGoods goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbGoodsExample example = new TbGoodsExample();
        Criteria criteria = example.createCriteria();

        if (goods != null) {
            if (goods.getSellerId() != null && goods.getSellerId().length() > 0) {
                criteria.andSellerIdLike("%" + goods.getSellerId() + "%");
            }
            if (goods.getGoodsName() != null && goods.getGoodsName().length() > 0) {
                criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            }
            if (goods.getAuditStatus() != null && goods.getAuditStatus().length() > 0) {
                criteria.andAuditStatusLike("%" + goods.getAuditStatus() + "%");
            }
            if (goods.getIsMarketable() != null && goods.getIsMarketable().length() > 0) {
                criteria.andIsMarketableLike("%" + goods.getIsMarketable() + "%");
            }
            if (goods.getCaption() != null && goods.getCaption().length() > 0) {
                criteria.andCaptionLike("%" + goods.getCaption() + "%");
            }
            if (goods.getSmallPic() != null && goods.getSmallPic().length() > 0) {
                criteria.andSmallPicLike("%" + goods.getSmallPic() + "%");
            }
            if (goods.getIsEnableSpec() != null && goods.getIsEnableSpec().length() > 0) {
                criteria.andIsEnableSpecLike("%" + goods.getIsEnableSpec() + "%");
            }
            if (goods.getIsDelete() != null && goods.getIsDelete().length() > 0) {
                criteria.andIsDeleteLike("%" + goods.getIsDelete() + "%");
            }

        }

        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(example);
        return new PageResultVO(page.getTotal(), page.getResult());
    }


}
