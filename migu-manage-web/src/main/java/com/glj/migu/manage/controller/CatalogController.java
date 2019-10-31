package com.glj.migu.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.PmsBaseCatalog1;
import com.glj.migu.bean.PmsBaseCatalog2;
import com.glj.migu.bean.PmsBaseCatalog3;
import com.glj.migu.service.PmsBaseCatalogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/23
 */
@RestController
@CrossOrigin
public class CatalogController {

    @Reference
    PmsBaseCatalogService pmsBaseCatalogService;


    @PostMapping("/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        return pmsBaseCatalogService.getCatalog1();
    }

    @PostMapping("/getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(HttpServletRequest request){
        String catalog1Id = request.getParameter("catalog1Id");
        return pmsBaseCatalogService.getCatalog2(catalog1Id);
    }

    @PostMapping("/getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(HttpServletRequest request){
        String catalog2Id = request.getParameter("catalog2Id");
        return pmsBaseCatalogService.getCatalog3(catalog2Id);
    }




}
