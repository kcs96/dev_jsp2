package com.mvc2;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MemberDao {
	Logger logger = Logger.getLogger(MemberDao.class);
	String resource = "orm/mybatis/Configuration.xml";
	SqlSessionFactory sqlMapper = null;
	public MemberDao() {
		sqlMapper = MyBatisCommonFactory.getSqlSessionFactory();
	}
	public String login(Map<String,Object> pMap){
		logger.info("login 호출 성공");
		String name= null;
		try {
			SqlSession sqlSec = sqlMapper.openSession();
			name = sqlSec.selectOne("login",pMap);
			System.out.println("회원이름 ==>"+ name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
		}
	
	
	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("MemberDao memberList호출 성공");
		List<Map<String, Object>> memList = null;
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			SqlSession sqlSec = sqlMapper.openSession();
			memList = sqlSec.selectList("memList",pMap);
			logger.info("memList.size() ==>"+memList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memList;
	}
	
	public int memberInsert(Map<String, Object> pMap) {
		logger.info("MemberDao memberList호출 성공");
		int result= 0;
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			SqlSession sqlSec = sqlMapper.openSession(true);
			result = sqlSec.insert("memIns",pMap);
			logger.info("result ==>"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
