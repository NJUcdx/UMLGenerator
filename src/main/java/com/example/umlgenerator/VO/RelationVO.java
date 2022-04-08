package com.example.umlgenerator.VO;

public class RelationVO {
    private int from;
    private int to;
    private String type;

    public RelationVO(int from, int to, String type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getType() {
        return type;
    }
}
