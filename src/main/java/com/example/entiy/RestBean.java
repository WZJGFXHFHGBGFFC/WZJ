package com.example.entiy;

import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private boolean success;
    private T messages;

    public RestBean(int status, boolean success, T messages) {
        this.status = status;
        this.success = success;
        this.messages = messages;
    }

    public static <T> RestBean<T> success(){
        return new RestBean<>(200,true,null);
    }

    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,true,data);
    }

    public static <T> RestBean<T> failure(int status){
        return new RestBean<>(status,false,null);
    }

    public static <T> RestBean<T> failure(int status,T data){
        return new RestBean<>(status,false,null);
    }

}
