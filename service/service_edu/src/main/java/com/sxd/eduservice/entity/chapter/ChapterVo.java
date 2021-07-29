package com.sxd.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator
 * 2021-7-29
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    //一个一级分类有多个二级分类
    private List<VideoVo> children =new ArrayList<>();
}
