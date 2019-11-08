package com.glj.migu.manage.mapper;

import com.glj.migu.bean.PmsSkuInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo> {

    @Select("select * from pms_sku_info where product_id=#{productId}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "product_id",property = "productId"),
            @Result(column = "price",property = "price"),
            @Result(column = "sku_name",property = "skuName"),
            @Result(column = "sku_desc",property = "skuDesc"),
            @Result(column = "weight",property = "weight"),
            @Result(column = "tm_id",property = "tmId"),
            @Result(column = "catalog3_id",property = "catalog3Id"),
            @Result(column = "sku_default_img",property = "skuDefaultImg"),
            @Result(column = "{skuId=id}",property = "skuImageList",
                many = @Many(select = "com.glj.migu.manage.mapper.PmsSkuImageMapper.selectBySkuId",
                    fetchType = FetchType.EAGER
                )
            ),
            @Result(column = "{skuId=id}",property = "skuAttrValueList",
                    many = @Many(select = "com.glj.migu.manage.mapper.PmsSkuAttrValueMapper.selectBySkuId",
                            fetchType = FetchType.EAGER
                    )
            ),
            @Result(column = "{skuId=id}",property = "skuSaleAttrValueList",
                    many = @Many(select = "com.glj.migu.manage.mapper.PmsSkuSaleAttrValueMapper.selectBySkuId",
                            fetchType = FetchType.EAGER
                    )
            )
    })
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);

}
