package com.sxd.vod.service.impl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.sxd.vod.service.VodService;
import com.sxd.vod.utils.ConstanVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Administrator
 * 2021-8-4
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadaliyun(MultipartFile file) {
        try {
            //上传文件原始名称
            String fileName = file.getOriginalFilename();

            //上传之后文件名称
            String title =fileName.substring(0,fileName.lastIndexOf("."));

            //inputStream 上传文件输入流
            InputStream inputStream=file.getInputStream();
            //上传视频的方法
            UploadStreamRequest request = new UploadStreamRequest(ConstanVodUtils.KEU_ID, ConstanVodUtils.KEY_SECRET, title, fileName,inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId=null;

            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
                videoId = response.getVideoId();
            }
            return videoId;
        }catch (Exception e){
             e.printStackTrace();
             return null;
        }
    }
}
