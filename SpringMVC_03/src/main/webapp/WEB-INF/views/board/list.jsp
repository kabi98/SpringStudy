
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    

<c:set var="cpath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"> 
  
  <link rel="stylesheet" href="${cpath}/resources/css/style.css">
  
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>  
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  
  <script type="text/javascript">
  
  	function goMsg(){
  		$("#myModal").modal("show");
  	}
  	
  </script>
  
</head>
<body>
 

  <div class="card">
    <div class="card-header">
		<div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1>Spring BOARD Project</h1>      
		    <p>Spring WEB, MVC, MySQL, jQuery(Ajax) JSON </p>
		  </div>
		</div>
		    	
    </div>
    
    <div class="card-body">
    	<div class = "row">
	    	<div class = "col-lg-2">
	    		<jsp:include page="left.jsp"/>
	    	</div>
	    	
	    	<div class = "col-lg-7">
		    	<div class = card>
	    	
		    		<div class = "card-body">
		    			<h4 class = "card-title">BOARD</h4>
		    			<p class = "card-text">게시판 리스트</p>
		    			
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<td> 번호 </td>
										<td> 제목 </td>
										<td> 작성자 </td>
										<td> 작성일 </td>
										<td> 아이디 </td>
										<td> 조회수 </td>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="vo" items="${list}">
									
										<tr>
											<td> ${vo.num} </td>
											
											<td>
											
												<c:if test = "${vo.blevel==0}" >
													<c:if test = "${vo.bdelete==0}">
														<a href="${cpath}/get?num=${vo.num}"> ${vo.title} </a>
													</c:if>
													<c:if test = "${vo.bdelete==1}">
														<a href="javascript:goMsg()"> 삭제된 게시물 입니다.</a>
													</c:if>
												</c:if>
											
												<c:if test = "${vo.blevel>0}" >
													<c:forEach begin="1" end="${vo.blevel}">
														<span style="padding-left: 20px"></span>
													</c:forEach>
													<!-- 
													<i class="bi bi-arrow-return-right"></i>
													<i class="bi bi-arrow-bar-down"></i> 
													-->
													
													<i class="bi bi-box-arrow-down-right"></i>
													
													<c:if test = "${vo.bdelete==0}">
														<a href="${cpath}/get?num=${vo.num}"> [RE] : ${vo.title} </a>
													</c:if>
													<c:if test = "${vo.bdelete==1}">
														<a href="javascript:goMsg()"> [RE] : 삭제된 게시물 입니다.</a>
													</c:if>
													
												</c:if>
												 
											</td>
											
											<td> ${vo.writer} </td>
											<td> <fmt:formatDate value= "${vo.indate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
											<td> ${vo.username} </td>
											<td> ${vo.count} </td>
										</tr>
								 		
									</c:forEach>
								</tbody>								
							</table>

							<c:if test="${!empty mvo}">							
								<button class = "btn btn-success btn-sm" onclick="location.href='${cpath}/register'"> 글쓰기 </button>
		    				</c:if>

		    		</div>
	    		</div>
	    		
	    	</div>
	    	
	    	<div class = "col-lg-3">
	    		<jsp:include page="right.jsp"/>
	    	</div>
    	</div>
    </div> 
    
    <div class="card-footer">
    	빅데이터 분석서비스 개발자 양성과정(문경수)
    </div>
    
  </div>

	<!-- The Modal -->
	<div class="modal fade" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">Message</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        <font color="red"> 삭제된 게시물 입니다 ^^ </font>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>
	<!-- The Modal -->

</body>
</html>


