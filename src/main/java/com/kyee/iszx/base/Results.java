package com.kyee.iszx.base;

import java.util.List;

import com.kyee.iszx.Enums.ErrorCodeEnum;

public class Results {

    public static <T> Result<T> newSuccessResult(T data) {
        return newResult(data, "success", true,0);
    }
    
    public static <T> Result<T> newSuccessResult() {
        return newResult(null, "success", true,0);
    }
    
    public static <T> Result<T> newSuccessResult(T data,Integer count) {
        return newResult(data, "success", true,0,count);
    }

    public static <T> Result<T> newFailedResult(String msg) {
        return newResult(null, msg, false,-10000);
    }

    public static <T> Result<T> newFailedResult(Integer code, String msg) {
        return newResult(null, msg, false, code);
    }

    public static <T> Result newFailedResult(ErrorCodeEnum errorCodeEnum) {
        return newResult(null, errorCodeEnum.getDescription(), false, errorCodeEnum.getCode());
    }

    public static <T> Result<T> newResult(T data, String msg, boolean success,Integer code) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(code);
        result.setSuccess(success);
        result.setMsg(msg);
        try {
        	if(data != null && (data instanceof List)) {
        		List listDat = (List) data;
            	result.setCount(listDat.size());
        	}
        }catch (Exception e) {}
        return result;
    }
    public static <T> Result<T> newResult(T data, String msg, boolean success,Integer code,Integer count) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(code);
        result.setSuccess(success);
        result.setMsg(msg);
        result.setCount(count);
        return result;
    }
}
