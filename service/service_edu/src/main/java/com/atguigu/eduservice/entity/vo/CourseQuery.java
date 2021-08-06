package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Administrator
 * 2021-7-20
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称，模糊查询")
    private String title;
    @ApiModelProperty(value = "状态 1未发布，2已发布")
    private String  status;
}
