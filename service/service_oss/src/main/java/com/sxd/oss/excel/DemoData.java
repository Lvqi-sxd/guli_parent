package com.sxd.oss.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Administrator
 * 2021-7-27
 */
@Data
@Component
public class DemoData {
    //设置表头名称
    @ExcelProperty(value = "账号",index = 0)
    private String accessKeyId;
    @ExcelProperty(value = "密码",index = 1)
    private String  accessKeySecret;
}
