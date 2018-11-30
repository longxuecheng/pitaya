package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.SaleOrder;

public interface SaleOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleOrder record);

    int insertSelective(SaleOrder record);

    SaleOrder selectByPrimaryKey(Long id);
    
    List<SaleOrder> selectAll();

    int updateByPrimaryKeySelective(SaleOrder record);

    int updateByPrimaryKey(SaleOrder record);
}