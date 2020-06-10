<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map"%>    
<%
	List<Map<String,Object>> boardList = 
		(List<Map<String,Object>>)request.getAttribute("boardDetail");
	Map<String, Object> rMap = null; 
	if(boardList != null) {
		rMap = boardList.get(0);
	}
	String rbm_no  = null;
	String rbm_title  = null;
	String rbm_writer = null;
	String rbm_email  = null;
	String rbm_content = null;
	String rbm_group = null;
	String rbm_pos = null;
	String rbm_step = null;
	String rbm_pw = null;
	if(boardList != null && boardList.size()>0) {
		rbm_no = rMap.get("BM_NO").toString();
		rbm_title = rMap.get("BM_TITLE").toString();
		rbm_writer = rMap.get("BM_WRITER").toString();
		rbm_email = rMap.get("BM_EMAIL").toString();
		rbm_content = rMap.get("BM_CONTENT").toString();
		rbm_group = rMap.get("BM_GROUP").toString();
		rbm_pos = rMap.get("BM_POS").toString();
		rbm_step = rMap.get("BM_STEP").toString();
		rbm_pw = rMap.get("BM_PW").toString();
	}
	
%>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글상세보기</title>
<!-- 공통 코드 include처리 -->
<%@ include file="../common/jEasyUICommon.jsp" %>
<script type="text/javascript">
	function repleForm() {
		alert("댓글쓰기 클릭");
		$('#dlg_boardAdd').dialog('open');
	}
	function updateForm() {
		alert("수정 클릭");
	}
	function boardDelView() {
		alert("삭제 클릭");
	}
	function boardList() {
		alert("목록 클릭");
	}
	function addAction() {
		alert("저장 클릭");
		$('#f_boardAdd').attr('method','get');
		$('#f_boardAdd').attr('action','./boardINS.mvc3');
		$('#f_boardAdd').submit();
	}
</script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){

	});
</script>
    <table align="center" id="p" class="easyui-panel" title="글상세보기" data-options="footer:'#tb_read'"
        style="width:670px;height:380px;padding:10px;background:#fafafa;">
	    	<tr>
	    	<td>제목</td>
	    	<td><input id="bm_title" value="<%=rbm_title%>" name="bm_title" data-options="width:'450px'" class="easyui-textbox"></td>
	    	</tr>
	    	<tr>
	    	<td>작성자</td>
	    	<td><input id="bm_writer" value="<%=rbm_writer%>" name="bm_writer" class="easyui-textbox"></td>
	    	</tr>
	    	<tr>
	    	<td>이메일</td>
	    	<td><input id="bm_email" value="<%=rbm_email%>" name="bm_email" class="easyui-textbox"></td>
	    	</tr>
	    	<tr>
	    	<td>내용</td>
	    	<td><input id="bm_content" value="<%=rbm_content%>" name="bm_content" data-options="multiline:'true', width:'570px', height:'90px'" class="easyui-textbox"></td>
	    	</tr>
	    	<tr>
	    	<td>비밀번호</td>
	    	<td><input id="bm_pw" value="<%=rbm_pw%>" name="bm_pw" class="easyui-passwordbox"></td>
	    	</tr>	    	
	 </table>
	 <div id="tb_read" style="padding:2px 5px;" align="center">
	    <a href="javascript:repleForm()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">댓글쓰기</a>
	    <a href="javascript:updateForm()" class="easyui-linkbutton" iconCls="icon-add" plain="true">수정</a>
	    <a href="javascript:boardDelView()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">삭제</a>
	    <a href="javascript:boardList()" class="easyui-linkbutton" iconCls="icon-search" plain="true">목록</a>
	</div>
		<!-- 글삭제 시작 -->
		<div id="d_boardDel" closed="true" class="easyui-dialog" style="padding:20px 50px">
			<div id="btn_boardDel" align="right">
			<a href="javascript:boardDel()" class="easyui-linkbutton" iconCls="icon-ok" style="width:90px">확인</a>
			<a href="javascript:boardDelClose()" class="easyui-linkbutton" iconCls="icon-cancel" style="width:90px">닫기</a>
			</div>
		</div>
		<!-- 글삭제  끝   -->
		<!-- 글수정 시작 -->
		<div id="d_boardUpd" closed="true" class="easyui-dialog" style="padding:20px 50px">
			<div id="btn_boardUpd" align="right">
			<a href="javascript:boardUpd()" class="easyui-linkbutton" iconCls="icon-ok" style="width:90px">등록</a>
			<a href="javascript:boardUpdClose()" class="easyui-linkbutton" iconCls="icon-cancel" style="width:90px">닫기</a>
			</div>
		</div>
		<!-- 글수정  끝  -->
		<!-- 댓글쓰기 시작 -->
<!--================== [[댓글쓰기 화면]] ==================-->
<div id="dlg_boardAdd" 
     title="댓글쓰기" 
     class="easyui-dialog" 
     style="width:600px;height:400px;padding:10px" 
     data-options="closed:'true',modal:'true',footer:'#tbar_boardAdd'">	
<!-- 
form전송시 encType옵션이 추가되면 request객체로 사용자가 입력한 값을 꺼낼 수 없다.
MultipartRequest  => cos.jar
 -->	
	<form id="f_boardAdd" method="post" enctype="multipart/form-data">
		<input type="hidden" name="cud" value="INS">
		<input type="hidden" name="bm_no" value="<%=rbm_no%>">
		<input type="hidden" name="bm_group" value="<%=rbm_group%>">
		<input type="hidden" name="bm_pos" value="<%=rbm_pos%>">
		<input type="hidden" name="bm_step" value="<%=rbm_step%>">
	<!-- <form id="f_boardAdd"> -->
		<table>
			<tr>
				<td width="100px">제목</td>
				<td width="500px">
					<input class="easyui-textbox" 
					       data-options="width:'350px'" 
					       id="bm_title" name="bm_title" required>
				</td>
			</tr>
			<tr>	
				<td width="100px">작성자</td>
				<td width="500px">
					<input class="easyui-textbox" 
					       data-options="width:'150px'" 
					       id="bm_writer" name="bm_writer" required>
				</td>
			</tr>
			<tr>
				<td width="100px">이메일</td>
				<td width="500px">
					<input class="easyui-textbox" 
					       data-options="width:'250px'" 
					       id="bm_email" name="bm_email">
				</td>
			</tr>
			<tr>			
				<td width="100px">내용</td>
				<td width="500px">
					<input class="easyui-textbox" 
					       id="bm_content" name="bm_content" 
					       data-options="multiline:'true',width:'400px',height:'90px'" required>
				</td>
			</tr>
			<tr>
				<td width="100px">비번</td>
				<td width="500px">
					<input class="easyui-textbox" 
					       data-options="width:'100px'" 
					       id="bm_pw" name="bm_pw" required>
				</td>
			</tr>
		</table>
	</form>
</div>
<!-- 입력 화면 버튼 추가 -->
<div id="tbar_boardAdd" align="right">
	<a href="javascript:addAction()" class="easyui-linkbutton" iconCls="icon-save">저장</a>
	<a href="javascript:$('#dlg_boardAdd').dialog('close')" 
	   class="easyui-linkbutton" iconCls="icon-cancel">닫기</a>
</div>
		<!-- 댓글쓰기  끝  -->	    
</body>
</html>