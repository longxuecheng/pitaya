package org.lxc.mall.api.supplier;

import java.util.List;

import org.lxc.mall.model.Supplier;

public interface ISupplierService {

	List<Supplier> queryByIds(List<Long> ids);
	
	Supplier queryById(Long id);
	
	List<Supplier> queryAll();
}
