package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.util.PrintMessage;
import org.omg.PortableInterceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by panyunyi on 2017/9/12.
 * CUFE cs14
 */
@Controller
@EnableAutoConfiguration
public class UserController {
    @Autowired
    UserService userService;

    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/loginUser")
    public void login(@RequestParam(required = true) String userId, @RequestParam(required = true) String password, HttpServletResponse response) {
        UserServiceImpl userService = new UserServiceImpl();
        List<UserEntity> list = userService.login(userId);
        if (list == null) {
            PrintMessage.PrintMessage(response, "false", "false");
        } else {

            if (((UserEntity)list.get(0)).getPassWord().equals(password)) {
                String JsonString = JSON.toJSONString(list);
                PrintMessage.PrintMessage(response, JsonString, "false");
            } else {
                PrintMessage.PrintMessage(response, "false", "false");
            }
        }
        logger.info("---->>>>----login---->>>>----" + userId + "+\n");
    }

    @RequestMapping(value = "/loginU")
    public ModelAndView login(@RequestParam(required = false, defaultValue = "200") String error, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        logger.info("-->login-ing--please wait");
        if (error.equals("200"))

            modelAndView.setViewName("signup");
        else {
            logger.info("-->failed");
            PrintMessage.PrintMessage(response, error, error);

            modelAndView.setViewName("signup");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUserMethod(@RequestParam String userName, @RequestParam String passWord, HttpServletResponse response) {
        logger.info("---->>>>----register---->>>>----" + userName + "~~" + passWord + "\n");
        UserEntity userEntity = new UserEntity();
        int userId = Integer.parseInt(userName);
        userEntity.setUserName(userId);
        userEntity.setPassWord(passWord);
        boolean result = userService.register(userEntity);
        if (result) {
            PrintMessage.PrintMessage(response, "true", "false");
        } else {
            PrintMessage.PrintMessage(response, "false", "false");
        }

        logger.info("---->>>>----register---->>>>----" + result + "" + userName + "\n");

    }

    @RequestMapping(value = "/updateInfo")
    public ModelAndView updateInfo(@RequestParam String userId, @RequestParam String sex, @RequestParam String age, @RequestParam String marry, @RequestParam String family, @RequestParam String education, @RequestParam String residence, @RequestParam String creditcard, @RequestParam String worklife, @RequestParam String loan, @RequestParam String huankuan, @RequestParam String income, @RequestParam String yongtu, @RequestParam String house, @RequestParam String car, @RequestParam String diyawu, HttpServletResponse response) {
        UserEntity userEntity = new UserEntity();
        ModelAndView modelAndView=new ModelAndView();
        List<UserEntity>list=userService.loginByUserId(userId);
        userEntity.setUserName(list.get(0).getUserName());
        userEntity.setPassWord(list.get(0).getPassWord());
        userEntity.setId(changeStrToInt(userId));
        if (sex.equals("on"))
            userEntity.setSex(0);
        else
            userEntity.setSex(1);
        if (!age.equals(""))
            userEntity.setAge(changeStrToInt(age));
        if (!marry.equals(""))
            userEntity.setMarry(changeStrToInt(marry));
        if (!family.equals(""))
            userEntity.setFamily(changeStrToInt(family));
        if (!education.equals(""))
            userEntity.setEducation(changeStrToInt(education));
        if (!residence.equals(""))
            userEntity.setResidence(changeStrToInt(residence));
        if (!creditcard.equals(""))
            userEntity.setCreditcard(changeStrToInt(creditcard));
        if (!worklife.equals(""))
            userEntity.setWorklife(changeStrToInt(worklife));
        if (!loan.equals(""))
            userEntity.setLoan(changeStrToDouble(loan));
        if (!huankuan.equals(""))
            userEntity.setHuankuan(changeStrToInt(huankuan));
        if (!income.equals(""))
            userEntity.setIncome(changeStrToDouble(income));
        if (!yongtu.equals(""))
            userEntity.setYongtu(changeStrToInt(yongtu));
        if (!house.equals(""))
            userEntity.setHouse(changeStrToDouble(house));
        if (!car.equals(""))
            userEntity.setCar(changeStrToDouble(car));
        if (!diyawu.equals(""))
            userEntity.setDiyawu(changeStrToDouble(diyawu));
        userEntity.setRole("1");
        boolean result = userService.update(userEntity);
        if (result) {
            /*PrintMessage.PrintMessage(response, "true", "false");*/
            modelAndView.setViewName("index");
        } else {
            /*PrintMessage.PrintMessage(response, "false", "false");*/
            modelAndView.setViewName("info");
        }
        return modelAndView;
    }

    public static int changeStrToInt(String s) {

        return Integer.parseInt(s);
    }

    public static double changeStrToDouble(String s) {
        return Double.parseDouble(s);
    }

}
