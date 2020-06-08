package com.mvc3;

import org.apache.log4j.Logger;

public class ControllerMapper3 {
	
	public static Controller2020 getController(String command) {
		Logger logger = Logger.getLogger(ControllerMapper3.class);
		Controller2020 controller = null;
		String commands[] = command.split("/");
			
			if(commands.length==2) {
				String work = commands[0];
				String requestName = commands[1];
				logger.info("work ==>"+work   );
				logger.info("requestName ==>"+requestName);
				if("member".equals(work)) {
					controller = new MemberController3(requestName);
				}
				//계층형 게시판 컨트롤러 생성하기
				else if("board".equals(work)) {
					controller = new BoardController(requestName);
					logger.info("controller"+controller);
					
				}
			}
		return controller;
	}//////end of getController
}
