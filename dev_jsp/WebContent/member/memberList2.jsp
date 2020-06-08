<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   		List<Map<String,Object>> memList = (List<Map<String,Object>>)request.getAttribute("memList");
  		if(memList==null) {
   			memList = new ArrayList<>();
   		} 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../common/jEasyUICommon.jsp" %>
<script type="text/javascript">
	function fun() {
		alert("fun호출");
	}
</script>
<title>회원목록</title>
</head>
<body>
회원 목록보기
<table id="dg_member" class="easyui-datagrid" data-options="toolbar:tbar">
    <thead>
        <tr>
            <th data-options ="field:'mem_id',width:120">아이디</th>
            <th data-options ="field:'mem_name',width:120">이름</th>
        </tr>
    </thead>
    <tbody>
<%
	if(memList.size()>0){//검색결과가 있을떄
		for(int i=0; i<memList.size(); i++) {
			Map<String,Object> rmap = memList.get(i);
%>
    <tr>
    	<td><%=rmap.get("MEM_ID") %></td>
    	<td><%=rmap.get("MEM_NAME") %></td>
    </tr>
<%
		}
	}else {//검색 결과가 없을 때
%>    
	<tr>
		<td colspan="2">목록이 없습니다</td>
	</tr>
<%
	}
%>
    </tbody>
    </table>
    <div id ="dl_add"></div>
    <script type="text/javascript">
	    var tbar = [{
	        text:'Add',
	        iconCls:'icon-add',
	        handler:function(){
	        		cmm_window_popup('memberIns.jsp','400','400','memberIns');
	        	}
	    },'-',{
	        text:'Cut',
	        iconCls:'icon-cut',
	        handler:function(){alert('cut')}
	    },'-',{
	        text:'Edit',
	        iconCls:'icon-save',
	        handler:function(){alert('edit')}
	    },'-',{
	        text:'Search',
	        iconCls:'icon-search',
	        handler:function(){
	        	//alert('save')
	        	//insert here
	        	$('#dl_memList').dialog({
					title:"회원관리",
				    width: 400,
				    height: 200,
				    closed: false,
				    cache: false,
				    href: '/member/memberList.mvc2?crud=memberList',
				    modal: true
				});
	        }
	    }];
    </script>
</body>
</html>