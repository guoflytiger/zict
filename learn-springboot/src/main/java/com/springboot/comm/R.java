package com.springboot.comm;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;
    public R() {
        this.code = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
    }
    public R(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(T data){
        this.code = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }
    public R(T data,HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.msg = httpStatus.getReasonPhrase();
        this.data = data;
    }
    public R(T data,String msg){
        this.code = HttpStatus.OK.value();
        this.msg = msg;
        this.data = data;
    }
    public static <T> R.RBuilder<T> builder(){
        return new R.RBuilder<T>();
    }

    public static class RBuilder<T> {
        private int code;
        private String msg;
        private T data;
        RBuilder(){
        }
        public R.RBuilder<T> code(int code){
            this.code = code;
            return this;
        }
        public R.RBuilder<T> msg(String msg){
            this.msg = msg;
            return this;
        }
        public R.RBuilder<T> data(T data){
            this.data = data;
            return this;
        }

        public R<T> build(){
            return new R<T>(this.code,this.msg,this.data);
        }
    }
}
