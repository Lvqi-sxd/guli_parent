package com.sxd.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * Administrator
 * 2021-7-28
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行读取excel中内容，从第二行读取
    @Override
    public void invoke(DemoData data , AnalysisContext analysisContext) {
        System.out.println("内容"+data);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+ headMap);
    }
    //读取完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
