package org.lxc.mall.service.hystrix;

/**
 * "HC" in HC_SmsSend is short for hystrix command with fall back
 * @author lxc
 *
 */
//@Component
public class HC_SmsSend {
	
//	@Autowired
//	RestTemplate restTemplate;
//	
//	private String smsUrl = "http://pitaya-sms/sms/send";
//	
//	@LoadBalanced
//	@Bean
//	public RestTemplate loadbalancedRestTemplate() {
//       return new RestTemplate();
//	} 
//	
//	
//	@SuppressWarnings("unchecked")
//	@HystrixCommand(
//			commandProperties = {
//	            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//		    },
//			commandKey="sendSmsg",groupKey="group-pitaya-sms",fallbackMethod="sendSmsgFallBack")
//	public RespDTO<Object> sendSmsg() {
//		try {
//			SmsgRequest r = new SmsgRequest();
//			r.setPhoneNos(new String[]{"18911792314"});
//			r.setParams(new String[]{"73127317231"});
//			r.setTemplateId(311741);
//			HttpHeaders headers = new HttpHeaders();
//			headers.set(HttpHeaders.CONTENT_TYPE,"application/json");
//			HttpEntity<SmsgRequest> entity = new HttpEntity<SmsgRequest>(r, headers);
//			return this.restTemplate.postForObject(smsUrl,entity , RespDTO.class);
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new ProcessException("call to service pitaya-sms error");
//		}
//	}
//	
//	RespDTO<Object> sendSmsgFallBack() {
//		RespDTO<Object> resp = new RespDTO<Object>();
//		resp.successResult();
//		resp.setErrDesc("send message erorr fall back");
//		return resp;
//	}
}

