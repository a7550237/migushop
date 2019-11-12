package com.glj.migu.search.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.glj.migu.bean.PmsSkuInfo;
import com.glj.migu.bean.PmsSkuInfoSearch;
import com.glj.migu.service.SearchService;
import com.glj.migu.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guolongjie
 * @since 2019/11/11
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Reference
    SkuService skuService;

    @Autowired
    JestClient jestClient;

    @Override
    public void test() throws InvocationTargetException, IllegalAccessException, IOException {
        List<PmsSkuInfoSearch> pmsSkuInfoSearchList = new ArrayList<>();
        List<PmsSkuInfo> allSku = skuService.getAllSku();
        for (PmsSkuInfo skuInfo : allSku) {
            PmsSkuInfoSearch pmsSkuInfoSearch = new PmsSkuInfoSearch();
            BeanUtils.copyProperties(pmsSkuInfoSearch,skuInfo);
            pmsSkuInfoSearchList.add(pmsSkuInfoSearch);
        }

        for (PmsSkuInfoSearch pmsSkuInfoSearch : pmsSkuInfoSearchList) {
            Index build = new Index.Builder(pmsSkuInfoSearch).index("migu").type("skuInfo").id(pmsSkuInfoSearch.getId()).build();
            jestClient.execute(build);

        }

    }

    @Override
    public List<PmsSkuInfoSearch> search(String keyword) {
        String dslStr = getSearchDsl(keyword);
        Search search = new Search.Builder(dslStr).addIndex("migu").addType("skuInfo").build();
        SearchResult searchResult = null;
        try {
            searchResult = jestClient.execute(search);

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PmsSkuInfoSearch> list = new ArrayList<>();

        List<SearchResult.Hit<PmsSkuInfoSearch, Void>> hits = searchResult.getHits(PmsSkuInfoSearch.class);
        for (SearchResult.Hit<PmsSkuInfoSearch, Void> hit : hits) {
            PmsSkuInfoSearch source = hit.source;
            list.add(source);

        }


        return list;
    }

    private String getSearchDsl(String keyword) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName",keyword);
        boolQueryBuilder.must(matchQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.field("skuName");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.sort("id", SortOrder.DESC);

        return searchSourceBuilder.toString();
    }


}
