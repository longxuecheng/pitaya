package org.lxc.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.lxc.mall.model.request.SmsgRequest;
import org.lxc.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sms")
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private StoreIntegration storeIntegration;
	
	@RequestMapping(value="send",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> sendSmsgToSinglePhone(@RequestBody SmsgRequest req) {
		smsService.sendTemplateSmsgToSinglePhone(req);
		Map<String,Object> map = new HashMap<>();
		map.put("name", "hello kitty");
        return map;
    }
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	@ResponseBody
    public Map<String,Object> testHystrix() {
		Map<String,Object> map = new HashMap<>();
		map.put("name",storeIntegration.getStores());
        return map;
    }
	
}
