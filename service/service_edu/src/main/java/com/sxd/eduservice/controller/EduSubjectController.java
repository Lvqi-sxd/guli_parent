package com.sxd.eduservice.controller;


import com.sxd.commonutils.R;
import com.sxd.eduservice.entity.EduSubject;
import com.sxd.eduservice.entity.subject.OneSubject;
import com.sxd.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@CrossOrigin //解决跨域问题
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    //课程分类列表（树形）
    @GetMapping("getSubject")
    public R getAllSubject(){
        List<OneSubject> list=subjectService.getAlllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

