package com.glj.migu.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.PmsBaseCatalog1;
import com.glj.migu.service.PmsBaseCatalog1Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/23
 */
@RestController
public class CatalogController {

    @Reference
    PmsBaseCatalog1Service pmsBaseCatalog1Service;


    @RequestMapping("/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        return pmsBaseCatalog1Service.getCatalog1();
    }


}
