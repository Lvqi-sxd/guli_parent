package com.sxd.eduservice.controller;

import com.sxd.commonutils.R;
import com.sxd.eduservice.entity.EduVideo;
import com.sxd.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-28
 */
@RestController
@RequestMapping("/eduservice/eduvideo")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduvideo){
        videoService.save(eduvideo);
        return R.ok();
    }
    //删除小节
    //TODO 后面这个方法需要完善:
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return R.ok();
    }
    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateVideo(eduVideo);
        return R.ok();
    }
    //根据id查询小节
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String  videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("videoInfo",eduVideo);
    }
}

