<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${query}-OmniAnswer</title>
<jsp:include page="/WEB-INF/jsp/include/html_head.jsp"></jsp:include>

<link href="./resources/css/search.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/result_header.jsp"></jsp:include>
	<div class=".container-fluid" style="margin-top: 120px">
		<div class="row">
			<div class="col-md-8 col-md-offset-2 label">
				<p class="text-left">
					<c:choose>
						<c:when test="${currentLanguage.shortName == 'en'}">Top Results For You :</c:when>
						<c:otherwise>最佳结果：</c:otherwise>
					</c:choose>
				</p>
			</div>

			<c:forEach var="result" items="${resultList}">
				<div class="col-md-8 col-md-offset-2 question">
					<div class="col-md-12 title">
						<a href="${result.url}" target="_blank">${result.title}</a>
					</div>
					<div class="col-md-12 vote" style="margin-top: 20px">
						<span> <c:choose>
								<c:when test="${currentLanguage.shortName == 'en'}">Votes: </c:when>
								<c:otherwise>赞同：</c:otherwise>
							</c:choose>
						</span><span>${result.votes}</span>
					</div>
					<div class="col-md-12 answer" style="margin-top: 5px">
						<p class="limitAnswer">
							<c:out value="${result.answer}" escapeXml="false" />
						</p>
					</div>

					<div class="col-md-12 source">
						<span class="sitename"><c:choose>
								<c:when test="${currentLanguage.shortName == 'en'}">From</c:when>
								<c:otherwise>来自</c:otherwise>
							</c:choose> ${result.source}</span>
					</div>
				</div>
			</c:forEach>
			<div class="col-md-8 col-md-offset-2 question"></div>
		</div>

		<div class="row footer">
			<div class="col-md-4 col-md-offset-4">
				<nav style="text-align: center">
					<ul class="pagination">
						<c:if test="${(currentPage-5>=1?currentPage-5:1)>1}">
							<li><a style="padding: 6px 8px;"
								href="./search.html?query=${queryEncode}&page=${(currentPage-5>=1?currentPage-5:1)-1}"
								aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if>
						<c:forEach var="i" begin="${currentPage-5>=1?currentPage-5:1}"
							end="${((currentPage-5>=1?currentPage-5:1)+9)<=totalPage?((currentPage-5>=1?currentPage-5:1)+9):totalPage}">
							<li><a style="padding: 6px 10px;" href="./search.html?query=${queryEncode}&page=${i}"
								<c:if test="${currentPage==i}">style="color:black"</c:if>>${i}${end}</a></li>
						</c:forEach>

						<c:if test="${(currentPage-5>=1?currentPage-5:1)+9<totalPage}">
							<li><a style="padding: 6px 8px;"
								href="./search.html?query=${queryEncode}&page=${(currentPage-5>=1?currentPage-5:1)+10}"
								aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>

	</div>

	<!-- researchTip template -->
	<%@ include file="/resources/template/searchTip.tpl"%>

	<script>
		require([ "./resources/js/search.js" ]);
	</script>
</body>
</html>