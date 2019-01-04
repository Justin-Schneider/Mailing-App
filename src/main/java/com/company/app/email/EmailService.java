package com.company.app.email;

import com.company.app.common.CommonConstants;
import com.company.app.common.CommonService;
import com.company.app.common.Recipient;
import org.springframework.http.ResponseEntity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;

public class EmailService {

    public static ResponseEntity<Map> sendingEmail(Email email) {

        Properties props = null;
        Session session = null;
        try {
            props = EmailService.getPropertiesForEmail();
            session = Session.getDefaultInstance(props);
        } catch (IOException e) {
            return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_INTERAL_SERVICE_ERROR, e.getMessage(), EmailConstants.FAILURE_MESSAGE, EmailConstants.EMAIL_SEND_PATH);
        }

        MimeMessage messages = null;
        try {
            messages = EmailService.getMessagesForEmail(session, email);
        } catch (MessagingException | UnsupportedEncodingException e) {
            return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_BAD_REQUEST, e.getMessage(), EmailConstants.FAILURE_MESSAGE, EmailConstants.EMAIL_SEND_PATH);
        }

        return EmailService.sendEmail(session, messages);
    }

    private static Properties getPropertiesForEmail() throws IOException {
        Properties vendorProps = CommonService.loadPropertyFile(CommonConstants.VENDOR_PROPTERY_PATH);
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", vendorProps.getProperty(EmailConstants.SMTP_PORT));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        return props;
    }

    private static MimeMessage getMessagesForEmail(Session session, Email email) throws MessagingException, UnsupportedEncodingException {
        String sendersName = email.getSender().getName();
        String subject = email.getSubject();
        String content = email.getContent();
        String sendersEmail = email.getSender().getEmail();

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(sendersEmail, sendersName));
        for (Recipient recipient : email.getRecipients()) {
            if(EmailService.validateEmailAddress(recipient.getEmail())){
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail()));
            } else {
                //TODO: say in the success/failure message someone was left out
            }
        }
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(content, EmailConstants.MIME_TYPE);

        return mimeMessage;
    }

    private static boolean validateEmailAddress(String emailAddress){
        return (Pattern.matches(EmailConstants.EMAIL_REGEX, emailAddress));
    }

    private static ResponseEntity<Map> sendEmail(Session session, MimeMessage message) {
        Transport transport = null;
        try {
            transport = session.getTransport();
            Properties props = CommonService.loadPropertyFile(CommonConstants.VENDOR_PROPTERY_PATH);
            transport.connect(props.getProperty(EmailConstants.SMTP_HOST), props.getProperty(EmailConstants.SMTP_USERNAME), props.getProperty(EmailConstants.SMTP_PASSWORD));
            transport.sendMessage(message, message.getAllRecipients());
            return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_OK, null, EmailConstants.SUCCESS_MESSAGE, EmailConstants.EMAIL_SEND_PATH);
        } catch (MessagingException | IOException e) {
            return CommonService.getResponse(new Date(), CommonConstants.HTTP_STATUS_INTERAL_SERVICE_ERROR, e.getMessage(), EmailConstants.FAILURE_MESSAGE, EmailConstants.EMAIL_SEND_PATH);
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
