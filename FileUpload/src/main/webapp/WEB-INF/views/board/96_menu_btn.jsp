<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />

<div class="card">
							<div class="btn-group btn-group-lg" role="group"
								aria-label="Large button group">
								<button type="button" class="btn btn-outline-dark" onClick="location.href='./03'">질병감지</button>
								<button type="button" class="btn btn-outline-dark" onClick="location.href='./community'">정보공유</button>
								<button type="button" class="btn btn-outline-dark" onClick="location.href='./04'">질병검색</button>
								<button type="button" class="btn btn-outline-dark" onClick="location.href='./history'">히스토리</button>
							</div>
</div>