package org.lxc.mall.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.api.sale.ISaleOrderService;
import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.common.utils.time.TimeFormatter;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.SaleDetailMapper;
import org.lxc.mall.dao.SaleOrderMapper;
import org.lxc.mall.model.SaleDetail;
import org.lxc.mall.model.SaleOrder;
import org.lxc.mall.model.Stock;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SaleDetailWriteCondition;
import org.lxc.mall.model.request.SaleQueryCondition;
import org.lxc.mall.model.request.SaleWriteCondition;
import org.lxc.mall.model.response.SaleOrder_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class SaleService implements ISaleOrderService{
	
	@Autowired
	private SaleOrderMapper saleOrderDao;
	
	@Autowired
	private SaleDetailMapper saleDetailDao;
	
	@Autowired
	private IStockService stockService;
	
	private List<SaleOrder_DTO> buildSaleOrderDTOs(List<SaleOrder> orders) {
		if (orders == null || orders.isEmpty()) {
			return null;
		}
		List<SaleOrder_DTO> dtos = new ArrayList<>();
		for (SaleOrder order : orders) {
			dtos.add(installSaleOrderDTO(order));
		}
		return dtos;
	}
	
	private SaleOrder_DTO installSaleOrderDTO(SaleOrder order) {
		if (order == null) {
			return null;
		}
		SaleOrder_DTO dto = new SaleOrder_DTO();
		// basic information
		dto.setId(order.getId());
		dto.setOrderNo(order.getOrderNo());
		dto.setOrderAmt(order.getOrderAmt());
		dto.setGoodsAmt(order.getGoodsAmt());
		dto.setExpressFee(order.getExpressFee());
		dto.setCreateTime(TimeFormatter.formatDefault(order.getCreateTime()));
		dto.setStatus(order.getStatus());
		// express info
		dto.setExpressMethod(order.getExpressMethod());
		dto.setExpressOrderNo(order.getExpressOrderNo());
		dto.setEstimatedArrivalDate(TimeFormatter.formatDefault(order.getEstimatedArrivalDate()));
		dto.setReceiver(order.getReceiver());
		dto.setAddress(order.getAddress());
		dto.setPhoneNo(order.getPhoneNo());
		return dto;
	}
	
	private SaleOrder installSaleOrder(SaleWriteCondition condition) {
		SaleOrder order = new SaleOrder();
		order.setSupplierId(condition.getSupplierId());
		order.setReceiver(condition.getReceiver());
		order.setAddress(condition.getAddress());
		order.setPhoneNo(condition.getPhoneNo());
		order.setExpressMethod(condition.getExpressMethod());
		order.setExpressOrderNo(condition.getExpressOrderNo());
		order.setOrderAmt(condition.getOrderAmt());
		order.setGoodsAmt(condition.getGoodsAmt());
		order.setExpressFee(condition.getExpressFee());
		order.setUserId(0l);
		return order;
	}
	
	@Override
	public Long add(SaleWriteCondition condition) throws Exception {
		SaleOrder so = installSaleOrder(condition);
		List<Stock> stocks = stockService.queryByIds(condition.getStockIds());
		List<SaleDetail> sds = new ArrayList<>(condition.getDetails().size());
		BigDecimal orderAmt = BigDecimal.ZERO;
		BigDecimal goodsAmt = BigDecimal.ZERO;
		for (SaleDetailWriteCondition sdwc : condition.getDetails()) {
			for (Stock stock : stocks) {
				if (stock.getId().equals(sdwc.getStockId())) {
					SaleDetail sd = new SaleDetail();
					sd.setCostUnitPrice(stock.getCostUnitPrice());
					sd.setSaleUnitPrice(stock.getSaleUnitPrice());
					sd.setGoodsId(stock.getGoodsId());
					sd.setStockId(stock.getId());
					sd.setGoodsName(stock.getName());
					sd.setGoodsQuantity(sdwc.getGoodsQuantity());
					sds.add(sd);
					goodsAmt = goodsAmt.add(stock.getSaleUnitPrice().multiply(sdwc.getGoodsQuantity()));
					break;
				}
			}
		}
		orderAmt = orderAmt.add(goodsAmt).add(condition.getExpressFee());
		try {
			saleOrderDao.insertSelective(so);
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增销售订单异常");
		}
		try {
			sds.stream().forEach((saleDetail) -> {
				saleDetail.setOrderId(so.getId());
				saleDetailDao.insertSelective(saleDetail);
			});
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增订单明细异常");
		}
		return so.getId();
	}

	@Override
	public PaginationInfo<SaleOrder_DTO> queryByCondition(SaleQueryCondition query) {
		PaginationInfo<SaleOrder_DTO> page = new PaginationInfo<>();
		Page<SaleOrder> p = PageHelper.startPage(query.pageNo, query.pageSize, true);
		List<SaleOrder> orders = saleOrderDao.selectAll();
		page.setItems(buildSaleOrderDTOs(orders));
		page.setTotal(p.getTotal());
		page.setPageNo(query.pageNo);
		page.setPageSize(query.pageSize);
		return page;
	}

	@Override
	public SaleOrder_DTO queryById(Long id) {
		SaleOrder order = saleOrderDao.selectByPrimaryKey(id);
		return installSaleOrderDTO(order);
	}

	@Override
	public List<SaleDetail> queryDetailsByOrderId(Long orderId) {
		return saleDetailDao.selectByOrderId(orderId);
	}
	
}
