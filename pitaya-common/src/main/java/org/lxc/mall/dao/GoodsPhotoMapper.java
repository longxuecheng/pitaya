package org.lxc.mall.dao;

import java.util.List;

import org.lxc.mall.model.GoodsPhoto;

public interface GoodsPhotoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsPhoto record);

    int insertSelective(GoodsPhoto record);

    GoodsPhoto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsPhoto record);
    
    int updateGoodsIdByPrimaryKey(GoodsPhoto record);

    int updateByPrimaryKey(GoodsPhoto record);

	List<GoodsPhoto> selectByGoodsId(Long goodsId);
}