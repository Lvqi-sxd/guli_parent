package com.sxd.eduservice.service;

import com.sxd.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxd.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAlllOneTwoSubject();
}
