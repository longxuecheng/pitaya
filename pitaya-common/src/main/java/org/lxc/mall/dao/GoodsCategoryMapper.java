package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.GoodsCategory;

public interface GoodsCategoryMapper {
	
	List<GoodsCategory> selectAll();
	
	List<GoodsCategory> selectAllTop();
	
	List<GoodsCategory> selectChildren(Integer parentId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);
}