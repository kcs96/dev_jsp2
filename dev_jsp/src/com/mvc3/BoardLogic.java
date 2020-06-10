package com.mvc3;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class BoardLogic {
	
	Logger logger = Logger.getLogger(BoardLogic.class);
	BoardMDao bmDao = null;
	BoardSDao bsDao = null;
	
	//생성자 
	public BoardLogic() {
		bmDao = new BoardMDao();
		bsDao = new BoardSDao();
	}

	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출 성공");
		List<Map<String, Object>> boardList = null;
		boardList = bmDao.boardList(pMap);
		return boardList;
	}
	public List<Map<String, Object>> proc_boardList(Map<String, Object> pMap) {
		logger.info("proc_boardList 호출 성공");
		List<Map<String, Object>> boardList = null;
		boardList = bmDao.proc_boardList(pMap);
		return boardList;
	}
	//난이도 : 상 
	//트랜잭션 처리에 대한 부분 - 프로시저로 해보면 좋겠다.
	//첨부파일 처리
	//위젯 API 구현해 본다.- 스타트업 기업, 1인 기업, 소규모
	//공통코드 작성 - PL - 1.공통팀, 2.형상관리팀, 품질관리팀, 개발팀, 관리팀, 테스트팀
	public int boardINS(Map<String, Object> pMap) {
		logger.info("boardINS 호출 성공");
		int result = 0; 
		int bm_group = 0;
		int bm_no = 0;
		bm_no = bmDao.getBmNo(pMap);
		//그룹번호가 있나요? 
		//어디서 왔니? list.jsp이면 없다. read.jsp 이면 있다.
		//예외처리 추가할 것 - [IO사용시] 꼭 필요 - 첨부파일
		int pbm_no = 0;
		if(pMap.get("bm_no") != null) {
			pbm_no = Integer.parseInt(pMap.get("bm_no").toString());
		}
		try {
			// 새글인가?
			if(pbm_no == 0) { //키가 없을 때  TRUE
				//새글이면 그룹번호를 새로 채번해야 합니다.
				logger.info("=====> BM_NO가없음. 새글");
				bm_group = bmDao.getBmGroup(pMap);
				
				pMap.put("bm_group", bm_group);
				pMap.put("bm_pos", 0);
				pMap.put("bm_step", 0);
			}
			// 아님 댓글인가?
			else { //키가 있을 때
				logger.info("=====> BM_NO가 있음. 댓글 bm_group : "+pMap.get("bm_group"));
				bmDao.bmStepUpdate(pMap);
				if(pMap.get("bm_pos")!=null) {
					pMap.put("bm_pos", Integer.parseInt(pMap.get("bm_pos").toString())+1);
				}
				if(pMap.get("bm_step")!=null) {
					pMap.put("bm_step", Integer.parseInt(pMap.get("bm_step").toString())+1);
				}
			}
			//첨부파일이 있을까요?
			if(pMap.get("bs_file")!=null) {
				logger.info("첨부파일이 있는 경우");
				int sresult = 0;
				sresult = bsDao.boardSINS(pMap);
			}
			pMap.put("bm_no", bm_no);
			//데이터 유효성 체크 코드 추가 . XML문서 분석하기
			//팀원은 PL로 부터 소스를 받으면 제일 먼저 단위테스트를 수행
			// 주의사항 : 테이블에 컬럼을 사용자로부터 입력받는 값과 개발자끼리만 공유하는 값 
			//insert가 되기전에 확인하는 로그, 웹페이지에서 넘어온 값 들 
			logger.info("bm_no     : "+pMap.get("bm_no"));
			logger.info("bm_group  : "+pMap.get("bm_group"));
			logger.info("bm_pos    : "+pMap.get("bm_pos"));
			logger.info("bm_step   : "+pMap.get("bm_step"));
			logger.info("bm_title  : "+pMap.get("bm_title"));
			logger.info("bm_writer : "+pMap.get("bm_writer"));
			logger.info("bm_email  : "+pMap.get("bm_email"));
			logger.info("bm_content: "+pMap.get("bm_content"));
			logger.info("bm_pw     : "+pMap.get("bm_pw"));
			result = bmDao.boardMINS(pMap);
			//첨부파일이 있을 때만 bsDao.boardSINS(pMap);
		} catch (Exception e) {
			//메모리에 쌓여있는 로그 히스토리를 출력, 라인번호도 출력
			e.printStackTrace();
		}
		
		return result;
	}

	public int boardUPD(Map<String, Object> pMap) {
		logger.info("boardUPD 호출 성공");
		int result = 0; 
		
		result = bmDao.boardUPD(pMap); //boardMUPD이 아닌 이유는 첨부파일을 수정 못하도록 처리
		return result;
	}
	
	public int boardDEL(Map<String, Object> pMap) {
		logger.info("boardDEL 호출 성공");
		int result = 0;
		
		result = bmDao.boardDEL(pMap);
		return result;
	}
	
	
}
