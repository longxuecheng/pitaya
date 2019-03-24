package org.lxc.mall.service;

import java.util.Date;
import java.util.List;

import org.lxc.mall.api.supplier.ISupplierService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.SupplierMapper;
import org.lxc.mall.dao.SupplierWarehouseMapper;
import org.lxc.mall.model.Supplier;
import org.lxc.mall.model.SupplierWarehouse;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SupplierWriteConditon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class SupplierService implements ISupplierService {
	
	@Autowired
	private SupplierMapper supplierDao;
	
	@Autowired
	private SupplierWarehouseMapper supplierWarehouseDao;
	
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

	@Override
	public PaginationInfo<Supplier> queryWithPagination(PageSerialization pagination) {
		PaginationInfo<Supplier> page = new PaginationInfo<>();
		Page<Supplier> p = PageHelper.startPage(pagination.pageNo, pagination.pageSize, true);
		List<Supplier> suppliers = supplierDao.selectAll();
		page.setItems(suppliers);
		page.setTotal(p.getTotal());
		page.setPageNo(pagination.pageNo);
		page.setPageSize(pagination.pageSize);
		return page;
	}

	@Override
	public List<SupplierWarehouse> warehouses(Long supplierId) {
		return supplierWarehouseDao.selectBySupplier(supplierId);
	}

	@Override
	public void create(SupplierWriteConditon c) throws Exception {
		Supplier s = parseSupplier(c);
		supplierDao.insert(s);
		c.setId(s.getId());
		createOrUpdateWarehouse(c);
	}
	
	@Override
	public void update(SupplierWriteConditon c) throws Exception {
		Supplier s = parseSupplier(c);
		supplierDao.updateByPrimaryKeySelective(s);
		createOrUpdateWarehouse(c);
	}
	
	private Supplier parseSupplier(SupplierWriteConditon c) {
		Supplier s = new Supplier();
		s.setId(c.getId());
		s.setName(c.getName());
		s.setAddress(c.getAddress());
		s.setContactName(c.getContactName());
		s.setEmail(c.getEmail());
		s.setLicense(c.getLicense());
		s.setPhoneNo(c.getPhoneNo());
		s.setTel(c.getTel());
		s.setRemark(c.getRemark());
		return s;
	}
	
	private void createOrUpdateWarehouse(SupplierWriteConditon s) throws Exception {
		if (s.getWarehouses() == null || s.getWarehouses().isEmpty()) {
			ProcessException.throwExeptionByFormat("请设置仓库");
		}
		try {
			s.getWarehouses().stream().forEach((item) -> {
				SupplierWarehouse swh = new SupplierWarehouse();
				swh.setSupplierId(s.getId());
				swh.setName(item.getName());
				swh.setCreateTime(new Date());
				if (item.getId() != null && item.getId() > 0) {
					swh.setId(item.getId());
					supplierWarehouseDao.updateByPrimaryKeySelective(swh);
				}else {
					supplierWarehouseDao.insertSelective(swh);
				}
				
			});
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增仓库异常");
		}
		
	}

}
