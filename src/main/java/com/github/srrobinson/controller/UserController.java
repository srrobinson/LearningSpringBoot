package com.github.srrobinson.controller;

import com.github.srrobinson.dao.GreetingDAO;
import com.github.srrobinson.dao.UserDAO;
import com.github.srrobinson.model.User;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by srrobinson.
 * Code is for learning purposes only and is not guaranteed in any way.
 */
@Controller
public class UserController {
    @Autowired
    GreetingDAO greetingDAO;

    @Autowired
    UserDAO userDAO;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserHome(Model model) {
        return "/user/home";

    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String getUserList(Model model) {
        model.addAttribute("users", IteratorUtils.toList(userDAO.findAll().iterator()));
        return "/user/list";

    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String getUserCreate(Model model) {
        model.addAttribute("user", new User());

        model.addAttribute("action", "create");
        return "/user/createedit";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String setUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                          @RequestParam(value = "action", required = false) String action) {
        switch (action) {
            case "create":
            case "update":
                if (bindingResult.hasErrors()) {

                    System.out.println(bindingResult.getAllErrors());
                    model.addAttribute("action", action);
                    return "/user/createedit";
                } else {
                    userDAO.save(user);
                    //TODO: Add a flash element to tell you it worked!
                    return "redirect:/user/list";
                }
            case "edit":
                user = userDAO.findOne(user.getId());

                if (user != null) {
                    model.addAttribute("user", user);
                    model.addAttribute("action", "update");
                    return "/user/createedit";
                } else {
                    //TODO: Add a flash element to tell you asked for a bad ID
                    return "redirect:/user/list";
                }
            case "delete":
                user = userDAO.findOne(user.getId());

                if (user != null) {
                    //TODO: Add a flash element to tell you deletion finished
                    userDAO.delete(user);
                    return "redirect:/user/list";
                } else {
                    //TODO: Add a flash element to tell you asked for a bad ID
                    return "redirect:/user/";
                }
            default:
                //TODO: Add a flash element to tell you you asked for a bad operation
                return "redirect:/user";
        }

    }
}
