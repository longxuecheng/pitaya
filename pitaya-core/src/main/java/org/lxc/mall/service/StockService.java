package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.dao.StockMapper;
import org.lxc.mall.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService {

	@Autowired
	private StockMapper stockDao;
	
	@Override
	public List<Stock> queryByGoodsId(Long goodsId) {
		return stockDao.selectByGoodsId(goodsId);
	}

	@Override
	public Stock queryById(Long id) {
		return stockDao.selectByPrimaryKey(id);
	}

	
}
