package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxc.mall.api.supplier.ISupplierService;
import org.lxc.mall.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/supplier")
public class SupplierController {
	
	@Autowired
	ISupplierService supplierService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
    public Map<String,Object> getSuppliers() throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		List<Supplier> suppliers = supplierService.queryAll();
		resultMap.put("suppliers", suppliers);
		return resultMap;
	} 

}
