
</html><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<title> 07 history </title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${cpath}/resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="card">
		<div class="card-header">
			<div class="jumbotron jumbotron-fluid">
				<div class="container">
					<h1> 07 history </h1>
				</div>
			</div>
		</div>
		<div class="card-body">
			<div class="row">
				<div class="col-lg-2">
					<jsp:include page="98_left.jsp" />
				</div>
				<div class="col-lg-7">
					<div class="card">
						<div class="card-header">
							<jsp:include page="96_menu_btn.jsp" />
						</div>
						
						<div class="card-body">
							<h4 class="card-title">히스토리 게시판</h4>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<td> 제목 </td>
										<td> 작성일 </td>
										<td> 작성자 </td>
										<td> 원본 </td>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="vo" items="${list_detect}">
									
										<tr>
											<td> ${vo.dd_comment} </td>
											<td> <fmt:formatDate value= "${vo.dd_date}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
											<td> ${vo.m_name} </td>
											<td> <p> <img alt="이미지" height = 100px src="${cpath}/resources${vo.org_img}" ></p></td>
										</tr>
								 		
									</c:forEach>

								</tbody>								
							</table>
							
							
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<jsp:include page="97_right.jsp" />
				</div>
			</div>
		</div>
		<div class="card-footer">권벤저스</div>
	</div>

</body>
</html>