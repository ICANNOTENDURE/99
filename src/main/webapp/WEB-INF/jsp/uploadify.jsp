<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Java API for WebSocket (JSR-356)</title> 
	<script src="./../static/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="./../plugins/uploadify3.2.1/jquery.uploadify.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="./../plugins/uploadify3.2.1/uploadify.css">
</head>
<body>
	<h1>Uploadify Demo</h1>
	<form>
		<div id="queue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
	</form>

	<script type="text/javascript">
		$(function() {
			$('#file_upload').uploadify({
				'swf'      : './../plugins/uploadify3.2.1/uploadify.swf',
				'uploader' : './../apppic/upload.do'
			});
		});
	</script>	
</body>
</html>