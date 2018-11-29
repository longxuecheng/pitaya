package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long id);
    
    List<Stock> selectByGoodsId(Long goodsId);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}