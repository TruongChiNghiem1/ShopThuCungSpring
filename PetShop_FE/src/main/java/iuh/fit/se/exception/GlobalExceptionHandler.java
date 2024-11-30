package iuh.fit.se.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
		
	@ExceptionHandler(ProductException.class)
	public ModelAndView productException(ProductException pe, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("url", request.getRequestURI());
		model.addObject("message", pe.getMessage());
		model.setViewName("/error");
		return model;
	}
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<Map<String, Object>> globleExcpetionHandler(Exception ex) {
		System.out.println(ex);
		Map<String, Object> errors = new LinkedHashMap<String, Object>();
		errors.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.put("message", ex.getMessage());
		return new ResponseEntity<Map<String,Object>>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
