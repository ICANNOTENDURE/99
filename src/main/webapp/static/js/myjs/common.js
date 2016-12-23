
function ismail(mail){
	return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
}
function commonClose(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭 
}
//去发送电子邮件页面
function sendEmail(EMAIL){
	 commonLayer({ 
			title: '发送电子邮件',
			area: ['660px', '520px'],
			content: 'head/goSendEmail.do?EMAIL='+EMAIL+'&msg=appuser'
	})
}

//去发送短信页面
function sendSms(phone){
	commonLayer({ 
			title: '发送短信',
			area: ['660px', '365px'],
			content: 'head/goSendSms.do?PHONE='+phone+'&msg=appuser'
	})
}
function commonAlert(msg){
	top.layer.alert(msg);
}
function commonLayer(_options){
	
	options={
		  type: 2,
		  shade: 0.8	
	}
	opts=$.extend(true,options, _options);
	top.layer.open(opts);
}
function commonLoad(){
	index = top.layer.load(1, {shade: [0.5,'#fff']});
	return index;
}
function commonLoadClose(index){
	top.layer.close(index)
}
function commonDelete(url){
	top.layer.confirm("确认删除吗？",function(index) {
		top.layer.close(index);
		index=commonLoad();
		$.post(
			url,
			function(data){
				commonLoadClose(index)
				if(data.code==0){
					searchs();
				}else{
					commonAlert("失败:"+data.message)
				}
				
			},'json'
		);
	})
}
function commonDeleteAll(url){
	
	ids=[];
	$("[name = ids]:checkbox").each(function () {
        if ($(this).is(":checked")) {
        	ids.push($(this).attr("value"));
        }
    });
	if(ids.length==0){
		top.layer.alert("<span class='bigger-110'>您没有选择任何内容!</span>");
		$("#zcheckbox").tips({
			side:3,
            msg:'点这里全选',
            bg:'#AE81FF',
            time:8
        });
		return;
	}
	top.layer.confirm("确认删除吗？",function(index) {
		top.layer.close(index);
		index=commonLoad();
		$.post(
			url+ids.join(","),
			function(data){
				commonLoadClose(index)
				searchs();
			},'json'
		);
	})
}
//批量操作
function commonBath(msg,url){
	
	ids=[];
	emstr=[];
	phones=[]
	$("[name = ids]:checkbox").each(function () {
        if ($(this).is(":checked")) {
        	ids.push($(this).attr("value"));
        	emstr.push($(this).attr("id"));
        	phones.push($(this).attr("alt"));
        }
    });
	if(ids.length==0){
		top.layer.alert("<span class='bigger-110'>您没有选择任何内容!</span>");
		$("#zcheckbox").tips({
			side:3,
            msg:'点这里全选',
            bg:'#AE81FF',
            time:8
        });
		return;
	}
	url=url+"/deleteAll.do";
	top.layer.confirm(msg,function(index) {
			   top.layer.close(index)	
			   if(msg == '确定要删除选中的数据吗?'){
					index=commonLoad();
					$.ajax({
						type: "POST",
						url: url,
				    	data: {ids:ids.join(",")},
						dataType:'json',
						cache: false,
						success: function(data){
							commonLoadClose(index);
							searchs();
						}
					});
				}else if(msg == '确定要给选中的用户发送邮件吗?'){
					sendEmail(emstr.join(";"));
				}else if(msg == '确定要给选中的用户发送短信吗?'){
					sendSms(phones.join(";"));
				}
	});
}

$(function() {
	
	/**bootstrap select2**/
	$.fn.commonSelect=function(_options){
		
		options=
		{  
		   language : 'zh-CN',
  		   placeholder: "请选择",
  		   allowClear: true
		}
		ajaxOption={
			ajax: {
			url:_options.url,   
			dataType: "json",     
		    processResults: function (data, page) {
		      return {
		        results: data
		      };
		    },
		    data: function (params) {
		      return {
		        search: params.term
		      };
		    },
		    cache:false
		  }	
		}
		var opts = $.extend({},options, _options);
		opts=((_options.url==undefined)?opts:$.extend({},opts, ajaxOption))
		$(this).select2(opts);
	}
	
	
	//复选框全选控制
	var active_class = 'active';
	$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
		var th_checked = this.checked;//checkbox inside "TH" table header
		$(this).closest('table').find('tbody > tr').each(function(){
			var row = this;
			if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
			else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
		});
	});
});


