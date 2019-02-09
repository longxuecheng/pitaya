package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.lxc.mall.api.userAddress.IUserAddressService;
import org.lxc.mall.model.request.UserAddressWriteCondition;
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
@RequestMapping("/manage/user")
public class UserAddressController {

	@Autowired
	private IUserAddressService userAddressService;
	
	
	@RequestMapping(value="address/list",method=RequestMethod.GET,params= {"userId"})
	@ResponseBody
    public Map<String,Object> getUserAddressList(@RequestParam Integer userId){
    	Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("address", userAddressService.queryListByUserId(userId));
		return resultMap;
    }
	
	@RequestMapping(value="address/edit",method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> EditUserAddress(@RequestBody UserAddressWriteCondition query) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		Integer id = 0;
		if(query.getId() != null && query.getId() != 0 ) {
			id = userAddressService.update(query);
		}else {
			id = userAddressService.add(query);
		}
		resultMap.put("id", id);
		return resultMap;
		
    }
}
