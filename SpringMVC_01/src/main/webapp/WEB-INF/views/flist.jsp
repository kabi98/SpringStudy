<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>flist.jsp</title>
</head>
<body>

<%
	List<String> list = (List<String>)request.getAttribute("list");
%>

과일 목록 

<table border = "1">
	<tr>
		<td> 과일 리스트 </td>
	</tr>
	
	<%
		for (String f : list) {
	%>
	
		<tr>
			<td> <%= f %></td>
		</tr>
	
	<%
		}
	%>

</table>

</body>
</html>