package com.example.controller;

import com.example.model.GreenWebhookModel;
import com.example.model.WebhookResponse;
import com.example.repository.GreenMessageRepository;
import com.example.service.GreenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/green")
public class GreenWebhookController {

    private final GreenService greenService;
    private final GreenMessageRepository repo;

    public GreenWebhookController(GreenService greenService, GreenMessageRepository repo) {
        this.greenService = greenService;
        this.repo = repo;
    }

    @GetMapping("/health")
    public String health() {
        return "GREEN OK";
    }

    // Webhook (GREEN-API → bize POST atar)
    @PostMapping("/webhook")
    public WebhookResponse webhook(@RequestBody GreenWebhookModel body) {
        return greenService.handleIncoming(body);
    }

    // Basit listeleme (test için)
    @GetMapping("/messages")
    public Object all() {
        return repo.findAll();
    }

    // Bizden dışarıya mesaj (ileride GreenClient ile gerçek API çağrısı yapılacak)
    @PostMapping("/send")
    public String send(@RequestParam String chatId, @RequestParam String text) {
        return greenService.sendText(chatId, text);
    }
}
