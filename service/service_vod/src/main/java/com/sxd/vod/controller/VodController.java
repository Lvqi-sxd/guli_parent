package com.sxd.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.sxd.commonutils.R;

import com.sxd.vod.service.VodService;
import com.sxd.vod.utils.ConstanVodUtils;
import com.sxd.vod.utils.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Administrator
 * 2021-8-4
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){
        //返回上传视频id
        String videoId = vodService.uploadaliyun(file);
        return R.ok().data("videoId",videoId);
    }
    //根据视频id删除阿里云中的视频
    @DeleteMapping("removeAliyunVideo/{id}")
    public R removeAliyunVideo(@PathVariable String id){
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstanVodUtils.KEU_ID, ConstanVodUtils.KEY_SECRET);
            DeleteVideoRequest request=new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化方法
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }

    }

}
