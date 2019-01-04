package com.company.app.sms;

import com.company.app.common.CommonConstants;
import com.company.app.common.CommonService;
import com.company.app.common.Recipient;
import com.company.app.common.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/sms")
public class SmsController implements SmsApi{

    @PostMapping("/send")
    public ResponseEntity<Map> sendSms(@RequestBody Sms sms) {
        return SmsService.sendingSms(sms);
    }

    @GetMapping("/get")
    public Sms getSms(@RequestParam(value="content") String content,
                      @RequestParam(value="recipients") Set<Recipient> recipients,
                      @RequestParam(value="sender") Sender sender){
        return new Sms(content, recipients, sender);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateSms(@RequestBody Sms sms){
        return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_OK, null, SmsConstants.SUCCESS_MESSAGE, SmsConstants.SMS_UPDATE_PATH);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map> deleteSms(@RequestBody Sms sms){
        return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_OK, null, SmsConstants.SUCCESS_MESSAGE, SmsConstants.SMS_DELETE_PATH);
    }
}
