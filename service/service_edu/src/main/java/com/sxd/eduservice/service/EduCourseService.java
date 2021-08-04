package com.sxd.eduservice.service;

import com.sxd.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxd.eduservice.entity.vo.CoursePublishVo;
import com.sxd.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseTnfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);
}
