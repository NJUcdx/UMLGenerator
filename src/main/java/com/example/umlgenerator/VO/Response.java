package com.example.umlgenerator.VO;

import java.util.List;

public class Response {
    private String msg;
    private Result data;
    private boolean success;

    public Response() {
        this("undefined", null, false);
    }

    public Response(String msg, Result data, boolean success) {
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    public static Response success(){
        return new Response(null, null, true);
    }

    public static Response success(String msg){
        return new Response(msg, null, true);
    }

    public static Response success(String msg, Result data){
        return new Response(msg, data, true);
    }

    public static Response failure(String msg, Result data){
        return new Response(msg, data, false);
    }

    public static Response failure(String msg){
        return new Response(msg, null, false);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getData() {
        return data;
    }

    public void setData(Result data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
