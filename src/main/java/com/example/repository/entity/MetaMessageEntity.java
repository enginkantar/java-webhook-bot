package com.example.repository.entity;

public class MetaMessageEntity {
    private String id;          // internal UUID
    private String senderId;
    private String message;
    private long   timestamp;

    public MetaMessageEntity(String id, String senderId, String message, long timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getId() { return id; }
    public String getSenderId() { return senderId; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }
}
