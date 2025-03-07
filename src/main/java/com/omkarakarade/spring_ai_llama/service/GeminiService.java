package com.omkarakarade.spring_ai_llama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class GeminiService {

    // Inject the API URL
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    // Inject the API KEY
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Autowired
    private WebClient webClient;

    // Prepare the Prompt Request accepted by the Gemini API
    public String getAnswer(String promptQuestion){

        // Construct the Request Payload
        Map<String,Object> requestBody = Map.of(
                "contents",new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text",promptQuestion)
                        })
                }
        );

        // Make API Call
        String geminiResponse = webClient.post()
                .uri(geminiApiUrl+geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Return the Response
        return geminiResponse;
    }
}
