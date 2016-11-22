<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:choose>

		<c:when test="${not empty(READ)}">
			<ul>
				<li>타이틀:${READ.title}</li>
				<li>배너이미지:<img src="/resources/managerimg/${READ.managerimg} " alt="이미지가 없습니다"></li>
			
				<li>상세내용:${READ.posting}</li>
			 
	 
	 			 
			 	
<!-- " -->
		</c:when>
		<c:otherwise>
			<span class="failed">파일업로드 실패</span>
		</c:otherwise>
	</c:choose>
	
		<a href="/manager/deleteposting?managerbno={READ.managerbno}"><input type="button" id="save" value="삭제"/></a>
</body>
</html>