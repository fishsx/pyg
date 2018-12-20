package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbGoods;
import com.songxin.pyg.seller.service.GoodsService;
import com.songxin.pyg.vo.OperateResultVO;
import com.songxin.pyg.vo.PageResultVO;
import com.songxin.pyg.vo.combvo.GoodsVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 商品控制层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){			
		return goodsService.findAll();
	}
	
	
	/**
	 * 分页返回全部列表
	 * @return
	 */
	@RequestMapping("/findByPage")
	public PageResultVO  findByPage(Integer pageNum, Integer pageSize){
		return goodsService.findByPage(pageNum, pageSize);
	}
	
	/**
	 * 增加组合货物实体
	 * @param goods
	 * @return
	 */
	@RequestMapping("/addCombGoods")
	public OperateResultVO addCombGoods(@RequestBody GoodsVO goods){
		try {
			//设置商家ID
			String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
			goods.getGoods().setSellerId(sellerId);
			goodsService.addCombGoods(goods);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public OperateResultVO update(@RequestBody TbGoods goods){
		try {
			goodsService.update(goods);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOneById")
	public TbGoods findOneById(Long id){
		return goodsService.findOneById(id);
	}
	
	/**
	 * 批量删除
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("/batchDelete")
	public OperateResultVO batchDelete(@RequestBody Long [] checkedIds){
		try {
			goodsService.batchDelete(checkedIds);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "删除失败");
		}
	}
	
	/**
	 * 根据条件分页查询
	 * @param goods
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findByCondition")
	public PageResultVO findByCondition(@RequestBody TbGoods goods,Integer pageNum, Integer pageSize){
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.setSellerId(sellerId);
		return goodsService.findByCondition(goods, pageNum, pageSize);
	}
	
}
