package com.ejunhai.junhaimall.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public final class OrderUtil {

	/**
	 * 订单编号规则
	 * 
	 * @return
	 */
	public static synchronized String createOrderMainNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
		return "D" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(4);
	}
	
	/**
	 * 补货单规则
	 * @return
	 */
	public static synchronized String createOrderReplNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
		return "B" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(4);
	}

}
