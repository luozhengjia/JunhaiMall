/*
 * Copyright 2010 Mttang.com All right reserved. This software is the
 * confidential and proprietary information of Mttang.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Mttang.com.
 */
package com.ejunhai.junhaimall.order;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.order.service.IOrderReplService;

/**
 * 优惠券
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestOrderRepl extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IOrderReplService orderReplService;
	
	@Resource
	private IOrderMainService orderMainService;

	@Test
	@Rollback(false)
	public void createOrderRepl(){
		OrderMain orderMain=orderMainService.getOrdermainByOrderMainNo("D301241023712");
		try {
			//orderReplService.createOrderRepl("一只死蟹", orderMain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
