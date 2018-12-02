package org.lxc.mall.sys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.core.exception.SimpleException;
import org.lxc.mall.model.common.RespDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 异常统一处理解析器
 * <b>Spring boot中有个默认Controller{@code BasicErrorController}</b>中会处理/error uri
 * 的异常，并且含有@ResponseBody注解，从而调用自定义的@ControllerAdvice方法导致返回体格式与预期不一致
 * 因此在此{@linkplain resolveException}中的返回视图给一个空的避免调用BasicErrorController
 * @author hasee
 * @since 2018-06-03
 */
@Component
public class BusinessHanlerExceptionResolver implements HandlerExceptionResolver{

	private final String SYS_ERROR_CODE = "sys-999";
	
	private final String SYS_ERROR_DESC = "System unknown error";
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		RespDTO<SimpleException> rst = new RespDTO<>();
		rst.errorResult();
		SimpleException spe = null;
		ex.printStackTrace();
		if(ex instanceof ProcessException) {
			spe = assembleSimpleException((ProcessException)ex);
			rst.setErrDesc("业务异常");
		}else {
			spe = new SimpleException(SYS_ERROR_CODE,SYS_ERROR_DESC);
			rst.setErrDesc("系统异常");
		}
		rst.setData(spe);
		String jsonResp = JSON.toJSONString(rst);
		try {
			response.reset();
			response.setStatus(HttpStatus.OK.value());
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
	        response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(jsonResp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView();
	}

	
	private SimpleException assembleSimpleException(ProcessException pe) {
		return new SimpleException(pe.getErrorCode(),pe.getErrorDesc());
	}
}
