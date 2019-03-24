package org.lxc.mall.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.util.Strings;
import org.lxc.mall.api.goods.IGoodsService;
import org.lxc.mall.api.stock.IStockService;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.lxc.mall.model.request.StockWriteCondition;
import org.lxc.mall.model.response.GoodsPhoto_DTO;
import org.lxc.mall.model.response.Goods_DTO;
import org.lxc.mall.service.basic.TencentCOSService;
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
	
	@Autowired
	private TencentCOSService tencentCOSService;
	
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
    public Map<String, Object> AddGoods(@RequestBody GoodsWriteCondition query) throws Exception {
		query.validate();
		Map<String,Object> resultMap = new HashMap<>();
		Long goodsId = goodsService.add(query);
		resultMap.put("id", goodsId);
		return resultMap;
    }
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
    public Long EditGoods(@RequestBody GoodsWriteCondition query) throws Exception {
		query.validate();
		goodsService.update(query);
		return query.getId();
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
    
    /**
     * 
     * According to {@linkplain https://jpuri.github.io/react-draft-wysiwyg/#/docs}.
     * like { data: { link: <THE_URL>}} 
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/pictures/save",method=RequestMethod.POST)
   	@ResponseBody
    public List<GoodsPhoto_DTO> savePictures(HttpServletRequest request,@RequestParam MultipartFile[] file) throws Exception {
    	List<GoodsPhoto_DTO> gpDtos = new ArrayList<>();
    	for (MultipartFile mf : file) {
    		String fileName = mf.getOriginalFilename();
    		String[] nameArray = fileName.split("\\.");
    		String fileType = nameArray[1];
    		File tmpFile = File.createTempFile(nameArray[0], fileType);
    		mf.transferTo(tmpFile);
        	String fileURL = tencentCOSService.saveGoodsSource(fileName, tmpFile);
        	gpDtos.add(goodsService.savePictures(fileName, fileURL));
        	tmpFile.delete();
    	}
    	return gpDtos;
    }
	
    @RequestMapping(value="/picture/list",method=RequestMethod.GET,params= {"goodsId"})
   	@ResponseBody
    public Map<String, Object> getGoodsPictures(@RequestParam Long goodsId) throws Exception{
    	Map<String,Object> result = new HashMap<>();
    	List<GoodsPhoto_DTO> pictures = goodsService.getPictures(goodsId);
    	result.put("pictures", pictures);
    	return result;
    }
    
    @RequestMapping(value="/picture/delete",method=RequestMethod.POST,params= {"pictureId"})
   	@ResponseBody
    public void deleteGoodsPicture(@RequestParam Long pictureId) throws Exception {
    	goodsService.deletePicture(pictureId);
    }
    
    public static void main(String[] args) throws IOException {
    	String fileName = "dove";
    	String[] ss = fileName.split("\\.");
    	
	}
}
