package com.tariq.ecommercedemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handler(){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The Page is Underconstructor");
		mv.addObject("errorDescription", "This page is under construction, so you are looking for this page is Panic!!!");
		mv.addObject("title", "404 Error Page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException(){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not Found");
		mv.addObject("errorDescription", "This page is under construction, so you are looking for this page is Panic!!!");
		mv.addObject("title", "404 Error Page");
		return mv;
	}

}
