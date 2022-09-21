package com.example.made_centos_0916.app.article.base;

import com.example.made_centos_0916.app.article.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prod")
public class ProdInitData {
    @Bean
    public CommandLineRunner initDevData(ArticleService articleService) {
        return args -> {
            articleService.write("제목1");
            articleService.write("제목2");
            articleService.write("som");
            articleService.write("title");
        };
    }
}