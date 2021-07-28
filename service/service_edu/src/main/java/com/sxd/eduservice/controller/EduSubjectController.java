package com.sxd.eduservice.controller;


import com.sxd.commonutils.R;
import com.sxd.eduservice.entity.EduSubject;
import com.sxd.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@RestController
@RequestMapping("/eduservice/edusubject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

}

