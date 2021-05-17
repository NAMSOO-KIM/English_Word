<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
  <head>
    <style>
      #columns{
        column-width:350px;
        column-gap: 15px;
      }
      #columns figure{
        display: inline-block;
        cursor: pointer;
        transition: all 0.5s ease-in;
        border:1px solid rgba(0,0,0,0.2);
        margin:0;
        margin-bottom: 15px;
        padding:10px;
        box-shadow: 2px 2px 5px rgba(0,0,0,0.5);;
      }
      
      #columns figure h2{
        width:100%;
        font-size: 150px;
      }
      
      #columns figure h3{
        border-top:5px solid rgba(0,0,0,0.2);
        padding:100px;
        margin-top:11px;
      }
      
      #columns figure img{
        width:100%;
      }
      
      #columns figure figcaption{
        border-top:1px solid rgba(0,0,0,0.2);
        padding:10px;
        margin-top:11px;
      }
    </style>

</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>


 <script>
  
 function button_event(element){

   var doc=document.getElementById("word_id");
   var word_id = doc.getAttribute("value");
     
   var result = confirm('나의 단어장에서 제거하시겠습니까?');
	  
   if (result == true){
     
     var params = {
                       id      : word_id
               }
               
               // ajax 통신
               $.ajax({
                   type : "POST",            // HTTP method type(GET, POST) 형식이다.
                   url : "/mvc/myplaceDeleteAction",      // 컨트롤러에서 대기중인 URL 주소이다.
                   data : params,            // Json 형식의 데이터이다.
                   success : function(res){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
               // 응답코드 > 0000
               alert(res.code);
           },
           error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
               alert("통신 실패.")
           }
       });
   }
   else{
     
   }
 }
</script>

  </head>
 
  <body>
  <%
	String user_id= (String)session.getAttribute("user_id");
	
   %>
    <c:forEach var="dto" items="${list}" varStatus="statusNum">
      <div id="columns">
        <figure id="figure" onclick="button_event(this);">
          <h2 id="question" >${dto.question}</h2>
          <figcaption id ="answer" >${dto.answer}</figcaption>
          <input type="hidden" id="word_id" value="${dto.id}"/>
        </figure>
      </div>
    </c:forEach>
    </body>
     
</html>