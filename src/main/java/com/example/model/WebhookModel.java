package com.example.model;

import java.util.Map;
import java.util.HashMap;

public class WebhookModel {
    private String message;
    private String user;
    private Map<String, Object> additionalData;

    // Constructor - Boş constructor (Spring için gerekli)
    public WebhookModel() {
        this.additionalData = new HashMap<>();
    }

    // Constructor - Parametreli
    public WebhookModel(String message, String user) {
        this();
        this.message = message;
        this.user = user;
    }

    // Constructor - Tüm parametreler
    public WebhookModel(String message, String user, Map<String, Object> additionalData) {
        this.message = message;
        this.user = user;
        this.additionalData = additionalData != null ? additionalData : new HashMap<>();
    }

    // Getter ve Setter'lar
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData != null ? additionalData : new HashMap<>();
    }

    // Utility method - Ek veri eklemek için
    public void addAdditionalData(String key, Object value) {
        if (this.additionalData == null) {
            this.additionalData = new HashMap<>();
        }
        this.additionalData.put(key, value);
    }

    // toString method - Debug için
    @Override
    public String toString() {
        return "WebhookModel{" +
                "message='" + message + '\'' +
                ", user='" + user + '\'' +
                ", additionalData=" + additionalData +
                '}';
    }
}