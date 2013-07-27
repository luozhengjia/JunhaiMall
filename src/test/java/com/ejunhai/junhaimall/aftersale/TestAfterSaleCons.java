package com.ejunhai.junhaimall.aftersale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleConsService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestAfterSaleCons extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private IAfterSaleConsService afterSaleConsService;

	@Test
	@Rollback(false)
	public void insertAfterSaleCons() throws ParseException {
		AfterSaleCons afterSaleCons = new AfterSaleCons();
		afterSaleCons.setId("12323423423");
		afterSaleCons.setConsultation("consultation");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		afterSaleCons.setCreateTime(simpleDateFormat.parse("2012-06-24 12:00"));
		afterSaleCons.setOrderMainNo("A2123456789");
		afterSaleCons.setReply("1234");
		afterSaleCons.setState(1);
		afterSaleCons.setReplyTime(simpleDateFormat.parse("2012-06-24 12:00"));
		afterSaleConsService.insertAfterSaleCons(afterSaleCons);
	}
	
	@Test
	@Rollback(false)
	public void doAfterSaleConsReply() throws ParseException {
		AfterSaleCons afterSaleCons = new AfterSaleCons();
		afterSaleCons.setId("01770983330c4098afb454e759856b4c");
		afterSaleCons.setReply("回复咨询test");
		afterSaleConsService.doAfterSaleConsReply(afterSaleCons);
	}
	
	@Test
	@Rollback(false)
	public void insertAfterSaleConsList() throws ParseException {
		for(int i=1;i<=40;i++){
			AfterSaleCons afterSaleCons = new AfterSaleCons();
			afterSaleCons.setConsultation("consultation"+i);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			afterSaleCons.setCreateTime(simpleDateFormat.parse("2012-06-24 12:00"));
			afterSaleCons.setOrderMainNo("A"+i);
			afterSaleCons.setState(1);
			afterSaleCons.setReplyTime(simpleDateFormat.parse("2012-06-24 12:00"));
			afterSaleConsService.insertAfterSaleCons(afterSaleCons);
		}
	}
	
	
	@Test
	public void queryAfterSaleConsList() throws ParseException {
		AfterSaleCons afterSaleCons = new AfterSaleCons();
//		afterSaleCons.setOrderMainNo("A21234567891");
		afterSaleCons.setOrderMainNo("A2123456789");
		afterSaleCons.setConsultation("");
		afterSaleCons.setState(1);
//		afterSaleCons.setState(0);
		List<AfterSaleCons> lstAfterSaleCons = afterSaleConsService.queryAfterSaleConsList(afterSaleCons,1,10);
		Assert.assertTrue(lstAfterSaleCons.size() > 0);
	}

}
