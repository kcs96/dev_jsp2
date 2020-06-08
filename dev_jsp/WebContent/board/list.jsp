<%@page import="java.util.Map,java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
//자바영역 - 서버에서 처리된 결과가 html코드에 합쳐져서 클라이언트 측으로 다운로드 되는것임
//이미 모든 값이 결정된 상태임-변경불가함. -정적임
	List<Map<String,Object>> boardList = (List<Map<String,Object>>)request.getAttribute("boardList");
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>아이</title>
<!-- 공통코드 영역 (화면공통코드, 인증처리) -->
   <%@ include file="../common/jEasyUICommon.jsp" %>
<script type="text/javascript">

	//다이얼로그에 저장버튼 눌렀을때
	function board_ins(){
		alert("저장 호출 성공");
		$("#f_write").attr("method","get");
		$("#f_write").attr("action","./boardINS.mvc3");
		$("#f_write").submit();
	}
	
	function boardList(){
		location.href = "./boardList.mvc3?cud=SEL";
		$.ajax({
			 url:"./boardList.mvc3?cud=SEL"
		})
	}
	function writeForm(){
		$('#dlg_write').dialog({
		    title: '글쓰기 화면',
		    width: 600,
		    height: 400,
		    closed: false,
		    cache: false,
		    href: 'writeForm.jsp',
		    modal: true
		});
	}
</script>
</head>
<body>
	 <table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"
            url="get_users.php"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
               <tr>
                <th field="bm_no" width="50">번호</th>
                <th field="bm_title" width="250">글제목</th>
                <th field="bm_writer" width="100">작성자</th>
                <th field="bm_file" width="120">첨부파일</th>
                <th field="bm_hit" width="90">조회수</th>
            </tr>
        </thead>
<% 
	if(boardList!=null){
%>
		<tbody>
<% 
		for(int i=0; i< boardList.size();i++){
			Map<String,Object>  rmap = boardList.get(i);
%>
			<tr>
				<td><%=rmap.get("BM_NO") %></td>
				<td><%=rmap.get("BM_TITLE") %></td>
				<td><%=rmap.get("BM_WRITER") %></td>
				<td><%=rmap.get("BM_FILE") %></td>
				<td><%=rmap.get("BM_HIT") %></td>
			</tr>
<%
		} //end of for문
%>
		</tbody>
<%
	}//end of if문
%>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="writeForm()">글쓰기</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="boardList()">조회</a>
    </div>
    <!-- list.jsp:미경, writeForm.jsp:동광  -->
    <!--================[[ 글쓰기 화면 처리 시작 ]] =========================-->
    <div id="dlg_write" class="easyui-dialog" data-options="closed:true" title="My Dialog" style="width:400px;height:200px;">
    	<!-- 동광 코드를 머지  -->
    </div>
    <!--================[[ 글쓰기 화면 처리     끝 ]] =========================-->
</body>
</html>