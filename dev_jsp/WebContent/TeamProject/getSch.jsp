<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String names= request.getParameter("names");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세일정</title>
</head>
<body>
<h2>상세일정</h2>
<p>일정 명</p>
<input type="text" width=400 value=<%= names %>>
<p>작성자</p>
<input type="text" width=400>
<p>시작 시간</p>
<input type="text" width=400>
<p>종료 시간</p>
<input type="text" width=400>
<p>내용</p>
<input type="text" width=400>
<table>
<tr>
	<td><button>추가</button></td><td><button>닫기</button></td>
</tr>
</table>
</body>
</html>