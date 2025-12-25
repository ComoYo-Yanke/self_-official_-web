package com.comoyo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


/**
 * 后端统一返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private static final Logger log = LoggerFactory.getLogger(Result.class);
    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据
    private LocalDateTime date;
    
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.date = LocalDateTime.now();
        return result;
    }
    
    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", date=" + date +
                '}';
    }
    
    public static Result success(Object object) {
        
        
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        result.date = LocalDateTime.now();
        return result;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public void setDate(LocalDateTime date) {this.date = date;}
    
    public LocalDateTime getDate() {return date;}
    
    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }
    
    
}

