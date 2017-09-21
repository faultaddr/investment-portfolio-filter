package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.DaoFactory;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import com.example.demo.service.returnStruct;
import com.example.demo.util.PrintMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by panyunyi on 2017/9/19.
 * CUFE cs14
 */
@Controller
@EnableAutoConfiguration
public class ContentController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/showProduct")
    public void showAllProductDetail(HttpServletResponse response){
        List proList=productService.showAll();
        String proJson= JSON.toJSONString(proList);
        PrintMessage.PrintMessage(response,proJson,"false");
        UserController.logger.info("---->>>>----"+Thread.currentThread() .getStackTrace()[1].getMethodName()+"---->>>>----");
    }

    @RequestMapping(value="/getGroupResult")
    public void searchProductDetail(HttpServletResponse response,@RequestParam String num,@RequestParam String day,@RequestParam String userId){
        List<returnStruct>list=productService.searchPro(num,userId);//useerRole=null 即为老用户
        sortIntMethod(list);
        UserController.logger.info("---->>>>----"+Thread.currentThread() .getStackTrace()[1].getMethodName()+"---->>>>----");
        //TODO 读取文件返回后组装成 json 返回给页面
        List<ProductEntity>proList=new ArrayList<>();
        List<Integer>proNum=new ArrayList<>();
        for(returnStruct item:list){
            int id=item.getProId();
            int n=item.getProNum();
            proNum.add(n);
            DaoFactory daoFactory=new DaoFactory();

            try {
                proList.add((ProductEntity) (daoFactory.cursor(null,"select * from product where product.id="+id,ProductEntity.class)).get(0));
            } catch (Exception e) {
                proList=null;
                e.printStackTrace();
            }
        }


        String pro=JSON.toJSONString(proList);
        String pron=JSON.toJSONString(proNum);
        String s="{\"pro\":"+pro+",\"num\":"+pron+",\"sum\":"+ ProductServiceImpl.s+"}";
        PrintMessage.PrintMessage(response,s,"false");
        UserController.logger.info(s);

    }
    @SuppressWarnings("unchecked")
    public static void sortIntMethod(List list){
        Collections.sort(list, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                returnStruct stu1=(returnStruct)o1;
                returnStruct stu2=(returnStruct)o2;
                if(stu1.getProNum()>stu2.getProNum()){
                    return -1;
                }else if(stu1.getProNum()==stu2.getProNum()){
                    return 0;
                }else{
                    return 1;
                }
            }
        });

}}
