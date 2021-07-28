package com.sxd.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Administrator
 * 2021-7-26
 */

public interface OssService {
    //上传头像到oss中

    String uploadFileAvatar(MultipartFile file);
}
