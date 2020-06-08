package com.mvc3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 1단계 : 리턴타입은 void였다.
 * 2단계 : 리턴타입은 String으로 바꾸어서 redirect와 forward를 분기
 * 3단계 : 리턴타입을 ModelAndView로 바꾸어서 유지 내용에 대한 부분
 *        까지도 포함시켜본다.(request scope에 대한 반영 필요)
 * 사용자 정의 클래스인 ModelAndView의 scope를 request로 설계해 본다.
 * 화면에 대한 이름(결정)도 추가할 수 있도록 지원해 본다.
 * 
 * 내가 설계한 POJO프레임워크는 req와 res에 의존적이다 | 아니다.
 */
public class ModelAndView {
	//다른곳에서 호출하려면 전변에 선언
	//인스턴스 변수로 호출할 수 있는 변수
	String viewName = null;
	HttpServletRequest	 req = null;
	HttpServletResponse  res = null;
	//생성자 : 변수의 끝
	//코드에 생성자를 활용하지않으면 고수가아니다.
	//역할 : 초기화
	//생성자 - 디폴트 생성자와 파라미터 생성자의 차이점에 대해 말할 수 있고 활용할 수 있다.(POJO F/W에 반영해 보기)
	public ModelAndView() {}
	//생성자 뭐를쓸지를 생각해봐야함.
	public ModelAndView(HttpServletRequest req,HttpServletResponse res) {
		this.req=req;
		this.res=res;
	}
	//member/memberList.jsp.jsp
	public void setViewName(String viewName) {
		this.viewName=viewName;
		//this.viewName = req.getContextPath()+"/WEB-INF/view/"+viewName+".jsp";
	}
	public String getViewName() {
		return viewName;
	}
	public void addObject(String name,Object obj) {
		req.setAttribute(name, obj);
	}
}
