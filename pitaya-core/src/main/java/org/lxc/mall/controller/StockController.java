package org.lxc.mall.controller;

import java.util.List;

import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.model.Stock;
import org.lxc.mall.model.response.Stock_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/stock")
public class StockController {
	
	@Autowired
	private IStockService stockService;
	
	@RequestMapping(value="info",method=RequestMethod.GET,params= {"id"})
	@ResponseBody
    public Stock getStockInfo(@RequestParam Long id){
		return stockService.queryById(id);
    }
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public List<Stock_DTO> getStocks(){
		return stockService.queryAll();
    }
	
	
}
