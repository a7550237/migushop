package com.glj.migu.service;

import com.glj.migu.bean.PmsBaseCatalog1;
import com.glj.migu.bean.PmsBaseCatalog2;
import com.glj.migu.bean.PmsBaseCatalog3;

import java.util.List;

public interface PmsBaseCatalogService {

    public List<PmsBaseCatalog1> getCatalog1();


    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
