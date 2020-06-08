package com.mvc2;

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

public class MemberController implements Controller{
	String crud = null;
	Logger logger = Logger.getLogger(MemberController.class);
	MemberLogic memLogic = null;
	public MemberController(String crud) {
		this.crud=crud;
		memLogic = new MemberLogic();
	}
	
	//서블릿 3단계  내가 아직 신경쓸 레벨이 아닌것같다.
	/*
	 * public ModelAndView process(String work, HttpServletRequest req) { return new
	 * ModelAndView();
	   }*/
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("process 호출 성공");
		String path = "";
		if("login".equals(crud)) {
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
		else if("memberList".equals(crud)) {
			List<Map<String,Object>> memList = null;
			Map<String,Object> pMap = new HashMap<>();
			memList =memLogic.memberList(pMap);
			if(memList==null) {
				memList = new ArrayList<>();
			}
			req.setAttribute("memList", memList);
			path="forward:/member/memberList2.jsp";
		}
		else if("memberList".equals(crud)) {
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
			int result =0; //1이면 등록성공 0이면 실패
			Map<String,Object> pMap = new HashMap<String, Object>();
			pMap.put("mem_id", req.getParameter("mem_id"));
			pMap.put("mem_pw", req.getParameter("mem_pw"));
			pMap.put("mem_name", req.getParameter("mem_name"));
			result = memLogic.memberInsert(pMap);
			if(result ==1) {
				path="sendRedirect:/member/memberList2.jsp";
			}else {
				path="sendRedirect:/member/false.jsp";
			}
		}
		return path;
	}
}
