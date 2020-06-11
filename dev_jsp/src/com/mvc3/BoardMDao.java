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
	SqlSessionFactory sqlMapper = null;
	SqlSession sqlSes = null;
	
	public BoardMDao() {
		this.sqlMapper = MyBatisCommonFactory.getSqlSessionFactory();
		logger.info("sqlMapper : "+sqlMapper);
		this.sqlSes = sqlMapper.openSession();
	}
	//게시판 목록 가져오기
	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출 성공");
		List<Map<String, Object>> boardList = null;
		boardList = sqlSes.selectList("boardList",pMap);
		logger.info(" - boardList : "+boardList.size()+"row");
		return boardList;
	}
	//프로시저로 가져오기 
	public List<Map<String, Object>> proc_boardList(Map<String, Object> pMap) {
		logger.info("proc_boardList 호출 성공");
		List<Map<String, Object>> boardList = null;
		sqlSes.selectOne("proc_boardList",pMap);
		boardList = (List)pMap.get("key");
		logger.info(" - boardList : "+boardList.size()+"row");
		Iterator iter = boardList.iterator();
		while(iter.hasNext()) {
			BoardVO bVo = (BoardVO)iter.next();
			logger.info(" - 글 번호 : "+bVo.getBm_no());
		}
		return boardList;
	} //이노플렉스 
	//그룹번호 채번하기
	public int getBmGroup(Map<String, Object> pMap) {
		logger.info("getBmGroup 호출 성공");
		int bm_group = 0;
		bm_group = sqlSes.selectOne("getBmGroup",pMap);
		logger.info(" - bm_group : "+bm_group);
		return bm_group;
	}
	
	public int bmStepUpdate(Map<String, Object> pMap) {
		logger.info("bmStepUpdate 호출 성공");
		int result = 0;
		result = sqlSes.update("bmStepUpdate",pMap);
		logger.info(" - result : "+result);
		sqlSes.commit(true);
		return result;
	}
	//글번호 채번하기
	public int getBmNo(Map<String, Object> pMap) {
		logger.info("getBmNo 호출 성공");
		int bm_no = 0;
		bm_no = sqlSes.selectOne("getBmNo",pMap);
		logger.info(" - bm_no : "+bm_no);
		return bm_no;
	}

	public int boardMINS(Map<String, Object> pMap) {
		logger.info("boardMINS 호출 성공");
		int result = 0;
		result = sqlSes.insert("boardMINS",pMap);
		if(result ==1 ) sqlSes.commit();
		return result;
	}

	public int boardUPD(Map<String, Object> pMap) {
		logger.info("boardUPD 호출 성공");
		return 0;
	}

	public int boardDEL(Map<String, Object> pMap) {
		logger.info("boardDEL 호출 성공"+pMap.get("bm_no"));
		int result = 0;
		result = sqlSes.delete("boardDEL",pMap);
		if(result ==1 ) sqlSes.commit();
		return result;
	}

	
	public static void main(String[] args) {
		BoardMDao mb = new BoardMDao();
		mb.proc_boardList(null);
	
	}
}
