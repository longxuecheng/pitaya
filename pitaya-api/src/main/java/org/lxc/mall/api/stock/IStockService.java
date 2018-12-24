package org.lxc.mall.api.stock;

import java.util.List;
import java.util.Map;

import org.lxc.mall.model.Stock;
import org.lxc.mall.model.request.StockWriteCondition;
import org.lxc.mall.model.response.Stock_DTO;

public interface IStockService {
	
	// Query stocks for provided goods id
	public List<Stock_DTO> queryByGoodsId(Long goodsId);
	
	// Query stock by id
	public Stock queryById(Long id);
	
	public List<Stock> queryByIds(List<Long> ids);
	
	public Map<Long,Stock> queryMapByIds(List<Long> ids);
	
	public List<Stock_DTO> queryAll();
	
	public int batchEdit(List<StockWriteCondition> stocks) throws Exception;
	
}
