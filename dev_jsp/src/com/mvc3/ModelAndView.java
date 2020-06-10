package com.mvc3;
/* 
 * 1단계 : 리턴 타입은 void였다.
 * 2단계 : 리턴 타입을 String으로 바꾸어서 redirect와 foward를 분기
 * 3단계 : 리턴 타입을 ModelAndView로 바꾸어서 유지 내용에 대한 부분
 * 		까지도 포함시켜본다.(request scope에 대한 반영 필요)
 * - 사용자 정의 클래스인 ModelAndView의 scope를 request로 설계 해 본다.
 * - 화면에 대한 이름(결정)도 추가 할 수 있도록 지원해본다.
 * req, res
 * 
 * 내가 설계한 pojo F/W은 req와 res의 의존적이다.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView {
	//전역변수
	HttpServletRequest  req = null;
	HttpServletResponse res = null;
	String viewName = null;
	StringBuilder path = null;
	
	//생성자 - 디폴트 생성자와 파라미터 생성자의 차이점에 대해 말할 수 있고 활용 할 수 있다.
	//      (pojo 프레임워크에 반영 해보기)
	public ModelAndView() {}
	public ModelAndView(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	public void addObject(String name, Object obj) {
		req.setAttribute(name, obj);
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
		//this.viewName = req.getContextPath()+"/WEB-INF/view/"+viewName+".jsp";
	}
	public String getViewName() {
		return this.viewName;
	}
	
}
