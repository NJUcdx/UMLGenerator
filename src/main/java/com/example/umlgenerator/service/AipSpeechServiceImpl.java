package com.example.umlgenerator.service;

import com.baidu.aip.speech.AipSpeech;
import com.example.umlgenerator.config.AipSpeechClientConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@Service
public class AipSpeechServiceImpl implements AipSpeechService {

    @Autowired
    private AipSpeech aipSpeech;

    public String getOriginSpeechRecognitionResults() {
        // 调用接口
        JSONObject res = aipSpeech.asr(AipSpeechClientConfig.getPath(), "pcm", 16000, null);
        Map<String, Object> stringObjectMap = res.toMap();
        if (stringObjectMap.get("result") != null) {
            String tmp = stringObjectMap.get("result").toString();
            return tmp.substring(1, tmp.length() - 1);
        }
        return "未识别成功！";
    }

    @Override
    public void savePcmFile(MultipartFile speechFile) throws IOException {
        OutputStream os;
        InputStream inputStream = speechFile.getInputStream();
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 文件实现上传
        os = new FileOutputStream(AipSpeechClientConfig.getPath());
        // 开始读取
        while ((len = inputStream.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
    }
}
