package com.sxd.eduservice.service.impl;

import com.sxd.eduservice.entity.EduCourse;
import com.sxd.eduservice.entity.EduCourseDescription;
import com.sxd.eduservice.entity.vo.CourseInfoVo;
import com.sxd.eduservice.mapper.EduCourseMapper;
import com.sxd.eduservice.service.EduCourseDescriptionService;
import com.sxd.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxd.servicedase.config.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourseTnfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        //下个课程表添加课程基本信息
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert<=0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        //获取添加后课程的id值
        String cid=eduCourse.getId();
        //向课程简介中添加课程简介
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        eduCourseDescription.setId(cid);
        courseDescriptionService.save(eduCourseDescription);

        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo=new CourseInfoVo();

        //1查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        //2查询课程描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update<=0){
            throw new GuliException(20001,"修改课程信息失败");
        }

        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);

    }
}
