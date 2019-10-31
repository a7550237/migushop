package com.glj.migu.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.PmsBaseAttrInfo;
import com.glj.migu.bean.PmsBaseAttrValue;
import com.glj.migu.bean.PmsBaseSaleAttr;
import com.glj.migu.service.AttrService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/24
 */
@RestController
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    @PostMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> getBaseSaleAttrList(){
        return attrService.getBaseSaleAttrList();
    }

    @GetMapping(value = "/attrInfoList")
    public List<PmsBaseAttrInfo> getBaseAttrInfo(String catalog3Id){
        return attrService.getBaseAttrInfo(catalog3Id);
    }

    @PostMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        attrService.saveAttrInfo(pmsBaseAttrInfo);

        return "success";
    }

    @PostMapping(value = "/getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        return attrService.getAttrValueList(attrId);
    }
}
