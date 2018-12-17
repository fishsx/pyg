package com.songxin.pyg.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.content.service.ContentCategoryService;
import com.songxin.pyg.pojo.TbContentCategory;
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
@RequestMapping("/contentCategory")
public class ContentCategoryController {

	@Reference
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbContentCategory> findAll(){			
		return contentCategoryService.findAll();
	}
	
	
	/**
	 * 分页返回全部列表
	 * @return
	 */
	@RequestMapping("/findByPage")
	public PageResultVO  findByPage(Integer pageNum, Integer pageSize){
		return contentCategoryService.findByPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping("/add")
	public OperateResultVO add(@RequestBody TbContentCategory contentCategory){
		try {
			contentCategoryService.add(contentCategory);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping("/update")
	public OperateResultVO update(@RequestBody TbContentCategory contentCategory){
		try {
			contentCategoryService.update(contentCategory);
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
	public TbContentCategory findOneById(Long id){
		return contentCategoryService.findOneById(id);
	}
	
	/**
	 * 批量删除
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("/batchDelete")
	public OperateResultVO batchDelete(@RequestBody Long [] checkedIds){
		try {
			contentCategoryService.batchDelete(checkedIds);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "删除失败");
		}
	}
	
	/**
	 * 根据条件分页查询
	 * @param contentCategory
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findByCondition")
	public PageResultVO findByCondition(@RequestBody TbContentCategory contentCategory,Integer pageNum, Integer pageSize){
		return contentCategoryService.findByCondition(contentCategory, pageNum, pageSize);
	}
	
}
