package com.company.app;

import com.company.app.sms.Sms;
import com.company.app.sms.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;

public class SmsServicesTests {

    private Sms basicSms;
    private Sms smsWithMulitpleRecipients;

    @BeforeEach
    public void createEmailObjects() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File basicSmsFile = new File("src\\test\\resources\\Sms\\sms.json");
        basicSms = objectMapper.readValue(basicSmsFile, Sms.class);

        File smsWithMultipleRecipientsFile = new File("src\\test\\resources\\Sms\\smsWithMulitpleRecipients.json");
        smsWithMulitpleRecipients = objectMapper.readValue(smsWithMultipleRecipientsFile, Sms.class);

    }

    @Test
    public void createEmail() {
        Assertions.assertNotNull(basicSms.getContent());
        Assertions.assertNotNull(basicSms.getSender());
        Assertions.assertNotNull(basicSms.getRecipients());
    }

    @Test
    public void createEmailWithMultipleRecipients(){
        Assertions.assertNotNull(smsWithMulitpleRecipients.getContent());
        Assertions.assertNotNull(smsWithMulitpleRecipients.getSender());
        Assertions.assertNotNull(smsWithMulitpleRecipients.getRecipients());
        Assertions.assertTrue(smsWithMulitpleRecipients.getRecipients().size() > 0);
    }

    @Test
    public void sendBasicEmail(){
        Assertions.assertEquals(SmsService.sendingSms(basicSms).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void sendEmailWithCc(){
        Assertions.assertEquals(SmsService.sendingSms(smsWithMulitpleRecipients).getStatusCode(), HttpStatus.OK);
    }

}
