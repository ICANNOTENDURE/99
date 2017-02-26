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
						<form action="docservice/listAuth.do" method="post" name="userForm" id="userForm">
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
								<td>
									状态:
								</td>
								<td>
								  	<select id="status" name="status" title="状态" style="width:200px;">
								  		<c:if test="${status != null }">
											<c:forEach items="${status}" var="statusrd" >
												<option value="${statusrd.id}" <c:if test="${statusrd.selected == '1' }">selected</c:if> >${statusrd.text}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
								<!-- 按钮 -->
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="searchs();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>


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
											 <c:if test="${rd.docServices != null }">
												 <table  class="table table-striped table-bordered table-hover">
														<c:forEach items="${rd.docServices}" var="serv" >
															<tr>
																<td>${serv.serviceTypeName}</td>
															 	<td>${serv.servicePrice}元</td>
															 	<td>
															 		<c:choose>
																 		<c:when test="${serv.auditFlag == 1 }">
																 		<a style="cursor:pointer;" onclick="audit('${serv.serviceId}',2,'${rd.docName}','${serv.serviceTypeName}');" class="tooltip-info" data-rel="tooltip" title="通过">
																		<span class="green">
																			<i class="ace-icon fa fa-check bigger-120"></i>
																			通过
																		</span> 
																		</a>
																		</c:when>
																		<c:otherwise>
																			${serv.auditFlagDesc}
																		</c:otherwise>
																	</c:choose>	
																</td>
																<td>	
																	<a style="cursor:pointer;" onclick="audit('${serv.serviceId}',3,'${rd.docName}','${serv.serviceTypeName}');" class="tooltip-info" data-rel="tooltip" title="拒绝">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		拒绝
																	</span> 
																	</a>
															 	</td>
															</tr>
														</c:forEach>
													</table>
												</c:if>
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
		function audit(id,status,docName,serviceName){
			str=status=="2"?"通过":"拒绝"
			layer.confirm('确认要'+str+docName+"的"+serviceName+"吗", {icon: 3, title:'提示'}, function(index){
				 
				 $.post(
							"docservice/audit.do",
							{serviceId:id,status:status,content:""},
							function(result){
						    	if(result.code==0){
						    		searchs();
						    	}else{
						    		commonAlert("失败:"+result.message)
						    	}
					 		},
					 		"json"
					 );
				  
			 });

		}
		</script>
</html>
