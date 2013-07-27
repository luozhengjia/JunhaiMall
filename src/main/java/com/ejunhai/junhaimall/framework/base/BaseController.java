package com.ejunhai.junhaimall.framework.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * Base Controller
 * 
 * @author 罗正加
 * 
 * @history 2011-12-13
 */
public class BaseController {
	
	protected final Logger logger =Logger.getLogger(this.getClass());

	/**
	 * 设置表单日期属性转换器
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 绑定验证信息
	 * 
	 * @param view
	 * @param result
	 */
	protected void BinderValidaDate(ModelAndView view, BindingResult... results) {
		for (BindingResult result : results) {
			List<FieldError> errList = result.getFieldErrors();
			view.getModel().put("validatorErrorList", errList);
		}
	}

	/**
	 * 绑定验证信息
	 * 
	 * @param view
	 * @param result
	 */
	protected void BinderValidaDate(ModelAndView view, String... results) {

		@SuppressWarnings("unchecked")
		List<FieldError> errList = (List<FieldError>) view.getModel().get("validatorErrorList");
		if (errList == null) {
			errList = new ArrayList<FieldError>();
		}

		for (String result : results) {
			FieldError error = new FieldError("", "", result);
			errList.add(error);
		}

		view.getModel().put("validatorErrorList", errList);
	}

	/**
	 * 绑定验证信息
	 * 
	 * @param view
	 * @param result
	 */
	protected void BinderValidaDate(ModelAndView view, List<String> resultList) {
		@SuppressWarnings("unchecked")
		List<FieldError> errList = (List<FieldError>) view.getModel().get("validatorErrorList");
		if (errList == null) {
			errList = new ArrayList<FieldError>();
		}

		for (String result : resultList) {
			FieldError error = new FieldError("", "", result);
			errList.add(error);
		}

		view.getModel().put("validatorErrorList", errList);
	}

	protected HttpSession getSession(HttpServletRequest req) {
		return req.getSession();
	}

	// AJAX输出，返回null
	public String ajax(HttpServletResponse response, String content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJson(HttpServletResponse response, String jsonString) {
		return ajax(response, jsonString, "text/html");
	}

//	// 根据Map输出JSON，返回null
//	public String ajaxJson(HttpServletResponse response, Map<String, String> jsonMap) {
//		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
//		return ajax(response, jsonObject.toString(), "text/html");
//	}
}
