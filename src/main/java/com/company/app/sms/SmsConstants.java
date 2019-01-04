package com.company.app.sms;

public class SmsConstants {

    public static final String SMS_PATH = "/sms";
    public static final String SMS_SEND_PATH = "/sms/send";
    public static final String SMS_GET_PATH = "/sms/get";
    public static final String SMS_UPDATE_PATH = "/sms/update";
    public static final String SMS_DELETE_PATH = "/sms/delete";

    public static final String SUCCESS_MESSAGE = "Successful";
    public static final String FAILURE_MESSAGE = "Failure";

    public static final String TWILIO_SID_ACCOUNT = "TWILIO_SID_ACCOUNT";
    public static final String TWILIO_AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
    public static final String TWILIO_PHONE_NUMBER = "TWILIO_PHONE_NUMBER";

    //e.164 format
    public static final String PHONE_NUMBER_REGEX = "^\\+?[1-9]\\d{1,14}$";
}
