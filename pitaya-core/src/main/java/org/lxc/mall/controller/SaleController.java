package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxc.mall.api.sale.ISaleOrderService;
import org.lxc.mall.model.SaleDetail;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.SaleQueryCondition;
import org.lxc.mall.model.request.SaleWriteCondition;
import org.lxc.mall.model.response.SaleOrder_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/sale")
public class SaleController {
	
	@Autowired
	private ISaleOrderService saleOrderService;
	
//	@Permission(value="manage.sale.order.query")
	@RequestMapping(value="order/list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<SaleOrder_DTO> getSaleList(@RequestBody SaleQueryCondition query){
		return saleOrderService.queryByCondition(query);
    }
	
//	@Permission(value="manage.sale.order.query")
	@RequestMapping(value="order/info",method=RequestMethod.GET,params= {"id"})
	@ResponseBody
    public Map<String,Object> getSaleInfo(@RequestParam Long id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		SaleOrder_DTO order = saleOrderService.queryById(id);
		List<SaleDetail> items = saleOrderService.queryDetailsByOrderId(id);
		resultMap.put("order", order);
		resultMap.put("items", items);
		return resultMap;
	}
	
	@RequestMapping(value="order/add",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> addSaleOrder(@RequestBody SaleWriteCondition condition) throws Exception{
		Map<String,Object> dataMap = new HashMap<>();
		Long id = saleOrderService.add(condition);
		dataMap.put("id", id);
		return dataMap;
	}
	
	@RequestMapping(value="order/edit",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> editSaleOrder(@RequestBody SaleWriteCondition condition) throws Exception{
		Map<String,Object> dataMap = new HashMap<>();
		Long id = saleOrderService.edit(condition);
		dataMap.put("id", id);
		return dataMap;
	}
	
	@RequestMapping(value="order/pay",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> pay(@RequestBody Map<String,Long> id) throws Exception{
		Map<String,Object> dataMap = new HashMap<>();
		saleOrderService.pay(id.get("id"));
		dataMap.put("id", id);
		return dataMap;
	}
	
}
