package com.glj.migu.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.PmsBaseSaleAttr;
import com.glj.migu.bean.PmsProductImage;
import com.glj.migu.bean.PmsProductInfo;
import com.glj.migu.bean.PmsProductSaleAttr;
import com.glj.migu.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/24
 */
@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @Value(value = "${fileServer.url}")
    private String fileUrl;

    @GetMapping(value = {"/spuList"})
    public List<PmsProductInfo> getSpuList(HttpServletRequest request){
        String catalog3Id = request.getParameter("catalog3Id");
        return spuService.getSpuList(catalog3Id);
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam(value = "file",required = false) MultipartFile multipartFile) throws IOException, MyException {
        String imgUrl = fileUrl;
        if (multipartFile!=null){
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer connection = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(connection,null);
            String fileName = multipartFile.getOriginalFilename();
            String extName = StringUtils.substringAfterLast(fileName,".");
            String[] upload_file = storageClient.upload_file(multipartFile.getBytes(), extName, null);
            imgUrl = fileUrl;
            for (int i = 0; i <upload_file.length;i++){
                String path = upload_file[i];
                imgUrl = imgUrl + "/" + path;
            }


        }
        return imgUrl;
    }

    @RequestMapping(value ={"/saveSpuInfo"} )
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);

        return "success";
    }

    @GetMapping(value = "/spuSaleAttrList")
    public List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId){
        return spuService.getSpuSaleAttrList(spuId);
    }

    @GetMapping(value = "/spuImageList")
    public List<PmsProductImage> getSpuImageList(String spuId){
        return spuService.getSpuImageList(spuId);
    }


}
