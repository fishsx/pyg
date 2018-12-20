package com.songxin.pyg.page.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.songxin.pyg.mapper.TbGoodsDescMapper;
import com.songxin.pyg.mapper.TbGoodsMapper;
import com.songxin.pyg.mapper.TbItemCatMapper;
import com.songxin.pyg.mapper.TbItemMapper;
import com.songxin.pyg.page.service.ItemPageService;
import com.songxin.pyg.pojo.TbGoods;
import com.songxin.pyg.pojo.TbGoodsDesc;
import com.songxin.pyg.pojo.TbItem;
import com.songxin.pyg.pojo.TbItemExample;
import com.songxin.pyg.vo.combvo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 商品详情页的实现
 * @author fishsx
 * @date 2018/12/19 下午3:54
 */
@Service
public class ItemPageServiceImpl implements ItemPageService {

    @Autowired
    private TbGoodsMapper goodsMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 根据id查找商品
     *
     * @param id
     * @return com.songxin.pyg.vo.combvo.GoodsVO
     * @author fishsx
     * @date 2018/12/19 下午4:07
     */
    @Override
    public GoodsVO findOne(Long id) {
        GoodsVO goodsVO = new GoodsVO();
        //1.设置SPU信息
        TbGoods goods = goodsMapper.selectByPrimaryKey(id);
        goodsVO.setGoods(goods);
        //2.设置SPU的描述信息
        TbGoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        goodsVO.setGoodsDesc(goodsDesc);
        //3.设置此SPU下所有的SKU列表
        TbItemExample example = new TbItemExample();
        example.or().andGoodsIdEqualTo(id);
        List<TbItem> itemList = itemMapper.selectByExample(example);
        goodsVO.setItemList(itemList);
        //4.查询SPU的多级类目
        List<String> categoryNameList = new ArrayList<>();
        String category1Name = itemCatMapper.selectByPrimaryKey(goods.getCategory1Id()).getName();
        String category2Name = itemCatMapper.selectByPrimaryKey(goods.getCategory2Id()).getName();
        String category3Name = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id()).getName();
        Collections.addAll(categoryNameList, category1Name, category2Name, category3Name);
        goodsVO.setCategoryNameList(categoryNameList);
        return goodsVO;
    }
}
