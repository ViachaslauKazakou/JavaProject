package com.org.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.org.project.db.UserRepository;
import com.org.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping("/")
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        System.out.println("Start index page ");
        logger.info("Processing home request...");
        logger.info("Get user data: " + users);

        return "index";
    }

}
