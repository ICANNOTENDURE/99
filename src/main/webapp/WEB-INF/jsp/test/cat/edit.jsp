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
						<form action="testcat/saveOrUpdate.do" name="form" id="form" method="post">
							<input type="hidden" name="id" id="id" value="${pd.id }"/>
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">名称:</td>
									<td colspan="3"><input type="text" name="name" id="name" value="${pd.name}" placeholder="这里输入账号" title="账号" style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">医院:</td>
									<td colspan="3">
										<select id="hopId" name="hopId" class="form-control inputBorder" style="width:200px;"></select>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">价格:</td>
									<td colspan="3">
										<input type="text" name="price" value="${pd.price}"  title="账号" style="width:98%;" />
									</td>
									<!-- 
									<td style="width:79px;text-align: right;padding-top: 13px;">号数:</td>
									<td >
										<input type="text" name="qty"  value="${pd.qty}"  style="width:98%;" />
									</td>
									 -->
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">介绍:</td>
									<td colspan="3">
										<input type="text" name="content" value="${pd.content}"  title="介绍" style="width:98%;" />
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">周一:</td>
									<td >
										<label>
											<input type="hidden" name="monday" value="${pd.monday}">
											<input onclick="upRb('monday',this)" class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.monday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="mondayQty" value="${pd.mondayQty}" style="width:50px;">
										</label>
									</td>

									<td style="width:79px;text-align: right;padding-top: 13px;">周二:</td>
									<td>
										<label>
											<input type="hidden" name="tuesday" value="${pd.tuesday}">
											<input onclick="upRb('tuesday',this)" class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.tuesday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="tuesdayQty" value="${pd.tuesdayQty}" style="width:50px;">
										</label>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">周三:</td>
									<td>
										<label>
											<input type="hidden" name="wednesday" value="${pd.wednesday}">
											<input onclick="upRb('wednesday',this)" class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.wednesday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="wednesdayQty" value="${pd.wednesdayQty}" style="width:50px;">
										</label>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">周四:</td>
									<td>
										<label>
											<input type="hidden" name="thursday" value="${pd.thursday}">
											<input onclick="upRb('thursday',this)"  class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.thursday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="thursdayQty" value="${pd.thursdayQty}" style="width:50px;">
										</label>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">周五:</td>
									<td>
										<label>
											<input type="hidden" name="friday" value="${pd.friday}">
											<input onclick="upRb('friday',this)"  class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.friday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="fridayQty" value="${pd.fridayQty}" style="width:50px;">
										</label>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">周六:</td>
									<td>
										<label>
											<input type="hidden" name="saturday" value="${pd.saturday}">
											<input onclick="upRb('saturday',this)"  class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.saturday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="saturdayQty" value="${pd.saturdayQty}" style="width:50px;">
										</label>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">周日:</td>
									<td >
										<label>
											<input type="hidden" name="sunday" value="${pd.sunday}">
											<input onclick="upRb('sunday',this)"  class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${pd.sunday == 1 }">checked="checked"</c:if> >
											<span class="lbl"></span>
											<input type="text" name="sundayQty" value="${pd.sundayQty}" style="width:50px;">
										</label>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">状态:</td>
									<td >
										<select name="status" title="状态">
										<option value="Y" <c:if test="${pd.status == 'Y' }">selected</c:if> >正常</option>
										<option value="N" <c:if test="${pd.status == 'N' }">selected</c:if> >冻结</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="center" colspan="4">
										<a class="btn  btn-primary" onclick="save();">保存</a>
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
</body>						
<script type="text/javascript">
	$(function() {
		$('#hopId').commonSelect({
			url:"hop/hopSelect.do"   
		});
		if('${pd.hopId}'!=""){
			$('#hopId').commonSelect({
				url:"hop/hopSelect.do",
				data:[{"id": '${pd.hopId}', "text": '${pd.hopName}'}]
			});
			$("#hopId").val('${pd.hopId}').trigger("change");
		}
	});
	//保存
	function save(){
		if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'输入名称',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}

		layer.load(1, {shade: [0.5,'#fff']})
		$("#form").submit();
	
	}
	
	function upRb(name,obj){
		if(($(obj).is(':checked'))){
			$("input[name='"+name+"']").val(1);
		}else{
			$("input[name='"+name+"']").val(0);
		}
	}
</script>
</html>