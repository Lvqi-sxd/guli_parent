package com.sxd.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxd.commonutils.R;
import com.sxd.eduservice.entity.EduTeacher;
import com.sxd.eduservice.entity.vo.TeacherQuery;
import com.sxd.eduservice.service.EduTeacherService;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-19
 */

@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin //解决跨域问题
public class EduTeacherController {
    //把service注入
    @Autowired
    private EduTeacherService  teacherService;
    //查询讲师所有数据
    //rest风格
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);

        return R.ok().data("items",list) ;
    }
    //逻辑删除讲师的方法
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //分页查询讲师方法
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable long current,
                          @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //调用方法实现分页
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
//        return R.ok().data("total",total).data("rows",records);

    }

    //条件查询带分页查询
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBegin();
        String end=teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        //实现排序条件
        queryWrapper.orderByDesc("gmt_create");
        //调用方法

        teacherService.page(pageTeacher,queryWrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //讲师修改
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
//        boolean flag = teacherService.updateById(eduTeacher);
        boolean flag =teacherService.updateTeacherById(eduTeacher);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

