package com.sxd.oss.controller;

import com.sxd.commonutils.R;
import com.sxd.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Administrator
 * 2021-7-26
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取到上传的文件
        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
