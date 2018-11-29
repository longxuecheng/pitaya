package org.lxc.mall.controller;

import org.lxc.mall.api.admin.IAdminService;
import org.lxc.mall.model.request.AdminWriteCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/manage/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
    public Long addAdmin(@RequestBody AdminWriteCondition query) throws Exception{
		return adminService.addAdmin(query);
	}
	
}
