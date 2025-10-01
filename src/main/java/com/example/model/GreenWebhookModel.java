package com.example.model;

public class GreenWebhookModel {
    private String chatId;
    private String text;
    private String type;

    // getter & setter
    public String getChatId() { return chatId; }
    public void setChatId(String chatId) { this.chatId = chatId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
