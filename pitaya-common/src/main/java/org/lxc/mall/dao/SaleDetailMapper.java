package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.SaleDetail;

public interface SaleDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleDetail record);

    int insertSelective(SaleDetail record);

    SaleDetail selectByPrimaryKey(Long id);
    
    List<SaleDetail> selectByOrderId(Long orderId);

    int updateByPrimaryKeySelective(SaleDetail record);

    int updateByPrimaryKey(SaleDetail record);
}