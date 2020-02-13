package com.gr.RecruMe.exceptions;

public class ErrorMessage extends Exception {
    public static final String applicantNotFound = "The applicant does not exist. Try another id!";

    public static String getErrorDescription(Exception exception, Integer statusCode){
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }
}
