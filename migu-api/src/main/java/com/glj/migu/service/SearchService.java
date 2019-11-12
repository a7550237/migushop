package com.glj.migu.service;

import com.glj.migu.bean.PmsSkuInfo;
import com.glj.migu.bean.PmsSkuInfoSearch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SearchService {

    public void test() throws InvocationTargetException, IllegalAccessException, IOException;

    public List<PmsSkuInfoSearch> search(String keyword);


}
