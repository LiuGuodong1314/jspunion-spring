package com.jspunion.handler;

import com.jspunion.beans.HttpEntity;
import com.jspunion.exception.ServerException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleError(Exception e, HttpServletRequest req) {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        ResponseStatus status = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);

        if (status != null)
            return  HttpEntity.fromResponseStatus(status);;

        // Otherwise setup and send the user to a default error-view.
       // ModelAndView mav = new ModelAndView();
       // mav.addObject("error", e);
       // mav.addObject("url", req.getRequestURL());
       // mav.setViewName(DEFAULT_ERROR_VIEW);
       // return mav;
       return HttpEntity.error();
    }
}
