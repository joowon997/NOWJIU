package com.nowjoo.nowjiu.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handle) throws IOException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// /post/list-view
		String uri = request.getRequestURI();
		
		// 로그인이 안된 상태에서 메모리스트, 메모 입력화면, .... 게시글과 관련된 페이지로 접근을 막고
		// 로그인 페이지로 이동
		if(userId == null) {
			// /post 로 시작하는 path로 요청
			if(uri.startsWith("/nowjiu/administrator") && !uri.equals("/nowjiu/administrator/login")) {
				// redirect  
				response.sendRedirect("/nowjiu/administrator/login");
				return false;
			}
			if(uri.startsWith("/nowjiu/order")) {
				// redirect  
				response.sendRedirect("/nowjiu/login");
				return false;
			}
			if(uri.startsWith("/nowjiu/cart")) {
				// redirect  
				response.sendRedirect("/nowjiu/login");
				return false;
			}
			
		} else {  // 로그인 된 상태 
			// 사용자와 관련된 페이지로 접근을 막고
			// 리스트 페이지로 이동
			if(uri.startsWith("/nowjiu/login")) {
				response.sendRedirect("/nowjiu/main");
				return false;
			}
		}
		
		return true;
		
	}
}
