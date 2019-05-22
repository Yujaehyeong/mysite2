<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.contents, newline, "<br>")}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
				<a href="${pageContext.servletContext.contextPath}/board/list">글목록</a>
				<c:choose>
					<c:when test="${authUser !=null && vo.userNo == authUser.no}">
						<a href="${pageContext.servletContext.contextPath}/board/modify?no=${vo.no}">글수정</a>
					</c:when>
					<c:when test="${authUser !=null && vo.userNo != authUser.no}">
						<a href="${pageContext.servletContext.contextPath}/board/write?no=${vo.no}&
						groupNo=${vo.groupNo}&orderNo=${vo.orderNo}&depth=${vo.depth}">댓글달기</a>
					</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>