<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
</head>
<body>


	<table class="table" border = "1">
		<thead>
			<tr>
				<td> 번호 </td>
				<td> 제목 </td>
				<td> 작가 </td>
				<td> 출판사 </td>
				<td> ISBN </td>
				<td> 보유도서수 </td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="vo" items="${list}">
			
				<tr>
					<td> ${vo.num} </td>
					<td> ${vo.title} </td>
					<td> ${vo.author} </td>
					<td> ${vo.company} </td>
					<td> ${vo.isbn} </td>
					<td> ${vo.count} </td>
				</tr>
				
		 		
			</c:forEach>
		</tbody>								
	</table>


</body>
</html>