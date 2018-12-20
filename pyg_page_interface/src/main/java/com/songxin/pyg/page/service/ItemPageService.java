package com.songxin.pyg.page.service;

import com.songxin.pyg.vo.combvo.GoodsVO;

/**
 * 商品详情页面接口
 * @author fishsx
 * @date 2018/12/19 下午3:49
 */
public interface ItemPageService {

    /**
     * 根据id查找商品 
     * @param id 
     * @return com.songxin.pyg.vo.combvo.GoodsVO
     * @author fishsx
     * @date 2018/12/19 下午4:16
     */
    GoodsVO findOne(Long id);
}
