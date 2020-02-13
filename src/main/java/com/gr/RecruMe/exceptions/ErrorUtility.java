package com.gr.RecruMe.exceptions;

public class ErrorUtility {

    public static String formatToHtml(Exception exception, Integer responseStatusCode){
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                responseStatusCode, exception == null ? "N/A" : exception.getMessage());
    }
}
