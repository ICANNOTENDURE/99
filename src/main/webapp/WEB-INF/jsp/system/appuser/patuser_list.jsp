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
						<!-- 检索  -->
						<form action="patuser/listUsers.do" method="post" name="userForm" id="userForm">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
									<span class="input-icon">
										<input class="nav-search-input" autocomplete="off" id="nav-search-input" type="text" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词" />
										<i class="ace-icon fa fa-search nav-search-icon"></i>
									</span>
									</div>
								</td>
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="searchs();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
									<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">账号</th>
									<th class="center">姓名</th>
									<th class="center">状态</th>
									<th class="center">性别</th>
									<th class="center">结婚</th>
									<th class="center">出生日期</th>
									<th class="center">登陆日期</th>
									<th class="center">操作</th>
								</tr>
							</thead>
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty userList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${userList}" var="user" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">
												<label><input type='checkbox' name='ids' value="${user.USER_ID }" id="ids"  class="ace" data-phone="${user.USER_ACCOUNT}" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class="center">${user.USER_ACCOUNT}</td>
											<td class="center">${user.USER_NAME }</td>
											<td class="center" >
												<c:if test="${user.STATUS == 'Y' }"><span class="label label-success arrowed-in">正常</span></c:if>
												<c:if test="${user.STATUS == 'N' }"><span class="label label-important arrowed">停用</span></c:if>
											</td>
											<td class="center">${user.USER_SEX} </td>
											<td class="center" style="width: 60px">
												<c:if test="${user.USER_MARRY == '1' }">已婚</c:if>
												<c:if test="${user.USER_MARRY == '0' }">未婚</c:if>
											</td>
											<td class="center">${user.USER_BIRTH}</td>
											<td class="center">${user.USER_LOGINDATE}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.email == 1 }">
													<a class="btn btn-xs btn-info" title='发送电子邮件' onclick="sendEmail('${user.EMAIL }');">
														<i class="ace-icon fa fa-envelope-o bigger-120" title="发送电子邮件"></i>
													</a>
													</c:if>
													<c:if test="${QX.sms == 1 }">
													<a class="btn btn-xs btn-warning" title='发送短信' onclick="sendSms('${user.PHONE }');">
														<i class="ace-icon fa fa-envelope-o bigger-120" title="发送短信"></i>
													</a>
													</c:if>
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="popUser('${user.USER_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="delUser('${user.USER_ID }','${user.USERNAME }');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.email == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="sendEmail('${user.EMAIL }');" class="tooltip-info" data-rel="tooltip" title="发送电子邮件">
																	<span class="blue">
																		<i class="ace-icon fa fa-envelope bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.sms == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="sendSms('${user.PHONE }');" class="tooltip-success" data-rel="tooltip" title="发送短信">
																	<span class="blue">
																		<i class="ace-icon fa fa-envelope-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="editUser('${user.USER_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="delUser('${user.USER_ID }','${user.USERNAME }');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
														</ul>
													</div>
												</div>
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
								<td style="vertical-align:top;">
									<c:if test="${QX.add == 1 }">
									<a class="btn btn-mini btn-success" onclick="popUser();">新增</a>
									</c:if>
									<c:if test="${QX.email == 1 }"><a title="批量发送电子邮件" class="btn btn-mini btn-info" onclick="commonBath('确定要给选中的用户发送邮件吗?');"><i class="ace-icon fa fa-envelope bigger-120"></i></a></c:if>
									<c:if test="${QX.sms == 1 }"><a title="批量发送短信" class="btn btn-mini btn-warning" onclick="commonBath('确定要给选中的用户发送短信吗?');"><i class="ace-icon fa fa-envelope-o bigger-120"></i></a></c:if>
									<c:if test="${QX.del == 1 }">
									<a title="批量删除" class="btn btn-mini btn-danger" onclick="commonBath('确定要删除选中的数据吗?','patuser');" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
								</td>
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
		function popUser(user_id){
			user_id=user_id==undefined?"":user_id
			commonLayer({ 
					title: '病人信息',
					area: ['600px', '280px'],
					content: '<%=basePath%>patuser/goSaveOrUpdate.do?USER_ID='+user_id
			})
		}
		
		//删除
		function delUser(userId,msg){
			top.layer.confirm("确定要删除["+msg+"]吗?", function(index) {
					top.layer.close(index)
					top.jzts();
					var url = "<%=basePath%>patuser/delete/"+userId;
					$.get(url,function(data){
						alert(data)
						nextPage(1);
					});
				
			});
		}
		

		
		//导出excel
		function toExcel(){
			var keywords = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			var STATUS = $("#STATUS").val();
			window.location.href='<%=basePath%>happuser/excel.do?keywords='+keywords+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&ROLE_ID='+ROLE_ID+'&STATUS='+STATUS;
		}
		</script>
</html>
