package com.mvc3;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MemberDao3 {

	Logger logger = Logger.getLogger(MemberDao3.class);
	SqlSessionFactory sqlMapper = null;
	String resource = "orm/mybatis/Configuration.xml";
	
	public MemberDao3() {
		sqlMapper = MyBatisCommonFactory.getSqlSessionFactory();
	}
	
//로그인 헀을때에 메소드
	public String login(Map<String, Object> pMap) {
		logger.info("login 호출");
		String s_name = null;
		SqlSession sqlSes = null;
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			s_name = sqlSes.selectOne("login", pMap);
			logger.info("s_name : "+s_name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSes.close();
		}
		return s_name;
	}
//회원 목록을 조회 할때의 메소드
	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("memberList 호출");
		List<Map<String, Object>> memList = null;
		SqlSession sqlSes = null;
		try {
			sqlSes = sqlMapper.openSession();
			memList = sqlSes.selectList("memList", pMap);
			logger.info("memList : "+memList.size()+"row");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSes.close();
		}
		return memList;
	}

	public int memberAdd(Map<String, Object> pMap) {
		logger.info("memberAdd 호출");
		int result = 0;
		SqlSession sqlSes = null;
		try {
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("memAdd", pMap);
			logger.info("result : "+result);
			if(result ==1) {
				sqlSes.commit();
			}
			else {
				sqlSes.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSes.close();
		}
		return result;
	}
}
