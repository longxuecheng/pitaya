package org.lxc.mall.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxc.mall.sys.annotation.Permission;
import org.lxc.mall.sys.auth.UserAuthorizationRealm;
import org.lxc.mall.sys.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor  {
	
	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private UserAuthorizationRealm userAuthorizationRealm;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getMethod() == HttpMethod.OPTIONS.name()) {
			return true;
		}
		String accessToken = getAccessToken(request);
		if (accessToken == null) {
			notAuthenticatedResponse(response);
			return false;
		}
		UserPrincipal principal = userAuthorizationRealm.getPrincipal(accessToken);
		if (principal == null) {
			notAuthenticatedResponse(response);
			return false;
		}
		if (!checkAuthorization(principal, handler)) {
			notAuthorizedResponse(response);
			return false;
		}
		return true;
	}
	
	/**
	 * Check whether administrator has permissions to access target resource
	 * @param handler
	 * @return
	 */
	private boolean checkAuthorization(UserPrincipal principal ,Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
			Permission pa = hm.getMethodAnnotation(Permission.class);
			if (pa == null)
				return true;
			System.out.println(String.format("当前需要权限为%s", pa.value()));
			if (principal.hasPermissions(pa.value())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Mark response status as unauthorized
	 * @param response
	 */
	private void notAuthorizedResponse(HttpServletResponse response) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
	
	private void notAuthenticatedResponse(HttpServletResponse response) {
		response.setStatus(HttpStatus.FORBIDDEN.value());
	}
	
	private String getAccessToken(HttpServletRequest request) {
		return request.getHeader(AUTHORIZATION);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
