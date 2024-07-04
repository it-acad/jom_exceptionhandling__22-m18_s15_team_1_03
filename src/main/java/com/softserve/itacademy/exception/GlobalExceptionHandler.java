package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullEntityReferenceException.class)
    public ModelAndView handleNullEntityReferenceException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("500", HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("404", HttpStatus.NOT_FOUND);
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
