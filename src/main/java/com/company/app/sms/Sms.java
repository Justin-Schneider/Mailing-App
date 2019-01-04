package com.company.app.sms;

import com.company.app.common.Recipient;
import com.company.app.common.Sender;

import java.util.Set;

public class Sms {

    private String content;
    private Set<Recipient> recipients;
    private Sender sender;

    public Sms() {
    }

    public Sms(String content, Set<Recipient> recipients, Sender sender) {
        this.content = content;
        this.recipients = recipients;
        this.sender = sender;
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

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
