package com.example.made_centos_0916.app.homeController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/")
    public String showMain() {

        return "home/main";
    }
}