package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Administrator
 * 2021-8-6
 */
@RestController
@RequestMapping("edumsm/msm")
@CrossOrigin
public class Msmcontroller {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){

        //从redis 获取验证码，如果获取到直接返回
        String code1 = redisTemplate.opsForValue().get(phone);

        if(!StringUtils.isEmpty(code1)){
            return R.ok();
        }

        //生成随机值，传递阿里云进行发送
        String code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();

        param.put("code",code);

        //调用service中短信发送方法
        boolean isSend = msmService.send(param,phone);

        if(isSend){
            //发送成功验证码放入redis中
            //设置有效时间
            redisTemplate.opsForValue().set(phone,"1234",5, TimeUnit.MINUTES);

            return R.ok();

        }else{
            return R.error().message("短信发送失败");
        }
    }
}
