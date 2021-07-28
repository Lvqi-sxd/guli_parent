package com.sxd.demo.excel;

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
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String  sname;
}
