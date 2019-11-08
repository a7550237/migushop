package com.glj.migu.manage.mapper;

import com.glj.migu.bean.PmsSkuAttrValue;
import com.glj.migu.bean.PmsSkuSaleAttrValue;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuSaleAttrValueMapper extends Mapper<PmsSkuSaleAttrValue> {

    @Select("select * from pms_sku_sale_attr_value where sku_id = #{skuId}")
    public List<PmsSkuSaleAttrValue> selectBySkuId(PmsSkuSaleAttrValue pmsSkuSaleAttrValue);

}
