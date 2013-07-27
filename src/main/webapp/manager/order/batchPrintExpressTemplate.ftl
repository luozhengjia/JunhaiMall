<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
<style type="text/css" media=print>
.noprint{display:none}
.PageNext{PAGE-BREAK-AFTER:always}
</style>
<style>
.drag{border:0px solid #ff8c40;background:#fff5f6;padding:5px;color:blue;height:16px; line-height:16px;
	font-size:16px;display:inline-block;position:absolute;left:0px;top:0px;padding-right:15px;font-weight:600;}
</style>
<title></title>
<script language="javascript">
	function printPage(obj) {
		try{
			var body = window.document.body.innerHTML;
			
			//循环打印快递单
			$("div[name='printBox']").each(function (i){
				i++;
				alert("准备打印第"+i+"张发货单");
				window.document.body.innerHTML = this.innerHTML;
				window.print();
		    });
		    
		    //异步更新打印状态
			<#if orderMainIds??>$.get("/system/order/printMainExpress.sc?orderMainIds=${orderMainIds}");</#if>
			<#if orderReplIds??>$.get("/system/order/printReplExpress.sc?orderReplIds=${orderReplIds}");</#if>
			window.document.body.innerHTML = body;
		}catch(e){}
	}
	</script>
</head>

<body>
<div class="container">
	<!--工具栏start--> 
    <div class="toolbar">
		<div class="t-content"> 
			<div class="btn"
				<#if orderMainIds??>onclick="javascript:gotolink('${BasePath}/system/order/orderMainList.sc?state=${state}')"</#if>
				<#if orderReplIds??>onclick="javascript:gotolink('${BasePath}/system/order/orderReplList.sc?state=${state}')"</#if>>
				<span class="btn-back"></span>
				<b class="ico_btn back"></b>
				<span class="btn_txt">返回</span>
				<span class="btn_r"></span> 
			</div>
		</div>
	</div>
    <!--工具栏end-->
    
	<div class="list_content">
		<div class="top clearfix" class="noprint">
			<ul class="tab">
				<li class="curr">
				  <span><a href="#" class="btn-onselc">批量打印快递单</a></span>
				</li>
			</ul>
		</div>
 		<div class="modify"> 
 			<#if orderList?? >
            <#list orderList as item >
				<div style="float:left;width:265px;height:200px;">
					<p style="height:32px;line-height:32px;font-size:14px;font-weight:600;">&nbsp;&nbsp;缩略图：<#if orderMainIds??>${item.orderMainNo!''}</#if><#if orderReplIds??>${item.orderReplNo!''}</#if>
	        		<div id="Thumbnail_${item.id!''}" style="position:relative;border:1px solid #ccc;width:250px;height:160px;background-repeat:no-repeat;background-image:url(${BasePath}/images/express/express_thumbnail.jpg)"></div>
	        		<div id="${item.id!''}" name="printBox" style="position:relative;display:none;border:1px solid #ccc;width:840px;height:550px;">
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 48px; LEFT: 70px" class="drag" >${deliveryCustomerCode!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 75px;  LEFT: 70px" class="drag" >${deliveryCompany!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 75px;  LEFT: 270px" class="drag" >${deliverySender!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 105px;  LEFT: 70px" class="drag" >${deliveryProCityArea!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 133px;  LEFT: 70px" class="drag" >${deliveryDetailAddress!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 160px;  LEFT: 140px" class="drag" >${deliveryMobilePhone!''}</SPAN>
						
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 205px;  LEFT: 70px" class="drag" >
							<#if orderMainIds??>${item.orderMainNo!''}</#if><#if orderReplIds??>${item.orderReplNo!''}</#if>
						</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 233px;  LEFT: 270px" class="drag" >${item.consignee!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 260px;  LEFT: 70px" class="drag" >${item.provinceCityArea!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 285px;  LEFT: 70px" class="drag" >${item.detailAddress!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 315px;  LEFT: 135px" class="drag" >${item.mobilePhone!''}</SPAN>
						
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 395px;  LEFT: 70px" class="drag" >${item.deliveryContent!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 5px;  LEFT: 555px" class="drag" >${deliveryOrignAddress!''}</SPAN>
						<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 235px;  LEFT: 640px" class="drag" >${deliverySenderSign!''}</SPAN>
					</div>
				</div>
			</#list>
            </#if>
			<div style="clear:both;"><input type="button" value="打印" onclick="printPage('Box')" class="btn-save"/><input type="button" class="btn-back" 
				<#if orderMainIds??>onclick="javascript:gotolink('${BasePath}/system/order/orderMainList.sc?state=${state}')"</#if>
				<#if orderReplIds??>onclick="javascript:gotolink('${BasePath}/system/order/orderReplList.sc?state=${state}')"</#if>
			 	value="返回"/></div>
    	</div>
  	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
