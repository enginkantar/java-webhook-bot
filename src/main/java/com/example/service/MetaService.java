package com.example.service;

import com.example.model.MetaWebhookModel;
import com.example.model.WebhookResponse;

public interface MetaService {
    WebhookResponse handleIncoming(MetaWebhookModel model);
    String sendText(String recipientId, String text);
}
