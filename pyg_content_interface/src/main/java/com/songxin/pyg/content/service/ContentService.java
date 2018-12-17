package com.songxin.pyg.content.service;

import com.songxin.pyg.pojo.TbContent;
import com.songxin.pyg.vo.PageResultVO;

import java.util.List;
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


	/**
	 * 根据id查找所有已启动的广告列表 
	 * @param id 
	 * @return java.util.List<com.songxin.pyg.pojo.TbContent>
	 * @author fishsx
	 * @date 2018/12/15 下午8:34
	 */
	public List<TbContent> findAllEnabledByCateId(Long id);
}
