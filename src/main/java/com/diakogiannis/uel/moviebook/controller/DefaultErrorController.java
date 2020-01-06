package com.diakogiannis.uel.moviebook.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultErrorController implements ErrorController {

    @RequestMapping(path = "/error",method = { RequestMethod.GET, RequestMethod.POST })
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "/error/error-404";
            }
            if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "/error/error-400";
            }
            if(HttpStatus.valueOf(statusCode).is4xxClientError() && statusCode != HttpStatus.NOT_FOUND.value()) {
                return "/error/error-400";
            }
            else if(HttpStatus.valueOf(statusCode).is5xxServerError()) {
                return "/error/error-500";
            }
        }

        return getErrorPath();
    }

    @Override
    public String getErrorPath() {
        return "/error/error";
    }


}