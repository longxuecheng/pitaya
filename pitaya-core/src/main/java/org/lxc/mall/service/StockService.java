package org.lxc.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.StockMapper;
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
	public int batchEdit(List<StockWriteCondition> stocks) throws Exception {
		try {
			for (StockWriteCondition s : stocks) {
				Stock dbStock = s.parseModel();
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
}
