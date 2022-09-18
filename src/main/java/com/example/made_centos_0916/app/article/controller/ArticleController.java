package com.example.made_centos_0916.app.article.controller;

import com.example.made_centos_0916.app.article.service.ArticleService;
import com.example.made_centos_0916.app.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @RequestMapping("/getList")
    @ResponseBody
    public List<Article> getList() {
        return articleService.getList();
    }
}