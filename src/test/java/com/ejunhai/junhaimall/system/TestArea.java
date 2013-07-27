/*
 * Copyright 2010 Mttang.com All right reserved. This software is the
 * confidential and proprietary information of Mttang.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Mttang.com.
 */
package com.ejunhai.junhaimall.system;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IAreaService;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 优惠券
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestArea extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IAreaService creaService;

	@Test
	@Rollback(false)
	public void readArea() throws Exception {
		try{
			creaService.getAreaByNo("root-18-3-21");
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();
		}
	}

}
