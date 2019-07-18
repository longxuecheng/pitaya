package org.lxc.mall.sys;

import org.lxc.mall.config.ControlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@SuppressWarnings("deprecation")
@Configuration
public class CorsMVCConfiguration extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ControlConfig controlConfig;
	
	@Autowired
	private HandlerInterceptor AuthorizationInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("*")
		.allowedOrigins("http://localhost:3000","http://localhost:8080",
				"https://www.geluxiya.com").allowedHeaders("*");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if (controlConfig.isUseAuth()) {
			registry.addInterceptor(AuthorizationInterceptor)
			.addPathPatterns("/manage/**")
			.excludePathPatterns("/manage/login");
		}
	}

	
}
