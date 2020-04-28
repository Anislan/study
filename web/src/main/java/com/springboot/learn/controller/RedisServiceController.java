package com.springboot.learn.controller;

import com.springboot.learn.domain.IqProduct;
import com.springboot.learn.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RedisServiceController

{
    @Autowired
    private RedisServiceImpl redisService;

    @RequestMapping(value = "/setredis")
    public String setredis(String keyredis){
        redisService.setStr(keyredis,"2018年1月26日");
        return "保存成功,请访问getredis查询redis";
    }
    @RequestMapping(value = "/setObj")
    public String setObj(String keyredis){
        IqProduct iqProduct = new IqProduct();
        iqProduct.setSort(1);
        iqProduct.setTimestamp(new Date().getTime());
        iqProduct.setProductName("productname");
        // list.add(iqProduct);
        redisService.setStr(keyredis, iqProduct);
        return "保存成功,请访问getredis查询redis";
    }
    @RequestMapping(value = "/getObj")
    public Object getObj(String keyredis){
        Object object = redisService.getKey(keyredis);
        if(object !=null){
            IqProduct iqProduct = (IqProduct) object;
            System. out.println(iqProduct.getProductName());
            System. out.println(iqProduct.getId());
            System. out.println(iqProduct.getTimestamp());
        }return object;
    }
    @RequestMapping(value = "/delObj")
    public boolean delObj(String keyredis)
    {boolean del = redisService.del(keyredis);
        return del;
    }
    @RequestMapping(value = "/getredis")
    public String getredis(String keyredis){
        String getredis = (String) redisService.getKey(keyredis);
        return "redis的key是===>"+getredis;
    }
    @RequestMapping(value = "/delredis")
    public String delredis(String keyredis){
        redisService.delKey(keyredis);
        return "删除成功，请通过getredis进行查询";
    }
    @RequestMapping(value = "/setList")
    public String setList(String keyredis){
        List list = new ArrayList();
        for (int i = 0; i<10; i++){
            IqProduct iqProduct = new IqProduct();
            iqProduct.setSort(1);
            iqProduct.setTimestamp(new Date().getTime());
            iqProduct.setProductName("productname");
            list.add(iqProduct);
        }
        redisService.setStr(keyredis, list);
        return "保存成功,请访问getredis查询redis";
    }
    @RequestMapping(value = "/getList")
    public Object getList(String keyredis){
        Object object = redisService.getKey(keyredis);
        if(object !=null){
            List<IqProduct> iqProducts = (List<IqProduct>) object;
            for (int i = 0;i<iqProducts.size();i++){
                IqProduct iqProduct = iqProducts.get(i);
                System. out.println(iqProduct.getProductName());
                System. out.println(iqProduct.getId());
                System. out.println(iqProduct.getTimestamp());
            }
        }return object;
    }
    @RequestMapping(value = "/delList")
    public boolean delList(String keyredis)
    {
        boolean del = redisService.del(keyredis);return del;
    }
}
