package com.github.srrobinson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by srrobinson.
 * Code is for learning purposes only and is not guaranteed in any way.
 */
@Controller
public class HomeController {


    @RequestMapping("/")
    public String getHome(Model model) {

        return "home";
    }




}
