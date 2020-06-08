<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<Map<String,Object>> memList = (List<Map<String,Object>>)request.getAttribute("memList");
%>
<table>
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>회원명</td>
	</tr>
	
	<%
		for(int i=0;i<memList.size();i++){
			Map<String,Object> rMap = memList.get(i);
	%>
		<tr>
			<td><%= rMap.get("mem_id") %></td>
			<td><%= rMap.get("mem_pw") %></td>
			<td><%= rMap.get("mem_name") %></td>
		</tr>
	<% 
		}
	%>
</table>