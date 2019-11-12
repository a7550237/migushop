package com.glj.migu.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.PmsSkuInfoSearch;
import com.glj.migu.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author guolongjie
 * @since 2019/11/11
 */
@Controller
@CrossOrigin
public class SearchController {

    @Reference
    SearchService searchService;

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        try {
            searchService.test();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }


    @RequestMapping("list")
    @ResponseBody
    public List<PmsSkuInfoSearch> list(@RequestParam(value = "keyword",required = true)String keyword){
        List<PmsSkuInfoSearch> search = searchService.search(keyword);


        return search;
    }

}
