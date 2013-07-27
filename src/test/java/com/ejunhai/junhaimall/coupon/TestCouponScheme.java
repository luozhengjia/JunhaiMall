/*
 * Copyright 2010 Mttang.com All right reserved. This software is the
 * confidential and proprietary information of Mttang.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Mttang.com.
 */
package com.ejunhai.junhaimall.coupon;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;

/**
 * 优惠券模板
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestCouponScheme extends AbstractTransactionalJUnit4SpringContextTests  {
	@Resource
	private ICouponSchemeService couponSchemeService;
	
	@Test
	public void insertCouponScheme() throws ParseException {
		CouponScheme couponScheme = new CouponScheme();
		couponScheme.setCouponName("388元大闸蟹礼品券");
		couponScheme.setParValue(388);
		couponScheme.setDayLimitNum(1000);
		couponScheme.setIssueAmount(10000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		couponScheme.setUseStartdate(simpleDateFormat.parse("2012-06-24 12:00"));
		couponScheme.setUseEnddate(simpleDateFormat.parse("2012-07-24 12:00"));
		couponScheme.setRemark("公蟹3只，母蟹子3只");
		couponScheme = couponSchemeService.insertCouponScheme(couponScheme);
		Assert.assertNotNull(couponScheme.getId());
	}
	
}
