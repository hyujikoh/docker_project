package com.example.made_centos_0916.app.homeController;

import com.example.made_centos_0916.app.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String showMain() {

        return "home/main";
    }
}