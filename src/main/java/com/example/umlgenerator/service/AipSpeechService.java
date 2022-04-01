package com.example.umlgenerator.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AipSpeechService {

    String getOriginSpeechRecognitionResults();

    void savePcmFile(MultipartFile speechFile) throws IOException;
}
