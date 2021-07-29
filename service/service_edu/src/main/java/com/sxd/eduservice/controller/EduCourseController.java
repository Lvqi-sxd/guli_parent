package com.sxd.eduservice.controller;


import com.sxd.commonutils.R;
import com.sxd.eduservice.entity.vo.CourseInfoVo;
import com.sxd.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@RestController
@RequestMapping("/eduservice/educourse")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //添加课程方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id=courseService.saveCourseTnfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

}

