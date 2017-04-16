<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
						<!-- 检索  -->
						<form action="testappoint/list.do" method="post" name="userForm" id="userForm">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
									<span class="input-icon">
										<input class="nav-search-input" autocomplete="off" id="nav-search-input" type="text" name="patName" value="${pd.patName}" placeholder="预约电话" />
										<i class="ace-icon fa fa-search nav-search-icon"></i>
									</span>
									</div>
								</td>
								<td>
									<div class="nav-search">
									<span class="input-icon">
										<input class="nav-search-input" autocomplete="off" id="nav-search-input" type="text" name="testname" value="${pd.testname}" placeholder="这里体检名称" />
										<i class="ace-icon fa fa-search nav-search-icon"></i>
									</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select id="hopId" name="hopId" class="form-control inputBorder" style="width:200px;"></select>
									
								</td>
	
								<td style="vertical-align:top;padding-left:2px;">
									
									<input class="span10 date-picker"  name="testStartDate" id="startDate"   type="text" data-date-format="yyyy-mm-dd" readonly="readonly"  placeholder="开始日期" value='${pd.startDate}' />
								</td>
								<td style="vertical-align:top;padding-left:2px;">
									
									<input class="span10 date-picker"  name="testEndDate" id="endDate"   type="text" data-date-format="yyyy-mm-dd" readonly="readonly"  placeholder="结束日期" value='${pd.endDate}' />
								</td>
	
														
								<!-- 按钮 -->
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="searchs();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
									<!-- 
									<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
									<c:if test="${QX.FromExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="ace-icon fa fa-cloud-upload bigger-110 nav-search-icon blue"></i></a></td></c:if>
									 -->
								</c:if>
								<!-- 按钮 -->
							</tr>
						</table>
						<!-- 检索  -->
						
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">医院名称</th>
									<th class="center">体检分类名称</th>
									<th class="center">体检日期</th>
									<th class="center">预约日期</th>
									<th class="center">价格</th>
									<th class="center">序号</th>
									<th class="center">预约电话</th>
									<th class="center">状态</th>
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
											<td class="center">${rd.testHopName}</td>
											<td class='center' >${rd.testCatName}</td>
											<td class='center' ><fmt:formatDate value='${rd.testDate}' pattern="yyyy-MM-dd"/></td>
											<td class='center' ><fmt:formatDate value='${rd.appointDate}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td class='center' >${rd.price}</td>
											<td class='center' >${rd.seq}</td>
											<td class='center' >${rd.patTel}</td>
											<td class='center' >${rd.status}</td>
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
	
		<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	</body>
 	<script type="text/javascript">
 	$(function() {
 		$('.date-picker').datepicker({autoclose: true,todayHighlight: true})
 	})	
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
					area: ['600px', '480px'],
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
					area: ['600px', '550px'],
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
			if('${pd.hopId}'!=""){
				$('#hopId').commonSelect({
					url:"hop/hopSelect.do",
					data:[{"id": '${hopId}', "text": '${hopName}'}]
				});
				$("#hopId").val('${hopId}').trigger("change");
			}
		});
		</script>
</html>
