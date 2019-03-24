package org.lxc.mall.controller;

import org.lxc.mall.api.attribute.IAttributeService;
import org.lxc.mall.model.Attribute;
import org.lxc.mall.model.AttributeCategory;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.AttributeQueryCondition;
import org.lxc.mall.model.request.AttributeWriteCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/attribute")
public class AttributeController {

	@Autowired
	private IAttributeService attributeService;
	
	@RequestMapping(value="category/list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<AttributeCategory> getAttributeCategories(@RequestBody PageSerialization pagination){
		return attributeService.getCategoriesByPagination(pagination);
    }
   
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<Attribute> getAttributesByCategory(@RequestBody AttributeQueryCondition query){
		return attributeService.getAttributesByPagination(query);
    }

	@RequestMapping(value="create",method=RequestMethod.POST)
	@ResponseBody
    public void creagteSpecification(@RequestBody AttributeWriteCondition params){
		attributeService.createAttribute(params);
    }
   
//	@RequestMapping(value="update",method=RequestMethod.POST)
//	@ResponseBody
//    public void updateSpecification(@RequestBody Specification spec){
//		specificationService.updateSpecification(spec);
//    }
   
	
}
