package com.glj.migu.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.glj.migu.bean.PmsBaseCatalog1;
import com.glj.migu.manage.mapper.PmsBaseCatalog1Mapper;
import com.glj.migu.service.PmsBaseCatalog1Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/23
 */
@Service
public class PmsBaseCatalog1ServiceImpl implements PmsBaseCatalog1Service {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }
}
