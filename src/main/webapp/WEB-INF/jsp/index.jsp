<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Omni Answer</title>
<jsp:include page="/WEB-INF/jsp/include/html_head.jsp"></jsp:include>

<link href="./resources/css/index.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/index_header.jsp"></jsp:include>

	<div id="container" class=".container-fluid">
		<%-- <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include> --%>

		<div class="row" style="margin-top: 50px">
			<div class="col-md-4 col-md-offset-4">
				<!-- <h1 class="text-primary text-center">Zhihu overflow</h1> -->
				<img src="./resources/img/logo.png" class="img-responsive center-block" alt="logo">

			</div>
			<div class="col-xs-1 col-sm-2 col-md-3"></div>
		</div>
		<div class="row" style="margin-top: 40px">
			<div
				class="col-xs-10 col-sm-8 col-md-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 input-group">
				<input id="inputBox" type="text" class="form-control input-lg" />
				<div class="input-group-btn">
					<button id="searchButton" type="button" class="btn btn-primary btn-lg">
						<c:choose>
							<c:when test="${currentLanguage.shortName == 'en'}">Search</c:when>
							<c:otherwise>&nbsp;搜索&nbsp;</c:otherwise>
						</c:choose>
					</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1 col-sm-2 col-md-3"></div>
			<div class="col-xs-10 col-sm-8 col-md-6 input-group dropdown">
				<ul id="searchTip" class="dropdown-menu">
					<li><a href="#">HTML</a></li>
					<li><a href="#">CSS</a></li>
					<li><a href="#">JavaScript</a></li>
				</ul>
			</div>
			<div class="col-xs-1 col-sm-2 col-md-3"></div>
		</div>
	</div>

	<!-- researchTip template -->
	<%@ include file="/resources/template/searchTip.tpl"%>
	<script>
		require([ './resources/js/index.js' ]);
	</script>
</body>
</html>
