package com.example.service;

import com.example.model.GreenWebhookModel;
import com.example.model.WebhookResponse;

public interface GreenService {
    WebhookResponse handleIncoming(GreenWebhookModel model);
    String sendText(String chatId, String text);
}
