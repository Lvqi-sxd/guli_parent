package com.sxd.eduservice.service.impl;

import com.sxd.eduservice.entity.EduTeacher;
import com.sxd.eduservice.mapper.EduTeacherMapper;
import com.sxd.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-19
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public boolean updateTeacherById(EduTeacher eduTeacher) {
        int i = baseMapper.updateById(eduTeacher);
        if (i<=0){
            return false;
        }else {
            return true;
        }

    }
}
