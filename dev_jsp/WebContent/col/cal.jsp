 <%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <title>일정</title>
  <%@ include file="../common/jEasyUICommon.jsp" %>
  <script type="text/javascript">
  	function insert(){
  		cmm_window_popup('addSch.jsp','400','400','addSch');

  	}
  	function getSchedule(){
  		var names=$("#sp").text();
  		cmm_window_popup('getSch.jsp?names='+names,'400','400','getSch');
  	}
  </script>
 </head>
 <body>
  <%
  Calendar cal=Calendar.getInstance(); //Calendar객체 cal생성
  int currentYear=cal.get(Calendar.YEAR); //현재 날짜 기억
  int currentMonth=cal.get(Calendar.MONTH);
  int currentDate=cal.get(Calendar.DATE);
  String Year=request.getParameter("year"); //나타내고자 하는 날짜
  String Month=request.getParameter("month");
  int year, month;
  if(Year == null && Month == null){ //처음 호출했을 때
  	year=currentYear;
  	month=currentMonth;
  }
  else { //나타내고자 하는 날짜를 숫자로 변환
  	 year=Integer.parseInt(Year);
  	 month=Integer.parseInt(Month);
  	 if(month<0) { month=11; year=year-1; } //1월부터 12월까지 범위 지정.
  	 if(month>11) { month=0; year=year+1; }
  }
  %>
  <table> <!-- 달력 상단 부분, 더 좋은 방법이 없을까? -->
   <tr>
    <td align=left width=200> <!-- 년 도-->
    	<button onClick="javascript:insert()">일정 등록</button>
    </td>
    <td align=center width=300> <!-- 월 -->
    <a href="calendar.jsp?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a>
    <font size=18><% out.print(month+1); %>月</font>
    <a href="calendar.jsp?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a>
    </td>
    <td align=right width=200><% out.print("today : "+currentYear + "-" + (currentMonth+1) + "-" + currentDate); %></td>
   </tr>
  </table>
  <table border=1 cellspacing=0> <!-- 달력 부분 -->
   <tr>
    <td width=100 align=center bgcolor="#D5D5D5" style="color:#FF4500;">일</td> <!-- 일=1 -->
    <td width=100 align=center bgcolor="#D5D5D5">월</td> <!-- 월=2 -->
    <td width=100 align=center bgcolor="#D5D5D5">화</td> <!-- 화=3 -->
    <td width=100 align=center bgcolor="#D5D5D5">수</td> <!-- 수=4 -->
    <td width=100 align=center bgcolor="#D5D5D5">목</td> <!-- 목=5 -->
    <td width=100 align=center bgcolor="#D5D5D5">금</td> <!-- 금=6 -->
    <td width=100 align=center bgcolor="#D5D5D5" style="color:#0100FF;">토</td> <!-- 토=7 -->
   </tr>
   <tr height=70>
   <%
   cal.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
   int startDay=cal.get(Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
   int end=cal.getActualMaximum(Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
   int br=0; //7일마다 줄 바꾸기
   for(int i=0; i<(startDay-1); i++) { //빈칸출력
    out.println("<td>&nbsp;</td>");
    br++;
    if((br%7)==0) {
     out.println("<br>");
    }
   }
   for(int i=1; i<=end; i++) { //날짜출력
    if(br%7==6){
    	 out.println("<td  align=right Valign=top style='color:#0100FF;'>" + i + "<br>");
	   }
    else if(br%7==0){
    	out.println("<td  align=right Valign=top style='color:#FF4500;'>" + i + "</br>");
    }
    else{   
	    if(year==currentYear && month==currentMonth && i==currentDate) {
  			out.println("<td  align=right Valign=top style='border:3px solid red;'>" + i + "<br>");
		}else{
  			out.println("<td  align=right Valign=top>" + i + "<br>");

}
  }
   //디비에서 조회온 값을 가져왔을 때.
    if(year==2020 && month+1==6 && i==2) {
        out.println("<div style='background-color:#CEFBC9;' onClick='javascript:getSchedule()'><span id='sp'>"+"db제목"+"</span></div>"); 
       }
    out.println("</td>");
    br++;
    if((br%7)==0 && i!=end) {
     out.println("</tr><tr height=70>");
    }
   }
   while((br++)%7!=0) //말일 이후 빈칸출력
    out.println("<td>&nbsp;</td>");
   %>
   </tr>
  </table>
 </body>
</html>
