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
						<!-- 检索  -->
						<form action="testcat/list.do" method="post" name="userForm" id="userForm">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
									<span class="input-icon">
										<input class="nav-search-input" autocomplete="off" id="nav-search-input" type="text" name="name" value="${name}" placeholder="这里输入关键词" />
										<i class="ace-icon fa fa-search nav-search-icon"></i>
									</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select id="hopId" name="hopId" class="form-control inputBorder" style="width:200px;"></select>
									
								</td>
								<!-- 按钮 -->
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="searchs();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
									<!-- 
									<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
									<c:if test="${QX.FromExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="ace-icon fa fa-cloud-upload bigger-110 nav-search-icon blue"></i></a></td></c:if>
									 -->
								</c:if>
								<c:if test="${QX.add == 1 }">
									<td style="vertical-align:top;padding-left:2px;">
									<a class="btn btn-xs btn-success" onclick="edit();">新增</a>
									</td>
								</c:if>
								<!-- 按钮 -->
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
									<th class="center">医院名称</th>
									<th class="center">体检分类名称</th>
									<th class="center">状态</th>
									<th class="center">周一</th>
									<th class="center">周二</th>
									<th class="center">周三</th>
									<th class="center">周四</th>
									<th class="center">周五</th>
									<th class="center">周六</th>
									<th class="center">周日</th>
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
											<td class='center' style="width: 30px;">
												<label><input type='checkbox' name='ids' value="${rd.hopId }" id="ids"  class="ace"/><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class="center">${rd.hopName}</td>
											<td class='center' >${rd.name}</td>
											<td class="center" >
												<c:if test="${rd.status == 'Y' }"><span class="label label-success arrowed-in">正常</span></c:if>
												<c:if test="${rd.status == 'N' }"><span class="label label-important arrowed">停用</span></c:if>
											</td>
											<td class='center' >
												<c:if test="${rd.monday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.monday == 0}"> <span class="label label-danger ">无</span> </c:if>
											</td>
											<td class='center' >
												<c:if test="${rd.tuesday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.tuesday == 0}"> <span class="label label-danger ">无</span> </c:if>
											</td>
											<td class='center' >
												<c:if test="${rd.wednesday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.wednesday == 0}"> <span class="label label-danger ">无</span> </c:if>
											</td>
											<td class='center' >
												<c:if test="${rd.thursday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.thursday == 0}"> <span class="label label-danger ">无</span> </c:if>
											
											</td>
											<td class='center' >
												<c:if test="${rd.friday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.friday == 0}"> <span class="label label-danger ">无</span> </c:if>
											
											</td>
											<td class='center' >
												<c:if test="${rd.saturday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.saturday == 0}"> <span class="label label-danger ">无</span> </c:if>
											
											</td>
											<td class='center' >
												<c:if test="${rd.sunday == 1}"> <span class="label label-success">有</span> </c:if>
												<c:if test="${rd.sunday == 0}"> <span class="label label-danger ">无</span> </c:if>
											
											</td>
											
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${rd.id}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<a class="btn btn-xs btn-primary" title="生成排班记录" onclick="create('${rd.id}');">
														<i class="ace-icon fa fa-key bigger-120" title="生成排班记录"></i>生成排班记录
													</a>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="edit('${rd.id }');" class="tooltip-error" data-rel="tooltip" title="修改">
																	<span class="red">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
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
			id=id==undefined?"":id;
			commonLayer({ 
					title: '体检分类信息',
					area: ['600px', '420px'],
					content: '<%=basePath%>testcat/goSaveOrUpdate.do?id='+id,
					end :function(){
						searchs();
					}		
			})
		}
		

		

		
		//导出excel
		function toExcel(){
			var keywords = $("#nav-search-input").val();
			var HOP_LEVEL = $("#HOP_LEVEL").val();
			window.location.href='<%=basePath%>hop/excel.do?keywords='+keywords+'&HOP_LEVEL='+HOP_LEVEL
		}
		
		//打开上传excel页面
		function fromExcel(){
			commonLayer({ 
				title: 'excel导入',
				area: ['300px', '250px'],
				content: '<%=basePath%>hop/goUploadExcel.do?',
				end :function(){
					searchs();
				}		
			});
		}
		function create(id){
			commonLayer({ 
					title: '生成排班记录',
					area: ['600px', '500px'],
					content: '<%=basePath%>testcat/goSaveOrUpdate.do?id='+id+'&isCreate=1',
					end :function(){
						searchs();
					}		
			})
		}
		$(function() {
			$('#hopId').commonSelect({
				url:"hop/hopSelect.do"   
			});
			if('${hopId}'!=""){
				$('#hopId').commonSelect({
					url:"hop/hopSelect.do",
					data:[{"id": '${hopId}', "text": '${hopName}'}]
				});
				$("#hopId").val('${hopId}').trigger("change");
			}
		});
		</script>
</html>
