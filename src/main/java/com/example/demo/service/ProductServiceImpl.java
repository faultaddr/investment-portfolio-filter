package com.example.demo.service;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.alibaba.fastjson.JSON;
import com.example.demo.controller.UserController;
import com.example.demo.dao.DaoFactory;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.util.FileUtil;
import com.example.demo.util.PrintMessage;
import com.example.demo.util.test;
import com.sun.deploy.net.HttpResponse;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panyunyi on 2017/9/19.
 * CUFE cs14
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    UserService userService;

    public static double s;

    @Override
    public List<ProductEntity> showAll() {
        List<ProductEntity> proList = null;
        DaoFactory daoFactory = new DaoFactory();
        try {
            proList = daoFactory.cursor(null, "select * from product", ProductEntity.class);
            if (proList.size() == 0) {
                proList = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            proList = null;
        }
        return proList;
    }

    @Override
    public List<returnStruct> searchPro(String investNum, String userId) {
        DaoFactory daoFactory = new DaoFactory();
        List<UserEntity> list = null;
        try {
            list = daoFactory.cursor(null, "select * from user where user.id=" + userId, UserEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean flag = true;
        if (list != null) {
            if (list.get(0).getRole() != null) {
                flag = false;
                String s = list.get(0).getSex() + " " + list.get(0).getAge() + " " + list.get(0).getMarry() + " " +
                        list.get(0).getFamily() + " " + list.get(0).getEducation() + " " + list.get(0).getResidence() + " "
                        + list.get(0).getCreditcard() + " " + list.get(0).getWorklife() + " " + list.get(0).getLoan() + " " +
                        list.get(0).getHuankuan() + " " + list.get(0).getYongtu() + " " + list.get(0).getIncome() + " " +
                        list.get(0).getHouse() + " " + list.get(0).getCar() + " " + list.get(0).getDiyawu();
                System.out.println(s);

                try {
                    File file = new File("E:\\code\\invest\\src\\main\\resources\\new_u.txt");
                    PrintStream ps = new PrintStream(new FileOutputStream(file));
                    ps.println(s);// 往文件里写入字符串
                    //ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }
        }
        Process proc = null;
        String[] args;


            if(flag){
                /*老用户*/
                String s="python E:\\code\\invest\\src\\main\\resources\\final.py old "+investNum+" "+userId;
                test.exeCmd(s);

            }else{
                String s="python E:\\code\\invest\\src\\main\\resources\\final.py new "+investNum;
                test.exeCmd(s);
            }



/*        try {
            System.out.println("start");
            proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("stop");
        // read file content from file
        StringBuffer sb = new StringBuffer("");

        FileReader reader = null;
        try {
            if (flag)
                reader = new FileReader("E:\\code\\invest\\src\\main\\resources\\old.txt");
            else
                reader = new FileReader("E:\\code\\invest\\src\\main\\resources\\new.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader);

        String str = null;
        double sum=0.0;
        int count=0;
        ArrayList<returnStruct>arrayList=new ArrayList<>();
        try {
            while ((str = br.readLine()) != null) {
                sb.append(str);
                if(count==0){
                    sum=Double.parseDouble(str);
                    s=sum;
                }else{
                    String ret[]=str.split(" ");
                    returnStruct returnStruct=new returnStruct();
                    returnStruct.proId=Integer.parseInt(ret[0]);
                    returnStruct.proNum=Integer.parseInt(ret[1]);
                    arrayList.add(returnStruct);
                }
                count++;

                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;

    }
}

