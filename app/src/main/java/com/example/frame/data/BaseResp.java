package com.example.frame.data;

/**
 * 返回值 外层数据
 *
 * @packageName: com.example.frame.data
 * @fileName: BaseRes
 * @date: 2019/7/2  9:59
 * @author: zdj
 */

public class BaseResp<T>{
    public T data;
    public int code;
    public String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
