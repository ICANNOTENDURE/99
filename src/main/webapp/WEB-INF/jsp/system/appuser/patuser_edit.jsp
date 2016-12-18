<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<%@ include file="../index/top.jsp"%>
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
						<form action="patuser/saveOrUpdate.do" name="userForm" id="userForm" method="post">
							<input type="hidden" name="userId" id="user_Id" value="${pd.userId }"/>
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">账号:</td>
									<td><input type="text" name="userAccount" id="loginname" value="${pd.userAccount}" placeholder="这里输入账号" title="账号" style="width:98%;" /></td>
									<td style="width:79px;text-align: right;padding-top: 13px;">姓名:</td>
									<td><input type="text" name="userName" id="USER_NAME" value="${pd.userName}" placeholder="姓名" title="姓名" style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">性别:</td>
									<td>
										<select name="userSex" title="性别">
										<option value="男" <c:if test="${pd.userSex == '男' }">selected</c:if> >男</option>
										<option value="女" <c:if test="${pd.userSex == '女' }">selected</c:if> >女</option>
										</select>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">状态:</td>
									<td>
										<select name="status" title="状态">
										<option value="Y" <c:if test="${pd.status == 'Y' }">selected</c:if> >正常</option>
										<option value="N" <c:if test="${pd.status == 'N' }">selected</c:if> >冻结</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">出生日期:</td>
									<td><input class="span10 date-picker" name="userBirth" id="USER_BIRTH" value="${pd.userBirth}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="出生日期" title="出生日期" style="width:98%;" /></td>
								
									<td style="width:79px;text-align: right;padding-top: 13px;">婚姻状态:</td>
									<td>
										<select name="userMarry" title="婚姻状态">
										<option value="1" <c:if test="${pd.userMarry == 'Y' }">selected</c:if> >已婚</option>
										<option value="0" <c:if test="${pd.userMarry == 'N' }">selected</c:if> >未婚</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="center" colspan="6">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="commonClose();">取消</a>
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
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</body>						
<script type="text/javascript">


	//保存
	function save(){

		if($("#loginname").val()=="" || $("#loginname").val()=="此用户名已存在!"){
			$("#loginname").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#loginname").focus();
			$("#loginname").val('');
			$("#loginname").css("background-color","white");
			return false;
		}else{
			$("#loginname").val(jQuery.trim($('#loginname').val()));
		}
	

		if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		if(($("#user_id").val()=="")||($("#user_id").val()=="undefined")){
			hasU();
		}else{
			$("#userForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	}
	
	//判断用户名是否存在
	function hasU(){
		var USERNAME = $("#loginname").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>patuser/checkAccount/'+USERNAME,
			dataType:'json',
			cache: false,
			success: function(data){
				 if(0 == data.code){
					$("#userForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#loginname").css("background-color","#D16E6C");
					setTimeout("$('#loginname').val('此用户名已存在!')",500);
				 }
			}
		});
	}
	
	
	$(function() {
		//日期框
		$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		//下拉框
		if(!ace.vars['touch']) {
			$('.chosen-select').chosen({allow_single_deselect:true}); 
			$(window)
			.off('resize.chosen')
			.on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			}).trigger('resize.chosen');
			$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
				if(event_name != 'sidebar_collapsed') return;
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			});
			$('#chosen-multiple-style .btn').on('click', function(e){
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
				 else $('#form-field-select-4').removeClass('tag-input-style');
			});
		}
	});

</script>
</html>