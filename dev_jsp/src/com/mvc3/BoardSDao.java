package com.mvc3;

import java.util.Map;

import org.apache.log4j.Logger;

public class BoardSDao {
	Logger logger = Logger.getLogger(BoardSDao.class);
	
	public BoardSDao() {
		logger.info("BoardSDao() 호출 성공");
	}
	
	
	public int boardSINS(Map<String, Object> pMap) {
		logger.info("boardSINS 호출 성공");
		int result =0;
		
		return result;
	}
	
	public int boardUPD(Map<String, Object> pMap) {
		logger.info("boardUPD 호출 성공");
		return 0;
	}

	public int boardDEL(Map<String, Object> pMap) {
		logger.info("boardDEL 호출 성공");
		return 0;
	}

}
