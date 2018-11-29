package org.lxc.mall.controller;

import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private IStockService stockService;
	
	@RequestMapping(value="info",method=RequestMethod.GET,params= {"id"})
	@ResponseBody
    public Stock getStockInfo(@RequestParam Long id){
		return stockService.queryById(id);
    }
	
}
