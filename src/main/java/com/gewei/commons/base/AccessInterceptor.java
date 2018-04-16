package com.gewei.commons.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: TODO 解决前端访问post请求跨域问题
 * @author: Tiger
 * @date: 2018年3月23日 上午9:22:43
 */
public class AccessInterceptor implements HandlerInterceptor {
	/**
	 * 访问控制器前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 设置：Access-Control-Allow-Origin头，处理Session问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("P3P", "CP=CAO PSA OUR");
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
			response.addHeader("Access-Control-Max-Age", "120");
		}
		// 放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}
