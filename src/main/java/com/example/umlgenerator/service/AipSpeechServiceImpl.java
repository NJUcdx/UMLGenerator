package com.example.umlgenerator.service;

import com.baidu.aip.speech.AipSpeech;
import com.example.umlgenerator.config.AipSpeechClientConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class AipSpeechServiceImpl implements AipSpeechService {

    @Autowired
    private AipSpeech aipSpeech;

    public String getSpeechRecognitionResults(MultipartFile speechFile) {
        saveFcmFile(speechFile);
        // 调用接口
        JSONObject res = aipSpeech.asr(AipSpeechClientConfig.getPath(), "pcm", 16000, null);
        Map<String, Object> stringObjectMap = res.toMap();
        if (stringObjectMap.get("result") != null) {
            String tmp = stringObjectMap.get("result").toString();
            return tmp.substring(1, tmp.length() - 1);
        }
        return "未识别成功！";
    }

    public void saveFcmFile(MultipartFile speechFile) {

    }


}
