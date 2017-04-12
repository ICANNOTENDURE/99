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
						<form name="form" id="form" method="post" >
							<input type="hidden" name="docInfo.infoId" id="id" value="${docInfo.infoId }"/>
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">医院:</td>
									<td colspan="2">
										<input type="text" value="${hopName}"  style="width:98%;" readonly="readonly"/>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">科室:</td>
									<td colspan="2">
										<input type="text" value="${locName}" style="width:98%;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">姓名:</td>
									<td colspan="2">
										<input type="text" value="${docInfo.docName}"  style="width:98%;" readonly="readonly"/>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">身份证号:</td>
									<td colspan="2">
										<input type="text" value="${docInfo.docIdCard}" style="width:98%;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">职称:</td>
									<td colspan="2">
										<input type="text" value="${docInfo.docTitle}"  style="width:98%;" readonly="readonly"/>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">手机:</td>
									<td colspan="2">
										<input type="text" value="${docAccount}" style="width:98%;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">申请历史:</td>
									<td colspan="5">
										<c:if test="${reqs != null &&  reqs != null }">
											<table  class="table table-striped table-bordered table-hover">
												<c:forEach items="${reqs}" var="rd" varStatus="vs">
													<tr>
														<td>${vs.index+1}</td>
														<td>${rd.createDate}</td>
													 	<td>${rd.statusDesc}</td>
													 	<td>${rd.content}</td>
													</tr>
												</c:forEach>
											</table>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">照片:</td>
									<td colspan="5">
										<c:if test="${docInfo != null && docInfo.docPic != '' && docInfo.docPic != null }">
											<a href="<%=basePath%>uploadFiles/uploadImgs/${docInfo.docPic}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${docInfo.docPic}" width="210"/></a>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">身份证照片:</td>
									<td colspan="5">
										<c:if test="${docInfo != null &&  docInfo.docIdCardImgList != null }">
											<c:forEach items="${docInfo.docIdCardImgList}" var="rd" varStatus="vs">
												<a href="<%=basePath%>uploadFiles/uploadImgs/${rd}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${rd}" width="210"/></a>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">工牌照片:</td>
									<td colspan="5">
										<c:if test="${docInfo != null &&  docInfo.docWorkCardImg != null }">
											<c:forEach items="${docInfo.docWorkCardImgList}" var="rd" varStatus="vs">
												<a href="<%=basePath%>uploadFiles/uploadImgs/${rd}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${rd}" width="210"/></a>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">医师资格证照片:</td>
									<td colspan="5">
										<c:if test="${docInfo != null &&  docInfo.docQualificationImg != null }">
											<c:forEach items="${docInfo.docQualificationImgList}" var="rd" varStatus="vs">
												<a href="<%=basePath%>uploadFiles/uploadImgs/${rd}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${rd}" width="210"/></a>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">原因:</td>
									<td colspan="5">
										<textarea id="content" rows="3" cols="25"></textarea>
									</td>
								</tr>
								<tr>
									<td class="center" colspan="6">
										<c:if test="${docInfo != null &&  docInfo.auditFlag == 1 }">
											<a class="btn  btn-primary" onclick="save(2);">通过</a>
										</c:if>
										<a class="btn  btn-danger" onclick="save(3);">拒绝</a>
									</td>
								</tr>
							</table>
							</div>
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
	
</body>						
<script type="text/javascript">


	//保存
	function save(status){
		if(status==3){
			if($("#content").val()==""){
				 layer.tips('请填写拒绝原因', '#content', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return;
			}
		};
		$.ajax({
			  type: 'POST',
			  url: '<%=basePath%>docauth/audit.do',
			  data: {
				  	 infoId:$("#id").val(),
				     status:status,
				     content:$("#content").val()
			  },
			  success: function(data){
					 if(0 == data.code){
						 commonClose();
					 }else{
						 layer.open({
							  type: 1, 
							  content: 'error' //这里content是一个普通的String
						});
					 }
			  },
			  dataType: 'json'
		});

		}
</script>
</html>