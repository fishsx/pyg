package com.songxin.pyg.content.service;
import java.util.List;
import com.songxin.pyg.pojo.TbContent;

import com.songxin.pyg.vo.PageResultVO;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ContentService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbContent> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResultVO findByPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbContent content);
	
	
	/**
	 * 修改
	 */
	public void update(TbContent content);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbContent findOneById(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void batchDelete(Long[] ids);

	/**
	 * 根据条件分页查询
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResultVO findByCondition(TbContent content, int pageNum, int pageSize);
	
}
