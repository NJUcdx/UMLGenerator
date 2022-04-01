package com.example.umlgenerator.service;

import org.springframework.web.multipart.MultipartFile;

public interface AipSpeechService {

    String getSpeechRecognitionResults(MultipartFile speechFile);
}
