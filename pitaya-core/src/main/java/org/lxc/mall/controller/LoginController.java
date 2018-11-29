package org.lxc.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.lxc.mall.model.request.LoginRequest;
import org.lxc.mall.sys.annotation.Permission;
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
	
	@Permission(value="admin.login")
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> login(@RequestBody LoginRequest query){
		Map<String,Object> result = new HashMap<>();
		String accessToken = userAuthorizationRealm.authenticate(query);
		result.put("accessToken", accessToken);
		return result;
	}
	
}
