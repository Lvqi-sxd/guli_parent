package com.sxd.servicedase.config.exceptionhandler;


import com.sxd.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Administrator
 * 2021-7-21
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody  //为了能够返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //特定的异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody  //为了能够返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }
    //自定义异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody  //为了能够返回数据
    public R error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}