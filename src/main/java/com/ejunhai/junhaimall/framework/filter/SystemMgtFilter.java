package com.ejunhai.junhaimall.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class SystemMgtFilter implements Filter {

	private String excludePatterns;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.excludePatterns = arg0.getInitParameter("excludePatterns");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
			ServletException {
		// 1.是否已排除过滤该请求
		String uri = ((HttpServletRequest) arg0).getRequestURI();
		if (excludePatterns.equalsIgnoreCase(uri)) {
			arg2.doFilter(arg0, arg1);
		} else {
			// 2.判断是否登录
			if (SystemSessionManager.get((HttpServletRequest) arg0) != null) {
				arg2.doFilter(arg0, arg1);
			} else {
				((HttpServletResponse) arg1).sendRedirect("/toLoginSystem.sc");
			}
		}
	}
	
	@Override
	public void destroy() {
	}
}
