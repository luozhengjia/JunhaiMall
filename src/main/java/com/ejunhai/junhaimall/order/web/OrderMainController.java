package com.ejunhai.junhaimall.order.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.base.PageFinder;
import com.ejunhai.junhaimall.framework.base.Query;
import com.ejunhai.junhaimall.framework.util.FreeMarkerTemplateHelper;
import com.ejunhai.junhaimall.framework.util.Md5Encrypt;
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.service.IOrderLogService;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 订单管理controller
 * 
 * @author 罗正加
 * @history 2011-12-16 罗正加 新建
 */
@Controller
@RequestMapping("system/order")
public class OrderMainController extends BaseController {

	@Autowired
	private IOrderMainService orderMainService;

	@Autowired
	private ICouponService couponService;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@Autowired
	private IConfigService configService;

	@Autowired
	private IOrderLogService orderLogService;

	@RequestMapping("/orderMainList")
	public String orderMainList(OrderMain orderMain, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = orderMainService.queryOrderMainCount(orderMain);

		List<OrderMain> orderMainList = new ArrayList<OrderMain>(0);
		if (count > 0) {
			orderMainList = orderMainService.queryOrderMainList(orderMain, pageNo, pageSize);
		}
		PageFinder<OrderMain> pageFinder = new PageFinder<OrderMain>(pageNo, pageSize, count);
		pageFinder.setData(orderMainList);
		modelMap.addAttribute("pageFinder", pageFinder);

		modelMap.addAttribute("orderMain", orderMain);
		return "manager/order/orderMainList";
	}

	@RequestMapping("/toOrderMain")
	public String toOrderMain(String orderMainId, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(orderMainId)) {
			OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
			modelMap.addAttribute("orderMain", orderMain);
			Coupon coupon = couponService.getCouponByOrderNumber(orderMain.getOrderMainNo());
			modelMap.addAttribute("coupon", coupon);
			CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
			modelMap.addAttribute("couponScheme", couponScheme);

			// 查询物流信息
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderNo(orderMain.getOrderMainNo());
			modelMap.put("orderLogList", orderLogService.queryOrderLogList(orderLog, 0, Integer.MAX_VALUE));
			return "manager/order/editOrderMain";
		}
		return "manager/order/addOrderMain";
	}

	@RequestMapping("/createOrderMain")
	public ModelAndView createOrderMain(Coupon coupon, OrderMain orderMain, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(coupon.getCouponNumber()) || StringUtils.isBlank(coupon.getCouponPassword())) {
			throw new Exception("创建订单参数出错");
		}
		String couponPassword = Md5Encrypt.md5(coupon.getCouponPassword());
		String[] msg = this.couponService.checkCoupon(coupon.getCouponNumber(), couponPassword);
		if (msg[0].equals("0")) {
			coupon = couponService.getCouponByNo(coupon.getCouponNumber());
			orderMainService.createOrderMain(coupon, orderMain);
		}
		return new ModelAndView(new RedirectView("/system/order/orderMainList.sc?state=0"));
	}

	@RequestMapping("/toDeliverOrderMain")
	public String toDeliver(String orderMainId, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(orderMainId)) {
			OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
			modelMap.addAttribute("orderMain", orderMain);
			Coupon coupon = couponService.getCouponByOrderNumber(orderMain.getOrderMainNo());
			modelMap.addAttribute("coupon", coupon);
			CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
			modelMap.addAttribute("couponScheme", couponScheme);
		}
		return "manager/order/deliverOrderMain";
	}

	@RequestMapping("/changeConsigneeInfo")
	public ModelAndView changeConsigneeInfo(OrderMain orderMain, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMain.getAreaCode()) || StringUtils.isBlank(orderMain.getDetailAddress())) {
			throw new Exception("发货地址信息有错");
		}
		orderMainService.changeConsigneeInfo(orderMain);
		return new ModelAndView(new RedirectView("/system/order/orderMainList.sc?state=0"));
	}

	@RequestMapping("/deliverOrderMain")
	public ModelAndView deliverOrderMain(OrderMain orderMain, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMain.getExpressOrderNo()) || StringUtils.isBlank(orderMain.getMobilePhone())) {
			throw new Exception("发货信息有错");
		}
		orderMainService.deliverOrderMain(orderMain);
		return new ModelAndView(new RedirectView("/system/order/orderMainList.sc?state=0"));
	}

	@RequestMapping("/toPrintMainExpress")
	public String toPrintMainExpress(String orderMainId, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMainId)) {
			logger.error("需打印的订单号不能为空");
		}

		// 查询需要打印的快递单数据
		modelMap.put("orderMainIds", orderMainId);
		OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
		modelMap.put("orderMain", orderMain);

		Config config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_COMPANY);
		modelMap.put("deliveryCompany", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER);
		modelMap.put("deliverySender", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_PROCITYAREA);
		modelMap.put("deliveryProCityArea", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_DETAIADDRESS);
		modelMap.put("deliveryDetailAddress", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_TELEPHONE);
		modelMap.put("deliveryTelphone", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MOBILE_PHONE);
		modelMap.put("deliveryMobilePhone", config.getConfigValue());

		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CONTENT);
		modelMap.put("deliveryContent", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER_SIGN);
		modelMap.put("deliverySenderSign", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CUSTOMER_CODE);
		modelMap.put("deliveryCustomerCode", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_ORIGN_ADDRESS);
		modelMap.put("deliveryOrignAddress", config.getConfigValue());
		
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MONTHLY_PAYMENT);
        modelMap.put("monthlyPayment", config.getConfigValue());
		return "manager/order/printExpressTemplate";
	}

	@RequestMapping("/toBatchPrintMainExpress")
	public String toBatchPrintMainExpress(String orderMainIds,int state, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMainIds)) {
			logger.error("需打印的订单号不能为空");
		}

		// 查询需要打印的快递单数据
		String[] arrOrderMainId = orderMainIds.split(",");
		List<OrderMain> orderMainList = new ArrayList<OrderMain>(arrOrderMainId.length);
		for (String orderMainId : arrOrderMainId) {
			OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
			if (orderMain != null) {
				orderMainList.add(orderMain);
			}
		}
		modelMap.put("state", state);
		modelMap.put("orderMainIds", orderMainIds);
		modelMap.put("orderList", orderMainList);

		Config config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_COMPANY);
		modelMap.put("deliveryCompany", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER);
		modelMap.put("deliverySender", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_PROCITYAREA);
		modelMap.put("deliveryProCityArea", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_DETAIADDRESS);
		modelMap.put("deliveryDetailAddress", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_TELEPHONE);
		modelMap.put("deliveryTelphone", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MOBILE_PHONE);
		modelMap.put("deliveryMobilePhone", config.getConfigValue());

		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CONTENT);
		modelMap.put("deliveryContent", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER_SIGN);
		modelMap.put("deliverySenderSign", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CUSTOMER_CODE);
		modelMap.put("deliveryCustomerCode", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_ORIGN_ADDRESS);
		modelMap.put("deliveryOrignAddress", config.getConfigValue());
		
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MONTHLY_PAYMENT);
        modelMap.put("monthlyPayment", config.getConfigValue());
		return "manager/order/batchPrintExpressTemplate";
	}

	@RequestMapping("/printMainExpress")
	@ResponseBody
	public String printMainExpress(String orderMainIds, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMainIds)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "需打印的订单参数有误");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}
		this.orderMainService.printExpress(orderMainIds);
		modelMap.put("state", "0");
		return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
	}
}
