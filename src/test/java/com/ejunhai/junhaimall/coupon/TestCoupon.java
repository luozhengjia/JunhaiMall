/*
 * Copyright 2010 Mttang.com All right reserved. This software is the
 * confidential and proprietary information of Mttang.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Mttang.com.
 */
package com.ejunhai.junhaimall.coupon;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;

/**
 * 优惠券
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestCoupon extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private ICouponService couponService;

	@Resource
	private ICouponSchemeService couponSchemeService;

	@Test
	@Rollback(false)
	public void generateCoupon() throws Exception {
		couponService.batchGenerateCoupon("c6100c5ad6b24e10b42e139717af9af6", 2);
	}

}
