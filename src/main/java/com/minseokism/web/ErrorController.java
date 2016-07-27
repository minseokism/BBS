package com.minseokism.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minseokism.service.ErrorService;

@Controller
public class ErrorController{
	private static final Logger log = LoggerFactory.getLogger(ErrorController.class);
	
	@Autowired
	private ErrorService errorService;
	     
	@RequestMapping(value="/errors",method=RequestMethod.GET)
	public String renderErrorPage(final Model model, final HttpServletRequest request){
	    log.info("[error handling !] ------------");
	    
		final int error_code=getHttpStatusCode(request);
	  
		final String error_message=errorService.generateErrorMessage(error_code);

		log.info("[error_code] : " +error_code+" [error_message] : "+error_message );
		model.addAttribute("errorMsg",error_message);
		return "error/error";
	}  
	
	private int getHttpStatusCode(final HttpServletRequest request){
		return (int) request.getAttribute("javax.servlet.error.status_code");
	}  
	
}
