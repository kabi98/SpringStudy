<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>01 login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<link rel="stylesheet" href="${cpath}/resources/css/style.css">

<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>로그인 페이지</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
  <style>
   body {
    background-image: url("./resources/images/01_back.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 120vh;
    padding: 50px;
  }

  .card {
    width: 400px;
    border: none;
    border-radius: 10px;
    background-color: #fff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  }

  .card-body {
    padding: 20px;
  }

  .card-title {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    color: black;
  }

  .card-text {
    text-align: center;
    margin-top: 10px;
    font-size: 16px;
    color: black;
  }

  .form-group label {
    font-weight: bold;
    color: black;
  }

  .form-control {
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  .btn-primary {
    width: 100%;
    margin-top: 20px;
    border-radius: 5px;
    background-color: #000a2e;
    color: white;
    font-weight: bold;
    border: 1px solid white;
  }
   .btn-primary:hover {
    background-color: none;
    cursor: pointer;
  }
  </style>
</head>

<body>
  <div class="container">
    <div class="card">
      <div class="card-body">
        <c:if test="${empty mvo}">
          <h4 class="card-title">GUEST</h4>
          <p class="card-text">회원님, 환영합니다.</p>
          <form action="${cpath}/login" method="post">
            <div class="form-group">
              <label for="username">아이디:</label>
              <input type="text" name="m_email" class="form-control" id="username">
            </div>
            <div class="form-group">
              <label for="password">비밀번호:</label>
              <input type="password" name="m_pw" class="form-control" id="password">
            </div>
            <button class="btn btn-primary">로그인</button>
            <button class="btn btn-primary">회원가입</button>
          </form>
        </c:if>
        <c:if test="${!empty mvo}">
          <h4 class="card-title">${mvo.m_name}</h4>
          <p class="card-text">회원님, 환영합니다.</p>
          <form action="${cpath}/logout" method="post">
            <button class="btn btn-primary">로그아웃</button>
            
          </form>
        </c:if>
      </div>
    </div>
  </div>
</body>

</html>



