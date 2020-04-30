<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSE Demo</title>
</head>
<body>
	<div id="msgFromPush"></div>
	<script src="assets/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		if (window.EventSource) {
			var source = new EventSource('push');
			var s = "";
			source.addEventListener('message', function(e) {
				console.log("message:", e.data);
				s += e.data + "<br>";
				$("#msgFromPush").html(s);
			});

			source.addEventListener('open', function(e) {
				console.log("连接打开");
			}, false);

			source.addEventListener('error', function(e) {
				if (e.readyState = EventSource.CLOSED) {
					console.log("连接关闭");
				} else {
					console.log(e.readyState);
				}
			}, false);
		} else {
			console.log("浏览器不支持SSE");
		}
	</script>
</body>
</html>