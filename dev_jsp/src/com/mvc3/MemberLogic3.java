package com.mvc3;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class MemberLogic3 {
	
	Logger logger = Logger.getLogger(MemberLogic3.class);
	MemberDao3 memDao = new MemberDao3();
	
//로그인 로직
	public String login(Map<String, Object> pMap) {
		logger.info("login 호출");
		String s_name = null;
		s_name = memDao.login(pMap);
		
		return s_name;
	}
//회원목록 로직
	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("memberList 호출");
		List<Map<String, Object>> memList = null;
		memList = memDao.memberList(pMap);
		
		return memList;
	}
	public int memberAdd(Map<String, Object> pMap) {
		int result = 0;
		result = memDao.memberAdd(pMap);
		return result;
	}
}
