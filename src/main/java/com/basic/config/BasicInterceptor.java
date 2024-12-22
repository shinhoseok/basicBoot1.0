package com.basic.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class BasicInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(BasicInterceptor.class);
	
	@Value("${server.servlet.context-path:/}")
	private String contextPath;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 요청 처리 전 로직
		log.debug("preHandle: " + request.getRequestURI());
		return true; // true를 반환하면 다음 인터셉터나 핸들러가 실행됨
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 요청 처리 후 로직
		if(modelAndView == null) {
			modelAndView = new ModelAndView();
		}
		log.debug("contextPath>>>>"+contextPath);
		modelAndView.addObject("contextPath", contextPath);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 요청 완료 후 로직
		log.debug("afterCompletion: " + request.getRequestURI());
	}
}
