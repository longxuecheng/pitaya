package org.lxc.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.api.sale.ISaleOrderService;
import org.lxc.mall.common.utils.time.TimeFormatter;
import org.lxc.mall.dao.SaleDetailMapper;
import org.lxc.mall.dao.SaleOrderMapper;
import org.lxc.mall.model.SaleDetail;
import org.lxc.mall.model.SaleOrder;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SaleQueryCondition;
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
	public SaleOrder queryById(Long id) {
		return saleOrderDao.selectByPrimaryKey(id);
	}

	@Override
	public List<SaleDetail> queryDetailsByOrderId(Long orderId) {
		return saleDetailDao.selectByOrderId(orderId);
	}
	
	private List<SaleOrder_DTO> buildSaleOrderDTOs(List<SaleOrder> orders) {
		if (orders == null && orders.isEmpty()) {
			return null;
		}
		List<SaleOrder_DTO> dtos = new ArrayList<>();
		for (SaleOrder order : orders) {
			SaleOrder_DTO dto = new SaleOrder_DTO();
			dto.setId(order.getId());
			dto.setOrderNo(order.getOrderNo());
			dto.setPayAmt(order.getPayAmt());
			dto.setOrderAmt(order.getOrderAmt());
			dto.setCreateTime(TimeFormatter.formatDefault(order.getCreateTime()));
			dto.setStatus(order.getStatus());
			dto.setReceiver(order.getReceiver());
			dto.setAddress(order.getAddress());
			dto.setPhoneNo(order.getPhoneNo());
			dtos.add(dto);
		}
		return dtos;
	}

}
