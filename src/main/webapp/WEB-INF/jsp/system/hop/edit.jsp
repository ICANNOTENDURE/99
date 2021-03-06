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
<%@ include file="../index/top.jsp"%>
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
						<form action="hop/saveOrUpdate.do" name="form" id="form" method="post"  enctype="multipart/form-data">
							<input type="hidden" name="hopId" id="id" value="${pd.hopId }"/>
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">名称:</td>
									<td><input type="text" name="hopName" id="name" value="${pd.hopName}" placeholder="这里输入名称" title="名称" style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">医疗机构级别:</td>
									<td>
										<select name="hopLevel" title="医疗机构级别">
												<c:forEach items="${levelList}" var="rd" varStatus="vs">
													<option value="${rd.DICTIONARIES_ID}" <c:if test="${pd.hopLevel == rd.DICTIONARIES_ID }">selected</c:if> >${rd.NAME}</option>
												</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">图片:</td>
									<td colspan="5">
										<c:if test="${pd == null || pd.hopPic == '' || pd.hopPic == null }">
										<input type="file" id="tp" name="tp" onchange="fileType(this)"/>
										</c:if>
										<c:if test="${pd != null && pd.hopPic != '' && pd.hopPic != null }">
											<a href="<%=basePath%>uploadFiles/uploadImgs/${pd.hopPic}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.hopPic}" width="210"/></a>
											<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.hopId }');" />
											<input type="hidden" name="hopPic"  value="${pd.hopPic }"/>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">医院地址:</td>
									<td><input type="text" name="hopAddress" id="name" value="${pd.hopAddress}" placeholder="这里输入地址" title="地址" style="width:98%;" /></td>
								</tr>								
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">是否体检:</td>
									<td>
										<select name="hopTest" title="体检">
										<option value="Y" <c:if test="${pd.hopTest == 'Y' }">selected</c:if> >是</option>
										<option value="N" <c:if test="${pd.hopTest != 'Y' }">selected</c:if> >否</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">状态:</td>
									<td>
										<select name="hopStatus" title="状态">
										<option value="Y" <c:if test="${pd.hopStatus == 'Y' }">selected</c:if> >正常</option>
										<option value="N" <c:if test="${pd.hopStatus == 'N' }">selected</c:if> >冻结</option>
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
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- 上传控件 -->
	<script src="static/ace/js/ace/elements.fileinput.js"></script>
</body>						
<script type="text/javascript">
$(function() {
	//上传
	$('#tp').ace_file_input({
		no_file:'请选择图片 ...',
		btn_choose:'选择',
		btn_change:'更改',
		droppable:false,
		onchange:null,
		thumbnail:false, //| true | large
		whitelist:'gif|png|jpg|jpeg',
		//blacklist:'gif|png|jpg|jpeg'
		//onchange:''
		//
	});
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
		if(($("#id").val()=="")||($("#id").val()=="undefined")){
			hasU();
		}else{
			$("#form").submit();
		}
	}
	
	//判断用户名是否存在
	function hasU(){
		var name=$("#name").val()
		$.ajax({
			type: "POST",
			url: '<%=basePath%>hop/checkName.do?name='+name,
			dataType:'json',
			cache: false,
			success: function(data){
				 if(0 == data.code){
					$("#form").submit();
				 }else{
					$("#name").css("background-color","#D16E6C");
					$("#name").tips({
						side:3,
			            msg:'此用户名已存在!',
			            bg:'#AE81FF',
			            time:3
			        });
				 }
			}
		});
	}
	//删除图片
	function delP(id){
		 if(confirm("确定要删除图片？")){
			var url = "hop/delPic.do?id="+id;
			$.get(url,function(data){
				if(data.code==0){
					alert("删除成功!");
					document.location.reload();
				}
			});
		} 
	}

</script>
</html>