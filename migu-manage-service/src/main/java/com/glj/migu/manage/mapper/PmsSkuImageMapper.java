package com.glj.migu.manage.mapper;

import com.glj.migu.bean.PmsSkuImage;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuImageMapper extends Mapper<PmsSkuImage> {

    @Select("select * from pms_sku_image where sku_id=#{skuId}")
    public List<PmsSkuImage> selectBySkuId(PmsSkuImage pmsSkuImage);

}
