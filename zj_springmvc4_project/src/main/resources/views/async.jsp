<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>servlet async support</title>
</head>
<body>
	<script src="assets/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		deferred();
		
		function deferred() {
			$.get('defer', function(data) {
				console.log(data);
				deferred();
			});
		}
	</script>
</body>
</html>