package com.example.umlgenerator.config;

import com.baidu.aip.speech.AipSpeech;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AipSpeechClientConfig {

    //设置APPID/AK/SK
    @Value("${baidu.APP_ID}")
    private String APP_ID;

    @Value("${baidu.API_KEY}")
    private String API_KEY;

    @Value("${baidu.SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${pcm.path}")
    private String Path;


    @Bean
    public AipSpeech getAipSpeech() {
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    public String getPath() {
        return Path;
    }
}
