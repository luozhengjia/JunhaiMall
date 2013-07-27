package com.ejunhai.junhaimall.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.framework.constant.Constant;
import com.ejunhai.junhaimall.framework.filter.SystemSessionManager;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.model.SystemMgtUser;
import com.ejunhai.junhaimall.system.service.IConfigService;

@Controller
@RequestMapping("")
public class SystemUserController {
	@Autowired
	private IConfigService configService;

	@RequestMapping("toLoginSystem")
	public ModelAndView toLogin(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("manager/login.html"));
	}

	@RequestMapping("loginSystem")
	public ModelAndView login(String username, String password, String validateCode, HttpServletRequest request) {
		String sValidateCode = (String) request.getSession().getAttribute(Constant.LOGIN_VALIDATE_IMAGE);
		Config config = configService.getConfigByKey(Constant.USER_LOGIN_PASSWORD);
		String userPassword = config == null ? "ejunhai" : config.getConfigValue();
		if ("ejunhai".equals(username) && userPassword.equals(password) && validateCode.equals(sValidateCode)) {
			SystemMgtUser sessionMgtUser = new SystemMgtUser();
			sessionMgtUser.setUsername(username);
			SystemSessionManager.set(sessionMgtUser, request);
			return new ModelAndView(new RedirectView("manager/index.html"));
		}
		return new ModelAndView(new RedirectView("manager/login.html"));
	}

	@RequestMapping("/systemError")
	public ModelAndView error(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("errorDesc", request.getAttribute("errorDesc"));
		return new ModelAndView("manager/error", modelMap);
	}

	/**
	 * 注销
	 */
	@RequestMapping("/manager/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		SystemSessionManager.clear(request);
		return new ModelAndView(new RedirectView("manager/login.ftl"));
	}
}
