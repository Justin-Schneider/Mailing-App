package com.company.app.email;

import com.company.app.common.Recipient;
import com.company.app.common.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

public interface EmailApi {

    ResponseEntity<Map> postEmail(@RequestBody Email email);

    Email getEmail(@RequestParam(value="content") String subject,
                   @RequestParam(value="content") String content,
                   @RequestParam(value="recipients") Set<Recipient> recipients,
                   @RequestParam(value="recipients", required = false) Set<Recipient> carbonCopy,
                   @RequestParam(value="sender") Sender sender);

    ResponseEntity<Map> updateEmail(@RequestBody Email email);

    ResponseEntity<Map> deleteEmail(@RequestBody Email email);

}
