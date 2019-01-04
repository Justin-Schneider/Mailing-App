package com.company.app.email;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.company.app.common.CommonConstants;
import com.company.app.common.CommonService;
import com.company.app.common.Recipient;
import com.company.app.common.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController implements EmailApi{

    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/send")
    public ResponseEntity<Map> postEmail(@RequestBody Email email) {
        return EmailService.sendingEmail(email);
    }

    @GetMapping("/get")
    public Email getEmail(@RequestParam(value="subject") String subject,
                          @RequestParam(value="content") String content,
                          @RequestParam(value="recipients") Set<Recipient> recipients,
                          @RequestParam(value="carbonCopy", required = false) Set<Recipient> carbonCopy,
                          @RequestParam(value="sender") Sender sender){
        return new Email(subject, content, recipients, carbonCopy, sender);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateEmail(@RequestBody Email email){
        return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_OK, null, EmailConstants.SUCCESS_MESSAGE, EmailConstants.EMAIL_UPDATE_PATH);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map> deleteEmail(@RequestBody Email email){
        return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_OK, null, EmailConstants.SUCCESS_MESSAGE, EmailConstants.EMAIL_DELETE_PATH);
    }

}
