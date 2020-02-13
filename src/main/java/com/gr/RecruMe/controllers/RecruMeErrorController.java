package com.gr.RecruMe.controllers;

import com.gr.RecruMe.exceptions.ApplicantNotFoundException;
import com.gr.RecruMe.exceptions.ErrorMessage;
import com.gr.RecruMe.exceptions.JobNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RecruMeErrorController implements ErrorController{
    @RequestMapping("error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        if (exception == null) {
            return "There is nothing here!";
        }
        Throwable causeOfException = exception.getCause();

        if (exception instanceof ApplicantNotFoundException || causeOfException instanceof ApplicantNotFoundException) {
            return ErrorMessage.applicantNotFound;
        }
        if (exception instanceof JobNotFoundException) {
            return exception.getMessage();
        }

        return ErrorMessage.getErrorDescription(exception, statusCode);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
