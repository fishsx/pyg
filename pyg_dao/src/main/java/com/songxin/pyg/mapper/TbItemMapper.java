package com.songxin.pyg.mapper;

import com.songxin.pyg.pojo.TbItem;
import com.songxin.pyg.pojo.TbItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    /**
     * 查找所有已上架的商品
     * @param
     * @return java.util.List<com.songxin.pyg.pojo.TbItem>
     * @author fishsx
     * @date 2018/12/17 下午7:01
     */
    List<TbItem> findAllGrounding();
}