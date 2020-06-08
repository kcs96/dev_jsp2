<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <%@ include file="../common/jEasyUICommon.jsp" %>
   
</head>
<body>
<!-- 새글 일때 [list.jsp -> 글쓰기 버튼을 눌럿을때]  
새글과 댓글을 어떻게 구분하나요? bm_no 있다   없다로  if(bm_no >0) 댓글     -->
<form id="f_write">
			<input type="hidden" name="cud" value="INS">
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="bm_title" name="bm_title" label="글제목" data-options="prompt:'Enter a ENAME'" style="width:250px">
			</div>
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="bm_writer" name="bm_writer" label="작성자" data-options="prompt:'Enter a JOB'" style="width:250px">
			</div>
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="bm_email" name="bm_email" label="이메일" data-options="prompt:'Enter a 입사일자'" style="width:250px">
			</div>
			<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="bm_content" name="bm_content" label="content" data-options="prompt:'Enter a bm_content'" style="width:250px">
			</div>
		<div id="d_ins" style="margin-bottom:10px">
			<a id="btn_save" href="javascript:board_ins()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">저장</a> 
			<a id="btn_close" href="javascript:$('#dlg_ins').dialog('close')" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">닫기</a> 
		</div>
</form>
<!-- 댓글 일때  -->
<form id="f_reple">
	<input type="hidden" name="bm_title" value="<%=5 %>"/>
	<input type="hidden" name="bm_writer" value="<%=2 %>"/>
	<input type="hidden" name="bm_email" value="<%=0 %>"/>
</form>
</body>
</html>