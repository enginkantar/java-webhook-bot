package com.example.service;

import com.example.client.GreenClient;
import com.example.model.GreenWebhookModel;
import com.example.model.WebhookResponse;
import com.example.repository.GreenMessageRepository;
import com.example.repository.entity.GreenMessageEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GreenServiceImpl implements GreenService {

    private final GreenMessageRepository repo;
    private final GreenClient greenClient;

    public GreenServiceImpl(GreenMessageRepository repo, GreenClient greenClient) {
        this.repo = repo;
        this.greenClient = greenClient;
    }

    @Override
    public WebhookResponse handleIncoming(GreenWebhookModel model) {
        String id = UUID.randomUUID().toString();
        repo.save(new GreenMessageEntity(id, model.getChatId(), model.getText(), model.getType()));

        // basit echo:
        if (model.getChatId() != null && model.getText() != null) {
            greenClient.sendText(model.getChatId(), "GREEN bot: " + model.getText());
        }
        return new WebhookResponse("OK", id);
    }

    @Override
    public String sendText(String chatId, String text) {
        return greenClient.sendText(chatId, text);
    }
}
