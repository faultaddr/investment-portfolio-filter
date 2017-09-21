package com.example.demo.service;

import com.example.demo.dao.DaoFactory;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panyunyi on 2017/9/14.
 * CUFE cs14
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserEntity> login(String id) {
        {
            UserEntity userEntity = new UserEntity();
            int i= Integer.parseInt(id);
            userEntity.setUserName(i);
            DaoFactory daoFactory = new DaoFactory();
            List<UserEntity> userEntitylist = null;
            try {
                userEntitylist = daoFactory.cursor(userEntity, "select * from user where user.userName='" + id + "'", UserEntity.class);
                if(userEntitylist.size()==0){
                    userEntitylist=null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return userEntitylist;
        }
    }

    @Override
    public List<UserEntity> loginByUserId(String id) {
        UserEntity userEntity = new UserEntity();
        int i= Integer.parseInt(id);
        userEntity.setId(i);
        DaoFactory daoFactory = new DaoFactory();
        List<UserEntity> userEntitylist = null;
        try {
            userEntitylist = daoFactory.cursor(userEntity, "select * from user where user.id='" + id + "'", UserEntity.class);
            if(userEntitylist.size()==0){
                userEntitylist=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userEntitylist;
    }


    @Override
    public boolean register(UserEntity userEntity) {
        DaoFactory daoFactory=new DaoFactory();
        boolean result=daoFactory.save(userEntity);

        return result;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        DaoFactory daoFactory=new DaoFactory();
        boolean result=daoFactory.update(userEntity);
        return result;
    }

}
