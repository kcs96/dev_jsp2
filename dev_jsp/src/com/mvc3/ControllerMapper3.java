package com.mvc3;

import org.apache.log4j.Logger;

public class ControllerMapper3 {
	
	public static Controller3 getController(String command) {
		Logger logger = Logger.getLogger(ControllerMapper3.class);
		logger.info("getController() 호출 성공!"); 
		Controller3 controller = null;
		int end = command.lastIndexOf(".");
		command = command.substring(0, end);
		String commands[] = command.split("/");
		for(int i=0; i<commands.length; i++) {
			logger.info("commands["+i+"] : "+commands[i]); 
		}
		if(commands.length == 2) {
			String work = commands[0];
			String requestName = commands[1];
			if("member".equals(work)) {
				
				controller = new MemberController3(requestName);
			} 
			//계층형 게시판 컨트롤러 생성하기
			else if("board".equals(work)) {
				controller = new BoardController(requestName); 
			}
		} 
		return controller;
	}
}
