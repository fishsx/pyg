package com.songxin.pyg.vo.combvo;

import com.songxin.pyg.pojo.TbGoods;
import com.songxin.pyg.pojo.TbGoodsDesc;
import com.songxin.pyg.pojo.TbItem;

import java.io.Serializable;
import java.util.List;

/**
 * 商品的组合[VO]
 * @author fishsx
 * @date 2018/12/11 下午7:43
 */
public class GoodsVO implements Serializable {

    private static final long serialVersionUID = 3563126053406681291L;

    private TbGoods goods;
    private TbGoodsDesc goodsDesc;
    private List<TbItem> itemList;
    private List<String> categoryNameList;

    public List<String> getCategoryNameList() {
        return categoryNameList;
    }

    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GoodsVO{");
        sb.append("goods=").append(goods);
        sb.append(", goodsDesc=").append(goodsDesc);
        sb.append(", itemList=").append(itemList);
        sb.append('}');
        return sb.toString();
    }
}
