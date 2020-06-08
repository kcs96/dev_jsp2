<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/jEasyUICommon.jsp" %>
<script type="text/javascript">
	//저장버튼눌럿을때 꺼진다. 
	function save() {
		//부모창을 열어줘야함 
		alert("memAdd호출 성공");
		//opener.location.href="javascript:fun()";
		$("#f_ins").attr("method" ,"get");
		$("#f_ins").attr("action","./member.mvc2");
		$("#f_ins").submit();
		$(opener.location).attr("href","javascript:fun()");
		self.close();
	}
</script>
</head>
<body>
회원가입 페이지
		<div id="dlg_ins" data-options="closed:false, title:'회원정보 등록',footer:'#d_ins', modal:'true'" class="easyui-dialog" style="width:100%;max-width:480px;padding:30px 60px">
		<form id="f_ins"> 
		<input type="hidden" name="crud" value="memberAdd">
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="mem_id" name="mem_id" label="아이디" data-options="prompt:'Enter a ID'" style="width:150px">
			</div>
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="mem_name" name="mem_name" label="이름" data-options="prompt:'Enter a name'" style="width:250px">
			</div>
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="mem_pw" name="mem_pw" label="비밀번호" data-options="prompt:'Enter a pw'" style="width:150px">
			</div>
		</form>
			<div id="d_ins" style="margin-bottom:10px">
				<a id="btn_save"  href="javascript:save()" class= "easyui-linkbutton" data-options="iconCls:'icon-save'">저장</a>
				<a id="btn_close" href="javascript:self.close()" class= "easyui-linkbutton" data-options="iconCls:'icon-cancel'">닫기</a>
			</div>
	</div>
</body>
</html>