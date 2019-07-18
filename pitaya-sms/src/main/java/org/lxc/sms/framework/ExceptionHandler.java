package org.lxc.sms.framework;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxc.mall.core.exception.ErrorCode;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.core.exception.SimpleException;
import org.lxc.mall.model.common.RespDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ExceptionHandler implements HandlerExceptionResolver{
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		RespDTO<SimpleException> rst = new RespDTO<>();
		rst.errorResult();
		logger.error("异常捕获:{}", ex.getMessage());
		ModelAndView mav = new ModelAndView();
		if(ex instanceof ProcessException) {
			ProcessException pe = (ProcessException)ex;
			rst.setErrDesc(ex.getMessage());
			if (ErrorCode.UNAUTHENTICATED.equals(pe.getErrorCode())) {
				writeJsonResponse(response,HttpStatus.FORBIDDEN,JSON.toJSONString(rst));
				return mav;
			}
			if (ErrorCode.UNAUTHORIZED.equals(pe.getErrorCode())) {
				writeJsonResponse(response,HttpStatus.UNAUTHORIZED,JSON.toJSONString(rst));
				return mav;
			}
			writeJsonResponse(response,HttpStatus.OK,JSON.toJSONString(rst));
		}else {
			rst.setStatus(ErrorCode.UNKNOWN);
			rst.setErrDesc("System unknown error");
			writeJsonResponse(response,HttpStatus.INTERNAL_SERVER_ERROR,JSON.toJSONString(rst));
		}
		return mav;
	}

	private void writeJsonResponse(HttpServletResponse response,HttpStatus status,String data) {
		try {
			response.reset();
			response.setStatus(status.value());
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
	        response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
