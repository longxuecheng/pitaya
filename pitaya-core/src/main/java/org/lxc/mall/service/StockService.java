package org.lxc.mall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.GoodsMapper;
import org.lxc.mall.dao.StockMapper;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.Stock;
import org.lxc.mall.model.request.StockWriteCondition;
import org.lxc.mall.model.response.Stock_DTO;
import org.lxc.mall.model.response.TimeGroup_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StockService implements IStockService {

	@Autowired
	private StockMapper stockDao;
	
	@Autowired
	private GoodsMapper goodsDao;
	
	private List<Stock_DTO> buildStockDTOs(List<Stock> stocks) {
		if (stocks == null || stocks.isEmpty()) {
			return null;
		}
		List<Stock_DTO> dtos = new ArrayList<>();
		
		for (Stock s : stocks) {
			dtos.add(installStockDTO(s));
		}
		return dtos;
	}
	
	private Stock_DTO installStockDTO(Stock s) {
		Stock_DTO dto = new Stock_DTO();
		dto.setId(s.getId());
		dto.setWarehouseId(s.getWarehouseId());
		dto.setStatus(s.getStatus());
		dto.setTimeGroup(new TimeGroup_DTO(s.getCreatedTime(), s.getUpdatedTime(), s.getDeletedTime()));
		dto.setAdminId(s.getAdminId());
		dto.setAdminName(s.getAdminName());
		dto.setGoodsId(s.getGoodsId());
		dto.setName(s.getName());
		dto.setSpecification(s.getSpecification());
		dto.setShippingFee(s.getShippingFee());
		dto.setCostUnitPrice(s.getCostUnitPrice());
		dto.setSaleUnitPrice(s.getSaleUnitPrice());
		dto.setTotalQuantity(s.getTotalQuantity());
		dto.setAvailableQuantity(s.getAvailableQuantity());
		return dto;
	}
	
	@Override
	public List<Stock_DTO> queryByGoodsId(Long goodsId) {
		List<Stock> stocks = stockDao.selectByGoodsId(goodsId);
		return buildStockDTOs(stocks);
	}

	@Override
	public Stock queryById(Long id) {
		return stockDao.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Stock> queryByIds(List<Long> ids) {
		return stockDao.selectByIds(ids);
	}
	
	@Override
	public List<Stock_DTO> queryAll() {
		List<Stock> stocks = stockDao.selectAll();
		return buildStockDTOs(stocks);
	}

	@Override
	public int batchEdit(List<StockWriteCondition> stocks) throws Exception {
		try {
			Goods g = goodsDao.selectByPrimaryKey(stocks.get(0).getGoodsId());
			for (StockWriteCondition s : stocks) {
				StockCreator sc = new StockCreator(s,g.getSupplierId());
				Stock dbStock = sc.transfer();
				if (dbStock.getId() != null && dbStock.getId().longValue() != 0) {
					stockDao.updateByPrimaryKeySelective(dbStock);
				}else {
					stockDao.insertSelective(dbStock);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("编辑库存信息异常");
		}
		return 0;
	}

	@Override
	public Map<Long, Stock> queryMapByIds(List<Long> ids) {
		List<Stock> stocks = queryByIds(ids);
		if (stocks != null && !stocks.isEmpty()) {
			Map<Long, Stock>  stockMap = new HashMap<>();
			stocks.stream().forEach((stock) -> {
				stockMap.put(stock.getId(), stock);
			});
			return stockMap;
		}
		return null;
	}
	
	private static class StockCreator {

		private StockWriteCondition stock;
		
		private Long supplierId;

		public StockCreator(StockWriteCondition stock,Long supplierId) {
			this.stock = stock;
			this.supplierId = supplierId;
		}
		
		public Stock transfer() {
			Stock s = new Stock();
	    	s.setId(stock.getId());
	    	s.setSupplierId(supplierId);
	    	s.setGoodsId(stock.getGoodsId());
	    	s.setCostUnitPrice(stock.getCostUnitPrice());
	    	s.setSaleUnitPrice(stock.getSaleUnitPrice());
	    	s.setDiscount(stock.getDiscount());
	    	s.setName(stock.getName());
	    	s.setTotalQuantity(stock.getAvailableQuantity());
	    	s.setAvailableQuantity(stock.getAvailableQuantity());
	    	s.setSpecification(String.join("_", stock.getSpecifications()));
	    	s.setShippingFee(stock.getShippingFee());
	    	s.setIsRush(stock.getIsRush());
	    	s.setStatus(stock.getStatus());
	    	s.setWarehouseId(stock.getWarehouseId());
	    	return s;
			
		}
		
	}

}
