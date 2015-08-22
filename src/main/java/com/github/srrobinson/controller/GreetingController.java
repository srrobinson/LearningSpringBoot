package com.github.srrobinson.controller;

import com.github.srrobinson.dao.GreetingDAO;
import com.github.srrobinson.dao.UserDAO;
import com.github.srrobinson.model.Greeting;
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
import java.util.List;

/**
 * Created by srrobinson.
 * Code is for learning purposes only and is not guaranteed in any way.
 */
@Controller
public class GreetingController {

    @Autowired
    GreetingDAO greetingDAO;

    @Autowired
    UserDAO userDAO;


    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String getGreetingHome() {

        return "/greeting/home";
    }


    @RequestMapping(value = "/greeting/list", method = RequestMethod.GET)
    public String getGreetingList(Model model) {
        List<Greeting> greetings = IteratorUtils.toList(greetingDAO.findAll().iterator());
        model.addAttribute("greetings", greetings);

        return "/greeting/list";
    }

    @RequestMapping(value = "/greeting/create", method = RequestMethod.GET)
    public String getGreetingCreate(Model model) {
        model.addAttribute("greeting", new Greeting());

        model.addAttribute("users", IteratorUtils.toList(userDAO.findAll().iterator()));

        model.addAttribute("action", "create");
        return "/greeting/createedit";
    }


    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String setGreeting(Model model, @Valid @ModelAttribute("greeting") Greeting greeting, BindingResult bindingResult,
                              @RequestParam(value = "action", required = false) String action) {

        if (action.equals("create") || action.equals("update")) {
            if (bindingResult.hasErrors()) {

                System.out.println(bindingResult.getAllErrors());
                model.addAttribute("users", IteratorUtils.toList(userDAO.findAll().iterator()));
                model.addAttribute("action", action);
                return "/greeting/createedit";
            } else {
                greetingDAO.save(greeting);
                //TODO: Add a flash element to tell you it worked!
                return "redirect:/greeting/list";
            }
        } else if (action.equals("edit")) {
            greeting = greetingDAO.findOne(greeting.getId());

            if (greeting != null) {
                model.addAttribute("users", IteratorUtils.toList(userDAO.findAll().iterator()));
                model.addAttribute("greeting", greeting);
                model.addAttribute("action", "update");
                return "/greeting/createedit";
            } else {
                //TODO: Add a flash element to tell you asked for a bad ID
                return "redirect:/greeting/list";
            }
        } else if (action.equals("delete")){
            greeting = greetingDAO.findOne(greeting.getId());

            if (greeting != null) {
                //TODO: Add a flash element to tell you deletion finished
                greetingDAO.delete(greeting);
                return "redirect:/greeting/list";
            } else {
                //TODO: Add a flash element to tell you asked for a bad ID
                return "redirect:/greeting/";
            }
        } else {
            //TODO: Add a flash element to tell you you asked for a bad operation
            return "redirect:/greeting";
        }

    }
}
