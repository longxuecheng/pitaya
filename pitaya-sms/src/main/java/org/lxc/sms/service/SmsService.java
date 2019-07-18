package org.lxc.sms.service;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.model.request.SmsgRequest;
import org.lxc.sms.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

@Service
public class SmsService {

	@Autowired
	private SmsConfig smsConfig;
	
	/**
	 * Send short message with template to single point
	 */
	public void sendTemplateSmsgToSinglePhone(SmsgRequest req) {
		try {
//			String smsSign = null;
//		    SmsSingleSender ssender = new SmsSingleSender(smsConfig.getAppId(), smsConfig.getAppKey());
//		    SmsSingleSenderResult result = ssender.sendWithParam("86", req.getPhoneNos()[0],
//		        req.getTemplateId(), req.getParams(), smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//		    System.out.println(result);

			System.out.println("[sendTemplateSmsgToSinglePhone]");
			Thread.sleep(1000 * 60);
		} catch (Exception e) {
			throw new ProcessException("Send short message erorr");
		} 
	}
}
