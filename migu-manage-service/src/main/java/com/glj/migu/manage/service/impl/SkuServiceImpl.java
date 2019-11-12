package com.glj.migu.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.glj.migu.bean.PmsSkuAttrValue;
import com.glj.migu.bean.PmsSkuImage;
import com.glj.migu.bean.PmsSkuInfo;
import com.glj.migu.bean.PmsSkuSaleAttrValue;
import com.glj.migu.manage.mapper.PmsSkuAttrValueMapper;
import com.glj.migu.manage.mapper.PmsSkuImageMapper;
import com.glj.migu.manage.mapper.PmsSkuInfoMapper;
import com.glj.migu.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.glj.migu.service.SkuService;
import com.glj.migu.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

/**
 * @author guolongjie
 * @since 2019/10/31
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        for (PmsSkuImage pmsSkuImage:pmsSkuInfo.getSkuImageList()){
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue:pmsSkuInfo.getSkuSaleAttrValueList()){
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        for (PmsSkuAttrValue pmsSkuAttrValue:pmsSkuInfo.getSkuAttrValueList()){
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        return "success";
    }

    public PmsSkuInfo getSkuByIdFromDb(String skuId){
        // sku商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        // sku的图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId, String remoteAddr) {
        Jedis jedis = redisUtil.getJedis();
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        String key = "sku:"+skuId+":info";
        String jsonStr = jedis.get(key);
        if (StringUtils.isNotBlank(jsonStr)){
            pmsSkuInfo = JSON.parseObject(jsonStr,PmsSkuInfo.class);
        }else {
            String token = UUID.randomUUID().toString();
            String OK = jedis.set("sku:" + skuId + ":info:lock", token, "nx", "px", 10 * 1000);
            if (StringUtils.isNotBlank(OK)&&OK.equals("OK")){
                pmsSkuInfo = getSkuByIdFromDb(skuId);
                if (pmsSkuInfo!=null){
                    String skuJson = JSON.toJSONString(pmsSkuInfo);
                    jedis.set("sku:"+skuId+":info",skuJson);
                }else {
                    jedis.setex("sku:"+skuId+":info",10,JSON.toJSONString(""));
                }
                String lockToken = jedis.get("sku:"+skuId+":info:lock");
                if (StringUtils.isNotBlank(lockToken) && lockToken.equals(token)){
                    jedis.del("sku:"+skuId+":info:lock");
                }

            }else {
                jedis.close();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getSkuById(skuId,remoteAddr);
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfos;
    }

    @Override
    public List<PmsSkuInfo> getAllSku() {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectAll();
        return pmsSkuInfos;
    }
}
