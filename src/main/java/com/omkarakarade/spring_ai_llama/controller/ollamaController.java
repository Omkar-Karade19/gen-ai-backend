package com.omkarakarade.spring_ai_llama.controller;

import com.omkarakarade.spring_ai_llama.dto.PromptRequest;
import com.omkarakarade.spring_ai_llama.dto.ResponseObject;
import com.omkarakarade.spring_ai_llama.service.LlamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ollama")
public class ollamaController {

    @Autowired
    private LlamaService llamaService;

    @PostMapping("/generate-response")
    public ResponseEntity<Object> generateResponse(@RequestBody PromptRequest promptRequest){
        String response = llamaService.generateResponse(promptRequest.getPromptMessage());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(response));
    }
}
