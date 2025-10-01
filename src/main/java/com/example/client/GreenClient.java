package com.example.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GreenClient {

    private final RestTemplate http = new RestTemplate();

    // ENV'den gelecek (application.properties yerine direkt ENV okuyoruz)
    private final String instanceId;  // GREEN_INSTANCE_ID
    private final String apiToken;    // GREEN_API_TOKEN

    public GreenClient() {
        this.instanceId = System.getenv().getOrDefault("GREEN_INSTANCE_ID", "");
        this.apiToken   = System.getenv().getOrDefault("GREEN_API_TOKEN", "");
    }

    // https://api.green-api.com/waInstance{ID}/sendMessage/{TOKEN}
    public String sendText(String chatId, String message) {
        if (chatId == null || chatId.isBlank() || message == null) return null;

        String url = String.format(
                "https://api.green-api.com/waInstance%s/sendMessage/%s",
                instanceId, apiToken
        );

        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "chatId", chatId,        // ör: "9053XXXXXXX@c.us"
                "message", message
        );

        try {
            ResponseEntity<String> resp = http.exchange(url, HttpMethod.POST, new HttpEntity<>(body, h), String.class);
            return resp.getBody();
        } catch (RestClientException e) {
            System.err.println("[GREEN] SICTI !!! sendText error: " + e.getMessage());
            return null;
        }
    }
}
