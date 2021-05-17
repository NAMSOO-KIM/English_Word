<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
 <style>
.container {
	width: 385px;
	line-height: 50px;
	/* margin: 40px auto; */
	margin: 40px auto;
}

h5 {
	text-align: center;
}

h5 span {
	color: teal;
}

.login {
	background-color: rgb(255, 80, 90);
	color: white;
	border-radius: 5px;
	border: 0;
	padding: 10px 172px;
}

#signup {
	background-color: white;
	color: teal;
	border: 0;
	font-size: 17px;
}

p {
	text-align: center;
}

i {
	color: lightgray;
}


#imail {
	position: absolute;
	top: 115px;
	margin: 0 355px;
}



#ipw {
	position: absolute;
	top: 165px;
	margin: 0 355px;
}

input {
	border: 1px solid lightgray;
	border-radius: 3px;
}
</style>

</head>
<body>

<div class="container">
        <div id="imail">
            <i class="material-icons">person_outline</i>
        </div>
        <div id="ipw">
            <i class="material-icons">lock_outline</i>
        </div>
        	<h5><span>로그인</span> 페이지입니다.</h5>
        <hr />
        <form action="login" method="post">
            <input type="text" placeholder="아이디" name="user_id" required style="height:30px; width: 380px" /><br />
            <input type="password" placeholder="비밀번호" name="password" required style="height:30px; width: 380px" /><br />
            <input type="submit" value="로그인" class="login" />
           
            <!-- <button onclick="location.href='home.do';" class="login" >HOME</button> -->
        </form>
        <hr />
        <p><a href="register"><input type="button" value="회원가입" id="signup" /></a></p>
    </div>







<!--  
<form action="loginAction" method="post">
<table>

<caption>로그인</caption>
<tr>
	<th>아이디</th>
	<td><input type="text" name="title" autofocus="autofocus" required="required"/></td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="text" name="name" required="required"/></td>
</tr>


<tr>
	<td colspan="2" align="center"><input type="submit" value="완료" /></td>
</tr>
</table>
-->
<!-- </form> -->
</body>
</html>