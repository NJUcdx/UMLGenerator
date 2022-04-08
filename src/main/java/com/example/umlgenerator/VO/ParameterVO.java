package com.example.umlgenerator.VO;

public class ParameterVO {
    private String name;
    private String type;

    public ParameterVO() {
        this(null, null);
    }

    public ParameterVO(String name, String type) {
        this.name = name;
        this.type = type;
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

}
