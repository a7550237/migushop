package com.glj.migu.service;

import com.glj.migu.bean.PmsBaseAttrInfo;
import com.glj.migu.bean.PmsBaseAttrValue;
import com.glj.migu.bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    List<PmsBaseSaleAttr> getBaseSaleAttrList();

    List<PmsBaseAttrInfo> getBaseAttrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}
