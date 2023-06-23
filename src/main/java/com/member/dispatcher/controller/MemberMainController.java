package com.member.dispatcher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberMainController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		return "index";
	}
}
