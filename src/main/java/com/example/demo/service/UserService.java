package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.List;

/**
 * Created by panyunyi on 2017/9/14.
 * CUFE cs14
 */
public interface UserService {

    List<UserEntity> login(String id);
    List<UserEntity> loginByUserId(String id);

    boolean register(UserEntity userEntiy);

    boolean update(UserEntity userEntity);
}
