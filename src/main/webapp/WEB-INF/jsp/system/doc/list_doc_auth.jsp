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
						<form action="docauth/list.do" method="post" name="userForm" id="userForm">
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
								  		<option value="0" <c:if test="${pd.status == '0' }">selected</c:if> >待认证</option>
								  		<option value="1" <c:if test="${pd.status == '1' }">selected</c:if> >审核中</option>
										<option value="2" <c:if test="${pd.status == '2' }">selected</c:if> >审核通过</option>
										<option value="3" <c:if test="${pd.status == '3' }">selected</c:if> >审核拒绝</option>
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
									<th class="center" style="width:50px;">序号</th>
									<th class="center">医生姓名</th>
									<th class="center">医院</th>
									<th class="center">科室</th>
									<th class="center">状态</th>
									<th class="center">操作</th>
								</tr>
							</thead>
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty list}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${list}" var="rd" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center' >${rd.docName}</td>
											<td class='center' >${rd.hopName}</td>
											<td class='center' >${rd.locName}</td>
											<td class='center' >${rd.statusDesc}</td>
											<td class='center' >
													<a class="btn btn-xs btn-success" title="认证" onclick="audit('${rd.reqInfoId}');">
														<i class="ace-icon fa fa-key bigger-120" title="认证"></i>认证
													</a>
											</td>
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
		function audit(id){
			id=id==undefined?"":id;
			commonLayer({ 
					title: '认证医生信息',
					area: ['800px', '600px'],
					content: '<%=basePath%>docauth/goAudit.do?id='+id,
					end :function(){
						searchs();
					}		
			})
		}
		$(function() {
			$('#docId').commonSelect({
				url:"docuser/docSelect.do"   
			});
		});	
		</script>
</html>
