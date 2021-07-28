package com.sxd.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.sxd.eduservice.entity.EduSubject;
import com.sxd.eduservice.entity.excel.SubjectData;
import com.sxd.eduservice.listener.SubjectExcelListener;
import com.sxd.eduservice.mapper.EduSubjectMapper;
import com.sxd.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
