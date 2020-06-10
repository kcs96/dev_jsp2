package com.mvc3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.util.HashMapBinder;

public class BoardController implements Controller3 {

	Logger logger = Logger.getLogger(BoardController.class);	
	String requestName = null;
	
	BoardLogic bLogic = null;
	
//생성자
	public BoardController(String requestName) {
		this.requestName = requestName; // 유지 및 공유
		this.bLogic = new BoardLogic();
	}
	
//String 
	/****************************************************************************************
	 * @param ActionSupport에서 넘겨 준 주소번지를 사용함.
	 * @return String
	 * @apiNote "redirect:XXX.jsp"하거나, "forward:XXX.jsp"하면 됨.
	 */
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("process:String 호출 성공");
		String path = null;
		//조회
		if("boardList".equals(requestName)) {
			List<Map<String, Object>> boardList = null;
			Map<String, Object> pMap = new HashMap<String, Object>();
			boardList = bLogic.boardList(pMap);
			//boardList = bLogic.proc_boardList(pMap);
			req.setAttribute("boardList", boardList);
			path = "forward:list.jsp";
		}
		//제목을 클릭했을때 ?
		if("boardDetail".equals(requestName)) {
			List<Map<String, Object>> boardList = null;
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("bm_no", req.getParameter("bm_no"));
			boardList = bLogic.boardList(pMap);
			//boardList = bLogic.proc_boardList(pMap);
			req.setAttribute("boardDetail", boardList);
			path = "forward:read.jsp";
		}
		//입력
		else if("boardINS".equals(requestName)) {
			int result = 0;
			Map<String, Object> pMap = new HashMap<String, Object>();
			HashMapBinder mb = new HashMapBinder(req);
			mb.binder(pMap);
			logger.info("bm_no : "+pMap.get("bm_no"));
			result = bLogic.boardINS(pMap);
			if(result == 1) path="redirect:boardInsOk.jsp"; 
			else if(result == 0) path="redirect:boardInsFail.jsp"; 
		}
		//수정
		else if("boardUPD".equals(requestName)) {
			int result = 0;
			Map<String, Object> pMap = new HashMap<String, Object>();
			result = bLogic.boardUPD(pMap);
			if(result == 1) path="redirect:boardUpdOk.jsp"; 
			else if(result == 0) path="redirect:boardUpdFail.jsp"; 

		}
		//삭제
		else if("boardDEL".equals(requestName)) {
			int result = 0;
			Map<String, Object> pMap = new HashMap<String, Object>();
			result = bLogic.boardDEL(pMap);
			if(result == 1) path="redirect:boardDelOk.jsp"; 
			else if(result == 0) path="redirect:boardDelFail.jsp"; 
			
		}//end of if(requestName)
		return path;
	}//end of process String
//ModelAndView : 예 외 상황
	@Override
	public ModelAndView process(String work, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		logger.info("process:ModelAndView 호출 성공");
		
		return null;
	}

}
