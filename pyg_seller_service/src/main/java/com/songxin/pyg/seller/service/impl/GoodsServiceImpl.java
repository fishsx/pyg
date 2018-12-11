package com.songxin.pyg.seller.service.impl;
import java.util.List;

import com.songxin.pyg.mapper.TbGoodsDescMapper;
import com.songxin.pyg.seller.service.GoodsService;
import com.songxin.pyg.vo.combvo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.songxin.pyg.mapper.TbGoodsMapper;
import com.songxin.pyg.pojo.TbGoods;

import com.songxin.pyg.pojo.TbGoodsExample;
import com.songxin.pyg.pojo.TbGoodsExample.Criteria;

import com.songxin.pyg.vo.PageResultVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;

	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	
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
		Page<TbGoods> page= (Page<TbGoods>) goodsMapper.selectByExample(null);
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
	public void update(TbGoods goods){
		goodsMapper.updateByPrimaryKey(goods);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbGoods findOneById(Long id){
		return goodsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDelete(Long[] ids) {
		for(Long id:ids){
			goodsMapper.deleteByPrimaryKey(id);
		}		
	}


	/**
	 * 分页查找
	 */
	@Override
	public PageResultVO findByCondition(TbGoods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodsExample example=new TbGoodsExample();
		Criteria criteria = example.createCriteria();
		
		if(goods!=null){			
						if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+goods.getSellerId()+"%");
			}
			if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
			}
			if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
				criteria.andAuditStatusLike("%"+goods.getAuditStatus()+"%");
			}
			if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
				criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
			}
			if(goods.getCaption()!=null && goods.getCaption().length()>0){
				criteria.andCaptionLike("%"+goods.getCaption()+"%");
			}
			if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
			}
			if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
				criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
			}
			if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
				criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
			}
	
		}
		
		Page<TbGoods> page= (Page<TbGoods>)goodsMapper.selectByExample(example);		
		return new PageResultVO(page.getTotal(), page.getResult());
	}

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
		}

	}
}
