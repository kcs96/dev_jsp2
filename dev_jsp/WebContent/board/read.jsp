<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//list.jsp는 n건이지만 read.jsp는 상세보기니까 1건
	List<Map<String,Object>> boardList = (List<Map<String,Object>>)request.getAttribute("boardList");
	int size = 0;
	Map<String,Object> rmap = boardList.get(0);
	if(boardList!=null){
	  size =boardList.size();
	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>