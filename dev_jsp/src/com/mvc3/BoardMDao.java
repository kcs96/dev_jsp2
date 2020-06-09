package com.mvc3;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.vo.BoardVO;

public class BoardMDao {
	Logger logger = Logger.getLogger(BoardMDao.class);
	public SqlSessionFactory sqlMapper = null;
	public SqlSession sqlSec = null;
	
	public BoardMDao() {
		logger.info("BoardMDao() 호출 성공"); 
		sqlMapper= MyBatisCommonFactory.getSqlSessionFactory();
		sqlSec = sqlMapper.openSession();
	}
	
	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출 성공");
		List<Map<String, Object>> bList = null;
		bList = sqlSec.selectList("boardList",pMap);
		logger.info("bList :"+bList.size());
		return bList;
	}
	
	public List<Map<String, Object>> proc_boardList(Map<String, Object> pMap) {
		logger.info("proc_boardList 호출 성공");
		List<Map<String, Object>> bList = null;
		sqlSec.selectOne("proc_boardList", pMap);
		bList = (List) pMap.get("key");
		logger.info("bList :" + bList.size());
		return bList;
	}
	
	public int getBmNo(Map<String, Object> pMap) {
		logger.info("getBmNo 호출 성공");
		int bm_no = 0;
		bm_no = sqlSec.selectOne("getBmNo",pMap);
		logger.info("bm_no :"+bm_no);
		return bm_no;
	}
	
	public int getBmGroup(Map<String, Object> pMap) {
		logger.info("getBmGroup 호출 성공");
		int bm_group = 0;
		bm_group = sqlSec.selectOne("getBmGroup",pMap);
		logger.info("bm_group :"+bm_group);
		return bm_group;
	}
	
	public int boardMINS(Map<String, Object> pMap) {
		logger.info("boardMINS 호출 성공");
		int result = 0;
		result = sqlSec.insert("boardMINS",pMap);
		logger.info("result ==> "+result);
		sqlSec.commit(true);
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
