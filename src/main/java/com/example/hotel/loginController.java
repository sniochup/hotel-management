package com.example.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

//    @GetMapping(path = "/login")
//    public String getLogin(Model model) {
//        return "views/login";
//    }

    @GetMapping(path = "/")
    public String getHome(Model model) {
        return "views/index";
    }
}
