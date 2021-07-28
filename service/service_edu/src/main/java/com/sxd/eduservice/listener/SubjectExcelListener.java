package com.sxd.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxd.eduservice.entity.EduSubject;
import com.sxd.eduservice.entity.excel.SubjectData;
import com.sxd.eduservice.service.EduSubjectService;
import com.sxd.servicedase.config.exceptionhandler.GuliException;

import java.util.Map;

/**
 * Administrator
 * 2021-7-28
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作

    public EduSubjectService subjectService;
    public SubjectExcelListener(){};
    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService=subjectService;
    };
    @Override
    public void invoke(SubjectData data, AnalysisContext analysisContext) {
        if(data==null){
            throw new GuliException(20001,"文件数据为空");
        }
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, data.getOneSubjectName());
        if (existOneSubject==null){
            existOneSubject=new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(data.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        //获取一级分类的id值
        String pid=existOneSubject.getId();
        //判断二级分类是否重复
        EduSubject existtwoSubject = this.existtwoSubject(subjectService, data.getTwoSubjectName(), pid);
        if (existtwoSubject==null){
            existtwoSubject=new EduSubject();
            existtwoSubject.setParentId(pid);
            existtwoSubject.setTitle(data.getTwoSubjectName());
            subjectService.save(existtwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return  oneSubject;
    }
    //判断二级分类不能重复添加
    private EduSubject existtwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return  twoSubject;
    }
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
