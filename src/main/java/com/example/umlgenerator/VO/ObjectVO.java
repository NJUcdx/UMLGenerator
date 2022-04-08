package com.example.umlgenerator.VO;

import java.util.ArrayList;
import java.util.List;

public class ObjectVO {
    private int key;
    private String name;
    private String type;
    private List<PropertyVO> properties;
    private List<MethodVO> methods;
    private ObjectVO extendsObject;
//    private List<ObjectVO> implementsObject;

    public ObjectVO() {
        this(-1, null, null);
    }

    public ObjectVO(int key) {
        this(key, null, null);
    }

    public ObjectVO(int key, String name, String type) {
        this.key = key;
        this.name = name;
        this.type = type;
        properties = new ArrayList<>();
        methods = new ArrayList<>();
//        implementsObject = new ArrayList<>();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PropertyVO> getProperties() {
        return properties;
    }

    public List<MethodVO> getMethods() {
        return methods;
    }

    public void addProperty(String name, String type, String visibility) {
        addProperty(new PropertyVO(name, type, visibility));
    }

    public void addProperty(PropertyVO property) {
        this.properties.add(property);
    }

    public void addMethod(String name, String type, String visibility) {
        addMethod(new MethodVO(name, type, visibility));
    }

    public void addMethod(MethodVO method) {
        methods.add(method);
    }

    public ObjectVO getExtendsObject() {
        return extendsObject;
    }

    public void setExtendsObject(ObjectVO extendsObject) {
        this.extendsObject = extendsObject;
    }

//    public List<ObjectVO> getImplementsObject() {
//        return implementsObject;
//    }
//
//    public void addImplementsObject(ObjectVO implementsObject) {
//        this.implementsObject.add(implementsObject);
//    }
}
