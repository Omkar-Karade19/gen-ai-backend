package com.omkarakarade.spring_ai_llama.controller;

import com.omkarakarade.spring_ai_llama.dto.PromptRequest;
import com.omkarakarade.spring_ai_llama.service.GeminiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gemini-ai")
public class GeminiController {

    private static final Logger log = LoggerFactory.getLogger(GeminiController.class);

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/generate-response")
    public ResponseEntity<Object> askQuestion(@RequestBody PromptRequest promptRequest){
        String response = geminiService.getAnswer(promptRequest.getPromptMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/eligibility")
    public ResponseEntity<Object> checkEligibility(@RequestParam double income, @RequestParam double creditScore){
        String prompt = "Given an income of " + income + " and a credit score of " + creditScore + ", is the user eligible for a loan? Provide a brief response.";
        String response = geminiService.getAnswer(prompt);
        log.info("API Response : {}", response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/movie-recommend")
    public ResponseEntity<Object> recommendMovie(@RequestParam String genre, @RequestParam String language){
        String prompt = "Given the " + genre +" and language " +language+ " suggest 10 movies, return only the list of movies";
        String response = geminiService.getAnswer(prompt);
        log.info("Movie recommendation API Response : {}", response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
