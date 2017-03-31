<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<form action="testcat/saveCreate.do" name="form" id="form" method="post">
							<input type="hidden" name="id" id="id" value="${pd.id }"/>
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">名称:</td>
									<td colspan="3"><input type="text" name="name" id="name" value="${pd.name}" placeholder="这里输入账号" title="账号" style="width:98%;" readonly="readonly" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">医院:</td>
									<td colspan="3">
										<input type="text" name="name" id="name" value="${pd.hopName}" placeholder="这里输入账号" title="账号" style="width:98%;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">排班星期:</td>
									<td colspan="3">
										<c:if test="${pd.monday == 1}"> <span class="label label-success">周一</span> </c:if>
										<c:if test="${pd.tuesday == 1}"> <span class="label label-success">周二</span> </c:if>
										<c:if test="${pd.wednesday == 1}"> <span class="label label-success">周三</span> </c:if>
										<c:if test="${pd.thursday == 1}"> <span class="label label-success">周四</span> </c:if>
										<c:if test="${pd.friday == 1}"> <span class="label label-success">周五</span> </c:if>
										<c:if test="${pd.saturday == 1}"> <span class="label label-success">周六</span> </c:if>
										<c:if test="${pd.sunday == 1}"> <span class="label label-success">周日</span> </c:if>
									</td>
								</tr>


								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">价格:</td>
									<td colspan="3">${pd.price}</td>
								</tr>
								<tr>	
									<td style="width:79px;text-align: right;padding-top: 13px;">号数:</td>
									<td colspan="3">${pd.qty}</td>
								</tr>	
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">上次排班结束日期:</td>
									<td style="padding-left:2px;"></td>
								</tr>
								
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">开始日期:</td>
									<td style="padding-left:2px;"><input class="span10 date-picker"  name="startDate" id="startDate"   type="text" data-date-format="yyyy-mm-dd" readonly="readonly"  placeholder="开始日期" value='${date}' /></td>
								</tr>
								<tr>	
									<td style="width:79px;text-align: right;padding-top: 13px;">结束日期:</td>
									<td style="padding-left:2px;"><input class="span10 date-picker"  name="endDate" id="endDate"  type="text" data-date-format="yyyy-mm-dd" readonly="readonly"  placeholder="结束日期" value='${eddate}' /></td>
								</tr>
								<tr>
									<td class="center" colspan="4">
										<a class="btn  btn-primary" onclick="save();">生成排班记录</a>
										<a class="btn  btn-danger" onclick="commonClose();">取消</a>
									</td>
								</tr>
							</table>
							</div>
							<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						</form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
</body>						
<script type="text/javascript">
	$(function() {
		//日期框
		
		$('.date-picker').datepicker({autoclose: true,todayHighlight: true,startDate:'${date}'})
	});
	//保存
	function save(){
		if($("#startDate").val()==""){
			$("#startDate").tips({
				side:3,
	            msg:'请选择开始日期',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#startDate").focus();
			return;
		}
		if($("#endDate").val()==""){
			$("#endDate").tips({
				side:3,
	            msg:'请选择结束日期',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#endDate").focus();
			return;
		}
		index=commonLoad();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>testcat/saveCreate.do?id='+$("#id").val()+'&startDate='+$("#startDate").val()+'&endDate='+$("#endDate").val(),
			dataType:'json',
			cache: false,
			success: function(data){
				 commonLoadClose(index)
				 if(0 == data.code){
					 top.layer.confirm(data.message, {
						  btn: ['确认', '返回']
						}, function(index){
							top.layer.close(index)
						}, function(index){
							top.layer.close(index)
						});
				 }else{
					 top.layer.alert(data.message)
				 }
			}
		});
	
	}

</script>
</html>