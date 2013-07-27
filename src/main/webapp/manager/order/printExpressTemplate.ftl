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
			var printArea = window.document.getElementById(obj).innerHTML;
			window.document.body.innerHTML = printArea;
			window.print();
			//Print();
			window.document.body.innerHTML = body;
			<#if orderMain??>$.get("/system/order/printMainExpress.sc?orderMainIds=${orderMain.id!''}");</#if>
			<#if orderRepl??>$.get("/system/order/printReplExpress.sc?orderReplIds=${orderRepl.id!''}");</#if>
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
				<#if orderMain??>onclick="javascript:gotolink('${BasePath}/system/order/orderMainList.sc?state=${orderMain.state!''}')"</#if>
				<#if orderRepl??>onclick="javascript:gotolink('${BasePath}/system/order/orderReplList.sc?state=${orderRepl.state!''}')"</#if>>
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
				  <span><a href="#" class="btn-onselc">打印快递单</a></span>
				</li>
			</ul>
		</div>
 		<div class="modify"> 
            <table class="com_modi_table">
				<tbody>
					<tr>
						<td>
							<div id="Box" style="position:relative;border:1px solid #ccc;width:840px;height:550px;background-repeat:no-repeat;background-image:url(${BasePath}/images/express/dly_bg_5.jpg)">
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 48px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryCustomerCode!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 75px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryCompany!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 75px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="1">${deliverySender!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 105px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryProCityArea!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 133px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryDetailAddress!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 160px; CURSOR: move; LEFT: 140px" class="drag" sizcache="7" sizset="1">${deliveryMobilePhone!''}</SPAN>
								
								<#if orderMain??>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 205px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderMain.orderMainNo!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 233px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="0">${orderMain.consignee!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 260px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderMain.provinceCityArea!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 285px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderMain.detailAddress!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 315px; CURSOR: move; LEFT: 135px" class="drag" sizcache="7" sizset="1">${orderMain.mobilePhone!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 395px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderMain.deliveryContent!''}</SPAN>
								</#if>
								
								<#if orderRepl??>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 205px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderRepl.orderReplNo!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 233px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="0">${orderRepl.consignee!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 260px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderRepl.provinceCityArea!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 285px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderRepl.detailAddress!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 315px; CURSOR: move; LEFT: 135px" class="drag" sizcache="7" sizset="1">${orderRepl.mobilePhone!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 395px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderRepl.deliveryContent!''}</SPAN>
								</#if>
								
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 5px; CURSOR: move; LEFT: 555px" class="drag" sizcache="7" sizset="1">${deliveryOrignAddress!''}</SPAN>
								<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 235px; CURSOR: move; LEFT: 640px" class="drag" sizcache="7" sizset="1">${deliverySenderSign!''}</SPAN>
							</div>
		            	</td>
					</tr>
					<tr>
						<td><input type="button" value="打印" onclick="printPage('Box')" class="btn-save"/><input type="button" class="btn-back"
							<#if orderMain??>onclick="javascript:gotolink('${BasePath}/system/order/orderMainList.sc?state=${orderMain.state!''}')"</#if>
							<#if orderRepl??>onclick="javascript:gotolink('${BasePath}/system/order/orderReplList.sc?state=${orderRepl.state!''}')"</#if>
							value="返回"/></td>
					</tr>
				</tbody>
			</table>
    	</div>
  	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
