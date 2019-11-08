package com.glj.migu.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.PmsSkuImage;
import com.glj.migu.bean.PmsSkuInfo;
import com.glj.migu.service.SkuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author guolongjie
 * @since 2019/10/31
 */
@RestController
@CrossOrigin
public class SkuController {

    @Reference
    SkuService skuService;

    @PostMapping(value = "/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        PmsSkuImage skuImage = pmsSkuInfo.getSkuImageList().stream().filter(pmsSkuImage -> pmsSkuImage.getIsDefault().equals("1")).findFirst().get();
        if (skuImage!=null){
            pmsSkuInfo.setSkuDefaultImg(skuImage.getImgUrl());
        }
        String code = skuService.saveSkuInfo(pmsSkuInfo);


        return code;
    }

}
