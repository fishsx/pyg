package com.songxin.pyg.mapper;

import com.songxin.pyg.pojo.TbItemCat;
import com.songxin.pyg.pojo.TbItemCatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCatMapper {
    int countByExample(TbItemCatExample example);

    int deleteByExample(TbItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemCat record);

    int insertSelective(TbItemCat record);

    List<TbItemCat> selectByExample(TbItemCatExample example);

    TbItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemCat record, @Param("example") TbItemCatExample example);

    int updateByExample(@Param("record") TbItemCat record, @Param("example") TbItemCatExample example);

    int updateByPrimaryKeySelective(TbItemCat record);

    int updateByPrimaryKey(TbItemCat record);

    /**
     * 根据父id查找类别
     * @param id
     * @return java.util.List<com.songxin.pyg.pojo.TbItemCat>
     * @author fishsx
     * @date 2018/12/8 下午3:08
     */
    List<TbItemCat> findByParentId(Long id);
}