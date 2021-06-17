package com.justech.mobile.mobileserver.common;


import com.justech.mobile.mobileserver.util.ResultEnum;

/**
 * @ClassName Response
 * @Author: xiaofeng.yang
 * @Date: Create in 9:45 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response(){
    }


    public  Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public  Response<T> of(ResultEnum resultEnum,T data) {
       return new Response(resultEnum.getCode(),resultEnum.getMsg(),data);
    }
    public  Response<T> of(ResultEnum resultEnum,String msg,T data) {
        return new Response(resultEnum.getCode(),msg,data);
    }

}


