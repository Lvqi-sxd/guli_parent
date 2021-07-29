package com.sxd.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sxd.eduservice.entity.EduSubject;
import com.sxd.eduservice.entity.excel.SubjectData;
import com.sxd.eduservice.entity.subject.OneSubject;
import com.sxd.eduservice.entity.subject.TwoSubject;
import com.sxd.eduservice.listener.SubjectExcelListener;
import com.sxd.eduservice.mapper.EduSubjectMapper;
import com.sxd.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in= file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();

        }


    }
    //课程分类列表(树形)
    @Override
    public List<OneSubject> getAlllOneTwoSubject() {
        //查询所有一级分类 parentid = 0
        QueryWrapper<EduSubject> wrapperOne=new QueryWrapper();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
//        this.list(wrapperOne)
        //查询所有二级分类parentid != 0
        QueryWrapper<EduSubject> wrapperTwo=new QueryWrapper();
        wrapperTwo.ne ("parent_id",0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //创建List集合 用于最终存储分装数据
        List<OneSubject> finalSubjectList=new ArrayList<>();
        //分装一级分类
        for (EduSubject eduOneSubject : oneSubjectList) {
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(eduOneSubject,oneSubject);
            //分装二级分类
            List<TwoSubject> finalTwoSubjectList=new ArrayList<>();
            for (EduSubject eduTwosubject : twoSubjectList) {
                if(eduTwosubject.getParentId().equals(eduOneSubject.getId())){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(eduTwosubject,twoSubject);
                    System.out.println(twoSubject);
                    finalTwoSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(finalTwoSubjectList);
            finalSubjectList.add(oneSubject);
        }

        return finalSubjectList;
    }
}
