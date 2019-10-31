package com.glj.migu.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.glj.migu.bean.PmsBaseCatalog1;
import com.glj.migu.bean.PmsBaseCatalog2;
import com.glj.migu.bean.PmsBaseCatalog3;
import com.glj.migu.manage.mapper.PmsBaseCatalog1Mapper;
import com.glj.migu.manage.mapper.PmsBaseCatalog2Mapper;
import com.glj.migu.manage.mapper.PmsBaseCatalog3Mapper;
import com.glj.migu.service.PmsBaseCatalogService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/23
 */
@Service
public class PmsBaseCatalogServiceImpl implements PmsBaseCatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        return pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        return pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
    }


}
