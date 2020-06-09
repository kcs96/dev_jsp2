package com.mvc3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BoardController implements Controller2020 {
	Logger logger = Logger.getLogger(BoardController.class);
	String requestName = null;
	public BoardLogic bLogic = null;
	
	public BoardController(String requestName) {
		logger.info("BoardController 결정 : "+requestName);
		this.requestName=requestName;
		bLogic = new BoardLogic();
	}
	/**************************************************************
	 * @param ActionSupport에서 넘겨 준 주소번지를 사용함.
	 * @return String
	 * 예시) "redirect:XXX.jsp" 하거나 "forward:XXX.jsp" 하면 됨. 
	 **************************************************************/
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("process:String호출 성공");
		String path = null;
		//너 조회버튼 누른거야?
		if("boardList".equals(requestName)) {
			logger.info("boardList 호출 성공");
			List<Map<String,Object>> mList = null;
			Map<String,Object> pMap = new HashMap<>();
			//mList = bLogic.boardList(pMap);
			mList = bLogic.proc_boardList(pMap);
			req.setAttribute("boardList", mList);
			path = "forward:list.jsp";
		}
		//제목을 클릭했을때?
		else if("boardDetail".equals(requestName)) {
			logger.info("boardDetail 호출 성공");
			List<Map<String,Object>> mList = null;
			Map<String,Object> pMap = new HashMap<>();
			pMap.put("bm_no", req.getParameter("bm_no"));
			mList = bLogic.boardList(pMap);
			req.setAttribute("boardDetail", mList);
			path = "forward:read.jsp";
		}		
		//너 입력하려구?
		else if("boardINS".equals(requestName)) {
			int result =0;
			Map<String,Object> pMap = new HashMap<>();
			pMap.put("bm_pw", req.getParameter("bm_pw"));
			pMap.put("bm_title", req.getParameter("bm_title"));
			pMap.put("bm_write", req.getParameter("bm_write"));
			pMap.put("bm_email", req.getParameter("bm_email"));
			pMap.put("bm_content", req.getParameter("bm_content"));
			result = bLogic.boardINS(pMap);
			if(result==1) path ="redirect:boardInsOk.jsp";
			else if(result ==0) path="redirect:boardInsFail.jsp";
		}
		//너 수정하려구?
		else if("boardUPD".equals(requestName)) {
			int result =0;
			Map<String,Object> pMap = new HashMap<>();
			result = bLogic.boardUPD(pMap);
			if(result==1) path ="redirect:boardUpdOk.jsp";
			else if(result ==0) path="redirect:boardUpdFail.jsp";
		}
		//너 삭제하려구?
		else if("boardDEL".equals(requestName)) {
			int result =0;
			Map<String,Object> pMap = new HashMap<>();
			result = bLogic.boardDEL(pMap);
			if(result==1) path ="redirect:boardDelOk.jsp";
			else if(result ==0) path="redirect:boardDelFail.jsp";
		}
		return path;
	}

	@Override
	public ModelAndView process(String work, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		logger.info("process호출:ModelAndView 성공");
		return null;
	}

}
