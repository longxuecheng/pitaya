package org.lxc.mall.api.stock;

import java.util.List;

import org.lxc.mall.model.Stock;

public interface IStockService {
	
	// Query stocks for provided goods id
	public List<Stock> queryByGoodsId(Long goodsId);
	
	// Query stock by id
	public Stock queryById(Long id);
}
