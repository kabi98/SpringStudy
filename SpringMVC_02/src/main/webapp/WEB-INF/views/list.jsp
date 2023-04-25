
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
</head>
<body>


	<table class="table" border = "1">
		<thead>
			<tr>
				<td> 번호 </td>
				<td> 제목 </td>
				<td> 작성자 </td>
				<td> 작성일 </td>
				<td> 조회수 </td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="vo" items="${list}">
			
				<tr>
					<td> ${vo.num} </td>
					<td> ${vo.title} </td>
					<td> ${vo.writer} </td>
					<td> ${vo.indate} </td>
					<td> ${vo.count} </td>
				</tr>
		 		
			</c:forEach>
		</tbody>								
	</table>

</body>
</html>


