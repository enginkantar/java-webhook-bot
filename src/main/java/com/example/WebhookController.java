package com.example;

import com.example.model.WebhookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private WebhookService webhookService;  // ← BU SATIRI EKLEYİN!

    private final Map<String, WebhookModel> messageStore = new ConcurrentHashMap<>();

    // Webhook'u doğrulama için (GET istekleri)
    @GetMapping
    public String verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {

        System.out.println("Webhook Doğrulama İsteği Geldi:");
        System.out.println("Mode: " + mode);
        System.out.println("Token: " + token);
        System.out.println("Challenge: " + challenge);

        if ("my_secret_token".equals(token)) {
            return challenge;
        }

        return "Error: Token geçersiz";
    }

    // Webhook mesajlarını almak için (POST istekleri)
    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookModel webhookModel) {

        System.out.println("🔴 CONTROLLER: İstek geldi!");

        // Service katmanını kullan
        String messageId = webhookService.processWebhook(webhookModel);

        System.out.println("🔴 CONTROLLER: Cevap hazır: " + messageId);
        return ResponseEntity.ok("Katmanlı Webhook alındı: " + messageId);
    }

    // Alınan mesajları görüntülemek için
    @GetMapping("/messages")
    public Map<String, WebhookModel> getMessages() {
        return messageStore;
    }

    // Health check için
    @GetMapping("/health")
    public String healthCheck() {
        return "Webhook sunucusu çalışıyor! Time: " + System.currentTimeMillis();
    }
}