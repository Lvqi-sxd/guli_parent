package com.sxd.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sxd.oss.service.OssService;
import com.sxd.oss.utils.ContantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * Administrator
 * 2021-7-26
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    //上传文件到oss
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ContantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ContantPropertiesUtils.KEU_ID;
        String accessKeySecret = ContantPropertiesUtils.KEY_SECRET;
        String bucketName = ContantPropertiesUtils.BUCKET_NAME;


        try {
            // 创建oss实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            //String filename=file.getOriginalFilename();
            //在文件名称中添加一个随机的唯一的值
            String uuid= UUID.randomUUID().toString().replaceAll("-","");


            //把文件按照日期进行分类
            // 获取当前日期
            String datePath=new DateTime().toString("yyyy/MM/dd");
            String filename=datePath+"/"+uuid+file.getOriginalFilename();
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            //文件上传的的路径返回
            //需要上传到阿里云oss路径手动拼接出来
            String  url="https://"+bucketName+"."+endpoint+"/"+filename;
            return  url;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
