package org.lxc.mall.controller;

import java.util.List;

import org.lxc.mall.api.specification.ISpecificationService;
import org.lxc.mall.model.Specification;
import org.lxc.mall.model.common.PageSerialization;
import org.lxc.mall.model.common.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/specification")
public class SpecificationController {

	@Autowired
	private ISpecificationService specificationService;
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<Specification> getSpecifications(@RequestBody PageSerialization query){
		return specificationService.getByPagination(query);
    }
	
	@RequestMapping(value="all",method=RequestMethod.GET)
	@ResponseBody
    public List<Specification> getAllSpecifications(){
		return specificationService.getAll();
    }
   
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
    public Specification creagteSpecification(Integer id){
		return specificationService.specificationInfo(id);
    }
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@ResponseBody
    public void creagteSpecification(@RequestBody Specification spec){
		specificationService.createSpecification(spec);
    }
   
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
    public void updateSpecification(@RequestBody Specification spec){
		specificationService.updateSpecification(spec);
    }
   
	
}
