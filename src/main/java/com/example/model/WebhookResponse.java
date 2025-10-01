package com.example.model;

public class WebhookResponse {
    private String status;
    private String messageId;

    public WebhookResponse(String status, String messageId) {
        this.status = status;
        this.messageId = messageId;
    }

    public String getStatus() { return status; }
    public String getMessageId() { return messageId; }
}
