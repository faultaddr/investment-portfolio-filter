package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

/**
 * Created by panyunyi on 2017/9/16.
 * CUFE cs14
 */

@Controller
public class IndexController {

    @RequestMapping(value = {"/index","/"})
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/product")
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView searchProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = "myInfo")
    public ModelAndView getMyInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");
        return modelAndView;
    }


}
