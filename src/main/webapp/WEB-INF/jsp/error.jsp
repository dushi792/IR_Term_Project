<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Omni Answer</title>
<jsp:include page="/WEB-INF/jsp/include/html_head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/error_header.jsp"></jsp:include>

	<div id="container" class=".container-fluid">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<h2>Sorry, we have an error.</h2>
				<h3>Error code: ${errorCode}</h3>
				<h3>${errorMessage}</h3>
				<br/>
				<h4><a href="./index.html">Back to homepage</a></h4>
			</div>
		</div>

	</div>

</body>
</html>
