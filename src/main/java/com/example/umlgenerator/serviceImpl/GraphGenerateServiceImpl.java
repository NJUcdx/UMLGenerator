package com.example.umlgenerator.serviceImpl;

import com.example.umlgenerator.VO.*;
import com.example.umlgenerator.service.GraphGenerateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class GraphGenerateServiceImpl implements GraphGenerateService {
    private final HashMap<String, String> keyword2operation;
    private final HashSet<String> names;
    private final HashMap<String, String> typeMap;
    private HashSet<String> attributes;
    private HashSet<String> methods;
    private int key;

    private HashMap<String, MethodVO> name2Method;
    private HashMap<String, ObjectVO> name2Object;

    private List<ObjectVO> objects;
    private List<RelationVO> relations;

    public GraphGenerateServiceImpl() {
        this.keyword2operation = new HashMap<>();
        keyword2operation.put("新建", "create");
        keyword2operation.put("新增", "create");
        keyword2operation.put("添加", "create");
        keyword2operation.put("创建", "create");
        keyword2operation.put("有", "add");

        names = new HashSet<>();
        names.add("名为");
        names.add("叫做");

        attributes = new HashSet<>();
        attributes.add("成员变量");
        attributes.add("成员属性");
        attributes.add("属性");
        attributes.add("变量");

        methods = new HashSet<>();
        methods.add("函数");
        methods.add("方法");
        methods.add("成员函数");
        methods.add("成员方法");

        typeMap = new HashMap<>();
        typeMap.put("整数", "int");
        typeMap.put("小数", "float");
        typeMap.put("字符", "char");
        typeMap.put("字符串", "String");
        typeMap.put("布尔", "boolean");

        key = 1;

        name2Method = new HashMap<>();
        name2Object = new HashMap<>();

        objects = new ArrayList<>();
        relations = new ArrayList<>();
    }

    @Override
    public Result generate(String[] sentence) {
        if (sentence.length == 0) {
            return new Result(null, null);
        }
        int i = 0;
        // 操作类型
        // 0 -> 未识别
        // 1 -> 创建对象
        // 2 -> 添加成员属性或方法
        // 3 -> 为方法添加参数
        int flag = 0;
        String method_name = "";
        while (i < sentence.length) {
            if (keyword2operation.containsKey(sentence[i])) {
                if (keyword2operation.get(sentence[i]).equals("create")) {
                    flag = 1;
                } else if (keyword2operation.get(sentence[i]).equals("add")) {
                    flag = Math.max(2, flag);
                    if (i > 1 && name2Method.containsKey(sentence[i - 2]) && methods.contains(sentence[i - 1])) {
                        method_name = sentence[i - 2];
                        flag = 3;
                    }
                }
            }
            ++i;
            if (flag == 1) {
                ObjectVO object = new ObjectVO(key);
                ++key;
                while (i < sentence.length && !keyword2operation.containsKey(sentence[i])) {
                    if (names.contains(sentence[i]) && object.getName() == null) {
                        ++i;
                        object.setName(sentence[i]);
                    } else if (sentence[i].equals("接口") && object.getType() == null) {
                        ++i;
                        object.setType("interface");
                    } else if (sentence[i].equals("抽象类") && object.getType() == null) {
                        object.setType("abstract class");
                    }
                    else if (sentence[i].equals("继承") && object.getExtendsObject() == null && name2Object.containsKey(sentence[i + 1])) {
                        ++i;
                        object.setExtendsObject(name2Object.get(sentence[i]));
                        relations.add(new RelationVO(object.getKey(), object.getExtendsObject().getKey(), "generalization"));
                    } else if (sentence[i].equals("实现")) {
                        ++i;
//                        object.addImplementsObject(name2Object.get(sentence[i]));
                        relations.add(new RelationVO(object.getKey(), name2Object.get(sentence[i]).getKey(), "realization"));
                    }else if (sentence[i].equals("关联") && name2Object.containsKey(sentence[i + 1])) {
                        ++i;
                        relations.add(new RelationVO(object.getKey(), name2Object.get(sentence[i]).getKey(), "association"));
                    } else{
                        ++i;
                    }
                }
                objects.add(object);
                name2Object.put(object.getName(), object);

            } else if (flag == 2) {
                if (objects.size() == 0) {
                    continue;
                }
                ObjectVO object = objects.get(objects.size() - 1);
                String name = null;
                String type = null;
                String visibility = null;
                while (i < sentence.length && !keyword2operation.containsKey(sentence[i])) {
                    if (sentence[i].equals("公有")){
                        ++i;
                        visibility = "public";
                    }else if (sentence[i].equals("私有")){
                        ++i;
                        visibility = "private";
                    }else if (names.contains(sentence[i])){
                        ++i;
                        name = sentence[i];
                    }else if (sentence[i].equals("类型")){
                        type = sentence[i - 1];
                        ++i;
                    }else if (methods.contains(sentence[i]) && !object.getMethods().contains(name2Method.get(sentence[i - 1]))){
                        MethodVO methodVO = new MethodVO(name, "void", visibility);
                        object.addMethod(methodVO);
                        name2Method.put(name, methodVO);
                        ++i;
                    }else if (attributes.contains(sentence[i])){
                        if(typeMap.containsKey(type)){
                            type = typeMap.get(type);
                        }else if(name2Object.containsKey(type)){
                            relations.add(new RelationVO(object.getKey(), name2Object.get(type).getKey(), "association"));
                        }
                        object.addProperty(new PropertyVO(name, type, visibility));
                        ++i;
                    }else{
                        ++i;
                    }
                }
            }else if (flag == 3){
                MethodVO method = name2Method.get(method_name);
                String return_type = null;
                String name = null;
                String type = null;
                String visibility = null;
                while (i < sentence.length && !keyword2operation.containsKey(sentence[i])){
                    if(sentence[i].equals("返回值")){
                        if(i > 1 && sentence[i - 1].equals("类型")){
                            return_type = sentence[i - 2];
                        }else if(i > 0){
                            return_type = sentence[i - 1];
                        }
                        if(typeMap.containsKey(return_type)){
                            return_type = typeMap.get(return_type);
                        }
                        ++i;
                        method.setType(return_type);
                        continue;
                    }
                    else if (names.contains(sentence[i])){
                        ++i;
                        name = sentence[i];
                    }
                    else if (sentence[i].equals("类型")){
                        type = sentence[i - 1];
                        if(typeMap.containsKey(type)){
                            type = typeMap.get(type);
                        }
                        ++i;
                    }else {
                        ++i;
                    }
                    if(name != null && type != null){
                        method.addParameter(new ParameterVO(name, type));
                        name = null;
                        type = null;
                    }
                }
            }
        }

        return new Result(objects, relations);
    }

    public void clear(){
        this.name2Object.clear();
        this.key = 1;
        this.relations.clear();
        this.objects.clear();
        this.name2Method.clear();
    }
}
