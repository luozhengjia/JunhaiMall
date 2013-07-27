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
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 优惠券
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestConfig extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IConfigService configService;

	@Test
	@Rollback(false)
	public void createConfig() throws Exception {
		Config config=new Config();
		config.setConfigKey("test_temp_1");
		config.setConfigValue("yew");
		config.setConfigName("测试配置项");
		config.setRemark("备注");
		configService.insertConfig(config);
	}
	
	@Test
	public void getConfig() throws Exception {
		Config config=configService.getConfigByKey("test_temp_1");
		Assert.assertNotNull(config);
		
		config=configService.readConfig(config.getId());
		Assert.assertNotNull(config);
	}
	
	@Test
	public void updateConfig() throws Exception {
		Config config=configService.getConfigByKey("test_temp_1");
		config.setConfigValue(config.getConfigValue()+"BBB…");
		configService.updateConfig(config);
	}
	
	
	@Test
	public void deleteConfig() throws Exception {
		Config config=configService.getConfigByKey("test_temp_1");
		configService.removeConfig(config.getId());
	}
}
