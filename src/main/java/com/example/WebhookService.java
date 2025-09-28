package com.example;

import com.example.model.WebhookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    @Autowired
    private WebhookRepository webhookRepository;

    // METOD İMZASINI DEĞİŞTİRİYORUZ: Map yerine WebhookModel
    public String processWebhook(WebhookModel webhookModel) {
        System.out.println("🟡 SERVICE: Webhook işleniyor...");

        // İş mantığı burada
        if (webhookModel.getMessage() != null && webhookModel.getMessage().contains("test")) {
            System.out.println("🟡 SERVICE: Test mesajı tespit edildi");
        }

        String messageId = webhookRepository.saveMessage(webhookModel);
        System.out.println("🟡 SERVICE: İşlem tamamlandı, ID: " + messageId);
        return messageId;
    }
}