package org.lxc.mall.api.sale;

import java.util.List;

import org.lxc.mall.model.SaleDetail;
import org.lxc.mall.model.SaleOrder;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SaleQueryCondition;

public interface ISaleOrderService {
	
	public PaginationInfo<SaleOrder> queryByCondition(SaleQueryCondition query);
	
	public SaleOrder queryById(Long id);
	
	public List<SaleDetail> queryDetailsByOrderId(Long orderId);
}
