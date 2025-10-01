package com.example.repository.entity;

public class GreenMessageEntity {
    private String id;       // internal UUID
    private String chatId;
    private String text;
    private String type;

    public GreenMessageEntity(String id, String chatId, String text, String type) {
        this.id = id;
        this.chatId = chatId;
        this.text = text;
        this.type = type;
    }
    public String getId() { return id; }
    public String getChatId() { return chatId; }
    public String getText() { return text; }
    public String getType() { return type; }
}
