package com.songxin.pyg.seller.service;
import java.util.List;
import com.songxin.pyg.pojo.TbGoods;

import com.songxin.pyg.vo.PageResultVO;
import com.songxin.pyg.vo.combvo.GoodsVO;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResultVO findByPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbGoods goods);
	
	
	/**
	 * 修改
	 */
	public void update(TbGoods goods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbGoods findOneById(Long id);
	
	
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
	public PageResultVO findByCondition(TbGoods goods, int pageNum, int pageSize);

	/**
	 * 添加货物[组合实体] 
	 * @param goods 
	 * @return void
	 * @author fishsx
	 * @date 2018/12/11 下午8:35
	 */
	public void addCombGoods(GoodsVO goods); 
	
}
