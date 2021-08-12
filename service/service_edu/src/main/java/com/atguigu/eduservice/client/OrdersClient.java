package com.atguigu.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Administrator
 * 2021-8-12
 */

@Component
@FeignClient("service-order")
public interface OrdersClient {
    //根据课程id和用户id查询订单中订单状态
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseI,@PathVariable("memberId") String memberId);
}
