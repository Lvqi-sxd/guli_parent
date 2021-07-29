package com.sxd.eduservice.controller;


import com.sxd.commonutils.R;
import com.sxd.eduservice.entity.chapter.ChapterVo;
import com.sxd.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@RestController
@RequestMapping("/eduservice/educhapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;
    //返回课程大纲列表
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable  String courseId){
        List<ChapterVo> list =chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

}

