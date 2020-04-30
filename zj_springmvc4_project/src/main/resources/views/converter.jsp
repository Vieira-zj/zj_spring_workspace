<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HttpMessageConverter Demo</title>
</head>
<body>
	<div id="resp"></div>
	<input type="button" onclick="req('application/x-zhengjin');" value="请求1" /><br>
	<input type="button" onclick="req('application/x-test');" value="请求2" />
	<script src="assets/js/jquery.js" type="text/javascript"></script>
	<script>
		function req(cType) {
			$.ajax({
				url : "convert",
				data : "1-zhengjin",
				type : "POST",
				contentType : cType,
				success : function(data) {
					$("#resp").html(data);
				}
			});
		}
	</script>
</body>
</html>