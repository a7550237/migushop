package com.glj.migu.manage.controller;

import com.glj.migu.bean.PmsSkuInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolongjie
 * @since 2019/10/31
 */
@RestController
@CrossOrigin
public class SkuController {

    @PostMapping(value = "/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        return "success";
    }

}
