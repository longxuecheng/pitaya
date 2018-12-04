package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.Supplier;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Long id);
    
    List<Supplier> selectByPrimaryKeys(List<Long> ids);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}