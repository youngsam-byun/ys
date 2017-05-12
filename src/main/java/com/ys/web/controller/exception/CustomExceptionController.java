package com.ys.web.controller.exception;

import com.ys.app.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by byun.ys on 4/19/2017.
 */
@ControllerAdvice
public class CustomExceptionController {

    private static final String NEWLINE = "\n";
    private static final String FOLDER = "/error";
    private static final String CUTOM_ERROR_JSP = "/cutomError.jsp";
    private static Logger logger = LoggerFactory.getLogger(CustomExceptionController.class);

    @ExceptionHandler(value = {CustomException.class, RuntimeException.class})
    public ModelAndView HandleException(Exception e, ModelAndView modelAndView) {

        StringBuilder sb = new StringBuilder();
        if (e instanceof CustomException) {
            String tClass = ((CustomException) e).gettClass().toString();
            String method = ((CustomException) e).getMethod();
            String message = e.getMessage();
            String stackTrace=getStackTrace(e);

            sb.append(tClass).append(NEWLINE)
                    .append(method).append(NEWLINE)
                    .append(message).append(NEWLINE)
                    .append(stackTrace).append(NEWLINE);

        } else
            sb.append(getStackTrace(e));

        logger.debug(sb.toString());

        modelAndView.setViewName(FOLDER + CUTOM_ERROR_JSP);
        return modelAndView;

    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
