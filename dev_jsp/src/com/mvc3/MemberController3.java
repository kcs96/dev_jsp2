package com.mvc3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mvc3.ModelAndView;

public class MemberController3 implements Controller3 {
	
	Logger logger = Logger.getLogger(MemberController3.class);
	String crud = null;
	MemberLogic3 memLogic = null;
	
	public MemberController3(String crud) {
		this.crud = crud;
		memLogic = new MemberLogic3();
	}

	@Override
	public ModelAndView process(String requestName, HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		logger.info("process[ModelAndView] 호출 성공 - requestName :"+requestName);
		ModelAndView mav = new ModelAndView(req, res);
		if("member/a".equals(requestName)) {
			try {
				//res.sendRedirect(req.getContextPath()+"/view/"+requestName+".jsp"); 
				mav.setViewName("/WEB-INF/view/"+this.crud+".jsp");
				logger.info("mav getViewName() : "+mav.getViewName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("zipcodeList".equals(requestName)) {
			
		}
		return mav;
	}
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("process[String] 호출 성공!, crud : "+crud); 
		String path = null;
		//DB연동 작업
		if("login".equals(crud)) {
			HttpSession session = req.getSession();
			String u_id = req.getParameter("mem_id");
			String u_pw = req.getParameter("mem_pw");
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("mem_id",u_id);
			pMap.put("mem_pw",u_pw);
			String s_name = memLogic.login(pMap);
			//session.setAttribute("s_name", "이순신");
			session.setAttribute("s_name", s_name);
			path = "forward:mapDesignVer3.jsp";
		}
		else if("memberList".equals(crud)) {
			List<Map<String, Object>> memList = null;
			Map<String, Object> pMap = new HashMap<>();
			memList = memLogic.memberList(pMap);
			//
			logger.info("memList : "+memList.size()+"row");
			req.setAttribute("memList", memList);
			path = "forward:memberList.jsp";
		}
		else if("memberAdd".equals(crud)) {
			Map<String, Object> pMap = new HashMap<>();
			int result =0;
			pMap.put("mem_id", req.getParameter("mem_id"));
			pMap.put("mem_pw", req.getParameter("mem_pw"));
			pMap.put("mem_name", req.getParameter("mem_name"));
			pMap.put("mem_addr", req.getParameter("mem_addr"));
			pMap.put("mem_email", req.getParameter("mem_email"));
			pMap.put("zipcode", req.getParameter("zipcode"));
			pMap.put("mem_addrDet", req.getParameter("mem_addrDet"));
			result = memLogic.memberAdd(pMap);
			//
			logger.info("result : "+result);
			path = "forward:/member/memeberList.mvc2?crud=memberList";
		}
		//다녀온 List, Map을 req에 저장.
		
		//응답페이지 방식 및 페이지 설정
		//path = "forward:memberList.jsp";
		return path;
	}

	

}
