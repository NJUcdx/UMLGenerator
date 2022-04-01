package com.example.umlgenerator.controller;

import com.example.umlgenerator.service.AipSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AipSpeechController {

    @Autowired
    private AipSpeechService aipSpeechService;

    @GetMapping("/getSpeech")
    public String getSpeech(MultipartFile speechFile) {
        return aipSpeechService.getSpeechRecognitionResults(speechFile);
    }
}
