package org.lxc.mall.api.sale;

import java.util.List;

import org.lxc.mall.model.SaleDetail;
import org.lxc.mall.model.SaleOrder;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SaleQueryCondition;
import org.lxc.mall.model.response.SaleOrder_DTO;

public interface ISaleOrderService {
	
	public PaginationInfo<SaleOrder_DTO> queryByCondition(SaleQueryCondition query);
	
	public SaleOrder queryById(Long id);
	
	public List<SaleDetail> queryDetailsByOrderId(Long orderId);
}
