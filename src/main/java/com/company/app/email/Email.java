package com.company.app.email;

import com.company.app.common.Recipient;
import com.company.app.common.Sender;

import java.util.Set;

public class Email {

    private String subject;
    private String content;
    private Set<Recipient> recipients;
    private Set<Recipient> carbonCopy;
    private Sender sender;

    public Email(){
    }

    public Email(String subject, String content, Set<Recipient> recipients, Set<Recipient> carbonCopy, Sender sender) {
        this.subject = subject;
        this.content = content;
        this.recipients = recipients;
        this.carbonCopy = carbonCopy;
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }

    public Set<Recipient> getCarbonCopy() {
        return carbonCopy;
    }

    public void setCarbonCopy(Set<Recipient> carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
