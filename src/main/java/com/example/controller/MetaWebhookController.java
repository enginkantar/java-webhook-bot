package com.example.controller;

import com.example.model.MetaWebhookModel;
import com.example.model.WebhookResponse;
import com.example.repository.MetaMessageRepository;
import com.example.service.MetaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meta")
public class MetaWebhookController {

    private final MetaService metaService;
    private final MetaMessageRepository repo;

    public MetaWebhookController(MetaService metaService, MetaMessageRepository repo) {
        this.metaService = metaService;
        this.repo = repo;
    }

    @GetMapping("/health")
    public String health() {
        return "META OK";
    }

    // Webhook (Meta → bize POST atar)
    @PostMapping("/webhook")
    public WebhookResponse webhook(@RequestBody MetaWebhookModel body) {
        return metaService.handleIncoming(body);
    }

    // Basit listeleme (test için)
    @GetMapping("/messages")
    public Object all() {
        return repo.findAll();
    }

    // Bizden dışarıya mesaj (ileride MetaClient ile gerçek API çağrısı yapılacak)
    @PostMapping("/send")
    public String send(@RequestParam String recipientId, @RequestParam String text) {
        return metaService.sendText(recipientId, text);
    }
}
