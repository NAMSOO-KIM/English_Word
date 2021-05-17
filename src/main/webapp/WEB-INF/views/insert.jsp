<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="insertAction" method="post">
<table>
<caption>단어 등록</caption>
<tr>
	<th>단어</th>
	<td><input type="text" name="title" autofocus="autofocus" required="required"/></td>
</tr>
<tr>
	<th>뜻</th>
	<td><input type="text" name="name" required="required"/></td>
</tr>
<tr>
	<th>단어</th>
	<td><input type="text" name="title" autofocus="autofocus" required="required"/></td>
</tr>
<tr>
	<th>뜻</th>
	<td><input type="text" name="name" required="required"/></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="완료" /></td>
</tr>
</table>
</form>
</body>
</html>