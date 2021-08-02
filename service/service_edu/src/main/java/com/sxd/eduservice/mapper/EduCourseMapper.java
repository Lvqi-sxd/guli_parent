package com.sxd.eduservice.mapper;

import com.sxd.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxd.eduservice.entity.subject.CoursePublishVo;
import com.sxd.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

}
