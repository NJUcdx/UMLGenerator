package com.example.umlgenerator.service;

import com.example.umlgenerator.VO.ObjectVO;
import com.example.umlgenerator.VO.Result;

import java.util.List;

public interface GraphGenerateService {
    Result generate(String[] sentence);

    void clear();
}
