package com.sxd.servicedase.config.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Administrator
 * 2021-7-21
 */
@Data
@AllArgsConstructor//生成有参构造方法
@NoArgsConstructor//生成无参构造方法
public class GuliException extends RuntimeException{
    private Integer code;

    private String msg;

}
