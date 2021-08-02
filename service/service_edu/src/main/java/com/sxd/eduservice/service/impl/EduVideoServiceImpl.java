package com.sxd.eduservice.service.impl;

import com.sxd.eduservice.entity.EduVideo;
import com.sxd.eduservice.mapper.EduVideoMapper;
import com.sxd.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void updateVideo(EduVideo eduVideo) {
        baseMapper.updateById(eduVideo);
    }
}
