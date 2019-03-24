package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxc.mall.api.supplier.ISupplierService;
import org.lxc.mall.model.Supplier;
import org.lxc.mall.model.SupplierWarehouse;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SupplierWriteConditon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/supplier")
public class SupplierController {
	
	@Autowired
	ISupplierService supplierService;
	
	@RequestMapping(value="all",method=RequestMethod.GET)
	@ResponseBody
    public Map<String,Object> getAllSuppliers() throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		List<Supplier> suppliers = supplierService.queryAll();
		resultMap.put("suppliers", suppliers);
		return resultMap;
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<Supplier> getSuppliers(@RequestBody PageSerialization page) throws Exception{
		PaginationInfo<Supplier> suppliers = supplierService.queryWithPagination(page);
		return suppliers;
	}
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
    public Map<String,Object> getSuppliers(Long id) throws Exception{
		Map<String,Object> rm = new HashMap<>();
		Supplier s = supplierService.queryById(id);
		List<SupplierWarehouse> swhs = supplierService.warehouses(id);
		rm.put("supplier", s);
		rm.put("warehouses", swhs);
		return rm;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
    public void addSupplier(@RequestBody SupplierWriteConditon supplier) throws Exception{
		supplierService.create(supplier);
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
    public void editSupplier(@RequestBody SupplierWriteConditon supplier) throws Exception{
		supplierService.update(supplier);
	}

}
