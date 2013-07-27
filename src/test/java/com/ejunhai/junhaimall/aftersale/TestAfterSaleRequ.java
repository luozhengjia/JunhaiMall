package com.ejunhai.junhaimall.aftersale;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleRequService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestAfterSaleRequ extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private IAfterSaleRequService afterSaleRequService;

	@Test
	@Rollback(false)
	public void insertAfterSaleRequ() throws ParseException {
		AfterSaleRequ afterSaleRequ = new AfterSaleRequ();
		afterSaleRequ.setDescription("尽快处理哦。。。");
		afterSaleRequ.setOrderMainNo("A2123456789");
		afterSaleRequ.setPic1Url("http://img.yougou.com/pics/zero/2012/99819620/99819620_01_s.jpg");
		afterSaleRequ.setPic2Url("http://img.yougou.com/pics/belle/2012/99818646/99818646_01_s.jpg");
		afterSaleRequ.setPic3Url("http://img.yougou.com/pics/tata/2011/58900111/58900111_01_s.jpg");
		afterSaleRequService.insertAfterSaleRequ(afterSaleRequ);
	}
	
	@Test
	@Rollback(false)
	public void insertAfterSaleRequList() throws ParseException {
		for(int i=1;i<=40;i++){
			AfterSaleRequ afterSaleRequ = new AfterSaleRequ();
			afterSaleRequ.setDescription("尽快处理哦。。。"+i);
			afterSaleRequ.setOrderMainNo("Axxxxxxx"+i);
			afterSaleRequ.setPic1Url("http://img.yougou.com/pics/zero/2012/99819620/99819620_01_s.jpg");
			afterSaleRequ.setPic2Url("http://img.yougou.com/pics/belle/2012/99818646/99818646_01_s.jpg");
			afterSaleRequ.setPic3Url("http://img.yougou.com/pics/tata/2011/58900111/58900111_01_s.jpg");
			afterSaleRequService.insertAfterSaleRequ(afterSaleRequ);
		}
	}
	
	@Test
	public void queryafterSaleRequList() throws ParseException {
		AfterSaleRequ afterSaleRequ = new AfterSaleRequ();
		afterSaleRequ.setOrderMainNo("A2123456789");
		afterSaleRequ.setState(1);
		List<AfterSaleRequ> lstAfterSaleRequ = afterSaleRequService.queryAfterSaleRequList(afterSaleRequ,1,10);
		Assert.assertTrue(lstAfterSaleRequ.size() > 0);
	}

}
