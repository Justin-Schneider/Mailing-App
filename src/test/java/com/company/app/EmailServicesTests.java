package com.company.app;

import com.company.app.email.Email;
import com.company.app.email.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;

public class EmailServicesTests {

    private Email basicEmail;
    private Email emailWithCc;
    private Email emailWithMultipleRecipients;

    @BeforeEach
    public void createEmailObjects() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File basicEmailFile = new File("src\\test\\resources\\Emails\\email.json");
        basicEmail = objectMapper.readValue(basicEmailFile, Email.class);

        File emailWithCcFile = new File("src\\test\\resources\\Emails\\emailWithCarbonCopy.json");
        emailWithCc = objectMapper.readValue(emailWithCcFile, Email.class);

        File emailWithMultipleRecipientsFile = new File("src\\test\\resources\\Emails\\emailWithMultipleRecipients.json");
        emailWithMultipleRecipients = objectMapper.readValue(emailWithMultipleRecipientsFile, Email.class);

    }

    //TODO:Find a way not to do some many asserts
    @Test
    public void createEmail() {
        Assertions.assertNotNull(basicEmail.getContent());
        Assertions.assertNotNull(basicEmail.getSender());
        Assertions.assertNotNull(basicEmail.getRecipients());
        Assertions.assertNotNull(basicEmail.getSubject());
    }

    @Test
    public void createEmailWithMultipleRecipients(){
        Assertions.assertNotNull(emailWithMultipleRecipients.getContent());
        Assertions.assertNotNull(emailWithMultipleRecipients.getSender());
        Assertions.assertNotNull(emailWithMultipleRecipients.getRecipients());
        Assertions.assertTrue(emailWithMultipleRecipients.getRecipients().size() > 0);
        Assertions.assertNotNull(emailWithMultipleRecipients.getSubject());
    }

    @Test
    public void createEmailWithCarbonCopy(){
        Assertions.assertNotNull(emailWithCc.getContent());
        Assertions.assertNotNull(emailWithCc.getSender());
        Assertions.assertNotNull(emailWithCc.getRecipients());
        Assertions.assertNotNull(emailWithCc.getCarbonCopy());
        Assertions.assertNotNull(emailWithCc.getSubject());
    }

    @Test
    public void sendBasicEmail(){
        Assertions.assertEquals(EmailService.sendingEmail(basicEmail).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void sendEmailWithCc(){
        Assertions.assertEquals(EmailService.sendingEmail(emailWithCc).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void sendEmailWithMultipleRecipients(){
        Assertions.assertEquals(EmailService.sendingEmail(emailWithMultipleRecipients).getStatusCode(), HttpStatus.OK);
    }

}
