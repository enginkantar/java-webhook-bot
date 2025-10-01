package com.example.service;

import com.example.client.MetaClient;
import com.example.model.MetaWebhookModel;
import com.example.model.WebhookResponse;
import com.example.repository.MetaMessageRepository;
import com.example.repository.entity.MetaMessageEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MetaServiceImpl implements MetaService {

    private final MetaMessageRepository repo;
    private final MetaClient metaClient;

    public MetaServiceImpl(MetaMessageRepository repo, MetaClient metaClient) {
        this.repo = repo;
        this.metaClient = metaClient;
    }

    @Override
    public WebhookResponse handleIncoming(MetaWebhookModel model) {
        String id = UUID.randomUUID().toString();
        repo.save(new MetaMessageEntity(id, model.getSenderId(), model.getMessage(), model.getTimestamp()));

        // basit echo:
        if (model.getSenderId() != null && model.getMessage() != null) {
            metaClient.sendText(model.getSenderId(), "META bot: " + model.getMessage());
        }
        return new WebhookResponse("OK", id);
    }

    @Override
    public String sendText(String recipientId, String text) {
        return metaClient.sendText(recipientId, text);
    }
}
