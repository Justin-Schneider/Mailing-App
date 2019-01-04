package com.company.app.email;

public class EmailConstants {

    public static final String EMAIL_PATH = "/email";
    public static final String EMAIL_SEND_PATH = "/email/send";
    public static final String EMAIL_GET_PATH = "/email/get";
    public static final String EMAIL_UPDATE_PATH = "/email/update";
    public static final String EMAIL_DELETE_PATH = "/email/delete";

    public static final String SUCCESS_MESSAGE = "Successful";
    public static final String FAILURE_MESSAGE = "Failure";

    //https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    //for other regions
    public static final String SMTP_HOST = "SMTP_HOST";
    public static final String SMTP_PORT = "SMTP_PORT";
    public static final String MIME_TYPE = "text/html";

    public static final String SMTP_USERNAME = "SMTP_USERNAME";
    public static final String SMTP_PASSWORD = "SMTP_PASSWORD";

    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
}
