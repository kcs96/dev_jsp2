package com.mvc2;

import org.apache.log4j.Logger;

public class ControllerMapper {
	public static Controller getController(String command, String crud) {
		Logger logger = Logger.getLogger(ControllerMapper.class);
		logger.info("command :"+command + ", crud :"+crud);
		Controller controller = null;
		String commands[] = command.split("/");
			
			if(commands.length==2) {
				String work = commands[0];
				String requestName = commands[1];
				if("member".equals(work)) {
					controller = new MemberController(crud);
				}
			}
		return controller;
	}//////end of getController
}
