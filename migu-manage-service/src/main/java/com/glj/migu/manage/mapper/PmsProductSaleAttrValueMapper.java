package com.glj.migu.manage.mapper;

import com.glj.migu.bean.PmsProductSaleAttrValue;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrValueMapper extends Mapper<PmsProductSaleAttrValue> {

    @Select("select * from pms_product_sale_attr_value where product_id = #{productId} and sale_attr_id=#{saleAttrId}")
    public List<PmsProductSaleAttrValue> getPmsProductSaleAttrValueList(String productId,String saleAttrId);

}
