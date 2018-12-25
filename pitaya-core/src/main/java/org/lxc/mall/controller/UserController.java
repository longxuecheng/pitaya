package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.lxc.mall.api.user.IUserService;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.UserQueryCondition;
import org.lxc.mall.model.request.UserWriteCondition;
import org.lxc.mall.model.response.User_DTO;
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
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
    public PaginationInfo<User_DTO> getUserList(@RequestBody UserQueryCondition query){
		return userService.queryByCondition(query);
    }
    
    @RequestMapping(value="info",method=RequestMethod.GET,params= {"id"})
	@ResponseBody
    public Map<String,Object> getUserInfo(@RequestParam Integer id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		User_DTO user = userService.queryById(id);
		resultMap.put("user", user);
		return resultMap;
    }
    
    @RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> AddUser(@RequestBody UserWriteCondition query) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		Integer userId = userService.add(query);
		resultMap.put("id", userId);
		return resultMap;
    }
    
    @RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
    public Integer EditUser(@RequestBody UserWriteCondition query) throws Exception {
    	userService.update(query);
		return query.getId();
    }
	
}
