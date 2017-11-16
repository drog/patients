package com.diego.rest.restExternal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diego.rest.restExternal.dto.RestResponseDTO;

@RestController
public class FakeLoginController {
	
	public static final Logger logger = LoggerFactory.getLogger(FakeLoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RestResponseDTO fakeLogin() {
		logger.debug("/login");
		return new RestResponseDTO(new Login("ticket", "sessionId"));
	}
	
	private class Login{
		private String ticket;
		
		public String getTicket() {
			return ticket;
		}

		public void setTicket(String ticket) {
			this.ticket = ticket;
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		private String sessionId;

		public Login(String ticket, String sessionId) {
			super();
			this.ticket = ticket;
			this.sessionId = sessionId;
		}
		
		
	}
	
}


