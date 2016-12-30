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
						<!-- 检索 2 -->
						<form action="docservice/list.do" method="post" name="userForm" id="userForm">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
									<span class="input-icon">
										<input class="nav-search-input" autocomplete="off" id="nav-search-input" type="text" name="keywords" value="${pd.keywords}" placeholder="这里输入关键词/账号或姓名" />
										<i class="ace-icon fa fa-search nav-search-icon"></i>
									</span>
									</div>
								</td>
								<!-- 按钮 -->
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="searchs();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
								<td>
									医生:
								</td>
								<td>
								  	<select id="docId" name="DOC_ID" class="form-control inputBorder" style="width:150px;"></select>
								</td>
								<td>
									价格:
								</td>
								<td>
								  	<input class="nav-search-input" autocomplete="off"  type="text" name="SERVICE_PRICE" id="servicePrice" placeholder="服务价格" />		
								</td>
								<td>
									服务类型:
								</td>
								<td>
								  		<select class="chosen-select form-control" name="SERVICE_TYPE" id="serviceType" style="vertical-align:top;width: 150px" >
											<c:forEach items="${docService}" var="dic">
												<option value="${dic.DICTIONARIES_ID }" >${dic.NAME }</option>
											</c:forEach>
										</select>	
								</td>
								<c:if test="${QX.add == 1 }">
									<td style="vertical-align:top;padding-left:2px;">
									<a class="btn btn-xs btn-success" onclick="edit();">新增</a>
									</td>
								</c:if>
								<!-- 按钮 -->
							</tr>
						</table>
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px" >
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">医生账号</th>
									<th class="center">姓名</th>
									<th class="center">服务</th>
									<th class="center">医院</th>
									<th class="center">科室</th>
									<th class="center">职称</th>
								</tr>
							</thead>
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty list}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${list}" var="rd" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">
												<label><input type='checkbox' name='ids' value="${rd.docUser.docId }" id="ids"  class="ace"/><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class="center">${rd.docUser.docAccount}</td>
											<td class='center' >${rd.docName}</td>
											<td class='center' >
													<c:forEach items="${rd.docServices}" var="serv" varStatus="vs1">
														<a class="btn btn-light btn-sm " onclick="(commonDelete('docservice/delete/${serv.serviceId}'));" style="margin-bottom: 5px">
														${serv.serviceTypeName}
														:${serv.servicePrice}元 
														<span class="red">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</span>
														</a>
														</br>
													</c:forEach>
											</td>
											<td class='center' >${rd.hopName}</td>
											<td class='center' >${rd.locName}</td>
											<td class='center' >${rd.docTitle}</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
							
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
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
		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	
	

	</body>
 	<script type="text/javascript">

		
		//检索
		function searchs(){
			top.jzts();
			$("#userForm").submit();
		}

		//修改
		function edit(id){
			 if($("#docId").val()==null){
				 layer.tips('医生不能为空', '#docId', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return;
			 }
			 price=$("#servicePrice").val().trim();
			 if(price==""){
				 layer.tips('价格不能为空', '#servicePrice', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return; 
			 }
			 if(isNaN(price)){
				 layer.tips('只能是数字', '#servicePrice', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return; 
			 }
			 if($("#serviceType").val()==""){
				 layer.tips('服务类型不能为空', '#serviceType', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return;
			 }
			 var index = layer.load(1, {shade: [0.5,'#fff']});
			 $.post(
					"docservice/saveOrUpdate.do",
					{docinfoId:$("#docId").val(),serviceType:$("#serviceType").val(),servicePrice:$("#servicePrice").val()},
					function(result){
						layer.close(index)
				    	if(result.code==0){
				    		searchs();
				    	}else{
				    		commonAlert("失败:"+result.message)
				    	}
			 		},
			 		"json"
			 );
		}
		$(function() {
			$('#docId').commonSelect({
				url:"docuser/docSelect.do"   
			});
		});	
		</script>
</html>
