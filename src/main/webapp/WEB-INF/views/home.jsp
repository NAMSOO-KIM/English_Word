<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="device-width",
  intitial-scale=1.0>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css" integrity="sha256-46r060N2LrChLLb5zowXQ72/iKKNiw/lAmygmHExk/o=" crossorigin="anonymous" />
  
<title>Home</title>

<style>

@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap');

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  background: #333;
  color: #fff;
  font-family: 'Roboto', sans-serif;
}

h2 {
  font-size: 45px;
  font-weight: 300;
  margin: 10px;
}

h2 span {
  font-size: 30px;
}

p {
  font-size: 20px;
}

.container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 5px;
}

.container > div {
  cursor: pointer;
  height: 210px;
  background-size: cover;
  background-attachment: fixed;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;

  transition: all 0.5s ease-in;
}

.container > div:hover {
  opacity: 0.7;
  transform: scale(0.98);
}

.container > div:nth-of-type(1) {
  grid-column: 1 / 3;
}

.container > div:nth-of-type(6) {
  grid-column: 3 / 5;
}

.container > div:nth-of-type(9) {
  grid-column: 3 / 5;
}

.container > div:nth-of-type(10) {
  grid-column: 1 / 3;
}


.bg2 {
  background: url('https://i.ibb.co/dBLbrRV/bg1.jpg');

}
</style>

<script>
</script>
</head>
<body>
<%
	String user_id= (String)session.getAttribute("user_id");
	String data_cnt = (String)session.getAttribute("word_result");
%>

<div class="container">
      <div class="bg2">
        <h2><%=user_id %> 님 <span></span></h2>
        
        
        <p>오늘도 화이팅</p>
      </div>
      <div class="bg2">
        <!-- <h2><i class="fas fa-battery-three-quarters"></i></h2> -->
        <h2 onclick="location.href='../exam'">단어 퀴즈</h2>
        
        
        
      </div>
      <div class="bg2">
        <!--<h2><i class="fas fa-running"></i></h2> -->
        <h2 onclick="location.href='mywordpage'">나만의 단어장</h2>
        
      </div>
      <div class="bg2">
        <h2>내 랭킹 </h2>
        <p> 1위 </p>
        
        <!-- <h2 onclick="location.href='ranking'">랭킹</h2> -->
      </div>
      <div class="bg2">
        <!--<h2><i class="fas fa-bed"></i></h2> -->
        <h2>맞은 문제</h2>
        <p>0개</p>
      </div>
      <div class="bg2">
        
        <h2 onclick="location.href='practice'">학습하기</h2>
        
        <!-- <h2 onclick="location.href='practice'">학습하기</h2> -->
        
      </div>
      <div class="bg2">
        <h2>단어 개수</span></h2>
        <p><%=data_cnt %></p>
      </div>
      <div class="bg2">
        <h2>총 학습일</span></h2>
        <p>1일</p>
      </div>
      <div class="bg2">
        <!--<a href="exam">틀린문제 확인</a> -->
        <h2 onclick="location.href='exam'">틀린문제 확인</h2>
        
      </div>
      <div class="bg2">
        <h2>마이 페이지</span></h2>
        
      </div>
      <div class="bg2">
        <h2 onclick="location.href='crawler'">단어 갱신</h2>
        
      </div>
      <div class="bg2">
        <h2>오늘의 단어장</h2>
        
      </div>
    </div>
</body>
</html>