package com.example.umlgenerator.config;

import com.baidu.aip.speech.AipSpeech;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AipSpeechClientConfig {

    //设置APPID/AK/SK
    private static final String APP_ID = "25888552";
    private static final String API_KEY = "4TqRGPeVEfXRuIksgAzRDkFZ";
    private static final String SECRET_KEY = "TzqFG6Oe2C30UbwggDBe8ouYADzlL87s";
    private static final String Path = "src/main/resources/result.pcm";


    @Bean
    public AipSpeech getAipSpeech() {
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    public static String getPath() {
        return Path;
    }
}
