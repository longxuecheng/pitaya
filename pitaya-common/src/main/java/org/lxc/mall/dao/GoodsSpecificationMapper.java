package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.GoodsSpecification;

public interface GoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    GoodsSpecification selectByPrimaryKey(Integer id);
    
    List<GoodsSpecification> selectByGoodsId(Long goodsId);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);
}