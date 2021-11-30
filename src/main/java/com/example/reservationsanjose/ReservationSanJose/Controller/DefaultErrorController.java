package com.example.reservationsanjose.ReservationSanJose.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class DefaultErrorController {

    private final static Logger logger = LoggerFactory.getLogger(DefaultErrorController.class);

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView viewAccessDeniedPage(HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("generic-error");
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String errorMessage = sw.toString();

        modelAndView.addObject("errorURL", request.getRequestURL());
        modelAndView.addObject("errorMessage", "You are not authorized to access this page.");
        logger.error("-------- Error Start for URL (" + request.getRequestURL() + ") -------------");
        logger.error("Error Description : " + errorMessage);
        logger.error("-------- Error End for URL (" + request.getRequestURL() + ") -------------");
        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView viewErrorPage(ModelAndView modelAndView, HttpServletRequest req) {
        modelAndView.setViewName("generic-error");
        return modelAndView;
    }

}
