package com.example.umlgenerator.VO;

import java.util.List;

public class Result {
    private List<ObjectVO> objects;
    private List<RelationVO> relations;

    public Result(List<ObjectVO> objects, List<RelationVO> relations) {
        this.objects = objects;
        this.relations = relations;
    }

    public List<ObjectVO> getObjects() {
        return objects;
    }

    public List<RelationVO> getRelations() {
        return relations;
    }
}
