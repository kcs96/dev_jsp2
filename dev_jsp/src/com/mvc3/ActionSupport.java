package com.mvc3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class ActionSupport extends HttpServlet {
	Logger logger = Logger.getLogger(ActionSupport.class);
//doService
	public void doService(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		logger.info("doService() 호출 성공");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length()+1);

		String requestName = null;
		int end = command.lastIndexOf(".");
		requestName = command.substring(0, end);
		//String webinfPath = req.getContextPath()+"/view/";
		String cud = req.getParameter("cud");
		logger.info("command : "+command);              
		logger.info("cud : "+cud);              
		logger.info("requestName : "+requestName);
		
		Controller3 controller = null;
		try {   
			controller = ControllerMapper3.getController(command);
		} catch (Exception e) {
			e.printStackTrace();
		}  		
		if(controller != null) {
			String pageMove[] = null;
			try {
				//Object에 오는 타입이 두가지다.
				//하나는 ModelAndView 나머지 하나는 String
				Object robj = null;
				if(cud == null) {
					robj = controller.process(requestName, req, res);
				}
				else { // 입력, 수정, 삭제
					robj = controller.process(req, res);
				}
				//ModelAndView인지 아니면 String 인지 찍어보자.
				logger.info("robj : "+robj);
				if(robj instanceof String) {
					pageMove = robj.toString().split(":");
					logger.info("pageMove[0]: "+pageMove[0]+", [1]:"+pageMove[1]);
				}
				else if(robj instanceof ModelAndView) {
					pageMove = new String[2];
					pageMove[0] = "forward";
					pageMove[1] = ((ModelAndView) robj).getViewName();
					logger.info("pageMove[0]: "+pageMove[0]+", [1]:"+pageMove[1]);
				} else { logger.info("robj 조건 없음"); }
				
			} catch (Exception e) {
				logger.info("Exception : "+e.toString());
			}
			//insert here - redirect인지 forward인지 경우를 쪼개기
			//힌트 : return "redirect:/member/memberList.mvc3"
			if(pageMove != null) {
				String path = pageMove[1];
				if("redirect".equals(pageMove[0])) {
					res.sendRedirect(path);
				}
				else if("forward".equals(pageMove[0])) {
					RequestDispatcher view = req.getRequestDispatcher(path);
					view.forward(req, res);
				}
				else {
					res.sendRedirect("/error/pageMoveFail.jsp");
				}
			} else { logger.info("pageMove == null"); }
		}//end of if(controller != null)
	}//end of doService

//doGet	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		logger.info("doGet() 호출 성공");
		doService(req, res);
	}
//doPost
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		logger.info("doPost() 호출 성공");
		doService(req, res);
	}
}
