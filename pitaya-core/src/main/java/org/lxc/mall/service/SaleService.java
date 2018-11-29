package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.sale.ISaleOrderService;
import org.lxc.mall.dao.SaleDetailMapper;
import org.lxc.mall.dao.SaleOrderMapper;
import org.lxc.mall.model.SaleDetail;
import org.lxc.mall.model.SaleOrder;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SaleQueryCondition;
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
	public PaginationInfo<SaleOrder> queryByCondition(SaleQueryCondition query) {
		PaginationInfo<SaleOrder> page = new PaginationInfo<SaleOrder>();
		Page<SaleOrder> p = PageHelper.startPage(query.pageNo, query.pageSize, true);
		List<SaleOrder> orders = saleOrderDao.selectAll();
		page.setItems(orders);
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

}
