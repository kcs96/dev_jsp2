package com.mvc3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import orm.dao.HashMapBilder;

public class MemberController3 implements Controller2020{
	String crud = null;
	Logger logger = Logger.getLogger(MemberController3.class);
	MemberLogic3 memLogic = null;
	public MemberController3(String crud) {
		this.crud=crud;
		memLogic = new MemberLogic3();
	}
	
	@Override
	public ModelAndView process(String requestName,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("process[ModelAndView] 호출 성공"+"requestName ==>"+requestName);
		//기능처리
		//기능처리 후
		ModelAndView mav = new ModelAndView(req,res);
		mav.setViewName(requestName);
		if("member/memberList3".equals(requestName)) {
			//res.sendRedirect(req.getContextPath()+"/"+requestName+".jsp");
			mav.setViewName("/member/memberList3.jsp");
			return null;
		}
		else if("zipcodeList".equals(requestName)) {
			return null;
		}
		return mav;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("process[String] 호출 성공");
		logger.info("crud ==> "+crud);
		String path = "";
		if("login".equals(crud)) {
			logger.info("로그인  호출 성공");
			String u_id = req.getParameter("mem_id");
			String u_pw = req.getParameter("mem_pw");
			Map<String,Object> pMap = new HashMap<>();
			pMap.put("mem_id", u_id);
			pMap.put("mem_pw", u_pw);
			String name= memLogic.login(pMap);
			HttpSession session = req.getSession();
			session.setAttribute("s_name", name);
			return "forward:mapDesign.jsp";
		}
		else if("memberList3".equals(crud)) {
			logger.info("회원리스트  호출 성공");
			List<Map<String,Object>> memList = null;
			Map<String,Object> pMap = new HashMap<>();
			memList =memLogic.memberList(pMap);
			if(memList==null) {
				memList = new ArrayList<>();
			}
			req.setAttribute("memList", memList);
			path="forward:/member/memberList2.jsp";
		}
		else if("memberInsert".equals(crud)) {
			logger.info("회원가입  호출 성공");
			int result =0; //1이면 등록성공 0이면 실패
			Map<String,Object> pMap = new HashMap<String, Object>();
			pMap.put("mem_id", req.getParameter("mem_id"));
			pMap.put("mem_pw", req.getParameter("mem_pw"));
			pMap.put("mem_name", req.getParameter("mem_name"));
			result = memLogic.memberInsert(pMap);
			if(result ==1) {
				path="sendRedirect:/memberList2.jsp";
			}else {
				path="sendRedirect:/member/false.jsp";
			}
		}
		return path;
	}

	
}
