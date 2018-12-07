package com.songxin.pyg.seller.service;
import java.util.List;
import com.songxin.pyg.pojo.TbGoodsDesc;

import com.songxin.pyg.vo.PageResultVO;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsDescService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoodsDesc> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResultVO findByPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbGoodsDesc goodsDesc);
	
	
	/**
	 * 修改
	 */
	public void update(TbGoodsDesc goodsDesc);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbGoodsDesc findOneById(Long id);
	
	
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
	public PageResultVO findByCondition(TbGoodsDesc goodsDesc, int pageNum, int pageSize);
	
}
