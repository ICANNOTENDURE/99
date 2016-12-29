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
						<form action="docuser/saveOrUpdate.do" name="form" id="form" method="post" enctype="multipart/form-data">
							<input type="hidden" name="docInfo.infoId" id="id" value="${pd.docInfo.infoId }"/>
							<input type="hidden" name="docInfo.docId" id="id" value="${pd.docInfo.docId }"/>
							<input type="hidden" name="docUser.docId"  value="${pd.docUser.docId }"/>
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">账号:</td>
									<td colspan="2"><input type="text" name="docUser.docAccount" id="name"  value="${pd.docUser.docAccount}" placeholder="这里输入账号" title="账号" style="width:98%;" /></td>
									<td style="width:79px;text-align: right;padding-top: 13px;">姓名:</td>
									<td colspan="2"><input type="text" name="docInfo.docName" value="${pd.docInfo.docName}" placeholder="这里输入姓名" title="姓名" style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">医院:</td>
									<td colspan="2">
										<select id="hopId" name="docInfo.docHopid" class="form-control inputBorder" style="width:200px;"></select>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">科室:</td>
									<td colspan="2">
										<select id="locId" name="docInfo.docLocid" class="form-control inputBorder" style="width:200px;"></select>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">职称:</td>
									<td colspan="2">
										<select class="chosen-select form-control" name="docInfo.docTitle"  data-placeholder="医生职称" style="vertical-align:top;width: 200px;">
											<option value=""></option>
											<option value="">全部</option>
											<c:forEach items="${pd.doctitle}" var="dic">
												<option value="${dic.DICTIONARIES_ID }" <c:if test="${pd.docInfo.docTitle==dic.DICTIONARIES_ID}">selected</c:if>>${dic.NAME }</option>
											</c:forEach>
								  	</select>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">状态:</td>
									<td colspan="2">
										<select id="status" name="docUser.status" title="状态" style="width:200px;">
										<option value="Y" <c:if test="${pd.docInfo.status == 'Y' }">selected</c:if> >正常</option>
										<option value="N" <c:if test="${pd.docInfo.status == 'N' }">selected</c:if> >冻结</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">服务次数:</td>
									<td>
										<input type="text" name="docInfo.docServerNum"  value="${pd.docInfo.docServerNum}" style="width:98%;" />
									</td>
									<td>
										<input type="text" name="docInfo.docReplyNum"  value="${pd.docInfo.docReplyNum}" style="width:98%;" />
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">回复次数:</td>

									<td style="width:79px;text-align: right;padding-top: 13px;">排序:</td>
									<td>
										<input type="text" name="docInfo.docSeq"  value="${pd.docInfo.docSeq}" style="width:98%;" />
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">擅长疾病:</td>
									<td colspan="5">
										<select id="diseaseId" name="diseasIds" class="form-control inputBorder" style="width:550px;"></select>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">图片:</td>
									<td colspan="5">
										<c:if test="${pd == null || pd.docInfo.docPic == '' || pd.docInfo.docPic == null }">
										<input type="file" id="tp" name="tp" onchange="fileType(this)"/>
										</c:if>
										<c:if test="${pd != null && pd.docInfo.docPic != '' && pd.docInfo.docPic != null }">
											<a href="<%=basePath%>uploadFiles/uploadImgs/${pd.docInfo.docPic}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.docInfo.docPic}" width="210"/></a>
											<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.docInfo.infoId }');" />
											<input type="hidden" name="docInfo.docPic" id="tpz" value="${pd.docInfo.docPic }"/>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">简介:</td>
									<td colspan="5">
										<script id="editor" name="docInfo.docIntroduce" type="text/plain" style="width:653px;height:259px;">${pd.docInfo.docIntroduce}</script>
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
	<!-- 上传控件 -->
	<script src="static/ace/js/ace/elements.fileinput.js"></script>
	<!-- 编辑框-->
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
	<!-- 编辑框-->
	
</body>						
<script type="text/javascript">


	//保存
	function save(){
		if($("#name").val()==""){
			 layer.tips('请输入账号', '#name', {
				  tips: [1, '#3595CC'],
				  time: 4000
			 });
			return false;
		}
		if($("#status").val()==""){
			 layer.tips('请选择状态', '#status', {
				  tips: [1, '#3595CC'],
				  time: 4000
			 });
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
			url: '<%=basePath%>docuser/checkName/'+name,
			dataType:'json',
			cache: false,
			success: function(data){
				 if(0 == data.code){
					$("#form").submit();
				 }else{
					 layer.tips('此账号以存在', '#name', {
						  tips: [1, '#3595CC'],
						  time: 4000
					 });
				 }
			}
		});
	}
	//删除图片
	function delP(id){
		 if(confirm("确定要删除图片？")){
			var url = "docuser/deltp.do?id="+id;
			$.get(url,function(data){
				if(data.code==0){
					alert("删除成功!");
					document.location.reload();
				}
			});
		} 
	}
	
	$(function() {
		$('#locId').commonSelect({
			url:"loc/locSelect.do"
		})	
		$('#diseaseId').commonSelect({
			url:"disease/diseaseSelect.do",
			tags: true,
			separator: ",", // 分隔符
			multiple: true
		});
		$('#hopId').commonSelect({
			url:"hop/hopSelect.do"   
		});
		//上传
		$('#tp').ace_file_input({
			no_file:'请选择图片 ...',
			btn_choose:'选择',
			btn_change:'更改',
			droppable:false,
			onchange:null,
			thumbnail:false, //| true | large
			whitelist:'gif|png|jpg|jpeg',
		});
		if('${pd.docInfo.docLocid}'!=""){
			$('#locId').commonSelect({
				url:"loc/locSelect.do",
				data:[{"id": '${pd.docInfo.docLocid}', "text": '${pd.docInfo.locName}'}]
			});
			$("#docLocid").val('${pd.docInfo.docLocid}').trigger("change");
		}
		if('${pd.docInfo.docHopid}'!=""){
			$('#hopId').commonSelect({
				url:"hop/hopSelect.do",
				data:[{"id": '${pd.docInfo.docHopid}', "text": '${pd.docInfo.hopName}'}]
			});
			$("#hopId").val('${pd.docInfo.docHopid}').trigger("change");
		}
		if('${pd.diseaseSelect}'!=""){
			$('#diseaseId').commonSelect({
				url:"disease/diseaseSelect.do",
				tags: true,
				separator: ",", // 分隔符
				multiple: true,
				data:JSON.parse('${pd.diseaseSelect}')
			});
			$("#diseaseId").val(JSON.parse('${pd.diseaseIds}')).trigger("change");
		}
	});
	var ue = UE.getEditor('editor');
</script>
</html>