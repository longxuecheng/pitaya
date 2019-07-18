package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.lxc.mall.model.request.LoginRequest;
import org.lxc.mall.model.response.Token_DTO;
import org.lxc.mall.sys.auth.UserAuthorizationRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/manage")
public class LoginController {
	
	@Autowired
	private UserAuthorizationRealm userAuthorizationRealm;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> login(@RequestBody LoginRequest query){
		Map<String,Object> result = new HashMap<>();
		Token_DTO token = userAuthorizationRealm.authenticate(query);
		result.put("token", token);
		return result;
	}
	
}
