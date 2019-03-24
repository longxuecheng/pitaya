package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.Specification;

public interface SpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Specification record);

    int insertSelective(Specification record);

    Specification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);
    
    List<Specification> selectAll();
}