package com.example;

import com.example.model.WebhookModel;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WebhookRepository {
    private final Map<String, WebhookModel> messageStore = new ConcurrentHashMap<>();

    // METOD İMZASINI DEĞİŞTİRİYORUZ: Map yerine WebhookModel
    public String saveMessage(WebhookModel webhookModel) {
        String messageId = "msg_" + System.currentTimeMillis();
        messageStore.put(messageId, webhookModel);
        System.out.println("🔵 REPOSITORY: Veri database'e kaydedildi: " + messageId);
        return messageId;
    }

    public Map<String, WebhookModel> getAllMessages() {
        return messageStore;
    }
}