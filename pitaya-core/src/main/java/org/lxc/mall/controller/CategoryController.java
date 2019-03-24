package org.lxc.mall.controller;

import java.util.List;

import org.lxc.mall.api.category.ICategoryService;
import org.lxc.mall.model.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value="top",method=RequestMethod.GET)
	@ResponseBody
    public List<GoodsCategory> getCategoryList(){
		return categoryService.allTop();
    }
	
	@RequestMapping(value="children",method=RequestMethod.GET)
	@ResponseBody
    public List<GoodsCategory> getCategoryChildren(Integer parentId){
		return categoryService.children(parentId);
    }
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
    public GoodsCategory getCategoryInfo(Integer id){
		return categoryService.info(id);
    }
	
}
