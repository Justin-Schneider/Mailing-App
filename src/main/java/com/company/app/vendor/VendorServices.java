package com.company.app.vendor;

import com.company.app.common.CommonConstants;
import com.company.app.common.CommonService;
import com.company.app.common.Recipient;
import com.company.app.sms.SmsConstants;
import com.company.app.sms.SmsService;
import com.twilio.Twilio;
import org.springframework.http.ResponseEntity;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class VendorServices {

    public static void initializeTwilio() throws IOException {
        Properties vendorProps = CommonService.loadPropertyFile(CommonConstants.VENDOR_PROPTERY_PATH);
        Twilio.init(vendorProps.getProperty(SmsConstants.TWILIO_SID_ACCOUNT), vendorProps.getProperty(SmsConstants.TWILIO_AUTH_TOKEN));
    }

    public static ResponseEntity<Map> sendSmsMessages(Set<Recipient> recipient, String smsBody) throws IOException {

        PhoneNumber phoneNumber = null;
        for (Recipient recipients : recipient ) {
            if(SmsService.validatePhoneNumber(recipients.getPhoneNumber())) {
                phoneNumber = new PhoneNumber(recipients.getPhoneNumber());
                Message message = Message.creator(phoneNumber, new PhoneNumber(SmsService.getFromNumber()), smsBody).create();
            } else {
                //TODO: update return message
            }
        }

        return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_OK, null, SmsConstants.SUCCESS_MESSAGE, SmsConstants.SMS_SEND_PATH);
    }

}
