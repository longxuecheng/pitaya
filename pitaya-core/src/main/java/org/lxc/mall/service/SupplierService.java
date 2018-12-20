package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.supplier.ISupplierService;
import org.lxc.mall.dao.SupplierMapper;
import org.lxc.mall.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupplierService implements ISupplierService {
	
	
	@Autowired
	private SupplierMapper supplierDao;
	
	@Override
	public List<Supplier> queryByIds(List<Long> ids) {
		return supplierDao.selectByPrimaryKeys(ids);
	}

	@Override
	public Supplier queryById(Long id) {
		return supplierDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Supplier> queryAll() {
		return supplierDao.selectAll();
	}

	
}
