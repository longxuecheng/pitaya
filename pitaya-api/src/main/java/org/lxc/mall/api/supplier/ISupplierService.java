package org.lxc.mall.api.supplier;

import java.util.List;

import org.lxc.mall.model.Supplier;
import org.lxc.mall.model.SupplierWarehouse;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SupplierWriteConditon;

public interface ISupplierService {
	
	void create(SupplierWriteConditon s) throws Exception;
	
	void update(SupplierWriteConditon s) throws Exception;
	
	List<Supplier> queryByIds(List<Long> ids);
	
	Supplier queryById(Long id);
	
	List<SupplierWarehouse> warehouses(Long supplierId);
	
	List<Supplier> queryAll();
	
	PaginationInfo<Supplier> queryWithPagination(PageSerialization page);
}
