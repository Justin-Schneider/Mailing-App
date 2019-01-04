package com.company.app.sms;

import com.company.app.common.CommonConstants;
import com.company.app.common.CommonService;
import com.company.app.vendor.VendorServices;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class SmsService {

    public static ResponseEntity<Map> sendingSms(Sms sms) {

        try {
            VendorServices.initializeTwilio();
            return VendorServices.sendSmsMessages(sms.getRecipients(), sms.getContent());
        } catch (IOException e) {
            e.printStackTrace();
            return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_INTERAL_SERVICE_ERROR, e.getMessage(), SmsConstants.FAILURE_MESSAGE, SmsConstants.SMS_SEND_PATH);
        }

    }

    public static String getFromNumber() throws IOException {
        Properties vendorProps = CommonService.loadPropertyFile(CommonConstants.VENDOR_PROPTERY_PATH);
        return vendorProps.getProperty(SmsConstants.TWILIO_PHONE_NUMBER);
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        return (Pattern.matches(SmsConstants.PHONE_NUMBER_REGEX, phoneNumber));
    }


}
