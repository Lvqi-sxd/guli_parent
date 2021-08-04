package com.sxd.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxd.eduservice.entity.EduChapter;
import com.sxd.eduservice.entity.EduVideo;
import com.sxd.eduservice.entity.chapter.ChapterVo;
import com.sxd.eduservice.entity.chapter.VideoVo;
import com.sxd.eduservice.mapper.EduChapterMapper;
import com.sxd.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxd.eduservice.service.EduVideoService;
import com.sxd.servicedase.config.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询课程所有章节
        QueryWrapper<EduChapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        //查询每个小节
        QueryWrapper<EduVideo> wrapperVideo=new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList =videoService.list(wrapperVideo);

        //创建list集合，用于最终分装数据
        List<ChapterVo> finalList=new ArrayList<>();
        //遍历查询章节集合进行分装
        for (EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //遍历小节集合，进行分装
            List<VideoVo> finalVideoList=new ArrayList<>();
            for (EduVideo eduVideo : eduVideoList) {
                if(eduVideo.getChapterId().equals(chapterVo.getId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    finalVideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(finalVideoList);
            finalList.add(chapterVo);
        }


        return finalList;
    }

    @Override
    public boolean deteleChapter(String chapterId) {
        //根据chapterId查询小节
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if(count>0){  //不进行删除
            throw new GuliException(20001,"不能删除");
        } else{//删除
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
