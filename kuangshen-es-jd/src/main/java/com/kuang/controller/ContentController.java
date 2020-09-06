package com.kuang.controller;

import com.kuang.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * ContentController
 *
 * @author danggui
 * @date 2020/9/6 18:37
 */

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;


    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable String keyword) throws Exception {
        return contentService.pareContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> search(@PathVariable String keyword,@PathVariable Integer pageNo,@PathVariable Integer pageSize) throws Exception {
        return contentService.searchPage(keyword,pageNo,pageSize);
    }




}
