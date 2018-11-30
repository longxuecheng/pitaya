package org.lxc.mall.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxc.mall.api.goods.IGoodsService;
import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.lxc.mall.model.request.StockWriteCondition;
import org.lxc.mall.model.response.Goods_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/goods")
public class GoodsController {
	
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IStockService stockService;
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<Goods_DTO> getGoodsList(@RequestBody GoodsQueryCondition query){
		return goodsService.queryByCondition(query);
    }
	
	@RequestMapping(value="info",method=RequestMethod.GET,params= {"id"})
	@ResponseBody
    public Map<String,Object> getGoodsInfo(@RequestParam Long id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Goods_DTO goods = goodsService.queryById(id);
		resultMap.put("goods", goods);
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
	
	@RequestMapping(value="/stock/list",method=RequestMethod.GET,params= {"goodsId"})
	@ResponseBody
    public Map<String,Object> getStocksByGoodsId(@RequestParam Long goodsId){
    	Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("stocks", stockService.queryByGoodsId(goodsId));
		return resultMap;
    }
    
    @RequestMapping(value="/stock/edit",method=RequestMethod.POST)
	@ResponseBody
    public int editGoodsStocks(@RequestBody List<StockWriteCondition> query) throws Exception{
    	
		return stockService.batchEdit(query);
		
    }
    
    @RequestMapping(value="/pictures/save",method=RequestMethod.POST)
   	@ResponseBody
    public void savePictures(@RequestParam MultipartFile multipartFile,@RequestParam Long id) throws Exception{
    	File f = new File(String.format("/Users/lxc/test/goods/ID_%d/%s", id,multipartFile.getOriginalFilename()));
    	if (!f.getParentFile().exists()) {
    		f.getParentFile().mkdirs();
    	}
    	multipartFile.transferTo(f);
    }
	
}
