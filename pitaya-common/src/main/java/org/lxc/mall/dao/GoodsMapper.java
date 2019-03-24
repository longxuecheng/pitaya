package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.Goods;

public interface GoodsMapper {
	
	List<Goods> selectAll();
	
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
}