package com.mvc3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.mvc2.MemberController;

public class ActionSupport extends HttpServlet {
	Logger logger = Logger.getLogger(ActionSupport.class);
	
	public void doService(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		logger.info("doService 호출 성공");
		
		String requestURI =req.getRequestURI();
		logger.info("requestURI :"+requestURI);   // ==> /member/memberList.mvc2
		
		String contextPath = req.getContextPath();
		logger.info("contextPath :"+contextPath);
		
		String command = requestURI.substring(contextPath.length()+1);  // ==> member/memberList.mvc2
		logger.info("before command :"+command);
		
		int end = command.lastIndexOf(".");
		command = command.substring(0,end);
		logger.info("after command :"+command);   // ==> member/memberList
		
		Controller2020 controller = null;
		String cud = req.getParameter("cud");
		logger.info("cud ==>"+cud);
		try {
			controller = ControllerMapper3.getController(command);
		} catch (Exception e) {
			System.out.println("예외상황발생 컨트롤러 매핑 실패");
		}
		logger.info("controller ==>"+controller);
		//값을 꺼내는 시점일때는 널체크를 해줘야한다. nullpointException 대처방안
		
		if(controller!=null) {
			String pageMove[] = null;
			try {
				//Object에 오는 타입이 두가지 이다.
				//1. ModelAndView
				//2. String
				Object robj = null;
				if(cud==null) { //ModelAndView로 가
					logger.info("cud가 null일때로 처리");
					robj = controller.process(command, req,res);
				}else { //입력이야? 수정이야? 삭제하려구해
					logger.info("cud가 null이 아닐때로 처리");
					robj = controller.process(req,res);
				}
				//ModelAndView 인지 String 인지 찍어보자.
				logger.info("robj ==>"+robj);
				if(robj instanceof String) {
					pageMove = robj.toString().split(":");
					logger.info("pageMove[0] ==> "+pageMove[0]+"pageMove[1] ==>"+pageMove[1]);
				}else if(robj instanceof ModelAndView) {
					ModelAndView mav = (ModelAndView)robj;
					pageMove = new String[2];
					pageMove[0] = "forward";
					pageMove[1] = mav.getViewName();
				}
			} catch (Exception e) {
				logger.info("Exception : "+e.toString());
			}
			//insert here - redirect인 경우와 forward인 경우를 쪼개기
			//힌트 : return "redirect:member/memberList.mvc3
			if(pageMove!=null) {
				String path = pageMove[1];
				if("redirect".equals(pageMove[0])) {//너 redirect 할꺼야?
					res.sendRedirect(path);
				}else{//너 forward 할꺼야?
					if("forward".equals(pageMove[0])) {
						RequestDispatcher view = req.getRequestDispatcher(path);
						view.forward(req, res);
					}else {
						res.sendRedirect("/error/pageMoveFail.jsp");
					}
				}
			}
		}/////////////////end of Controller가 널이 아닐때 ///////////////////////
	}//////////////////////end of doService/////////////////////////////
	
	@Override
	public void doGet(HttpServletRequest req,
			 	      HttpServletResponse res) throws ServletException, IOException {
		logger.info("doGet 호출 성공");
		doService(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req,
	 	      HttpServletResponse res) throws ServletException, IOException {
		logger.info("doPost 호출 성공");
		doService(req,res);
	}
}
