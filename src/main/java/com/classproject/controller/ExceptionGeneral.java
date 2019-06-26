package com.classproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGeneral {
	
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("exceptionCause", ex.getCause().toString());
		model.addAttribute("exceptionMessage", ex.getMessage());
		return "exceptionHandler";
	}
	
}
