package com.server.exception;

import com.server.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/15 16:50
*/
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public Result appException(AppException ex) {
        log.info("code: {}, message: {}", ex.getCode(), ex.getMessage());
        return Result.build(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result argumentException(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<String> list = new ArrayList<>();

        for (ObjectError obj : allErrors) {
            list.add(obj.getDefaultMessage());
        }
        return Result.build(HttpStatus.FORBIDDEN.value(), list.toString());
    }



}
