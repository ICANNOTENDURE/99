﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
						<!-- 检索  -->
						<form action="locdisease/list.do" method="post" name="userForm" id="userForm">
						<table style="margin-top:5px;">
							<tr>
								<td>
									科室:
								</td>
								<td>
									<input type="hidden" name="LOC_ID" id="LOC_ID">
								  	<select id="locId" class="form-control inputBorder" style="width:150px;"></select>
								</td>
								<td>
									疾病:
								</td>
								<td style="vertical-align:top;padding-left:2px;">
									<input type="hidden" name="DISEASE_ID" id="DISEASE_ID">
									<select id="diseaseId" class="form-control inputBorder" style="width:250px;"></select>
								</td>
								<!-- 按钮 -->
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="searchs();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
									<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
									<c:if test="${QX.FromExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="ace-icon fa fa-cloud-upload bigger-110 nav-search-icon blue"></i></a></td></c:if>
								</c:if>
								<c:if test="${QX.add == 1 }">
									<td style="vertical-align:top;padding-left:2px;">
									<a class="btn btn-xs btn-success" onclick="edit();">新增</a>
									</td>
								</c:if>
								<c:if test="${QX.del == 1 }">
									<td style="vertical-align:top;padding-left:2px;">
									<a title="批量删除" class="btn btn-xs btn-danger" onclick="commonDeleteAll('locdisease/deleteAll/');" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
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
									<th class="center">科室</th>
									<th class="center">疾病</th>
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
												<label><input type='checkbox' name='ids' value="${rd.locDiseaseId }" id="ids"  class="ace"/><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class="center">${rd.locName}</td>
											<td class='center' >${rd.diseaseName}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="commonDelete('locdisease/delete/${rd.locDiseaseId }');">
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
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="commonDelete('locdisease/delete/${rd.locDiseaseId }');" class="tooltip-error" data-rel="tooltip" title="删除">
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
			locid=$("#locId").val();
			$("#LOC_ID").val(locid==null?"":locid)
			disId=$("#diseaseId").val()
			$("#DISEASE_ID").val(disId==null?"":disId)
			$("#userForm").submit();
		}

		//修改
		function edit(){
			 
			 if($("#locId").val()==null){
				 layer.tips('科室不能为空', '#locId', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return;
			 }
			 if($("#diseaseId").val()==null){
				 layer.tips('疾病不能为空', '#diseaseId', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				 return;
			 }
			 var index = commonLoad();
			 $.post(
					"locdisease/saveOrUpdate.do",
					{locId:$("#locId").val(),diseaseId:$("#diseaseId").val()},
					function(result){
						commonClose(index);
				    	if(result.code==0){
				    		searchs();
				    	}else{
				    		commonAlert("失败:"+result.message)
				    	}
			 		},
			 		"json"
			 );
		}

		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>locdisease/excel.do'
		}
		
		//打开上传excel页面
		function fromExcel(){
			commonLayer({ 
				title: 'excel导入',
				area: ['300px', '250px'],
				content: '<%=basePath%>locdisease/goUploadExcel.do?',
				end :function(){
					searchs();
				}		
			});
		}
		$(function() {
			$('#locId').commonSelect({
				url:"loc/locSelect.do"   
			});
			$('#diseaseId').commonSelect({
				url:"disease/diseaseSelect.do"   
			});
		});
		</script>
</html>
