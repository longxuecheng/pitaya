package org.lxc.mall.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private PaymentService paymentService;
	
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
	
	private void setSaleOrder(SaleWriteCondition condition,SaleOrder order) {
		order.setSupplierId(condition.getSupplierId());
		order.setReceiver(condition.getReceiver());
		order.setAddress(condition.getAddress());
		order.setPhoneNo(condition.getPhoneNo());
		order.setExpressMethod(condition.getExpressMethod());
		order.setExpressOrderNo(condition.getExpressOrderNo());
		order.setExpressFee(condition.getExpressFee());
		order.setStatus(condition.getStatus());
		order.setUserId(0l);
	}
	
	private SaleDetail installSaleDetail(Stock stock,Long id ,BigDecimal quantity) {
		SaleDetail sd = new SaleDetail();
		sd.setId(id);
		sd.setCostUnitPrice(stock.getCostUnitPrice());
		sd.setSaleUnitPrice(stock.getSaleUnitPrice());
		sd.setGoodsId(stock.getGoodsId());
		sd.setStockId(stock.getId());
		sd.setGoodsName(stock.getName());
		sd.setQuantity(quantity);
		return sd;
	}
	
	protected void addSaleDetails(Long orderId,List<SaleDetail> saleDetails) throws Exception {
		try {
			saleDetails.stream().forEach((saleDetail) -> {
				saleDetail.setOrderId(orderId);
				saleDetailDao.insertSelective(saleDetail);
			});
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增订单明细异常");
		}
	}
	
	@Override
	public Long add(SaleWriteCondition condition) throws Exception {
		SaleOrder so = new SaleOrder();
		setSaleOrder(condition,so);
		Map<Long, Stock> stockMap = stockService.queryMapByIds(condition.getStockIds());
		List<SaleDetail> sds = new ArrayList<>(condition.getDetails().size());
		BigDecimal orderAmt = BigDecimal.ZERO;
		BigDecimal goodsAmt = BigDecimal.ZERO;
		for (SaleDetailWriteCondition sdwc : condition.getDetails()) {
			Stock stock = stockMap.get(sdwc.getStockId());
			if (stock != null) {
				sds.add(installSaleDetail(stock, null, sdwc.getQuantity()));
				goodsAmt = goodsAmt.add(stock.getSaleUnitPrice().multiply(sdwc.getQuantity()));
			}
		}
		orderAmt = goodsAmt.add(condition.getExpressFee());
		so.setOrderAmt(orderAmt);
		so.setGoodsAmt(goodsAmt);
		try {
			saleOrderDao.insertSelective(so);
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增销售订单异常");
		}
		addSaleDetails(so.getId(),sds);
		return so.getId();
	}

	@Override
	public Long edit(SaleWriteCondition condition) throws Exception {
		Map<Long, Stock> stockMap = stockService.queryMapByIds(condition.getStockIds());
		List<SaleDetail> details2Add = new ArrayList<>();
		List<SaleDetail> details2Edit = new ArrayList<>();
		List<Long> detailsIds2Edit = new ArrayList<>();
		BigDecimal orderAmt = BigDecimal.ZERO;
		BigDecimal goodsAmt = BigDecimal.ZERO;
		for (SaleDetailWriteCondition sdwc : condition.getDetails()) {
			// 加总商品金额
			Stock stock = stockMap.get(sdwc.getStockId());
			if (stock != null) {
				goodsAmt = goodsAmt.add(stock.getSaleUnitPrice().multiply(sdwc.getQuantity()));
			}
			// 筛选待更新的订单明细
			if (sdwc.getId() != null && sdwc.getId() > 0) {
				detailsIds2Edit.add(sdwc.getId());
				details2Edit.add(installSaleDetail(stock,sdwc.getId(),sdwc.getQuantity()));
			}
			// 筛选待新增的订单明细
			if (sdwc.getId() == null || sdwc.getId() == 0) {
				if (stock != null) {
					details2Add.add(installSaleDetail(stock,null,sdwc.getQuantity()));
				}
			}
			
		}
		orderAmt = goodsAmt.add(condition.getExpressFee());
		
		try {
			SaleOrder so = saleOrderDao.selectByPrimaryKey(condition.getId());
			setSaleOrder(condition, so);
			so.setOrderAmt(orderAmt);
			so.setGoodsAmt(goodsAmt);
			so.setUpdateTime(new Date());
			saleOrderDao.updateByPrimaryKeySelective(so);
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("更新销售订单异常");
		}
		List<SaleDetail> originDetails2Edit = saleDetailDao.selectByIds(detailsIds2Edit);
		try {
			originDetails2Edit.stream().forEach((detail) -> {
				for (SaleDetail detal2Edit : details2Edit) {
					if (detal2Edit.getId().equals(detail.getId())) {
						detail.setCostUnitPrice(detal2Edit.getCostUnitPrice());
						detail.setSaleUnitPrice(detal2Edit.getSaleUnitPrice());
						detail.setGoodsId(detal2Edit.getGoodsId());
						detail.setStockId(detal2Edit.getId());
						detail.setGoodsName(detal2Edit.getGoodsName());
						detail.setQuantity(detal2Edit.getQuantity());
						break;
					}
				}
				saleDetailDao.updateByPrimaryKey(detail);
			});
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("更新订单明细异常");
		}
		
		return condition.getId();
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

	@Override
	public void pay(Long id) throws Exception {
		SaleOrder order = saleOrderDao.selectByPrimaryKey(id);
		if (!order.payable()) {
			ProcessException.throwExeptionByFormat("Now order is not payable");
		}
		paymentService.payOffline(order.getOrderAmt(), order.getOrderNo(), order.getId());
		order.setStatus(SaleOrder.OrderStatus.PAYED.code());
		order.update();
		saleOrderDao.updateByPrimaryKeySelective(order);
	}
}
