package com.example.umlgenerator.VO;

import java.util.ArrayList;
import java.util.List;

public class MethodVO {
    private String name;
    private String type;
    private List<ParameterVO> parameters;
    private String visibility;

    public MethodVO() {
        this(null, null, null);
    }

    public MethodVO(String name) {
        this(name, null, null);
    }

    public MethodVO(String name, String visibility) {
        this(name, null, visibility);
    }

    public MethodVO(String name, String type, String visibility) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
        parameters = new ArrayList<>();
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<ParameterVO> getParameters() {
        return parameters;
    }

    public void addParameter(String name, String type){
        addParameter(new ParameterVO(name, type));
    }

    public void addParameter(ParameterVO parameter){
        this.parameters.add(parameter);
    }
}
