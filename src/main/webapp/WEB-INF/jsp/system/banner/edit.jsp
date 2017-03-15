<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<form action="banner/saveOrUpdate.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
							<input type="hidden" name="bannerId" id="bannerId" value="${pd.bannerId}"/>
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">标题:</td>
									<td colspan="5"><input type="text" name="bannerTitle" id="bannerTitle" value="${pd.bannerTitle}" maxlength="32" style="width:99%;" placeholder="这里输入标题" title="标题"/></td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">图片:</td>
									<td colspan="5">
										<c:if test="${pd == null || pd.bannerImg == '' || pd.bannerImg == null }">
										<input type="file" id="tp" name="tp" onchange="fileType(this)"/>
										</c:if>
										<c:if test="${pd != null && pd.bannerImg != '' && pd.bannerImg != null }">
											<a href="<%=basePath%>uploadFiles/uploadImgs/${pd.bannerImg}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.bannerImg}" width="210"/></a>
											<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.bannerId }');" />
											<input type="hidden" name="bannerImg" id="tpz" value="${pd.bannerImg }"/>
										</c:if>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">状态:</td>
									<td>
										<select name="bannerStatus" title="状态" style="width:120px">
										<option value="Y" <c:if test="${pd.bannerStatus == 'Y' }">selected</c:if> >正常</option>
										<option value="N" <c:if test="${pd.bannerStatus == 'N' }">selected</c:if> >冻结</option>
										</select>
									</td>
									<td style="width:50px;text-align: right;padding-top: 13px;">类型:</td>
									<td>
										<select name="bannerType" title="类型" style="width:120px">
											<c:if test="${bannerType != null }">
											<c:forEach items="${bannerType}" var="statusrd" >
												<option value="${statusrd.id}" <c:if test="${statusrd.selected == '1' }">selected</c:if> >${statusrd.text}</option>
											</c:forEach>
											</c:if>
										</select>
									</td>
									<td style="width:50px;text-align: right;padding-top: 13px;">排序:</td>
									<td>
										<input type="text" name="bannerSeq" id="bannerSeq" value="${pd.bannerSeq}" maxlength="32" style="width:99%;" placeholder="这里输入排序" title="排序"/>
									</td>									
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">外网链接:</td>
									<td style="text-align: center;" colspan="5">
										<input type="text" name="bannerLinkUrl" id="bannerLinkUrl" value="${pd.bannerLinkUrl}"  style="width:99%;" placeholder="外网链接" title="外网链接"/>
									</td>
								</tr>
								<tr>
									<td style="width:50px;text-align: right;padding-top: 13px;">内容:</td>
									<td colspan="5"><script id="editor" name="bannerContent" type="text/plain" style="width:643px;height:259px;">${pd.bannerContent}</script></td>
								</tr>
								<tr>
									<td style="text-align: center;" colspan="6">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="commonClose();">取消</a>
									</td>
								</tr>
							</table>
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
<%@ include file="../../system/index/foot.jsp"%>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!-- 上传控件 -->
<script src="static/ace/js/ace/elements.fileinput.js"></script>
<!-- 编辑框-->
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "${APP_URL}plugins/ueditor/";</script>
<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
<!-- 编辑框-->
	

<script type="text/javascript">
		var ue = UE.getEditor('editor');
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
		if($("#bannerTitle").val()==""){
			 layer.tips('请输入标题', '#bannerTitle', {
				  tips: [1, '#3595CC'],
				  time: 4000
			 });
			return false;
		}
		if(typeof($("#tpz").val()) == "undefined"){
			if($("#tp").val()=="" || document.getElementById("tp").files[0] =='请选择图片'){
				 layer.tips('请选图片', '#tp', {
					  tips: [1, '#3595CC'],
					  time: 4000
				 });
				return false;
			}
		}
		$("#Form").submit();
	}
	
	//过滤类型
	function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.gif' && fileType != '.png' && fileType != '.jpg' && fileType != '.jpeg'){
	    	$("#tp").tips({
				side:3,
	            msg:'请上传图片格式的文件',
	            bg:'#AE81FF',
	            time:3
	        });
	    	$("#tp").val('');
	    	document.getElementById("tp").files[0] = '请选择图片';
	    }
	}
	
	//删除图片
	function delP(id){
		 if(confirm("确定要删除图片？")){
			var url = "banner/deltp.do?id="+id;
			$.get(url,function(data){
				if(data.code==0){
					alert("删除成功!");
					document.location.reload();
				}
			});
		} 
	}
</script>
</body>
</html>