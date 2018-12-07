package com.songxin.pyg.shop.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbGoodsDesc;
import com.songxin.pyg.seller.service.GoodsDescService;

import com.songxin.pyg.vo.PageResultVO;
import com.songxin.pyg.vo.OperateResultVO;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goodsDesc")
public class GoodsDescController {

	@Reference
	private GoodsDescService goodsDescService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoodsDesc> findAll(){			
		return goodsDescService.findAll();
	}
	
	
	/**
	 * 分页返回全部列表
	 * @return
	 */
	@RequestMapping("/findByPage")
	public PageResultVO  findByPage(Integer pageNum, Integer pageSize){
		return goodsDescService.findByPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param goodsDesc
	 * @return
	 */
	@RequestMapping("/add")
	public OperateResultVO add(@RequestBody TbGoodsDesc goodsDesc){
		try {
			goodsDescService.add(goodsDesc);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goodsDesc
	 * @return
	 */
	@RequestMapping("/update")
	public OperateResultVO update(@RequestBody TbGoodsDesc goodsDesc){
		try {
			goodsDescService.update(goodsDesc);
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
	public TbGoodsDesc findOneById(Long id){
		return goodsDescService.findOneById(id);
	}
	
	/**
	 * 批量删除
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("/batchDelete")
	public OperateResultVO batchDelete(@RequestBody Long [] checkedIds){
		try {
			goodsDescService.batchDelete(checkedIds);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "删除失败");
		}
	}
	
	/**
	 * 根据条件分页查询
	 * @param goodsDesc
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findByCondition")
	public PageResultVO findByCondition(@RequestBody TbGoodsDesc goodsDesc,Integer pageNum, Integer pageSize){
		return goodsDescService.findByCondition(goodsDesc, pageNum, pageSize);
	}
	
}
