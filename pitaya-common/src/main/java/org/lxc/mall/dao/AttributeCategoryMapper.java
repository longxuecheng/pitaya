package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.AttributeCategory;

public interface AttributeCategoryMapper {
	
	List<AttributeCategory> selectAll();
	
    int deleteByPrimaryKey(Integer id);

    int insert(AttributeCategory record);

    int insertSelective(AttributeCategory record);

    AttributeCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttributeCategory record);

    int updateByPrimaryKey(AttributeCategory record);
}