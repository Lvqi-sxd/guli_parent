package com.sxd.oss.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator
 * 2021-7-27
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现读操作
        String filename="D:\\01.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
