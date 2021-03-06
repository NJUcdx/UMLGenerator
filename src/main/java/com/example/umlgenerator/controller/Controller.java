package com.example.umlgenerator.controller;
import com.alibaba.fastjson.JSON;
import com.example.umlgenerator.VO.Response;
import com.example.umlgenerator.VO.Result;
import com.example.umlgenerator.service.AipSpeechService;
import com.example.umlgenerator.service.GraphGenerateService;
import com.example.umlgenerator.service.TokenizeService;
import com.example.umlgenerator.serviceImpl.GraphGenerateServiceImpl;
import com.example.umlgenerator.serviceImpl.TokenizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private AipSpeechService aipSpeechService;
    @Autowired
    private TokenizeService tokenizeService;
    @Autowired
    private GraphGenerateService graphGenerateService;

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public Response generate(@RequestParam("file") MultipartFile speechFile) throws IOException {
        if (null == speechFile) {
            return Response.failure("File not recieved.");
        }
        aipSpeechService.savePcmFile(speechFile);
        String input =  aipSpeechService.getOriginSpeechRecognitionResults();
        String[] sentence = tokenizeService.tokenize(input);
        Result result = graphGenerateService.generate(sentence);
        return Response.success(input, result);
    }

    @RequestMapping(value = "/recognize", method = RequestMethod.POST)
    public Response recognize(@RequestParam("file") MultipartFile speechFile) throws IOException {
        if (null == speechFile) {
            return Response.failure("File not recieved.");
        }
        aipSpeechService.savePcmFile(speechFile);
        String input =  aipSpeechService.getOriginSpeechRecognitionResults();
        return Response.success(input);
    }

    @RequestMapping(value = "/generateByString", method = RequestMethod.POST)
    public Response generateByString(@RequestParam("s") String s) throws IOException {
        String[] sentence = tokenizeService.tokenize(s);
        Result result = graphGenerateService.generate(sentence);
        return Response.success("success", result);
    }


    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public Response clear(){
        graphGenerateService.clear();
        return Response.success();
    }

    public static void main(String[] args){
        TokenizeService tokenizeService = new TokenizeServiceImpl();
        GraphGenerateService graphGenerateService = new GraphGenerateServiceImpl();
        String s = "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????run?????????????????????????????????sit?????????????????????????????????height???????????????????????????????????????run????????????????????????????????????time????????????????????????????????????????????????";
        String[] sentence = tokenizeService.tokenize(s);
        Result result = graphGenerateService.generate(sentence);
        String jsonObj = JSON.toJSONString(result);
        System.out.println();
    }
}
