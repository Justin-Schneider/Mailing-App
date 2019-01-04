package com.company.app.sms;

import com.company.app.common.Recipient;
import com.company.app.common.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

public interface SmsApi {

    ResponseEntity<Map> sendSms(@RequestBody Sms sms);

    Sms getSms(@RequestParam(value="content") String content,
               @RequestParam(value="recipients") Set<Recipient> recipients,
               @RequestParam(value="sender") Sender sender);

    ResponseEntity<Map> updateSms(@RequestBody Sms sms);

    ResponseEntity<Map> deleteSms(@RequestBody Sms sms);

}
