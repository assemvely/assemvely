<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<table>
	<tr>
		<th>bno</th>
		<th>title</th>
		<th>writedate</th>
	</tr>
 
  <c:forEach items="${LIST}" var="managervo">
  
   <tr>
  	<td>${managervo.managerbno}</td>
  
  	<td><a href="/manager/readposting?managerbno=${managervo.managerbno}">${managervo.title}</a></td>
  	<td>${managervo.writedate}</td>
   
  	 
  </tr>
  
  </c:forEach>

</table>
	
		 
		 
			
				<input type="button" value="글쓰기"/>
		 
		 
</body>
</html>
