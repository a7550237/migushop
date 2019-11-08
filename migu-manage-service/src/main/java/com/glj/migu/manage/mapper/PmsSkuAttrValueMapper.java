package com.glj.migu.manage.mapper;

import com.glj.migu.bean.PmsSkuAttrValue;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuAttrValueMapper extends Mapper<PmsSkuAttrValue> {

    @Select("select * from pms_sku_attr_value where sku_id = #{skuId}")
    public List<PmsSkuAttrValue> selectBySkuId(PmsSkuAttrValue  pmsSkuAttrValue);

}
