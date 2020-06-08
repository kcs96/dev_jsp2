package com.mvc3;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class BoardLogic {
	Logger logger = Logger.getLogger(BoardLogic.class);
	public BoardMDao bmDao = null;
	public BoardSDao bsDao = null;
	//디폴트 생성자
	public BoardLogic() {
		logger.info("BoardLogic() 생성 성공");
		bmDao = new BoardMDao();
		bsDao = new BoardSDao();
	}
	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출성공");
		List<Map<String,Object>> bList = null;
		bList = bmDao.boardList(pMap);
		return bList;
	}
	//난이도 : 상, 1차 목표, 2차 목표, 3차 목표
	//트랜젝션처리에 대한 부분 - 프로시저로 해보면 좋겠다.-금융,보험,물류,회계
	//첨부파일 처리
	//위젯 API 구현해 본다. -스타트업,1인기업,소규모
	//공통코드 작성 - PL - 공통팀, 형상관리팀, 품질관리팀, 개발팀, 관리팀, 테스트팀
	public int boardINS(Map<String, Object> pMap) {
		logger.info("boardINS 호출성공");
		int result = 0;
		int bm_no = 0;
	    bm_no = bmDao.getBmNo(pMap);
	    pMap.put("bm_no",bm_no);
	    int bm_group = 0;
		//그룹번호가 있나요?
		//어디서 오셨죠? list.jsp이면 없다, read.jsp이면 있다.
		bm_group=bmDao.getBmGroup(pMap);
		
		//예외처리 추가할것. -io사용시 꼭 필요
		try {
			//새글인가?  조회했던번호가있어?
			if(!pMap.containsKey("bm_no")) {
				//새글이면 그룹번호를 새로 채번해야 합니다.
				pMap.put("bm_group", bm_group);
				pMap.put("bm_pos",0);
				pMap.put("bm_step",0);
			}
			//아님 댓글이야?
			else {
				if(pMap.get("bm_pos")!=null) {
					pMap.put("bm_pos",Integer.parseInt(pMap.get("bm_pos").toString())+1);
				}
				if(pMap.get("bm_step")!=null) {
					pMap.put("bm_step",Integer.parseInt(pMap.get("bm_step").toString())+1);
				}
			}
			//첨부파일이 있을까요?
			if(pMap.get("bs_file")!=null) {
				logger.info("첨부파일이 있는 경우");
				int sresult = 0;
				sresult=bsDao.boardSINS(pMap);
			}
			//데이터 유효성 체크 코드 추가
			//크루는 pl로 부터 소스를 받으면 제일 먼저 단위테스트를 수행
			//주의사항:테이블 컬럼을 사용자로부터 입력받는 값과 개발자끼리만 공유하는 값
			logger.info("bm_no ===>"+pMap.get("bm_no"));
			logger.info("bm_title ===>"+pMap.get("bm_title"));
			logger.info("bm_content ===>"+pMap.get("bm_content"));
			logger.info("bm_email ===>"+pMap.get("bm_email"));
			logger.info("bm_pw ===>"+pMap.get("bm_pw"));
			result = bmDao.boardMINS(pMap);
			//첨부파일이 있을때만 bsDao.boardSINS(pMap);
		} catch (Exception e) {
			//라인 번호도 출력됨. - log4j
			e.printStackTrace();//stack메모리에 쌓여있는 로그 히스토리를 출력함.
		}
		return result;
	}

	public int boardUPD(Map<String, Object> pMap) {
		logger.info("boardUPD 호출성공");
		int result =0;
		result = bmDao.boardUPD(pMap);
		return result;
	}

	public int boardDEL(Map<String, Object> pMap) {
		logger.info("boardDEL 호출성공");
		int result =0;
		result = bmDao.boardDEL(pMap);
		return result;
	}
}
