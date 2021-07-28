package com.sxd.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator
 * 2021-7-27
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        // 1设置写入文件夹地址和excel文件名称
//        String filename="D:\\write.xlsx";
//        //2调用easyexcel方法
//        //write方法中两个参数:第一个参数文件路径名称，第二参数实体类class
//        EasyExcel.write(filename,DemoData.class).sheet("学生").doWrite(getData());


        //实现读操作
        String filename="D:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();

    }
    //创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setSname("Make"+i);
            list.add(data);
        }
        return list;
    }
}
