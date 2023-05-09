
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
  
  <link rel="stylesheet" href="${cpath}/resources/css/style.css">
  
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>  
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  
  <script type="text/javascript">
  
  	$(document).ready(function(){
  		
  		console.log("ready")
  		
  		var pageForm = $("#pageForm");
  		$(".pagination a").on("click", function(e) {
	  	  	console.log(".pagination a on click")
  			e.preventDefault(); // a tag의 고유한 기능을 막는 방법
  			var page = $(this).attr("href");
     	 	  //location.href="${cpath}/list?page="+page+"&aaa=aaa"; / form 이용
  			$("#page").val(page);
  			pageForm.submit();
  	 	});
  			
 	});
  		
  
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
										<td> 제목 </td>
										<td> 작성일 </td>
										<td> 작성자 </td>
										<td> 사진 </td>
										<td> 조회수 </td>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="vo" items="${list}">
									
										<tr>
											<td> ${vo.title} </td>
											<td> <fmt:formatDate value= "${vo.pdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
											<td> ${vo.nick} </td>
											<td> <p> <img alt="이미지" height = 100px src="${cpath}/resources${vo.file}" ></p></td>
											<td> ${vo.view} </td>
										</tr>
								 		
									</c:forEach>

								</tbody>								
							</table>
							
              <!-- 페이징 리스트 출력 시작 -->
               <ul class="pagination justify-content-center">
                <c:if test="${pm.prev}">
                  <li class="page-item"><a class="page-link" href="${pm.startPage-1}">◀(Prev)</a></li>
                </c:if>
                <c:forEach var="pageNum" begin="${pm.startPage}" end="${pm.endPage}">
                  <li class="page-item ${pm.cri.page==pageNum ? 'active' : ''}"><a class="page-link" href="${pageNum}">${pageNum}</a></li>
                  
                  <!-- 
                  <li class="page-item ${pm.cri.page==pageNum ? 'active' : ''}"><a class="page-link" href="${cpath}/list?page=${pageNum}">${pageNum}</a></li>
                   -->                                                                                                             
                  
                </c:forEach>
                <c:if test="${pm.next}">
                  <li class="page-item"><a class="page-link" href="${pm.endPage+1}">▶(Next)</a></li>
                </c:if>
               </ul>
               
               <form id="pageForm" action="${cpath}/list" method="post">
                  <input type="hidden" id="page" name="page" value="${pm.cri.page}"/>
                 
                  <input type="hidden" id="type" name="type" value="${pm.cri.type}"/>
                  <input type="hidden" id="keyword" name="keyword" value="${pm.cri.keyword}"/>
               </form>
              <!-- 페이징 리스트 출력 끝 -->
							

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

</body>
</html>


