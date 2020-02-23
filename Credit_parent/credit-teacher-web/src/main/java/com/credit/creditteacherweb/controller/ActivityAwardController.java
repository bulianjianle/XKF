package com.credit.creditteacherweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.credit.creditteacherweb.utils.AcitivityAwardListener;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Prize;
import com.credit.service.IActivityAward;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ActivityAwardController")
public class ActivityAwardController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IActivityAward iActivityAward;



    //前端文档上传uploadByelsx
    @ResponseBody
    @RequestMapping("/uploadByelsx")
    private String uploadByelsx(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (file!=null){
                EasyExcel.read(file.getInputStream(), Prize.class, new AcitivityAwardListener(iActivityAward)).sheet(2).headRowNumber(3).doRead();
            }
            return "写入成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "写入失败,格式不正确";
        }
    }
    
    
    //读文件写入数据库
    @ResponseBody
    @RequestMapping("/insertUserByExcel")
    private void insertUserByExcel()throws IOException {
        System.out.println(iActivityAward);
        // 写法1：
        String fileName = "G:\\IntelliJ IDEA 2019.2.4\\Credit_parent\\credit-teacher-web\\src\\main\\resources\\demo\\demo9.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Prize.class, new AcitivityAwardListener(iActivityAward)).sheet(2).headRowNumber(3).doRead();
    }
    
    
    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Prize prize, int page, int rows){
        return iActivityAward.findPage(prize, page, rows);
    }


    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return iActivityAward.findPage(page, rows);
    }

    //查询活动实体findOneAcitivity 
    @ResponseBody
    @RequestMapping("/findOneAcitivity")
    public Prize findOneAcitivity (int id){
        return iActivityAward.findOnestuprize(id);
    }

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Prize prize){
        try {
            iActivityAward.add(prize);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param 
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Prize prize){
        try {
            iActivityAward.update(prize);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(int[] ids){
        try {
            iActivityAward.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }
}
