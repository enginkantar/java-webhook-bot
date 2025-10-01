package com.example.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class MetaClient {

    private final RestTemplate http = new RestTemplate();

    // ENV'den gelecek (application.properties içinde placeholder)
    private final String accessToken;     // META_ACCESS_TOKEN
    private final String phoneNumberId;   // META_PHONE_NUMBER_ID

    public MetaClient() {
        this.accessToken   = System.getenv().getOrDefault("META_ACCESS_TOKEN", "");
        this.phoneNumberId = System.getenv().getOrDefault("META_PHONE_NUMBER_ID", "");
    }

    public String sendText(String toE164, String text) {
        if (toE164 == null || toE164.isBlank() || text == null) return null;

        String url = "https://graph.facebook.com/v19.0/" + phoneNumberId + "/messages";

        HttpHeaders h = new HttpHeaders();
        h.setBearerAuth(accessToken);
        h.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "messaging_product", "whatsapp",
                "to", toE164,                 // ör: 9053XXXXXXX
                "type", "text",
                "text", Map.of("body", text)
        );

        try {
            ResponseEntity<String> resp = http.exchange(url, HttpMethod.POST, new HttpEntity<>(body, h), String.class);
            return resp.getBody(); // dilersen parse edip message id döndürebilirsin
        } catch (RestClientException e) {
            // prod’da log framework’üyle logla
            System.err.println("[META] SIÇTI!! sendText error: " + e.getMessage());
            return null;
        }
    }
}
