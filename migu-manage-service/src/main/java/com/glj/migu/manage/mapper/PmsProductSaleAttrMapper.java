package com.glj.migu.manage.mapper;

import com.glj.migu.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    @Select("select * from pms_product_sale_attr where product_id=#{productId}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "product_id",property = "productId"),
            @Result(column = "sale_attr_id",property = "saleAttrId"),
            @Result(column = "sale_attr_name",property = "saleAttrName"),
            @Result(column = "{productId=product_id,saleAttrId=sale_attr_id}",property = "spuSaleAttrValueList",
                many = @Many(select = "com.glj.migu.manage.mapper.PmsProductSaleAttrValueMapper.getPmsProductSaleAttrValueList",
                        fetchType= FetchType.EAGER
                )
            )
    })
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(String productId, String id);

}
