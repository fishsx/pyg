package com.songxin.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.pojo.TbItemCat;
import com.songxin.pyg.seller.service.ItemCatService;
import com.songxin.pyg.vo.OperateResultVO;
import com.songxin.pyg.vo.PageResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;

	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){
		return itemCatService.findAll();
	}

	/**
	 * 根据父id查找类别
	 * @param id
	 * @return java.util.List<com.songxin.pyg.pojo.TbItemCat>
	 * @author fishsx
	 * @date 2018/12/8 下午3:04
	 */
	@RequestMapping("/findByParentId")
	public List<TbItemCat> findByParentId(Long id) {
		return itemCatService.findByParentId(id);
	}

	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public OperateResultVO add(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public OperateResultVO update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public TbItemCat findOneById(Long id){
		return itemCatService.findOneById(id);
	}

	/**
	 * 批量删除
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("/batchDelete")
	public OperateResultVO batchDelete(@RequestBody Long [] checkedIds){
		try {
			itemCatService.batchDelete(checkedIds);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "删除失败");
		}
	}

	/**
	 * 根据条件分页查询
	 * @param itemCat
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findByCondition")
	public PageResultVO findByCondition(@RequestBody TbItemCat itemCat,Integer pageNum, Integer pageSize){
		return itemCatService.findByCondition(itemCat, pageNum, pageSize);
	}

}
