package com.example.umlgenerator.controller;

import com.example.umlgenerator.service.AipSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AipSpeechController {

    @Autowired
    private AipSpeechService aipSpeechService;

    /**
     * 语音文件上传
     * @param speechFile
     * @return
     */
    @RequestMapping(value = "/saveSpeechFile", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String saveSpeechFile(@RequestParam("file") MultipartFile speechFile) throws IOException {
        if (null == speechFile) {
            return "失败！";
        }
        aipSpeechService.savePcmFile(speechFile);
        return aipSpeechService.getOriginSpeechRecognitionResults();
    }
}
