package com.sxd.eduservice.service;

import com.sxd.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-19
 */
public interface EduTeacherService extends IService<EduTeacher> {

    boolean updateTeacherById(EduTeacher eduTeacher);
}
