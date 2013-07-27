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

/**
 * 优惠券
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestOrderMain extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IOrderMainService orderMainService;

	@Test
	@Rollback(false)
	public void createOrderMain(){
		OrderMain orderMain=new OrderMain();
		orderMain.setOrderDate("2012-06-25");
		orderMain.setConsignee("罗正加");
		orderMain.setProvinceCityArea("广东省深圳市南山区");
		orderMain.setDetailAddress("南油大厦B区23栋620");
		orderMain.setLogisticsCompany("顺丰快递");
		orderMain.setMobilePhone("18682481905");
		try {
			//orderMainService.createOrderMain("8599237343680",orderMain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
