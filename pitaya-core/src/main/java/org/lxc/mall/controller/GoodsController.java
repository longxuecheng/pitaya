package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxc.mall.api.goods.IGoodsService;
import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.Stock;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
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
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IStockService stockService;
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<Goods> getGoodsList(@RequestBody GoodsQueryCondition query){
		return goodsService.queryByCondition(query);
    }
	
	@RequestMapping(value="info",method=RequestMethod.GET,params= {"id"})
	@ResponseBody
    public Map<String,Object> getGoodsInfo(@RequestParam Long id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Goods goods = goodsService.queryById(id);
		List<Stock> stocks = stockService.queryByGoodsId(id);
		resultMap.put("goods", goods);
		resultMap.put("stocks", stocks);
		return resultMap;
    }
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
    public Long AddGoods(@RequestBody GoodsWriteCondition query){
		return goodsService.add(query);
    }
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
    public Long EditGoods(@RequestBody GoodsWriteCondition query){
		return goodsService.update(query);
    }
	
}
