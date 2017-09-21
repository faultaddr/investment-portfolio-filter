package com.example.demo.service;

import com.example.demo.entity.ProductEntity;

import java.util.List;

/**
 * Created by panyunyi on 2017/9/19.
 * CUFE cs14
 */

public interface ProductService {
    List<ProductEntity> showAll();
    List <returnStruct>searchPro(String s,String userId);


}
