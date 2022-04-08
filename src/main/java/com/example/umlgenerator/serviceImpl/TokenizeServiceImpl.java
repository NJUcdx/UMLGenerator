package com.example.umlgenerator.serviceImpl;

import com.example.umlgenerator.service.TokenizeService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TokenizeServiceImpl implements TokenizeService {

    @Override
    public String[] tokenize(String s) {
        String curDir = System.getProperty("user.dir");
        String result = "";

        String python = "C:\\Users\\cdx\\anaconda3\\python.exe";
        String pythonFile = curDir+"\\NounExtract\\main.py";
//        String input_string = "添加一个名为Person的接口，它继承AbstractPerson类，有一个名为run的公有方法，有一个名为sit的私有方法，有一个height类型的公有成员变量，run方法有一个类型为int的名为time的参数，有一个类型为bool的名为result的返回值。";

        String[] arguments = {python, pythonFile, s};
        List<String> strings = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            int res = process.waitFor();
            if(res != 0){
                throw new InterruptedException();
            }
            BufferedInputStream in = new BufferedInputStream(process.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String lineStr = null;
            while ((lineStr = br.readLine()) != null) {
                result = lineStr;
                String[] words = result.split(" ");
                strings.addAll(Arrays.asList(words));
            }
            br.close();
            in.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return strings.toArray(new String[]{});
    }

//    public static void main(String[] args) {
//        String curDir = System.getProperty("user.dir");
//        System.out.println("你当前的工作目录为 :" + curDir);
//        String result = "";
//
//        String python = "C:\\Users\\cdx\\anaconda3\\python.exe";
//        String pythonFile = curDir+"\\NounExtract\\main.py";
//        String input_string = "添加一个名为Person的接口，它继承AbstractPerson类，有一个名为run的公有方法，有一个名为sit的私有方法，有一个height类型的公有成员变量，run方法有一个类型为int的名为time的参数，有一个类型为bool的名为result的返回值。";
//
//        String[] arguments = {python, pythonFile, input_string};
//        List<String[]> strings = new ArrayList<>();
//        try {
//            Process process = Runtime.getRuntime().exec(arguments);
//            int res = process.waitFor();
//            if(res != 0){
//                throw new InterruptedException();
//            }
//            BufferedInputStream in = new BufferedInputStream(process.getInputStream());
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String lineStr = null;
//            while ((lineStr = br.readLine()) != null) {
//                result = lineStr;
//                strings.add(result.split(" "));
//            }
//            br.close();
//            in.close();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (String[] s: strings){
//            System.out.println(Arrays.toString(s));
//        }
//    }
}
