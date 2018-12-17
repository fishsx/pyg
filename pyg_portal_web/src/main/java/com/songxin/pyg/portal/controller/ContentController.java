package com.songxin.pyg.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.content.service.ContentService;
import com.songxin.pyg.pojo.TbContent;
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
@RequestMapping("/content")
public class ContentController {

	@Reference
	private ContentService contentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbContent> findAll(){			
		return contentService.findAll();
	}
	
	/**
	 * 查找所有已启用的广告 
	 * @param id 
	 * @return java.util.List<com.songxin.pyg.pojo.TbContent>
	 * @author fishsx
	 * @date 2018/12/15 下午8:40
	 */
	@RequestMapping("/findAllEnabledByCateId")
	public List<TbContent> findAllEnabledByCateId(Long id) {
		return contentService.findAllEnabledByCateId(id);
	}
	
	/**
	 * 分页返回全部列表
	 * @return
	 */
	@RequestMapping("/findByPage")
	public PageResultVO  findByPage(Integer pageNum, Integer pageSize){
		return contentService.findByPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param content
	 * @return
	 */
	@RequestMapping("/add")
	public OperateResultVO add(@RequestBody TbContent content){
		try {
			contentService.add(content);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param content
	 * @return
	 */
	@RequestMapping("/update")
	public OperateResultVO update(@RequestBody TbContent content){
		try {
			contentService.update(content);
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
	public TbContent findOneById(Long id){
		return contentService.findOneById(id);
	}
	
	/**
	 * 批量删除
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("/batchDelete")
	public OperateResultVO batchDelete(@RequestBody Long [] checkedIds){
		try {
			contentService.batchDelete(checkedIds);
			return new OperateResultVO(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResultVO(false, "删除失败");
		}
	}
	
	/**
	 * 根据条件分页查询
	 * @param content
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findByCondition")
	public PageResultVO findByCondition(@RequestBody TbContent content,Integer pageNum, Integer pageSize){
		return contentService.findByCondition(content, pageNum, pageSize);
	}
	
}
